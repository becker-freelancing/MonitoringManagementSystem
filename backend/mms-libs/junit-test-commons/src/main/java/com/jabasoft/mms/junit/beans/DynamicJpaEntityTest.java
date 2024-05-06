package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

public abstract class DynamicJpaEntityTest extends DynamicBeanTest {

	protected Class<? extends Annotation> tableAnnotation(){
		return Table.class;
	}

	protected Class<? extends Annotation> entityAnnotation(){
		return Entity.class;
	}

	protected Class<? extends Annotation> columnAnnotation(){
		return Column.class;
	}

	protected Class<? extends Annotation> oneToOneAnnotation(){
		return OneToOne.class;
	}

	protected Class<? extends Annotation> oneToManyAnnotation(){
		return OneToMany.class;
	}

	protected Class<? extends Annotation> manyToOneAnnotation(){
		return ManyToOne.class;
	}

	protected Class<? extends Annotation> joinTableAnnotation(){
		return JoinTable.class;
	}

	protected Class<? extends Annotation> joinColumnAnnotation(){
		return JoinColumn.class;
	}

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(createEntity());
	}

	protected abstract Class<?> createEntity();

	@Test
	void testAttributesAreCorrectAnnotated() {

		Class<?> entity = createEntity();

		for (Field field : entity.getDeclaredFields()) {
			List<Class<? extends Annotation>> expectedAnnotations = new ArrayList<>();
			if(field.isAnnotationPresent(columnAnnotation())) {
				expectedAnnotations.add(columnAnnotation());
			} else if(field.isAnnotationPresent(oneToOneAnnotation())){
				expectedAnnotations.add(oneToOneAnnotation());
				expectedAnnotations.add(joinColumnAnnotation());
			} else if(field.isAnnotationPresent(oneToManyAnnotation())){
				expectedAnnotations.add(oneToManyAnnotation());
				expectedAnnotations.add(joinTableAnnotation());
			} else if (field.isAnnotationPresent(manyToOneAnnotation())) {
				expectedAnnotations.add(manyToOneAnnotation());
				expectedAnnotations.add(joinColumnAnnotation());
			}

			assertFalse(expectedAnnotations.isEmpty(), "Min one Annotation needed for Field " + field.getName());

			for (Class<? extends Annotation> expectedAnnotation : expectedAnnotations) {
				assertTrue(field.isAnnotationPresent(expectedAnnotation),
					"Field " + field.getName() + " is annotated with " + expectedAnnotation);
			}

		}
	}

	@Test
	void testClassIsAnnotatedWithEntity() {

		Class<?> entity = createEntity();
		assertTrue(entity.isAnnotationPresent(entityAnnotation()), "Class is annotated with " + entityAnnotation());
	}

	@Test
	void testClassIsAnnotatedWithTable() {

		Class<?> entity = createEntity();
		assertTrue(entity.isAnnotationPresent(tableAnnotation()), "Class is annotated with " + tableAnnotation());
	}

}
