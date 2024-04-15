package com.jabasoft.mms.customermanagement.test.adapter;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	//...

	@Column
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Address getAddress() {

		return address;
	}

	public void setAddress(Address address) {

		this.address = address;
	}

	// ... getters and setters

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(
			address,
			user.address);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, address);
	}

}
