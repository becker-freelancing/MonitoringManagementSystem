package com.jabasoft.mms.settings.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class DirectoryCannotBeCreatedExceptionTest {

	@Test
	void testConstructor(@TempDir Path path){

		DirectoryCannotBeCreatedException directoryCannotBeCreatedException =
			new DirectoryCannotBeCreatedException(path, new Exception());

		assertEquals(path, directoryCannotBeCreatedException.getDirectory());
	}

}