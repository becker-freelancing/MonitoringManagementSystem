package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

class ContactPersonPositionId {

	private Long contactPersonPositionId;

	public ContactPersonPositionId(Long contactPersonPositionId) {

		this.contactPersonPositionId = contactPersonPositionId;
	}

	public Long getContactPersonPositionId() {

		return contactPersonPositionId;
	}

	public void setContactPersonPositionId(Long contactPersonPositionId) {

		this.contactPersonPositionId = contactPersonPositionId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ContactPersonPositionId addressId1 = (ContactPersonPositionId) o;
		return Objects.equals(contactPersonPositionId, addressId1.contactPersonPositionId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(contactPersonPositionId);
	}

}
