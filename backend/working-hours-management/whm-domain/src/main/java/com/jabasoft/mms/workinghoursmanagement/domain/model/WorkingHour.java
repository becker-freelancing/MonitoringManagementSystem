package com.jabasoft.mms.workinghoursmanagement.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class WorkingHour {

	private Long id;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Long customerId;
	private Long projectId;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public LocalDate getDate() {

		return date;
	}

	public void setDate(LocalDate date) {

		this.date = date;
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

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
	}

	public Long getProjectId() {

		return projectId;
	}

	public void setProjectId(Long projectId) {

		this.projectId = projectId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WorkingHour that = (WorkingHour) o;
		return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(
			startTime,
			that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(
			customerId,
			that.customerId) && Objects.equals(projectId, that.projectId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, date, startTime, endTime, customerId, projectId);
	}

}
