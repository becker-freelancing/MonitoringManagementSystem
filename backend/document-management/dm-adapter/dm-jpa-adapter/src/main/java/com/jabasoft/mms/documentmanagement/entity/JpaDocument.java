package com.jabasoft.mms.documentmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DOCUMENTS")
public class JpaDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DOCUMENT_ID")
	private Long documentId;

	@Column(name = "PATH_TO_DOCUMENT_FROM_ROOT")
	private String pathToDocumentFromRoot;

	@Column(name = "DOCUMENT_NAME")
	private String documentName;

	@Column(name = "FILE_TYPE")
	private String fileType;

	@Column(name = "CONTENT")
	private byte[] content;

	public Long getDocumentId() {

		return documentId;
	}

	public void setDocumentId(Long documentId) {

		this.documentId = documentId;
	}

	public String getPathToDocumentFromRoot() {

		return pathToDocumentFromRoot;
	}

	public void setPathToDocumentFromRoot(String pathToDocumentFromRoot) {

		this.pathToDocumentFromRoot = pathToDocumentFromRoot;
	}

	public String getDocumentName() {

		return documentName;
	}

	public void setDocumentName(String documentName) {

		this.documentName = documentName;
	}

	public String getFileType() {

		return fileType;
	}

	public void setFileType(String fileType) {

		this.fileType = fileType;
	}

	public byte[] getContent() {

		return content;
	}

	public void setContent(byte[] content) {

		this.content = content;
	}

}
