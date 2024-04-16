package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

public class ContactPersonPosition {

	private String position;
	private String description;

	public ContactPersonPosition(String position) {

		this.position = position;
	}

	public ContactPersonPosition(String position, String description) {

		this.position = position;
		this.description = description;
	}

	public String getPosition() {

		return position;
	}

	public String getDescription() {

		return description;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ContactPersonPosition that = (ContactPersonPosition) o;
		return Objects.equals(position, that.position) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {

		return Objects.hash(position, description);
	}

}
