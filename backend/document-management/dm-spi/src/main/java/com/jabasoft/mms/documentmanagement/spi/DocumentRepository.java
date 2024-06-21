package com.jabasoft.mms.documentmanagement.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;

public interface DocumentRepository {

	Optional<Document> deleteDocument(FilePath path, String name);
	public List<Document> getAllDocuments();
	public Optional<Document> getDocument(Long documentId);
	public Optional<Document> saveDocument(Document document);

}
