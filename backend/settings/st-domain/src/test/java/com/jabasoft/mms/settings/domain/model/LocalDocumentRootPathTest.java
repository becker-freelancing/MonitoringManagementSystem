package com.jabasoft.mms.settings.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LocalDocumentRootPathTest {

	@Test
	void testConstructorThrowsExceptionWhenPathNotExists(@TempDir Path existingPath){

		Path nonExistingDir = Path.of(existingPath.toString(), "nonExistingDir");

		assertThrows(DirectoryCannotBeCreatedException.class, () -> new LocalDocumentRootPath(nonExistingDir));
	}

	@Test
	void testGetLocalDocumentRootPath(@TempDir Path expected){

		LocalDocumentRootPath rootPath = new LocalDocumentRootPath(expected);

		assertEquals(expected, rootPath.getLocalDocumentRootPath());
	}

}