package com.jabasoft.mms.customermanagement.contactperson.reasonforcontact.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.api.ReasonForContactPort;
import com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.spi.ReasonForContactRepository;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;
import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;

@Component
class ReasonForContactInteractor implements ReasonForContactPort {

	private ReasonForContactRepository reasonForContactRepository;

	@Autowired
	public ReasonForContactInteractor(ReasonForContactRepository reasonForContactRepository) {

		this.reasonForContactRepository = reasonForContactRepository;
	}

	@Override
	public List<ReasonForContactDto> getReasons() {

		List<ReasonForContact> reasons = reasonForContactRepository.findAll();

		return reasons.stream().map(this::mapToDto).collect(Collectors.toList());
	}

	@Override
	public Optional<ReasonForContactDto> getReason(String reason) {

		Optional<ReasonForContact> foundReason = reasonForContactRepository.findByReason(reason);

		return foundReason.map(this::mapToDto);
	}

	@Override
	public Optional<ReasonForContactDto> deleteReason(String reason) {

		Optional<ReasonForContact> deletedReason = reasonForContactRepository.deleteByReason(reason);

		return deletedReason.map(this::mapToDto);
	}

	@Override
	public Optional<ReasonForContactDto> saveReason(ReasonForContactDto reason) {

		ReasonForContact reasonForContact = mapToDomain(reason);

		Optional<ReasonForContact> savedReason = reasonForContactRepository.saveReason(reasonForContact);

		return savedReason.map(this::mapToDto);
	}

	private ReasonForContactDto mapToDto(ReasonForContact reason) {

		ReasonForContactDto reasonForContactDto = new ReasonForContactDto();

		reasonForContactDto.setReason(reason.getReason());
		reasonForContactDto.setDescription(reason.getDescription());

		return reasonForContactDto;
	}

	private ReasonForContact mapToDomain(ReasonForContactDto reason) {

		if (reason.getDescription() == null) {
			return new ReasonForContact(reason.getReason());
		}

		return new ReasonForContact(reason.getReason(), reason.getDescription());
	}

}
