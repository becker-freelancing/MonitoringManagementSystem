package com.jabasoft.mms.tools.architecture.coding;

import java.io.OutputStream;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@SuppressWarnings("unused")
public class ViolatedCodingStyle {

	@Autowired
	@Inject
	@com.google.inject.Inject
	@Value("test")
	@Resource
	private String autowired;

	private DateTime now;

	Logger logger = Logger.getLogger("UtilLogger");

	private void genericException() throws Throwable {

		switch ("") {
			case "1" :
				throw new Exception();
			case "2" :
				throw new RuntimeException("error");
			default :
				throw new Throwable("error");
		}
	}

	private void standardStreams() {

		System.out.println("foo");
		System.err.println("bar");
		OutputStream out = System.out;

		try {
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
