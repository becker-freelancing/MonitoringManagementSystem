package com.jabasoft.mms.junit.beans;

import java.util.Arrays;

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
			", bean2=" + getBean2() +
			", aBoolean=" + getaBoolean() +
			", bean3=" + getBean3() +
			", bytes=" + getBytes() +
			", bean2s=" + getBean2s() +
			'}';
	}

}
