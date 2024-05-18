package com.jabasoft.mms.junit.beans.supplier.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.supplier.NotConstructableException;
import com.jabasoft.mms.junit.beans.supplier.parameter.RandomParameterCreator;

public abstract class RandomBeanCreator<B> {

	protected abstract Class<? extends B> getBeanClass();

	public Stream<B> createBeans(){

		Class<? extends B> beanClass = getBeanClass();

		Constructor<?>[] declaredConstructor = beanClass.getDeclaredConstructors();
		List<B> beans = new ArrayList<>();
		for (Constructor<?> constructor : declaredConstructor) {
			List<B> beansForConstructor = createBeansForConstructor(constructor);
			beans.addAll(beansForConstructor);
		}

		return beans.stream();
	}

	private List<B> createBeansForConstructor(Constructor<?> constructor) {

		Parameter[] parameters = constructor.getParameters();
		List<Parameter> constructorParameter = List.of(parameters);

		if(constructorParameter.isEmpty()){
			try {
				Object instance = constructor.newInstance();
				return List.of((B) instance);
			}
			catch (Exception e) {
				throw new NotConstructableException(e);
			}
		}

		List<List<?>> parameterValues = new ArrayList<>();

		for (Parameter parameter : constructorParameter) {
			if(parameter.getType().equals(List.class)){
				String[] split = parameter.toString().split("[<>]");
				if(split.length == 3){
					String className = split[1].trim();
					try {
						Class<?> genericClass = Class.forName(className);
						List<?> singleGenericParameter = createParameters(genericClass);
						List<List<?>> genericParameter = new ArrayList<>();
						for (Object param : singleGenericParameter) {
							genericParameter.add(List.of(param));
						}
						parameterValues.add(genericParameter);
					}
					catch (ClassNotFoundException e) {
						throw new NotConstructableException(e);
					}
				}
			} else {
				parameterValues.add(createParameters(parameter.getType()));
			}
		}

		return createBeans(constructor, parameterValues);
	}

	private List<B> createBeans(Constructor<?> constructor, List<List<?>> parameterValues) {

		List<List<Object>> permutedParams = permute(parameterValues);

		List<B> beans = new ArrayList<>();

		for (List<Object> params : permutedParams) {
			try {
				Object instance = constructor.newInstance(params.toArray());
				beans.add((B) instance);
			}
			catch (Exception ignored) {
			}
		}

		return beans;
	}

	private List<List<Object>> permute(List<List<?>> lists) {
		List<List<Object>> result = new ArrayList<>();
		permuteHelper(lists, 0, new ArrayList<>(), result);
		return result;
	}

	private void permuteHelper(List<List<?>> lists, int depth, List<Object> current, List<List<Object>> result) {
		if (depth == lists.size()) {
			result.add(new ArrayList<>(current));
			return;
		}
		for (Object obj : lists.get(depth)) {
			current.add(obj);
			permuteHelper(lists, depth + 1, current, result);
			current.remove(current.size() - 1);
		}
	}

	private List<? extends Object> createParameters(Class<?> paramClass){

		return new RandomParameterCreator().buildParameter(paramClass);
	}



}
