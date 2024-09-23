package com.jabasoft.mms.documentmanagement.entity;

import java.util.Set;

public class JpaDocumentWithoutContent {

    private Long documentId;
    private String pathToDocumentFromRoot;
    private String documentName;
    private String fileType;
    private Set<JpaTag> tags;

    public JpaDocumentWithoutContent(Long documentId, String pathToDocumentFromRoot, String documentName, String fileType, Set<JpaTag> tags) {
        this.documentId = documentId;
        this.pathToDocumentFromRoot = pathToDocumentFromRoot;
        this.documentName = documentName;
        this.fileType = fileType;
        this.tags = tags;
    }

    public JpaDocumentWithoutContent(Long documentId, String pathToDocumentFromRoot, String documentName, String fileType, JpaTag tags) {
        this.documentId = documentId;
        this.pathToDocumentFromRoot = pathToDocumentFromRoot;
        this.documentName = documentName;
        this.fileType = fileType;
        this.tags = Set.of(tags);
    }

    public JpaDocumentWithoutContent(Long documentId, String pathToDocumentFromRoot, String documentName, String fileType) {
        this.documentId = documentId;
        this.pathToDocumentFromRoot = pathToDocumentFromRoot;
        this.documentName = documentName;
        this.fileType = fileType;
        this.tags = Set.of();
    }

    public JpaDocumentWithoutContent(){}

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

    public Set<JpaTag> getTags() {
        return tags;
    }
}
