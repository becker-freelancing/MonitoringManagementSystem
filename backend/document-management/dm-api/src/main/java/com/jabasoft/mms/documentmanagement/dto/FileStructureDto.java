package com.jabasoft.mms.documentmanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class FileStructureDto {

	private String current;

	private List<FileStructureDto> children;

	public FileStructureDto(){
		this.children = new ArrayList<>();
	}

	public String getCurrent() {

		return current;
	}

	public void setCurrent(String current) {

		this.current = current;
	}

	public List<FileStructureDto> getChildren() {

		return children;
	}

	public void setChildren(List<FileStructureDto> children) {

		this.children = children;
	}

	public void addChildren(FileStructureDto children) {
		this.children.add(children);
	}
}
