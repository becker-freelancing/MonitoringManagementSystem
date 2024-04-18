package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaReasonForContactRepository extends CrudRepository<JpaReasonForContact, String> {

	public boolean existsByReason(String reason);

}
