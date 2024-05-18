package com.jabasoft.mms.junit.beans.supplier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.jabasoft.mms.junit.beans.supplier.NotConstructableException;

class NotConstructableExceptionTest {

	@Test
	void testConstructorWithThrowableExists(){

		Throwable throwable = new Throwable();

		NotConstructableException notConstructableException = new NotConstructableException(throwable);

		assertNotNull(notConstructableException);
		assertEquals(notConstructableException.getCause(), throwable);
	}

}