package com.jabasoft.mms.commons.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MapConverterTest {

	private MapConverter<String, Integer> mapConverter;

	@BeforeEach
	void setUp() {

		mapConverter = new MapConverter<>() {

			@Override
			protected void initMappings(Map<String, Integer> entityToDatabaseMappings) {

				entityToDatabaseMappings.put("1", 1);
				entityToDatabaseMappings.put("2", 2);
			}
		};
	}

	@Test
	void ambiguousToDatabaseMapping() {

		mapConverter = new MapConverter<>() {

			@Override
			protected void initMappings(Map<String, Integer> entityToDatabaseMappings) {

				entityToDatabaseMappings.put("1", 1);
				entityToDatabaseMappings.put("2", 1);
			}
		};

		assertThrows(IllegalStateException.class, () -> mapConverter.convertToEntityAttribute(1));
	}

	@Test
	void ambiguousToEntityMapping() {

		mapConverter = new MapConverter<>() {

			@Override
			protected void initMappings(Map<String, Integer> entityToDatabaseMappings) {

				entityToDatabaseMappings.put("1", 1);
				entityToDatabaseMappings.put("1", 2);
			}
		};

		assertThrows(IllegalStateException.class, () -> mapConverter.convertToDatabaseColumn("1"));
	}

	@Test
	void convertToDatabaseColumn() {

		assertNull(mapConverter.convertToDatabaseColumn(null));
		assertEquals(1, mapConverter.convertToDatabaseColumn("1"));
		assertEquals(2, mapConverter.convertToDatabaseColumn("2"));

		assertThrows(IllegalArgumentException.class, () -> mapConverter.convertToDatabaseColumn("3"));
	}

	@Test
	void convertToEntityAttribute() {

		assertNull(mapConverter.convertToEntityAttribute(null));
		assertEquals("1", mapConverter.convertToEntityAttribute(1));
		assertEquals("2", mapConverter.convertToEntityAttribute(2));

		assertThrows(IllegalArgumentException.class, () -> mapConverter.convertToEntityAttribute(3));
	}
}