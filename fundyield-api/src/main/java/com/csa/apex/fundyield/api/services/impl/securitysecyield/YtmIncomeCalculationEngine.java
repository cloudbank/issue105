/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineSubType;
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineType;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Instrument;
import com.csa.apex.fundyield.seccommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * The YTM (TIPS) Income calculation engine.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class YtmIncomeCalculationEngine extends BaseCalculationEngine {

	/**
	 * The calculator.
	 */
	@Autowired
	private YtmIncomeCalculator calculator;

	/**
	 * Engine code.
	 */
	@Value("${Engine.ytm_income}")
	private String engineCode;

	/**
	 * The Y/FX threshold for the income calculation.
	 */
	private double yFxThreshold = 0.2;

	/**
	 * Constructor
	 */
	public YtmIncomeCalculationEngine() {
		super(CalculationEngineType.YTM, CalculationEngineSubType.INCOME);
	}

	/**
	 * Checks the configuration.
	 *
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(calculator, "YtmIncomeCalculator");
		CommonUtility.checkStringConfig(engineCode, "engineCode");
	}

	/**
	 * Get engine code.
	 * 
	 * @return engine code
	 */
	@Override
	public String getEngineCode() {
		return engineCode;
	}

	/**
	 * Do calculation.
	 *
	 * @param data The FundAccountingYieldData to calculate
	 * @param instrument The Instrument to calculate
	 * @param tes The TradableEntitySnapshot to calculate
	 * @param configuration The SECConfiguration to be used for config values
	 */
	@Override
	protected void doCalculate(FundAccountingYieldData data, Instrument instrument, TradableEntitySnapshot tes,
			SECConfiguration configuration) {

		YtmIncomeCalculationInput input = new YtmIncomeCalculationInput(configuration);

		input.setyFxThreshold(yFxThreshold);
		input.setDerOneDaySecurityYield(tes.getDerOneDaySecurityYield());

		for (PortfolioHoldingSnapshot holding : CommonUtility.getRelatedPortfolioHoldings(data,
				tes.getTradableEntitySid())) {
			input.setFxRate(holding.getFxRt());
			input.setMarketValueBaseAmount(holding.getMarketValueBaseAmt());
			input.setAccruedIncomeAmount(holding.getAccruedIncomeAmt());
			input.setEarnedInflCmpsBaseAmount(holding.getEarnedInflCmpsBaseAmt());

			// calculate
			YtmIncomeCalculationOutput output = calculator.calculate(input);

			holding.setDerSecYield1DayIncomeAmt(output.getDerSecYield1DayIncomeAmt());
		}
	}
}
