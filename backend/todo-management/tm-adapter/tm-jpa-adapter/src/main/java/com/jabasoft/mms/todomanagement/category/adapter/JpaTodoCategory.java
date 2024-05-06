package com.jabasoft.mms.todomanagement.category.adapter;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TODO_CATEGORY")
public class JpaTodoCategory {

	@Id
	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "DESCRIPTION")
	private String description;

	public String getCategory() {

		return category;
	}

	public void setCategory(String category) {

		this.category = category;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

}
