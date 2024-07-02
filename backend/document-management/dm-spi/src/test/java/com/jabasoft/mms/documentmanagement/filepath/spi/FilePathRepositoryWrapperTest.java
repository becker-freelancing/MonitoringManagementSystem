package com.jabasoft.mms.documentmanagement.filepath.spi;

import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doAnswer;

class FilePathRepositoryWrapperTest {


    @Test
    void testConstructorThrowsExceptionWhenNoFilePathRepositoryIsGiven(){
        assertThrows(IllegalArgumentException.class, () -> new FilePathRepositoryWrapper(List.of(), null));
    }

    @Test
    void testConstructorThrowsExceptionWhenRepositoryOneInRepositoryListIsTheDefaultRepository(){

        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo1.class);

        assertThrows(IllegalArgumentException.class, () -> new FilePathRepositoryWrapper(List.of(repo1), repo2));
    }

    @Test
    void testFindAllPathsIsOnlyExecutedOnDefaultRepository(){

        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).findAllPaths();
        when(repo2.findAllPaths()).thenThrow(AssertionFailedError.class);

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2), repo1);

        repository.findAllPaths();

        assertEquals(1, methodCount.get());
    }

    @Test
    void testIsPathCreatableIsOnlyExecutedOnDefaultRepository(){

        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).isPathCreatable(any());
        when(repo2.isPathCreatable(any())).thenThrow(AssertionFailedError.class);

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2), repo1);

        repository.isPathCreatable(null);

        assertEquals(1, methodCount.get());
    }

    @Test
    void testFindAllPathsWithDocumentsIsOnlyExecutedOnDefaultRepository(){

        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).findAllPathsWithDocuments();
        when(repo2.findAllPathsWithDocuments()).thenThrow(AssertionFailedError.class);

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2), repo1);

        repository.findAllPathsWithDocuments();

        assertEquals(1, methodCount.get());
    }

    @Test
    void testHasPathChildrenIsOnlyExecutedOnDefaultRepository(){

        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).hasPathChildren(any());
        when(repo2.hasPathChildren(any())).thenThrow(AssertionFailedError.class);

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2), repo1);

        repository.hasPathChildren(null);

        assertEquals(1, methodCount.get());
    }

    @Test
    void testCreateFileStructureIsExecutedOnAllRepos() throws FileModificationException {
        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);
        FilePathRepository repo3 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).createFileStructure(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo2).createFileStructure(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo3).createFileStructure(any());

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2, repo3), repo1);

        repository.createFileStructure(null);

        assertEquals(3, methodCount.get());
    }

    @Test
    void testDeleteFileStructureIsExecutedOnAllRepos() throws FileModificationException {
        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);
        FilePathRepository repo3 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).deleteFileStructure(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo2).deleteFileStructure(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo3).deleteFileStructure(any());

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2, repo3), repo1);

        repository.deleteFileStructure(null);

        assertEquals(3, methodCount.get());
    }

    @Test
    void testDeleteFileStructureWithChildrenIsExecutedOnAllRepos() throws FileModificationException {
        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);
        FilePathRepository repo3 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).deleteFileStructureWithChildren(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo2).deleteFileStructureWithChildren(any());

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo3).deleteFileStructureWithChildren(any());

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2, repo3), repo1);

        repository.deleteFileStructureWithChildren(null);

        assertEquals(3, methodCount.get());
    }

    @Test
    void testFindAllChildrenFromPathIsOnlyExecutedOnDefaultRepository(){

        FilePathRepository repo1 = mock(FilePathRepo1.class);
        FilePathRepository repo2 = mock(FilePathRepo2.class);

        AtomicInteger methodCount = new AtomicInteger(0);

        doAnswer(invocationOnMock -> {
            methodCount.getAndIncrement();
            return null;
        }).when(repo1).findAllChildrenFromPath(any());
        when(repo2.findAllChildrenFromPath(any())).thenThrow(AssertionFailedError.class);

        FilePathRepositoryWrapper repository = new FilePathRepositoryWrapper(List.of(repo2), repo1);

        repository.findAllChildrenFromPath(null);

        assertEquals(1, methodCount.get());
    }


    static abstract class FilePathRepo1 implements FilePathRepository {

    }

    static abstract class FilePathRepo2 implements FilePathRepository {

    }
}