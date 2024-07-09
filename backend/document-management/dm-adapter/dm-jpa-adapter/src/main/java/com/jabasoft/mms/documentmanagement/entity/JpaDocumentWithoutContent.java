package com.jabasoft.mms.documentmanagement.entity;

public class JpaDocumentWithoutContent {

    private Long documentId;
    private String pathToDocumentFromRoot;
    private String documentName;
    private String fileType;

    public JpaDocumentWithoutContent(Long documentId, String pathToDocumentFromRoot, String documentName, String fileType) {
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
