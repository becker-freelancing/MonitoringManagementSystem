package com.jabasoft.mms.todomanagement.dto;

import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;

class TodoCategoryDtoTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(TodoCategoryDto.class);
	}

}