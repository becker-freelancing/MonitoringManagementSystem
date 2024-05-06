package com.jabasoft.mms.todomanagement.todo.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaTodoTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaTodo.class;
	}

}