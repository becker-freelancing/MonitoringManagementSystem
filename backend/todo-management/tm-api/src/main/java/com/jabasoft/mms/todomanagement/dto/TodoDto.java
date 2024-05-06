package com.jabasoft.mms.todomanagement.dto;

import java.time.LocalDate;

public class TodoDto {

	private Long todoId;

	private String title;
	private String shortDescription;
	private String longDescription;

	private LocalDate creationTime;
	private LocalDate endTime;
	private LocalDate closedTime;

	private Long customerId;

	private TodoCategory category;

	public Long getTodoId() {

		return todoId;
	}

	public void setTodoId(Long todoId) {

		this.todoId = todoId;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getShortDescription() {

		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {

		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {

		return longDescription;
	}

	public void setLongDescription(String longDescription) {

		this.longDescription = longDescription;
	}

	public LocalDate getCreationTime() {

		return creationTime;
	}

	public void setCreationTime(LocalDate creationTime) {

		this.creationTime = creationTime;
	}

	public LocalDate getEndTime() {

		return endTime;
	}

	public void setEndTime(LocalDate endTime) {

		this.endTime = endTime;
	}

	public LocalDate getClosedTime() {

		return closedTime;
	}

	public void setClosedTime(LocalDate closedTime) {

		this.closedTime = closedTime;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

	public TodoCategory getCategory() {

		return category;
	}

	public void setCategory(TodoCategory category) {

		this.category = category;
	}

}
