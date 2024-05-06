package com.jabasoft.mms.todomanagement.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Todo {

	private Long todoId;

	private String title;
	private String shortDescription;
	private String longDescription;

	private LocalDateTime creationTime;
	private LocalDateTime endTime;
	private LocalDateTime closedTime;

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

	public LocalDateTime getCreationTime() {

		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {

		this.creationTime = creationTime;
	}

	public LocalDateTime getEndTime() {

		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {

		this.endTime = endTime;
	}

	public LocalDateTime getClosedTime() {

		return closedTime;
	}

	public void setClosedTime(LocalDateTime closedTime) {

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

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Todo todo = (Todo) o;
		return Objects.equals(todoId, todo.todoId)
			&& Objects.equals(title, todo.title)
			&& Objects.equals(shortDescription, todo.shortDescription)
			&& Objects.equals(longDescription, todo.longDescription)
			&& Objects.equals(creationTime, todo.creationTime)
			&& Objects.equals(endTime, todo.endTime)
			&& Objects.equals(closedTime, todo.closedTime)
			&& Objects.equals(customerId, todo.customerId)
			&& Objects.equals(category, todo.category);
	}

	@Override
	public int hashCode() {

		return Objects.hash(
			todoId,
			title,
			shortDescription,
			longDescription,
			creationTime,
			endTime,
			closedTime,
			customerId,
			category);
	}

	@Override
	public String toString() {

		return "Todo{" +
			"todoId=" + todoId +
			", title='" + title + '\'' +
			", shortDescription='" + shortDescription + '\'' +
			", longDescription='" + longDescription + '\'' +
			", creationTime=" + creationTime +
			", endTime=" + endTime +
			", closedTime=" + closedTime +
			", customerId=" + customerId +
			", category=" + category +
			'}';
	}

}
