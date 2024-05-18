package com.jabasoft.mms.workinghoursmanagement.dto;

import java.time.LocalDateTime;

public class WorkingHourDto {

	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long customerId;

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

}
