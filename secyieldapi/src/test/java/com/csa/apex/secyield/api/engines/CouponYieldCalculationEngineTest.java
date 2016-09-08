/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.engines;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import com.csa.apex.secyield.api.engines.impl.CouponYieldCalculationEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.utility.TestUtility;

/**
 * Test class for the CouponYieldCalculationEngine.
 *
 * @see CouponYieldCalculationEngine
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CouponYieldCalculationEngineTest {

	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * CouponYieldCalculationEngine object
	 */
	@Autowired
	private CouponYieldCalculationEngine couponYieldCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if SecuritySECData is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		couponYieldCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		couponYieldCalculationEngine.calculate(securitySECData, null);
	}

	/**
	 * Checks scale value when operation scale is overridden by configuration
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkScaleWithConfiguredOperationScale() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setInterestRt(new BigDecimal(10));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(5);
		couponYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerOneDaySecurityYield().scale(), 5);
	}

	/**
	 * Checks coupon yield calculation Coupon Rate : 0.04959
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculation() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.04959)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		couponYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerOneDaySecurityYield(), securityReferenceData.getInterestRt());
	}

	/**
	 * Checks clean price calculation
	 *
	 * Security Price : 500.50
	 * TIPS Inflationary Ratio: 10.10
	 *
	 * @throws Exception
	 */
	@Test
	public void checkCleanPriceCalculation() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.04959)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
		securitySECData.setSecurityPrice(utility.getBigDecimalWithScale7(new BigDecimal(500.50)));
		securitySECData.setDerTIPSInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(10.10)));
		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		couponYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(utility.getBigDecimalWithScale7(new BigDecimal(49.55445544)), securitySECData.getDerCleanPrice());
	}

}
