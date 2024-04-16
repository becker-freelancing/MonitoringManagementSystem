package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.spi;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;

public interface ReasonForContactRepository {

	public Optional<ReasonForContact> deleteByReason(String reason);
	public List<ReasonForContact> findAll();
	public Optional<ReasonForContact> findByReason(String reason);
	public Optional<ReasonForContact> saveReason(ReasonForContact reasonForContact);

}
