package com.jabasoft.mms.tools.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;

public class CodingStyleRules {

	public static final ArchRule CLASS_RULES = noClasses().should(GeneralCodingRules.ACCESS_STANDARD_STREAMS)
		.orShould(GeneralCodingRules.USE_JAVA_UTIL_LOGGING)
		.orShould(GeneralCodingRules.THROW_GENERIC_EXCEPTIONS)
		.orShould(GeneralCodingRules.USE_JODATIME).allowEmptyShould(true);

	public static final ArchRule FIELD_RULES = noFields()
		.should(GeneralCodingRules.BE_ANNOTATED_WITH_AN_INJECTION_ANNOTATION).allowEmptyShould(true);

	private CodingStyleRules() {

	}
}
