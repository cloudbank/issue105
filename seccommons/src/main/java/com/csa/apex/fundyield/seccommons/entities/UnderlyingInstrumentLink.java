/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Underlying instrument.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class UnderlyingInstrumentLink extends Versionable {

    /**
     * Surrogate id to uniquely identify an underlying instrument link.
     */
	@ColumnName("UNDERLYING_INSTRUMENT_LINK_SID")
    private long underlyingInstrumentLinkSid;

    /**
     * Underlying Instrument.
     */
	@ColumnName("UNDERLYING_INSTRUMENT_SID")
    private Instrument underlyingInstrument;

    /**
     * Overlaying Instrument.
     */
	@ColumnName("OVERLAYING_INSTRUMENT_SID")
    private Instrument overlayingInstrument;

    /**
     * For futures, options and entitlements (e.g., warrants) this field specifies the date on which the contract ceases
     * to exist. For other instruments, this field specifies the last date that a security can be converted at the
     * current conversion price and quantity to the underlying security. SEC Yield business confirm they might need to
     * use this date as well for calculations.
     */
	@ColumnName("EXPIRATION_OR_CONVER_END_DT")
    private Date expirationOrConverEndDate;

    /**
     * Informational field derived to facilitate reporting calculations.
     */
	@ColumnName("CONVER_FCTR")
    private BigDecimal converFctr;

    /**
     * Constructor.
     */
    public UnderlyingInstrumentLink() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>underlyingInstrumentLinkSid</tt>.
     * @return property value of underlyingInstrumentLinkSid
     */
    public long getUnderlyingInstrumentLinkSid() {
        return underlyingInstrumentLinkSid;
    }

    /**
     * Setter method for property <tt>underlyingInstrumentLinkSid</tt>.
     * @param underlyingInstrumentLinkSid value to be assigned to property underlyingInstrumentLinkSid
     */
    public void setUnderlyingInstrumentLinkSid(long underlyingInstrumentLinkSid) {
        this.underlyingInstrumentLinkSid = underlyingInstrumentLinkSid;
    }

    /**
     * Getter method for property <tt>underlyingInstrument</tt>.
     * @return property value of underlyingInstrument
     */
    public Instrument getUnderlyingInstrument() {
        return underlyingInstrument;
    }

    /**
     * Setter method for property <tt>underlyingInstrument</tt>.
     * @param underlyingInstrument value to be assigned to property underlyingInstrument
     */
    public void setUnderlyingInstrument(Instrument underlyingInstrument) {
        this.underlyingInstrument = underlyingInstrument;
    }

    /**
     * Getter method for property <tt>overlayingInstrument</tt>.
     * @return property value of overlayingInstrument
     */
    public Instrument getOverlayingInstrument() {
        return overlayingInstrument;
    }

    /**
     * Setter method for property <tt>overlayingInstrument</tt>.
     * @param overlayingInstrument value to be assigned to property overlayingInstrument
     */
    public void setOverlayingInstrument(Instrument overlayingInstrument) {
        this.overlayingInstrument = overlayingInstrument;
    }

    /**
     * Getter method for property <tt>expirationOrConverEndDate</tt>.
     * @return property value of expirationOrConverEndDate
     */
    public Date getExpirationOrConverEndDate() {
        return expirationOrConverEndDate;
    }

    /**
     * Setter method for property <tt>expirationOrConverEndDate</tt>.
     * @param expirationOrConverEndDate value to be assigned to property expirationOrConverEndDate
     */
    public void setExpirationOrConverEndDate(Date expirationOrConverEndDate) {
        this.expirationOrConverEndDate = expirationOrConverEndDate;
    }

    /**
     * Getter method for property <tt>converFctr</tt>.
     * @return property value of converFctr
     */
    public BigDecimal getConverFctr() {
        return converFctr;
    }

    /**
     * Setter method for property <tt>converFctr</tt>.
     * @param converFctr value to be assigned to property converFctr
     */
    public void setConverFctr(BigDecimal converFctr) {
        this.converFctr = converFctr;
    }
}
