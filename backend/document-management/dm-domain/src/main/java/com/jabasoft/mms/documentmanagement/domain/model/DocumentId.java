package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.Objects;

public class DocumentId {

	private String documentId;

	public DocumentId(String documentId) {

		this.documentId = documentId;
	}

	public String getDocumentId() {

		return documentId;
	}

	public void setDocumentId(String documentId) {

		this.documentId = documentId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DocumentId that = (DocumentId) o;
		return Objects.equals(documentId, that.documentId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(documentId);
	}

}
