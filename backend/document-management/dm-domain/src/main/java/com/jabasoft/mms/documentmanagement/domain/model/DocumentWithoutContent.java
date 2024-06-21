package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.Objects;

public class DocumentWithoutContent {

	private Long documentId;
	private FilePath pathToDocumentFromRoot;
	private String documentName;
	private FileType fileType;

	public Long getDocumentId() {

		return documentId;
	}

	public void setDocumentId(Long documentId) {

		this.documentId = documentId;
	}

	public FilePath getPathToDocumentFromRoot() {

		return pathToDocumentFromRoot;
	}

	public void setPathToDocumentFromRoot(FilePath pathToDocumentFromRoot) {

		this.pathToDocumentFromRoot = pathToDocumentFromRoot;
	}

	public String getDocumentName() {

		return documentName;
	}

	public void setDocumentName(String documentName) {

		this.documentName = documentName;
	}

	public FileType getFileType() {

		return fileType;
	}

	public void setFileType(FileType fileType) {

		this.fileType = fileType;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DocumentWithoutContent that = (DocumentWithoutContent) o;
		return Objects.equals(documentId, that.documentId) && Objects.equals(
			pathToDocumentFromRoot,
			that.pathToDocumentFromRoot) && Objects.equals(documentName, that.documentName) && fileType == that.fileType;
	}

	@Override
	public int hashCode() {

		return Objects.hash(documentId, pathToDocumentFromRoot, documentName, fileType);
	}

}
