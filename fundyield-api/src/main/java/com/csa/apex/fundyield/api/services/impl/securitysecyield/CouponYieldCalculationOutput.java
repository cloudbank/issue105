/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

/**
 * The Coupon Yield calculation output.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class CouponYieldCalculationOutput {

	/**
	 * The yield amount.
	 */
	private BigDecimal derOneDaySecurityYield;

	/**
	 * Empty constructor.
	 */
	public CouponYieldCalculationOutput() {
		// Empty
	}

	/**
	 * Getter method for property <tt>derOneDaySecurityYield</tt>.
	 * 
	 * @return property value of derOneDaySecurityYield
	 */
	public BigDecimal getDerOneDaySecurityYield() {
		return derOneDaySecurityYield;
	}

	/**
	 * Setter method for property <tt>derOneDaySecurityYield</tt>.
	 * 
	 * @param derOneDaySecurityYield
	 *            value to be assigned to property derOneDaySecurityYield
	 */
	public void setDerOneDaySecurityYield(BigDecimal derOneDaySecurityYield) {
		this.derOneDaySecurityYield = derOneDaySecurityYield;
	}
}
