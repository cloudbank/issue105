/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;

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
    private BigDecimal y;

    /**
     * Accrued Income
     */
    private BigDecimal ai;

    /**
     * Earned Inflation Income
     */
    private BigDecimal inflInc;

    /**
     * Current FX Rate
     */
    private BigDecimal fx;

    /**
     * Market value.
     */
    private BigDecimal mv;

    /**
     * The Y/FX threshold for the income calculation.
     */
    private double yFxThreshold;

	/**
	 * Constructor.
	 * @param configuration The SEC configuration
	 */
	public YtmIncomeCalculationInput(SECConfiguration configuration) {
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
	 * Getter method for property <tt>ai</tt>.
	 * @return property value of ai
	 */
	public BigDecimal getAi() {
		return ai;
	}

	/**
	 * Setter method for property <tt>ai</tt>.
	 * @param ai value to be assigned to property ai
	 */
	public void setAi(BigDecimal ai) {
		this.ai = ai;
	}

	/**
	 * Getter method for property <tt>inflInc</tt>.
	 * @return property value of inflInc
	 */
	public BigDecimal getInflInc() {
		return inflInc;
	}

	/**
	 * Setter method for property <tt>inflInc</tt>.
	 * @param inflInc value to be assigned to property inflInc
	 */
	public void setInflInc(BigDecimal inflInc) {
		this.inflInc = inflInc;
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

	/**
	 * Getter method for property <tt>mv</tt>.
	 * @return property value of mv
	 */
	public BigDecimal getMv() {
		return mv;
	}

	/**
	 * Setter method for property <tt>mv</tt>.
	 * @param mv value to be assigned to property mv
	 */
	public void setMv(BigDecimal mv) {
		this.mv = mv;
	}

	/**
	 * Getter method for property <tt>yFxThreshold</tt>.
	 * @return property value of yFxThreshold
	 */
	public double getyFxThreshold() {
		return yFxThreshold;
	}

	/**
	 * Setter method for property <tt>yFxThreshold</tt>.
	 * @param yFxThreshold value to be assigned to property yFxThreshold
	 */
	public void setyFxThreshold(double yFxThreshold) {
		this.yFxThreshold = yFxThreshold;
	}
}

