package com.jabasoft.mms.projectmanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	basePackages = JpaProjectManagementConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = JpaProjectManagementConfig.ADAPTER_PACKAGES_FILTER)})
@EntityScan(basePackages = JpaProjectManagementConfig.JPA_ADAPTER_BASE_PACKAGES)
@ComponentScan(
	basePackages = JpaProjectManagementConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.adapter\\..*")})
public class JpaProjectManagementConfig {

	static final String JPA_ADAPTER_BASE_PACKAGES = "com.jabasoft.mms.projectmanagement";
	static final String ADAPTER_PACKAGES_FILTER = ".*\\.adapter\\..*";
}
