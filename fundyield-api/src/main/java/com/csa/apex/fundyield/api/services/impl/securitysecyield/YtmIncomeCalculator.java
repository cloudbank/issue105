/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

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
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				"Parameter YtmIncomeCalculationInput");

		BigDecimal income;
		BigDecimal condition1 = input.getDerOneDaySecurityYield();
		condition1 = condition1.divide(input.getFxRate(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		BigDecimal condition2 = BigDecimal.valueOf(input.getyFxThreshold()).setScale(input.getOperationScale(),
				BigDecimal.ROUND_HALF_UP);

		BigDecimal earnedInflCmpsBaseAmount = input.getEarnedInflCmpsBaseAmount();
		
		if (condition1.compareTo(condition2) > 0) {
			income = input.getMarketValueBaseAmount();
			income = income.add(input.getAccruedIncomeAmount());
			income = income.multiply(BigDecimal.valueOf(input.getyFxThreshold()).setScale(input.getOperationScale(),
					BigDecimal.ROUND_HALF_UP));
			income = income.divide(new BigDecimal(360), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		} else {
			income = input.getMarketValueBaseAmount();
			income = income.add(input.getAccruedIncomeAmount());
			income = income.multiply(input.getDerOneDaySecurityYield());
			income = income.divide(
					BigDecimal.valueOf(360).setScale(input.getOperationScale(), BigDecimal.ROUND_HALF_UP),
					BigDecimal.ROUND_HALF_UP);
			income = income.divide(input.getFxRate(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		}
		income = income.add(earnedInflCmpsBaseAmount);

		// round off income to operation scale
		income = income.setScale(input.getOperationScale(), input.getRoundingMode());
		YtmIncomeCalculationOutput output = new YtmIncomeCalculationOutput();
		output.setDerSecYield1DayIncomeAmt(income);
		return output;
	}
}
