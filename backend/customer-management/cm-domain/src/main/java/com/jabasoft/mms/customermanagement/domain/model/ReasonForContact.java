package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

public class ReasonForContact {

	private String reason;
	private String description;

	public ReasonForContact(String reason) {

		this.reason = reason;
	}

	public ReasonForContact(String reason, String description) {

		this.reason = reason;
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
		ReasonForContact that = (ReasonForContact) o;
		return Objects.equals(reason, that.reason) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {

		return Objects.hash(reason, description);
	}

}
