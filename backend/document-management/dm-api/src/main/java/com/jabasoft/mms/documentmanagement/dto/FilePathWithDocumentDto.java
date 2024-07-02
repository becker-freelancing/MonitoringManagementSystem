package com.jabasoft.mms.documentmanagement.dto;

public class FilePathWithDocumentDto extends FilePathDto{

    private DocumentWithoutContentDto document;

    public FilePathWithDocumentDto(String filePath, DocumentWithoutContentDto document) {
        super(filePath);
        this.document = document;
    }

    public FilePathWithDocumentDto() {
        super();
    }

    public DocumentWithoutContentDto getDocument() {
        return document;
    }

    public void setDocument(DocumentWithoutContentDto document) {
        this.document = document;
    }
}
