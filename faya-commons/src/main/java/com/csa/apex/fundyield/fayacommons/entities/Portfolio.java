/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.util.List;

/**
 * A Portfolio (or Mutual Fund) is an investment vehicle consisting of a collection of investments that are managed by
 * an investment advisor.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class Portfolio extends Versionable {

    /**
     * Surrogate id to uniquely identify a portfolio.
     */
	@ColumnName("PORTFOLIO_SID")
    private long portfolioSid;

    /**
     * A unique value to identify a fund in FundRef/MDM system.
     */
	@ColumnName("PORTFOLIO_ID")
    private long portfolioId;

    /**
     * Short name of a given portfolio.
     */
	@ColumnName("PORTFOLIO_SHORT_NM")
    private String portfolioShortName;

    /**
     * The user-defined long description of a given portfolio, based on the defined and published naming conventions.
     */
	@ColumnName("PORTFOLIO_NM")
    private String portfolioName;

    /**
     * A Y/N value to indicate if calculations(Usually) for a given portfolio/fund are to be distributed to downstreams
     * (e.g: FIRS, UNITY Etc).
     */
	@ColumnName("FDR_DISTRIBUTION_ELIGIBLE_IND")
    private String fdrDistributionEligibleInd;

    /**
     * A code to indicate if it is SEC_YIELD, MMKT_YIELD or a DISTRIBUTABLE_YIELD funds.
     */
	@ColumnName("FDR_PORTFOLIO_TYPE_CD")
    private String fdrPortfolioTypeCode;

    /**
     * The CUSIP (Security Alt ID) used by the Portfolio/Fund when being bought/sold by FOFs. FoF buy/sell are
     * trades/transactions generated for book keeping purposes.
     */
	@ColumnName("CUSIP")
    private String cusip;

    /**
     * Share classes.
     */
    private List<ShareClass> shareClasses;

    /**
     * Portfolio snapshots.
     */
    private List<PortfolioSnapshot> portfolioSnapshots;

    /**
     * Portfolio tax exclusions.
     */
    private List<TaxExclusion> portfolioTaxExclusions;

    /**
     * Portfolio holdings.
     */
    private List<PortfolioHoldingSnapshot> portfolioHoldings;

    /**
     * Constructor.
     */
    public Portfolio() {
        // default empty constructor
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
     * Getter method for property <tt>portfolioId</tt>.
     * @return property value of portfolioId
     */
    public long getPortfolioId() {
        return portfolioId;
    }

    /**
     * Setter method for property <tt>portfolioId</tt>.
     * @param portfolioId value to be assigned to property portfolioId
     */
    public void setPortfolioId(long portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * Getter method for property <tt>portfolioShortName</tt>.
     * @return property value of portfolioShortName
     */
    public String getPortfolioShortName() {
        return portfolioShortName;
    }

    /**
     * Setter method for property <tt>portfolioShortName</tt>.
     * @param portfolioShortName value to be assigned to property portfolioShortName
     */
    public void setPortfolioShortName(String portfolioShortName) {
        this.portfolioShortName = portfolioShortName;
    }

    /**
     * Getter method for property <tt>portfolioName</tt>.
     * @return property value of portfolioName
     */
    public String getPortfolioName() {
        return portfolioName;
    }

    /**
     * Setter method for property <tt>portfolioName</tt>.
     * @param portfolioName value to be assigned to property portfolioName
     */
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    /**
     * Getter method for property <tt>fdrDistributionEligibleInd</tt>.
     * @return property value of fdrDistributionEligibleInd
     */
    public String getFdrDistributionEligibleInd() {
        return fdrDistributionEligibleInd;
    }

    /**
     * Setter method for property <tt>fdrDistributionEligibleInd</tt>.
     * @param fdrDistributionEligibleInd value to be assigned to property fdrDistributionEligibleInd
     */
    public void setFdrDistributionEligibleInd(String fdrDistributionEligibleInd) {
        this.fdrDistributionEligibleInd = fdrDistributionEligibleInd;
    }

    /**
     * Getter method for property <tt>fdrPortfolioTypeCode</tt>.
     * @return property value of fdrPortfolioTypeCode
     */
    public String getFdrPortfolioTypeCode() {
        return fdrPortfolioTypeCode;
    }

    /**
     * Setter method for property <tt>fdrPortfolioTypeCode</tt>.
     * @param fdrPortfolioTypeCode value to be assigned to property fdrPortfolioTypeCode
     */
    public void setFdrPortfolioTypeCode(String fdrPortfolioTypeCode) {
        this.fdrPortfolioTypeCode = fdrPortfolioTypeCode;
    }

    /**
     * Getter method for property <tt>cusip</tt>.
     * @return property value of cusip
     */
    public String getCusip() {
        return cusip;
    }

    /**
     * Setter method for property <tt>cusip</tt>.
     * @param cusip value to be assigned to property cusip
     */
    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    /**
     * Getter method for property <tt>shareClasses</tt>.
     * @return property value of shareClasses
     */
    public List<ShareClass> getShareClasses() {
        return shareClasses;
    }

    /**
     * Setter method for property <tt>shareClasses</tt>.
     * @param shareClasses value to be assigned to property shareClasses
     */
    public void setShareClasses(List<ShareClass> shareClasses) {
        this.shareClasses = shareClasses;
    }

    /**
     * Getter method for property <tt>portfolioSnapshots</tt>.
     * @return property value of portfolioSnapshots
     */
    public List<PortfolioSnapshot> getPortfolioSnapshots() {
        return portfolioSnapshots;
    }

    /**
     * Setter method for property <tt>portfolioSnapshots</tt>.
     * @param portfolioSnapshots value to be assigned to property portfolioSnapshots
     */
    public void setPortfolioSnapshots(List<PortfolioSnapshot> portfolioSnapshots) {
        this.portfolioSnapshots = portfolioSnapshots;
    }

    /**
     * Getter method for property <tt>portfolioTaxExclusions</tt>.
     * @return property value of portfolioTaxExclusions
     */
    public List<TaxExclusion> getPortfolioTaxExclusions() {
        return portfolioTaxExclusions;
    }

    /**
     * Setter method for property <tt>portfolioTaxExclusions</tt>.
     * @param portfolioTaxExclusions value to be assigned to property portfolioTaxExclusions
     */
    public void setPortfolioTaxExclusions(List<TaxExclusion> portfolioTaxExclusions) {
        this.portfolioTaxExclusions = portfolioTaxExclusions;
    }

    /**
     * Getter method for property <tt>portfolioHoldings</tt>.
     * @return property value of portfolioHoldings
     */
    public List<PortfolioHoldingSnapshot> getPortfolioHoldings() {
        return portfolioHoldings;
    }

    /**
     * Setter method for property <tt>portfolioHoldings</tt>.
     * @param portfolioHoldings value to be assigned to property portfolioHoldings
     */
    public void setPortfolioHoldings(List<PortfolioHoldingSnapshot> portfolioHoldings) {
        this.portfolioHoldings = portfolioHoldings;
    }
}
