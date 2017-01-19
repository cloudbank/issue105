/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

/**
 * The YTM Yield calculation output.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class YtmYieldCalculationOutput {

	/**
	 * Clean price.
	 */
	private BigDecimal fdrCleanPrice;

	/**
	 * The yield amount.
	 */
	private BigDecimal derOneDaySecurityYield;

	/**
	 * Redemption value.
	 */
	private BigDecimal derRedemptionPrice;

	/**
	 * Empty constructor.
	 */
	public YtmYieldCalculationOutput() {
		// Empty
	}

	/**
	 * Getter method for property <tt>fdrCleanPrice</tt>.
	 * 
	 * @return property value of fdrCleanPrice
	 */
	public BigDecimal getFdrCleanPrice() {
		return fdrCleanPrice;
	}

	/**
	 * Setter method for property <tt>fdrCleanPrice</tt>.
	 * 
	 * @param fdrCleanPrice
	 *            value to be assigned to property fdrCleanPrice
	 */
	public void setFdrCleanPrice(BigDecimal fdrCleanPrice) {
		this.fdrCleanPrice = fdrCleanPrice;
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

	/**
	 * Getter method for property <tt>derRedemptionPrice</tt>.
	 * 
	 * @return property value of derRedemptionPrice
	 */
	public BigDecimal getDerRedemptionPrice() {
		return derRedemptionPrice;
	}

	/**
	 * Setter method for property <tt>derRedemptionPrice</tt>.
	 * 
	 * @param derRedemptionPrice
	 *            value to be assigned to property derRedemptionPrice
	 */
	public void setDerRedemptionPrice(BigDecimal derRedemptionPrice) {
		this.derRedemptionPrice = derRedemptionPrice;
	}

}
