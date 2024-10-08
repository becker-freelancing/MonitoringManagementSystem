package com.jabasoft.mms.customermanagement.customer.adapter;

import java.util.List;

import com.jabasoft.mms.customermanagement.customer.address.adapter.JpaAddress;
import com.jabasoft.mms.customermanagement.customer.contactperson.adapter.JpaContactPerson;

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
@Table(name = "CUSTOMERS")
public class JpaCustomer {

	@Id
	@Column(name = "CUSTOMER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "CUSTOMER_TO_CONTACT_PERSONS",
		joinColumns = @JoinColumn(name = "CUSTOMER_ID"),
		inverseJoinColumns = @JoinColumn(name = "CONTACT_PERSON_ID"))
	private List<JpaContactPerson> contactPersons;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id", referencedColumnName = "ADDRESS_ID")
	private JpaAddress address;

	public JpaAddress getAddress() {

		return address;
	}

	public void setAddress(JpaAddress address) {

		this.address = address;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

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
