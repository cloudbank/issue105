/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.fundyield.Application;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.TestUtility;

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
	 * Utility class.
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * CouponYieldCalculationEngine object.
	 */
	@Autowired
	private CouponYieldCalculationEngine couponYieldCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if FundAccountingYieldData is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		couponYieldCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		couponYieldCalculationEngine.calculate(new FundAccountingYieldData(), null);
	}

	/**
	 * Checks scale value when operation scale is overridden by configuration.
	 *
	 * @throws Exception
	 */
	@Test
	public void checkScaleWithConfiguredOperationScale() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		tes.setDerYieldCalcEngineCode(couponYieldCalculationEngine.getEngineCode());
		tes.setCurrentIncomeRate(new BigDecimal(10));

		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(5);
		couponYieldCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield().scale(), 5);
	}

	/**
	 * Checks coupon yield calculation Coupon Rate : 0.04959 .
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculation() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		tes.setDerYieldCalcEngineCode(couponYieldCalculationEngine.getEngineCode());
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.04959)));

		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		couponYieldCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield(), tes.getCurrentIncomeRate());
	}

	/**
	 * Checks clean price calculation.
	 *
	 * Security Price : 500.50
	 * TIPS Inflationary Ratio: 10.10
	 *
	 * @throws Exception
	 */
	@Test
	public void checkCleanPriceCalculation() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		tes.setDerYieldCalcEngineCode(couponYieldCalculationEngine.getEngineCode());
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.04959)));
		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(500.50)));
		tes.setFdrTipsInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(10.10)));

		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		couponYieldCalculationEngine.calculate(data, configuration);
		assertEquals(utility.getBigDecimalWithScale7(new BigDecimal(49.55445544)), tes.getFdrCleanPrice());
	}

}
