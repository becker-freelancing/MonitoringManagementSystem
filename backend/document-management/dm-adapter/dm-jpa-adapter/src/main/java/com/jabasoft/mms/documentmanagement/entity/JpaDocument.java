package com.jabasoft.mms.documentmanagement.entity;

import jakarta.persistence.*;

import java.util.Set;

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

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @ManyToMany
    @JoinTable(
            name = "DOCUMENT_TAGS",
            joinColumns = @JoinColumn(name = "DOCUMENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private Set<JpaTag> tags;

    public Set<JpaTag> getTags() {
        return tags;
    }

    public void setTags(Set<JpaTag> tags) {
        this.tags = tags;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

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
