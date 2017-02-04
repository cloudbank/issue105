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
public class CouponYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public CouponYieldCalculator() {
		// Empty
	}

	/**
	 * Calculates the Coupon Income.
	 *
	 * @param input
	 *            the calculation input
	 * @return calculated result
	 * @throws IllegalArgumentException
	 *             in case any error during calculation
	 */
	public CouponYieldCalculationOutput calculate(CouponYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, "Parameter CouponYieldCalculationInput");

		CouponYieldCalculationOutput output = new CouponYieldCalculationOutput();
		BigDecimal yield = input.getCurrentIncomeRate().setScale(input.getOperationScale(), input.getRoundingMode());
		output.setDerOneDaySecurityYield(yield);
		return output;
	}

}
