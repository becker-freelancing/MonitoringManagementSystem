package com.jabasoft.mms.documentmanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TAGS")
public class JpaTag {

    @Id
    @Column(name = "TAG")
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private Set<JpaDocument> documents = new HashSet<>();

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<JpaDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<JpaDocument> documents) {
        this.documents = documents;
    }
}
