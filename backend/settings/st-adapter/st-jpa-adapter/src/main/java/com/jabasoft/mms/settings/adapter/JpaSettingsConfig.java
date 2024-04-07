package com.jabasoft.mms.settings.adapter;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	basePackages = JpaSettingsConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = JpaSettingsConfig.ADAPTER_PACKAGES_FILTER)})
@EntityScan(basePackages = JpaSettingsConfig.JPA_ADAPTER_BASE_PACKAGES)
@ComponentScan(
	basePackages = JpaSettingsConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.adapter\\..*")})
class JpaSettingsConfig {

	static final String JPA_ADAPTER_BASE_PACKAGES = "com.jabasoft.mms.settings";
	static final String ADAPTER_PACKAGES_FILTER = ".*\\.adapter\\..*";

}
