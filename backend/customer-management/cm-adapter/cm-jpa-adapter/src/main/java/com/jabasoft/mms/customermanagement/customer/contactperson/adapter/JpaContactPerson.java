package com.jabasoft.mms.customermanagement.customer.contactperson.adapter;

import java.math.BigInteger;
import java.util.List;

import com.jabasoft.mms.customermanagement.customer.adapter.JpaCustomer;
import com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter.JpaEmail;
import com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter.JpaPhoneNumber;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACT_PERSONS")
public class JpaContactPerson {

	@Id
	@Column(name = "CONTACT_PERSON_ID")
	private String contactPersonId;

	@Column(name = "CONTACT_PERSON_POSITION")
	private BigInteger position;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPersonId", cascade = CascadeType.ALL)
	private List<JpaEmail> emails;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPersonId", cascade = CascadeType.ALL)
	private List<JpaPhoneNumber> phoneNumbers;

//	@ManyToMany
//	@JoinTable(name = "CONTACT_PERSON_REASON_FOR_CONTACT",
//		joinColumns = @JoinColumn(name = "CONTACT_PERSON_ID"),
//		inverseJoinColumns = @JoinColumn(name = "REASON"))
//	private List<JpaReasonForContact> reasonForContacts;

	public BigInteger getPosition() {

		return position;
	}

	public void setPosition(BigInteger position) {

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

	public String getContactPersonId() {

		return contactPersonId;
	}

	public void setContactPersonId(String contactPersonId) {

		this.contactPersonId = contactPersonId;
	}

//	public List<JpaReasonForContact> getReasonForContacts() {
//
//		return reasonForContacts;
//	}
//
//	public void setReasonForContacts(List<JpaReasonForContact> reasonForContacts) {
//
//		this.reasonForContacts = reasonForContacts;
//	}

}
