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
@Table(name = "CONTACT_PERSON_REASON_FOR_CONTACT")
public class JpaReasonForContact {

	@Id
	@Column(name = "REASON")
	private String reason;

	@Column(name = "DESCRIPTION")
	private String description;

	public String getReason() {

		return reason;
	}

	public void setReason(String reason) {

		this.reason = reason;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

}
