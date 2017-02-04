/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.fayacommons.entities.CalculationEngineSubType;
import com.csa.apex.fundyield.fayacommons.entities.CalculationEngineType;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

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
		CommonUtility.checkNullConfig(calculator, this.getClass().getCanonicalName(), "YtmIncomeCalculator");
		CommonUtility.checkStringConfig(engineCode, this.getClass().getCanonicalName(), "engineCode");
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
	 * @param fundAccountingYieldData The FundAccountingYieldData to calculate
	 * @param instrument The Instrument to calculate
	 * @param tes The TradableEntitySnapshot to calculate
	 * @param configuration The SECConfiguration to be used for config values
	 */
	@Override
	protected void doCalculate(FundAccountingYieldData fundAccountingYieldData, Instrument instrument,
			TradableEntitySnapshot tes, SECConfiguration configuration) {
		CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(),
				Constants.METHOD_DO_CALCULATE, Constants.FUND_ACCOUNTING_YIELD_DATA);
		CommonUtility.checkNull(configuration, this.getClass().getCanonicalName(), Constants.METHOD_DO_CALCULATE,
				Constants.PARAMETER_CONFIGURATION);

		YtmIncomeCalculationInput input = new YtmIncomeCalculationInput(configuration);

		input.setyFxThreshold(yFxThreshold);
		input.setDerOneDaySecurityYield(tes.getDerOneDaySecurityYield());

		for (PortfolioHoldingSnapshot holding : CommonUtility.getRelatedPortfolioHoldings(fundAccountingYieldData,
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
