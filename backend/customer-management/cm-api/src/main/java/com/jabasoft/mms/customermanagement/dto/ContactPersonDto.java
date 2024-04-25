package com.jabasoft.mms.customermanagement.dto;

import java.util.List;
import java.util.Objects;

public class ContactPersonDto {

	private Long id;
	private ContactPersonPositionDto position;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private ReasonForContactDto reasonForContact;

	public Long getId() {

		return id;
	}

	public ContactPersonPositionDto getPosition() {

		return position;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public void setPosition(ContactPersonPositionDto position) {

		this.position = position;
	}

	public String getFirstName() {

		return firstName;
	}

	public void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getPhoneNumber() {

		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;
	}

	public ReasonForContactDto getReasonForContact() {

		return reasonForContact;
	}

	public void setReasonForContact(ReasonForContactDto reasonForContact) {

		this.reasonForContact = reasonForContact;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ContactPersonDto that = (ContactPersonDto) o;
		return Objects.equals(id, that.id)
			&& Objects.equals(position, that.position)
			&& Objects.equals(
			firstName,
			that.firstName)
			&& Objects.equals(lastName, that.lastName)
			&& Objects.equals(email, that.email)
			&& Objects.equals(phoneNumber, that.phoneNumber)
			&& Objects.equals(reasonForContact, that.reasonForContact);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, position, firstName, lastName, email, phoneNumber, reasonForContact);
	}

}
