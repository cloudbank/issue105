/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.fayacommons.entities.CalculationEngineSubType;
import com.csa.apex.fundyield.fayacommons.entities.CalculationEngineType;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The Coupon Yield calculation engine.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class CouponYieldCalculationEngine extends BaseCalculationEngine {

	/**
	 * The calculator.
	 */
	@Autowired
	private CouponYieldCalculator calculator;

	/**
	 * Engine code.
	 */
	@Value("${Engine.coupon_yield}")
	private String engineCode;

	/**
	 * Constructor
	 */
	public CouponYieldCalculationEngine() {
		super(CalculationEngineType.COUPON, CalculationEngineSubType.YIELD);
	}

	/**
	 * Checks the configuration.
	 *
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkStringConfig(engineCode, this.getClass().getCanonicalName(), "engineCode");
		CommonUtility.checkNullConfig(calculator, this.getClass().getCanonicalName(), "calculator");
	}

	/**
	 * Get engine code.
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
	 * @param tradableEntitySnapshot The TradableEntitySnapshot to calculate
	 * @param configuration The SECConfiguration to be used for config values
	 */
	@Override
	protected void doCalculate(FundAccountingYieldData fundAccountingYieldData, Instrument instrument,
			TradableEntitySnapshot tradableEntitySnapshot, SECConfiguration configuration) {
		CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(),
				Constants.METHOD_DO_CALCULATE, Constants.FUND_ACCOUNTING_YIELD_DATA);
		CommonUtility.checkNull(configuration, this.getClass().getCanonicalName(), Constants.METHOD_DO_CALCULATE,
				Constants.PARAMETER_CONFIGURATION);

		if (tradableEntitySnapshot.getMarketPrice() != null
				&& tradableEntitySnapshot.getFdrTipsInflationaryRatio() != null) {
			BigDecimal cleanPrice = tradableEntitySnapshot.getMarketPrice().divide(
					tradableEntitySnapshot.getFdrTipsInflationaryRatio(), configuration.getOperationScale(),
					BigDecimal.ROUND_HALF_UP);
			tradableEntitySnapshot.setFdrCleanPrice(cleanPrice);
		}
		CouponYieldCalculationInput input = new CouponYieldCalculationInput(configuration);
		input.setCurrentIncomeRate(tradableEntitySnapshot.getCurrentIncomeRate());
		CouponYieldCalculationOutput output = calculator.calculate(input);
		tradableEntitySnapshot.setDerOneDaySecurityYield(output.getDerOneDaySecurityYield());
	}
}
