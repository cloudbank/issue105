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
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineSubType;
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineType;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Instrument;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;

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
		CommonUtility.checkStringConfig(engineCode, "engineCode");
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
	 * @param data The FundAccountingYieldData to calculate
	 * @param instrument The Instrument to calculate
	 * @param tes The TradableEntitySnapshot to calculate
	 * @param configuration The SECConfiguration to be used for config values
	 */
	@Override
	protected void doCalculate(FundAccountingYieldData data, Instrument instrument, TradableEntitySnapshot tes,
			SECConfiguration configuration) {

		if (tes.getMarketPrice() != null && tes.getFdrTipsInflationaryRatio() != null) {
			BigDecimal cleanPrice = tes.getMarketPrice()
					.divide(tes.getFdrTipsInflationaryRatio(), configuration.getOperationScale(), BigDecimal.ROUND_HALF_UP);
			tes.setFdrCleanPrice(cleanPrice);
		}
		CouponYieldCalculationInput input = new CouponYieldCalculationInput(configuration);
		input.setCurrentIncomeRate(tes.getCurrentIncomeRate());
		CouponYieldCalculationOutput output = calculator.calculate(input);
	    tes.setDerOneDaySecurityYield(output.getDerOneDaySecurityYield());
	}
}
