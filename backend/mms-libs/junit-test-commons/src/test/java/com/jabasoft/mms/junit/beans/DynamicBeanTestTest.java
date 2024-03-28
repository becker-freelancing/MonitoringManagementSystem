package com.jabasoft.mms.junit.beans;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DynamicBeanTestTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(Bean.class, BeanWithEquals.class, BeanWithToString.class);
	}

	@BeforeEach
	void setUp() {

		setValueGeneratorRegistry(new ReflectiveValueGeneratorRegistry());
	}

	@Test
	void getAndSetOneProperty() throws Exception {

		Bean bean = new Bean();
		testSetAndGetProperty(bean, "name");
	}

	@Test
	void getAndSetOnePropertyNoSuchProperty() throws Exception {

		Bean bean = new Bean();
		Assertions.assertThrows(IllegalArgumentException.class, () -> testSetAndGetProperty(bean, "1234545"));
	}
}
