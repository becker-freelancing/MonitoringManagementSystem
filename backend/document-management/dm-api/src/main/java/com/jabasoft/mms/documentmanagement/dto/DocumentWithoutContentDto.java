package com.jabasoft.mms.documentmanagement.dto;

public class DocumentWithoutContentDto {

	private Long documentId;
	private FilePathDto pathToDocumentFromRoot;
	private String documentName;
	private FileTypeDto fileType;

	public Long getDocumentId() {

		return documentId;
	}

	public void setDocumentId(Long documentId) {

		this.documentId = documentId;
	}

	public FilePathDto getPathToDocumentFromRoot() {

		return pathToDocumentFromRoot;
	}

	public void setPathToDocumentFromRoot(FilePathDto pathToDocumentFromRoot) {

		this.pathToDocumentFromRoot = pathToDocumentFromRoot;
	}

	public String getDocumentName() {

		return documentName;
	}

	public void setDocumentName(String documentName) {

		this.documentName = documentName;
	}

	public FileTypeDto getFileType() {

		return fileType;
	}

	public void setFileType(FileTypeDto fileType) {

		this.fileType = fileType;
	}

}
