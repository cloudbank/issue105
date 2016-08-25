/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services.impl.engines;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.csa.apex.secyield.api.services.impl.CalculationEngine;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.CalculationException;
import com.csa.apex.secyield.utility.CommonUtility;

/**
 * CouponYieldCalculationEngine
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@Component
public class CouponYieldCalculationEngine implements CalculationEngine {
	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(CouponYieldCalculationEngine.class);

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
	 * Error log message format
	 */
	@Value("${messages.errorlogmessage}")
	private String logErrorFormat;

	/**
	 * Calculation engine name
	 */
	public static final String ENGINE_NAME = "CouponYieldCalculationEngine";

	/**
	 * The scale for the BigDecimal operations. Has the default value.
	 */
	private int operationScale = 7;

	/**
	 * Default Rounding mode
	 */
	private int roundingMode = 4;

	/**
	 * Constructor
	 */
	public CouponYieldCalculationEngine() {
		// default constructor
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
	 * Read from configuraion object and override the operationScale default value
	 * 
	 * @param configuration
	 *            the configuration object
	 */
	private void setConfiguration(SECConfiguration configuration) {
		int passedOperationScale = configuration.getOperationScale();
		int passedRoundingMode = configuration.getRoundingMode();
		operationScale = passedOperationScale != 0 ? passedOperationScale : operationScale;
		roundingMode = passedRoundingMode != 0 ? passedRoundingMode : roundingMode;
	}

	/**
	 * Engine Calculate method implementation
	 * 
	 * 
	 * @param securitySECData
	 * @param configuration
	 * @return securitySECData with calculated result
	 * @throws CalculationException
	 */
	@Override
	public SecuritySECData calculate(SecuritySECData securitySECData, SECConfiguration configuration)
			throws CalculationException {
		if (!checkPassedParameters(securitySECData, configuration)) {
			logger.error(String.format(logErrorFormat, calculateMethodName, illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		setConfiguration(configuration);
		try {
			BigDecimal yield = securitySECData.getSecurityReferenceData().getInterestRt().setScale(operationScale,
					roundingMode);
			securitySECData.setDerOneDaySecurityYield(yield);
			return securitySECData;
		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, calculateMethodName, e.getMessage()));
			throw new CalculationException(e.getMessage(), e);
		}
	}
}
