package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter;

import java.math.BigInteger;

import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER_CONTACT_PERSON_REASON_FOR_CONTACT")
public class JpaReasonForContact {

	@Id
	@Column(name = "REASON_FOR_CONTACT")
	private BigInteger reasonForContact;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTACT_PERSON_ID")
	private JpaContactPerson contactPersonId;

	public BigInteger getReasonForContact() {

		return reasonForContact;
	}

	public void setReasonForContact(BigInteger reasonForContact) {

		this.reasonForContact = reasonForContact;
	}

	public JpaContactPerson getContactPersonId() {

		return contactPersonId;
	}

	public void setContactPersonId(JpaContactPerson contactPersonId) {

		this.contactPersonId = contactPersonId;
	}

}
