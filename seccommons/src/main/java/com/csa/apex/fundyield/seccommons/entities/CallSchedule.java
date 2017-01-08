/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Call schedule.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class CallSchedule extends Versionable {

	/**
	 * Surrogate id to uniquely identify an instrument.
	 */
	@ColumnName("INSTRUMENT_SID")
	private long instrumentSid;

    /**
     * Surrogate id to uniquely identify a call schedule.
     */
	@ColumnName("CALL_SCHEDULE_SID")
    private long callScheduleSid;

    /**
     * Contains codes that indicate the provision type of an instrument's call: TRADITIONAL, SOFT, MAKE WHOLE, and
     * EXTRAORDINARY.
     */
	@ColumnName("CALL_PROVISION_TYPE_CD")
    private String callProvisionTypeCd;

    /**
     * The date on which an instrument may be/may have been redeemed before maturity (i.e., called). An issuer may
     * choose to call an instrument if the issuer feels there is a benefit to re-financing the issue. Instruments might
     * be redeemed at par or at a small premium..
     */
	@ColumnName("CALL_DT")
    private Date callDate;

    /**
     * The price at which an instrument may be/may have been called.
     */
	@ColumnName("CALL_PRC")
    private BigDecimal callPrc;

    /**
     * Constructor.
     */
    public CallSchedule() {
        // default empty constructor
    }

	/**
	 * Getter method for property <tt>instrumentSid</tt>.
	 * 
	 * @return property value of instrumentSid
	 */
	public long getInstrumentSid() {
		return instrumentSid;
	}

	/**
	 * Setter method for property <tt>instrumentSid</tt>.
	 * 
	 * @param instrumentSid
	 *            value to be assigned to property instrumentSid
	 */
	public void setInstrumentSid(long instrumentSid) {
		this.instrumentSid = instrumentSid;
	}

    /**
     * Getter method for property <tt>callScheduleSid</tt>.
     * @return property value of callScheduleSid
     */
    public long getCallScheduleSid() {
        return callScheduleSid;
    }

    /**
     * Setter method for property <tt>callScheduleSid</tt>.
     * @param callScheduleSid value to be assigned to property callScheduleSid
     */
    public void setCallScheduleSid(long callScheduleSid) {
        this.callScheduleSid = callScheduleSid;
    }

    /**
     * Getter method for property <tt>callProvisionTypeCd</tt>.
     * @return property value of callProvisionTypeCd
     */
    public String getCallProvisionTypeCd() {
        return callProvisionTypeCd;
    }

    /**
     * Setter method for property <tt>callProvisionTypeCd</tt>.
     * @param callProvisionTypeCd value to be assigned to property callProvisionTypeCd
     */
    public void setCallProvisionTypeCd(String callProvisionTypeCd) {
        this.callProvisionTypeCd = callProvisionTypeCd;
    }

    /**
     * Getter method for property <tt>callDate</tt>.
     * @return property value of callDate
     */
    public Date getCallDate() {
        return callDate;
    }

    /**
     * Setter method for property <tt>callDate</tt>.
     * @param callDate value to be assigned to property callDate
     */
    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    /**
     * Getter method for property <tt>callPrc</tt>.
     * @return property value of callPrc
     */
    public BigDecimal getCallPrc() {
        return callPrc;
    }

    /**
     * Setter method for property <tt>callPrc</tt>.
     * @param callPrc value to be assigned to property callPrc
     */
    public void setCallPrc(BigDecimal callPrc) {
        this.callPrc = callPrc;
    }
}
