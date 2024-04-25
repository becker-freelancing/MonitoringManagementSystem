package com.jabasoft.mms.customermanagement.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CustomerDto {

	private Long customerId;
	private String companyName;
	private AddressDto address;
	private List<ContactPersonDto> contactPersons;

	private byte[] logo;

	public String getCompanyName() {

		return companyName;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCompanyName(String companyName) {

		this.companyName = companyName;
	}

	public AddressDto getAddress() {

		return address;
	}

	public void setAddress(AddressDto address) {

		this.address = address;
	}

	public List<ContactPersonDto> getContactPersons() {

		return contactPersons;
	}

	public void setContactPersons(List<ContactPersonDto> contactPersons) {

		this.contactPersons = contactPersons;
	}

	public byte[] getLogo() {

		return logo;
	}

	public void setCustomerId(Long id) {

		this.customerId = id;
	}

	public void setLogo(byte[] logo) {

		this.logo = logo;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CustomerDto that = (CustomerDto) o;
		return Objects.equals(customerId, that.customerId)
			&& Objects.equals(companyName, that.companyName)
			&& Objects.equals(address, that.address)
			&& Objects.equals(contactPersons, that.contactPersons)
			&& Arrays.equals(logo, that.logo);
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(customerId, companyName, address, contactPersons);
		result = 31 * result + Arrays.hashCode(logo);
		return result;
	}

}
