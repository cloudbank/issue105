/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.utility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.csa.apex.secyield.entities.SecuritySECData;

/**
 * Utility class for the CalculationEngine.
 *
 * @see TestUtility
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@Component
public class TestUtility {

	/**
	 * Returns value with scale 7 with ROUND_HALF_DOWN
	 * 
	 * @param val
	 *            value passed
	 * @return value with scale 7
	 */
	public BigDecimal getBigDecimalWithScale7(BigDecimal val) {
		val = val.setScale(7, BigDecimal.ROUND_HALF_DOWN);
		return val;
	}

	/**
	 * Get Mock list of SecuritySECData
	 * 
	 * @return List<SecuritySECData>
	 * @throws ParseException
	 *             if any parsing exception occurs
	 */
	public List<SecuritySECData> getSecuritySECData() throws ParseException {
		return MockDataServiceUtility.getSecuritySECDataWithYieldAndIncomeData();
	}

	/**
	 * Get Mock array of SecuritySECData
	 * 
	 * @return SecuritySECData[]
	 * @throws ParseException
	 *             if any parsing exception occurs
	 */
	public SecuritySECData[] getSecuritySECDataArray() throws ParseException {
		final List<SecuritySECData> securitySECData = MockDataServiceUtility.getSecuritySECDataWithYieldAndIncomeData();
		return securitySECData.toArray(new SecuritySECData[securitySECData.size()]);
	}
}
