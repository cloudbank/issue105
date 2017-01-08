/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

/**
 * The warning log.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class WarningLog extends Auditable {

    /**
     * Surrogate id to uniquely identify a warning log.
     */
    private long warningLogSid;

    /**
     * A code to uniquely identify a warning to be displayed on UI.
     */
    private String warningCd;

    /**
     * The event log.
     */
    private ProcessingEventLog eventLog;

    /**
     * The tolerance.
     */
    private Tolerance tolerance;

    /**
     * Constructor.
     */
    public WarningLog() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>warningLogSid</tt>.
     * @return property value of warningLogSid
     */
    public long getWarningLogSid() {
        return warningLogSid;
    }

    /**
     * Setter method for property <tt>warningLogSid</tt>.
     * @param warningLogSid value to be assigned to property warningLogSid
     */
    public void setWarningLogSid(long warningLogSid) {
        this.warningLogSid = warningLogSid;
    }

    /**
     * Getter method for property <tt>warningCd</tt>.
     * @return property value of warningCd
     */
    public String getWarningCd() {
        return warningCd;
    }

    /**
     * Setter method for property <tt>warningCd</tt>.
     * @param warningCd value to be assigned to property warningCd
     */
    public void setWarningCd(String warningCd) {
        this.warningCd = warningCd;
    }

    /**
     * Getter method for property <tt>eventLog</tt>.
     * @return property value of eventLog
     */
    public ProcessingEventLog getEventLog() {
        return eventLog;
    }

    /**
     * Setter method for property <tt>eventLog</tt>.
     * @param eventLog value to be assigned to property eventLog
     */
    public void setEventLog(ProcessingEventLog eventLog) {
        this.eventLog = eventLog;
    }

    /**
     * Getter method for property <tt>tolerance</tt>.
     * @return property value of tolerance
     */
    public Tolerance getTolerance() {
        return tolerance;
    }

    /**
     * Setter method for property <tt>tolerance</tt>.
     * @param tolerance value to be assigned to property tolerance
     */
    public void setTolerance(Tolerance tolerance) {
        this.tolerance = tolerance;
    }
}
