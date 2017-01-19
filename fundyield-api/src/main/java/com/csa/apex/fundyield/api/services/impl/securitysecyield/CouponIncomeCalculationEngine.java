/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
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
 * The Coupon Income calculation engine.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class CouponIncomeCalculationEngine extends BaseCalculationEngine {

	/**
	 * Logger class instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(CouponIncomeCalculationEngine.class);

	/**
	 * The calculator.
	 */
	@Autowired
	private CouponIncomeCalculator calculator;

	/**
	 * Engine code.
	 */
	@Value("${Engine.coupon_income}")
	private String engineCode;

	/**
	 * Constructor
	 */
	public CouponIncomeCalculationEngine() {
		super(CalculationEngineType.COUPON, CalculationEngineSubType.INCOME);
	}

	/**
	 * Checks the configuration.
	 *
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(calculator, "CouponIncomeCalculator");
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

		CouponIncomeCalculationInput input = new CouponIncomeCalculationInput(configuration);

		input.setDerOneDaySecurityYield(tes.getDerOneDaySecurityYield());

		for (PortfolioHoldingSnapshot holding : CommonUtility
				.getRelatedPortfolioHoldings(data, tes.getTradableEntitySid())) {
			input.setFxRate(holding.getFxRt());
			input.setEarnedAmortBaseAmount(holding.getEarnedAmortBaseAmt());
			input.setSettledShareCount(holding.getSettleShareCnt());

			if (input.getEarnedAmortBaseAmount() == null || input.getSettledShareCount() == null) {
				LOGGER.info(String.format("CouponIncomeCalculationEngine.calculate will be escapsed, earnedAmortBaseAmount:%s, settleShareCnt:%s",
						input.getEarnedAmortBaseAmount(), input.getSettledShareCount()));
				continue;
			}

			// calculate
			CouponIncomeCalculationOutput output = calculator.calculate(input);

			holding.setDerSecYield1DayIncomeAmt(output.getDerSecYield1DayIncomeAmt());
		}
	}
}
