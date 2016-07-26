/*
 * Copyright (c) 2015 TopCoder, Inc. All rights reserved.
 */
package com.sancus.secyield.exceptions;

/**
 * The sec yield exception.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class SecYieldException extends Exception {
    /**
	 * The default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor with message
     * @param message the error message
     */
    public SecYieldException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
     * @param message the error message
     * @param cause the cause
     */
    public SecYieldException(String message, Throwable cause) {
        super(message, cause);
    }
}
