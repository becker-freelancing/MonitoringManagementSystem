package com.jabasoft.mms.workinghoursmanagement.adapter;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "WORKING_HOURS")
public class JpaWorkingHour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DATE")
	private LocalDate date;

	@Column(name = "START_TIME")
	private LocalTime startTime;

	@Column(name = "END_TIME")
	private LocalTime endTime;

	@Column(name = "CUSTOMER_ID")
	private Long customerId;

	@Column(name = "PROJECT_ID")
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
