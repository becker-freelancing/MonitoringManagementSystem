package com.jabasoft.mms.projectmanagement.dto;

import java.time.LocalDateTime;

public class ProjectDto {

	private Long projectId;

	private String title;
	private String shortDescription;
	private String longDescription;

	private LocalDateTime creationTime;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

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

	public LocalDateTime getCreationTime() {

		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {

		this.creationTime = creationTime;
	}

	public LocalDateTime getStartTime() {

		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {

		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {

		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {

		this.endTime = endTime;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

}
