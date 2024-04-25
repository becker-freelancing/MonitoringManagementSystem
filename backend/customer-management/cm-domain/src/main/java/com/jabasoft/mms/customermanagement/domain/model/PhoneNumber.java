package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

public class PhoneNumber {

	private String phoneNumber;

	public PhoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {

		return phoneNumber;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PhoneNumber that = (PhoneNumber) o;
		return Objects.equals(phoneNumber, that.phoneNumber);
	}

	@Override
	public int hashCode() {

		return Objects.hash(phoneNumber);
	}

}
