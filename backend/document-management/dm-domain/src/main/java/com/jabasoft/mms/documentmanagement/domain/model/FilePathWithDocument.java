package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.Objects;

public class FilePathWithDocument extends FilePath{

	private DocumentWithoutContent document;

	public FilePathWithDocument(String filePath, DocumentWithoutContent document) {
		super(filePath);
		this.document = document;
	}

	public FilePathWithDocument() {
		super();
	}

	public DocumentWithoutContent getDocument() {
		return document;
	}

	public void setDocument(DocumentWithoutContent document) {
		this.document = document;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		FilePathWithDocument that = (FilePathWithDocument) o;
		return Objects.equals(document, that.document);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), document);
	}
}
