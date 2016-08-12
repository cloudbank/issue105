package com.csa.apex.secyield.entities;

import org.springframework.stereotype.Component;

/**
 * 
 * SECConfiguration
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 *
 */
@Component
public class SECConfiguration {
	/**
	 * Operation scale
	 */
	private int operationScale;
	
	/**
	 * Rounding mode
	 */
	private int roundingMode;

	/**
	 * Constructor
	 */
	public SECConfiguration()
	{
		// default empty constructor
	}
	
	/**
	 * Getter operationScale
	 * @return operationScale
	 */
	public final int getOperationScale() {
		return operationScale;
	}

	/**
	 * Setter operationScale
	 * @param operationScale
	 */
	public final void setOperationScale(int operationScale) {
		this.operationScale = operationScale;
	}

	/**
	 * Getter roundingMode
	 * @return roundingMode
	 */
	public final int getRoundingMode() {
		return roundingMode;
	}

	/**
	 * Setter roundingMode
	 * @param roundingMode
	 */
	public final void setRoundingMode(int roundingMode) {
		this.roundingMode = roundingMode;
	}
	
	
}
