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
import com.csa.apex.fundyield.api.services.impl.securitysecyield.CouponIncomeCalculationEngine;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.TestUtility;

/**
 * Test class for the CouponIncomeCalculationEngine.
 *
 * @see CouponIncomeCalculationEngine
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CouponIncomeCalculationEngineTest {
	/**
	 * Utility class.
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * CouponIncomeCalculationEngine object.
	 */
	@Autowired
	private CouponIncomeCalculationEngine couponIncomeCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if FundAccountingYieldData is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		couponIncomeCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		couponIncomeCalculationEngine.calculate(new FundAccountingYieldData(), null);
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 * 
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration() throws Exception {
		new CouponIncomeCalculationEngine().checkConfiguration();
	}

	/**
	 * Checks coupon income value.
	 * 
	 * Sh = 7000000 Y = 0.049592404 FX = 1 Am = -45.69 Income = 918.61
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkIncomeCalculationTest1() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);
		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);

		tes.setDerIncomeCalcEngineCode(couponIncomeCalculationEngine.getEngineCode());
		tes.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.049592404)));
		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setSettleShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(7000000)));
		holding.setEarnedAmortBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(-45.69)));

		SECConfiguration configuration = new SECConfiguration();
		couponIncomeCalculationEngine.calculate(data, configuration);
		assertEquals(holding.getDerSecYield1DayIncomeAmt().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(918.61).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

	/**
	 * Checks coupon income value.
	 * 
	 * Sh = 4900000 Y = 0.004199982 FX = 1 Am = 0 Income = 57.17
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkIncomeCalculationTest2() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);
		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);

		tes.setDerIncomeCalcEngineCode(couponIncomeCalculationEngine.getEngineCode());
		tes.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.004199982)));
		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setSettleShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(4900000)));
		holding.setEarnedAmortBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(0)));

		SECConfiguration configuration = new SECConfiguration();
		couponIncomeCalculationEngine.calculate(data, configuration);
		assertEquals(holding.getDerSecYield1DayIncomeAmt().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(57.17).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

	/**
	 * Checks coupon income value.
	 * 
	 * Am is null, calculation should be escaped.
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkIncomeCalculationTest3() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);
		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);

		tes.setDerIncomeCalcEngineCode(couponIncomeCalculationEngine.getEngineCode());
		tes.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.004199982)));
		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setSettleShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(4900000)));

		SECConfiguration configuration = new SECConfiguration();
		couponIncomeCalculationEngine.calculate(data, configuration);
		assertEquals(holding.getDerSecYield1DayIncomeAmt(), null);
	}

}
