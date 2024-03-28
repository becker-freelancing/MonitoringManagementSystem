package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import org.opentest4j.AssertionFailedError;

public class BeanAssertion {

	private final BeanInfo beanInfo;
	private final Object expectedBean;

	public static void assertBeanClassEquals(Object expectedBean, Object actualBean) {

		Class<?> expectedBeanClass = expectedBean.getClass();
		Class<?> actualBeanClass = actualBean.getClass();
		assertEquals(expectedBeanClass, actualBeanClass, "bean class");
	}

	public BeanAssertion(Object expectedBean) {

		assertNotNull(expectedBean, "expected bean");
		this.expectedBean = expectedBean;

		try {
			beanInfo = Introspector.getBeanInfo(expectedBean.getClass());
		}
		catch (IntrospectionException e) {
			throw new AssertionFailedError("BeanInfo", e);
		}
	}

	public void assertBeanEquals(Object actualBean) {

		assertNotNull(actualBean, "actual bean");

		assertBeanClassEquals(expectedBean, actualBean);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			assertPropertyEquals(propertyDescriptor, actualBean);
		}
	}

	private void assertPropertyEquals(PropertyDescriptor propertyDescriptor, Object actualBean) {

		Method readMethod = propertyDescriptor.getReadMethod();

		try {
			Object expectedPropertyValue = readMethod.invoke(expectedBean);
			Object actualPropertyValue = readMethod.invoke(actualBean);

			Class<?> propertyType = propertyDescriptor.getPropertyType();
			if (propertyType.isArray()) {
				assertArrayEquals(
					(Object[]) expectedPropertyValue,
					(Object[]) actualPropertyValue,
					propertyMsg(propertyDescriptor));
			} else {
				assertEquals(expectedPropertyValue, actualPropertyValue, propertyMsg(propertyDescriptor));
			}
		}
		catch (InvocationTargetException | IllegalAccessException e) {
			Throwable cause = e.getCause();
			if (e instanceof InvocationTargetException) {
				cause = ((InvocationTargetException) e).getTargetException();
			}
			throw new AssertionFailedError("Unable to read property " + propertyDescriptor.getName(), cause);
		}
	}

	private Supplier<String> propertyMsg(PropertyDescriptor propertyDescriptor) {

		return () -> {
			String propertyName = propertyDescriptor.getName();
			BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
			Class<?> beanClass = beanDescriptor.getBeanClass();
			return beanClass.getName() + "." + propertyName;
		};
	}
}
