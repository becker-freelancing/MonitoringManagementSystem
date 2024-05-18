package com.jabasoft.mms.junit.beans.supplier.setter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import com.jabasoft.mms.junit.beans.supplier.NotConstructableException;
import com.jabasoft.mms.junit.beans.supplier.parameter.RandomParameterCreator;

public abstract class RandomSetterBeanCreator<B> {

	protected abstract Class<? extends B> getBeanClass();

	public List<B> createBeans() {

		List<Method> setter = getSetter();

		return createBeansForSetter(setter);
	}

	private List<Method> getSetter() {

		B bean = constructBean();
		Method[] declaredMethods = bean.getClass().getDeclaredMethods();
		List<Method> setter = new ArrayList<>();

		for (Method declaredMethod : declaredMethods) {
			if(declaredMethod.getName().startsWith("set")){
				setter.add(declaredMethod);
			}
		}

		return setter;
	}

	private List<B> createBeansForSetter(List<Method> setter) {

		if (setter.isEmpty()) {
			return List.of();
		}

		List<Parameter> parameters = setter.stream()
			.map(Method::getParameters)
			.filter(params -> params.length == 1)
			.map(params -> params[0])
			.toList();

		if (parameters.size() != setter.size()) {
			return List.of();
		}

		List<List<?>> parameterValues = new ArrayList<>();

		for (Parameter parameter : parameters) {
			if (parameter.getType().equals(List.class)) {
				String[] split = parameter.toString().split("[<>]");
				if (split.length == 3) {
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

		return createBeans(setter, parameterValues);
	}

	private List<B> createBeans(List<Method> setter, List<List<?>> parameterValues) {

		List<B> beans = createBeansRecursive(new ArrayList<>(), new ArrayList<>(setter), new ArrayList<>(parameterValues));
		try {
			return fillNulls(beans);
		}
		catch (IllegalAccessException e) {
			throw new NotConstructableException(e);
		}
	}

	private List<B> fillNulls(List<B> beans) throws IllegalAccessException {

		for (B bean : beans) {
			for (Field declaredField : bean.getClass().getDeclaredFields()) {
				declaredField.setAccessible(true);
				Object val = declaredField.get(bean);
				if(val != null){
					continue;
				}
				for (B other : beans) {
					Object otherVal = declaredField.get(other);
					if(otherVal != null){
						declaredField.set(bean, otherVal);
						break;
					}
				}
			}
		}

		return beans;
	}

	private List<B> createBeansRecursive(List<B> constructed, List<Method> setters, List<List<?>> parameterValues){
		if(setters.isEmpty()){
			return constructed;
		}

		Method setter = setters.remove(0);
		List<?> parameters = parameterValues.remove(0);

		for (int i = 0; i < parameters.size(); i++){
			B bean;
			if(i < constructed.size()){
				bean = constructed.remove(i);
			} else {
				bean = constructBean();
			}

			try {
				setter.invoke(bean, parameters.get(i));
			}
			catch (Exception e) {
				throw new NotConstructableException(e);
			}

			constructed.add(bean);
		}

		return createBeansRecursive(constructed, setters, parameterValues);
	}

	private B constructBean() {

		try {
			Constructor<? extends B> defaultConstructor = getBeanClass().getDeclaredConstructor();
			return defaultConstructor.newInstance();
		}
		catch (Exception e) {
			throw new NotConstructableException(e);
		}
	}

	private List<? extends Object> createParameters(Class<?> paramClass) {

		return new RandomParameterCreator().buildParameter(paramClass);
	}

}
