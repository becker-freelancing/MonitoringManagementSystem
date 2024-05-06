package com.jabasoft.mms.todomanagement.domain.model;

import java.util.Objects;

public class TodoCategory {

	private String category;
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

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TodoCategory that = (TodoCategory) o;
		return Objects.equals(category, that.category) && Objects.equals(description, that.description);
	}

	@Override
	public int hashCode() {

		return Objects.hash(category, description);
	}

	@Override
	public String toString() {

		return "TodoCategory{" +
			"category='" + category + '\'' +
			", description='" + description + '\'' +
			'}';
	}

}
