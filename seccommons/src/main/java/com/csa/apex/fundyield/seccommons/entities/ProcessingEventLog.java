/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.util.Date;

/**
 * Processing event log.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class ProcessingEventLog extends Auditable {

    /**
     * Surrogate id to uniquely identify a processing event log.
     */
    private long eventLogSid;

    /**
     * The id of the user that logged the event.
     */
    private String eventLogUserId;

    /**
     * Event code, ex: Bus.Lock, Approval, FundTrx, SECYield, Exp Proc, Adjistment, Feed-Batch etc.
     */
    private String eventCd;

    /**
     * Surrogate id to uniquely identify a portfolio holding snapshot.
     */
    private long portfolioHoldingSnapshotSid;

    /**
     * Surrogate id to uniquely identify a tradable entity snapshot.
     */
    private long tradableEntitySnapshotSid;

    /**
     * Comment associated with the event log.
     */
    private String eventLogCommentsTxt;

    /**
     * Date and time up to sub second when a given occurrence of an event was logged.
     */
    private Date eventLogTs;

    /**
     * Constructor.
     */
    public ProcessingEventLog() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>eventLogSid</tt>.
     * @return property value of eventLogSid
     */
    public long getEventLogSid() {
        return eventLogSid;
    }

    /**
     * Setter method for property <tt>eventLogSid</tt>.
     * @param eventLogSid value to be assigned to property eventLogSid
     */
    public void setEventLogSid(long eventLogSid) {
        this.eventLogSid = eventLogSid;
    }

    /**
     * Getter method for property <tt>eventLogUserId</tt>.
     * @return property value of eventLogUserId
     */
    public String getEventLogUserId() {
        return eventLogUserId;
    }

    /**
     * Setter method for property <tt>eventLogUserId</tt>.
     * @param eventLogUserId value to be assigned to property eventLogUserId
     */
    public void setEventLogUserId(String eventLogUserId) {
        this.eventLogUserId = eventLogUserId;
    }

    /**
     * Getter method for property <tt>eventCd</tt>.
     * @return property value of eventCd
     */
    public String getEventCd() {
        return eventCd;
    }

    /**
     * Setter method for property <tt>eventCd</tt>.
     * @param eventCd value to be assigned to property eventCd
     */
    public void setEventCd(String eventCd) {
        this.eventCd = eventCd;
    }

    /**
     * Getter method for property <tt>portfolioHoldingSnapshotSid</tt>.
     * @return property value of portfolioHoldingSnapshotSid
     */
    public long getPortfolioHoldingSnapshotSid() {
        return portfolioHoldingSnapshotSid;
    }

    /**
     * Setter method for property <tt>portfolioHoldingSnapshotSid</tt>.
     * @param portfolioHoldingSnapshotSid value to be assigned to property portfolioHoldingSnapshotSid
     */
    public void setPortfolioHoldingSnapshotSid(long portfolioHoldingSnapshotSid) {
        this.portfolioHoldingSnapshotSid = portfolioHoldingSnapshotSid;
    }

    /**
     * Getter method for property <tt>tradableEntitySnapshotSid</tt>.
     * @return property value of tradableEntitySnapshotSid
     */
    public long getTradableEntitySnapshotSid() {
        return tradableEntitySnapshotSid;
    }

    /**
     * Setter method for property <tt>tradableEntitySnapshotSid</tt>.
     * @param tradableEntitySnapshotSid value to be assigned to property tradableEntitySnapshotSid
     */
    public void setTradableEntitySnapshotSid(long tradableEntitySnapshotSid) {
        this.tradableEntitySnapshotSid = tradableEntitySnapshotSid;
    }

    /**
     * Getter method for property <tt>eventLogCommentsTxt</tt>.
     * @return property value of eventLogCommentsTxt
     */
    public String getEventLogCommentsTxt() {
        return eventLogCommentsTxt;
    }

    /**
     * Setter method for property <tt>eventLogCommentsTxt</tt>.
     * @param eventLogCommentsTxt value to be assigned to property eventLogCommentsTxt
     */
    public void setEventLogCommentsTxt(String eventLogCommentsTxt) {
        this.eventLogCommentsTxt = eventLogCommentsTxt;
    }

    /**
     * Getter method for property <tt>eventLogTs</tt>.
     * @return property value of eventLogTs
     */
    public Date getEventLogTs() {
        return eventLogTs;
    }

    /**
     * Setter method for property <tt>eventLogTs</tt>.
     * @param eventLogTs value to be assigned to property eventLogTs
     */
    public void setEventLogTs(Date eventLogTs) {
        this.eventLogTs = eventLogTs;
    }
}
