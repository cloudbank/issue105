/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services.impl.engines;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.csa.apex.secyield.api.services.impl.CalculationEngine;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.CalculationException;
import com.csa.apex.secyield.exceptions.ConfigurationException;
import com.csa.apex.secyield.utility.CommonUtility;

/**
 * SequenceSecurityCalculationEngine
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@Component
public class SequenceSecurityCalculationEngine implements CalculationEngine {
	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(SequenceSecurityCalculationEngine.class);

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.illegalargumentexception}")
	private String illegalArgumentExceptionMessage;

	/**
	 * Calculate method name
	 */
	@Value("${calculationengine.calculatemethodname}")
	private String calculateMethodName;

	/**
	 * Configuration exception message
	 */
	@Value("${messages.configurationargumentexception}")
	private String configurationArgumentExceptionMessage;

	/**
	 * Error log message format
	 */
	@Value("${messages.errorlogmessage}")
	private String logErrorFormat;

	/**
	 * list of calculation engines to be executed in sequence
	 */
	private List<CalculationEngine> calculationEngines;

	/**
	 * Constructor
	 */
	public SequenceSecurityCalculationEngine() {
		// default constructor
	}

	/**
	 * Getter for calculationEngines
	 * 
	 * @return calculationEngines
	 */
	public List<CalculationEngine> getCalculationEngines() {
		return calculationEngines;
	}

	/**
	 * Setter for calculationEngines
	 * 
	 * @param calculationEngines
	 */
	public void setCalculationEngines(List<CalculationEngine> calculationEngines) {
		this.calculationEngines = calculationEngines;
	}

	/**
	 * Check passed parameter should not be null
	 * 
	 * @param securitySECData
	 *            the passed SecuritySECData object
	 * @param configuration
	 *            the passed SECConfiguration object
	 * @return true if both are not null else returns false
	 */
	private Boolean checkPassedParameters(SecuritySECData securitySECData, SECConfiguration configuration) {
		return CommonUtility.checkPassedParametersEngines(securitySECData, configuration);
	}

	/**
	 * Checks beans are injected properly on postconstruct
	 */
	@PostConstruct
	protected void checkConfiguration() {
		if (calculationEngines == null || calculationEngines.isEmpty()) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
	}

	/**
	 * Engine Calculate method implementation
	 * 
	 * 
	 * @param securitySECData
	 * @param configuration
	 * @return securitySECData with calculated result
	 * 
	 * @throws CalculationException
	 */
	@Override
	public SecuritySECData calculate(SecuritySECData securitySECData, SECConfiguration configuration)
			throws CalculationException {
		if (!checkPassedParameters(securitySECData, configuration)) {
			logger.error(String.format(logErrorFormat, calculateMethodName, illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		try {
			SecuritySECData updatedSecuritySECData = new SecuritySECData();
			for (CalculationEngine calcEngine : calculationEngines) {
				updatedSecuritySECData = calcEngine.calculate(securitySECData, configuration);
			}
			return updatedSecuritySECData;
		} catch (Exception ex) {
			logger.error(String.format(logErrorFormat, calculateMethodName, ex.getMessage()));
			throw new CalculationException(ex.getMessage(), ex);
		}
	}
}
