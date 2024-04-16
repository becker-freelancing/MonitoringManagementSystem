package com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.contactperson.position.spi.ContactPersonPositionRepository;
import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter.SpringJpaReasonForContactRepository;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;

@Component
class ContactPersonPositionDao implements ContactPersonPositionRepository {

	private SpringJpaPositionRepository positionRepository;

	@Autowired
	public ContactPersonPositionDao(SpringJpaPositionRepository positionRepository) {

		this.positionRepository = positionRepository;
	}

	@Override
	public Optional<ContactPersonPosition> deleteByPosition(String position) {

		Optional<JpaContactPersonPosition> jpaPosition = positionRepository.findById(position);

		positionRepository.deleteById(position);

		return jpaPosition.map(this::mapToDomain);
	}

	@Override
	public List<ContactPersonPosition> findAll() {

		List<JpaContactPersonPosition> all = new ArrayList<>();

		positionRepository.findAll().forEach(all::add);

		return all.stream().map(this::mapToDomain).toList();
	}

	@Override
	public Optional<ContactPersonPosition> findByPosition(String position) {

		Optional<JpaContactPersonPosition> reasonForContact = positionRepository.findById(position);

		return reasonForContact.map(this::mapToDomain);
	}

	@Override
	public Optional<ContactPersonPosition> savePosition(ContactPersonPosition contactPersonPosition) {

		JpaContactPersonPosition jpaReasonForContact = mapToEntity(contactPersonPosition);

		JpaContactPersonPosition saved = positionRepository.save(jpaReasonForContact);

		return Optional.of(saved).map(this::mapToDomain);
	}

	private ContactPersonPosition mapToDomain(JpaContactPersonPosition entity){

		return new ContactPersonPosition(entity.getPosition(), entity.getDescription());
	}

	private JpaContactPersonPosition mapToEntity(ContactPersonPosition domain){

		JpaContactPersonPosition entity = new JpaContactPersonPosition();

		entity.setPosition(domain.getPosition());
		entity.setDescription(domain.getDescription());

		return entity;
	}

}
