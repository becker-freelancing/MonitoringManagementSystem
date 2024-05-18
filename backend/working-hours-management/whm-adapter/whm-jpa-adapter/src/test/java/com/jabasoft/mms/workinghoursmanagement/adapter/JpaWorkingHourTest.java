package com.jabasoft.mms.workinghoursmanagement.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.jabasoft.mms.junit.beans.DynamicJpaEntityTest;

class JpaWorkingHourTest extends DynamicJpaEntityTest {

	@Override
	protected Class<?> createEntity() {

		return JpaWorkingHour.class;
	}

}