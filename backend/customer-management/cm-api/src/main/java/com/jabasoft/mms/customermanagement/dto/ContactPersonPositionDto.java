package com.jabasoft.mms.customermanagement.dto;

import java.util.Objects;

public class ContactPersonPositionDto {

	private String position;
	private String description;

	public void setPosition(String position) {

		this.position = position;
	}

	public void setDescription(String description) {

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
		ContactPersonPositionDto dto = (ContactPersonPositionDto) o;
		return Objects.equals(position, dto.position) && Objects.equals(description, dto.description);
	}

	@Override
	public int hashCode() {

		return Objects.hash(position, description);
	}

}
