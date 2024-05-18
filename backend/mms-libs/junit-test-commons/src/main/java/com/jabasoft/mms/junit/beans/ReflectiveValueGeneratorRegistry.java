package com.jabasoft.mms.junit.beans;

import static org.mockito.Mockito.mock;

import java.lang.reflect.Method;
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
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import com.jabasoft.mms.junit.beans.supplier.constructor.RandomConstructorBeanSupplierRegistry;

@SuppressWarnings({"java:S112"})
public class ReflectiveValueGeneratorRegistry implements ValueGeneratorRegistry {

	private class GeneratorMethod {

		private Method method;
		private final Object[] args;

		public GeneratorMethod(Method method, Object... args) {

			this.method = method;
			this.args = args;
		}

		public Object generate() throws Exception {

			return method.invoke(ReflectiveValueGeneratorRegistry.this, args);
		}
	}

	private List<Method> generatorMethods;
	private Random random = new SecureRandom();

	public List<Method> getGeneratorMethods() {

		if (generatorMethods == null) {
			List<Method> methods = new ArrayList<>();
			Class<?> thisType = getClass();
			while (!Object.class.equals(thisType)) {
				methods.addAll(getGeneratorMethods(thisType));
				thisType = thisType.getSuperclass();
			}
			generatorMethods = methods;
		}
		return generatorMethods;
	}

	private List<Method> getGeneratorMethods(Class<?> type) {

		return Arrays.stream(type.getDeclaredMethods()).filter(this::isGeneratorMethod).collect(Collectors.toList());
	}

	protected boolean isGeneratorMethod(Method method) {

		return method.getName().startsWith("generate");
	}

	@Override
	public ValueGenerator getValueGenerator(Class<?> type) {

		Optional<GeneratorMethod> generatorMethod = findGeneratorMethod(getGeneratorMethods(), type);

		return generatorMethod.map(this::wrapGeneratorMethod)
			.orElseThrow(() -> new IllegalStateException("No ValueGenerator available for " + type));
	}

	protected ValueGenerator wrapGeneratorMethod(GeneratorMethod generatorMethod) {

		return () -> {
			try {
				return generatorMethod.generate();
			}
			catch (Exception e) {
				throw new IllegalStateException(e);
			}
		};
	}

	protected Optional<GeneratorMethod> findGeneratorMethod(List<Method> generatorMethods, Class<?> type) {

		for (Method generatorMethod : generatorMethods) {
			if (generatorMethod.getReturnType().equals(type)) {
				return Optional.of(new GeneratorMethod(generatorMethod));
			}
		}

		Class<?> superclass = type.getSuperclass();
		for (Method generatorMethod : generatorMethods) {
			Class<?>[] parameterTypes = generatorMethod.getParameterTypes();
			Class<?> returnType = generatorMethod.getReturnType();
			if (returnType.equals(superclass) && parameterTypes.length == 1 && parameterTypes[0].equals(Class.class)) {
				return Optional.of(new GeneratorMethod(generatorMethod, type));
			}
		}

		for (Method generatorMethod : generatorMethods) {
			if(generatorMethod.getName().equals("generateComplexObject")){
				return Optional.of(new GeneratorMethod(generatorMethod, type));
			}
		}

		return Optional.empty();
	}

	protected LocalDateTime generateDateTime() {

		return LocalDateTime.of(generateDate(), generateTime());
	}

	protected byte[] generateByte() {

		return new byte[new Random().nextInt(1, 1000)];
	}

	protected Number generateNumber(Class<?> numberType) {

		if (Integer.class.equals(numberType)) {
			return random.nextInt();
		} else if (Long.class.equals(numberType)) {
			return random.nextLong();
		} else if (Double.class.equals(numberType)) {
			return random.nextDouble();
		} else if (Float.class.equals(numberType)) {
			return random.nextFloat();
		} else if (BigDecimal.class.equals(numberType)) {
			return BigDecimal.valueOf(random.nextDouble());
		} else if (BigInteger.class.equals(numberType)) {
			return new BigInteger(Integer.toString(random.nextInt()));
		}
		throw new UnsupportedOperationException("Number " + numberType + " not implemented yet.");
	}

	protected LocalTime generateTime() {

		int hour = random.nextInt(0, 23);
		int minute = random.nextInt(0, 59);
		int second = random.nextInt(0, 59);
		int nanoOfSecond = random.nextInt(0, 999_999);
		return LocalTime.of(hour, minute, second, nanoOfSecond);
	}

	protected LocalDate generateDate() {

		int year = random.nextInt(1900, 2023);
		int month = random.nextInt(1, 12);
		int dayOfMonth = random.nextInt(1, 28);
		return LocalDate.of(year, month, dayOfMonth);
	}

	protected String generateSting() {

		return UUID.randomUUID().toString();
	}

	protected Boolean generateBoolean() {

		return random.nextBoolean();
	}

	protected Enum<?> generateEnum(Class<?> enumType) {

		Object[] enumConstants = enumType.getEnumConstants();
		List<Object> enumConstantsList = Arrays.asList(enumConstants);
		Collections.shuffle(enumConstantsList);
		return (Enum<?>) enumConstantsList.get(0);
	}

	protected Object generateComplexObject(Class<?> objectClass){
		if(objectClass.isInterface()){
			return mock(objectClass);
		}
		return RandomConstructorBeanSupplierRegistry.getRandomBeanSupplier(objectClass).get();
	}
}
