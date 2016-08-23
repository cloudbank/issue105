/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * The spring test application for the Customer API
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@SpringBootApplication
@ImportResource("applicationContext-test.xml")
public class TestApplication {
	/**
	 * The method to enter the application.
	 * 
	 * @param args
	 *            the arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
