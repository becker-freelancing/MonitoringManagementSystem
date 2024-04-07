package com.jabasoft.mms.tools.architecture.coding;

import java.time.LocalTime;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@SuppressWarnings("unused")
public class CompliantCodingStyle {

	LocalTime time;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Inject
	@com.google.inject.Inject
	public CompliantCodingStyle(@Value("test") String autowired) {

	}

	@Resource
	public void postConstruct(String autowired) {

	}

	private void customException() {

		throw new CustomException();
	}
}
