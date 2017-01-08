/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The spring application for the FAYA API.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Application extends SpringBootServletInitializer {

	/**
	 * The method to enter the application.
	 * 
	 * @param args
	 *            the arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
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
				registry.addMapping("/fayaFundAccountingSECYieldData").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/calculatedFundAccountingSECYieldData").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/securitySECDataConfiguration").allowedOrigins("*").allowedMethods("*");
			}
		};
	}
}
