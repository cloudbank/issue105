/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.exceptions;

/**
 * The calculation exception.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class CalculationException extends SECYieldException {
	/**
	 * The default serial version uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with message.
	 * 
	 * @param message
	 *            the error message
	 */
	public CalculationException(String message) {
		super(message);
	}

	/**
	 * Constructor with message and cause.
	 * 
	 * @param message
	 *            the error message
	 * @param cause
	 *            the cause
	 */
	public CalculationException(String message, Throwable cause) {
		super(message, cause);
	}
}
