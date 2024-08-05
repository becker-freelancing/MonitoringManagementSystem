package com.jabasoft.mms.documentmanagement.spi;

import com.jabasoft.mms.documentmanagement.domain.model.DocumentWithoutContent;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DocumentRepositoryWrapperTest {

    @Test
    void testConstructorThrowsExceptionWhenNoDocumentRepositoryIsGiven(){
        assertThrows(IllegalArgumentException.class, () -> new DocumentRepositoryWrapper(List.of(), null));
    }

    @Test
    void testConstructorThrowsExceptionWhenRepositoryOneInRepositoryListIsTheDefaultRepository(){

        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo1.class);

        assertThrows(IllegalArgumentException.class, () -> new DocumentRepositoryWrapper(List.of(repo1), repo2));
    }

    @Test
    void testDeleteDocumentIsExecutedOnAllDocuments(){
        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo2.class);
        DocumentRepository repo3 = mock(DocumentRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).deleteDocument(any(), any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo2).deleteDocument(any(), any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo3).deleteDocument(any(), any());

        DocumentRepositoryWrapper repository = new DocumentRepositoryWrapper(List.of(repo2, repo3), repo1);

        repository.deleteDocument(null, null);

        assertEquals(3, methodCount.get());
    }

    @Test
    void testGetAllDocumentsIsOnlyExecutedOnDefaultRepository(){

        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).getAllDocuments();
        when(repo2.getAllDocuments()).thenThrow(AssertionFailedError.class);

        DocumentRepositoryWrapper repository = new DocumentRepositoryWrapper(List.of(repo2), repo1);

        repository.getAllDocuments();

        assertEquals(1, methodCount.get());
    }

    @Test
    void testGetDocumentIsOnlyExecutedOnDefaultRepository(){

        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).getDocument(any());
        when(repo2.getDocument(any())).thenThrow(AssertionFailedError.class);

        DocumentRepositoryWrapper repository = new DocumentRepositoryWrapper(List.of(repo2), repo1);

        repository.getDocument(12L);

        assertEquals(1, methodCount.get());
    }

    @Test
    void testSaveDocumentIsExecutedOnAllDocuments(){
        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo2.class);
        DocumentRepository repo3 = mock(DocumentRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).saveDocument(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo2).saveDocument(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo3).saveDocument(any());

        DocumentRepositoryWrapper repository = new DocumentRepositoryWrapper(List.of(repo2, repo3), repo1);

        repository.saveDocument(null);

        assertEquals(3, methodCount.get());
    }

    @Test
    void testExistsDocumentIsOnlyExecutedOnDefaultRepository(){

        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).existsDocument(any());
        when(repo2.existsDocument(any())).thenThrow(AssertionFailedError.class);

        DocumentRepositoryWrapper repository = new DocumentRepositoryWrapper(List.of(repo2), repo1);

        repository.existsDocument(new DocumentWithoutContent());

        assertEquals(1, methodCount.get());
    }

    @Test
    void testSetCustomerIsOnlyExecutedOnDefaultRepository() {

        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).setCustomer(any(), any());
        when(repo2.setCustomer(any(), any())).thenThrow(AssertionFailedError.class);

        DocumentRepositoryWrapper repository = new DocumentRepositoryWrapper(List.of(repo2), repo1);

        repository.setCustomer(12L, 43L);

        assertEquals(1, methodCount.get());
    }

    @Test
    void testResetCustomerIsOnlyExecutedOnDefaultRepository() {

        DocumentRepository repo1 = mock(DocumentRepo1.class);
        DocumentRepository repo2 = mock(DocumentRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).resetCustomer(any());
        when(repo2.resetCustomer(any())).thenThrow(AssertionFailedError.class);

        DocumentRepositoryWrapper repository = new DocumentRepositoryWrapper(List.of(repo2), repo1);

        repository.resetCustomer(12L);

        assertEquals(1, methodCount.get());
    }

    static abstract class DocumentRepo1 implements DocumentRepository {

    }

    static abstract class DocumentRepo2 implements DocumentRepository {

    }
}