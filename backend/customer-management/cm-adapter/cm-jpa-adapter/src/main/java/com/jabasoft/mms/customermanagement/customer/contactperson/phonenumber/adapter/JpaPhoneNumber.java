package com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter;

import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER_CONTACT_PERSON_PHONE_NUMBERS")
public class JpaPhoneNumber {

	@Id
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTACT_PERSON_ID")
	private JpaContactPerson contactPersonId;

	public String getPhoneNumber() {

		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;
	}

	public JpaContactPerson getContactPersonId() {

		return contactPersonId;
	}

	public void setContactPersonId(JpaContactPerson contactPersonId) {

		this.contactPersonId = contactPersonId;
	}

}
