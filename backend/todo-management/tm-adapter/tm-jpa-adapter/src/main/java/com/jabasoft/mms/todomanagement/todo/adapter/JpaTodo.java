package com.jabasoft.mms.todomanagement.todo.adapter;

import java.time.LocalDateTime;

import com.jabasoft.mms.todomanagement.category.adapter.JpaTodoCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TODOS")
public class JpaTodo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TODO_ID")
	private Long todoId;

	@Column(name = "TITLE")
	private String title;
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;
	@Column(name = "LONG_DESCRIPTION")
	private String longDescription;

	@Column(name = "CREATION_TIME")
	private LocalDateTime creationTime;
	@Column(name = "END_TIME")
	private LocalDateTime endTime;
	@Column(name = "CLOSED_TIME")
	private LocalDateTime closedTime;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY", referencedColumnName = "CATEGORY")
	private JpaTodoCategory category;

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

	public JpaTodoCategory getCategory() {

		return category;
	}

	public void setCategory(JpaTodoCategory category) {

		this.category = category;
	}

}
