/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services.impl.engines;

import java.util.Map;

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
 * CalculationEngineSelector
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@Component
public class CalculationEngineSelector implements CalculationEngine {
	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(CalculationEngineSelector.class);

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.illegalargumentexception}")
	private String illegalArgumentExceptionMessage;

	/**
	 * Unsupported operation exception message
	 */
	@Value("${messages.unsupportedoperationexception}")
	private String unSupportedOperationException;

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
	private Map<String, CalculationEngine> calculationEngines;

	/**
	 * Constructor
	 */
	public CalculationEngineSelector() {
		// default constructor
	}

	/**
	 * Getter calculationEngines
	 * 
	 * @return calculationEngines
	 */
	public Map<String, CalculationEngine> getCalculationEngines() {
		return calculationEngines;
	}

	/**
	 * Setter calculationEngines
	 * 
	 * @param calculationEngines
	 */
	public void setCalculationEngines(Map<String, CalculationEngine> calculationEngines) {
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
		if (calculationEngines == null || calculationEngines.size() == 0) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
	}

	/**
	 * Engine Calculate method implementation Checks whether IvType is VPS or VRDN or DVRN and accordingly calls
	 * respective engines
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
		if (securitySECData.getSecurityReferenceData().isDerStepIndicator()
				|| securitySECData.getSecurityReferenceData().isDerHybridIndicator()) {
			throw new UnsupportedOperationException(unSupportedOperationException);
		}
		try {
			// if type VP
			if ("VPS".equalsIgnoreCase(securitySECData.getSecurityReferenceData().getIvType())) {
				securitySECData.setDerYieldCalcEngine(YtmYieldCalculationEngine.ENGINE_NAME);
				securitySECData.setDerIncomeCalcEngine(YtmIncomeCalculationEngine.ENGINE_NAME);

			}
			// if type VRDN || DVRN
			else if ("VRDN".equalsIgnoreCase(securitySECData.getSecurityReferenceData().getIvType())
					|| "DVRN".equalsIgnoreCase(securitySECData.getSecurityReferenceData().getIvType())) {
				securitySECData.setDerYieldCalcEngine(CouponYieldCalculationEngine.ENGINE_NAME);
				securitySECData.setDerIncomeCalcEngine(CouponIncomeCalculationEngine.ENGINE_NAME);
			}
			// else throw exception
			else {
				throw new UnsupportedOperationException(unSupportedOperationException);
			}
			return securitySECData;
		} catch (UnsupportedOperationException ex) {
			logger.error(String.format(logErrorFormat, calculateMethodName, ex.getMessage()));
			throw ex;
		} catch (Exception ex) {
			logger.error(String.format(logErrorFormat, calculateMethodName, ex.getMessage()));
			throw new CalculationException(ex.getMessage(), ex);
		}
	}
}
