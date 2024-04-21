package com.jabasoft.mms.customermanagement.dto;

import java.util.Objects;

public class ReasonForContactDto {

	private String reason;
	private String description;

	public void setReason(String reason) {

		this.reason = reason;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getReason() {

		return reason;
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
		ReasonForContactDto that = (ReasonForContactDto) o;
		return Objects.equals(reason, that.reason) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {

		return Objects.hash(reason, description);
	}

}
