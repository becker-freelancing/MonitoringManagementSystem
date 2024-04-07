package com.jabasoft.mms.tools.architecture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.Dependency;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.EvaluationResult;
import com.tngtech.archunit.lang.ViolationHandler;

import compliant.com.jabasoft.CompliantClasses;
import violation.com.jabasoft.ViolatedClasses;
import violation.com.jabasoft.adapter.DomainRepositoryAdapter;
import violation.com.jabasoft.api.ApplicationApi;
import violation.com.jabasoft.application.Application;
import violation.com.jabasoft.autoconfigure.AutoConfiguration;
import violation.com.jabasoft.controller.Controller;
import violation.com.jabasoft.domain.model.DomainModel;
import violation.com.jabasoft.domain.service.DomainService;
import violation.com.jabasoft.spi.DomainRepository;

class HexagonalArchitectureRulesTest {

	private ClassFileImporter importer;

	@BeforeEach
	void setUp() {

		importer = new ClassFileImporter();
	}

	@Test
	void compliant() {

		JavaClasses javaClasses = importer.importPackagesOf(CompliantClasses.class);

		HexagonalArchitectureRules.HEXAGONALE_ARCHITECTURE.check(javaClasses);
	}

	@Test
	void violation() {

		JavaClasses javaClasses = importer.importPackagesOf(ViolatedClasses.class);

		EvaluationResult evaluationResult = HexagonalArchitectureRules.HEXAGONALE_ARCHITECTURE.evaluate(javaClasses);

		DependencyAssertion dependencyAssertion = new DependencyAssertion();
		evaluationResult.handleViolations(dependencyAssertion);

		dependencyAssertion.assertViolation(DomainModel.class).to(DomainService.class);
		dependencyAssertion.assertViolation(DomainModel.class).to(Application.class);
		dependencyAssertion.assertViolation(DomainModel.class).to(ApplicationApi.class);
		dependencyAssertion.assertViolation(DomainModel.class).to(DomainRepository.class);
		dependencyAssertion.assertViolation(DomainModel.class).to(DomainRepositoryAdapter.class);

		dependencyAssertion.assertViolation(DomainService.class).to(Application.class);
		dependencyAssertion.assertViolation(DomainService.class).to(ApplicationApi.class);
		dependencyAssertion.assertViolation(DomainService.class).to(DomainRepository.class);
		dependencyAssertion.assertViolation(DomainService.class).to(DomainRepositoryAdapter.class);

		dependencyAssertion.assertViolation(Application.class).to(Controller.class);
		dependencyAssertion.assertViolation(Application.class).to(DomainRepositoryAdapter.class);

		dependencyAssertion.assertViolation(ApplicationApi.class).to(DomainModel.class);
		dependencyAssertion.assertViolation(ApplicationApi.class).to(DomainService.class);
		dependencyAssertion.assertViolation(ApplicationApi.class).to(DomainRepository.class);
		dependencyAssertion.assertViolation(ApplicationApi.class).to(DomainRepositoryAdapter.class);
		dependencyAssertion.assertViolation(ApplicationApi.class).to(Application.class);
		dependencyAssertion.assertViolation(ApplicationApi.class).to(Controller.class);

		dependencyAssertion.assertViolation(DomainRepository.class).to(DomainService.class);
		dependencyAssertion.assertViolation(DomainRepository.class).to(Application.class);
		dependencyAssertion.assertViolation(DomainRepository.class).to(DomainRepositoryAdapter.class);
		dependencyAssertion.assertViolation(DomainRepository.class).to(ApplicationApi.class);
		dependencyAssertion.assertViolation(DomainRepository.class).to(Controller.class);

		dependencyAssertion.assertViolation(DomainRepositoryAdapter.class).to(DomainService.class);
		dependencyAssertion.assertViolation(DomainRepositoryAdapter.class).to(Application.class);
		dependencyAssertion.assertViolation(DomainRepositoryAdapter.class).to(ApplicationApi.class);
		dependencyAssertion.assertViolation(DomainRepositoryAdapter.class).to(Controller.class);

		dependencyAssertion.assertViolation(Controller.class).to(DomainService.class);
		dependencyAssertion.assertViolation(Controller.class).to(Application.class);
		dependencyAssertion.assertViolation(Controller.class).to(DomainRepository.class);
		dependencyAssertion.assertViolation(Controller.class).to(DomainRepositoryAdapter.class);
		dependencyAssertion.assertViolation(Controller.class).to(DomainModel.class);

		dependencyAssertion.assertViolation(AutoConfiguration.class).to(DomainModel.class);
	}

	static class DependencyAssertion implements ViolationHandler<Dependency> {

		public static interface OngoingAssertion {

			void to(Class<?> toClass);
		}

		private List<Dependency> dependencies = new ArrayList<>();

		@Override
		public void handle(Collection<Dependency> violatingObjects, String message) {

			dependencies.addAll(violatingObjects);
		}

		public OngoingAssertion assertViolation(Class<?> fromClass) {

			return toClass -> {

				List<Dependency> dependencyViolations = dependencies.stream()
					.filter(d -> d.getOriginClass().getFullName().equals(fromClass.getName())).collect(Collectors.toList());
				Optional<Dependency> optionalDependency = dependencyViolations.stream()
					.filter(d -> d.getTargetClass().getFullName().equals(toClass.getName())).findFirst();

				if (!optionalDependency.isPresent()) {
					Assertions.fail("No violation reported from " + fromClass.getName() + " to " + toClass.getName());
				}

				Dependency dependency = optionalDependency.get();
				Assertions.assertEquals(toClass.getName(), dependency.getTargetClass().getFullName());
			};
		}
	}
}
