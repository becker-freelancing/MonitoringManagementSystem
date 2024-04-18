package com.jabasoft.mms.customermanagement.domain.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ContactPerson {

	private ContactPersonId contactPersonId;
	private ContactPersonPosition position;
	private String firstName;
	private String lastName;
	private List<EMail> emails;
	private List<PhoneNumber> phoneNumbers;
	private List<ReasonForContact> reasonsForContact;

	public ContactPerson(
		ContactPersonId contactPersonId,
		ContactPersonPosition position,
		String firstName,
		String lastName,
		List<EMail> emails,
		List<PhoneNumber> phoneNumbers,
		List<ReasonForContact> reasonsForContact) {

		this.contactPersonId = contactPersonId;
		this.position = position;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emails = emails;
		this.phoneNumbers = phoneNumbers;
		this.reasonsForContact = reasonsForContact;
	}

	public ContactPerson(
		ContactPersonPosition position,
		String firstName,
		String lastName,
		List<EMail> emails,
		List<PhoneNumber> phoneNumbers,
		List<ReasonForContact> reasonsForContact) {

		this.position = position;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emails = emails;
		this.phoneNumbers = phoneNumbers;
		this.reasonsForContact = reasonsForContact;
	}

	public Optional<ContactPersonId> getContactPersonId() {

		return Optional.ofNullable(contactPersonId);
	}

	public ContactPersonPosition getPosition() {

		return position;
	}

	public String getFirstName() {

		return firstName;
	}

	public String getLastName() {

		return lastName;
	}

	public List<EMail> getEmails() {

		return emails;
	}

	public List<PhoneNumber> getPhoneNumbers() {

		return phoneNumbers;
	}

	public List<ReasonForContact> getReasonsForContact() {

		return reasonsForContact;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ContactPerson that = (ContactPerson) o;
		return Objects.equals(position, that.position)
			&& Objects.equals(firstName, that.firstName)
			&& Objects.equals(lastName, that.lastName)
			&& Objects.equals(emails, that.emails)
			&& Objects.equals(phoneNumbers, that.phoneNumbers)
			&& Objects.equals(reasonsForContact, that.reasonsForContact);
	}

	@Override
	public int hashCode() {

		return Objects.hash(position, firstName, lastName, emails, phoneNumbers, reasonsForContact);
	}

}
