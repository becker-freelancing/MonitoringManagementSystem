package com.jabasoft.mms.customermanagement.customer.adapter;

import java.util.List;

import com.jabasoft.mms.customermanagement.customer.address.adapter.JpaAddress;
import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;
import com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter.JpaPhoneNumber;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class JpaCustomer {

	@Id
	@Column(name = "CUSTOMER_ID")
	private String customerId;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "contactPersonId", cascade = CascadeType.ALL)
	private List<JpaContactPerson> contactPersons;

	//@OneToOne(fetch = FetchType.EAGER, mappedBy = "address_id", cascade = CascadeType.ALL)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private JpaAddress address;

	public JpaAddress getAddress() {

		return address;
	}

	public void setAddress(JpaAddress address) {

		this.address = address;
	}

	public String getCustomerId() {

		return customerId;
	}

	public void setCustomerId(String customerId) {

		this.customerId = customerId;
	}

	public String getCompanyName() {

		return companyName;
	}

	public void setCompanyName(String companyName) {

		this.companyName = companyName;
	}

	public List<JpaContactPerson> getContactPersons() {

		return contactPersons;
	}

	public void setContactPersons(List<JpaContactPerson> contactPersons) {

		this.contactPersons = contactPersons;
	}

}
