package com.jabasoft.mms.customermanagement.customer.contactperson.reasonforcontact.adapter;

import java.math.BigInteger;
import java.util.Map;

import com.jabasoft.mms.commons.jpa.MapConverter;
import com.jabasoft.mms.customermanagement.domain.model.ReasonForContact;

public class ReasonForContactConverter extends MapConverter<ReasonForContact, BigInteger> {

	@Override
	protected void initMappings(Map<ReasonForContact, BigInteger> entityToDatabaseMappings) {
		entityToDatabaseMappings.put(ReasonForContact.TEAM_LEADER, BigInteger.valueOf(0));
		entityToDatabaseMappings.put(ReasonForContact.MARKETING, BigInteger.valueOf(1));
		entityToDatabaseMappings.put(ReasonForContact.HUMAN_RESOURCES, BigInteger.valueOf(2));
	}

}
