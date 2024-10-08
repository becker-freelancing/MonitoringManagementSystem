package com.jabasoft.mms.documentmanagement.application;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import com.jabasoft.mms.documentmanagement.domain.model.Tag;
import com.jabasoft.mms.documentmanagement.dto.*;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepositoryWrapper;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DocumentManagementInteractorTest {


    DocumentRepository documentRepository;
	DocumentManagementInteractor interactor;
	
	@BeforeEach
	void setUp(){
		this.documentRepository = mock(DocumentRepository.class);
        interactor = new DocumentManagementInteractor(documentRepository, mock(FilePathRepositoryWrapper.class));
	}

	@Test
	void testSaveDocumentReturnsEmptyOptionalOnError() {
		when(documentRepository.saveDocument(any())).thenReturn(Optional.empty());

		Optional<DocumentDto> saved = interactor.saveDocument(getDocumentDto());
		
		assertTrue(saved.isEmpty());
	}

	@Test
	void testSaveDocumentReturnsSavedDocumentOnSuccess() {
		when(documentRepository.saveDocument(any())).thenReturn(Optional.of(getDocument()));

		Optional<DocumentDto> saved = interactor.saveDocument(getDocumentDto());

		assertTrue(saved.isPresent());

		DocumentDto actual = saved.get();
		
		assertEquals(12L, actual.getDocumentId(), "DocumentId");
		assertEquals("9ab2b6e2-e6ec-45ab-b2b6-e2e6ec65ab95", actual.getDocumentName(), "DocumentName");
		assertArrayEquals(new byte[]{12, 23, 25, 31}, actual.getContent(), "Content");
		assertEquals(new FileTypeDto("pdf"), actual.getFileType(), "FileType");
		assertEquals("root\\dir1", actual.getPathToDocumentFromRoot().getFilePath(), "DocumentPath");
	}

	@Test
	void testGetAllDocumentsReturnsEmptyListWhenNoDocumentsExists() {
		when(documentRepository.getAllDocuments()).thenReturn(List.of());

		List<DocumentDto> allDocuments = interactor.getAllDocuments();
		
		assertEquals(0, allDocuments.size());
	}

	@Test
	void testGetAllDocumentsReturnsListWithDocumentsWhenDocumentsExists() {
		when(documentRepository.getAllDocuments()).thenReturn(List.of(getDocument()));

		List<DocumentDto> allDocuments = interactor.getAllDocuments();

		assertEquals(1, allDocuments.size());

		DocumentDto actual = allDocuments.get(0);

		assertEquals(12L, actual.getDocumentId(), "DocumentId");
		assertEquals("9ab2b6e2-e6ec-45ab-b2b6-e2e6ec65ab95", actual.getDocumentName(), "DocumentName");
		assertArrayEquals(new byte[]{12, 23, 25, 31}, actual.getContent(), "Content");
		assertEquals(new FileTypeDto("pdf"), actual.getFileType(), "FileType");
		assertEquals("root\\dir1", actual.getPathToDocumentFromRoot().getFilePath(), "DocumentPath");
	}

	@Test
	void testGetDocumentReturnsEmptyOptionalWhenNoDocumentExists() {
		when(documentRepository.getDocument(any())).thenReturn(Optional.empty());

        Optional<DocumentDto> actual = interactor.getDocument(123L);
		
		assertTrue(actual.isEmpty());
	}

	@Test
	void testGetDocumentReturnsOptionalWithDocumentWhenDocumentExists() {
		when(documentRepository.getDocument(12L)).thenReturn(Optional.of(getDocument()));

		Optional<DocumentDto> find = interactor.getDocument(12L);

		assertTrue(find.isPresent());

		DocumentDto actual = find.get();

		assertEquals(12L, actual.getDocumentId(), "DocumentId");
		assertEquals("9ab2b6e2-e6ec-45ab-b2b6-e2e6ec65ab95", actual.getDocumentName(), "DocumentName");
		assertArrayEquals(new byte[]{12, 23, 25, 31}, actual.getContent(), "Content");
		assertEquals(new FileTypeDto("pdf"), actual.getFileType(), "FileType");
		assertEquals("root\\dir1", actual.getPathToDocumentFromRoot().getFilePath(), "DocumentPath");
	}


    @Test
    void testGetDocumentWithoutContentReturnsEmptyOptionalWhenNoDocumentExists() {
        when(documentRepository.getDocument(any())).thenReturn(Optional.empty());

        Optional<DocumentWithoutContentDto> actual = interactor.getDocumentWithoutContent(123L);

        assertTrue(actual.isEmpty());
    }

    @Test
    void testGetDocumentWithoutContentReturnsOptionalWithDocumentWhenDocumentExists() {
        when(documentRepository.getDocument(12L)).thenReturn(Optional.of(getDocument()));

        Optional<DocumentWithoutContentDto> find = interactor.getDocumentWithoutContent(12L);

        assertTrue(find.isPresent());

        DocumentWithoutContentDto actual = find.get();

        assertEquals(12L, actual.getDocumentId(), "DocumentId");
        assertEquals("9ab2b6e2-e6ec-45ab-b2b6-e2e6ec65ab95", actual.getDocumentName(), "DocumentName");
        assertEquals(new FileTypeDto("pdf"), actual.getFileType(), "FileType");
        assertEquals("root\\dir1", actual.getPathToDocumentFromRoot().getFilePath(), "DocumentPath");
    }


	@Test
	void testDeleteDocumentWithNotExistingDocumentReturnsEmptyOptional(){
		when(documentRepository.deleteDocument(new FilePath("/abc/def/"), "test")).thenReturn(Optional.empty());

		Optional<DocumentWithoutContentDto> deleted = interactor.deleteDocument(new FilePathDto("/abc/def/"), "test");

		assertTrue(deleted.isEmpty());
	}

	@Test
	void testDeleteDocumentWithExistingDocumentReturnsOptionalWithDocument(){
		when(documentRepository.deleteDocument(new FilePath("/abc/def/"), "test")).thenReturn(Optional.of(getDocument()));

		Optional<DocumentWithoutContentDto> deleted = interactor.deleteDocument(new FilePathDto("/abc/def/"), "test");

		assertTrue(deleted.isPresent());
		DocumentWithoutContentDto actual = deleted.get();

		assertEquals(12L, actual.getDocumentId(), "DocumentId");
		assertEquals("9ab2b6e2-e6ec-45ab-b2b6-e2e6ec65ab95", actual.getDocumentName(), "DocumentName");
		assertEquals(new FileTypeDto("pdf"), actual.getFileType(), "FileType");
		assertEquals("root\\dir1", actual.getPathToDocumentFromRoot().getFilePath(), "DocumentPath");
	}

	@Test
	void testExistsDocumentReturnsTrueIfDocumentExists(){
		when(documentRepository.existsDocument(any())).thenReturn(true);

		boolean exists = interactor.existsDocument(getDocumentDto());

		assertTrue(exists);
	}

	@Test
	void testExistsDocumentReturnsFalseIfDocumentDoesNotExists(){
		when(documentRepository.existsDocument(any())).thenReturn(false);

		boolean exists = interactor.existsDocument(getDocumentDto());

		assertFalse(exists);
	}

    @Test
    void testSetCustomerAddsCustomer() {
        Document expected = getDocument();
        expected.setCustomerId(12L);
        when(documentRepository.setCustomer(34L, 12L)).thenReturn(Optional.of(expected));

        Optional<DocumentDto> actual = interactor.setCustomer(34L, 12L);

        assertTrue(actual.isPresent());
        assertEquals(12L, actual.get().getCustomerId());
    }

    @Test
    void testResetCustomerAddsCustomer() {
        Document expected = getDocument();
        expected.setCustomerId(null);
        when(documentRepository.resetCustomer(34L)).thenReturn(Optional.of(expected));

        Optional<DocumentDto> actual = interactor.resetCustomer(34L);

        assertTrue(actual.isPresent());
        assertNull(actual.get().getCustomerId());
    }

    @Test
    void testAddTagReturnsEmptyOptionalWhenNoDocumentExists() {
        when(documentRepository.getDocument(12L)).thenReturn(Optional.empty());

        Optional<DocumentDto> actual = interactor.addTag(12L, new TagDto("Test"));

        assertTrue(actual.isEmpty());
    }

    @Test
    void testAddTagReturnsDocumentWithTagWhenDocumentExists() {
        when(documentRepository.getDocument(12L)).thenReturn(Optional.of(getDocument()));
        when(documentRepository.saveDocument(any())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return Optional.of((Document) args[0]);
        });

        Optional<DocumentDto> actual = interactor.addTag(12L, new TagDto("Test"));

        assertTrue(actual.isPresent());
        assertEquals(Set.of(new TagDto("Test")), actual.get().getTags());
    }

    @Test
    void testRemoveTagReturnsEmptyOptionalWhenNoDocumentExists() {
        when(documentRepository.getDocument(12L)).thenReturn(Optional.empty());

        Optional<DocumentDto> actual = interactor.removeTag(12L, new TagDto("Test"));

        assertTrue(actual.isEmpty());
    }

    @Test
    void testRemoveTagReturnsDocumentWithoutTagsWhenDocumentHasOnlyOneTag() {
        Document document = getDocument();
        document.addTag(new Tag("Test"));
        when(documentRepository.getDocument(12L)).thenReturn(Optional.of(document));
        when(documentRepository.saveDocument(any())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return Optional.of((Document) args[0]);
        });


        Optional<DocumentDto> actual = interactor.removeTag(12L, new TagDto("Test"));

        assertTrue(actual.isPresent());
        assertEquals(Set.of(), actual.get().getTags());
    }

    @Test
    void testRemoveTagReturnsDocumentWithoutTagWhenDocumentHasMultipleTag() {
        Document document = getDocument();
        document.addTag(new Tag("Test"));
        document.addTag(new Tag("Test2"));
        when(documentRepository.getDocument(12L)).thenReturn(Optional.of(document));
        when(documentRepository.saveDocument(any())).thenAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            return Optional.of((Document) args[0]);
        });

        Optional<DocumentDto> actual = interactor.removeTag(12L, new TagDto("Test"));

        assertTrue(actual.isPresent());
        assertEquals(Set.of(new TagDto("Test2")), actual.get().getTags());
    }

	public DocumentDto getDocumentDto(){

		DocumentDto documentDto = new DocumentDto();


		documentDto.setDocumentId(12L);
		documentDto.setDocumentName("9ab2b6e2-e6ec-45ab-b2b6-e2e6ec65ab95");
		documentDto.setContent(new byte[]{12, 23, 25, 31});
		documentDto.setFileType(new FileTypeDto("pdf"));

		FilePathDto filePathDto = new FilePathDto();
		filePathDto.setFilePath("/root/dir1/");
		documentDto.setPathToDocumentFromRoot(filePathDto);
		
		return documentDto;
	}

	public Document getDocument(){

		Document document = new Document();


		document.setDocumentId(12L);
		document.setDocumentName("9ab2b6e2-e6ec-45ab-b2b6-e2e6ec65ab95");
		document.setContent(new byte[]{12, 23, 25, 31});
		document.setFileType(FileType.PDF);

		FilePath filePath = new FilePath();
		filePath.setFilePath("/root/dir1/");
		document.setPathToDocumentFromRoot(filePath);

		return document;
	}
}