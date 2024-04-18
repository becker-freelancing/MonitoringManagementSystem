package com.jabasoft.mms.junit.beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Bean {

	private String name;
	private Integer intValue;
	private Long longValue;
	private Float floatValue;
	private Double doubleValue;
	private BigInteger bigIntegerValue;
	private BigDecimal bigDecimalValue;
	private LocalDate localDate;
	private LocalTime localTime;
	private LocalDateTime localDateTime;
	private ValuesEnum valuesEnum;
	private EnumWithOneValue enumWithOneValue;
	private Bean2 bean2;
	private Boolean aBoolean;
	private Bean3 bean3;
	private byte[] bytes;
	private List<Bean2> bean2s;


	public List<Bean2> getBean2s() {

		return bean2s;
	}

	public void setBean2s(List<Bean2> bean2s) {

		this.bean2s = bean2s;
	}

	public byte[] getBytes() {

		return bytes;
	}

	public void setBytes(byte[] bytes) {

		this.bytes = bytes;
	}

	public Bean3 getBean3() {

		return bean3;
	}

	public void setBean3(Bean3 bean3) {

		this.bean3 = bean3;
	}

	public Boolean getaBoolean() {

		return aBoolean;
	}

	public void setaBoolean(Boolean aBoolean) {

		this.aBoolean = aBoolean;
	}

	public Bean2 getBean2() {

		return bean2;
	}

	public void setBean2(Bean2 bean2) {

		this.bean2 = bean2;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Integer getIntValue() {

		return intValue;
	}

	public void setIntValue(Integer intValue) {

		this.intValue = intValue;
	}

	public Long getLongValue() {

		return longValue;
	}

	public void setLongValue(Long longValue) {

		this.longValue = longValue;
	}

	public Float getFloatValue() {

		return floatValue;
	}

	public void setFloatValue(Float floatValue) {

		this.floatValue = floatValue;
	}

	public Double getDoubleValue() {

		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {

		this.doubleValue = doubleValue;
	}

	public BigInteger getBigIntegerValue() {

		return bigIntegerValue;
	}

	public void setBigIntegerValue(BigInteger bigIntegerValue) {

		this.bigIntegerValue = bigIntegerValue;
	}

	public BigDecimal getBigDecimalValue() {

		return bigDecimalValue;
	}

	public void setBigDecimalValue(BigDecimal bigDecimalValue) {

		this.bigDecimalValue = bigDecimalValue;
	}

	public LocalDate getLocalDate() {

		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {

		this.localDate = localDate;
	}

	public LocalTime getLocalTime() {

		return localTime;
	}

	public void setLocalTime(LocalTime localTime) {

		this.localTime = localTime;
	}

	public LocalDateTime getLocalDateTime() {

		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {

		this.localDateTime = localDateTime;
	}

	public ValuesEnum getValuesEnum() {

		return valuesEnum;
	}

	public void setValuesEnum(ValuesEnum valuesEnum) {

		this.valuesEnum = valuesEnum;
	}

	public EnumWithOneValue getEnumWithOneValue() {

		return enumWithOneValue;
	}

	public void setEnumWithOneValue(EnumWithOneValue enumWithOneValue) {

		this.enumWithOneValue = enumWithOneValue;
	}
}
