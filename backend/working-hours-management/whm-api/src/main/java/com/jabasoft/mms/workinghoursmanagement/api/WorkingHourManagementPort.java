package com.jabasoft.mms.workinghoursmanagement.api;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.workinghoursmanagement.dto.WorkingHourDto;

public interface WorkingHourManagementPort {

	public Optional<WorkingHourDto> save(WorkingHourDto workingHourDto);
	public Optional<WorkingHourDto> delete(Long id);
	public Optional<WorkingHourDto> find(Long id);
	public List<WorkingHourDto> findAll();
	public Optional<WorkingHourDto> findCurrentOpenForCustomer(Long customerId);
	public Optional<WorkingHourDto> findAnyOpenWorkingHour();
}
