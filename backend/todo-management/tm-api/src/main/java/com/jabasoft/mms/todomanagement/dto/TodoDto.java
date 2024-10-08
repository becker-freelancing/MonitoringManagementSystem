package com.jabasoft.mms.todomanagement.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class TodoDto {

	private Long todoId;

	private String title;
	private String shortDescription;
	private String longDescription;

	private LocalDateTime creationTime;
	private LocalDateTime endTime;
	private LocalDateTime closedTime;

	private Long customerId;

	private TodoCategoryDto category;

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

	public TodoCategoryDto getCategory() {

		return category;
	}

	public void setCategory(TodoCategoryDto category) {

		this.category = category;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TodoDto todoDto = (TodoDto) o;
		return Objects.equals(todoId, todoDto.todoId)
			&& Objects.equals(title, todoDto.title)
			&& Objects.equals(shortDescription, todoDto.shortDescription)
			&& Objects.equals(longDescription, todoDto.longDescription)
			&& Objects.equals(creationTime, todoDto.creationTime)
			&& Objects.equals(endTime, todoDto.endTime)
			&& Objects.equals(closedTime, todoDto.closedTime)
			&& Objects.equals(customerId, todoDto.customerId)
			&& Objects.equals(category, todoDto.category);
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

		return "TodoDto{" +
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
