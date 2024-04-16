package com.jabasoft.mms.customermanagement.customer.contactperson.position.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;

public interface ContactPersonPositionRepository {

	public Optional<ContactPersonPosition> deleteByPosition(String position);
	public List<ContactPersonPosition> findAll();
	public Optional<ContactPersonPosition> findByPosition(String position);
	public Optional<ContactPersonPosition> savePosition(ContactPersonPosition contactPersonPosition);

}
