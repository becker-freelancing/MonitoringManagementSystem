package com.jabasoft.mms.documentmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FileTypeTest {

	@Test
	void testConstructorSetsCorrectAttribute() {

		FileType pdf = new FileType("pdf");

		assertEquals("pdf", pdf.getFileEnding());
	}

}