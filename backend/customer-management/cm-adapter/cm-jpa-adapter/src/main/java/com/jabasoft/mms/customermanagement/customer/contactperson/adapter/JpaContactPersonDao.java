package com.jabasoft.mms.customermanagement.customer.contactperson.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.contactperson.emails.adapter.SpringJpaEmailRepository;
import com.jabasoft.mms.customermanagement.customer.contactperson.phonenumber.adapter.SpringJpaPhoneNumberRepository;
import com.jabasoft.mms.customermanagement.customer.contactperson.position.adapter.SpringJpaPositionRepository;
import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter.SpringJpaReasonForContactRepository;
import com.jabasoft.mms.customermanagement.domain.model.ContactPerson;

@Component
public class JpaContactPersonDao {

	private SpringJpaContactPersonRepository contactPersonRepository;
	private SpringJpaEmailRepository emailRepository;
	private SpringJpaPhoneNumberRepository phoneNumberRepository;
	private SpringJpaPositionRepository positionRepository;
	private SpringJpaReasonForContactRepository reasonForContactRepository;

	@Autowired
	public JpaContactPersonDao(
		SpringJpaContactPersonRepository contactPersonRepository,
		SpringJpaEmailRepository emailRepository,
		SpringJpaPhoneNumberRepository phoneNumberRepository,
		SpringJpaPositionRepository positionRepository,
		SpringJpaReasonForContactRepository reasonForContactRepository) {

		this.contactPersonRepository = contactPersonRepository;
		this.emailRepository = emailRepository;
		this.phoneNumberRepository = phoneNumberRepository;
		this.positionRepository = positionRepository;
		this.reasonForContactRepository = reasonForContactRepository;
	}

	public void save(JpaContactPerson contactPerson){
		reasonForContactRepository.saveAll(contactPerson.getReasonForContacts());
		if (contactPerson.getPosition() != null) {
			positionRepository.save(contactPerson.getPosition());
		}
		contactPersonRepository.save(contactPerson);
	}

	public void saveAll(List<JpaContactPerson> contactPersons) {

		contactPersons.forEach(this::save);
	}


}
