package com.jabasoft.mms.documentmanagement.adapter;

import com.jabasoft.mms.documentmanagement.MmsDaoImplTest;
import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import com.jabasoft.mms.documentmanagement.domain.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@MmsDaoImplTest
class JpaDocumentDaoTest {

    JpaDocumentDao documentDao;

    @Autowired
    public JpaDocumentDaoTest(JpaDocumentDao documentDao) {

        this.documentDao = documentDao;
    }

    @Test
    void testGetAllDocumentsWithNoExistingDocumentsReturnsEmptyList() {

        List<Document> documents = documentDao.getAllDocuments();

        assertEquals(0, documents.size());
    }

    @Test
    void testGetAllDocumentsWithExistingDocumentsReturnsListWithDocuments() {

        List<Document> expected = List.of(
                getDocument("", "test", FileType.PDF),
                getDocument("test", "test", FileType.DOCX),
                getDocument("a\\b", "test45", FileType.PNG),
                getDocument("a\\b\\c", "abababa", FileType.PDF),
                getDocumentWithCustomer("a\\b", "adjkf", FileType.DOCX, 12L),
                getDocumentWithCustomer("a\\b\\v", "adjkf", FileType.AI, 12L),
                getDocumentWithTags("test\\a", "ajdf", FileType.DOC, Set.of("a", "b", "c")),
                getDocumentWithCustomerIdAndTags("a\\test", "oanfa", FileType._7Z, 123L, Set.of("a", "b", "d", "e")));

        expected.forEach(documentDao::saveDocument);

        List<Document> actual = documentDao.getAllDocuments();

        assertEquals(expected.size(), actual.size());

        for (int i = 0; i < expected.size(); i++) {
            Document doc1 = expected.get(i);
            Document doc2 = actual.get(i);
            assertTrue(equalsWithoutId(doc1, doc2), "\n" + doc1 + "\n" + doc2);
        }
    }

    @Test
    void testGetDocumentReturnsEmptyOptionalWhenNoDocumentExists() {

        documentDao.saveDocument(getDocument("", "test", FileType.PDF));
        documentDao.saveDocument(getDocument("test", "test", FileType.DOCX));
        Optional<Document> saved = documentDao.saveDocument(getDocument("a\\b", "test45", FileType.PNG));
        documentDao.saveDocument(getDocument("a\\b\\c", "abababa", FileType.PDF));

        assertTrue(saved.isPresent());

        Optional<Document> document = documentDao.getDocument(saved.get().getDocumentId());

        assertTrue(document.isPresent());
        Document actual = document.get();

        assertTrue(equalsWithoutId(saved.get(), actual), "\n" + saved.get() + "\n" + actual);
    }

    @Test
    void testGetDocumentReturnsOptionalWithDocumentIfDocumentExists() {

        Optional<Document> document = documentDao.getDocument(12L);

        assertTrue(document.isEmpty());
    }

    @Test
    void testSaveDocumentOverridesDocumentIfDocumentExists() {

        Document document = getDocument("a\\b\\c", "abababa", FileType.PDF);

        Optional<Document> saved = documentDao.saveDocument(document);
        assertTrue(saved.isPresent());

        Document overrideDocument = saved.get();
        overrideDocument.setContent(new byte[]{12, 12, 12});
        Optional<Document> actual = documentDao.saveDocument(overrideDocument);

        assertTrue(actual.isPresent());
        assertTrue(equalsWithoutId(overrideDocument, actual.get()), "\n" + overrideDocument + "\n" + actual.get());
    }

    @Test
    void testSaveDocumentReturnsEmptyOptionalOnNullParameter() {

        Optional<Document> actual = documentDao.saveDocument(null);

        assertTrue(actual.isEmpty());
    }

    @Test
    void testDeleteDocumentWithNotExistingFilePathReturnsEmptyOptional() {

        Optional<Document> deleted = documentDao.deleteDocument(new FilePath("/test/"), "test");

        assertTrue(deleted.isEmpty());
    }

    @Test
    void testExistingDocumentWithOtherDocumentsInFileReturnsDeletedDocument() {
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
    void testExistsDocumentReturnsFalseWhenDocumentDoesNotExist() {

        boolean exists = documentDao.existsDocument(getDocument("root/test", "Test", FileType.PDF));

        assertFalse(exists);
    }

    @Test
    void testExistsDocumentReturnsTrueWhenDocumentDoesExist() {

        Document test = getDocument("root/test", "Test", FileType.PDF);
        Optional<Document> saved = documentDao.saveDocument(test);

        assertTrue(saved.isPresent());

        boolean exists = documentDao.existsDocument(test);

        assertTrue(exists);
    }

    @Test
    void testSetCustomerOnNonExistingDocumentReturnsEmptyOptional() {

        Optional<Document> actual = documentDao.setCustomer(398347L, 12L);

        assertTrue(actual.isEmpty());
    }

    @Test
    void testSetCustomerOnExistingDocumentSetsCustomer() {

        Optional<Document> savedWithoutCustomer = documentDao.saveDocument(getDocument("a\\b", "test", FileType.PNG));

        assertTrue(savedWithoutCustomer.isPresent());
        assertNull(savedWithoutCustomer.get().getCustomerId());

        Optional<Document> withCustomer = documentDao.setCustomer(savedWithoutCustomer.get().getDocumentId(), 12L);

        assertTrue(withCustomer.isPresent());
        assertEquals(12L, withCustomer.get().getCustomerId());
    }

    @Test
    void testResetCustomerOnNonExistingDocumentReturnsEmptyOptional() {

        Optional<Document> actual = documentDao.resetCustomer(398347L);

        assertTrue(actual.isEmpty());
    }

    @Test
    void testResetCustomerOnExistingDocumentResetsCustomer() {
        Optional<Document> savedWithoutCustomer = documentDao.saveDocument(getDocumentWithCustomer("a\\b", "test", FileType.PNG, 12L));

        assertTrue(savedWithoutCustomer.isPresent());
        assertEquals(12L, savedWithoutCustomer.get().getCustomerId());

        Optional<Document> withCustomer = documentDao.resetCustomer(savedWithoutCustomer.get().getDocumentId());

        assertTrue(withCustomer.isPresent());
        assertNull(withCustomer.get().getCustomerId());
    }


    Document getDocument(String relativePath, String documentName, FileType fileType) {

        Document document = new Document();

        document.setDocumentName(documentName);
        document.setFileType(fileType);
        document.setContent(new byte[]{12, 34, 54, 32});
        document.setPathToDocumentFromRoot(new FilePath(relativePath));

        return document;
    }

    Document getDocumentWithCustomer(String relativePath, String documentName, FileType fileType, Long customerId) {

        Document document = getDocument(relativePath, documentName, fileType);

        document.setCustomerId(customerId);

        return document;
    }

    Document getDocumentWithTags(String relativePath, String documentName, FileType fileType, Set<String> tags) {

        Document document = getDocument(relativePath, documentName, fileType);

        document.setTags(tags.stream().map(Tag::new).collect(Collectors.toSet()));

        return document;
    }

    Document getDocumentWithCustomerIdAndTags(String relativePath, String documentName, FileType fileType, Long customerId, Set<String> tags) {

        Document document = getDocumentWithCustomer(relativePath, documentName, fileType, customerId);

        document.setTags(tags.stream().map(Tag::new).collect(Collectors.toSet()));

        return document;
    }

    public boolean equalsWithoutId(Document document1, Document document2) {

        return Objects.equals(document1.getPathToDocumentFromRoot(), document2.getPathToDocumentFromRoot())
                && Objects.equals(document1.getDocumentName(), document2.getDocumentName())
                && Objects.equals(document1.getFileType(), document2.getFileType())
                && Objects.equals(document1.getCustomerId(), document2.getCustomerId())
                && Objects.equals(document1.getTags(), document2.getTags())
                && Arrays.equals(document1.getContent(), document2.getContent());
    }

}