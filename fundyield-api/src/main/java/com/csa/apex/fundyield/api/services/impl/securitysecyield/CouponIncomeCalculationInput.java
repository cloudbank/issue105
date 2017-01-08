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
    private BigDecimal y;

    /**
     * Daily Earned Amort
     */
    private BigDecimal am;

    /**
     * Shares amount.
     */
    private BigDecimal sh;

    /**
     * Current FX Rate
     */
    private BigDecimal fx;

	/**
	 * Constructor.
	 * @param configuration The SEC configuration
	 */
	public CouponIncomeCalculationInput(SECConfiguration configuration) {
		super(configuration);
	}

	/**
	 * Getter method for property <tt>y</tt>.
	 * @return property value of y
	 */
	public BigDecimal getY() {
		return y;
	}

	/**
	 * Setter method for property <tt>y</tt>.
	 * @param y value to be assigned to property y
	 */
	public void setY(BigDecimal y) {
		this.y = y;
	}

	/**
	 * Getter method for property <tt>am</tt>.
	 * @return property value of am
	 */
	public BigDecimal getAm() {
		return am;
	}

	/**
	 * Setter method for property <tt>am</tt>.
	 * @param am value to be assigned to property am
	 */
	public void setAm(BigDecimal am) {
		this.am = am;
	}

	/**
	 * Getter method for property <tt>sh</tt>.
	 * @return property value of sh
	 */
	public BigDecimal getSh() {
		return sh;
	}

	/**
	 * Setter method for property <tt>sh</tt>.
	 * @param sh value to be assigned to property sh
	 */
	public void setSh(BigDecimal sh) {
		this.sh = sh;
	}

	/**
	 * Getter method for property <tt>fx</tt>.
	 * @return property value of fx
	 */
	public BigDecimal getFx() {
		return fx;
	}

	/**
	 * Setter method for property <tt>fx</tt>.
	 * @param fx value to be assigned to property fx
	 */
	public void setFx(BigDecimal fx) {
		this.fx = fx;
	}
}

