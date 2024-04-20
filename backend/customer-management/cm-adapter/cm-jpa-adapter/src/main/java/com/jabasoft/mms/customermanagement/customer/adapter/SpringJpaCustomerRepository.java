package com.jabasoft.mms.customermanagement.customer.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaCustomerRepository extends CrudRepository<JpaCustomer, Long> {

	public boolean existsByCustomerId(Long customerId);
}
