package com.jabasoft.mms.tools.architecture;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.inject.Inject;
import com.jabasoft.mms.tools.architecture.coding.CompliantCodingStyle;
import com.jabasoft.mms.tools.architecture.coding.ViolatedCodingStyle;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.EvaluationResult;
import com.tngtech.archunit.lang.FailureReport;
import com.tngtech.archunit.lang.ViolationHandler;

class CodingStyleRulesTest {

	private ClassFileImporter importer;

	@BeforeEach
	void setUp() {

		importer = new ClassFileImporter();
	}

	@Test
	void compliant() {

		CodingStyleRules.FIELD_RULES.check(importer.importClasses(CompliantCodingStyle.class));
		CodingStyleRules.CLASS_RULES.check(importer.importClasses(CompliantCodingStyle.class));
	}

	@Test
	void violated() {

		EvaluationResult fieldEvaluationResult =
			CodingStyleRules.FIELD_RULES.evaluate(importer.importClasses(ViolatedCodingStyle.class));
		FieldAssertion fieldAssertion = new FieldAssertion();
		fieldEvaluationResult.handleViolations(fieldAssertion);

		fieldAssertion.assertViolation(ViolatedCodingStyle.class, "autowired").hasAnnotation(Inject.class);
		fieldAssertion.assertViolation(ViolatedCodingStyle.class, "autowired").hasAnnotation(javax.inject.Inject.class);
		fieldAssertion.assertViolation(ViolatedCodingStyle.class, "autowired").hasAnnotation(Autowired.class);
		fieldAssertion.assertViolation(ViolatedCodingStyle.class, "autowired").hasAnnotation(Value.class);
		fieldAssertion.assertViolation(ViolatedCodingStyle.class, "autowired").hasAnnotation(Resource.class);

		EvaluationResult classEvaluationResult =
			CodingStyleRules.CLASS_RULES.evaluate(importer.importClasses(ViolatedCodingStyle.class));

		FailureReport failureReport = classEvaluationResult.getFailureReport();

		assertTrue(!failureReport.isEmpty());
		assertTrue(isViolationIn("org.joda.time.DateTime", failureReport));
		assertTrue(isViolationIn("java.lang.Exception", failureReport));
		assertTrue(isViolationIn("java.lang.RuntimeException", failureReport));
		assertTrue(isViolationIn("java.lang.Throwable", failureReport));
		assertTrue(isViolationIn("logger", failureReport));
		assertTrue(isViolationIn("java.lang.System.err", failureReport));
		assertTrue(isViolationIn("java.lang.System.out", failureReport));
	}

	private boolean isViolationIn(String violation, FailureReport report) {

		return report.getDetails().stream().anyMatch(m -> m.contains(violation));
	}

	static class FieldAssertion implements ViolationHandler<JavaField> {

		public static interface OngoingAssertion {

			void hasAnnotation(Class<?> toClass);
		}

		private List<JavaField> violatedFields = new ArrayList<>();

		@Override
		public void handle(Collection<JavaField> violatingObjects, String message) {

			violatedFields.addAll(violatingObjects);
		}

		public OngoingAssertion assertViolation(Class<?> type, String fieldName) {

			return annotationType -> {
				JavaField violatedField = violatedFields.stream().filter(f -> f.getOwner().getFullName().equals(type.getName()))
					.filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);
				if (violatedField == null) {
					Assertions.fail("No violation of " + type.getSimpleName() + "." + fieldName + " exists");
				}

				JavaAnnotation<JavaField> annotationOfType = violatedField.getAnnotationOfType(annotationType.getName());
				Assertions.assertNotNull(annotationOfType);
			};
		}
	}
}