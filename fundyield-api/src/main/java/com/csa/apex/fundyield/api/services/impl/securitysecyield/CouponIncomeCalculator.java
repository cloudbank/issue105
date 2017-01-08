/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.utility.CommonUtility;

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
		CommonUtility.checkNull(input, "Parameter CouponIncomeCalculationInput");

		BigDecimal rightSide = input.getSh().multiply(input.getY())
				.divide(input.getFx(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP)
				.divide(new BigDecimal(360), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		BigDecimal income = input.getAm().divide(input.getFx(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP)
				.add(rightSide);
		income = income.setScale(input.getOperationScale(), input.getRoundingMode());

		CouponIncomeCalculationOutput output = new CouponIncomeCalculationOutput();
		output.setI(income);
		return output;
    }
}
