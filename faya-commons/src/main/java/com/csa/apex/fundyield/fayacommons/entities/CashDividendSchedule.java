/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Cash dividend schedule.
 * 
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class CashDividendSchedule extends Versionable {

	/**
	 * Surrogate id to uniquely identify an instrument.
	 */
	@ColumnName("INSTRUMENT_SID")
	private long instrumentSid;

	/**
	 * Code that can be used to identify the type of an individual dividend
	 * (e.g., cash or stock), within Cash Dividend, distinguishes between
	 * RegualCash, Additional Cash div etc. DIV Regular Cash Dividend ADL
	 * Additional Cash Dividend SPL Special Cash Dividend MEM Memorial Cash
	 * Dividend.
	 */
	@ColumnName("DIVIDEND_TYPE_CD")
	private String dividendTypeCd;

	/**
	 * Codes that can be used to specify the nature of a dividend that is paid
	 * by an instrument. Examples include "regular cash" and "memorial".
	 * Dividend subtype codes provide granular level detail about a given
	 * dividend payment. Each dividend subtype is associated with a higher-level
	 * DIVIDEND_TYPE_CD in the DIVIDEND_SUBTYPE code table.
	 */
	@ColumnName("DIVIDEND_SUBTYPE_CD")
	private String dividendSubtypeCd;

	/**
	 * Identifies the currency in which the amounts in GROSS_PAYMNT_AMT and
	 * NET_PAYMNT_AMT are denominated.
	 */
	@ColumnName("CURRENCY_CD")
	private String currencyCd;

	/**
	 * The date on which an investor must officially own shares or units in
	 * order to be entitled to a distribution (dividend).
	 */
	@ColumnName("EX_DIVIDEND_DT")
	private Date exDividendDate;

	/**
	 * The net amount of the distribution, expressed in amount per unit (per
	 * share or per unit of par). The net amount is the after tax amount being
	 * paid out to shareholders (in local currency).
	 */
	@ColumnName("NET_PAYMNT_AMT")
	private BigDecimal netPaymentAmt;

	/**
	 * Constructor.
	 */
	public CashDividendSchedule() {
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
	 * Getter method for property <tt>dividendTypeCd</tt>.
	 * 
	 * @return property value of dividendTypeCd
	 */
	public String getDividendTypeCd() {
		return dividendTypeCd;
	}

	/**
	 * Setter method for property <tt>dividendTypeCd</tt>.
	 * 
	 * @param dividendTypeCd
	 *            value to be assigned to property dividendTypeCd
	 */
	public void setDividendTypeCd(String dividendTypeCd) {
		this.dividendTypeCd = dividendTypeCd;
	}

	/**
	 * Getter method for property <tt>dividendSubtypeCd</tt>.
	 * 
	 * @return property value of dividendSubtypeCd
	 */
	public String getDividendSubtypeCd() {
		return dividendSubtypeCd;
	}

	/**
	 * Setter method for property <tt>dividendSubtypeCd</tt>.
	 * 
	 * @param dividendSubtypeCd
	 *            value to be assigned to property dividendSubtypeCd
	 */
	public void setDividendSubtypeCd(String dividendSubtypeCd) {
		this.dividendSubtypeCd = dividendSubtypeCd;
	}

	/**
	 * Getter method for property <tt>currencyCd</tt>.
	 * 
	 * @return property value of currencyCd
	 */
	public String getCurrencyCd() {
		return currencyCd;
	}

	/**
	 * Setter method for property <tt>currencyCd</tt>.
	 * 
	 * @param currencyCd
	 *            value to be assigned to property currencyCd
	 */
	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	/**
	 * Getter method for property <tt>exDividendDate</tt>.
	 * 
	 * @return property value of exDividendDate
	 */
	public Date getExDividendDate() {
		return exDividendDate;
	}

	/**
	 * Setter method for property <tt>exDividendDate</tt>.
	 * 
	 * @param exDividendDate
	 *            value to be assigned to property exDividendDate
	 */
	public void setExDividendDate(Date exDividendDate) {
		this.exDividendDate = exDividendDate;
	}

	/**
	 * Getter method for property <tt>netPaymentAmt</tt>.
	 * 
	 * @return property value of netPaymentAmt
	 */
	public BigDecimal getNetPaymentAmt() {
		return netPaymentAmt;
	}

	/**
	 * Setter method for property <tt>netPaymentAmt</tt>.
	 * 
	 * @param netPaymentAmt
	 *            value to be assigned to property netPaymentAmt
	 */
	public void setNetPaymentAmt(BigDecimal netPaymentAmt) {
		this.netPaymentAmt = netPaymentAmt;
	}
}
