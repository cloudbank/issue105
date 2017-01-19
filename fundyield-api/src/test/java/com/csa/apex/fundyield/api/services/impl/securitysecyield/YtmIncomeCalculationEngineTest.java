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
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.TestUtility;

/**
 * Test class for the YtmIncomeCalculationEngine.
 *
 * @see YtmIncomeCalculationEngine
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class YtmIncomeCalculationEngineTest {
	/**
	 * Utility class.
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * YtmIncomeCalculationEngine object.
	 */
	@Autowired
	private YtmIncomeCalculationEngine ytmIncomeCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if FundAccountingYieldData is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		ytmIncomeCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		ytmIncomeCalculationEngine.calculate(new FundAccountingYieldData(), null);
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 *
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration() throws Exception {
		new YtmIncomeCalculationEngine().checkConfiguration();
	}

	/**
	 * Ytm income calculation mv = 70135342.4 ai = 257693.72 y = 0.00948961511103637 infllnc = -4956.56 .
	 * 
	 * @throws Exception
	 */

	@Test
	public void checkIncomeCalculationTest1() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);
		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);

		tes.setDerIncomeCalcEngineCode(ytmIncomeCalculationEngine.getEngineCode());
		tes.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.00948961511103637)));

		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setMarketValueBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(70135342.4)));
		holding.setAccruedIncomeAmt(utility.getBigDecimalWithScale7(new BigDecimal(257693.72)));
		holding.setEarnedInflCmpsBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(-4956.56)));

		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		ytmIncomeCalculationEngine.calculate(data, configuration);
		assertEquals(holding.getDerSecYield1DayIncomeAmt().setScale(0,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(-3101).setScale(0, BigDecimal.ROUND_HALF_DOWN));
	}

	/**
	 * Ytm income calculation mv = 70135342.4 ai = 257693.72 y = 0.30948961511103637 infllnc = -4956.56 Y%/FX>
	 * threshold.
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkIncomeCalculationTest2() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0);
		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);

		tes.setDerIncomeCalcEngineCode(ytmIncomeCalculationEngine.getEngineCode());
		tes.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.30948961511103637)));

		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setMarketValueBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(70135342.4)));
		holding.setAccruedIncomeAmt(utility.getBigDecimalWithScale7(new BigDecimal(257693.72)));
		holding.setEarnedInflCmpsBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(-4956.56)));

		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		ytmIncomeCalculationEngine.calculate(data, configuration);
		assertEquals(holding.getDerSecYield1DayIncomeAmt().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(34150.68).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

}
