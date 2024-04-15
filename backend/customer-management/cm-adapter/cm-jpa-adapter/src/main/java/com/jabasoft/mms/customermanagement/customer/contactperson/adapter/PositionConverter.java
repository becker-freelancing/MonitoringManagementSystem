package com.jabasoft.mms.customermanagement.customer.contactperson.adapter;

import java.math.BigInteger;
import java.util.Map;

import com.jabasoft.mms.commons.jpa.MapConverter;
import com.jabasoft.mms.customermanagement.domain.model.ContactPersonPosition;

public class PositionConverter extends MapConverter<ContactPersonPosition, BigInteger> {

	@Override
	protected void initMappings(Map<ContactPersonPosition, BigInteger> entityToDatabaseMappings) {
		entityToDatabaseMappings.put(ContactPersonPosition.CEO, BigInteger.valueOf(0));
		entityToDatabaseMappings.put(ContactPersonPosition.EMPLOYEE, BigInteger.valueOf(1));
		entityToDatabaseMappings.put(ContactPersonPosition.TEAM_LEADER, BigInteger.valueOf(2));
	}

}
