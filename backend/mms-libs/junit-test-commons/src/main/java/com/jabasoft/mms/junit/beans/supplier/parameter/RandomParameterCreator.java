package com.jabasoft.mms.junit.beans.supplier.parameter;

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

import com.jabasoft.mms.junit.beans.supplier.constructor.RandomBeanCreator;

public class RandomParameterCreator{

	private Random random = new SecureRandom();

	public List<? extends Object> buildParameter(Class<?> paramClass){

		if (paramClass.equals(String.class)){
			return generateString();
		} else if(paramClass.equals(LocalDateTime.class)){
			return generateDateTime();
		} else if(paramClass.equals(byte[].class)){
			return generateByte();
		} else if(Number.class.isAssignableFrom(paramClass) || paramClass.equals(int.class) || paramClass.equals(double.class) || paramClass.equals(float.class) || paramClass.equals(long.class)){
			return generateNumber(paramClass);
		} else if(paramClass.equals(LocalTime.class)){
			return generateTime();
		} else if(paramClass.equals(LocalDate.class)){
			return generateDate();
		} else if(paramClass.equals(Boolean.class) || paramClass.equals(boolean.class)){
			return generateBoolean();
		} else if(paramClass.isEnum()){
			return generateEnum(paramClass);
		} else {
			return new RandomBeanCreator<>(){

				@Override
				protected Class<?> getBeanClass() {

					return paramClass;
				}
			}.createBeans().toList();
		}
	}

	protected List<LocalDateTime> generateDateTime() {

		List<LocalDateTime> times = new ArrayList<>();

		int year = random.nextInt(1900, 2023);
		int month = random.nextInt(1, 12);
		int dayOfMonth = random.nextInt(1, 28);
		LocalDate localDate = LocalDate.of(year, month, dayOfMonth);

		int hour = random.nextInt(0, 23);
		int minute = random.nextInt(0, 59);
		int second = random.nextInt(0, 59);
		int nanoOfSecond = random.nextInt(0, 999_999);
		LocalTime localTime = LocalTime.of(hour, minute, second, nanoOfSecond);

		times.add(LocalDateTime.of(localDate, localTime));
		times.add(null);
//		times.add(LocalDateTime.MAX);
//		times.add(LocalDateTime.MIN);

		return times;
	}

	protected List<byte[]> generateByte() {

		List<byte[]> bytes = new ArrayList<>();

		bytes.add(new byte[new Random().nextInt(1, 100)]);
		bytes.add(null);
		bytes.add(new byte[0]);

		return bytes;
	}

	protected List<Number> generateNumber(Class<?> numberType) {

		List<Number> numbers = new ArrayList<>();

		if (Integer.class.equals(numberType) || numberType.equals(int.class)) {
			numbers.add(random.nextInt());
//			numbers.add(0);
//			numbers.add(-1);
		} else if (Long.class.equals(numberType) || numberType.equals(long.class)) {
			numbers.add(random.nextLong());
//			numbers.add(0L);
//			numbers.add(-1L);
		} else if (Double.class.equals(numberType) || numberType.equals(double.class)) {
			numbers.add(random.nextDouble());
//			numbers.add(0.d);
//			numbers.add(-1.d);
		} else if (Float.class.equals(numberType) || numberType.equals(float.class)) {
			numbers.add(random.nextFloat());
//			numbers.add(0.f);
//			numbers.add(-1.f);
		} else if (BigDecimal.class.equals(numberType)) {
			numbers.add(BigDecimal.valueOf(random.nextDouble()));
//			numbers.add(BigDecimal.ZERO);
//			numbers.add(BigDecimal.valueOf(-1));
		} else if (BigInteger.class.equals(numberType)) {
			numbers.add(BigInteger.valueOf(random.nextInt()));
//			numbers.add(BigInteger.ZERO);
//			numbers.add(BigInteger.valueOf(-1));
		} else {
			throw new UnsupportedOperationException("Number " + numberType + " not implemented yet.");
		}

		return numbers;
	}

	protected List<LocalTime> generateTime() {

		List<LocalTime> times = new ArrayList<>();

		int hour = random.nextInt(0, 23);
		int minute = random.nextInt(0, 59);
		int second = random.nextInt(0, 59);
		int nanoOfSecond = random.nextInt(0, 999_999);
		LocalTime localTime = LocalTime.of(hour, minute, second, nanoOfSecond);

		times.add(localTime);
//		times.add(LocalTime.MIN);
//		times.add(LocalTime.MAX);

		return times;
	}

	protected List<LocalDate> generateDate() {

		List<LocalDate> times = new ArrayList<>();

		int year = random.nextInt(1900, 2023);
		int month = random.nextInt(1, 12);
		int dayOfMonth = random.nextInt(1, 28);
		LocalDate localDate = LocalDate.of(year, month, dayOfMonth);

		times.add(localDate);
//		times.add(LocalDate.MIN);
//		times.add(LocalDate.MAX);

		return times;
	}

	protected List<String> generateString() {

		List<String> strings = new ArrayList<>();

		strings.add(UUID.randomUUID().toString());
//		strings.add(null);
//		strings.add("");

		return strings;
	}

	protected List<Boolean> generateBoolean() {

		return List.of(true, false);
	}

	protected List<? extends Enum<?>> generateEnum(Class<?> enumType) {

		Object[] enumConstants = enumType.getEnumConstants();
		List<Object> enumConstantsList = Arrays.asList(enumConstants);
		Collections.shuffle(enumConstantsList);
		return enumConstantsList.stream().map(o -> (Enum<?>) o).toList();
	}
	
}
