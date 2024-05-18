package com.jabasoft.mms.workinghoursmanagement.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.workinghoursmanagement.domain.model.WorkingHour;

public interface WorkingHourRepository {

	Optional<WorkingHour> delete(Long id);
	Optional<WorkingHour> find(Long id);
	List<WorkingHour> findAll();
	Optional<WorkingHour> findAnyOpenWorkingHour();
	Optional<WorkingHour> findOpenForCustomer(Long customerId);
	Optional<WorkingHour> save(WorkingHour workingHour);

}
