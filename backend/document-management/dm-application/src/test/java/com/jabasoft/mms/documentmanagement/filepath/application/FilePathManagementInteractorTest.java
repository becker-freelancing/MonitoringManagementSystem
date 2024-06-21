package com.jabasoft.mms.documentmanagement.filepath.application;

import com.jabasoft.mms.documentmanagement.api.dto.*;
import com.jabasoft.mms.documentmanagement.dto.*;
import com.jabasoft.mms.documentmanagement.error.ApiFileModificationException;
import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepository;
import com.jabasoft.mms.documentmanagement.domain.service.filepath.FilePathServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FilePathManagementInteractorTest {

    FilePathManagementInteractor interactor;
    FilePathRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(FilePathRepository.class);
        interactor = new FilePathManagementInteractor(repository, new FilePathServiceImpl());
    }

    @Test
    void testGetFileStructureReturnsEmptyOptionalWhenNoFilesExist() {
        when(repository.findAllPaths()).thenReturn(Set.of());

        Optional<FileStructureDto> fileStructure = interactor.getFileStructure();

        assertTrue(fileStructure.isEmpty());
    }

    @Test
    void testGetFileStructureReturnsFileStructureWithSingleRootWhenOnlyOneDocumentExistsInRoot() {
        when(repository.findAllPaths()).thenReturn(Set.of(new FilePath("root")));

        Optional<FileStructureDto> fileStructure = interactor.getFileStructure();

        assertTrue(fileStructure.isPresent());

        FileStructureDto actual = fileStructure.get();

        assertEquals("root", actual.getCurrent());
        assertEquals(0, actual.getChildren().size());
    }

    @Test
    void testGetFileStructureReturnsFileStructureWithOnePathWhenOnlyOneDocumentExistsInSubPath() {
        when(repository.findAllPaths()).thenReturn(Set.of(new FilePath("root/a")));

        Optional<FileStructureDto> fileStructure = interactor.getFileStructure();

        assertTrue(fileStructure.isPresent());

        FileStructureDto actual = fileStructure.get();

        assertEquals("root", actual.getCurrent());

        List<FileStructureDto> children = actual.getChildren();
        assertEquals(1, children.size());
        assertEquals("a", children.get(0).getCurrent());
    }

    @Test
    void testGetFileStructureReturnsFileStructureWhenMultipleDocumentsExist() {
        when(repository.findAllPaths()).thenReturn(Set.of("root/a", "root/a/b", "root/b/c").stream().map(FilePath::new).collect(Collectors.toSet()));

        Optional<FileStructureDto> fileStructure = interactor.getFileStructure();

        assertTrue(fileStructure.isPresent());

        FileStructureDto actual = fileStructure.get();

        assertEquals("root", actual.getCurrent());
        assertEquals(2, actual.getChildren().size());

        FileStructureDto child1 = actual.getChildren().get(0);
        FileStructureDto child2 = actual.getChildren().get(1);

        assertEquals(1, child1.getChildren().size());
        assertEquals(1, child2.getChildren().size());

        assertEquals(0, child1.getChildren().get(0).getChildren().size());
        assertEquals(0, child2.getChildren().get(0).getChildren().size());
    }


    @Test
    void testGetFileStructureWithDocumentsReturnsEmptyOptionalWhenNoFilesExist() {
        when(repository.findAllPathsWithDocuments()).thenReturn(Set.of());

        Optional<FileStructureWithDocumentsDto> fileStructure = interactor.getFileStructureWithDocuments();

        assertTrue(fileStructure.isEmpty());
    }

    @Test
    void testGetFileStructureWithDocumentsReturnsFileStructureWithSingleRootWhenOnlyOneDocumentExistsInRoot() {
        when(repository.findAllPathsWithDocuments()).thenReturn(Set.of(
                getFilePathWithDocument("root", "Test")
        ));

        Optional<FileStructureWithDocumentsDto> fileStructure = interactor.getFileStructureWithDocuments();

        assertTrue(fileStructure.isPresent());

        FileStructureWithDocumentsDto actual = fileStructure.get();

        assertEquals("root", actual.getCurrent());
        assertEquals(0, actual.getChildren().size());
        assertEquals(1, actual.getDocuments().size());

        DocumentWithoutContentDto document = actual.getDocuments().get(0);

        assertEquals(FileTypeDto.PDF, document.getFileType());
        assertEquals("Test", document.getDocumentName());
        assertEquals("root", document.getPathToDocumentFromRoot().getFilePath());
    }

    @Test
    void testGetFileStructureWithDocumentsReturnsFileStructureWithOnePathWhenOnlyOneDocumentExistsInSubPath() {
        when(repository.findAllPathsWithDocuments()).thenReturn(Set.of(
                getFilePathWithDocument("root/a", "Test")
        ));

        Optional<FileStructureWithDocumentsDto> fileStructure = interactor.getFileStructureWithDocuments();

        assertTrue(fileStructure.isPresent());

        FileStructureWithDocumentsDto actual = fileStructure.get();

        assertEquals("root", actual.getCurrent());

        List<FileStructureWithDocumentsDto> children = actual.getChildren();
        assertEquals(1, children.size());
        assertEquals("a", children.get(0).getCurrent());


        DocumentWithoutContentDto document = children.get(0).getDocuments().get(0);

        assertEquals(FileTypeDto.PDF, document.getFileType());
        assertEquals("Test", document.getDocumentName());
        assertEquals("root\\a", document.getPathToDocumentFromRoot().getFilePath());
    }

    @Test
    void testGetFileStructureWithDocumentsReturnsFileStructureWhenMultipleDocumentsExist() {
        when(repository.findAllPathsWithDocuments()).thenReturn(Set.of(
                getFilePathWithDocument("root/a", "ABC"),
                getFilePathWithDocument("root/a", "CDE"),
                getFilePathWithDocument("root/a/b", "CDE"),
                getFilePathWithDocument("root/b/c", "FGH")));

        Optional<FileStructureWithDocumentsDto> fileStructure = interactor.getFileStructureWithDocuments();

        assertTrue(fileStructure.isPresent());

        FileStructureWithDocumentsDto actual = fileStructure.get();

        assertEquals("root", actual.getCurrent());
        assertEquals(2, actual.getChildren().size());

        FileStructureWithDocumentsDto a = childByName(actual, "a");
        FileStructureWithDocumentsDto b = childByName(actual, "b");

        assertEquals(1, a.getChildren().size());
        assertEquals(2, a.getDocuments().size());
        assertEquals(1, b.getChildren().size());
        assertEquals(0, b.getDocuments().size());

        assertEquals(0, a.getChildren().get(0).getChildren().size());
        assertEquals(1, a.getChildren().get(0).getDocuments().size());
        assertEquals(0, b.getChildren().get(0).getChildren().size());
        assertEquals(1, b.getChildren().get(0).getDocuments().size());
    }

    static FileStructureWithDocumentsDto childByName(FileStructureWithDocumentsDto parent, String name) {

        for (FileStructureWithDocumentsDto child : parent.getChildren()) {
            if(child.getCurrent().equals(name)){
                return child;
            }
        }

        throw new AssertionFailedError("Child with name " + name + " not found");
    }


    static DocumentWithoutContentDto documentByName(FileStructureWithDocumentsDto parent, String name) {

        for (DocumentWithoutContentDto document : parent.getDocuments()) {
            if(document.getDocumentName().equals(name)){
                return document;
            }
        }

        throw new AssertionFailedError("Document with name " + name + " not found");
    }

    @Test
    void testCreateFileStructureThrowsExceptionWhenAdapterThrowsException() throws FileModificationException {
        when(repository.isPathCreatable(any())).thenReturn(true);
        doThrow(FileModificationException.class).when(repository).createFileStructure(any());

        assertThrows(ApiFileModificationException.class, () -> interactor.createFileStructure(new FilePathDto("root/a/b")));
    }

    @Test
    void testCreateFileStructureReturnsEmptyOptionalIfPathIsNotCreatable() throws ApiFileModificationException {
        when(repository.isPathCreatable(any())).thenReturn(false);

        Optional<FileStructureDto> actual = interactor.createFileStructure(new FilePathDto("a/b/c"));

        assertTrue(actual.isEmpty());
    }

    @Test
    void testCreateFileStructureReturnsOptionalWithFileStructureIfPathIsCreatable() throws ApiFileModificationException, FileModificationException {
        when(repository.isPathCreatable(any())).thenReturn(true);
        doNothing().when(repository).createFileStructure(any());
        when(repository.findAllPaths()).thenReturn(Set.of(new FilePath("a/b/c")));

        Optional<FileStructureDto> actual = interactor.createFileStructure(new FilePathDto("a/b/c"));

        assertTrue(actual.isPresent());

        FileStructureDto fileStructureDto = actual.get();

        assertEquals("root", fileStructureDto.getCurrent());
        assertEquals(1, fileStructureDto.getChildren().size());

        FileStructureDto a = fileStructureDto.getChildren().get(0);
        assertEquals("a", a.getCurrent());
        assertEquals(1, a.getChildren().size());

        FileStructureDto b = a.getChildren().get(0);
        assertEquals("b", b.getCurrent());
        assertEquals(1, b.getChildren().size());

        FileStructureDto c = b.getChildren().get(0);
        assertEquals("c", c.getCurrent());
        assertEquals(0, c.getChildren().size());
    }

    @Test
    void testDeleteFileStructureThrowsExceptionWhenAdapterThrowsException() throws FileModificationException {
        when(repository.hasPathChildren(any())).thenReturn(false);
        doThrow(FileModificationException.class).when(repository).deleteFileStructure(any());

        assertThrows(ApiFileModificationException.class, () -> interactor.deleteFileStructure(new FilePathDto("root/a/b")));
    }

    @Test
    void testDeleteFileStructureReturnsEmptyOptionalIfPathHasChildren() throws ApiFileModificationException {
        when(repository.hasPathChildren(any())).thenReturn(true);

        Optional<FileStructureDto> actual = interactor.deleteFileStructure(new FilePathDto("a/b/c"));

        assertTrue(actual.isEmpty());
    }

    @Test
    void testDeleteFileStructureReturnsOptionalWithFileStructureIfPathHasNoChildren() throws ApiFileModificationException, FileModificationException {
        when(repository.hasPathChildren(any())).thenReturn(false);
        doNothing().when(repository).deleteFileStructure(any());
        when(repository.findAllPaths()).thenReturn(Set.of(new FilePath("a/b/a")));

        Optional<FileStructureDto> actual = interactor.deleteFileStructure(new FilePathDto("a/b/c"));

        assertTrue(actual.isPresent());

        FileStructureDto fileStructureDto = actual.get();

        assertEquals("root", fileStructureDto.getCurrent());
        assertEquals(1, fileStructureDto.getChildren().size());

        FileStructureDto a = fileStructureDto.getChildren().get(0);
        assertEquals("a", a.getCurrent());
        assertEquals(1, a.getChildren().size());

        FileStructureDto b = a.getChildren().get(0);
        assertEquals("b", b.getCurrent());
        assertEquals(1, b.getChildren().size());

        FileStructureDto c = b.getChildren().get(0);
        assertEquals("a", c.getCurrent());
        assertEquals(0, c.getChildren().size());
    }

    @Test
    void testDeleteFileStructureWithChildrenThrowsExceptionWhenAdapterThrowsException() throws FileModificationException {
        when(repository.hasPathChildren(any())).thenReturn(false);
        doThrow(FileModificationException.class).when(repository).deleteFileStructureWithChildren(any());

        assertThrows(ApiFileModificationException.class, () -> interactor.deleteFileStructureWithChildren(new FilePathDto("root/a/b")));
    }

    @Test
    void testDeleteFileStructureWithChildrenReturnsOptionalWithFileStructureIfPathIsDeletable() throws ApiFileModificationException, FileModificationException {
        when(repository.hasPathChildren(any())).thenReturn(false);
        doNothing().when(repository).deleteFileStructureWithChildren(any());
        when(repository.findAllPaths()).thenReturn(Set.of(new FilePath("a/b/a")));

        Optional<FileStructureDto> actual = interactor.deleteFileStructureWithChildren(new FilePathDto("a/b/c"));

        assertTrue(actual.isPresent());

        FileStructureDto fileStructureDto = actual.get();

        assertEquals("root", fileStructureDto.getCurrent());
        assertEquals(1, fileStructureDto.getChildren().size());

        FileStructureDto a = fileStructureDto.getChildren().get(0);
        assertEquals("a", a.getCurrent());
        assertEquals(1, a.getChildren().size());

        FileStructureDto b = a.getChildren().get(0);
        assertEquals("b", b.getCurrent());
        assertEquals(1, b.getChildren().size());

        FileStructureDto c = b.getChildren().get(0);
        assertEquals("a", c.getCurrent());
        assertEquals(0, c.getChildren().size());
    }

    static FilePathWithDocument getFilePathWithDocument(String filePath, String documentName){

        DocumentWithoutContent documentWithoutContent = new DocumentWithoutContent();
        documentWithoutContent.setDocumentName(documentName);
        documentWithoutContent.setFileType(FileType.PDF);
        documentWithoutContent.setPathToDocumentFromRoot(new FilePath(filePath));

        return new FilePathWithDocument(filePath, documentWithoutContent);
    }
}