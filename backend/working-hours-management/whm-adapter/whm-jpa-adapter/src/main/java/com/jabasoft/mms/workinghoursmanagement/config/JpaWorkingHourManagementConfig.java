package com.jabasoft.mms.workinghoursmanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	basePackages = JpaWorkingHourManagementConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = JpaWorkingHourManagementConfig.ADAPTER_PACKAGES_FILTER)})
@EntityScan(basePackages = JpaWorkingHourManagementConfig.JPA_ADAPTER_BASE_PACKAGES)
@ComponentScan(
	basePackages = JpaWorkingHourManagementConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.adapter\\..*")})
public class JpaWorkingHourManagementConfig {

	static final String JPA_ADAPTER_BASE_PACKAGES = "com.jabasoft.mms.workinghoursmanagement";
	static final String ADAPTER_PACKAGES_FILTER = ".*\\.adapter\\..*";
}
