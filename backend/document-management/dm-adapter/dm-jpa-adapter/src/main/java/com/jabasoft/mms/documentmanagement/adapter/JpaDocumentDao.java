package com.jabasoft.mms.documentmanagement.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentId;
import com.jabasoft.mms.documentmanagement.spi.DocumentRepository;

@Component
public class JpaDocumentDao implements DocumentRepository {

	private SpringJpaDocumentRepository documentRepository;

	@Autowired
	public JpaDocumentDao(SpringJpaDocumentRepository documentRepository) {

		this.documentRepository = documentRepository;
	}

	@Override
	public List<Document> getAllDocuments() {

		Iterable<JpaDocument> jpaDocuments = documentRepository.findAll();

		List<Document> documents = new ArrayList<>();

		for (JpaDocument jpaDocument : jpaDocuments) {
			documents.add(mapToDomain(jpaDocument));
		}

		return documents;
	}

	@Override
	public Document getDocument(DocumentId documentId) {

		Optional<JpaDocument> byId = documentRepository.findById(documentId.getDocumentId());

		return byId.map(this::mapToDomain).orElse(null);
	}

	@Override
	public DocumentId saveDocument(Document document) {

		JpaDocument byRelativePath = documentRepository.findByRelativePath(document.getRelativePath());
		if(byRelativePath != null){
			documentRepository.deleteById(byRelativePath.getDocumentId());
		}

		String documentId = UUID.randomUUID().toString();

		JpaDocument jpaDocument = new JpaDocument();
		jpaDocument.setDocumentId(documentId);
		jpaDocument.setContent(document.getContent());
		jpaDocument.setRelativePath(document.getRelativePath());

		documentRepository.save(jpaDocument);

		return new DocumentId(documentId);
	}

	private Document mapToDomain(JpaDocument document){

		DocumentId documentId = new DocumentId(document.getDocumentId());
		return new Document(document.getRelativePath(), document.getContent(), documentId);
	}
}
