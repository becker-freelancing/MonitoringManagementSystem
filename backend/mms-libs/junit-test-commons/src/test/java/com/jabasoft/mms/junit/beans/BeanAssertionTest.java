package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.IntrospectionException;
import java.beans.Introspector;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.opentest4j.AssertionFailedError;

class BeanAssertionTest {

	private BoundedRangeModel expectedModel;
	private BeanAssertion beanAssertion;

	@BeforeEach
	void setUp() {

		expectedModel = new DefaultBoundedRangeModel(15, 5, 0, 100);

		beanAssertion = new BeanAssertion(expectedModel);
	}

	@Test
	void assertBeanClassEquals() {

	}

	@Test
	void assertBeanEquals() {

		BoundedRangeModel equalModel = new DefaultBoundedRangeModel(15, 5, 0, 100);
		beanAssertion.assertBeanEquals(equalModel);

		BoundedRangeModel unequalModel = new DefaultBoundedRangeModel(15, 5, 0, 101);
		assertThrows(AssertionFailedError.class, () -> beanAssertion.assertBeanEquals(unequalModel));
	}

	@Test
	void exceptionOnPropertyAccess() {

		class ExceptionModel extends DefaultBoundedRangeModel {

			public ExceptionModel(int value, int extent, int min, int max) {

				super(value, extent, min, max);
			}

			@Override
			public int getValue() {

				throw new IllegalStateException("TEST");
			}
		}

		beanAssertion = new BeanAssertion(new ExceptionModel(15, 5, 0, 100));

		AssertionFailedError assertionFailedError =
			assertThrows(AssertionFailedError.class, () -> beanAssertion.assertBeanEquals(new ExceptionModel(15, 5, 0, 100)));
		Throwable cause = assertionFailedError.getCause();
		assertTrue(cause instanceof IllegalStateException);
		assertEquals("TEST", cause.getMessage());
	}

	@Test
	void throwsAssertionFailedErrorWhenNoBeanInfoExists() {

		try (MockedStatic<Introspector> mock = Mockito.mockStatic(Introspector.class)) {
			mock.when(() -> Introspector.getBeanInfo(Object.class)).thenThrow(IntrospectionException.class);

			assertThrows(AssertionFailedError.class, () -> new BeanAssertion(new Object()));
		}
	}
}