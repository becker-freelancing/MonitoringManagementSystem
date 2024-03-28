package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public abstract class DynamicJpaEntityTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(createEntity());
	}

	protected abstract Class<? extends Annotation> columnAnnotation();

	protected abstract Class<?> createEntity();

	protected abstract Class<? extends Annotation> entityAnnotation();

	protected abstract Class<? extends Annotation> tableAnnotation();

	@Test
	void testAttributesAreAnnotatedWithColumn() {

		Class<?> entity = createEntity();
		Class<? extends Annotation> columnAnnotation = columnAnnotation();

		for (Field field : entity.getDeclaredFields()) {
			assertTrue(
				field.isAnnotationPresent(columnAnnotation),
				"Field " + field.getName() + " is annotated with " + columnAnnotation);
		}
	}

	@Test
	void testClassIsAnnotatedWithEntity() {

		Class<?> entity = createEntity();
		Class<? extends Annotation> entityAnnotation = entityAnnotation();
		assertTrue(entity.isAnnotationPresent(entityAnnotation), "Class is annotated with " + entityAnnotation);
	}

	@Test
	void testClassIsAnnotatedWithTable() {

		Class<?> entity = createEntity();
		Class<? extends Annotation> tableAnnotation = tableAnnotation();
		assertTrue(entity.isAnnotationPresent(tableAnnotation), "Class is annotated with " + tableAnnotation);
	}

}
