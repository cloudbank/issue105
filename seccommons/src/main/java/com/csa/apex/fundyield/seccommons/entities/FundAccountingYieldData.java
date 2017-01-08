/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.util.Date;
import java.util.List;

import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * Fund accounting yield data.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class FundAccountingYieldData {

    /**
     * Report date.
     */
    private Date reportDate;

    /**
     * Business date.
     */
    private Date businessDate;

    /**
     * Instruments list.
     */
    private List<Instrument> instruments;

    /**
     * Portfolios list.
     */
    private List<Portfolio> portfolios;

    /**
     * Constructor.
     */
    public FundAccountingYieldData() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>reportDate</tt>.
     * @return property value of reportDate
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * Setter method for property <tt>reportDate</tt>.
     * @param reportDate value to be assigned to property reportDate
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * Getter method for property <tt>businessDate</tt>.
     * @return property value of businessDate
     */
    public Date getBusinessDate() {
        return businessDate;
    }

    /**
     * Setter method for property <tt>businessDate</tt>.
     * @param businessDate value to be assigned to property businessDate
     */
    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    /**
     * Getter method for property <tt>instruments</tt>.
     * @return property value of instruments
     */
    public List<Instrument> getInstruments() {
        return instruments;
    }

    /**
     * Setter method for property <tt>instruments</tt>.
     * @param instruments value to be assigned to property instruments
     */
    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    /**
     * Getter method for property <tt>portfolios</tt>.
     * @return property value of portfolios
     */
    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    /**
     * Setter method for property <tt>portfolios</tt>.
     * @param portfolios value to be assigned to property portfolios
     */
    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
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
