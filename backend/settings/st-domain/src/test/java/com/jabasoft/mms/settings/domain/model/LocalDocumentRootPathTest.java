package com.jabasoft.mms.settings.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LocalDocumentRootPathTest {

	@Test
	void testConstructorCreatesDirectoriesWhenPathNotExists(@TempDir Path existingPath){

		Path nonExistingDir = Path.of(existingPath.toString(), "nonExistingDir");

		new LocalDocumentRootPath(nonExistingDir);

		assertTrue(Files.exists(nonExistingDir));
	}

	@Test
	void testGetLocalDocumentRootPath(@TempDir Path expected){

		LocalDocumentRootPath rootPath = new LocalDocumentRootPath(expected);

		assertEquals(expected, rootPath.getLocalDocumentRootPath());
	}

}