package com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaEmailTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaEmail.class;
	}

}