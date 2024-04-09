package com.jabasoft.mms.documentmanagement.spi;

import java.util.List;

import com.jabasoft.mms.documentmanagement.domain.model.Document;
import com.jabasoft.mms.documentmanagement.domain.model.DocumentId;

public interface DocumentRepository {

	public List<Document> getAllDocuments();
	public Document getDocument(DocumentId documentId);
	public DocumentId saveDocument(Document document);

}
