package com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter;

import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER_CONTACT_PERSON_E_MAILS")
public class JpaEmail {


	@Id
	@Column(name = "E_MAIL")
	private String email;

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

}
