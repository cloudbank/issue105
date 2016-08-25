/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services.impl.engines;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.utility.TestUtility;

/**
 * Test class for the SequenceSecurityCalculationEngine.
 *
 * @see SequenceSecurityCalculationEngine
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SequenceSecurityCalculationEngineTest {

	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * SequenceSecurityCalculationEngine object
	 */
	@Autowired
	private SequenceSecurityCalculationEngine sequenceSecurityCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if SecuritySECData is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		sequenceSecurityCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		sequenceSecurityCalculationEngine.calculate(securitySECData, null);
	}

	/**
	 * Checks yield and income of coupon type Coupon Engines are automatically injected through bean Calculations are
	 * already tested in independent engine unit tests Only call to engine calculation is checked
	 * 
	 * @throws Exception
	 */

	@Test
	public void checkCouponYieldIncomeCalculation() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.049592404)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setFxRate(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		PositionData positionData = new PositionData();
		positionData.setSharePerAmount(utility.getBigDecimalWithScale7(new BigDecimal(7000000)));
		positionData.setEarnedAmortizationBase(utility.getBigDecimalWithScale7(new BigDecimal(-45.69)));
		securitySECData.setPositionData(new PositionData[] { positionData });
		SECConfiguration configuration = new SECConfiguration();
		sequenceSecurityCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerOneDaySecurityYield(), securityReferenceData.getInterestRt());
		assertEquals(securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(918.61).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

}
