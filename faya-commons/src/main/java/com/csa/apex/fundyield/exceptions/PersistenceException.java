/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.exceptions;

/**
 * The exception is thrown if any error during persistence access.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class PersistenceException extends FundAccountingYieldException {
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
	public PersistenceException(String message) {
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
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}
