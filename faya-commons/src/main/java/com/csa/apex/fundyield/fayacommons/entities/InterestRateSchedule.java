/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.math.BigDecimal;

/**
 * Interest rate schedule.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class InterestRateSchedule extends Versionable {

	/**
	 * Surrogate id to uniquely identify an instrument.
	 */
	@ColumnName("INSTRUMENT_SID")
	private long instrumentSid;

    /**
     * Surrogate id to uniquely identify an instrument rate schedule.
     */
	@ColumnName("INTEREST_RATE_SCHEDULE_SID")
    private long interestRateScheduleSid;

    /**
     * Code that can be used to indicate if the interest rate specified in INTEREST RATE is PIK or Cash.
     */
	@ColumnName("INTEREST_RATE_TYPE_CD")
    private String interestRateTypeCd;

    /**
     * New coupon rate effective as of interest_reate_Eff_dt. To get Step_Rt use filter where
     * INSTRUMENT-,COUPON_RATE_TYPE_CD= 'S' i.e.STEPPED COUPON.
     */
	@ColumnName("INTEREST_RT")
    private BigDecimal interestRt;

    /**
     * Constructor.
     */
    public InterestRateSchedule() {
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
     * Getter method for property <tt>interestRateScheduleSid</tt>.
     * @return property value of interestRateScheduleSid
     */
    public long getInterestRateScheduleSid() {
        return interestRateScheduleSid;
    }

    /**
     * Setter method for property <tt>interestRateScheduleSid</tt>.
     * @param interestRateScheduleSid value to be assigned to property interestRateScheduleSid
     */
    public void setInterestRateScheduleSid(long interestRateScheduleSid) {
        this.interestRateScheduleSid = interestRateScheduleSid;
    }

    /**
     * Getter method for property <tt>interestRateTypeCd</tt>.
     * @return property value of interestRateTypeCd
     */
    public String getInterestRateTypeCd() {
        return interestRateTypeCd;
    }

    /**
     * Setter method for property <tt>interestRateTypeCd</tt>.
     * @param interestRateTypeCd value to be assigned to property interestRateTypeCd
     */
    public void setInterestRateTypeCd(String interestRateTypeCd) {
        this.interestRateTypeCd = interestRateTypeCd;
    }

    /**
     * Getter method for property <tt>interestRt</tt>.
     * @return property value of interestRt
     */
    public BigDecimal getInterestRt() {
        return interestRt;
    }

    /**
     * Setter method for property <tt>interestRt</tt>.
     * @param interestRt value to be assigned to property interestRt
     */
    public void setInterestRt(BigDecimal interestRt) {
        this.interestRt = interestRt;
    }

}
