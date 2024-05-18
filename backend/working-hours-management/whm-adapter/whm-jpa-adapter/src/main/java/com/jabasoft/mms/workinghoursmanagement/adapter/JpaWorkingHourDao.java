package com.jabasoft.mms.workinghoursmanagement.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.workinghoursmanagement.domain.model.WorkingHour;
import com.jabasoft.mms.workinghoursmanagement.spi.WorkingHourRepository;

@Component
class JpaWorkingHourDao implements WorkingHourRepository {

	private SpringJpaWorkingHourRepository repository;

	@Autowired
	public JpaWorkingHourDao(SpringJpaWorkingHourRepository repository) {

		this.repository = repository;
	}

	@Override
	public Optional<WorkingHour> delete(Long id) {

		Optional<JpaWorkingHour> byId = repository.findById(id);

		if(byId.isEmpty()){
			return Optional.empty();
		}

		repository.deleteById(id);

		return byId.map(this::map);
	}

	@Override
	public Optional<WorkingHour> find(Long id) {

		return repository.findById(id).map(this::map);
	}

	@Override
	public List<WorkingHour> findAll() {

		Iterable<JpaWorkingHour> all = repository.findAll();
		List<WorkingHour> workingHours = new ArrayList<>();

		for (JpaWorkingHour jpaWorkingHour : all) {
			workingHours.add(map(jpaWorkingHour));
		}

		return workingHours;
	}

	@Override
	public Optional<WorkingHour> findAnyOpenWorkingHour() {

		Optional<JpaWorkingHour> openWorkingHour = repository.findByEndTimeIsNull();

		return openWorkingHour.map(this::map);
	}

	@Override
	public Optional<WorkingHour> findOpenForCustomer(Long customerId) {

		return repository.findByCustomerIdAndAndEndTimeIsNull(customerId).map(this::map);
	}

	@Override
	public Optional<WorkingHour> save(WorkingHour workingHour) {

		JpaWorkingHour jpaWorkingHour = map(workingHour);
		JpaWorkingHour saved = repository.save(jpaWorkingHour);
		return Optional.of(saved).map(this::map);
	}

	private WorkingHour map(JpaWorkingHour jpaWorkingHour) {

		WorkingHour workingHour = new WorkingHour();

		workingHour.setId(jpaWorkingHour.getId());
		workingHour.setStartTime(jpaWorkingHour.getStartTime());
		workingHour.setEndTime(jpaWorkingHour.getEndTime());
		workingHour.setCustomerId(jpaWorkingHour.getCustomerId());

		return workingHour;
	}

	private JpaWorkingHour map(WorkingHour workingHour) {

		JpaWorkingHour jpaWorkingHour = new JpaWorkingHour();

		jpaWorkingHour.setId(workingHour.getId());
		jpaWorkingHour.setStartTime(workingHour.getStartTime());
		jpaWorkingHour.setEndTime(workingHour.getEndTime());
		jpaWorkingHour.setCustomerId(workingHour.getCustomerId());

		return jpaWorkingHour;
	}
}
