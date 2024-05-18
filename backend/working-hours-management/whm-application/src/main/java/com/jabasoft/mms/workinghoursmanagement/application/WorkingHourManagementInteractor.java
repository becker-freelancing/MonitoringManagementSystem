package com.jabasoft.mms.workinghoursmanagement.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.workinghoursmanagement.api.WorkingHourManagementPort;
import com.jabasoft.mms.workinghoursmanagement.domain.model.WorkingHour;
import com.jabasoft.mms.workinghoursmanagement.dto.WorkingHourDto;
import com.jabasoft.mms.workinghoursmanagement.spi.WorkingHourRepository;

@Component
class WorkingHourManagementInteractor implements WorkingHourManagementPort {

	private WorkingHourRepository repository;

	@Autowired
	public WorkingHourManagementInteractor(WorkingHourRepository repository) {

		this.repository = repository;
	}

	@Override
	public Optional<WorkingHourDto> save(WorkingHourDto workingHourDto) {

		return repository.save(map(workingHourDto)).map(this::map);
	}

	@Override
	public Optional<WorkingHourDto> delete(Long id) {

		return repository.delete(id).map(this::map);
	}

	@Override
	public Optional<WorkingHourDto> find(Long id) {

		return repository.find(id).map(this::map);
	}

	@Override
	public List<WorkingHourDto> findAll() {

		return repository.findAll().stream().map(this::map).toList();
	}

	@Override
	public Optional<WorkingHourDto> findCurrentOpenForCustomer(Long customerId) {


		return repository.findOpenForCustomer(customerId).map(this::map);
	}

	@Override
	public Optional<WorkingHourDto> findAnyOpenWorkingHour() {

		return repository.findAnyOpenWorkingHour().map(this::map);
	}

	protected WorkingHourDto map(WorkingHour workingHour){

		WorkingHourDto workingHourDto = new WorkingHourDto();

		workingHourDto.setId(workingHour.getId());
		workingHourDto.setStartTime(workingHour.getStartTime());
		workingHourDto.setEndTime(workingHour.getEndTime());
		workingHourDto.setCustomerId(workingHour.getCustomerId());

		return workingHourDto;
	}

	protected WorkingHour map(WorkingHourDto workingHourDto){

		WorkingHour workingHour = new WorkingHour();

		workingHour.setId(workingHourDto.getId());
		workingHour.setStartTime(workingHourDto.getStartTime());
		workingHour.setEndTime(workingHourDto.getEndTime());
		workingHour.setCustomerId(workingHourDto.getCustomerId());

		return workingHour;
	}
}
