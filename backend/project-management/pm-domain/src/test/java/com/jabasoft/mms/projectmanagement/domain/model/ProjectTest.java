package com.jabasoft.mms.projectmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;

class ProjectTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(Project.class);
	}

}