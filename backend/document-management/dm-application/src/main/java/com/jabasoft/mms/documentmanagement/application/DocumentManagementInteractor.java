package com.jabasoft.mms.documentmanagement.application;

import com.jabasoft.mms.documentmanagement.api.DocumentManagementPort;
import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentWithoutContent;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import com.jabasoft.mms.documentmanagement.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.dto.DocumentWithoutContentDto;
import com.jabasoft.mms.documentmanagement.dto.FilePathDto;
import com.jabasoft.mms.documentmanagement.dto.FileTypeDto;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class DocumentManagementInteractor implements DocumentManagementPort {

	private DocumentRepository documentRepository;

	@Autowired
	public DocumentManagementInteractor(DocumentRepository documentRepository) {

		this.documentRepository = documentRepository;
	}

	@Override
	public Optional<DocumentDto> saveDocument(DocumentDto document) {

		Document mapped = map(document);
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
	public Optional<DocumentWithoutContentDto> deleteDocument(FilePathDto path, String name) {

		Optional<Document> deleted = documentRepository.deleteDocument(map(path), name);

		return deleted.map(this::map);
	}

	@Override
	public boolean existsDocument(DocumentWithoutContentDto document) {

        return documentRepository.existsDocument(map(document));
	}

	private DocumentDto map(Document document){

		DocumentDto documentDto = new DocumentDto();
		documentDto.setDocumentId(document.getDocumentId());
		documentDto.setDocumentName(document.getDocumentName());
		documentDto.setContent(document.getContent());
		documentDto.setFileType(new FileTypeDto(document.getFileType().getFileEnding()));
		documentDto.setPathToDocumentFromRoot(map(document.getPathToDocumentFromRoot()));

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

		return document;
	}

	private DocumentWithoutContent map(DocumentWithoutContentDto documentDto){

		Document document = new Document();
		document.setDocumentId(documentDto.getDocumentId());
		document.setDocumentName(documentDto.getDocumentName());
		document.setFileType(new FileType(documentDto.getFileType().getFileEnding()));
		document.setPathToDocumentFromRoot(map(documentDto.getPathToDocumentFromRoot()));

		return document;
	}

	private <S extends Enum<S>, T extends Enum<T>> T mapEnumByName(Class<T> targetEnumType, S sourceEnum) {

		if (sourceEnum == null) {
			return null;
		}

		return Enum.valueOf(targetEnumType, sourceEnum.name());
	}
}
