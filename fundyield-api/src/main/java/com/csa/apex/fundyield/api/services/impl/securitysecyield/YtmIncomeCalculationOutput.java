/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

/**
 * The YTM Income calculation output.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class YtmIncomeCalculationOutput {

	/**
	 * Income amount.
	 */
	private BigDecimal derSecYield1DayIncomeAmt;

	/**
	 * Empty constructor.
	 */
	public YtmIncomeCalculationOutput() {
		// Empty
	}

	/**
	 * Getter method for property <tt>derSecYield1DayIncomeAmt</tt>.
	 * 
	 * @return property value of derSecYield1DayIncomeAmt
	 */
	public BigDecimal getDerSecYield1DayIncomeAmt() {
		return derSecYield1DayIncomeAmt;
	}

	/**
	 * Setter method for property <tt>derSecYield1DayIncomeAmt</tt>.
	 * 
	 * @param derSecYield1DayIncomeAmt
	 *            value to be assigned to property derSecYield1DayIncomeAmt
	 */
	public void setDerSecYield1DayIncomeAmt(BigDecimal derSecYield1DayIncomeAmt) {
		this.derSecYield1DayIncomeAmt = derSecYield1DayIncomeAmt;
	}

}
