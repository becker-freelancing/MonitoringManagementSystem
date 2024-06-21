package com.jabasoft.mms.documentmanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	basePackages = JpaDocumentsConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = JpaDocumentsConfig.ADAPTER_PACKAGES_FILTER)})
@EntityScan(basePackages = JpaDocumentsConfig.JPA_ADAPTER_BASE_PACKAGES)
@ComponentScan(
	basePackages = JpaDocumentsConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.adapter\\..*")})
public class JpaDocumentsConfig {

	static final String JPA_ADAPTER_BASE_PACKAGES = "com.jabasoft.mms.documentmanagement";
	static final String ADAPTER_PACKAGES_FILTER = ".*\\.adapter\\..*";

}
