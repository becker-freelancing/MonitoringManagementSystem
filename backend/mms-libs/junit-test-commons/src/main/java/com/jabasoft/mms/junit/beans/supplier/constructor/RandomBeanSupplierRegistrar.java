package com.jabasoft.mms.junit.beans.supplier.constructor;

import java.lang.reflect.Constructor;
import java.util.List;

public class RandomBeanSupplierRegistrar<B> {

	private Class<B> beanClass;
	private List<Constructor<?>> constructors;

	public RandomBeanSupplierRegistrar(Class<B> beanClass) {

		this.beanClass = beanClass;

		createDefaults();
	}

	private void createDefaults() {

		constructors = List.of(beanClass.getDeclaredConstructors());
	}

	public void register(){

		RandomBeanSupplier<B> randomBeanSupplier = new RandomBeanSupplier<>(constructors);

		RandomConstructorBeanSupplierRegistry.registerRandomBeanSupplier(beanClass, randomBeanSupplier);
	}

	public RandomBeanSupplierRegistrar<B> withConstructors(List<Constructor<?>> constructors){
		this.constructors = constructors;
		return this;
	}

}
