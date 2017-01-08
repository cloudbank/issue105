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
	private BigDecimal p;

	/**
	 * The yield amount
	 */
	private BigDecimal y;

	/**
	 * Redemption value.
	 */
	private BigDecimal rv;

	/**
	 * Empty constructor.
	 */
	public YtmYieldCalculationOutput() {
		// Empty
	}

	/**
	 * Getter method for property <tt>p</tt>.
	 * 
	 * @return property value of p
	 */
	public BigDecimal getP() {
		return p;
	}

	/**
	 * Setter method for property <tt>p</tt>.
	 * 
	 * @param p
	 *            value to be assigned to property p
	 */
	public void setP(BigDecimal p) {
		this.p = p;
	}

	/**
	 * Getter method for property <tt>y</tt>.
	 * 
	 * @return property value of y
	 */
	public BigDecimal getY() {
		return y;
	}

	/**
	 * Setter method for property <tt>y</tt>.
	 * 
	 * @param y
	 *            value to be assigned to property y
	 */
	public void setY(BigDecimal y) {
		this.y = y;
	}

	/**
	 * Getter method for property <tt>rv</tt>.
	 * 
	 * @return property value of rv
	 */
	public BigDecimal getRv() {
		return rv;
	}

	/**
	 * Setter method for property <tt>rv</tt>.
	 * 
	 * @param rv
	 *            value to be assigned to property rv
	 */
	public void setRv(BigDecimal rv) {
		this.rv = rv;
	}
}
