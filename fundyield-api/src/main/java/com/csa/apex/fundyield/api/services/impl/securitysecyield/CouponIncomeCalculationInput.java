/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;

/**
 * The Coupon Income calculation input.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class CouponIncomeCalculationInput extends BaseCalculationInput {

	/**
	 * Yield amount.
	 */
	private BigDecimal derOneDaySecurityYield;

	/**
	 * Daily Earned Amort amount.
	 */
	private BigDecimal earnedAmortBaseAmount;

	/**
	 * Shares amount.
	 */
	private BigDecimal settledShareCount;

	/**
	 * Current FX Rate.
	 */
	private BigDecimal fxRate;

	/**
	 * Constructor.
	 * 
	 * @param configuration
	 *            The SEC configuration
	 */
	public CouponIncomeCalculationInput(SECConfiguration configuration) {
		super(configuration);
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
	 * Getter method for property <tt>earnedAmortBaseAmount</tt>.
	 * 
	 * @return property value of earnedAmortBaseAmount
	 */
	public BigDecimal getEarnedAmortBaseAmount() {
		return earnedAmortBaseAmount;
	}

	/**
	 * Setter method for property <tt>earnedAmortBaseAmount</tt>.
	 * 
	 * @param earnedAmortBaseAmount
	 *            value to be assigned to property earnedAmortBaseAmount
	 */
	public void setEarnedAmortBaseAmount(BigDecimal earnedAmortBaseAmount) {
		this.earnedAmortBaseAmount = earnedAmortBaseAmount;
	}

	/**
	 * Getter method for property <tt>settledShareCount</tt>.
	 * 
	 * @return property value of settledShareCount
	 */
	public BigDecimal getSettledShareCount() {
		return settledShareCount;
	}

	/**
	 * Setter method for property <tt>settledShareCount</tt>.
	 * 
	 * @param settledShareCount
	 *            value to be assigned to property settledShareCount
	 */
	public void setSettledShareCount(BigDecimal settledShareCount) {
		this.settledShareCount = settledShareCount;
	}

	/**
	 * Getter method for property <tt>fxRate</tt>.
	 * 
	 * @return property value of fxRate
	 */
	public BigDecimal getFxRate() {
		return fxRate;
	}

	/**
	 * Setter method for property <tt>fxRate</tt>.
	 * 
	 * @param fxRate
	 *            value to be assigned to property fxRate
	 */
	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}

}
