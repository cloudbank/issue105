/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.engines.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.csa.apex.secyield.api.engines.CalculationEngine;
import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.CalculationException;
import com.csa.apex.secyield.utility.CommonUtility;

/**
 * CouponIncomeCalculationEngine
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@Component
public class CouponIncomeCalculationEngine implements CalculationEngine {
	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(CouponIncomeCalculationEngine.class);

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.illegalargumentexception}")
	private String illegalArgumentExceptionMessage;

	/**
	 * Error log message format
	 */
	@Value("${messages.errorlogmessage}")
	private String logErrorFormat;

	/**
	 * Calculation engine name
	 */
	public static final String ENGINE_NAME = "CouponIncomeCalculationEngine";

	/**
	 * Calculate method name
	 */
	@Value("${calculationengine.calculatemethodname}")
	private String calculateMethodName;

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
	public CouponIncomeCalculationEngine() {
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
	 * Read from configuration object and override the operationScale default
	 * value
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
			BigDecimal am;
			BigDecimal sh;
			BigDecimal fx = securitySECData.getFxRate();
			BigDecimal y = securitySECData.getDerOneDaySecurityYield();
			BigDecimal income;
			// calculate the income
			for (PositionData positionData : securitySECData.getPositionData()) {

				am = positionData.getEarnedAmortizationBase();
				sh = positionData.getShareParAmount();

				BigDecimal rightSide = sh.multiply(y).divide(fx, operationScale, BigDecimal.ROUND_HALF_UP)
						.divide(new BigDecimal(360), operationScale, BigDecimal.ROUND_HALF_UP);
				income = am.divide(fx, operationScale, BigDecimal.ROUND_HALF_UP).add(rightSide);
				income = income.setScale(operationScale, roundingMode);

				positionData.setDerOneDaySecurityIncome(income);

			}
			return securitySECData;
		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, calculateMethodName, e.getMessage()));
			logger.error(String.format(
					"CouponIncomeCalculationEngine.calculate Exception,securitySECData:%s,SECConfiguration:%s",
					securitySECData.toString(), configuration.toString()));
			throw new CalculationException(e.getMessage(), e);
		}
	}
}
