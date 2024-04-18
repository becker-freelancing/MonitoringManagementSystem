package com.jabasoft.mms.customermanagement.customer.adapter;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaCustomerTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaCustomer.class;
	}

}