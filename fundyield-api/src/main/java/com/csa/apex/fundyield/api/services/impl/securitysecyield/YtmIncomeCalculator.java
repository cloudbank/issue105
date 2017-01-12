/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * The YTM (TIPS) Income calculator.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class YtmIncomeCalculator {

	/**
	 * Empty constructor.
	 */
	public YtmIncomeCalculator() {
		// Empty
	}

	/**
	 * Calculates the YTM (TIPS) Income.
	 *
	 * @param input the calculation input
	 * @return calculated result
	 * @throws IllegalArgumentException in case any error during calculation
	 */
	public YtmIncomeCalculationOutput calculate(YtmIncomeCalculationInput input) {
		CommonUtility.checkNull(input, "Parameter YtmIncomeCalculationInput");

		BigDecimal income;
		if (input.getDerOneDaySecurityYield().divide(input.getFxRate(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal
				.valueOf(input.getyFxThreshold()).setScale(input.getOperationScale(), BigDecimal.ROUND_HALF_UP)) > 0)
			income = input.getMarketValueBaseAmount().add(input.getAccruedIncomeAmount())
					.multiply(BigDecimal.valueOf(input.getyFxThreshold()).setScale(input.getOperationScale(),
							BigDecimal.ROUND_HALF_UP))
					.divide(new BigDecimal(360), input.getOperationScale(), BigDecimal.ROUND_HALF_UP)
					.add(input.getEarnedInflCmpsBaseAmount());
		else
			income = input.getMarketValueBaseAmount().add(input.getAccruedIncomeAmount()).multiply(input.getDerOneDaySecurityYield())
					.divide(BigDecimal.valueOf(360).setScale(input.getOperationScale(), BigDecimal.ROUND_HALF_UP),
							BigDecimal.ROUND_HALF_UP)
					.divide(input.getFxRate(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP).add(input.getEarnedInflCmpsBaseAmount());

		// round off income to operation scale
		income = income.setScale(input.getOperationScale(), input.getRoundingMode());
		YtmIncomeCalculationOutput output = new YtmIncomeCalculationOutput();
		output.setDerSecYield1DayIncomeAmt(income);
		return output;
	}
}
