package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;

public class ContactPersonId {

	private Long contactPersonId;

	public ContactPersonId(Long contactPersonId) {

		this.contactPersonId = contactPersonId;
	}

	public Long getContactPersonId() {

		return contactPersonId;
	}

	public void setContactPersonId(Long contactPersonId) {

		this.contactPersonId = contactPersonId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ContactPersonId addressId1 = (ContactPersonId) o;
		return Objects.equals(contactPersonId, addressId1.contactPersonId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(contactPersonId);
	}

}
