/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

/**
 * The YTM Income calculation input.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class YtmIncomeCalculationInput extends BaseCalculationInput {

	/**
	 * Yield amount.
	 */
	private BigDecimal derOneDaySecurityYield;

	/**
	 * Accrued Income
	 */
	private BigDecimal accruedIncomeAmount;

	/**
	 * Earned Inflation Income
	 */
	private BigDecimal earnedInflCmpsBaseAmount;

	/**
	 * Current FX Rate
	 */
	private BigDecimal fxRate;

	/**
	 * Market value.
	 */
	private BigDecimal marketValueBaseAmount;

	/**
	 * The Y/FX threshold for the income calculation.
	 */
	private double yFxThreshold;

	/**
	 * Constructor.
	 * 
	 * @param configuration
	 *            The SEC configuration
	 */
	public YtmIncomeCalculationInput(SECConfiguration configuration) {
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
	 * Getter method for property <tt>accruedIncomeAmount</tt>.
	 * 
	 * @return property value of accruedIncomeAmount
	 */
	public BigDecimal getAccruedIncomeAmount() {
		return accruedIncomeAmount;
	}

	/**
	 * Setter method for property <tt>accruedIncomeAmount</tt>.
	 * 
	 * @param accruedIncomeAmount
	 *            value to be assigned to property accruedIncomeAmount
	 */
	public void setAccruedIncomeAmount(BigDecimal accruedIncomeAmount) {
		this.accruedIncomeAmount = accruedIncomeAmount;
	}

	/**
	 * Getter method for property <tt>earnedInflCmpsBaseAmount</tt>.
	 * 
	 * @return property value of earnedInflCmpsBaseAmount
	 */
	public BigDecimal getEarnedInflCmpsBaseAmount() {
		return earnedInflCmpsBaseAmount;
	}

	/**
	 * Setter method for property <tt>earnedInflCmpsBaseAmount</tt>.
	 * 
	 * @param earnedInflCmpsBaseAmount
	 *            value to be assigned to property earnedInflCmpsBaseAmount
	 */
	public void setEarnedInflCmpsBaseAmount(BigDecimal earnedInflCmpsBaseAmount) {
		this.earnedInflCmpsBaseAmount = earnedInflCmpsBaseAmount;
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

	/**
	 * Getter method for property <tt>marketValueBaseAmount</tt>.
	 * 
	 * @return property value of marketValueBaseAmount
	 */
	public BigDecimal getMarketValueBaseAmount() {
		return marketValueBaseAmount;
	}

	/**
	 * Setter method for property <tt>marketValueBaseAmount</tt>.
	 * 
	 * @param marketValueBaseAmount
	 *            value to be assigned to property marketValueBaseAmount
	 */
	public void setMarketValueBaseAmount(BigDecimal marketValueBaseAmount) {
		this.marketValueBaseAmount = marketValueBaseAmount;
	}

	/**
	 * Getter method for property <tt>yFxThreshold</tt>.
	 * 
	 * @return property value of yFxThreshold
	 */
	public double getyFxThreshold() {
		return yFxThreshold;
	}

	/**
	 * Setter method for property <tt>yFxThreshold</tt>.
	 * 
	 * @param yFxThreshold
	 *            value to be assigned to property yFxThreshold
	 */
	public void setyFxThreshold(double yFxThreshold) {
		this.yFxThreshold = yFxThreshold;
	}
}
