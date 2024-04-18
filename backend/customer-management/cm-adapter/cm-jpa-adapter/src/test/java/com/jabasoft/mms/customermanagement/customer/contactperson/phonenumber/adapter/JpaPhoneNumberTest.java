package com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaPhoneNumberTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaPhoneNumber.class;
	}

}