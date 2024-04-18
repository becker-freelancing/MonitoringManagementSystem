package com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter;

import org.springframework.data.repository.CrudRepository;

import com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter.JpaEmail;

import jakarta.transaction.Transactional;

@Transactional
public interface SpringJpaPhoneNumberRepository extends CrudRepository<JpaPhoneNumber, String> {

}
