/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

/**
 * Tax exclusion.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class TaxExclusion extends Versionable {

    /**
     * Surrogate id to uniquely identify a tax exclusion.
     */
	@ColumnName("PORTFOLIO_TAX_EXCLUSION_SID")
    private long taxExclusionSid;

    /**
     * Surrogate id to uniquely identify a portfolio.
     */
	@ColumnName("PORTFOLIO_SID")
    private long portfolioSid;

    /**
     * Has the two character state code or US Territories for state to be excluded from using the portfolio's state tax
     * rate.
     */
	@ColumnName("PORTFOLIO_TAX_EXCL_LOC_CD")
    private String portfolioTaxExclLocCd;

    /**
     * Constructor.
     */
    public TaxExclusion() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>taxExclusionSid</tt>.
     * @return property value of taxExclusionSid
     */
    public long getTaxExclusionSid() {
        return taxExclusionSid;
    }

    /**
     * Setter method for property <tt>taxExclusionSid</tt>.
     * @param taxExclusionSid value to be assigned to property taxExclusionSid
     */
    public void setTaxExclusionSid(long taxExclusionSid) {
        this.taxExclusionSid = taxExclusionSid;
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
     * Getter method for property <tt>portfolioTaxExclLocCd</tt>.
     * @return property value of portfolioTaxExclLocCd
     */
    public String getPortfolioTaxExclLocCd() {
        return portfolioTaxExclLocCd;
    }

    /**
     * Setter method for property <tt>portfolioTaxExclLocCd</tt>.
     * @param portfolioTaxExclLocCd value to be assigned to property portfolioTaxExclLocCd
     */
    public void setPortfolioTaxExclLocCd(String portfolioTaxExclLocCd) {
        this.portfolioTaxExclLocCd = portfolioTaxExclLocCd;
    }
}
