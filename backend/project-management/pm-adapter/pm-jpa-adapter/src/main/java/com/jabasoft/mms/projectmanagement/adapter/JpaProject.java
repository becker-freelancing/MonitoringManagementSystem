package com.jabasoft.mms.projectmanagement.adapter;

import java.time.LocalDate;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROJECTS")
public class JpaProject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROJECT_ID")
	private Long projectId;


	@Column(name = "TITLE")
	private String title;
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;
	@Column(name = "LONG_DESCRIPTION")
	private String longDescription;

	@Column(name = "CREATION_TIME")
	private LocalDate creationTime;
	@Column(name = "START_TIME")
	private LocalDate startTime;
	@Column(name = "END_TIME")
	private LocalDate endTime;
	@Column(name = "CLOSED_TIME")
	private LocalDate closedTime;

	@Column(name = "CUSTOMER")
	private Long customerId;

	public Long getProjectId() {

		return projectId;
	}

	public void setProjectId(Long projectId) {

		this.projectId = projectId;
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

	public LocalDate getStartTime() {

		return startTime;
	}

	public void setStartTime(LocalDate startTime) {

		this.startTime = startTime;
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

}

