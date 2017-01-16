/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;

/**
 * CalculationEngine Interface for all calculation engines.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public interface CalculationEngine {

	/**
	 * Get engine code.
	 * @return engine code
	 */
	public default String getEngineCode() {
	    return this.getClass().getSimpleName();
	}

	/**
	 * Calculate method.
	 * 
	 * 
	 * @param fundAccountingYieldData
	 *            the input fundAccountingYieldData
	 * @param configuration
	 *            the SECConfiguration to be used for config values
	 * @throws CalculationException
	 */
	public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration)
			throws CalculationException;
}
