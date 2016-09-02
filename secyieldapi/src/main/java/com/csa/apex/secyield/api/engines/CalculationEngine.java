/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.engines;

import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.CalculationException;

/**
 * CalculationEngine Interface for all calculation engines
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@FunctionalInterface
public interface CalculationEngine {

	/**
	 * Calculate method
	 * 
	 * 
	 * @param securitySECData
	 *            the input securitySECData
	 * @param configuration
	 *            the SECConfiguration to be used for config values
	 * @return SecuritySECData with updated data
	 * @throws CalculationException
	 */
	public SecuritySECData calculate(SecuritySECData securitySECData, SECConfiguration configuration)
			throws CalculationException;

}
