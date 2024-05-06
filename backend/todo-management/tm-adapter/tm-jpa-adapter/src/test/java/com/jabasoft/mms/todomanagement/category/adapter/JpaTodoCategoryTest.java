package com.jabasoft.mms.todomanagement.category.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaTodoCategoryTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaTodoCategory.class;
	}

}