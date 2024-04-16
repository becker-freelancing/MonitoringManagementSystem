package com.jabasoft.mms.customermanagement.dto;

import java.util.List;

public class CustomerDto {

	private String companyName;
	private AddressDto address;
	private List<ContactPersonDto> contactPersons;

	private byte[] logo;

	public String getCompanyName() {

		return companyName;
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

	public void setLogo(byte[] logo) {

		this.logo = logo;
	}

}
