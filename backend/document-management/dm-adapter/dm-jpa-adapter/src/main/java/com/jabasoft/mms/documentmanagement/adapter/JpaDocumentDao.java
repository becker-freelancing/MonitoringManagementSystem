package com.jabasoft.mms.documentmanagement.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.documentmanagement.FileTypeMapper;
import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import com.jabasoft.mms.documentmanagement.entity.JpaDocument;
import com.jabasoft.mms.documentmanagement.filepath.spi.FilePathRepository;
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
			if(jpaDocument.getContent() != null) {
				documents.add(map(jpaDocument));
			}
		}

		return documents;
	}

	@Override
	public Optional<Document> getDocument(Long documentId) {

		 Optional<JpaDocument> byId = documentRepository.findById(documentId);

		return byId.map(this::map);
	}

	@Override
	public Optional<Document> saveDocument(Document document) {

		if (document == null){
			return Optional.empty();
		}

		JpaDocument saved = documentRepository.save(map(document));

		return Optional.of(saved).map(this::map);
	}

	@Override
	public Optional<Document> deleteDocument(FilePath path, String name) {

		Optional<JpaDocument> toDelete = documentRepository.findByPathToDocumentFromRootAndDocumentName(path.getFilePath(), name);

		if(toDelete.isEmpty()){
			return Optional.empty();
		}

		documentRepository.deleteById(toDelete.get().getDocumentId());

		return toDelete.map(this::map);
	}

	private JpaDocument map(Document document) {

		JpaDocument jpaDocument = new JpaDocument();
		jpaDocument.setDocumentId(document.getDocumentId());
		jpaDocument.setDocumentName(document.getDocumentName());
		jpaDocument.setContent(document.getContent());
		jpaDocument.setFileType(document.getFileType().getFileEnding());
		jpaDocument.setPathToDocumentFromRoot(document.getPathToDocumentFromRoot().getFilePath());

		return jpaDocument;
	}

	private Document map(JpaDocument jpaDocument) {


		Document document = new Document();
		document.setDocumentId(jpaDocument.getDocumentId());
		document.setDocumentName(jpaDocument.getDocumentName());
		document.setContent(jpaDocument.getContent());
		document.setFileType(new FileType(jpaDocument.getFileType()));
		document.setPathToDocumentFromRoot(new FilePath(jpaDocument.getPathToDocumentFromRoot()));

		return document;
	}
}
