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
                registry.addMapping("/securitySECDataConfiguration").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/fayaFundAccountingSECYieldData").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/calculatedFundAccountingSECYieldData").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/customerMoneyMarketFundYieldData").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/calculatedMoneyMarketFundYieldPortfolio").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/calculatedFAYAMoneyMarketFundYieldData").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/customerMoneyMarketFundYieldData").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/calculatedMoneyMarketFundYieldPortfolio").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/calculatedFAYAMoneyMarketFundYieldData").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/avgOfMm1DayDistYieldPctForPreviousDays").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/sumOfDer1DayYieldN1AMmPctPreviousDays").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/sumOfDerRestate1DayYieldMmPctPreviousDays").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/avgOfMm7DayYieldPctForPreviousDays").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/sumOfDer7DayYieldN1AMmPctPreviousDays").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/secFundLevelBatchToleranceCheck").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/securityLevelBatchToleranceCheck").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/moneyMarketFundLevelBatchToleranceCheck").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/distributionFundLevelBatchToleranceCheck").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/securityLevelWhatIfToleranceCheck").allowedOrigins("*").allowedMethods("*");
                registry.addMapping("/positionLevelWhatIfToleranceCheck").allowedOrigins("*").allowedMethods("*");
			}
		};
	}
}
