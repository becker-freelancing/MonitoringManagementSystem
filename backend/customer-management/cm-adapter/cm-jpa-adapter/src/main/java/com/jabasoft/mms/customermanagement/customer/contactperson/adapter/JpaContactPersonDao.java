package com.jabasoft.mms.customermanagement.customer.contactperson.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter.SpringJpaPositionRepository;
import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter.SpringJpaReasonForContactRepository;

@Component
public class JpaContactPersonDao {

	private SpringJpaContactPersonRepository contactPersonRepository;
	private SpringJpaPositionRepository positionRepository;
	private SpringJpaReasonForContactRepository reasonForContactRepository;

	@Autowired
	public JpaContactPersonDao(
		SpringJpaContactPersonRepository contactPersonRepository,
		SpringJpaPositionRepository positionRepository,
		SpringJpaReasonForContactRepository reasonForContactRepository) {

		this.contactPersonRepository = contactPersonRepository;
		this.positionRepository = positionRepository;
		this.reasonForContactRepository = reasonForContactRepository;
	}

	public void save(JpaContactPerson contactPerson){
		if(contactPerson.getReasonForContacts() != null && contactPerson.getReasonForContacts().getReason() != null) {
			reasonForContactRepository.save(contactPerson.getReasonForContacts());
		} else {
			contactPerson.setReasonForContact(null);
		}
		if (contactPerson.getPosition() != null && contactPerson.getPosition().getPosition() != null) {
			positionRepository.save(contactPerson.getPosition());
		} else {
			contactPerson.setPosition(null);
		}
		contactPersonRepository.save(contactPerson);
	}

	public void saveAll(List<JpaContactPerson> contactPersons) {

		contactPersons.forEach(this::save);
	}


}
