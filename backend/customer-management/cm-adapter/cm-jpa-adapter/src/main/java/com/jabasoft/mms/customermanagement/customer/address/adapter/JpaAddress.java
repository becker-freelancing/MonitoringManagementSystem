package com.jabasoft.mms.customermanagement.customer.address.adapter;

import java.math.BigInteger;

import com.jabasoft.mms.customermanagement.customer.adapter.JpaCustomer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ADDRESSES")
public class JpaAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID")
	private Long id;

	@Column(name = "STREET")
	private String street;

	@Column(name = "HOUSE_NUMBER")
	private String houseNumber;

	@Column(name = "CITY")
	private String city;

	@Column(name = "COUTRY")
	private BigInteger country;

	@Column(name = "ZIP_CODE")
	private String zipCode;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getStreet() {

		return street;
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

	public BigInteger getCountry() {

		return country;
	}

	public void setCountry(BigInteger country) {

		this.country = country;
	}

	public String getZipCode() {

		return zipCode;
	}

	public void setZipCode(String zipCode) {

		this.zipCode = zipCode;
	}

}
