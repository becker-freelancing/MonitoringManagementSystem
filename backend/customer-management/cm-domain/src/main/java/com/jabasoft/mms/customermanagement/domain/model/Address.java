package com.jabasoft.mms.customermanagement.domain.model;

import java.util.Objects;
import java.util.Optional;

public class Address {

	private AddressId addressId;

	public Address(AddressId addressId, String street, String houseNumber, String city, Country country, String zipCode) {

		this.addressId = addressId;
		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	private String street;
	private String houseNumber;
	private String city;
	private Country country;
	private String zipCode;

	public Address(String street, String houseNumber, String city, Country country, String zipCode) {

		this.street = street;
		this.houseNumber = houseNumber;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}

	public Optional<AddressId> getAddressId() {

		return Optional.ofNullable(addressId);
	}

	public String getStreet() {

		return street;
	}

	public String getHouseNumber() {

		return houseNumber;
	}

	public String getCity() {

		return city;
	}

	public Country getCountry() {

		return country;
	}

	public String getZipCode() {

		return zipCode;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Address address = (Address) o;
		return Objects.equals(street, address.street)
			&& Objects.equals(houseNumber, address.houseNumber)
			&& Objects.equals(city, address.city)
			&& country == address.country
			&& Objects.equals(zipCode, address.zipCode);
	}

	@Override
	public int hashCode() {

		return Objects.hash(street, houseNumber, city, country, zipCode);
	}

}
