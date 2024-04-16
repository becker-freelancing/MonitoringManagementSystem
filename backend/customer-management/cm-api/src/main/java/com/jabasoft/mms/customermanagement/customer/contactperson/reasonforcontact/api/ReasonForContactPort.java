package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.api;

import java.util.List;
import java.util.Optional;

import com.jabasoft.mms.customermanagement.dto.ReasonForContactDto;

public interface ReasonForContactPort {

	public List<ReasonForContactDto> getReasons();
	public Optional<ReasonForContactDto> getReason(String reason);
	public Optional<ReasonForContactDto> deleteReason(String reason);
	public Optional<ReasonForContactDto> saveReason(ReasonForContactDto reason);

}
