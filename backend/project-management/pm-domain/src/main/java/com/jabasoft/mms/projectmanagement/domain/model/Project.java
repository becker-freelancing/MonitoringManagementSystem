package com.jabasoft.mms.projectmanagement.domain.model;

import java.time.LocalDate;

public class Project {

	private Long projectId;

	private String title;
	private String shortDescription;
	private String longDescription;

	private LocalDate creationTime;
	private LocalDate startTime;
	private LocalDate endTime;
	private LocalDate closedTime;

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
