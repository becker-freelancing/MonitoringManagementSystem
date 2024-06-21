package com.jabasoft.mms.documentmanagement.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.documentmanagement.MmsDaoImplTest;
import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;

@MmsDaoImplTest
class JpaDocumentDaoTest {

	JpaDocumentDao documentDao;

	@Autowired
	public JpaDocumentDaoTest(JpaDocumentDao documentDao) {

		this.documentDao = documentDao;
	}

	@Test
	void testGetAllDocumentsWithNoExistingDocumentsReturnsEmptyList(){
		
		List<Document> documents = documentDao.getAllDocuments();

		assertEquals(0, documents.size());
	}

	@Test
	void testGetAllDocumentsWithExistingDocumentsReturnsListWithDocuments(){
		
		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));
		documentDao.saveDocument(getDocument("a\\b", "test45", FileType.PNG));
		documentDao.saveDocument(getDocument("a\\b\\c", "abababa", FileType.PDF));

		List<Document> documents = documentDao.getAllDocuments();

		assertEquals(4, documents.size());
		assertArrayEquals(new byte[]{12, 34, 54, 32}, documents.get(0).getContent());
	}

	@Test
	void testGetDocumentReturnsEmptyOptionalWhenNoDocumentExists(){

		documentDao.saveDocument(getDocument("", "test", FileType.PDF));
		documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));
		Optional<Document> saved = documentDao.saveDocument(getDocument("a\\b", "test45", FileType.PNG));
		documentDao.saveDocument(getDocument("a\\b\\c", "abababa", FileType.PDF));

		assertTrue(saved.isPresent());

		Optional<Document> document = documentDao.getDocument(saved.get().getDocumentId());

		assertTrue(document.isPresent());
		Document actual = document.get();

		assertEquals("a\\b", actual.getPathToDocumentFromRoot().getFilePath(), "FilePath");
		assertEquals("test45", actual.getDocumentName(), "DocumentName");
		assertEquals(FileType.PNG, actual.getFileType(), "FileType");
		assertEquals(saved.get().getDocumentId(), actual.getDocumentId(), "DocumentId");
		assertArrayEquals(new byte[]{12, 34, 54, 32}, actual.getContent(), "Content");
	}

	@Test
	void testGetDocumentReturnsOptionalWithDocumentIfDocumentExists(){

		Optional<Document> document = documentDao.getDocument(12L);

		assertTrue(document.isEmpty());
	}

	@Test
	void testSaveDocumentOverridesDocumentIfDocumentExists(){
		
		Document document = getDocument("a\\b\\c", "abababa", FileType.PDF);

		Optional<Document> saved = documentDao.saveDocument(document);
		assertTrue(saved.isPresent());

		Document overrideDocument = saved.get();
		overrideDocument.setContent(new byte[]{12, 12, 12});
		Optional<Document> actual = documentDao.saveDocument(overrideDocument);

		assertTrue(actual.isPresent());
		assertArrayEquals(new byte[]{12, 12, 12}, actual.get().getContent());
	}

	@Test
	void testSaveDocumentReturnsEmptyOptionalOnNullParameter(){

		Optional<Document> actual = documentDao.saveDocument(null);

		assertTrue(actual.isEmpty());
	}

	@Test
	void testDeleteDocumentWithNotExistingFilePathReturnsEmptyOptional(){

		Optional<Document> deleted = documentDao.deleteDocument(new FilePath("/test/"), "test");

		assertTrue(deleted.isEmpty());
	}

	@Test
	void testExistingDocumentWithOtherDocumentsInFileReturnsDeletedDocument(){
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


	Document getDocument(String relativePath, String documentName, FileType fileType){

		Document document = new Document();

		document.setDocumentName(documentName);
		document.setFileType(fileType);
		document.setContent(new byte[]{12, 34, 54, 32});
		document.setPathToDocumentFromRoot(new FilePath(relativePath));

		return document;
	}

}