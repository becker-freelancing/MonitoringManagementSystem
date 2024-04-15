package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

public class EMail {

	private String email;

	public EMail(String email) {

		this.email = email;
	}

	public String getEmail() {

		return email;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EMail eMail = (EMail) o;
		return Objects.equals(email, eMail.email);
	}

	@Override
	public int hashCode() {

		return Objects.hash(email);
	}

}
