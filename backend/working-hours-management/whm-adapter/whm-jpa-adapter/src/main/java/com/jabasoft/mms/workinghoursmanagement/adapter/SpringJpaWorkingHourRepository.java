package com.jabasoft.mms.workinghoursmanagement.adapter;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaWorkingHourRepository extends CrudRepository<JpaWorkingHour, Long> {

	public Optional<JpaWorkingHour> findByCustomerIdAndAndEndTimeIsNull(Long customerId);

	public Optional<JpaWorkingHour> findByEndTimeIsNull();
}
