package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.Objects;

public class Tag {

    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag() {
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tagName);
    }
}
