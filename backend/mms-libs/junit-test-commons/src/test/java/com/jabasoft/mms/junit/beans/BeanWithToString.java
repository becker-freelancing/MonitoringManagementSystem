package com.jabasoft.mms.junit.beans;

class BeanWithToString extends Bean {

	@Override
	public String toString() {

		return "BeanWithToString{" +
			"name='" + getName() + '\'' +
			", intValue=" + getIntValue() +
			", longValue=" + getLongValue() +
			", floatValue=" + getFloatValue() +
			", doubleValue=" + getDoubleValue() +
			", bigIntegerValue=" + getBigIntegerValue() +
			", bigDecimalValue=" + getBigDecimalValue() +
			", localDate=" + getLocalDate() +
			", localTime=" + getLocalTime() +
			", localDateTime=" + getLocalDateTime() +
			", valuesEnum=" + getValuesEnum() +
			", enumWithOneValue=" + getEnumWithOneValue() +
			'}';
	}
}
