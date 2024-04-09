package com.jabasoft.mms.documentmanagement.adapter;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

class JpaDocumentTest extends DynamicJpaEntityTest {

	@Override
	protected Class<? extends Annotation> columnAnnotation() {

		return Column.class;
	}

	@Override
	protected Class<?> createEntity() {

		return JpaDocument.class;
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