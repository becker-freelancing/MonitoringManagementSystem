package com.jabasoft.mms.projectmanagement.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;

class ProjectDtoTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(ProjectDto.class);
	}

}