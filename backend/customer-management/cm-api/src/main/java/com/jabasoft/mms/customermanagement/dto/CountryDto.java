package com.jabasoft.mms.customermanagement.dto;

public enum CountryDto {

	GERMANY("Deutschland"),
	USA("USA"),
	SPAIN("Spanien"),
	SWISS("Schweiz");

	private String  countryName;

	CountryDto(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryName() {

		return countryName;
	}

	public static CountryDto fromCountryName(String countryName){

		for (CountryDto value : CountryDto.values()) {
			if(value.countryName.equals(countryName)){
				return value;
			}
		}
		return null;
	}
}
