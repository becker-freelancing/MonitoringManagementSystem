package com.jabasoft.mms.customermanagement.customer.address.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaAddressRepository extends CrudRepository<JpaAddress, Long> {

}
