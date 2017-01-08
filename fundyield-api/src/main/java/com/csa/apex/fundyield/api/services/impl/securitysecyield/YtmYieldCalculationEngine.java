/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;
import java.util.List;

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
 * The YTM (TIPS) Yield calculation engine.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class YtmYieldCalculationEngine extends BaseCalculationEngine {

	/**
	 * The calculator.
	 */
	@Autowired
	private YtmYieldCalculator calculator;

	/**
	 * Engine code.
	 */
	@Value("${Engine.ytm_yield}")
	private String engineCode;

	/**
	 * The frequency value used in calculation. Has the default value.
	 */
	private int frequencyValue = 2;

	/**
	 * The num of days in period used for calculation. Has the default value.
	 */
	private int numOfDaysInPeriod = 180;

	/**
	 * Min yield when it is negative.
	 */
	@Value("${YtmYieldCalculationEngine.minYield}")
	private double minYield = -0.02;

	/**
	 * Max yield when it is negative.
	 */
	@Value("${YtmYieldCalculationEngine.maxYield}")
	private double maxYield = 0.02;

	/**
	 * It is the step to increase the beginning yield in order to find the range
	 * of the yield.
	 */
	@Value("${YtmYieldCalculationEngine.calculationStep}")
	private double calculationStep = 0.000000001;

	/**
	 * It is count to perform binary search. The larger value will result in
	 * more precise outcome yield.
	 */
	@Value("${YtmYieldCalculationEngine.binarySearchCount}")
	private int binarySearchCount = 100;

	/**
	 * Constructor
	 */
	public YtmYieldCalculationEngine() {
		super(CalculationEngineType.YTM, CalculationEngineSubType.YIELD);
	}

	/**
	 * Checks the configuration.
	 *
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(calculator, "YtmYieldCalculator");
		CommonUtility.checkStringConfig(engineCode, "engineCode");

		if (minYield > maxYield) {
			throw new ConfigurationException("minYield can not be greater than maxYield");
		}
		if (maxYield <= 0) {
			throw new ConfigurationException("maxYield must be positive");
		}
		if (calculationStep <= 0) {
			throw new ConfigurationException("calculationStep must be positive");
		}
		if (binarySearchCount <= 0) {
			throw new ConfigurationException("binarySearchCount must be positive");
		}
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

		YtmYieldCalculationInput input = new YtmYieldCalculationInput(configuration);

		// Calculate derTIPSInflationaryRatio
		List<PortfolioHoldingSnapshot> holdings = CommonUtility.getRelatedPortfolioHoldings(data,
				tes.getTradableEntitySid());
		if (holdings != null && !holdings.isEmpty()) {
			PortfolioHoldingSnapshot holding = holdings.get(0);
			BigDecimal derTIPSInflationaryRatio = holding.getInflationAdjShareCnt().divide(holding.getSettleShareCnt(),
					input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
			tes.setFdrTipsInflationaryRatio(derTIPSInflationaryRatio);
		}

		input.setFrequencyValue(frequencyValue);
		input.setCalculationStep(calculationStep);
		input.setBinarySearchCount(binarySearchCount);
		input.setNumOfDaysInPeriod(numOfDaysInPeriod);
		input.setMinYield(minYield);
		input.setMaxYield(maxYield);
		input.setMp(tes.getMarketPrice());
		input.setCir(tes.getCurrentIncomeRate());
		input.setIir(tes.getFdrTipsInflationaryRatio());
		input.setMaturityPrice(instrument.getMaturityPrc());
		input.setMaturityDate(instrument.getFinalMaturityDate());
		input.setReportDate(data.getReportDate());

		// calculate
		YtmYieldCalculationOutput output = calculator.calculate(input);

		tes.setDerOneDaySecurityYield(output.getY());
		tes.setFdrCleanPrice(output.getP());
		tes.setDerRedemptionPrice(output.getRv());
	}
}
