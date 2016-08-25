/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.utility;

import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;

/**
 * CommonUtility Exposes useful function used through out the code
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class CommonUtility {
	/**
	 * Private constructor
	 */
	private CommonUtility() {

	}

	/**
	 * Check passed parameter in engine implementations
	 * 
	 * @param securitySECData
	 *            the passed SecuritySECData object
	 * @param configuration
	 *            the passed SECConfiguration object
	 * @return true if both are not null else returns false
	 */
	public static Boolean checkPassedParametersEngines(SecuritySECData securitySECData,
			SECConfiguration configuration) {
		Boolean isParamsNotNull = false;
		if (securitySECData != null && configuration != null) {
			isParamsNotNull = true;
		}
		return isParamsNotNull;
	}

}
