package com.jabasoft.mms.customermanagement.customer.contactperson.position.api;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.customermanagement.dto.ContactPersonPositionDto;

public interface ContactPersonPositionPort {


	public List<ContactPersonPositionDto> getPositions();
	public Optional<ContactPersonPositionDto> getPosition(String position);
	public Optional<ContactPersonPositionDto> deletePosition(String position);
	public Optional<ContactPersonPositionDto> savePosition(ContactPersonPositionDto position);

}
