package com.jabasoft.mms.documentmanagement.domain.model;

import java.nio.file.Path;

public class Document {


	private String relativePath;
	private byte[] content;
	private DocumentId documentId;

	public Document(String  path, byte[] content, DocumentId documentId) {

		this.relativePath = path;
		this.content = content;
		this.documentId = documentId;
	}

	public String  getRelativePath() {

		return relativePath;
	}

	public byte[] getContent() {

		return content;
	}

	public DocumentId getDocumentId() {

		return documentId;
	}

}
