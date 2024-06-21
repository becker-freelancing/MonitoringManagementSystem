package com.jabasoft.mms.documentmanagement.api.dto;

import java.util.stream.Stream;

import com.jabasoft.mms.documentmanagement.dto.DocumentDto;
import com.jabasoft.mms.junit.beans.DynamicBeanTest;

class DocumentDtoTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(DocumentDto.class);
	}

}