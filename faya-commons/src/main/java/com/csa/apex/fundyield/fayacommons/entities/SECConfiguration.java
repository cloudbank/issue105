/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * 
 * SECConfiguration
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
public class SECConfiguration {
	/**
	 * Operation scale.
	 */
	private int operationScale;

	/**
	 * Rounding mode.
	 */
	private int roundingMode = -1;

	/**
	 * Constructor
	 */
	public SECConfiguration() {
		// default empty constructor
	}

	/**
	 * Getter operationScale.
	 * 
	 * @return operationScale
	 */
	public final int getOperationScale() {
		return operationScale;
	}

	/**
	 * Setter operationScale.
	 * 
	 * @param operationScale
	 */
	public final void setOperationScale(int operationScale) {
		this.operationScale = operationScale;
	}

	/**
	 * Getter roundingMode.
	 * 
	 * @return roundingMode
	 */
	public final int getRoundingMode() {
		return roundingMode;
	}

	/**
	 * Setter roundingMode.
	 * 
	 * @param roundingMode
	 */
	public final void setRoundingMode(int roundingMode) {
		this.roundingMode = roundingMode;
	}

	/**
	 * Test whether given object equals this object.
	 * 
	 * @return true if given object equals this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		return CommonUtility.getHashText(this).equals(CommonUtility.getHashText(obj));
	}

	/**
	 * Get hash code.
	 * 
	 * @return hash code.
	 */
	@Override
	public int hashCode() {
		return CommonUtility.getHashText(this).hashCode();
	}

	/**
	 * Get string representation of this object.
	 * 
	 * @return string representation of this object.
	 */
	@Override
	public String toString() {
		return CommonUtility.toString(this);
	}
}
