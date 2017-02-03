/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.fundyield.Application;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.TestUtility;

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
	 * CouponIncomeCalculationEngine object.
	 */
	@Autowired
	private CouponIncomeCalculationEngine couponIncomeCalculationEngine;

	/**
	 * the YtmYieldCalculationEngine object.
	 */
	@Autowired
	private YtmYieldCalculationEngine ytmYieldCalculationEngine;

	/**
	 * YtmIncomeCalculationEngine object.
	 */
	@Autowired
	private YtmIncomeCalculationEngine ytmIncomeCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if
	 * FundAccountingYieldData is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		new SequenceSecurityCalculationEngine().calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is
	 * null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		new SequenceSecurityCalculationEngine().calculate(new FundAccountingYieldData(), null);
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured
	 * properly.
	 *
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration() throws Exception {
		new SequenceSecurityCalculationEngine().checkConfiguration();
	}

	/**
	 * Checks yield and income of Coupon type engines are orderly calculated.
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkCouponYieldIncomeCalculation() throws Exception {
		SequenceSecurityCalculationEngine sequenceSecurityCalculationEngine = new SequenceSecurityCalculationEngine();
		sequenceSecurityCalculationEngine
				.setCalculationEngines(Arrays.asList(couponYieldCalculationEngine, couponIncomeCalculationEngine));

		FundAccountingYieldData data = utility.constructFAYAData();
		TradableEntitySnapshot tes = data.getInstruments().get(0).getTradableEntities().get(0)
				.getTradableEntitySnapshots().get(0);

		tes.setDerYieldCalcEngineCode(couponYieldCalculationEngine.getEngineCode());
		tes.setDerIncomeCalcEngineCode(couponIncomeCalculationEngine.getEngineCode());
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.049592404)));

		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);
		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setSettleShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(7000000)));
		holding.setEarnedAmortBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(-45.69)));

		SECConfiguration configuration = new SECConfiguration();
		sequenceSecurityCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield(), tes.getCurrentIncomeRate());
		assertEquals(holding.getDerSecYield1DayIncomeAmt().setScale(2, BigDecimal.ROUND_HALF_DOWN),
				new BigDecimal(918.61).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

	/**
	 * Checks yield and income of YTM type engines are orderly calculated.
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYTMYieldIncomeCalculation() throws Exception {
		SequenceSecurityCalculationEngine sequenceSecurityCalculationEngine = new SequenceSecurityCalculationEngine();
		sequenceSecurityCalculationEngine
				.setCalculationEngines(Arrays.asList(ytmYieldCalculationEngine, ytmIncomeCalculationEngine));

		FundAccountingYieldData data = utility.constructFAYAData();
		Instrument instrument = data.getInstruments().get(0);
		TradableEntitySnapshot tes = instrument.getTradableEntities().get(0).getTradableEntitySnapshots().get(0);
		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		data.setReportDate(formatter.parse("06/03/2016"));
		instrument.setFinalMaturityDate(formatter.parse("02/15/2044"));
		instrument.setMaturityPrc(utility.getBigDecimalWithScale7(new BigDecimal(100)));

		tes.setDerYieldCalcEngineCode(ytmYieldCalculationEngine.getEngineCode());
		tes.setDerIncomeCalcEngineCode(ytmIncomeCalculationEngine.getEngineCode());
		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(114.7389035)));
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.01375)));
		tes.setFdrTipsInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.022250268)));

		holding.setInflationAdjShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(42.23)));
		holding.setSettleShareCnt(utility.getBigDecimalWithScale7(new BigDecimal(142.23)));
		holding.setFxRt(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		holding.setMarketValueBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(70135342.4)));
		holding.setAccruedIncomeAmt(utility.getBigDecimalWithScale7(new BigDecimal(257693.72)));
		holding.setEarnedInflCmpsBaseAmt(utility.getBigDecimalWithScale7(new BigDecimal(-4956.56)));

		SECConfiguration configuration = new SECConfiguration();
		sequenceSecurityCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield().setScale(2, BigDecimal.ROUND_HALF_DOWN),
				new BigDecimal(-0.02).setScale(2, BigDecimal.ROUND_HALF_DOWN));
		assertEquals(holding.getDerSecYield1DayIncomeAmt().setScale(2, BigDecimal.ROUND_HALF_DOWN),
				new BigDecimal(-8867.28).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

}
