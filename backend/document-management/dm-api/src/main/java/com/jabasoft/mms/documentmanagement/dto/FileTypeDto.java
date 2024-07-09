package com.jabasoft.mms.documentmanagement.dto;

import java.util.Objects;

public class FileTypeDto {

	private String fileEnding;

	public FileTypeDto(String fileEnding) {
		this.fileEnding = fileEnding;
	}

	public FileTypeDto() {
	}

	public String getFileEnding() {
		return fileEnding;
	}

	public void setFileEnding(String fileEnding) {
		this.fileEnding = fileEnding;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileTypeDto that = (FileTypeDto) o;
		return Objects.equals(fileEnding, that.fileEnding);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(fileEnding);
	}

	@Override
	public String toString() {
		return "FileTypeDto{" +
				"fileEnding='" + fileEnding + '\'' +
				'}';
	}
}
