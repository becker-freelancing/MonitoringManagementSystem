package com.jabasoft.mms.documentmanagement.dto;

public class DocumentDto extends DocumentWithoutContentDto{

	private byte[] content;

	public byte[] getContent() {

		return content;
	}

	public void setContent(byte[] content) {

		this.content = content;
	}

}
