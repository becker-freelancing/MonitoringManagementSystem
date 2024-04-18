package com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaPositionRepository extends CrudRepository<JpaContactPersonPosition, String> {

}
