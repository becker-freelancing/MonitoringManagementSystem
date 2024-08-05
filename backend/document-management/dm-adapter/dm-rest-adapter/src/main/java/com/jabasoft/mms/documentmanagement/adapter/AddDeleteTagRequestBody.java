package com.jabasoft.mms.documentmanagement.adapter;

public class AddDeleteTagRequestBody {

    private String tag;
    private Long documentId;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
