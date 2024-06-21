package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.Objects;

public class FilePath {

	private String filePath;

	public FilePath(String filePath) {

		this.filePath = transformFilePath(filePath);
	}

	public FilePath() {

	}

	public String getFilePath() {

		return filePath;
	}

	public void setFilePath(String filePath) {

		this.filePath = transformFilePath(filePath);
	}

	private String transformFilePath(String filePath){
		if(filePath == null){
			return "";
		}

		filePath = filePath.replace("/", "\\");
		if(filePath.startsWith("\\")){
			filePath = filePath.substring(1);
		}
		if(filePath.endsWith("\\")){
			filePath = filePath.substring(0, filePath.length() - 1);
		}
		return filePath;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FilePath filePath1 = (FilePath) o;
		return Objects.equals(filePath, filePath1.filePath);
	}

	@Override
	public int hashCode() {

		return Objects.hash(filePath);
	}

	@Override
	public String toString() {
		return "FilePath{" +
				"filePath='" + filePath + '\'' +
				'}';
	}
}
