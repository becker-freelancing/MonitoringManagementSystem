package com.jabasoft.mms.documentmanagement.spi;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentWithoutContent;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	public Optional<Document> deleteDocument(FilePath path, String name) {

		for (DocumentRepository documentRepository : documentRepositories) {
			documentRepository.deleteDocument(path, name);
		}

		return defaultDocumentRepository.deleteDocument(path, name);
	}

	@Override
	public List<Document> getAllDocuments() {

		return defaultDocumentRepository.getAllDocuments();
	}

	@Override
	public Optional<Document> getDocument(Long documentId) {

		return defaultDocumentRepository.getDocument(documentId);
	}

	@Override
	public Optional<Document> saveDocument(Document document) {


		for (DocumentRepository documentRepository : documentRepositories) {
			documentRepository.saveDocument(document);
		}

		return defaultDocumentRepository.saveDocument(document);
	}

	@Override
	public boolean existsDocument(DocumentWithoutContent document) {
		return defaultDocumentRepository.existsDocument(document);
	}

	@Override
	public Optional<Document> setCustomer(Long documentId, Long customerId) {
		return defaultDocumentRepository.setCustomer(documentId, customerId);
	}

	@Override
	public Optional<Document> resetCustomer(Long documentId) {
		return defaultDocumentRepository.resetCustomer(documentId);
	}


}
