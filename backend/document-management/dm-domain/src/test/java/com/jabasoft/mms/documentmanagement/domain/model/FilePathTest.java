package com.jabasoft.mms.documentmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.DynamicBeanTest;
import org.junit.jupiter.api.Test;

class FilePathTest extends DynamicBeanTest {

	@Override
	protected Stream<Class<?>> beanClasses() {

		return Stream.of(FilePath.class);
	}

	@Test
	void testIncorrectFormattedFilePathGetsTransformedCorrectlyInConstructor(){
		FilePath filePath = new FilePath("\\a/b\\c\\d/e\\");

		assertEquals("a\\b\\c\\d\\e", filePath.getFilePath());
	}

	@Test
	void testIncorrectFormattedFilePathGetsTransformedCorrectlyInSetter(){
		FilePath filePath = new FilePath();
		filePath.setFilePath("\\a/b\\c\\d/e\\");

		assertEquals("a\\b\\c\\d\\e", filePath.getFilePath());
	}

	@Test
	void testNullFilePathGetsTransformedCorrectlyInConstructor(){
		FilePath filePath = new FilePath(null);

		assertEquals("", filePath.getFilePath());
	}

	@Test
	void testNullFilePathGetsTransformedCorrectlyInSetter(){
		FilePath filePath = new FilePath();
		filePath.setFilePath(null);

		assertEquals("", filePath.getFilePath());
	}
}