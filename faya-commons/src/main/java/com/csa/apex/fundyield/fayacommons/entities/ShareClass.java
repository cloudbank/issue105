/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.util.List;

/**
 * Share classes are specific shares of portfolio available to the market place.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class ShareClass extends Versionable {

    /**
     * Surrogate id to uniquely identify a share class.
     */
	@ColumnName("SHARE_CLASS_SID")
    private long shareClassSid;

    /**
     * Surrogate id to uniquely identify a portfolio.
     */
	@ColumnName("PORTFOLIO_SID")
    private long portfolioSid;

    /**
     * Transfer agent share class id. Also referred as SAS_ID or DAL_ID.
     */
	@ColumnName("TRANSFER_AGENT_SHARE_CLASS_ID")
    private String transferAgentShareClassId;

    /**
     * Alternate id of a share class in the Accounting system.
     */
	@ColumnName("ACCOUNTING_SHARE_CLASS_ID")
    private String accountingShareClassId;

    /**
     * Alternate id of a share class in the FIRS system.
     */
	@ColumnName("FIRS_SHARE_CLASS_ID")
    private String firsShareClassId;

    /**
     * Alternate id of a share class in the TAAC/Shareholder Activity system.
     */
	@ColumnName("TAAC_SHARE_CLASS_ID")
    private String taacShareClassId;

    /**
     * The user-defined long description of a share class, based on the defined and published naming conventions.
     */
	@ColumnName("SHARE_CLASS_NM")
    private String shareClassName;

    /**
     * Alternate code that is unique to every Fund's Class.
     */
	@ColumnName("NASDAQ_SHARE_CLASS_ID")
    private String nasdaqShareClassId;

    /**
     * Share class snapshots.
     */
    private List<ShareClassSnapshot> shareClassSnapshots;

    /**
     * Constructor.
     */
    public ShareClass() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>shareClassSid</tt>.
     * @return property value of shareClassSid
     */
    public long getShareClassSid() {
        return shareClassSid;
    }

    /**
     * Setter method for property <tt>shareClassSid</tt>.
     * @param shareClassSid value to be assigned to property shareClassSid
     */
    public void setShareClassSid(long shareClassSid) {
        this.shareClassSid = shareClassSid;
    }

    /**
     * Getter method for property <tt>portfolioSid</tt>.
     * @return property value of portfolioSid
     */
    public long getPortfolioSid() {
        return portfolioSid;
    }

    /**
     * Setter method for property <tt>portfolioSid</tt>.
     * @param portfolioSid value to be assigned to property portfolioSid
     */
    public void setPortfolioSid(long portfolioSid) {
        this.portfolioSid = portfolioSid;
    }

    /**
     * Getter method for property <tt>transferAgentShareClassId</tt>.
     * @return property value of transferAgentShareClassId
     */
    public String getTransferAgentShareClassId() {
        return transferAgentShareClassId;
    }

    /**
     * Setter method for property <tt>transferAgentShareClassId</tt>.
     * @param transferAgentShareClassId value to be assigned to property transferAgentShareClassId
     */
    public void setTransferAgentShareClassId(String transferAgentShareClassId) {
        this.transferAgentShareClassId = transferAgentShareClassId;
    }

    /**
     * Getter method for property <tt>accountingShareClassId</tt>.
     * @return property value of accountingShareClassId
     */
    public String getAccountingShareClassId() {
        return accountingShareClassId;
    }

    /**
     * Setter method for property <tt>accountingShareClassId</tt>.
     * @param accountingShareClassId value to be assigned to property accountingShareClassId
     */
    public void setAccountingShareClassId(String accountingShareClassId) {
        this.accountingShareClassId = accountingShareClassId;
    }

    /**
     * Getter method for property <tt>firsShareClassId</tt>.
     * @return property value of firsShareClassId
     */
    public String getFirsShareClassId() {
        return firsShareClassId;
    }

    /**
     * Setter method for property <tt>firsShareClassId</tt>.
     * @param firsShareClassId value to be assigned to property firsShareClassId
     */
    public void setFirsShareClassId(String firsShareClassId) {
        this.firsShareClassId = firsShareClassId;
    }

    /**
     * Getter method for property <tt>taacShareClassId</tt>.
     * @return property value of taacShareClassId
     */
    public String getTaacShareClassId() {
        return taacShareClassId;
    }

    /**
     * Setter method for property <tt>taacShareClassId</tt>.
     * @param taacShareClassId value to be assigned to property taacShareClassId
     */
    public void setTaacShareClassId(String taacShareClassId) {
        this.taacShareClassId = taacShareClassId;
    }

    /**
     * Getter method for property <tt>shareClassName</tt>.
     * @return property value of shareClassName
     */
    public String getShareClassName() {
        return shareClassName;
    }

    /**
     * Setter method for property <tt>shareClassName</tt>.
     * @param shareClassName value to be assigned to property shareClassName
     */
    public void setShareClassName(String shareClassName) {
        this.shareClassName = shareClassName;
    }

    /**
     * Getter method for property <tt>nasdaqShareClassId</tt>.
     * @return property value of nasdaqShareClassId
     */
    public String getNasdaqShareClassId() {
        return nasdaqShareClassId;
    }

    /**
     * Setter method for property <tt>nasdaqShareClassId</tt>.
     * @param nasdaqShareClassId value to be assigned to property nasdaqShareClassId
     */
    public void setNasdaqShareClassId(String nasdaqShareClassId) {
        this.nasdaqShareClassId = nasdaqShareClassId;
    }

    /**
     * Getter method for property <tt>shareClassSnapshots</tt>.
     * @return property value of shareClassSnapshots
     */
    public List<ShareClassSnapshot> getShareClassSnapshots() {
        return shareClassSnapshots;
    }

    /**
     * Setter method for property <tt>shareClassSnapshots</tt>.
     * @param shareClassSnapshots value to be assigned to property shareClassSnapshots
     */
    public void setShareClassSnapshots(List<ShareClassSnapshot> shareClassSnapshots) {
        this.shareClassSnapshots = shareClassSnapshots;
    }
}
