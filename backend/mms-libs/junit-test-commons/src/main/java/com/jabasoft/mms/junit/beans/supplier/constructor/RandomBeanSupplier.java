package com.jabasoft.mms.junit.beans.supplier.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.jabasoft.mms.junit.beans.supplier.NotConstructableException;
import com.jabasoft.mms.junit.beans.supplier.parameter.RandomParameterSupplier;

public class RandomBeanSupplier<B> implements Supplier<B>{

	private List<Constructor<?>> constructors;

	protected RandomBeanSupplier(List<Constructor<?>> constructors) {

		this.constructors = constructors;
	}


	private List<Supplier<?>> createParamSupplierForConstructor(Constructor<?> constructor) {

		Parameter[] parameters = constructor.getParameters();
		List<Parameter> constructorParameter = List.of(parameters);

		if (constructorParameter.isEmpty()) {
			return List.of();
		}

		List<Supplier<?>> parameterSupplier = new ArrayList<>();

		for (Parameter parameter : constructorParameter) {
			if (parameter.getType().equals(List.class)) {
				String[] split = parameter.toString().split("[<>]");
				if (split.length == 3) {
					String className = split[1].trim();
					try {
						Class<?> genericClass = Class.forName(className);
						Supplier<?> singleGenericParameterSupplier = RandomConstructorBeanSupplierRegistry.getRandomBeanSupplier(genericClass);
						Supplier<List<?>> listSupplier = () -> List.of(singleGenericParameterSupplier.get());
						parameterSupplier.add(listSupplier);
					}
					catch (ClassNotFoundException e) {
						throw new NotConstructableException(e);
					}
				}
			} else {
				parameterSupplier.add(createParameterSupplier(parameter.getType()));
			}
		}

		return parameterSupplier;
	}

	private Supplier<?> createParameterSupplier(Class<?> genericClass) {

		return new RandomParameterSupplier(genericClass).buildParameterSupplier();
	}

	public List<B> createBeans(int limit){
		List<B> beans = new ArrayList<>();
		for(int i = 0; i < limit; i++){
			beans.add(get());
		}
		return beans;
	}

	@Override
	public B get() {

		Random random = new Random();
		Constructor<?> constructor = constructors.get(random.nextInt(constructors.size()));

		List<Supplier<?>> suppliers = createParamSupplierForConstructor(constructor);
		Object[] parameter = suppliers.stream().map(Supplier::get).toArray();

		try {
			return (B) constructor.newInstance(parameter);
		}
		catch (Exception e) {
			throw new NotConstructableException(e);
		}
	}

}
