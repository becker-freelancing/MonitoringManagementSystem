package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStructureWithDocuments {

	private String current;
	private List<FileStructureWithDocuments> children;
	private List<DocumentWithoutContent> documents;

	public FileStructureWithDocuments() {
		this.documents = new ArrayList<>();
		this.children = new ArrayList<>();
	}


	public FileStructureWithDocuments(String current) {
		this();
		this.current = current;
	}

	public FileStructureWithDocuments(FileStructure fileStructure) {
		this(fileStructure.getCurrent());
		List<FileStructure> children = fileStructure.getChildren();
		if(!children.isEmpty()){
			for (FileStructure child : children) {
				addChildren(new FileStructureWithDocuments(child));
			}
		}
	}


	public String getCurrent() {

		return current;
	}

	public void setCurrent(String current) {

		this.current = current;
	}

	public List<FileStructureWithDocuments> getChildren() {

		return children;
	}

	public void setChildren(List<FileStructureWithDocuments> children) {

		this.children = children;
	}

	public void addChildren(FileStructureWithDocuments children) {
		this.children.add(children);
	}

	public List<DocumentWithoutContent> getDocuments() {

		return documents;
	}

	public void setDocuments(List<DocumentWithoutContent> documents) {
		this.documents = documents;
	}

	public void addDocument(DocumentWithoutContent document){
		documents.add(document);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileStructureWithDocuments that = (FileStructureWithDocuments) o;
		return Objects.equals(current, that.current) && Objects.equals(children, that.children) && Objects.equals(documents, that.documents);
	}

	@Override
	public int hashCode() {
		return Objects.hash(current, children, documents);
	}
}
