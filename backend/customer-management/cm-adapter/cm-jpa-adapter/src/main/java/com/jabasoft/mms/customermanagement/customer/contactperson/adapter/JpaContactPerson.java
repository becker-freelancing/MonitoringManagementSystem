package com.jabasoft.mms.customermanagement.customer.contactperson.adapter;

import com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter.JpaContactPersonPosition;
import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter.JpaReasonForContact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACT_PERSONS")
public class JpaContactPerson {

	@Id
	@Column(name = "CONTACT_PERSON_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactPersonId;

	//@Column(name = "CONTACT_PERSON_POSITION")
	@ManyToOne
	@JoinColumn(name = "POSITION", referencedColumnName = "POSITION", unique = false)
	private JpaContactPersonPosition position;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "E_MAIL")
	private String emails;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumbers;

	@ManyToOne
	@JoinColumn(name = "REASON_FOR_CONTACT_ID")
	private JpaReasonForContact reasonForContacts;

	public JpaContactPersonPosition getPosition() {

		return position;
	}

	public void setPosition(JpaContactPersonPosition position) {

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

		return emails;
	}

	public void setEmail(String emails) {

		this.emails = emails;
	}

	public String getPhoneNumber() {

		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {

		this.phoneNumbers = phoneNumbers;
	}

	public Long getContactPersonId() {

		return contactPersonId;
	}

	public void setContactPersonId(Long contactPersonId) {

		this.contactPersonId = contactPersonId;
	}

	public JpaReasonForContact getReasonForContacts() {

		return reasonForContacts;
	}

	public void setReasonForContact(JpaReasonForContact reasonForContacts) {

		this.reasonForContacts = reasonForContacts;
	}

}
