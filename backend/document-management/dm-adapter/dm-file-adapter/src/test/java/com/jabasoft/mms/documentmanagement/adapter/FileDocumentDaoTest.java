package com.jabasoft.mms.documentmanagement.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import com.jabasoft.mms.settings.api.SettingsPort;
import org.mockito.MockedStatic;

class FileDocumentDaoTest {

	@Test
	void testGetAllDocumentsWithNoExistingDocumentsReturnsEmptyList(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		List<Document> documents = documentDao.getAllDocuments();

		assertEquals(0, documents.size());
	}

	@Test
	void testGetAllDocumentsWithExistingDocumentsReturnsListWithDocuments(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));
		documentDao.saveDocument(getDocument("a\\b", "test45", FileType.PNG));
		documentDao.saveDocument(getDocument("a\\b\\c", "abababa", FileType.PDF));

		List<Document> documents = documentDao.getAllDocuments();

		assertEquals(4, documents.size());
		assertArrayEquals(new byte[]{12, 34, 54, 32}, documents.get(0).getContent());
	}

	@Test
	void testGetAllDocumentsReturnsEmptyListWhenExceptionOccurres(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		try(MockedStatic<Files> mock = mockStatic(Files.class)){
			mock.when(() -> Files.walk(any())).thenThrow(IOException.class);

			List<Document> allDocuments = documentDao.getAllDocuments();

			assertEquals(0, allDocuments.size());
		}
	}

	@Test
	void testGetDocumentReturnsEmptyOptional(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		Optional<Document> document = documentDao.getDocument(12L);

		assertTrue(document.isEmpty());
	}

	@Test
	void testSaveDocumentOverridesDocumentIfDocumentExists(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		Document document = getDocument("a\\b\\c", "abababa", FileType.PDF);

		Optional<Document> saved = documentDao.saveDocument(document);
		assertTrue(saved.isPresent());

		document.setContent(new byte[]{12, 12, 12});
		Optional<Document> actual = documentDao.saveDocument(document);

		assertTrue(actual.isPresent());
		assertArrayEquals(new byte[]{12, 12, 12}, actual.get().getContent());
	}

	@Test
	void testDeleteDocumentWithNotExistingFilePathReturnsEmptyOptional(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		Optional<Document> deleted = documentDao.deleteDocument(new FilePath("/test/"), "test");

		assertTrue(deleted.isEmpty());
	}

	@Test
	void testExistingDocumentWithOtherDocumentsInFileReturnsDeletedDocument(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));
		documentDao.saveDocument(getDocument("a\\b", "test45", FileType.PNG));
		documentDao.saveDocument(getDocument("a\\b", "abababa", FileType.PDF));

		Optional<Document> deleted = documentDao.deleteDocument(new FilePath("a\\b"), "test45");

		assertTrue(deleted.isPresent());

		Document actual = deleted.get();

		assertEquals("a\\b", actual.getPathToDocumentFromRoot().getFilePath(), "FilePath");
		assertEquals("test45", actual.getDocumentName(), "DocumentName");
	}

	@Test
	void testSaveDocumentReturnsEmptyOptionalWhenExceptionOccurres(@TempDir Path tempDir){
		SettingsPort settingsPort = mock(SettingsPort.class);
		when(settingsPort.getLocalDocumentsRootPath()).thenReturn(tempDir);
		FileDocumentDao documentDao = new FileDocumentDao(settingsPort);

		try(MockedStatic<Files> mock = mockStatic(Files.class)){
			mock.when(() -> Files.createFile(any())).thenThrow(IOException.class);

			Optional<Document> allDocuments = documentDao.saveDocument(getDocument("", "test", FileType.PDF));

			assertTrue(allDocuments.isEmpty());
		}
	}


	Document getDocument(String relativePath, String documentName, FileType fileType){

		Document document = new Document();

		document.setDocumentName(documentName);
		document.setFileType(fileType);
		document.setContent(new byte[]{12, 34, 54, 32});
		document.setPathToDocumentFromRoot(new FilePath(relativePath));

		return document;
	}


}