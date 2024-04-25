package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;
import java.util.Optional;

public class ContactPerson {

	private ContactPersonId contactPersonId;
	private ContactPersonPosition position;
	private String firstName;
	private String lastName;
	private EMail emails;
	private PhoneNumber phoneNumbers;
	private ReasonForContact reasonsForContact;

	public ContactPerson(
		ContactPersonId contactPersonId,
		ContactPersonPosition position,
		String firstName,
		String lastName,
		EMail emails,
		PhoneNumber phoneNumbers,
		ReasonForContact reasonsForContact) {

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
		EMail emails,
		PhoneNumber phoneNumbers,
		ReasonForContact reasonsForContact) {

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

	public EMail getEmail() {

		return emails;
	}

	public PhoneNumber getPhoneNumber() {

		return phoneNumbers;
	}

	public ReasonForContact getReasonForContact() {

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
