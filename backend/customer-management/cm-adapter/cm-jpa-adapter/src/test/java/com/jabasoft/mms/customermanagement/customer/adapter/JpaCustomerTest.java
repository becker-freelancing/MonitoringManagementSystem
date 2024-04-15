package com.jabasoft.mms.customermanagement.customer.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.customermanagement.AbstractJpaEntityTest;

class JpaCustomerTest extends AbstractJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaCustomer.class;
	}

}