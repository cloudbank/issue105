/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * The spring application for the Commons Module
 *
 * @author TCSDEVELOPER
 * @version 1.1
 */
@SpringBootApplication
@ImportResource("applicationContext.xml")
public class Application {
	/**
	 * The method to enter the application.
	 * 
	 * @param args
	 *            the arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
