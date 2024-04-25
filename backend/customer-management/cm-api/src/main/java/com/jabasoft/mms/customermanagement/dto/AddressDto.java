package com.jabasoft.mms.customermanagement.dto;

import java.util.Objects;

public class AddressDto {

	private Long addressId;
	private String street;
	private String houseNumber;
	private String city;
	private String country;
	private String zipCode;

	public Long getAddressId() {

		return addressId;
	}

	public String getStreet() {

		return street;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setAddressId(Long addressId) {

		this.addressId = addressId;
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

	public String getCountry() {

		return country;
	}

	public void setCountry(String country) {

		this.country = country;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AddressDto that = (AddressDto) o;
		return Objects.equals(addressId, that.addressId) && Objects.equals(street, that.street) && Objects.equals(
			houseNumber,
			that.houseNumber) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(
			zipCode,
			that.zipCode);
	}

	@Override
	public int hashCode() {

		return Objects.hash(addressId, street, houseNumber, city, country, zipCode);
	}

}
