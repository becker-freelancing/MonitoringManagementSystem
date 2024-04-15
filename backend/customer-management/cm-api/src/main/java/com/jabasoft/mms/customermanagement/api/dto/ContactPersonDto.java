package com.jabasoft.mms.customermanagement.api.dto;

import java.util.List;

public class ContactPersonDto {

	private String position;
	private String firstName;
	private String lastName;
	private List<String> emails;
	private List<String> phoneNumbers;
	private List<ReasonForContactDto> reasonsForContact;

	public String getPosition() {

		return position;
	}

	public void setPosition(String position) {

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

}
