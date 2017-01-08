/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.fundyield.Application;
import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineSubType;
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineType;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.TestUtility;

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
	 * Utility class.
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * CalculationEngineSelector object.
	 */
	@Autowired
	private CalculationEngineSelector calculationEngineSelector;

	/**
	 * Tests IllegalArgumentException should be thrown if FundAccountingYieldData is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		calculationEngineSelector.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		calculationEngineSelector.calculate(new FundAccountingYieldData(), null);
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration1() throws Exception {
		new CalculationEngineSelector().checkConfiguration();
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration2() throws Exception {
		CalculationEngineSelector selector = new CalculationEngineSelector();
		Map<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>> calculationEngines
		    = new HashMap<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>>();
		// Empty map
		utility.injectField(selector, "calculationEngines", calculationEngines);
		selector.checkConfiguration();
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration3() throws Exception {
		CalculationEngineSelector selector = new CalculationEngineSelector();
		Map<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>> calculationEngines
		    = new HashMap<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>>();

		// Miss Coupon
		calculationEngines.put(CalculationEngineType.YTM, new HashMap<CalculationEngineSubType, CalculationEngine>());
		utility.injectField(selector, "calculationEngines", calculationEngines);
		selector.checkConfiguration();
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration4() throws Exception {
		CalculationEngineSelector selector = new CalculationEngineSelector();
		Map<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>> calculationEngines
		    = new HashMap<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>>();

		// Empty sub map
		calculationEngines.put(CalculationEngineType.COUPON, new HashMap<CalculationEngineSubType, CalculationEngine>());
		calculationEngines.put(CalculationEngineType.YTM, new HashMap<CalculationEngineSubType, CalculationEngine>());
		utility.injectField(selector, "calculationEngines", calculationEngines);
		selector.checkConfiguration();
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration5() throws Exception {
		CalculationEngineSelector selector = new CalculationEngineSelector();
		Map<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>> calculationEngines
		    = new HashMap<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>>();

		calculationEngines.put(CalculationEngineType.COUPON, new HashMap<CalculationEngineSubType, CalculationEngine>());
		calculationEngines.put(CalculationEngineType.YTM, new HashMap<CalculationEngineSubType, CalculationEngine>());

		// Miss Income calculator
		calculationEngines.get(CalculationEngineType.COUPON).put(CalculationEngineSubType.YIELD, new CouponYieldCalculationEngine());
		calculationEngines.get(CalculationEngineType.YTM).put(CalculationEngineSubType.YIELD, new YtmYieldCalculationEngine());

		utility.injectField(selector, "calculationEngines", calculationEngines);
		selector.checkConfiguration();
	}

	
//
//	/**
//	 * Tests UnsupportedOperationException should be thrown if DerStepIndicator is true.
//	 * 
//	 * @throws Exception
//	 */
//	@Test(expected = UnsupportedOperationException.class)
//	public void checkUnsupportedOperationExceptionWhenDerStepIndicatorTrue() throws Exception {
//		SecuritySECData securitySECData = new SecuritySECData();
//		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
//		securityReferenceData.setDerStepIndicator(true);
//		securityReferenceData.setDerHybridIndicator(false);
//		securityReferenceData.setIvType("VPS");
//		securitySECData.setSecurityReferenceData(securityReferenceData);
//		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
//		SECConfiguration configuration = new SECConfiguration();
//		calculationEngineSelector.calculate(securitySECData, configuration);
//	}
//
//	/**
//	 * Tests UnsupportedOperationException should be thrown if DerHybridIndicator is true.
//	 * 
//	 * @throws Exception
//	 */
//	@Test(expected = UnsupportedOperationException.class)
//	public void checkUnsupportedOperationExceptionWhenDerHybridIndicatorTrue() throws Exception {
//		SecuritySECData securitySECData = new SecuritySECData();
//		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
//		securityReferenceData.setDerStepIndicator(false);
//		securityReferenceData.setDerHybridIndicator(true);
//		securityReferenceData.setIvType("VPS");
//		securitySECData.setSecurityReferenceData(securityReferenceData);
//		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
//		SECConfiguration configuration = new SECConfiguration();
//		calculationEngineSelector.calculate(securitySECData, configuration);
//	}
//
//	/**
//	 * Tests UnsupportedOperationException should be thrown if unsupported IVType.
//	 * 
//	 * @throws Exception
//	 */
//	@Test(expected = UnsupportedOperationException.class)
//	public void checkUnsupportedOperationExceptionWhenUnSupportedIVType() throws Exception {
//		SecuritySECData securitySECData = new SecuritySECData();
//		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
//		securityReferenceData.setDerStepIndicator(false);
//		securityReferenceData.setDerHybridIndicator(false);
//		securityReferenceData.setIvType("UNKNOWN");
//		securitySECData.setSecurityReferenceData(securityReferenceData);
//		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
//		SECConfiguration configuration = new SECConfiguration();
//		calculationEngineSelector.calculate(securitySECData, configuration);
//	}
//
//	/**
//	 * Tests when security IV type is VPS Yield and Income engine should be YTM.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void checkCalculationEnginesWhenTypeVPS() throws Exception {
//		SecuritySECData securitySECData = new SecuritySECData();
//		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
//		securityReferenceData.setDerStepIndicator(false);
//		securityReferenceData.setDerHybridIndicator(false);
//		securityReferenceData.setIvType("VPS");
//		securitySECData.setSecurityReferenceData(securityReferenceData);
//		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
//		SECConfiguration configuration = new SECConfiguration();
//		calculationEngineSelector.calculate(securitySECData, configuration);
//		assertEquals(securitySECData.getDerYieldCalcEngine(), "YtmYieldCalculationEngine");
//		assertEquals(securitySECData.getDerIncomeCalcEngine(), "YtmIncomeCalculationEngine");
//	}
//
//	/**
//	 * Tests when security IV type is VRDN Yield and Income engine should be Coupon.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void checkCalculationEnginesWhenTypeVRDN() throws Exception {
//		SecuritySECData securitySECData = new SecuritySECData();
//		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
//		securityReferenceData.setDerStepIndicator(false);
//		securityReferenceData.setDerHybridIndicator(false);
//		securityReferenceData.setIvType("VRDN");
//		securitySECData.setSecurityReferenceData(securityReferenceData);
//		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
//		SECConfiguration configuration = new SECConfiguration();
//		calculationEngineSelector.calculate(securitySECData, configuration);
//		assertEquals(securitySECData.getDerYieldCalcEngine(), "CouponYieldCalculationEngine");
//		assertEquals(securitySECData.getDerIncomeCalcEngine(), "CouponIncomeCalculationEngine");
//	}
//
//	/**
//	 * Tests when security IV type is DVRN Yield and Income engine should be Coupon.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void checkCalculationEnginesWhenTypeDVRN() throws Exception {
//		SecuritySECData securitySECData = new SecuritySECData();
//		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
//		securityReferenceData.setDerStepIndicator(false);
//		securityReferenceData.setDerHybridIndicator(false);
//		securityReferenceData.setIvType("DVRN");
//		securitySECData.setSecurityReferenceData(securityReferenceData);
//		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
//		SECConfiguration configuration = new SECConfiguration();
//		calculationEngineSelector.calculate(securitySECData, configuration);
//		assertEquals(securitySECData.getDerYieldCalcEngine(), "CouponYieldCalculationEngine");
//		assertEquals(securitySECData.getDerIncomeCalcEngine(), "CouponIncomeCalculationEngine");
//	}
}
