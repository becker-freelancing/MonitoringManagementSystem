package com.jabasoft.mms.commons.jpa;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.AttributeConverter;

public abstract class MapConverter<E, D> implements AttributeConverter<E, D> {

	private Map<E, D> entityToDatabase;
	private Map<D, E> databaseToEntity;

	public Map<D, E> getDatabaseToEntity() {

		if (databaseToEntity == null) {
			initMappings();
		}
		return databaseToEntity;
	}

	private void initMappings() {

		Map<E, D> entityToDatabaseMappings = new HashMap<>() {

			@Override
			public D put(E key, D value) {

				D previousValue = super.put(key, value);
				if (previousValue != null) {
					String msg = "Ambiguous mapping defined: " + key + " => " + value;
					throw new IllegalStateException(msg);
				}
				return previousValue;
			}
		};

		initMappings(entityToDatabaseMappings);
		entityToDatabase = new HashMap<>(entityToDatabaseMappings);
		initDatabaseToEntityMapping(entityToDatabase);
	}

	private void initDatabaseToEntityMapping(Map<E, D> entityToDatabase) {

		databaseToEntity = new HashMap<>();

		for (Map.Entry<E, D> entityToDatabaseEntry : entityToDatabase.entrySet()) {
			E entity = entityToDatabaseEntry.getKey();
			D databaseObject = entityToDatabaseEntry.getValue();

			E previousEntry = databaseToEntity.put(databaseObject, entity);
			if (previousEntry != null) {
				String msg = "Ambiguous mapping defined: " + previousEntry + " = " + entity + " => " + databaseObject;
				throw new IllegalStateException(msg);
			}
		}
	}

	protected abstract void initMappings(Map<E, D> entityToDatabaseMappings);

	public Map<E, D> getEntityToDatabase() {

		if (entityToDatabase == null) {
			initMappings();
		}
		return entityToDatabase;
	}

	@Override
	public D convertToDatabaseColumn(E e) {

		if (e == null) {
			return null;
		}

		D databaseObject = getEntityToDatabase().get(e);
		if (databaseObject == null) {
			String msg = getClass().getSimpleName() + ". No mapping registered for entity: " + e;
			throw new IllegalArgumentException(msg);
		}
		return databaseObject;
	}

	@Override
	public E convertToEntityAttribute(D d) {

		if (d == null) {
			return null;
		}

		E entity = getDatabaseToEntity().get(d);
		if (entity == null) {
			String msg = getClass().getSimpleName() + ". No mapping registered for database object: " + d;
			throw new IllegalArgumentException(msg);
		}
		return entity;
	}
}
