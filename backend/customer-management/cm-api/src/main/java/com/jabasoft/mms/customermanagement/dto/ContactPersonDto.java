package com.jabasoft.mms.customermanagement.dto;

import java.util.List;
import java.util.Objects;

public class ContactPersonDto {

	private Long id;
	private ContactPersonPositionDto position;
	private String firstName;
	private String lastName;
	private List<String> emails;
	private List<String> phoneNumbers;
	private List<ReasonForContactDto> reasonsForContact;

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

	public List<String> getEmails() {

		return emails;
	}

	public void setEmails(List<String> emails) {

		this.emails = emails;
	}

	public List<String> getPhoneNumbers() {

		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {

		this.phoneNumbers = phoneNumbers;
	}

	public List<ReasonForContactDto> getReasonsForContact() {

		return reasonsForContact;
	}

	public void setReasonsForContact(List<ReasonForContactDto> reasonsForContact) {

		this.reasonsForContact = reasonsForContact;
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
			&& Objects.equals(emails, that.emails)
			&& Objects.equals(phoneNumbers, that.phoneNumbers)
			&& Objects.equals(reasonsForContact, that.reasonsForContact);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, position, firstName, lastName, emails, phoneNumbers, reasonsForContact);
	}

}
