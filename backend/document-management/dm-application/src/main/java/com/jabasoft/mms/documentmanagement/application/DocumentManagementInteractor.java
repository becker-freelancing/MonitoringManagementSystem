package com.jabasoft.mms.documentmanagement.application;

import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.documentmanagement.api.DocumentManagementPort;
import com.jabasoft.mms.documentmanagement.api.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.api.dto.GetDocumentDto;
import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentId;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;
import com.jabasoft.mms.settings.api.SettingsPort;

@Component
class DocumentManagementInteractor implements DocumentManagementPort {

	private DocumentRepository documentRepository;

	@Autowired
	public DocumentManagementInteractor(DocumentRepository documentRepository) {

		this.documentRepository = documentRepository;
	}

	@Override
	public String saveDocument(DocumentDto document) {

		DocumentId dtoDocumentId = new DocumentId(document.getDocumentId());

		Document domainDocument = new Document(document.getRelativePath(), document.getContent(), dtoDocumentId);

		DocumentId documentId = documentRepository.saveDocument(domainDocument);
		return documentId.getDocumentId();
	}

	@Override
	public List<GetDocumentDto> getAllDocuments() {

		List<Document> documents = documentRepository.getAllDocuments();

		return documents.stream().map(this::mapToDto).toList();
	}

	@Override
	public GetDocumentDto getDocument(String id) {

		DocumentId documentId = new DocumentId(id);

		Document document = documentRepository.getDocument(documentId);

		return mapToDto(document);
	}

	private GetDocumentDto mapToDto(Document document){

		DocumentDto documentDto = new DocumentDto();
		documentDto.setDocumentId(document.getDocumentId().getDocumentId());
		documentDto.setContent(documentDto.getContent());
		documentDto.setRelativePath(document.getRelativePath());
		return documentDto;
	}

}
