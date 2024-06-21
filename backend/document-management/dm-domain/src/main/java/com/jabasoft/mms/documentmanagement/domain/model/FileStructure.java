package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStructure {

	private String current;

	private List<FileStructure> children;

	public FileStructure(){
		this.children = new ArrayList<>();
	}

	public FileStructure(String current) {
		this();
		this.current = current;
	}

	public String getCurrent() {

		return current;
	}

	public void setCurrent(String current) {

		this.current = current;
	}

	public List<FileStructure> getChildren() {

		return children;
	}

	public void setChildren(List<FileStructure> children) {

		this.children = children;
	}

	public void addChildren(FileStructure children) {
		this.children.add(children);
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof FileStructure that))
			return false;
		return Objects.equals(current, that.current) && Objects.equals(children, that.children);
	}

	@Override
	public int hashCode() {

		return Objects.hash(current, children);
	}

}
