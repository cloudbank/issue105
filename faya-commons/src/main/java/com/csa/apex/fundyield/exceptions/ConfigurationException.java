/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.exceptions;

/**
 * The configuration exception.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ConfigurationException extends RuntimeException {
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
	public ConfigurationException(String message) {
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
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

}
