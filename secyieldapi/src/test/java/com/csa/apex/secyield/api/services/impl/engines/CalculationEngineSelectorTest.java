/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services.impl.engines;

import static org.junit.Assert.*;

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

/**
 * Test class for the CalculationEngineSelector.
 *
 * @see CalculationEngineSelectorTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CalculationEngineSelectorTest {

	/**
	 * CalculationEngineSelector object
	 */
	@Autowired
	private CalculationEngineSelector calculationEngineSelector;

	/**
	 * Tests IllegalArgumentException should be thrown if SecuritySECData is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		calculationEngineSelector.calculate(securitySECData, null);
	}

	/**
	 * Tests UnsupportedOperationException should be thrown if DerStepIndicator is true
	 * 
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void checkUnsupportedOperationExceptionWhenDerStepIndicatorTrue() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(true);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("VPS");
		securitySECData.setSecurityReferenceData(securityReferenceData);
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(securitySECData, configuration);
	}

	/**
	 * Tests UnsupportedOperationException should be thrown if DerHybridIndicator is true
	 * 
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void checkUnsupportedOperationExceptionWhenDerHybridIndicatorTrue() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(true);
		securityReferenceData.setIvType("VPS");
		securitySECData.setSecurityReferenceData(securityReferenceData);
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(securitySECData, configuration);
	}

	/**
	 * Tests UnsupportedOperationException should be thrown if unsupported IVType
	 * 
	 * @throws Exception
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void checkUnsupportedOperationExceptionWhenUnSupportedIVType() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("UNKNOWN");
		securitySECData.setSecurityReferenceData(securityReferenceData);
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(securitySECData, configuration);
	}

	/**
	 * Tests when security IV type is VPS Yield and Income engine should be YTM
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkCalculationEnginesWhenTypeVPS() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("VPS");
		securitySECData.setSecurityReferenceData(securityReferenceData);
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerYieldCalcEngine(), "YtmYieldCalculationEngine");
		assertEquals(securitySECData.getDerIncomeCalcEngine(), "YtmIncomeCalculationEngine");
	}

	/**
	 * Tests when security IV type is VRDN Yield and Income engine should be Coupon
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkCalculationEnginesWhenTypeVRDN() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("VRDN");
		securitySECData.setSecurityReferenceData(securityReferenceData);
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerYieldCalcEngine(), "CouponYieldCalculationEngine");
		assertEquals(securitySECData.getDerIncomeCalcEngine(), "CouponIncomeCalculationEngine");
	}

	/**
	 * Tests when security IV type is DVRN Yield and Income engine should be Coupon
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkCalculationEnginesWhenTypeDVRN() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("DVRN");
		securitySECData.setSecurityReferenceData(securityReferenceData);
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerYieldCalcEngine(), "CouponYieldCalculationEngine");
		assertEquals(securitySECData.getDerIncomeCalcEngine(), "CouponIncomeCalculationEngine");
	}
}
