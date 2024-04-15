package com.jabasoft.mms.junit.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.jabasoft.mms.commons.jpa.MapConverter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractEnumConverterTest<E extends Enum<E>, D> {

	protected MapConverter<E, D> converter;

	@BeforeAll
	void setUp() {

		converter = createConverter();
	}

	protected abstract MapConverter<E, D> createConverter();

	Stream<E> enums() {

		return Arrays.stream(getEnumType().getEnumConstants());
	}

	/**
	 * Testet, dass alle Enum-Werte abgebildet sind und keiner vergessen wurde. Diese Methode kann nicht den Test
	 * ersetzen, welchen testet, ob die Enum-Werte richtig abgebildet werden.
	 *
	 * @param anEnum
	 */
	@ParameterizedTest
	@MethodSource("enums")
	void enumMapped(E anEnum) {

		D mappedObject = converter.convertToDatabaseColumn(anEnum);

		assertNotNull(mappedObject, anEnum.name());

		E mappedEnum = converter.convertToEntityAttribute(mappedObject);
		assertEquals(anEnum, mappedEnum, () -> this.createErrorMessage(anEnum, mappedObject));
	}

	@Test
	void expectedEnums() {

		List<Enum<E>> expectedEnumValues = getCheckedExpectedEnumValues();

		Class<E> enumType = getEnumType();
		List<E> actualEnumValues = Arrays.asList(enumType.getEnumConstants());

		List<E> unexpectedEnumValues = new ArrayList<>(actualEnumValues);
		unexpectedEnumValues.removeAll(expectedEnumValues);

		assertFalse(unexpectedEnumValues.size() > 0, () -> {
			StringBuilder sb = new StringBuilder();

			sb.append("Unexpected enum values.\n");
			sb.append(getEnumType().getSimpleName());
			sb.append(" declares enum values:\n");
			sb.append("\t");
			sb.append(unexpectedEnumValues);
			sb.append("\n");
			sb.append("that are not contained in the list of expected values:\n");
			sb.append("\t");
			sb.append(expectedEnumValues);

			return sb.toString();
		});
	}

	@Test
	void expectedMappingValues() {

		MappingCompairPair<E, D>[] expectedMappingValues = getExpectedMappingValues();

		for (MappingCompairPair<E, D> mappingValue : expectedMappingValues) {
			E enumValue = mappingValue.getEnumValue();
			D expectedMappingValue = mappingValue.getMappingValue();

			D actualMappingValue = converter.convertToDatabaseColumn(enumValue);

			assertEquals(expectedMappingValue, actualMappingValue, "Mapping of " + enumValue);
		}
	}

	protected abstract MappingCompairPair<E, D>[] getExpectedMappingValues();

	@Test
	void mappingEnumValueIsUnique() {

		MappingCompairPair<E, D>[] expectedMappingValues = getExpectedMappingValues();

		Set<E> alreadyContainedEnumValues = new HashSet<>();

		for (MappingCompairPair<E, D> expectedMappingValue : expectedMappingValues) {
			E enumValue = expectedMappingValue.getEnumValue();

			assertTrue(!alreadyContainedEnumValues.contains(enumValue), "Enum Value " + enumValue + " is unique");

			alreadyContainedEnumValues.add(enumValue);
		}
	}

	private List<Enum<E>> getCheckedExpectedEnumValues() {

		List<Enum<E>> expectedEnumValues = Arrays.asList(getExpectedEnumValues());
		Set<Enum<E>> duplicateExpectedEnumValues = new LinkedHashSet<>();

		for (Enum<E> expectedEnumValue : expectedEnumValues) {
			if (Collections.frequency(expectedEnumValues, expectedEnumValue) > 1) {
				duplicateExpectedEnumValues.add(expectedEnumValue);
			}
		}

		assertTrue(duplicateExpectedEnumValues.isEmpty(), () -> {
			StringBuilder sb = new StringBuilder("Expected enum values should not contains duplicate entries.\n");

			sb.append("But there are duplicate entries:\n");

			for (Enum<E> duplicateExpectedEnumValue : duplicateExpectedEnumValues) {
				sb.append("\t");
				sb.append(duplicateExpectedEnumValue);
				sb.append("\n");
			}

			return sb.toString();
		});
		return expectedEnumValues;
	}

	private String createErrorMessage(E anEnum, D enumValue) {

		StringBuilder sb = new StringBuilder();

		sb.append("Missing enum mapping: ");
		sb.append(enumRef(anEnum));

		if (converter instanceof MapConverter) {
			sb.append("\n\t");
			sb.append(anEnum.getClass().getSimpleName());
			sb.append(" extends ");
			sb.append(MapConverter.class);
			sb.append(": Consider adding:");
			sb.append("\n\t\t");
			sb.append("entityToDatabaseMappings.put(");
			sb.append(enumRef(anEnum));
			sb.append(", ");
			sb.append(enumValue == null ? "?" : String.valueOf(enumValue));
			sb.append(");");
		}
		return sb.toString();
	}

	private CharSequence enumRef(E anEnum) {

		StringBuilder sb = new StringBuilder();

		sb.append(anEnum.getClass().getSimpleName());
		sb.append('.');
		sb.append(anEnum.name());

		return sb;
	}

	protected abstract Enum<E>[] getExpectedEnumValues();

	protected Class<E> getEnumType() {

		Type genericSuperclass = getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType parameterizedType) {
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			Type enumTypeArg = actualTypeArguments[0];
			if (enumTypeArg instanceof Class<?> enumType) {
				return (Class<E>) enumType;
			}
		}
		throw new IllegalStateException("Unable to reflectively resolve enum type. Maybe you need to override this method.");
	}

	@Test
	void mappingValueIsUnique() {

		MappingCompairPair<E, D>[] expectedMappingValues = getExpectedMappingValues();

		Set<D> alreadyContainedMappingValues = new HashSet<>();

		for (MappingCompairPair<E, D> expectedMappingValue : expectedMappingValues) {
			D mappingValue = expectedMappingValue.getMappingValue();

			assertTrue(!alreadyContainedMappingValues.contains(mappingValue), "Mapping Value " + mappingValue + " is unique");

			alreadyContainedMappingValues.add(mappingValue);
		}
	}

	public static class MappingCompairPair<E, D> {

		private E enumValue;
		private D mappingValue;

		public MappingCompairPair(E enumValue, D mappingValue) {

			this.enumValue = enumValue;
			this.mappingValue = mappingValue;
		}

		public static <E, D> MappingCompairPair<E, D> of(E enumValue, D mappingValue) {

			return new MappingCompairPair<>(enumValue, mappingValue);
		}

		public E getEnumValue() {

			return enumValue;
		}

		public D getMappingValue() {

			return mappingValue;
		}
	}
}
