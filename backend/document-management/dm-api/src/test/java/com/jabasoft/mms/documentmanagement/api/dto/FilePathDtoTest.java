package com.jabasoft.mms.documentmanagement.api.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.jabasoft.mms.documentmanagement.dto.FilePathDto;
import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

class FilePathDtoTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(FilePathDto.class);
	}

	@Test
	void testConstrocutorWithParameterSetsFilePath(){
		FilePathDto filePathDto = new FilePathDto("a/b/c");

		assertEquals("a/b/c", filePathDto.getFilePath());
	}
}