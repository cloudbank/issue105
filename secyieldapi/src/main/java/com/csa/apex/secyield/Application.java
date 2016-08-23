/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The spring application for the Public API
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

	/**
	 * The CORS configurer.
	 *
	 * @return the new mvc configurer
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// add allowed origins here
				// currently, it allows all origins
				registry.addMapping("/securitySECData").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/calcualtedSecuritySECData").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/exportCalculatedSecuritySECData").allowedOrigins("*").allowedMethods("*");
			}
		};
	}
}
