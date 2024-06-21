package com.jabasoft.mms.documentmanagement.domain.model;

import java.util.Arrays;
import java.util.Objects;

public class Document extends DocumentWithoutContent{

	private byte[] content;


	public byte[] getContent() {

		return content;
	}

	public void setContent(byte[] content) {

		this.content = content;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Document document))
			return false;
		if (!super.equals(o))
			return false;

		return Arrays.equals(content, document.content) && super.equals(document);
	}

	@Override
	public int hashCode() {

		return Objects.hash(Arrays.hashCode(content), super.hashCode());
	}

}
