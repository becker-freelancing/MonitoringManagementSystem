package com.jabasoft.mms.junit.beans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NotConstructableExceptionTest {

	@Test
	void testConstructorWithThrowableExists(){

		Throwable throwable = new Throwable();

		NotConstructableException notConstructableException = new NotConstructableException(throwable);

		assertNotNull(notConstructableException);
		assertEquals(notConstructableException.getCause(), throwable);
	}

}