package com.jabasoft.mms.junit.beans.supplier.setter;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;


class RandomSetterBeanCreatorTest {

	@Test
	void testCreateBeans() {

		List<Bean> beans = new RandomSetterBeanCreator<Bean>() {

			@Override
			protected Class<? extends Bean> getBeanClass() {

				return Bean.class;
			}
		}.createBeans();

		assertEquals(3, beans.size());
	}
}