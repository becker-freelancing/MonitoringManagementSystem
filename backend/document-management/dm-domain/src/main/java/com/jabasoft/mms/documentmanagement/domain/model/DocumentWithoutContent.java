package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DocumentWithoutContent {

	private Long documentId;
	private FilePath pathToDocumentFromRoot;
	private String documentName;
	private FileType fileType;
    private Long customerId;
    private Set<Tag> tags;

    public DocumentWithoutContent() {
        this.tags = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentWithoutContent that = (DocumentWithoutContent) o;
        return Objects.equals(documentId, that.documentId) && Objects.equals(pathToDocumentFromRoot, that.pathToDocumentFromRoot) && Objects.equals(documentName, that.documentName) && Objects.equals(fileType, that.fileType) && Objects.equals(customerId, that.customerId) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, pathToDocumentFromRoot, documentName, fileType, customerId, tags);
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

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
    public String toString() {
        return "DocumentWithoutContent{" +
                "documentId=" + documentId +
                ", pathToDocumentFromRoot=" + pathToDocumentFromRoot +
                ", documentName='" + documentName + '\'' +
                ", fileType=" + fileType +
                ", customerId=" + customerId +
                ", tags=" + tags +
                '}';
	}
}
