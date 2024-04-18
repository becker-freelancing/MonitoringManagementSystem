package com.jabasoft.mms.junit.beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

class RandomParameterSupplier<B>{

	private Random random;
	
	private Class<B> beanClass;

	public RandomParameterSupplier(Class<B> beanClass) {

		this.beanClass = beanClass;
		this.random = new SecureRandom();
	}

	public Supplier<?> buildParameterSupplier(){

		if (beanClass.equals(String.class)){
			return generateString();
		} else if(beanClass.equals(LocalDateTime.class)){
			return generateDateTime();
		} else if(beanClass.equals(byte[].class)){
			return generateByte();
		} else if(Number.class.isAssignableFrom(beanClass) || beanClass.equals(int.class) || beanClass.equals(double.class) || beanClass.equals(float.class) || beanClass.equals(long.class)){
			return generateNumber(beanClass);
		} else if(beanClass.equals(LocalTime.class)){
			return generateTime();
		} else if(beanClass.equals(LocalDate.class)){
			return generateDate();
		} else if(beanClass.equals(Boolean.class) || beanClass.equals(boolean.class)){
			return generateBoolean();
		} else if(beanClass.isEnum()){
			return generateEnum(beanClass);
		} else {
			return RandomBeanSupplierRegistry.getRandomBeanSupplier(beanClass);
		}
	}

	protected Supplier<LocalDateTime> generateDateTime() {

		return LocalDateTime::now;
	}

	protected Supplier<byte[]> generateByte() {

		return () -> new byte[new Random().nextInt(1, 100)];
	}

	protected Supplier<Number> generateNumber(Class<?> numberType) {

		return () -> {
			if (Integer.class.equals(numberType) || numberType.equals(int.class)) {
				return random.nextInt();
			} else if (Long.class.equals(numberType) || numberType.equals(long.class)) {
				return random.nextLong();
			} else if (Double.class.equals(numberType) || numberType.equals(double.class)) {
				return random.nextDouble();
			} else if (Float.class.equals(numberType) || numberType.equals(float.class)) {
				return random.nextFloat();
			} else if (BigDecimal.class.equals(numberType)) {
				return BigDecimal.valueOf(random.nextDouble());
			} else if (BigInteger.class.equals(numberType)) {
				return BigInteger.valueOf(random.nextInt());
			} else {
				throw new UnsupportedOperationException("Number " + numberType + " not implemented yet.");
			}
		};
	}

	protected Supplier<LocalTime> generateTime() {

		return () -> {
			int hour = random.nextInt(0, 23);
			int minute = random.nextInt(0, 59);
			int second = random.nextInt(0, 59);
			int nanoOfSecond = random.nextInt(0, 999_999);
			return LocalTime.of(hour, minute, second, nanoOfSecond);
		};
	}

	protected Supplier<LocalDate> generateDate() {

		return () -> {
			int year = random.nextInt(1900, 2023);
			int month = random.nextInt(1, 12);
			int dayOfMonth = random.nextInt(1, 28);
			return LocalDate.of(year, month, dayOfMonth);
		};
	}

	protected Supplier<String> generateString() {

		return () -> UUID.randomUUID().toString();
	}

	protected Supplier<Boolean> generateBoolean() {

		return () -> random.nextBoolean();
	}

	protected Supplier<? extends Enum<?>> generateEnum(Class<?> enumType) {

		Object[] enumConstants = enumType.getEnumConstants();
		List<Object> enumConstantsList = Arrays.asList(enumConstants);
		return () -> (Enum<?> ) enumConstantsList.get(random.nextInt(enumConstantsList.size()));
	}
	
}
