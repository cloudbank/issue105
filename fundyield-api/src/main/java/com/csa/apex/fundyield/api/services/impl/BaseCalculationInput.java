/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl;

import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

/**
 * The base calculation input.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public abstract class BaseCalculationInput {

	/**
	 * Operation scale.
	 */
	private int operationScale;

	/**
	 * Rounding mode.
	 */
	private int roundingMode;

	/**
	 * Constructor.
	 * @param configuration The SEC configuration
	 */
	protected BaseCalculationInput(SECConfiguration configuration) {
		if (configuration == null)
			return;

		this.operationScale = configuration.getOperationScale();
		this.roundingMode = configuration.getRoundingMode();
	}

	/**
	 * Getter method for property <tt>operationScale</tt>.
	 * @return property value of operationScale
	 */
	public int getOperationScale() {
		return operationScale;
	}

	/**
	 * Setter method for property <tt>operationScale</tt>.
	 * @param operationScale value to be assigned to property operationScale
	 */
	public void setOperationScale(int operationScale) {
		this.operationScale = operationScale;
	}

	/**
	 * Getter method for property <tt>roundingMode</tt>.
	 * @return property value of roundingMode
	 */
	public int getRoundingMode() {
		return roundingMode;
	}

	/**
	 * Setter method for property <tt>roundingMode</tt>.
	 * @param roundingMode value to be assigned to property roundingMode
	 */
	public void setRoundingMode(int roundingMode) {
		this.roundingMode = roundingMode;
	}
}
