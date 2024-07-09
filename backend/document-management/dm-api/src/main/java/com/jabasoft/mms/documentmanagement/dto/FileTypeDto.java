package com.jabasoft.mms.documentmanagement.dto;

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
}
