package com.jabasoft.mms.documentmanagement.adapter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DOCUMENTS")
public class JpaDocument {

	@Id
	@Column(name = "DOCUMENT_ID")
	private String documentId;

	@Column(name = "RELATIVE_PATH")
	private String relativePath;

	@Column(name = "CONTENT")
	private byte[] content;

	public String getDocumentId() {

		return documentId;
	}

	public void setDocumentId(String documentId) {

		this.documentId = documentId;
	}

	public String getRelativePath() {

		return relativePath;
	}

	public void setRelativePath(String relativePath) {

		this.relativePath = relativePath;
	}

	public byte[] getContent() {

		return content;
	}

	public void setContent(byte[] content) {

		this.content = content;
	}

}
