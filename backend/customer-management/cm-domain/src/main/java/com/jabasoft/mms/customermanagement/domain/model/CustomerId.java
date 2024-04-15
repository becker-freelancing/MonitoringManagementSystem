package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

public class CustomerId {

	private String customerId;

	public CustomerId(String customerId) {

		this.customerId = customerId;
	}

	public String getCustomerId() {

		return customerId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CustomerId that = (CustomerId) o;
		return Objects.equals(customerId, that.customerId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(customerId);
	}

}
