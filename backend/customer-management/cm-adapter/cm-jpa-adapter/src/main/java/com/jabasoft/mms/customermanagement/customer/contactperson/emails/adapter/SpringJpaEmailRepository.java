package com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaEmailRepository extends CrudRepository<JpaEmail, String> {

}
