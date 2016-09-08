/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.engines;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.csa.apex.secyield.api.engines.impl.YtmYieldCalculationEngine;
import com.csa.apex.secyield.entities.PositionData;
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
 * Test class for the YtmYieldCalculationEngine .
 *
 * @see YtmYieldCalculationEngineTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class YtmYieldCalculationEngineTest {
	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * the YtmYieldCalculationEngine object
	 */
	@Autowired
	private YtmYieldCalculationEngine ytmYieldCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Checks yield value when operationscale is overridden by configuration
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		ytmYieldCalculationEngine.calculate(securitySECData, null);
	}

	/**
	 * Yield calculation test r = 0.01375 market price = 114.7389035 maturity date = 02/15/2044 report date = 06/03/2016
	 * rv = 100 TIPSInflationaryRatio = 1.0222502
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationTest1() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setSecurityRedemptionPrice(utility.getBigDecimalWithScale7(new BigDecimal(100)));
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.01375)));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("02/15/2044"));
		securitySECData.setSecurityPrice(utility.getBigDecimalWithScale7(new BigDecimal(114.7389035)));
		securitySECData.setDerTIPSInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.022250268)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());		
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(0.00876286944279763)));
	}

	/**
	 * Yield calculation test r = 0.0075 market price = 98.3082779 maturity date = 02/15/2045 report date = 06/03/2016
	 * rv = 100 TIPSInflationaryRatio = 1.011740011
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationTest2() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setSecurityRedemptionPrice(utility.getBigDecimalWithScale7(new BigDecimal(100)));
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.0075)));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("02/15/2045"));
		securitySECData.setSecurityPrice(utility.getBigDecimalWithScale7(new BigDecimal(98.3082779)));
		securitySECData.setDerTIPSInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.011740011)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(0.00861600697373704)));
	}

	/**
	 * Yield calculation test r = 0.00625 market price = 104.7185855 maturity date = 01/15/2026 report date = 06/03/2016
	 * rv = 100 TIPSInflationaryRatio = 1.00267030
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationTest3() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setSecurityRedemptionPrice(utility.getBigDecimalWithScale7(new BigDecimal(100)));
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.00625)));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("01/15/2026"));
		securitySECData.setSecurityPrice(utility.getBigDecimalWithScale7(new BigDecimal(104.7185855)));
		securitySECData.setDerTIPSInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.00267030)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(0.001595912)));
	}

	/**
	 * Yield calculation test when yield < 0 
	 * r = 0.02375
	 * market price = 125.4284756 maturity date = 01/15/2017 
	 * report date = 12/01/2014, rv = 100 
	 * TIPSInflationaryRatio = 1.18024
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationNegativeTest1() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setSecurityRedemptionPrice(utility.getBigDecimalWithScale7(new BigDecimal(100)));
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.02375)));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("01/15/2017"));
		securitySECData.setSecurityPrice(utility.getBigDecimalWithScale7(new BigDecimal(125.4284756)));
		securitySECData.setDerTIPSInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.18024)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
		securitySECData.setReportDate(formatter.parse("12/01/2014"));
		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(-0.0055940)));
	}

	/**
	 * derTIPSInflationaryRatio calculation test on normal scenario
	 *
	 * positionValInflationAdjShares = 50438.66
	 * shareParAmount = 45
	 *
	 * @throws Exception
	 */
	@Test
	public void checkDerTIPSInflationaryRatioTest() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setSecurityRedemptionPrice(utility.getBigDecimalWithScale7(new BigDecimal(100)));
		securityReferenceData.setInterestRt(utility.getBigDecimalWithScale7(new BigDecimal(0.01375)));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("02/15/2044"));
		securitySECData.setSecurityPrice(utility.getBigDecimalWithScale7(new BigDecimal(114.7389035)));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		SECConfiguration configuration = new SECConfiguration();

		PositionData positionData = new PositionData();
		positionData.setPositionValInflationAdjShares(new BigDecimal("50438.66"));
		positionData.setShareParAmount(new BigDecimal("45"));
		securitySECData.setPositionData(new PositionData[]{positionData});

		ytmYieldCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getDerTIPSInflationaryRatio().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(1120.859111111111)));
	}

}
