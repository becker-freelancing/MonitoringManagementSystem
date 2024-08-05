package com.jabasoft.mms.documentmanagement.dto;

import java.util.Objects;
import java.util.Set;

public class DocumentWithoutContentDto {

	private Long documentId;
	private FilePathDto pathToDocumentFromRoot;
	private String documentName;
	private FileTypeDto fileType;
    private Long customerId;
    private Set<TagDto> tags;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentWithoutContentDto that = (DocumentWithoutContentDto) o;
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

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

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
