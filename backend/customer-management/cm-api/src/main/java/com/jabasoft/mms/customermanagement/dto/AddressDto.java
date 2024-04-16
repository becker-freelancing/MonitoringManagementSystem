package com.jabasoft.mms.customermanagement.dto;

public class AddressDto {

	private String street;
	private String houseNumber;
	private String city;
	private CountryDto country;
	private String zipCode;

	public String getStreet() {

		return street;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setStreet(String street) {

		this.street = street;
	}

	public String getHouseNumber() {

		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {

		this.houseNumber = houseNumber;
	}

	public String getCity() {

		return city;
	}

	public void setCity(String city) {

		this.city = city;
	}

	public CountryDto getCountry() {

		return country;
	}

	public void setCountry(CountryDto country) {

		this.country = country;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}

}
