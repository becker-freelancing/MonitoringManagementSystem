package com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CONTACT_PERSON_POSITION")
public class JpaContactPersonPosition {

	@Id
	@Column(name = "POSITION")
	private String position;

	@Column(name = "DESCRIPTION")
	private String description;

	public String getPosition() {

		return position;
	}

	public void setPosition(String position) {

		this.position = position;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

}
