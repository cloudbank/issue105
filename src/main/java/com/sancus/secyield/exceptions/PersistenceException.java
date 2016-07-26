/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.sancus.secyield.exceptions;

/**
 * The persistence exception.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class PersistenceException extends Exception {
    /**
	 * The default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor with message
     * @param message the error message
     */
    public PersistenceException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
     * @param message the error message
     * @param cause the cause
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
