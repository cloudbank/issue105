package com.csa.apex.secyield.api.services.impl;

import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.CalculationException;

/**
 * CalculationEngine
 * Interface for all calculation engines
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
	 * @param configuration
	 * @return SecuritySECData with updated data
	 * @throws CalculationException
	 */
	public SecuritySECData calculate(SecuritySECData securitySECData,SECConfiguration configuration) throws CalculationException;
	
}
