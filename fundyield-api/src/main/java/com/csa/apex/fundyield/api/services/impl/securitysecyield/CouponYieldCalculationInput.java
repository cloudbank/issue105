/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;

/**
 * The Coupon Yield calculation input.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class CouponYieldCalculationInput extends BaseCalculationInput {

	/**
	 * Current income rate.
	 */
	private BigDecimal currentIncomeRate;

	/**
	 * Constructor.
	 * 
	 * @param configuration
	 *            The SEC configuration
	 */
	public CouponYieldCalculationInput(SECConfiguration configuration) {
		super(configuration);
	}

	/**
	 * Getter method for property <tt>currentIncomeRate</tt>.
	 * 
	 * @return property value of currentIncomeRate
	 */
	public BigDecimal getCurrentIncomeRate() {
		return currentIncomeRate;
	}

	/**
	 * Setter method for property <tt>currentIncomeRate</tt>.
	 * 
	 * @param currentIncomeRate
	 *            value to be assigned to property currentIncomeRate
	 */
	public void setCurrentIncomeRate(BigDecimal currentIncomeRate) {
		this.currentIncomeRate = currentIncomeRate;
	}

}
