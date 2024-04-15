package com.jabasoft.mms.customermanagement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
	basePackages = JpaCustomerConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = JpaCustomerConfig.ADAPTER_PACKAGES_FILTER)})
@EntityScan(basePackages = JpaCustomerConfig.JPA_ADAPTER_BASE_PACKAGES)
@ComponentScan(
	basePackages = JpaCustomerConfig.JPA_ADAPTER_BASE_PACKAGES,
	includeFilters = {@ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*\\.adapter\\..*")})
public class JpaCustomerConfig {

	static final String JPA_ADAPTER_BASE_PACKAGES = "com.jabasoft.mms.customermanagement";
	static final String ADAPTER_PACKAGES_FILTER = ".*\\.adapter\\..*";
}
