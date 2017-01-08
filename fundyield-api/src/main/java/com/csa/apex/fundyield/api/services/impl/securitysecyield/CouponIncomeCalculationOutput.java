/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

/**
 * The Coupon Income calculation output.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class CouponIncomeCalculationOutput {

	/**
     * Income amount
     */
	private BigDecimal i;

	/**
	 * Empty constructor.
	 */
	public CouponIncomeCalculationOutput() {
		// Empty
	}

	/**
	 * Getter method for property <tt>i</tt>.
	 * 
	 * @return property value of i
	 */
	public BigDecimal getI() {
		return i;
	}

	/**
	 * Setter method for property <tt>i</tt>.
	 * 
	 * @param i
	 *            value to be assigned to property i
	 */
	public void setI(BigDecimal i) {
		this.i = i;
	}
}

