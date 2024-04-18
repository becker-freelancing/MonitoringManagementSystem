package com.jabasoft.mms.junit.beans;

import java.util.Arrays;
import java.util.Objects;

class BeanWithEquals extends Bean {


	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Bean bean = (Bean) o;
		return Objects.equals(getName(), bean.getName())
			&& Objects.equals(getIntValue(), bean.getIntValue())
			&& Objects.equals(getLongValue(), bean.getLongValue())
			&& Objects.equals(getFloatValue(), bean.getFloatValue())
			&& Objects.equals(getDoubleValue(), bean.getDoubleValue())
			&& Objects.equals(getBigIntegerValue(), bean.getBigIntegerValue())
			&& Objects.equals(getBigDecimalValue(), bean.getBigDecimalValue())
			&& Objects.equals(getLocalDate(), bean.getLocalDate())
			&& Objects.equals(getLocalTime(), bean.getLocalTime())
			&& Objects.equals(getLocalDateTime(), bean.getLocalDateTime())
			&& getValuesEnum() == bean.getValuesEnum()
			&& getEnumWithOneValue() == bean.getEnumWithOneValue()
			&& Objects.equals(getBean2(), bean.getBean2())
			&& Objects.equals(getaBoolean(), bean.getaBoolean())
			&& Objects.equals(getBean3(), bean.getBean3())
			&& Arrays.equals(getBytes(), bean.getBytes())
			&& Objects.equals(getBean2s(), bean.getBean2s());
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(
			getName(),
			getIntValue(),
			getLongValue(),
			getFloatValue(),
			getDoubleValue(),
			getBigIntegerValue(),
			getBigDecimalValue(),
			getLocalDate(),
			getLocalTime(),
			getLocalDateTime(),
			getValuesEnum(),
			getEnumWithOneValue(),
			getBean2(),
			getaBoolean(),
			getBean3(),
			getBean2s());
		result = 31 * result + Arrays.hashCode(getBytes());
		return result;
	}

}
