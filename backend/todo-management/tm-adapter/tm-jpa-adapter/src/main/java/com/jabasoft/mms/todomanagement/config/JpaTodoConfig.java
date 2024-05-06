package com.jabasoft.mms.todomanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	basePackages = JpaTodoConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = JpaTodoConfig.ADAPTER_PACKAGES_FILTER)})
@EntityScan(basePackages = JpaTodoConfig.JPA_ADAPTER_BASE_PACKAGES)
@ComponentScan(
	basePackages = JpaTodoConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.adapter\\..*")})
public class JpaTodoConfig {

	static final String JPA_ADAPTER_BASE_PACKAGES = "com.jabasoft.mms.todomanagement";
	static final String ADAPTER_PACKAGES_FILTER = ".*\\.adapter\\..*";
}
