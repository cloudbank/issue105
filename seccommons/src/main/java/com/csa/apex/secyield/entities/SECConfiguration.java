/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

/**
 * 
 * SECConfiguration
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
 *
 */
@Component
public class SECConfiguration {
	/**
	 * Operation scale.
	 */
	private int operationScale;

	/**
	 * Rounding mode.
	 */
	private int roundingMode=-1;

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

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SECConfiguration)) {
			return false;
		}
		SECConfiguration castOther = (SECConfiguration) other;
		return new EqualsBuilder().append(operationScale, castOther.operationScale)
				.append(roundingMode, castOther.roundingMode).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(operationScale).append(roundingMode).toHashCode();
	}

	@Override
	public String toString() {
		return "SECConfiguration{" +
				"operationScale=" + operationScale +
				", roundingMode=" + roundingMode +
				'}';
	}
}
