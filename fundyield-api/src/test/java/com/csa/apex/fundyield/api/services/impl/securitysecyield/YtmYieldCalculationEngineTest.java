/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
 * Test class for the YtmYieldCalculationEngine .
 *
 * @see YtmYieldCalculationEngine
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class YtmYieldCalculationEngineTest {
	/**
	 * Utility class.
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * the YtmYieldCalculationEngine object.
	 */
	@Autowired
	private YtmYieldCalculationEngine ytmYieldCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if FundAccountingYieldData is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is null.
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		ytmYieldCalculationEngine.calculate(new FundAccountingYieldData(), null);
	}

	/**
	 * Tests ConfigurationException should be thrown if bean is not configured properly.
	 *
	 * @throws Exception
	 */
	@Test(expected = ConfigurationException.class)
	public void checkConfiguration() throws Exception {
		new YtmYieldCalculationEngine().checkConfiguration();
	}

	/**
	 * Yield calculation test r = 0.01375 market price = 114.7389035 maturity date = 02/15/2044 report date = 06/03/2016
	 * rv = 100 TIPSInflationaryRatio = 1.0222502 .
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationTest1() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		data.setPortfolios(new ArrayList<>());
		Instrument instrument = data.getInstruments().get(0);
		TradableEntitySnapshot tes = instrument.getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		data.setReportDate(formatter.parse("06/03/2016"));
		instrument.setFinalMaturityDate(formatter.parse("02/15/2044"));
		instrument.setMaturityPrc(utility.getBigDecimalWithScale7(new BigDecimal(100)));

		tes.setDerYieldCalcEngineCode(ytmYieldCalculationEngine.getEngineCode());
		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(114.7389035)));
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.01375)));
		tes.setFdrTipsInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.022250268)));

		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(0.00876286944279763)));
	}

	/**
	 * Yield calculation test r = 0.0075 market price = 98.3082779 maturity date = 02/15/2045 report date = 06/03/2016
	 * rv = 100 TIPSInflationaryRatio = 1.011740011 .
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationTest2() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		data.setPortfolios(new ArrayList<>());
		Instrument instrument = data.getInstruments().get(0);
		TradableEntitySnapshot tes = instrument.getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		data.setReportDate(formatter.parse("06/03/2016"));
		instrument.setFinalMaturityDate(formatter.parse("02/15/2045"));
		instrument.setMaturityPrc(utility.getBigDecimalWithScale7(new BigDecimal(100)));

		tes.setDerYieldCalcEngineCode(ytmYieldCalculationEngine.getEngineCode());
		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(98.3082779)));
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.0075)));
		tes.setFdrTipsInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.011740011)));

		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(0.00861600697373704)));
	}

	/**
	 * Yield calculation test r = 0.00625 market price = 104.7185855 maturity date = 01/15/2026 report date = 06/03/2016
	 * rv = 100 TIPSInflationaryRatio = 1.00267030 .
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationTest3() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		data.setPortfolios(new ArrayList<>());
		Instrument instrument = data.getInstruments().get(0);
		TradableEntitySnapshot tes = instrument.getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		data.setReportDate(formatter.parse("06/03/2016"));
		instrument.setFinalMaturityDate(formatter.parse("01/15/2026"));
		instrument.setMaturityPrc(utility.getBigDecimalWithScale7(new BigDecimal(100)));

		tes.setDerYieldCalcEngineCode(ytmYieldCalculationEngine.getEngineCode());
		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(104.7185855)));
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.00625)));
		tes.setFdrTipsInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.00267030)));

		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(0.001595912)));
	}

	/**
	 * Yield calculation test when yield < 0 .
	 * r = 0.02375
	 * market price = 125.4284756 maturity date = 01/15/2017 
	 * report date = 12/01/2014, rv = 100 
	 * TIPSInflationaryRatio = 1.18024
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkYieldCalculationNegativeTest1() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		data.setPortfolios(new ArrayList<>());
		Instrument instrument = data.getInstruments().get(0);
		TradableEntitySnapshot tes = instrument.getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		data.setReportDate(formatter.parse("12/01/2014"));
		instrument.setFinalMaturityDate(formatter.parse("01/15/2017"));
		instrument.setMaturityPrc(utility.getBigDecimalWithScale7(new BigDecimal(100)));

		tes.setDerYieldCalcEngineCode(ytmYieldCalculationEngine.getEngineCode());
		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(125.4284756)));
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.02375)));
		tes.setFdrTipsInflationaryRatio(utility.getBigDecimalWithScale7(new BigDecimal(1.18024)));

		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(-0.0055940)));
	}

	/**
	 * derTIPSInflationaryRatio calculation test on normal scenario.
	 *
	 * positionValInflationAdjShares = 50438.66
	 * shareParAmount = 45
	 *
	 * @throws Exception
	 */
	@Test
	public void checkDerTIPSInflationaryRatioTest() throws Exception {
		FundAccountingYieldData data = utility.constructFAYAData();
		Instrument instrument = data.getInstruments().get(0);
		TradableEntitySnapshot tes = instrument.getTradableEntities().get(0).getTradableEntitySnapshots().get(0);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		data.setReportDate(formatter.parse("06/03/2016"));
		instrument.setFinalMaturityDate(formatter.parse("02/15/2044"));
		instrument.setMaturityPrc(utility.getBigDecimalWithScale7(new BigDecimal(100)));

		tes.setDerYieldCalcEngineCode(ytmYieldCalculationEngine.getEngineCode());
		tes.setMarketPrice(utility.getBigDecimalWithScale7(new BigDecimal(114.7389035)));
		tes.setCurrentIncomeRate(utility.getBigDecimalWithScale7(new BigDecimal(0.01375)));

		PortfolioHoldingSnapshot holding = data.getPortfolios().get(0).getPortfolioHoldings().get(0);
		holding.setSettleShareCnt(new BigDecimal("45"));
		holding.setInflationAdjShareCnt(new BigDecimal("50438.66"));

		SECConfiguration configuration = new SECConfiguration();
		ytmYieldCalculationEngine.calculate(data, configuration);
		assertEquals(tes.getFdrTipsInflationaryRatio().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(1120.859111111111)));
		assertEquals(tes.getDerOneDaySecurityYield().setScale(7, BigDecimal.ROUND_HALF_DOWN),
				utility.getBigDecimalWithScale7(new BigDecimal(0.0200000)));
	}

}
