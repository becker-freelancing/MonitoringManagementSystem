package com.jabasoft.mms.customermanagement.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;

class AddressDtoTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(AddressDto.class);
	}

}