package com.jabasoft.mms.documentmanagement.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentId;

@MmsDaoImplTest
class JpaDocumentDaoTest {

	JpaDocumentDao documentDao;

	@Autowired
	public JpaDocumentDaoTest(JpaDocumentDao documentDao) {

		this.documentDao = documentDao;
	}

	@Test
	void testGetAllDocuments() {

		String relativePath = "\\ab\\cd\\test.pdf";
		Document mockDocument = getMockDocument(relativePath);
		documentDao.saveDocument(mockDocument);

		String relativePath2 = "\\cd\\ab\\test.pdf";
		Document mockDocument2 = getMockDocument(relativePath2);
		documentDao.saveDocument(mockDocument2);

		String relativePath3 = "\\ab\\cd\\test2.pdf";
		Document mockDocument3 = getMockDocument(relativePath3);
		documentDao.saveDocument(mockDocument3);

		String relativePath4 = "\\test.pdf";
		Document mockDocument4 = getMockDocument(relativePath4);
		documentDao.saveDocument(mockDocument4);

		List<Document> documents = documentDao.getAllDocuments();

		assertEquals(4, documents.size());
		Set<String> relativePaths = documents.stream().map(Document::getRelativePath).collect(Collectors.toSet());
		assertEquals(Set.of(relativePath, relativePath2, relativePath3, relativePath4), relativePaths);
	}

	@Test
	void testSaveDocumentsOverwritesIfFileExists() {
		String relativePath = "\\ab\\cd\\test.pdf";
		Document mockDocument = getMockDocument(relativePath);
		documentDao.saveDocument(mockDocument);

		String relativePath2 = "\\ab\\cd\\test.pdf";
		Document mockDocument2 = getMockDocument(relativePath2);
		documentDao.saveDocument(mockDocument2);

		List<Document> documents = documentDao.getAllDocuments();

		assertEquals(1, documents.size());
		assertEquals("\\ab\\cd\\test.pdf", documents.get(0).getRelativePath());
	}

	@Test
	void testGetDocumentReturnNull() {

		String relativePath = "\\ab\\cd\\test.pdf";

		Document documentToSave = new Document(relativePath, new byte[0], null);

		DocumentId documentId = documentDao.saveDocument(documentToSave);

		Document actual = documentDao.getDocument(documentId);

		assertNotNull(actual);
		assertEquals(relativePath, actual.getRelativePath());
	}

	@Test
	void testSaveDocument() {
		String relativePath = "\\ab\\cd\\test.pdf";

		Document mockDocument = getMockDocument(relativePath);

		documentDao.saveDocument(mockDocument);
	}

	Document getMockDocument(String relativePath){
		return new Document(relativePath, new byte[0], new DocumentId(UUID.randomUUID().toString()));
	}

}