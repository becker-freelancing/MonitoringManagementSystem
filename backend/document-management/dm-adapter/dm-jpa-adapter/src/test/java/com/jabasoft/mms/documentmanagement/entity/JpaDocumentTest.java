package com.jabasoft.mms.documentmanagement.entity;

import java.lang.annotation.Annotation;

import com.jabasoft.mms.documentmanagement.entity.JpaDocument;
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