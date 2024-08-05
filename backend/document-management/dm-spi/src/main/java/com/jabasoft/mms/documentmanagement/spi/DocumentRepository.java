package com.jabasoft.mms.documentmanagement.spi;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentWithoutContent;
import com.jabasoft.mms.documentmanagement.domain.model.FilePath;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository {

    public Optional<Document> deleteDocument(FilePath path, String name);
	public List<Document> getAllDocuments();
	public Optional<Document> getDocument(Long documentId);
	public Optional<Document> saveDocument(Document document);

    public boolean existsDocument(DocumentWithoutContent map);

    public Optional<Document> setCustomer(Long documentId, Long customerId);

    public Optional<Document> resetCustomer(Long documentId);
}
