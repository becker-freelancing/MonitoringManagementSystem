package com.jabasoft.mms.junit.beans.supplier.constructor;

import java.util.HashMap;
import java.util.Map;

public class RandomConstructorBeanSupplierRegistry {

	private static Map<Class<?>, RandomBeanSupplier<?>> registeredSupplier = new HashMap<>();

	protected static <B> void registerRandomBeanSupplier(Class<B> beanClass, RandomBeanSupplier<B> supplier){
		registeredSupplier.put(beanClass, supplier);
	}

	public static void clear(){
		registeredSupplier.clear();
	}

	public static <B> RandomBeanSupplier<B> getRandomBeanSupplier(Class<B> beanClass){

		if(!registeredSupplier.containsKey(beanClass)){
			new RandomBeanSupplierRegistrar<B>(beanClass).register();
		}

		return (RandomBeanSupplier<B>) registeredSupplier.get(beanClass);
	}

}
