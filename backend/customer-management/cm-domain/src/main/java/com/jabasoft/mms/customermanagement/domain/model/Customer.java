package com.jabasoft.mms.customermanagement.domain.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Customer {

	private CustomerId customerId;
	private String companyName;
	private Address address;
	private List<ContactPerson> contactPersons;

	public Customer(String companyName, Address address, List<ContactPerson> contactPersons) {

		this.companyName = companyName;
		this.address = address;
		this.contactPersons = contactPersons;
	}

	public Customer(CustomerId customerId, String companyName, Address address, List<ContactPerson> contactPersons) {

		this.customerId = customerId;
		this.companyName = companyName;
		this.address = address;
		this.contactPersons = contactPersons;
	}

	public Optional<CustomerId> getCustomerId() {

		return Optional.ofNullable(customerId);
	}

	public String getCompanyName() {

		return companyName;
	}

	public Address getAddress() {

		return address;
	}

	public List<ContactPerson> getContactPersons() {

		return contactPersons;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Customer customer = (Customer) o;
		return Objects.equals(customerId, customer.customerId) && Objects.equals(
			companyName,
			customer.companyName) && Objects.equals(address, customer.address) && Objects.equals(
			contactPersons,
			customer.contactPersons);
	}

	@Override
	public int hashCode() {

		return Objects.hash(customerId, companyName, address, contactPersons);
	}

}
