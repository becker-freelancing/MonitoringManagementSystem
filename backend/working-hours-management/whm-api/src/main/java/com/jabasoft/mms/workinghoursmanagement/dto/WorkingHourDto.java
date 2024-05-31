package com.jabasoft.mms.workinghoursmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingHourDto {

	private Long id;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Long customerId;
	private Long projectId;

	public LocalDate getDate() {

		return date;
	}

	public void setDate(LocalDate date) {

		this.date = date;
	}

	public Long getProjectId() {

		return projectId;
	}

	public void setProjectId(Long projectId) {

		this.projectId = projectId;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public LocalTime getStartTime() {

		return startTime;
	}

	public void setStartTime(LocalTime startTime) {

		this.startTime = startTime;
	}

	public LocalTime getEndTime() {

		return endTime;
	}

	public void setEndTime(LocalTime endTime) {

		this.endTime = endTime;
	}

}
