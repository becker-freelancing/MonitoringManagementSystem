package com.jabasoft.mms.projectmanagement.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaProjectTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaProject.class;
	}

}