package com.jabasoft.mms.customermanagement.test.adapter;

import java.util.Objects;

import com.jabasoft.mms.customermanagement.domain.model.Country;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	//...

	@Column
	private String street;

	@Column
	@Convert(converter = CountryConverter2.class)
	private Country country;

	@OneToOne(mappedBy = "address")
	private User user;

	public Country getCountry() {

		return country;
	}

	public void setCountry(Country country) {

		this.country = country;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Address address = (Address) o;
		return Objects.equals(id, address.id)
			&& Objects.equals(street, address.street)
			&& Objects.equals(country, address.country);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, street, country);
	}

	public String getStreet() {

		return street;
	}

	public void setStreet(String street) {

		this.street = street;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	//... getters and setters
}
