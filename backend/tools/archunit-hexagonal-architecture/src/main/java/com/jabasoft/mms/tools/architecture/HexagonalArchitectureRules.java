package com.jabasoft.mms.tools.architecture;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

public class HexagonalArchitectureRules {

	public static final ArchRule HEXAGONALE_ARCHITECTURE;

	static {
		HEXAGONALE_ARCHITECTURE = Architectures.layeredArchitecture()
			.layer("domain.model").definedBy("..domain.model..") //
			.layer("domain.service").definedBy("..domain.service..") //
			.layer("spi").definedBy("..spi..") //
			.layer("api").definedBy("..api..") //
			.layer("application").definedBy("..application..")//
			.layer("adapter").definedBy("..adapter..")//
			.layer("controller").definedBy("..controller..")//
			.layer("spring-autoconfigure").definedBy("..autoconfigure..")//
			.withOptionalLayers(true) //
			.whereLayer("domain.model").mayOnlyBeAccessedByLayers("domain.service", "application", "spi", "adapter") //
			.whereLayer("domain.service").mayOnlyBeAccessedByLayers("application", "spring-autoconfigure") //
			.whereLayer("application").mayOnlyBeAccessedByLayers("spring-autoconfigure") //
			.whereLayer("spi").mayOnlyBeAccessedByLayers("application", "adapter", "spring-autoconfigure") //
			.whereLayer("adapter").mayOnlyBeAccessedByLayers("spring-autoconfigure") //
			.whereLayer("api").mayOnlyBeAccessedByLayers("application", "controller", "spring-autoconfigure") //
			.whereLayer("controller").mayOnlyBeAccessedByLayers("spring-autoconfigure") //
			.whereLayer("spring-autoconfigure").mayNotBeAccessedByAnyLayer();
	}

	private HexagonalArchitectureRules() {

	}
}
