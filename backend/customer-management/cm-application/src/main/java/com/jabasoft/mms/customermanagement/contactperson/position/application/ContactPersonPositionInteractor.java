package com.jabasoft.mms.customermanagement.contactperson.position.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.contactperson.position.api.ContactPersonPositionPort;
import com.jabasoft.mms.customermanagement.customer.contactperson.position.spi.ContactPersonPositionRepository;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.dto.ContactPersonPositionDto;

@Component
class ContactPersonPositionInteractor implements ContactPersonPositionPort {

	private ContactPersonPositionRepository positionRepository;

	@Autowired
	public ContactPersonPositionInteractor(ContactPersonPositionRepository positionRepository) {

		this.positionRepository = positionRepository;
	}

	@Override
	public List<ContactPersonPositionDto> getPositions() {

		List<ContactPersonPosition> positions = positionRepository.findAll();

		return positions.stream().map(this::mapToDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ContactPersonPositionDto> getPosition(String position) {

		Optional<ContactPersonPosition> foundPositions = positionRepository.findByPosition(position);

		return foundPositions.map(this::mapToDto);
	}

	@Override
	public Optional<ContactPersonPositionDto> deletePosition(String position) {

		Optional<ContactPersonPosition> deletedPositions = positionRepository.deleteByPosition(position);

		return deletedPositions.map(this::mapToDto);
	}

	@Override
	public Optional<ContactPersonPositionDto> savePosition(ContactPersonPositionDto position) {

		ContactPersonPosition contactPersonPosition = mapToDomain(position);

		Optional<ContactPersonPosition> savePosition = positionRepository.savePosition(contactPersonPosition);

		return savePosition.map(this::mapToDto);
	}

	private ContactPersonPositionDto mapToDto(ContactPersonPosition reason){

		ContactPersonPositionDto contactPersonPositionDto = new ContactPersonPositionDto();

		contactPersonPositionDto.setPosition(reason.getPosition());
		contactPersonPositionDto.setDescription(reason.getDescription());

		return contactPersonPositionDto;
	}

	private ContactPersonPosition mapToDomain(ContactPersonPositionDto reason){

		if(reason.getDescription() == null){
			return new ContactPersonPosition(reason.getPosition());
		}

		return new ContactPersonPosition(reason.getPosition(), reason.getDescription());
	}

}
