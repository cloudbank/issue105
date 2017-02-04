/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Put schedule.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class PutSchedule extends Versionable {

	/**
	 * Surrogate id to uniquely identify an instrument.
	 */
	@ColumnName("INSTRUMENT_SID")
	private long instrumentSid;

    /**
     * Surrogate id to uniquely identify a put schedule.
     */
	@ColumnName("PUT_SCHEDULE_SID")
    private long putScheduleSid;

    /**
     * A system-generated sequence number to identify a given set of put terms. If an instrument has multiple sets of
     * terms, each entry in the PUT_TERMS will receive a different PUT_TERMS_SEQ_NBR. This number is stored in both the
     * PUT_SCHEDULE and PUT_TERMS tables for cross referencing purposes.
     */
	@ColumnName("PUT_TERMS_SEQ_NBR")
    private long putTermsSeqNbr;

    /**
     * The date on which a put could be or could have been exercised.
     */
	@ColumnName("PUT_DT")
    private Date putDate;

    /**
     * The price at which an instrument may be redeemed on the PUT_DT.
     */
	@ColumnName("PUT_PRC")
    private BigDecimal putPrc;

    /**
     * Constructor.
     */
    public PutSchedule() {
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
     * Getter method for property <tt>putScheduleSid</tt>.
     * @return property value of putScheduleSid
     */
    public long getPutScheduleSid() {
        return putScheduleSid;
    }

    /**
     * Setter method for property <tt>putScheduleSid</tt>.
     * @param putScheduleSid value to be assigned to property putScheduleSid
     */
    public void setPutScheduleSid(long putScheduleSid) {
        this.putScheduleSid = putScheduleSid;
    }

    /**
     * Getter method for property <tt>putTermsSeqNbr</tt>.
     * @return property value of putTermsSeqNbr
     */
    public long getPutTermsSeqNbr() {
        return putTermsSeqNbr;
    }

    /**
     * Setter method for property <tt>putTermsSeqNbr</tt>.
     * @param putTermsSeqNbr value to be assigned to property putTermsSeqNbr
     */
    public void setPutTermsSeqNbr(long putTermsSeqNbr) {
        this.putTermsSeqNbr = putTermsSeqNbr;
    }

    /**
     * Getter method for property <tt>putDate</tt>.
     * @return property value of putDate
     */
    public Date getPutDate() {
        return putDate;
    }

    /**
     * Setter method for property <tt>putDate</tt>.
     * @param putDate value to be assigned to property putDate
     */
    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }

    /**
     * Getter method for property <tt>putPrc</tt>.
     * @return property value of putPrc
     */
    public BigDecimal getPutPrc() {
        return putPrc;
    }

    /**
     * Setter method for property <tt>putPrc</tt>.
     * @param putPrc value to be assigned to property putPrc
     */
    public void setPutPrc(BigDecimal putPrc) {
        this.putPrc = putPrc;
    }
}
