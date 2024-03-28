package com.jabasoft.mms.junit.beans;

public interface ValueGeneratorRegistry {

	public ValueGenerator getValueGenerator(Class<?> type);
}
