package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter;

import java.lang.annotation.Annotation;

import com.jabasoft.mms.customermanagement.AbstractJpaEntityTest;
import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

class JpaReasonForContactTest extends AbstractJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaReasonForContact.class;
	}

}