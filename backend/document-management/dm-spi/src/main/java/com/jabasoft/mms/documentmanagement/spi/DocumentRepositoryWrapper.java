package com.jabasoft.mms.documentmanagement.spi;

import java.util.List;
import java.util.stream.Collectors;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentId;

public class DocumentRepositoryWrapper implements DocumentRepository{

	private List<DocumentRepository> documentRepositories;
	private DocumentRepository defaultDocumentRepository;

	public DocumentRepositoryWrapper(List<DocumentRepository> documentRepositories, DocumentRepository defaultDocumentRepository) {
		if(documentRepositories.isEmpty()){
			throw new IllegalArgumentException("Min one DocumentRepository needed");
		}
		if(documentRepositories.stream().map(DocumentRepository::getClass).collect(Collectors.toSet()).contains(defaultDocumentRepository.getClass())){
			throw new IllegalArgumentException("DocumentRepositories can not contain the default DocumentRepository");
		}
		this.documentRepositories = documentRepositories;
		this.defaultDocumentRepository = defaultDocumentRepository;
	}

	@Override
	public List<Document> getAllDocuments() {

		return defaultDocumentRepository.getAllDocuments();
	}

	@Override
	public Document getDocument(DocumentId documentId) {

		return defaultDocumentRepository.getDocument(documentId);
	}

	@Override
	public DocumentId saveDocument(Document document) {


		for (DocumentRepository documentRepository : documentRepositories) {
			documentRepository.saveDocument(document);
		}

		return defaultDocumentRepository.saveDocument(document);
	}

}
