package com.jabasoft.mms.customermanagement.customer.contactperson.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaContactPersonRepository extends CrudRepository<JpaContactPerson, Long> {

}
