package com.jabasoft.mms.documentmanagement.application;

import com.jabasoft.mms.documentmanagement.api.DocumentManagementPort;
import com.jabasoft.mms.documentmanagement.domain.model.*;
import com.jabasoft.mms.documentmanagement.domain.model.error.FileModificationException;
import com.jabasoft.mms.documentmanagement.dto.*;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepository;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class DocumentManagementInteractor implements DocumentManagementPort {

	private DocumentRepository documentRepository;
	private FilePathRepository filePathRepository;

	@Autowired
	public DocumentManagementInteractor(DocumentRepository documentRepository, FilePathRepository filePathRepository) {

		this.documentRepository = documentRepository;
		this.filePathRepository = filePathRepository;
    }

	@Override
	public Optional<DocumentDto> saveDocument(DocumentDto document) {

		Document mapped = map(document);

		boolean dirDoesNotExist = filePathRepository.isPathCreatable(mapped.getPathToDocumentFromRoot().getParent());
		if (dirDoesNotExist) {
			try {
				filePathRepository.createFileStructure(mapped.getPathToDocumentFromRoot().getParent());
			} catch (FileModificationException e) {
				return Optional.empty();
			}
		}

		boolean exists = documentRepository.existsDocument(mapped);
		if (exists) {
			documentRepository.deleteDocument(mapped.getPathToDocumentFromRoot(), mapped.getDocumentName());
		}
		Optional<Document> saved = documentRepository.saveDocument(mapped);

		return saved.map(this::map);
	}

	@Override
	public List<DocumentDto> getAllDocuments() {
		return documentRepository.getAllDocuments().stream().map(this::map).toList();
	}

	@Override
	public Optional<DocumentDto> getDocument(Long id) {

		Optional<Document> document = documentRepository.getDocument(id);

		return document.map(this::map);
	}

	@Override
	public Optional<DocumentWithoutContentDto> getDocumentWithoutContent(Long documentId) {

		Optional<Document> document = documentRepository.getDocument(documentId);

		return document.map(this::map);
	}

	@Override
	public Optional<DocumentWithoutContentDto> deleteDocument(FilePathDto path, String name) {

		Optional<Document> deleted = documentRepository.deleteDocument(map(path), name);

		return deleted.map(this::map);
	}

	@Override
	public boolean existsDocument(DocumentWithoutContentDto document) {

        return documentRepository.existsDocument(map(document));
	}

    @Override
    public Optional<DocumentDto> setCustomer(Long documentId, Long customerId) {
        Optional<Document> document = documentRepository.setCustomer(documentId, customerId);

        return document.map(this::map);
    }

    @Override
    public Optional<DocumentDto> resetCustomer(Long documentId) {
        Optional<Document> document = documentRepository.resetCustomer(documentId);

        return document.map(this::map);
    }

    @Override
    public Optional<DocumentDto> addTag(Long documentId, TagDto tag) {

        Optional<Document> optionalDocument = documentRepository.getDocument(documentId);
        if (optionalDocument.isEmpty()) {
            return Optional.empty();
        }

        Document document = optionalDocument.get();
        document.addTag(map(tag));

        Optional<Document> saved = documentRepository.saveDocument(document);

        return saved.map(this::map);
    }

    @Override
    public Optional<DocumentDto> removeTag(Long documentId, TagDto tag) {
        Optional<Document> optionalDocument = documentRepository.getDocument(documentId);
        if (optionalDocument.isEmpty()) {
            return Optional.empty();
        }

        Document document = optionalDocument.get();
        document.removeTag(map(tag));

        Optional<Document> saved = documentRepository.saveDocument(document);

        return saved.map(this::map);
    }

	private DocumentDto map(Document document){

		DocumentDto documentDto = new DocumentDto();
		documentDto.setDocumentId(document.getDocumentId());
		documentDto.setDocumentName(document.getDocumentName());
		documentDto.setContent(document.getContent());
		documentDto.setFileType(new FileTypeDto(document.getFileType().getFileEnding()));
		documentDto.setPathToDocumentFromRoot(map(document.getPathToDocumentFromRoot()));
        documentDto.setTags(new HashSet<>(document.getTags().stream().map(this::map).collect(Collectors.toSet())));
        documentDto.setCustomerId(document.getCustomerId());

		return documentDto;
	}


	private FilePathDto map(FilePath pathToDocumentFromRoot) {

		FilePathDto filePathDto = new FilePathDto();
		filePathDto.setFilePath(pathToDocumentFromRoot.getFilePath());

		return filePathDto;
	}

	private FilePath map(FilePathDto pathToDocumentFromRoot) {

		FilePath filePath = new FilePath();
		filePath.setFilePath(pathToDocumentFromRoot.getFilePath());

		return filePath;
	}

	private Document map(DocumentDto documentDto){

		Document document = new Document();
		document.setDocumentId(documentDto.getDocumentId());
		document.setDocumentName(documentDto.getDocumentName());
		document.setContent(documentDto.getContent());
		document.setFileType(new FileType(documentDto.getFileType().getFileEnding()));
		document.setPathToDocumentFromRoot(map(documentDto.getPathToDocumentFromRoot()));
        if (documentDto.getTags() != null) {
            document.setTags(new HashSet<>(documentDto.getTags().stream().map(this::map).collect(Collectors.toSet())));
        }
		return document;
	}

	private DocumentWithoutContent map(DocumentWithoutContentDto documentDto){

		Document document = new Document();
		document.setDocumentId(documentDto.getDocumentId());
		document.setDocumentName(documentDto.getDocumentName());
		document.setFileType(new FileType(documentDto.getFileType().getFileEnding()));
		document.setPathToDocumentFromRoot(map(documentDto.getPathToDocumentFromRoot()));
        if (documentDto.getTags() != null) {
            document.setTags(new HashSet<>(documentDto.getTags().stream().map(this::map).collect(Collectors.toSet())));
        }
		return document;
	}

    private Tag map(TagDto dto) {
        return new Tag(dto.getTag());
    }


    private TagDto map(Tag tag) {
        return new TagDto(tag.getTagName());
	}
}
