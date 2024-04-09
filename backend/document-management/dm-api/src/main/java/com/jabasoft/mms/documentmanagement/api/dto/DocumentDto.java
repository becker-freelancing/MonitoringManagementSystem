package com.jabasoft.mms.documentmanagement.api.dto;

public class DocumentDto implements GetDocumentDto{

	private String relativePath;
	private byte[] content;
	private String  documentId;

	@Override
	public String  getDocumentId() {

		return documentId;
	}

	public void setDocumentId(String  documentId) {

		this.documentId = documentId;
	}

	@Override
	public byte[] getContent() {

		return content;
	}

	public void setContent(byte[] content) {

		this.content = content;
	}

	@Override
	public String getRelativePath() {

		return relativePath;
	}

	public void setRelativePath(String relativePath) {

		this.relativePath = relativePath;
	}

}
