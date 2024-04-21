package com.jabasoft.mms.customermanagement.customer.contactperson.adapter;

import java.math.BigInteger;
import java.util.List;

import com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter.JpaEmail;
import com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter.JpaPhoneNumber;
import com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter.JpaContactPersonPosition;
import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter.JpaReasonForContact;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
	@OneToOne
	@JoinColumn(name = "POSITION", referencedColumnName = "POSITION")
	private JpaContactPersonPosition position;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTACT_PERSON_ID", referencedColumnName = "CONTACT_PERSON_ID")
	private List<JpaEmail> emails;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTACT_PERSON_ID", referencedColumnName = "CONTACT_PERSON_ID")
	private List<JpaPhoneNumber> phoneNumbers;

	@ManyToMany
	@JoinTable(name = "CONTACT_PERSON_TO_REASON_FOR_CONTACT",
	joinColumns = @JoinColumn(name = "REASON_FOR_CONTACT"),
	inverseJoinColumns = @JoinColumn(name = "REASON"))
	private List<JpaReasonForContact> reasonForContacts;

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

	public List<JpaEmail> getEmails() {

		return emails;
	}

	public void setEmails(List<JpaEmail> emails) {

		this.emails = emails;
	}

	public List<JpaPhoneNumber> getPhoneNumbers() {

		return phoneNumbers;
	}

	public void setPhoneNumbers(List<JpaPhoneNumber> phoneNumbers) {

		this.phoneNumbers = phoneNumbers;
	}

	public Long getContactPersonId() {

		return contactPersonId;
	}

	public void setContactPersonId(Long contactPersonId) {

		this.contactPersonId = contactPersonId;
	}

	public List<JpaReasonForContact> getReasonForContacts() {

		return reasonForContacts;
	}

	public void setReasonForContacts(List<JpaReasonForContact> reasonForContacts) {

		this.reasonForContacts = reasonForContacts;
	}

}
