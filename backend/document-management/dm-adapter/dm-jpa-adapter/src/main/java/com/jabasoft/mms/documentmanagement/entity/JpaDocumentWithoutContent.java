package com.jabasoft.mms.documentmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class JpaDocumentWithoutContent {

    private Long documentId;
    private String pathToDocumentFromRoot;
    private String documentName;
    private Long fileType;

    public JpaDocumentWithoutContent(Long documentId, String pathToDocumentFromRoot, String documentName, Long fileType) {
        this.documentId = documentId;
        this.pathToDocumentFromRoot = pathToDocumentFromRoot;
        this.documentName = documentName;
        this.fileType = fileType;
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

    public Long getFileType() {
        return fileType;
    }

    public void setFileType(Long fileType) {
        this.fileType = fileType;
    }
}
