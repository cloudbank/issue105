/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.util.List;

import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * Tolerance check result.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class ToleranceCheckResult {

    /**
     * Flag if processed without errors.
     */
    private boolean processedWithoutErrors;

    /**
     * Warning logs.
     */
    private List<WarningLog> warningLogs;

    /**
     * Constructor.
     */
    public ToleranceCheckResult() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>processedWithoutErrors</tt>.
     * @return property value of processedWithoutErrors
     */
    public boolean isProcessedWithoutErrors() {
        return processedWithoutErrors;
    }

    /**
     * Setter method for property <tt>processedWithoutErrors</tt>.
     * @param processedWithoutErrors value to be assigned to property processedWithoutErrors
     */
    public void setProcessedWithoutErrors(boolean processedWithoutErrors) {
        this.processedWithoutErrors = processedWithoutErrors;
    }

    /**
     * Getter method for property <tt>warningLogs</tt>.
     * @return property value of warningLogs
     */
    public List<WarningLog> getWarningLogs() {
        return warningLogs;
    }

    /**
     * Setter method for property <tt>warningLogs</tt>.
     * @param warningLogs value to be assigned to property warningLogs
     */
    public void setWarningLogs(List<WarningLog> warningLogs) {
        this.warningLogs = warningLogs;
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
