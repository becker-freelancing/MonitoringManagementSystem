package com.jabasoft.mms.customermanagement.dto;

import java.util.Objects;

public class AddressDto {

	private Long id;
	private String street;
	private String houseNumber;
	private String city;
	private CountryDto country;
	private String zipCode;

	public Long getId() {

		return id;
	}

	public String getStreet() {

		return street;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setId(Long id) {

		this.id = id;
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

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AddressDto that = (AddressDto) o;
		return Objects.equals(id, that.id) && Objects.equals(street, that.street) && Objects.equals(
			houseNumber,
			that.houseNumber) && Objects.equals(city, that.city) && country == that.country && Objects.equals(
			zipCode,
			that.zipCode);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, street, houseNumber, city, country, zipCode);
	}

}
