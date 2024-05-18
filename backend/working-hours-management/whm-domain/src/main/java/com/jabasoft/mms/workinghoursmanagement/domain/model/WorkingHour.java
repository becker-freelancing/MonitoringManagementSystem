package com.jabasoft.mms.workinghoursmanagement.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class WorkingHour {

	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Long customerId;

	public Long getId() {

		return id;
	}

	public Long getCustomerId() {

		return customerId;
	}

	public void setCustomerId(Long customerId) {

		this.customerId = customerId;
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

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		WorkingHour that = (WorkingHour) o;
		return Objects.equals(id, that.id)
			&& Objects.equals(startTime, that.startTime)
			&& Objects.equals(endTime, that.endTime)
			&& Objects.equals(customerId, that.customerId);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, startTime, endTime, customerId);
	}

}
