package com.jabasoft.mms.customermanagement.test.adapter;

import java.math.BigInteger;
import java.util.Map;

import com.jabasoft.mms.commons.jpa.MapConverter;
import com.jabasoft.mms.customermanagement.domain.model.Country;

public class CountryConverter2 extends MapConverter<Country, BigInteger> {

	@Override
	protected void initMappings(Map<Country, BigInteger> entityToDatabaseMappings) {
		entityToDatabaseMappings.put(Country.GERMANY, BigInteger.valueOf(0));
		entityToDatabaseMappings.put(Country.SWISS, BigInteger.valueOf(1));
		entityToDatabaseMappings.put(Country.SPAIN, BigInteger.valueOf(2));
		entityToDatabaseMappings.put(Country.USA, BigInteger.valueOf(3));
	}

}
