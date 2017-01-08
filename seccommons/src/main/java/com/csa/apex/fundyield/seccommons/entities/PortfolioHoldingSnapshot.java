/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The portfolio holding snapshot for the given date.
 * 
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class PortfolioHoldingSnapshot extends Adjustable {

	/**
	 * Surrogate id to uniquely identify a portfolio holding snapshot.
	 */
	@ColumnName("PORTFOLIO_HOLDING_SNAPSHOT_SID")
	private long portfolioHoldingSnapshotSid;

	/**
	 * Surrogate id to uniquely identify a portfolio.
	 */
	@ColumnName("PORTFOLIO_SID")
	private long portfolioSid;

	/**
	 * Date for which calculations are being done and reported (e.g:
	 * yield/income, etc). (Usually closest business day, prior to the current
	 * business day).
	 */
	@ColumnName("REPORTING_DT")
	private Date reportDate;

	/**
	 * Tradable entity.
	 */
	@ColumnName("TRADABLE_ENTITY_SID")
	private TradableEntity tradableEntity;

	/**
	 * A Code indicating different (BusinessGroup/System) views of the data.
	 * Initial phase of the project will focus on FA (Fund Accounting View) The
	 * diff views could be based on (BusinessGroup, or usually Accounting
	 * groups/systems) : Ex: FA Fund Accounting view A1 Tax view - for e-Tax
	 * application looks at it from tax focused way IA InvestOne only view,
	 * Adjusted mo-end view (1st 3 business days of a moth, prev month numbers
	 * are adjusted and reported) etc.
	 */
	@ColumnName("HOLDING_BUSINESS_GROUP_VIEW_CD")
	private String holdingBusinessGroupViewCd;

	/**
	 * A code indicating different (TimePeriod) views of the data. Initial phase
	 * of the project will focus on EOD (End of Day View) Ex: SOD - Start of Day
	 * view of the data EOD - End of Day view of the data T1M data as of 1st
	 * business day of the month T2M data as of 2nd business day of the month
	 * T3M data as of 3rd business day of the month ME month end.
	 */
	@ColumnName("HOLDING_VIEW_CD")
	private String holdingViewCd;

	/**
	 * Indicates whether the position is held long or short. "LONG" or "SHORT".
	 * In this application, business, is only interested in tracking securities
	 * held in long position. Hence all rows might end up having the same value
	 * i.e. "LONG". Keeping this column on the holdings table as across
	 * different applications, position code is part of alternate key on the
	 * holdings table.
	 */
	@ColumnName("POSITION_CD")
	private String positionCd;

	/**
	 * An value to uniquely identify a given Tradable Entity as used in the
	 * source system (i.e. MDM).
	 */
	@ColumnName("TRADABLE_ENTITY_ID")
	private long tradableEntityId;

	/**
	 * Accrued income for a given reporting date.
	 */
	@ColumnName("ACCRUED_INCOME_AMT")
	private BigDecimal accruedIncomeAmt;

	/**
	 * earned amortization amount in base currency.
	 */
	@ColumnName("EARNED_AMORT_BASE_AMT")
	private BigDecimal earnedAmortBaseAmt;

	/**
	 * earned Inflationary Compensation amount in Base currency.
	 */
	@ColumnName("EARNED_INFL_CMPS_BASE_AMT")
	private BigDecimal earnedInflCmpsBaseAmt;

	/**
	 * Market value in Base currency. MktVal is a position level, and is at
	 * shares*price. point-in-time, Daily snapshot and not a cumulative amount.
	 */
	@ColumnName("MARKET_VALUE_BASE_AMT")
	private BigDecimal marketValueBaseAmt;

	/**
	 * Settled shares count adjusted for inflation.
	 */
	@ColumnName("INFLATION_ADJ_SHARE_CNT")
	private BigDecimal inflationAdjShareCnt;

	/**
	 * Number of Shares of a security held by a given fund. Also referred to as
	 * Share Par.
	 */
	@ColumnName("SETTLE_SHARE_CNT")
	private BigDecimal settleShareCnt;

	/**
	 * This is a value specific to Mortgage backed securities, which shows the
	 * number of shares that was used to calculate the original Face Amount.
	 * Used in deriving the true current factor (esp.when currentFactor=0).
	 */
	@ColumnName("ORIGINAL_FACE_SHARE_QTY")
	private BigDecimal originalFaceShareQty;

	/**
	 * one fx rate per security, and always against used. Valid Values: 1.5
	 * (implies For local JPY-USD exchange rate, 1.5 JPY is equal to One USD.
	 * fx-rate - pulled at position level from Accounting system, hence
	 * recording at position level in this model.
	 */
	@ColumnName("FX_RT")
	private BigDecimal fxRt;

	/**
	 * One day income amount as calculated in SEC yield application. Income is
	 * calculated at position level (i.e. Fund/Portfolio and Security level).
	 */
	@ColumnName("DER_SEC_YIELD_1_DAY_INCOME_AMT")
	private BigDecimal derSecYield1DayIncomeAmt;

	/**
	 * One day income amount as calculated in SEC yield application. Income is
	 * calculated at position level (i.e. Fund/Portfolio and Security level).
	 */
	@ColumnName("ADJ_DER_SEC_YIELD_1DAY_INC_AMT")
	private BigDecimal adjDerSecYield1ayIncAmt;

	/**
	 * Market value in Base currency. MktVal is a position level, and is at
	 * shares*price. point-in-time, Daily snapshot and not a cumulative amount.
	 */
	@ColumnName("ADJ_DER_MARKET_VALUE_BASE_AMT")
	private BigDecimal adjDerMarketValueBaseAmt;

	/**
	 * Number of Shares of a security held by a given fund. Also referred to as
	 * Share Par, as adjusted by business user usually via UI.
	 */
	@ColumnName("ADJ_SETTLE_SHARE_CNT")
	private BigDecimal adjSettleShareCnt;

	/**
	 * Accrued income for a given reporting date as adjusted by business user
	 * usually via UI.
	 */
	@ColumnName("ADJ_ACCRUED_INCOME_AMT")
	private BigDecimal adjAccruedIncomeAmt;

	/**
	 * earned amortization amount in base currency as adjusted by business user
	 * usually via UI.
	 */
	@ColumnName("ADJ_EARNED_AMORT_BASE_AMT")
	private BigDecimal adjEarnedAmortBaseAmt;

	/**
	 * earned Inflationary Compensation amount in Base currency as adjusted by
	 * business user usually via UI.
	 */
	@ColumnName("ADJ_EARNED_INFL_CMPS_BASE_AMT")
	private BigDecimal adjEarnedInflCmpsBaseAmt;

	/**
	 * comments associated with the adjustments. Comments from the previous
	 * adjustment will be either overwritten or new comments can be appended for
	 * the same reporting period if needed..
	 */
	@ColumnName("ADJ_COMMENTS_TXT")
	private String adjCommentsTxt;

	/**
	 * Constructor.
	 */
	public PortfolioHoldingSnapshot() {
		// default empty constructor
	}

	/**
	 * Getter method for property <tt>portfolioHoldingSnapshotSid</tt>.
	 * 
	 * @return property value of portfolioHoldingSnapshotSid
	 */
	public long getPortfolioHoldingSnapshotSid() {
		return portfolioHoldingSnapshotSid;
	}

	/**
	 * Setter method for property <tt>portfolioHoldingSnapshotSid</tt>.
	 * 
	 * @param portfolioHoldingSnapshotSid
	 *            value to be assigned to property portfolioHoldingSnapshotSid
	 */
	public void setPortfolioHoldingSnapshotSid(long portfolioHoldingSnapshotSid) {
		this.portfolioHoldingSnapshotSid = portfolioHoldingSnapshotSid;
	}

	/**
	 * Getter method for property <tt>portfolioSid</tt>.
	 * 
	 * @return property value of portfolioSid
	 */
	public long getPortfolioSid() {
		return portfolioSid;
	}

	/**
	 * Setter method for property <tt>portfolioSid</tt>.
	 * 
	 * @param portfolioSid
	 *            value to be assigned to property portfolioSid
	 */
	public void setPortfolioSid(long portfolioSid) {
		this.portfolioSid = portfolioSid;
	}

	/**
	 * Getter method for property <tt>reportDate</tt>.
	 * 
	 * @return property value of reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * Setter method for property <tt>reportDate</tt>.
	 * 
	 * @param reportDate
	 *            value to be assigned to property reportDate
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * Getter method for property <tt>tradableEntity</tt>.
	 * 
	 * @return property value of tradableEntity
	 */
	public TradableEntity getTradableEntity() {
		return tradableEntity;
	}

	/**
	 * Setter method for property <tt>tradableEntity</tt>.
	 * 
	 * @param tradableEntity
	 *            value to be assigned to property tradableEntity
	 */
	public void setTradableEntity(TradableEntity tradableEntity) {
		this.tradableEntity = tradableEntity;
	}

	/**
	 * Getter method for property <tt>tradableEntityId</tt>.
	 * 
	 * @return property value of tradableEntityId
	 */
	public long getTradableEntityId() {
		return tradableEntityId;
	}

	/**
	 * Setter method for property <tt>tradableEntityId</tt>.
	 * 
	 * @param tradableEntityId
	 *            value to be assigned to property tradableEntityId
	 */
	public void setTradableEntityId(long tradableEntityId) {
		this.tradableEntityId = tradableEntityId;
	}

	/**
	 * Getter method for property <tt>holdingBusinessGroupViewCd</tt>.
	 * 
	 * @return property value of holdingBusinessGroupViewCd
	 */
	public String getHoldingBusinessGroupViewCd() {
		return holdingBusinessGroupViewCd;
	}

	/**
	 * Setter method for property <tt>holdingBusinessGroupViewCd</tt>.
	 * 
	 * @param holdingBusinessGroupViewCd
	 *            value to be assigned to property holdingBusinessGroupViewCd
	 */
	public void setHoldingBusinessGroupViewCd(String holdingBusinessGroupViewCd) {
		this.holdingBusinessGroupViewCd = holdingBusinessGroupViewCd;
	}

	/**
	 * Getter method for property <tt>holdingViewCd</tt>.
	 * 
	 * @return property value of holdingViewCd
	 */
	public String getHoldingViewCd() {
		return holdingViewCd;
	}

	/**
	 * Setter method for property <tt>holdingViewCd</tt>.
	 * 
	 * @param holdingViewCd
	 *            value to be assigned to property holdingViewCd
	 */
	public void setHoldingViewCd(String holdingViewCd) {
		this.holdingViewCd = holdingViewCd;
	}

	/**
	 * Getter method for property <tt>positionCd</tt>.
	 * 
	 * @return property value of positionCd
	 */
	public String getPositionCd() {
		return positionCd;
	}

	/**
	 * Setter method for property <tt>positionCd</tt>.
	 * 
	 * @param positionCd
	 *            value to be assigned to property positionCd
	 */
	public void setPositionCd(String positionCd) {
		this.positionCd = positionCd;
	}

	/**
	 * Getter method for property <tt>accruedIncomeAmt</tt>.
	 * 
	 * @return property value of accruedIncomeAmt
	 */
	public BigDecimal getAccruedIncomeAmt() {
		return accruedIncomeAmt;
	}

	/**
	 * Setter method for property <tt>accruedIncomeAmt</tt>.
	 * 
	 * @param accruedIncomeAmt
	 *            value to be assigned to property accruedIncomeAmt
	 */
	public void setAccruedIncomeAmt(BigDecimal accruedIncomeAmt) {
		this.accruedIncomeAmt = accruedIncomeAmt;
	}

	/**
	 * Getter method for property <tt>earnedAmortBaseAmt</tt>.
	 * 
	 * @return property value of earnedAmortBaseAmt
	 */
	public BigDecimal getEarnedAmortBaseAmt() {
		return earnedAmortBaseAmt;
	}

	/**
	 * Setter method for property <tt>earnedAmortBaseAmt</tt>.
	 * 
	 * @param earnedAmortBaseAmt
	 *            value to be assigned to property earnedAmortBaseAmt
	 */
	public void setEarnedAmortBaseAmt(BigDecimal earnedAmortBaseAmt) {
		this.earnedAmortBaseAmt = earnedAmortBaseAmt;
	}

	/**
	 * Getter method for property <tt>earnedInflCmpsBaseAmt</tt>.
	 * 
	 * @return property value of earnedInflCmpsBaseAmt
	 */
	public BigDecimal getEarnedInflCmpsBaseAmt() {
		return earnedInflCmpsBaseAmt;
	}

	/**
	 * Setter method for property <tt>earnedInflCmpsBaseAmt</tt>.
	 * 
	 * @param earnedInflCmpsBaseAmt
	 *            value to be assigned to property earnedInflCmpsBaseAmt
	 */
	public void setEarnedInflCmpsBaseAmt(BigDecimal earnedInflCmpsBaseAmt) {
		this.earnedInflCmpsBaseAmt = earnedInflCmpsBaseAmt;
	}

	/**
	 * Getter method for property <tt>marketValueBaseAmt</tt>.
	 * 
	 * @return property value of marketValueBaseAmt
	 */
	public BigDecimal getMarketValueBaseAmt() {
		return marketValueBaseAmt;
	}

	/**
	 * Setter method for property <tt>marketValueBaseAmt</tt>.
	 * 
	 * @param marketValueBaseAmt
	 *            value to be assigned to property marketValueBaseAmt
	 */
	public void setMarketValueBaseAmt(BigDecimal marketValueBaseAmt) {
		this.marketValueBaseAmt = marketValueBaseAmt;
	}

	/**
	 * Getter method for property <tt>inflationAdjShareCnt</tt>.
	 * 
	 * @return property value of inflationAdjShareCnt
	 */
	public BigDecimal getInflationAdjShareCnt() {
		return inflationAdjShareCnt;
	}

	/**
	 * Setter method for property <tt>inflationAdjShareCnt</tt>.
	 * 
	 * @param inflationAdjShareCnt
	 *            value to be assigned to property inflationAdjShareCnt
	 */
	public void setInflationAdjShareCnt(BigDecimal inflationAdjShareCnt) {
		this.inflationAdjShareCnt = inflationAdjShareCnt;
	}

	/**
	 * Getter method for property <tt>settleShareCnt</tt>.
	 * 
	 * @return property value of settleShareCnt
	 */
	public BigDecimal getSettleShareCnt() {
		return settleShareCnt;
	}

	/**
	 * Setter method for property <tt>settleShareCnt</tt>.
	 * 
	 * @param settleShareCnt
	 *            value to be assigned to property settleShareCnt
	 */
	public void setSettleShareCnt(BigDecimal settleShareCnt) {
		this.settleShareCnt = settleShareCnt;
	}

	/**
	 * Getter method for property <tt>originalFaceShareQty</tt>.
	 * 
	 * @return property value of originalFaceShareQty
	 */
	public BigDecimal getOriginalFaceShareQty() {
		return originalFaceShareQty;
	}

	/**
	 * Setter method for property <tt>originalFaceShareQty</tt>.
	 * 
	 * @param originalFaceShareQty
	 *            value to be assigned to property originalFaceShareQty
	 */
	public void setOriginalFaceShareQty(BigDecimal originalFaceShareQty) {
		this.originalFaceShareQty = originalFaceShareQty;
	}

	/**
	 * Getter method for property <tt>fxRt</tt>.
	 * 
	 * @return property value of fxRt
	 */
	public BigDecimal getFxRt() {
		return fxRt;
	}

	/**
	 * Setter method for property <tt>fxRt</tt>.
	 * 
	 * @param fxRt
	 *            value to be assigned to property fxRt
	 */
	public void setFxRt(BigDecimal fxRt) {
		this.fxRt = fxRt;
	}

	/**
	 * Getter method for property <tt>derSecYield1DayIncomeAmt</tt>.
	 * 
	 * @return property value of derSecYield1DayIncomeAmt
	 */
	public BigDecimal getDerSecYield1DayIncomeAmt() {
		return derSecYield1DayIncomeAmt;
	}

	/**
	 * Setter method for property <tt>derSecYield1DayIncomeAmt</tt>.
	 * 
	 * @param derSecYield1DayIncomeAmt
	 *            value to be assigned to property derSecYield1DayIncomeAmt
	 */
	public void setDerSecYield1DayIncomeAmt(BigDecimal derSecYield1DayIncomeAmt) {
		this.derSecYield1DayIncomeAmt = derSecYield1DayIncomeAmt;
	}

	/**
	 * Getter method for property <tt>adjDerSecYield1ayIncAmt</tt>.
	 * 
	 * @return property value of adjDerSecYield1ayIncAmt
	 */
	public BigDecimal getAdjDerSecYield1ayIncAmt() {
		return adjDerSecYield1ayIncAmt;
	}

	/**
	 * Setter method for property <tt>adjDerSecYield1ayIncAmt</tt>.
	 * 
	 * @param adjDerSecYield1ayIncAmt
	 *            value to be assigned to property adjDerSecYield1ayIncAmt
	 */
	public void setAdjDerSecYield1ayIncAmt(BigDecimal adjDerSecYield1ayIncAmt) {
		this.adjDerSecYield1ayIncAmt = adjDerSecYield1ayIncAmt;
	}

	/**
	 * Getter method for property <tt>adjDerMarketValueBaseAmt</tt>.
	 * 
	 * @return property value of adjDerMarketValueBaseAmt
	 */
	public BigDecimal getAdjDerMarketValueBaseAmt() {
		return adjDerMarketValueBaseAmt;
	}

	/**
	 * Setter method for property <tt>adjDerMarketValueBaseAmt</tt>.
	 * 
	 * @param adjDerMarketValueBaseAmt
	 *            value to be assigned to property adjDerMarketValueBaseAmt
	 */
	public void setAdjDerMarketValueBaseAmt(BigDecimal adjDerMarketValueBaseAmt) {
		this.adjDerMarketValueBaseAmt = adjDerMarketValueBaseAmt;
	}

	/**
	 * Getter method for property <tt>adjSettleShareCnt</tt>.
	 * 
	 * @return property value of adjSettleShareCnt
	 */
	public BigDecimal getAdjSettleShareCnt() {
		return adjSettleShareCnt;
	}

	/**
	 * Setter method for property <tt>adjSettleShareCnt</tt>.
	 * 
	 * @param adjSettleShareCnt
	 *            value to be assigned to property adjSettleShareCnt
	 */
	public void setAdjSettleShareCnt(BigDecimal adjSettleShareCnt) {
		this.adjSettleShareCnt = adjSettleShareCnt;
	}

	/**
	 * Getter method for property <tt>adjAccruedIncomeAmt</tt>.
	 * 
	 * @return property value of adjAccruedIncomeAmt
	 */
	public BigDecimal getAdjAccruedIncomeAmt() {
		return adjAccruedIncomeAmt;
	}

	/**
	 * Setter method for property <tt>adjAccruedIncomeAmt</tt>.
	 * 
	 * @param adjAccruedIncomeAmt
	 *            value to be assigned to property adjAccruedIncomeAmt
	 */
	public void setAdjAccruedIncomeAmt(BigDecimal adjAccruedIncomeAmt) {
		this.adjAccruedIncomeAmt = adjAccruedIncomeAmt;
	}

	/**
	 * Getter method for property <tt>adjEarnedAmortBaseAmt</tt>.
	 * 
	 * @return property value of adjEarnedAmortBaseAmt
	 */
	public BigDecimal getAdjEarnedAmortBaseAmt() {
		return adjEarnedAmortBaseAmt;
	}

	/**
	 * Setter method for property <tt>adjEarnedAmortBaseAmt</tt>.
	 * 
	 * @param adjEarnedAmortBaseAmt
	 *            value to be assigned to property adjEarnedAmortBaseAmt
	 */
	public void setAdjEarnedAmortBaseAmt(BigDecimal adjEarnedAmortBaseAmt) {
		this.adjEarnedAmortBaseAmt = adjEarnedAmortBaseAmt;
	}

	/**
	 * Getter method for property <tt>adjEarnedInflCmpsBaseAmt</tt>.
	 * 
	 * @return property value of adjEarnedInflCmpsBaseAmt
	 */
	public BigDecimal getAdjEarnedInflCmpsBaseAmt() {
		return adjEarnedInflCmpsBaseAmt;
	}

	/**
	 * Setter method for property <tt>adjEarnedInflCmpsBaseAmt</tt>.
	 * 
	 * @param adjEarnedInflCmpsBaseAmt
	 *            value to be assigned to property adjEarnedInflCmpsBaseAmt
	 */
	public void setAdjEarnedInflCmpsBaseAmt(BigDecimal adjEarnedInflCmpsBaseAmt) {
		this.adjEarnedInflCmpsBaseAmt = adjEarnedInflCmpsBaseAmt;
	}

	/**
	 * Getter method for property <tt>adjCommentsTxt</tt>.
	 * 
	 * @return property value of adjCommentsTxt
	 */
	public String getAdjCommentsTxt() {
		return adjCommentsTxt;
	}

	/**
	 * Setter method for property <tt>adjCommentsTxt</tt>.
	 * 
	 * @param adjCommentsTxt
	 *            value to be assigned to property adjCommentsTxt
	 */
	public void setAdjCommentsTxt(String adjCommentsTxt) {
		this.adjCommentsTxt = adjCommentsTxt;
	}
}
