package com.jabasoft.mms.documentmanagement.dto;

import java.util.Objects;

public class TagDto {

    private String tag;

    public TagDto(String tag) {
        this.tag = tag;
    }

    public TagDto() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDto tag1 = (TagDto) o;
        return Objects.equals(tag, tag1.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tag);
    }
}
