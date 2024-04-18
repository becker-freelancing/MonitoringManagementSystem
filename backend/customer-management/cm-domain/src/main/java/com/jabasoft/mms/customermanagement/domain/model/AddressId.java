package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

public class AddressId {

	private Long addressId;

	public AddressId(Long addressId) {

		this.addressId = addressId;
	}

	public Long getAddressId() {

		return addressId;
	}

	public void setAddressId(Long addressId) {

		this.addressId = addressId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AddressId addressId1 = (AddressId) o;
		return Objects.equals(addressId, addressId1.addressId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(addressId);
	}

}
