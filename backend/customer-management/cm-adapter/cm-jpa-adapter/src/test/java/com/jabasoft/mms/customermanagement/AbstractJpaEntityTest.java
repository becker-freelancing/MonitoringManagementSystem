package com.jabasoft.mms.customermanagement;

import java.lang.annotation.Annotation;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public abstract class AbstractJpaEntityTest extends DynamicJpaEntityTest {

	@Override
	protected Class<? extends Annotation> columnAnnotation() {

		return Column.class;
	}

	@Override
	protected Class<? extends Annotation> entityAnnotation() {

		return Entity.class;
	}

	@Override
	protected Class<? extends Annotation> tableAnnotation() {

		return Table.class;
	}

}
