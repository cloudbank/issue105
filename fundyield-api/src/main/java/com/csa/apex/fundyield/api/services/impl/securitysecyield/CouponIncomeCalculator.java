/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The Coupon Income calculator.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class CouponIncomeCalculator {

	/**
	 * Empty constructor.
	 */
	public CouponIncomeCalculator() {
		// Empty
	}

	/**
	 * Calculates the Coupon Income.
	 *
	 * @param input the calculation input
	 * @return calculated result
	 * @throws IllegalArgumentException in case any error during calculation
	 */
	public CouponIncomeCalculationOutput calculate(CouponIncomeCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				"Parameter CouponIncomeCalculationInput");

		BigDecimal rightSide = input.getSettledShareCount();
		rightSide = rightSide.multiply(input.getDerOneDaySecurityYield());
		rightSide = rightSide.divide(input.getFxRate(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		rightSide = rightSide.divide(new BigDecimal(360), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);

		BigDecimal income = input.getEarnedAmortBaseAmount();
		income = income.divide(input.getFxRate(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		income = income.add(rightSide);
		income = income.setScale(input.getOperationScale(), input.getRoundingMode());

		CouponIncomeCalculationOutput output = new CouponIncomeCalculationOutput();
		output.setDerSecYield1DayIncomeAmt(income);
		return output;
	}
}
