package com.jabasoft.mms.customermanagement.customer.address.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaAddressTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaAddress.class;
	}

}