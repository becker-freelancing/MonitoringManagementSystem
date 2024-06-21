package com.jabasoft.mms.documentmanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class FileStructureWithDocumentsDto {


	private String current;
	private List<FileStructureWithDocumentsDto> children;
	private List<DocumentWithoutContentDto> documents;

	public FileStructureWithDocumentsDto() {
		this.children = new ArrayList<>();
		this.documents = new ArrayList<>();
	}

	public String getCurrent() {

		return current;
	}

	public void setCurrent(String current) {

		this.current = current;
	}

	public List<FileStructureWithDocumentsDto> getChildren() {

		return children;
	}

	public void setChildren(List<FileStructureWithDocumentsDto> children) {

		this.children = children;
	}

	public void addChildren(FileStructureWithDocumentsDto children) {
		this.children.add(children);
	}

	public List<DocumentWithoutContentDto> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentWithoutContentDto> documents) {
		this.documents = documents;
	}

	public void addDocument(DocumentWithoutContentDto document) {
		documents.add(document);
	}
}
