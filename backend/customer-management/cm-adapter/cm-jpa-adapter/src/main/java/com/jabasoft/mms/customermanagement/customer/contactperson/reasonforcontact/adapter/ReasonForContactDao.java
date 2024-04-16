package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.spi.ReasonForContactRepository;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;

@Component
class ReasonForContactDao implements ReasonForContactRepository {

	private SpringJpaReasonForContactRepository reasonForContactRepository;

	@Autowired
	public ReasonForContactDao(SpringJpaReasonForContactRepository reasonForContactRepository) {

		this.reasonForContactRepository = reasonForContactRepository;
	}

	@Override
	public Optional<ReasonForContact> deleteByReason(String reason) {

		Optional<JpaReasonForContact> reasonForContact = reasonForContactRepository.findById(reason);
		reasonForContactRepository.deleteById(reason);

		return reasonForContact.map(this::mapToDomain);
	}

	@Override
	public List<ReasonForContact> findAll() {

		List<JpaReasonForContact> all = new ArrayList<>();

		reasonForContactRepository.findAll().forEach(all::add);

		return all.stream().map(this::mapToDomain).toList();
	}

	@Override
	public Optional<ReasonForContact> findByReason(String reason) {

		Optional<JpaReasonForContact> reasonForContact = reasonForContactRepository.findById(reason);

		return reasonForContact.map(this::mapToDomain);
	}

	@Override
	public Optional<ReasonForContact> saveReason(ReasonForContact reasonForContact) {

		JpaReasonForContact jpaReasonForContact = mapToEntity(reasonForContact);

		JpaReasonForContact saved = reasonForContactRepository.save(jpaReasonForContact);

		return Optional.of(saved).map(this::mapToDomain);
	}

	private ReasonForContact mapToDomain(JpaReasonForContact entity){

		return new ReasonForContact(entity.getReason(), entity.getDescription());
	}

	private JpaReasonForContact mapToEntity(ReasonForContact domain){

		JpaReasonForContact entity = new JpaReasonForContact();

		entity.setReason(domain.getReason());
		entity.setDescription(domain.getDescription());

		return entity;
	}

}
