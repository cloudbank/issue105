/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The portfolio snapshot for the given report date.
 * 
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class PortfolioSnapshot extends Auditable {

	/**
	 * Surrogate id to uniquely identify a portfolio snapshot.
	 */
	@ColumnName("PORTFOLIO_SNAPSHOT_SID")
	private long portfolioSnapshotSid;

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
	 * A numeric value indicating the level_number of a fund within in the FoF
	 * Hierarchy. Used to figure out the order of yield/income calculations. The
	 * calculations are done for the lowest level fund in Fund of fund hierarchy
	 * and then move to the parent funds in the order specified in this fields.
	 */
	@ColumnName("DER_PORTFOLIO_TIER_NBR")
	private Long derPortfolioTierNbr;

	/**
	 * Total portfolio/fund level Expenses to hit a portfolio that may NOT be
	 * distributed to the individual classes.
	 */
	@ColumnName("FDR_SEC_EXPENSE_AMT")
	private BigDecimal expenseAmt;

	/**
	 * Total SEC Yield Income for the portfolio. i.e. income calculated using
	 * the Yield as an input.
	 */
	@ColumnName("FDR_SEC_TOTAL_INCOME_AMT")
	private BigDecimal derTotalIncomeAmt;

	/**
	 * Der sec 1 day yield amt.
	 */
	@ColumnName("DER_SEC_1_DAY_YIELD_PCT")
	private BigDecimal derSec1DayYieldAmt;

	/**
	 * 30 Day SEC Yield as calculated in SEC yield application (usually Income
	 * less expenses divided by shares outstanding multiplied by 365).
	 */
	@ColumnName("DER_SEC_30_DAY_YIELD_PCT")
	private BigDecimal derSec30DayYieldAmt;

	/**
	 * 30 Day SEC Yield amount as calculated in SEC yield application. This is
	 * the Average of 1 day Fund SEC Yield over the past 30 days.
	 */
	@ColumnName("DER_SEC_RESTATED_YIELD_PCT")
	private BigDecimal derSecRestartedYieldAmt;

	/**
	 * Net adjustment to the Portfolio level Mil entered by user. For intra-day
	 * updates, only the latest updates are stored here. for interim intra-day
	 * adjustments there is a separate Audit table (Event_log).
	 */
	@ColumnName("FDR_SEC_MIL_ADJ_PCT")
	private BigDecimal fdrSecYieldAdjAmt;

	/**
	 * Sum of all class level GROSS_INCOME_UNMODIFIED in order to get a fund
	 * level Out Of State Income.
	 */
	@ColumnName("FDR_N1A_TOT_GROS_INC_UNMOD_AMT")
	private BigDecimal fdrN1ATotGrosIncUnmodAmt;

	/**
	 * The total amount of income generated for specific OOS (Out of State
	 * Paper) securities held on Money Market Funds.
	 */
	@ColumnName("FDR_N1A_OOSP_DIST_INCOME_AMT")
	private BigDecimal fdrN1AOospDistIncomeAmt;

	/**
	 * The total amount of amortization generated for specific OOSP (Out of
	 * State Paper) securities held on Money Market Funds.
	 */
	@ColumnName("FDR_N1A_OOSP_DIST_AMORT_AMT")
	private BigDecimal fdrN1AOospDistAmortAmt;

	/**
	 * This number is used in multiple calculations in order to show Taxable
	 * Distributable Income and takes into account Distribution Amortization
	 * amount to calculate Distribution Income. (Its
	 * DER_N1A_FUNDLEVEL_OOSP_DISTRIBUTABLE_INCOME_AMT +
	 * DER_N1A_FUNDLEVEL_OOSP_DISTRIBUTABLE_AMORT_AMT).
	 */
	@ColumnName("FDR_N1A_NET_OOSP_DIST_INC_AMT")
	private BigDecimal fdrN1ANetOospDistIncAmt;

	/**
	 * The percentage of gross distributable income that occurred on Out of
	 * State Paper.
	 */
	@ColumnName("FDR_N1A_OOSP_GROS_DIST_INC_PCT")
	private BigDecimal fdrN1AOospGrosDistInc;

	/**
	 * Tax rate applied to the portfolio/fund for the income coming from
	 * non-origin state/locations of the fund. (This rate does not apply to the
	 * States that are listed in PORTFOLIO_STATE_EXCLUSION list). Until an
	 * automated source for this is found, the rated will be updated by business
	 * user once a year.
	 */
	@ColumnName("FDR_PORTFOLIO_STATE_TAX_RT")
	private BigDecimal fdrPortfolioStateTaxRt;

	/**
	 * 1 Day SEC Yield as calculated in SEC yield application (usually Income
	 * less expenses divided by shares outstanding multiplied by 365).
	 */
	@ColumnName("ADJ_DER_SEC_1_DAY_YIELD_PCT")
	private BigDecimal adjDerSec1DayYieldAmt;

	/**
	 * 30 Day SEC Yield as calculated in SEC yield application (usually Income
	 * less expenses divided by shares outstanding multiplied by 365).
	 */
	@ColumnName("ADJ_DER_SEC_30_DAY_YIELD_PCT")
	private BigDecimal adjDerSec30DayYieldAmt;

	/**
	 * The percent change between today's Fund Level SEC Yield and prior month
	 * end's SEC YIELD.
	 */
	@ColumnName("DER_SEC_YIELD_PME_CHG_PCT")
	private BigDecimal derFundSecYieldPmeChgPct;

	/**
	 * The percent change between today's Restated SEC Yield and prior month
	 * end's SEC YIELD.
	 */
	@ColumnName("DER_RST_SEC_YIELD_PME_CHG_PCT")
	private BigDecimal derRstSecYieldPmeChgPct;

	/**
	 * Percent change on the Restated 7 Day Yield From Prior Month End.
	 */
	@ColumnName("DER_MM_7_DAY_YLD_PME_CHG_PCT")
	private BigDecimal derMnyMkt7DayYieldPmeChgPct;

	/**
	 * Percent change on the Restated 30 Day Yield From Prior Month End.
	 */
	@ColumnName("DER_MM_30_DAY_YLD_PME_CHG_PCT")
	private BigDecimal derMnyMkt30DayYieldPmeChgPct;

	/**
	 * Day Over Day Gross Yield Change.
	 */
	@ColumnName("DER_MM_GROSS_YIELD_DOD_CHG_PCT")
	private BigDecimal derMnyMktGrossYieldDodChgPct;

	/**
	 * Day Over Day Restated 7 Day Yield Change.
	 */
	@ColumnName("DER_MM_RST_7D_YLD_DOD_CHG_PCT")
	private BigDecimal derMnyMktRst7DayYieldDodChgPct;

	/**
	 * Class to Class Compare 1 Day Yield.
	 */
	@ColumnName("DER_DIST_1D_YLD_C2C_CMPR_PCT")
	private BigDecimal derDist1DayYieldC2CCmprPct;

	/**
	 * Class to Class Compare 30 Day Yield.
	 */
	@ColumnName("DER_DIST_30D_YLD_C2C_CMPR_PCT")
	private BigDecimal derDist30DayYieldC2CCmprPct;

	/**
	 * Class Level 1 Day Yield Change From Prior Month End.
	 */
	@ColumnName("DER_DIST_1_DAY_YLD_PME_CHG_PCT")
	private BigDecimal derDist1DayYieldPmeChgPct;

	/**
	 * Class Level 30 Day Yield Change From Prior Month End.
	 */
	@ColumnName("DER_DIST_30D_YLD_CHG_PME_PCT")
	private BigDecimal derDist30DayYieldChgPmePct;

	/**
	 * Class Level 12 Month Yield Change From Prior Month End.
	 */
	@ColumnName("DER_DIST_12MO_YLD_CHG_PME_PCT")
	private BigDecimal derDist12MoYieldChgPmePct;

	/**
	 * For Money Market Funds, Gross Yield shows distributable income, less
	 * expenses for a given class. Ignores waivers and reimbursements.
	 */
	@ColumnName("MM_1DAY_GROSS_YIELD_PCT")
	private BigDecimal mnyMkt1DayGrossYieldPct;

	/**
	 * For Money Market Funds, Distribution Yield shows distributable Income.
	 */
	@ColumnName("MM_1DAY_DIST_YIELD_PCT")
	private BigDecimal mnyMkt1DayDistYieldPct;

	/**
	 * User entered field, determines the rate that out of state paper is taxed
	 * at for the fund in question.
	 */
	@ColumnName("MM_N1A_STATE_TAX_RT")
	private BigDecimal mnyMktN1AStateTaxRt;

	/**
	 * Sum of all class level shares held for a mutual fund (All classes
	 * together = fund level value).
	 */
	@ColumnName("FDR_DISTRIBUTABLE_CAPSTOCK_QTY")
	private BigDecimal fdrDistributableCapstockQty;

	/**
	 * The daily income rate for an SEC Yield Mutual Fund.
	 */
	@ColumnName("DER_SEC_1_DAY_MIL_RT")
	private BigDecimal der1DaySecMilRt;

	/**
	 * The average of the previous 30 day's SEC Yield Mutual Fund.
	 */
	@ColumnName("DER_SEC_30_DAY_MIL_RT")
	private BigDecimal der30DaySecMilRt;

	/**
	 * Constructor.
	 */
	public PortfolioSnapshot() {
		// default empty constructor
	}

	/**
	 * Getter method for property <tt>portfolioSnapshotSid</tt>.
	 * 
	 * @return property value of portfolioSnapshotSid
	 */
	public long getPortfolioSnapshotSid() {
		return portfolioSnapshotSid;
	}

	/**
	 * Setter method for property <tt>portfolioSnapshotSid</tt>.
	 * 
	 * @param portfolioSnapshotSid
	 *            value to be assigned to property portfolioSnapshotSid
	 */
	public void setPortfolioSnapshotSid(long portfolioSnapshotSid) {
		this.portfolioSnapshotSid = portfolioSnapshotSid;
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
	 * Getter method for property <tt>derPortfolioTierNbr</tt>.
	 * 
	 * @return property value of derPortfolioTierNbr
	 */
	public Long getDerPortfolioTierNbr() {
		return derPortfolioTierNbr;
	}

	/**
	 * Setter method for property <tt>derPortfolioTierNbr</tt>.
	 * 
	 * @param derPortfolioTierNbr
	 *            value to be assigned to property derPortfolioTierNbr
	 */
	public void setDerPortfolioTierNbr(Long derPortfolioTierNbr) {
		this.derPortfolioTierNbr = derPortfolioTierNbr;
	}

	/**
	 * Getter method for property <tt>expenseAmt</tt>.
	 * 
	 * @return property value of expenseAmt
	 */
	public BigDecimal getExpenseAmt() {
		return expenseAmt;
	}

	/**
	 * Setter method for property <tt>expenseAmt</tt>.
	 * 
	 * @param expenseAmt
	 *            value to be assigned to property expenseAmt
	 */
	public void setExpenseAmt(BigDecimal expenseAmt) {
		this.expenseAmt = expenseAmt;
	}

	/**
	 * Getter method for property <tt>derTotalIncomeAmt</tt>.
	 * 
	 * @return property value of derTotalIncomeAmt
	 */
	public BigDecimal getDerTotalIncomeAmt() {
		return derTotalIncomeAmt;
	}

	/**
	 * Setter method for property <tt>derTotalIncomeAmt</tt>.
	 * 
	 * @param derTotalIncomeAmt
	 *            value to be assigned to property derTotalIncomeAmt
	 */
	public void setDerTotalIncomeAmt(BigDecimal derTotalIncomeAmt) {
		this.derTotalIncomeAmt = derTotalIncomeAmt;
	}

	/**
	 * Getter method for property <tt>derSec1DayYieldAmt</tt>.
	 * 
	 * @return property value of derSec1DayYieldAmt
	 */
	public BigDecimal getDerSec1DayYieldAmt() {
		return derSec1DayYieldAmt;
	}

	/**
	 * Setter method for property <tt>derSec1DayYieldAmt</tt>.
	 * 
	 * @param derSec1DayYieldAmt
	 *            value to be assigned to property derSec1DayYieldAmt
	 */
	public void setDerSec1DayYieldAmt(BigDecimal derSec1DayYieldAmt) {
		this.derSec1DayYieldAmt = derSec1DayYieldAmt;
	}

	/**
	 * Getter method for property <tt>derSec30DayYieldAmt</tt>.
	 * 
	 * @return property value of derSec30DayYieldAmt
	 */
	public BigDecimal getDerSec30DayYieldAmt() {
		return derSec30DayYieldAmt;
	}

	/**
	 * Setter method for property <tt>derSec30DayYieldAmt</tt>.
	 * 
	 * @param derSec30DayYieldAmt
	 *            value to be assigned to property derSec30DayYieldAmt
	 */
	public void setDerSec30DayYieldAmt(BigDecimal derSec30DayYieldAmt) {
		this.derSec30DayYieldAmt = derSec30DayYieldAmt;
	}

	/**
	 * Getter method for property <tt>derSecRestartedYieldAmt</tt>.
	 * 
	 * @return property value of derSecRestartedYieldAmt
	 */
	public BigDecimal getDerSecRestartedYieldAmt() {
		return derSecRestartedYieldAmt;
	}

	/**
	 * Setter method for property <tt>derSecRestartedYieldAmt</tt>.
	 * 
	 * @param derSecRestartedYieldAmt
	 *            value to be assigned to property derSecRestartedYieldAmt
	 */
	public void setDerSecRestartedYieldAmt(BigDecimal derSecRestartedYieldAmt) {
		this.derSecRestartedYieldAmt = derSecRestartedYieldAmt;
	}

	/**
	 * Getter method for property <tt>fdrSecYieldAdjAmt</tt>.
	 * 
	 * @return property value of fdrSecYieldAdjAmt
	 */
	public BigDecimal getFdrSecYieldAdjAmt() {
		return fdrSecYieldAdjAmt;
	}

	/**
	 * Setter method for property <tt>fdrSecYieldAdjAmt</tt>.
	 * 
	 * @param fdrSecYieldAdjAmt
	 *            value to be assigned to property fdrSecYieldAdjAmt
	 */
	public void setFdrSecYieldAdjAmt(BigDecimal fdrSecYieldAdjAmt) {
		this.fdrSecYieldAdjAmt = fdrSecYieldAdjAmt;
	}

	/**
	 * Getter method for property <tt>fdrN1ATotGrosIncUnmodAmt</tt>.
	 * 
	 * @return property value of fdrN1ATotGrosIncUnmodAmt
	 */
	public BigDecimal getFdrN1ATotGrosIncUnmodAmt() {
		return fdrN1ATotGrosIncUnmodAmt;
	}

	/**
	 * Setter method for property <tt>fdrN1ATotGrosIncUnmodAmt</tt>.
	 * 
	 * @param fdrN1ATotGrosIncUnmodAmt
	 *            value to be assigned to property fdrN1ATotGrosIncUnmodAmt
	 */
	public void setFdrN1ATotGrosIncUnmodAmt(BigDecimal fdrN1ATotGrosIncUnmodAmt) {
		this.fdrN1ATotGrosIncUnmodAmt = fdrN1ATotGrosIncUnmodAmt;
	}

	/**
	 * Getter method for property <tt>fdrN1AOospDistIncomeAmt</tt>.
	 * 
	 * @return property value of fdrN1AOospDistIncomeAmt
	 */
	public BigDecimal getFdrN1AOospDistIncomeAmt() {
		return fdrN1AOospDistIncomeAmt;
	}

	/**
	 * Setter method for property <tt>fdrN1AOospDistIncomeAmt</tt>.
	 * 
	 * @param fdrN1AOospDistIncomeAmt
	 *            value to be assigned to property fdrN1AOospDistIncomeAmt
	 */
	public void setFdrN1AOospDistIncomeAmt(BigDecimal fdrN1AOospDistIncomeAmt) {
		this.fdrN1AOospDistIncomeAmt = fdrN1AOospDistIncomeAmt;
	}

	/**
	 * Getter method for property <tt>fdrN1AOospDistAmortAmt</tt>.
	 * 
	 * @return property value of fdrN1AOospDistAmortAmt
	 */
	public BigDecimal getFdrN1AOospDistAmortAmt() {
		return fdrN1AOospDistAmortAmt;
	}

	/**
	 * Setter method for property <tt>fdrN1AOospDistAmortAmt</tt>.
	 * 
	 * @param fdrN1AOospDistAmortAmt
	 *            value to be assigned to property fdrN1AOospDistAmortAmt
	 */
	public void setFdrN1AOospDistAmortAmt(BigDecimal fdrN1AOospDistAmortAmt) {
		this.fdrN1AOospDistAmortAmt = fdrN1AOospDistAmortAmt;
	}

	/**
	 * Getter method for property <tt>fdrN1ANetOospDistIncAmt</tt>.
	 * 
	 * @return property value of fdrN1ANetOospDistIncAmt
	 */
	public BigDecimal getFdrN1ANetOospDistIncAmt() {
		return fdrN1ANetOospDistIncAmt;
	}

	/**
	 * Setter method for property <tt>fdrN1ANetOospDistIncAmt</tt>.
	 * 
	 * @param fdrN1ANetOospDistIncAmt
	 *            value to be assigned to property fdrN1ANetOospDistIncAmt
	 */
	public void setFdrN1ANetOospDistIncAmt(BigDecimal fdrN1ANetOospDistIncAmt) {
		this.fdrN1ANetOospDistIncAmt = fdrN1ANetOospDistIncAmt;
	}

	/**
	 * Getter method for property <tt>fdrN1AOospGrosDistInc</tt>.
	 * 
	 * @return property value of fdrN1AOospGrosDistInc
	 */
	public BigDecimal getFdrN1AOospGrosDistInc() {
		return fdrN1AOospGrosDistInc;
	}

	/**
	 * Setter method for property <tt>fdrN1AOospGrosDistInc</tt>.
	 * 
	 * @param fdrN1AOospGrosDistInc
	 *            value to be assigned to property fdrN1AOospGrosDistInc
	 */
	public void setFdrN1AOospGrosDistInc(BigDecimal fdrN1AOospGrosDistInc) {
		this.fdrN1AOospGrosDistInc = fdrN1AOospGrosDistInc;
	}

	/**
	 * Getter method for property <tt>fdrPortfolioStateTaxRt</tt>.
	 * 
	 * @return property value of fdrPortfolioStateTaxRt
	 */
	public BigDecimal getFdrPortfolioStateTaxRt() {
		return fdrPortfolioStateTaxRt;
	}

	/**
	 * Setter method for property <tt>fdrPortfolioStateTaxRt</tt>.
	 * 
	 * @param fdrPortfolioStateTaxRt
	 *            value to be assigned to property fdrPortfolioStateTaxRt
	 */
	public void setFdrPortfolioStateTaxRt(BigDecimal fdrPortfolioStateTaxRt) {
		this.fdrPortfolioStateTaxRt = fdrPortfolioStateTaxRt;
	}

	/**
	 * Getter method for property <tt>adjDerSec1DayYieldAmt</tt>.
	 * 
	 * @return property value of adjDerSec1DayYieldAmt
	 */
	public BigDecimal getAdjDerSec1DayYieldAmt() {
		return adjDerSec1DayYieldAmt;
	}

	/**
	 * Setter method for property <tt>adjDerSec1DayYieldAmt</tt>.
	 * 
	 * @param adjDerSec1DayYieldAmt
	 *            value to be assigned to property adjDerSec1DayYieldAmt
	 */
	public void setAdjDerSec1DayYieldAmt(BigDecimal adjDerSec1DayYieldAmt) {
		this.adjDerSec1DayYieldAmt = adjDerSec1DayYieldAmt;
	}

	/**
	 * Getter method for property <tt>adjDerSec30DayYieldAmt</tt>.
	 * 
	 * @return property value of adjDerSec30DayYieldAmt
	 */
	public BigDecimal getAdjDerSec30DayYieldAmt() {
		return adjDerSec30DayYieldAmt;
	}

	/**
	 * Setter method for property <tt>adjDerSec30DayYieldAmt</tt>.
	 * 
	 * @param adjDerSec30DayYieldAmt
	 *            value to be assigned to property adjDerSec30DayYieldAmt
	 */
	public void setAdjDerSec30DayYieldAmt(BigDecimal adjDerSec30DayYieldAmt) {
		this.adjDerSec30DayYieldAmt = adjDerSec30DayYieldAmt;
	}

	/**
	 * Getter method for property <tt>derFundSecYieldPmeChgPct</tt>.
	 * 
	 * @return property value of derFundSecYieldPmeChgPct
	 */
	public BigDecimal getDerFundSecYieldPmeChgPct() {
		return derFundSecYieldPmeChgPct;
	}

	/**
	 * Setter method for property <tt>derFundSecYieldPmeChgPct</tt>.
	 * 
	 * @param derFundSecYieldPmeChgPct
	 *            value to be assigned to property derFundSecYieldPmeChgPct
	 */
	public void setDerFundSecYieldPmeChgPct(BigDecimal derFundSecYieldPmeChgPct) {
		this.derFundSecYieldPmeChgPct = derFundSecYieldPmeChgPct;
	}

	/**
	 * Getter method for property <tt>derRstSecYieldPmeChgPct</tt>.
	 * 
	 * @return property value of derRstSecYieldPmeChgPct
	 */
	public BigDecimal getDerRstSecYieldPmeChgPct() {
		return derRstSecYieldPmeChgPct;
	}

	/**
	 * Setter method for property <tt>derRstSecYieldPmeChgPct</tt>.
	 * 
	 * @param derRstSecYieldPmeChgPct
	 *            value to be assigned to property derRstSecYieldPmeChgPct
	 */
	public void setDerRstSecYieldPmeChgPct(BigDecimal derRstSecYieldPmeChgPct) {
		this.derRstSecYieldPmeChgPct = derRstSecYieldPmeChgPct;
	}

	/**
	 * Getter method for property <tt>derMnyMkt7DayYieldPmeChgPct</tt>.
	 * 
	 * @return property value of derMnyMkt7DayYieldPmeChgPct
	 */
	public BigDecimal getDerMnyMkt7DayYieldPmeChgPct() {
		return derMnyMkt7DayYieldPmeChgPct;
	}

	/**
	 * Setter method for property <tt>derMnyMkt7DayYieldPmeChgPct</tt>.
	 * 
	 * @param derMnyMkt7DayYieldPmeChgPct
	 *            value to be assigned to property derMnyMkt7DayYieldPmeChgPct
	 */
	public void setDerMnyMkt7DayYieldPmeChgPct(BigDecimal derMnyMkt7DayYieldPmeChgPct) {
		this.derMnyMkt7DayYieldPmeChgPct = derMnyMkt7DayYieldPmeChgPct;
	}

	/**
	 * Getter method for property <tt>derMnyMkt30DayYieldPmeChgPct</tt>.
	 * 
	 * @return property value of derMnyMkt30DayYieldPmeChgPct
	 */
	public BigDecimal getDerMnyMkt30DayYieldPmeChgPct() {
		return derMnyMkt30DayYieldPmeChgPct;
	}

	/**
	 * Setter method for property <tt>derMnyMkt30DayYieldPmeChgPct</tt>.
	 * 
	 * @param derMnyMkt30DayYieldPmeChgPct
	 *            value to be assigned to property derMnyMkt30DayYieldPmeChgPct
	 */
	public void setDerMnyMkt30DayYieldPmeChgPct(BigDecimal derMnyMkt30DayYieldPmeChgPct) {
		this.derMnyMkt30DayYieldPmeChgPct = derMnyMkt30DayYieldPmeChgPct;
	}

	/**
	 * Getter method for property <tt>derMnyMktGrossYieldDodChgPct</tt>.
	 * 
	 * @return property value of derMnyMktGrossYieldDodChgPct
	 */
	public BigDecimal getDerMnyMktGrossYieldDodChgPct() {
		return derMnyMktGrossYieldDodChgPct;
	}

	/**
	 * Setter method for property <tt>derMnyMktGrossYieldDodChgPct</tt>.
	 * 
	 * @param derMnyMktGrossYieldDodChgPct
	 *            value to be assigned to property derMnyMktGrossYieldDodChgPct
	 */
	public void setDerMnyMktGrossYieldDodChgPct(BigDecimal derMnyMktGrossYieldDodChgPct) {
		this.derMnyMktGrossYieldDodChgPct = derMnyMktGrossYieldDodChgPct;
	}

	/**
	 * Getter method for property <tt>derMnyMktRst7DayYieldDodChgPct</tt>.
	 * 
	 * @return property value of derMnyMktRst7DayYieldDodChgPct
	 */
	public BigDecimal getDerMnyMktRst7DayYieldDodChgPct() {
		return derMnyMktRst7DayYieldDodChgPct;
	}

	/**
	 * Setter method for property <tt>derMnyMktRst7DayYieldDodChgPct</tt>.
	 * 
	 * @param derMnyMktRst7DayYieldDodChgPct
	 *            value to be assigned to property
	 *            derMnyMktRst7DayYieldDodChgPct
	 */
	public void setDerMnyMktRst7DayYieldDodChgPct(BigDecimal derMnyMktRst7DayYieldDodChgPct) {
		this.derMnyMktRst7DayYieldDodChgPct = derMnyMktRst7DayYieldDodChgPct;
	}

	/**
	 * Getter method for property <tt>derDist1DayYieldC2CCmprPct</tt>.
	 * 
	 * @return property value of derDist1DayYieldC2CCmprPct
	 */
	public BigDecimal getDerDist1DayYieldC2CCmprPct() {
		return derDist1DayYieldC2CCmprPct;
	}

	/**
	 * Setter method for property <tt>derDist1DayYieldC2CCmprPct</tt>.
	 * 
	 * @param derDist1DayYieldC2CCmprPct
	 *            value to be assigned to property derDist1DayYieldC2CCmprPct
	 */
	public void setDerDist1DayYieldC2CCmprPct(BigDecimal derDist1DayYieldC2CCmprPct) {
		this.derDist1DayYieldC2CCmprPct = derDist1DayYieldC2CCmprPct;
	}

	/**
	 * Getter method for property <tt>derDist30DayYieldC2CCmprPct</tt>.
	 * 
	 * @return property value of derDist30DayYieldC2CCmprPct
	 */
	public BigDecimal getDerDist30DayYieldC2CCmprPct() {
		return derDist30DayYieldC2CCmprPct;
	}

	/**
	 * Setter method for property <tt>derDist30DayYieldC2CCmprPct</tt>.
	 * 
	 * @param derDist30DayYieldC2CCmprPct
	 *            value to be assigned to property derDist30DayYieldC2CCmprPct
	 */
	public void setDerDist30DayYieldC2CCmprPct(BigDecimal derDist30DayYieldC2CCmprPct) {
		this.derDist30DayYieldC2CCmprPct = derDist30DayYieldC2CCmprPct;
	}

	/**
	 * Getter method for property <tt>derDist1DayYieldPmeChgPct</tt>.
	 * 
	 * @return property value of derDist1DayYieldPmeChgPct
	 */
	public BigDecimal getDerDist1DayYieldPmeChgPct() {
		return derDist1DayYieldPmeChgPct;
	}

	/**
	 * Setter method for property <tt>derDist1DayYieldPmeChgPct</tt>.
	 * 
	 * @param derDist1DayYieldPmeChgPct
	 *            value to be assigned to property derDist1DayYieldPmeChgPct
	 */
	public void setDerDist1DayYieldPmeChgPct(BigDecimal derDist1DayYieldPmeChgPct) {
		this.derDist1DayYieldPmeChgPct = derDist1DayYieldPmeChgPct;
	}

	/**
	 * Getter method for property <tt>derDist30DayYieldChgPmePct</tt>.
	 * 
	 * @return property value of derDist30DayYieldChgPmePct
	 */
	public BigDecimal getDerDist30DayYieldChgPmePct() {
		return derDist30DayYieldChgPmePct;
	}

	/**
	 * Setter method for property <tt>derDist30DayYieldChgPmePct</tt>.
	 * 
	 * @param derDist30DayYieldChgPmePct
	 *            value to be assigned to property derDist30DayYieldChgPmePct
	 */
	public void setDerDist30DayYieldChgPmePct(BigDecimal derDist30DayYieldChgPmePct) {
		this.derDist30DayYieldChgPmePct = derDist30DayYieldChgPmePct;
	}

	/**
	 * Getter method for property <tt>derDist12MoYieldChgPmePct</tt>.
	 * 
	 * @return property value of derDist12MoYieldChgPmePct
	 */
	public BigDecimal getDerDist12MoYieldChgPmePct() {
		return derDist12MoYieldChgPmePct;
	}

	/**
	 * Setter method for property <tt>derDist12MoYieldChgPmePct</tt>.
	 * 
	 * @param derDist12MoYieldChgPmePct
	 *            value to be assigned to property derDist12MoYieldChgPmePct
	 */
	public void setDerDist12MoYieldChgPmePct(BigDecimal derDist12MoYieldChgPmePct) {
		this.derDist12MoYieldChgPmePct = derDist12MoYieldChgPmePct;
	}

	/**
	 * Getter method for property <tt>mnyMkt1DayGrossYieldPct</tt>.
	 * 
	 * @return property value of mnyMkt1DayGrossYieldPct
	 */
	public BigDecimal getMnyMkt1DayGrossYieldPct() {
		return mnyMkt1DayGrossYieldPct;
	}

	/**
	 * Setter method for property <tt>mnyMkt1DayGrossYieldPct</tt>.
	 * 
	 * @param mnyMkt1DayGrossYieldPct
	 *            value to be assigned to property mnyMkt1DayGrossYieldPct
	 */
	public void setMnyMkt1DayGrossYieldPct(BigDecimal mnyMkt1DayGrossYieldPct) {
		this.mnyMkt1DayGrossYieldPct = mnyMkt1DayGrossYieldPct;
	}

	/**
	 * Getter method for property <tt>mnyMkt1DayDistYieldPct</tt>.
	 * 
	 * @return property value of mnyMkt1DayDistYieldPct
	 */
	public BigDecimal getMnyMkt1DayDistYieldPct() {
		return mnyMkt1DayDistYieldPct;
	}

	/**
	 * Setter method for property <tt>mnyMkt1DayDistYieldPct</tt>.
	 * 
	 * @param mnyMkt1DayDistYieldPct
	 *            value to be assigned to property mnyMkt1DayDistYieldPct
	 */
	public void setMnyMkt1DayDistYieldPct(BigDecimal mnyMkt1DayDistYieldPct) {
		this.mnyMkt1DayDistYieldPct = mnyMkt1DayDistYieldPct;
	}

	/**
	 * Getter method for property <tt>mnyMktN1AStateTaxRt</tt>.
	 * 
	 * @return property value of mnyMktN1AStateTaxRt
	 */
	public BigDecimal getMnyMktN1AStateTaxRt() {
		return mnyMktN1AStateTaxRt;
	}

	/**
	 * Setter method for property <tt>mnyMktN1AStateTaxRt</tt>.
	 * 
	 * @param mnyMktN1AStateTaxRt
	 *            value to be assigned to property mnyMktN1AStateTaxRt
	 */
	public void setMnyMktN1AStateTaxRt(BigDecimal mnyMktN1AStateTaxRt) {
		this.mnyMktN1AStateTaxRt = mnyMktN1AStateTaxRt;
	}

	/**
	 * Getter method for property <tt>der1DaySecMilRt</tt>.
	 * 
	 * @return property value of der1DaySecMilRt
	 */
	public BigDecimal getDer1DaySecMilRt() {
		return der1DaySecMilRt;
	}

	/**
	 * Setter method for property <tt>der1DaySecMilRt</tt>.
	 * 
	 * @param der1DaySecMilRt
	 *            value to be assigned to property der1DaySecMilRt
	 */
	public void setDer1DaySecMilRt(BigDecimal der1DaySecMilRt) {
		this.der1DaySecMilRt = der1DaySecMilRt;
	}

	/**
	 * Getter method for property <tt>der30DaySecMilRt</tt>.
	 * 
	 * @return property value of der30DaySecMilRt
	 */
	public BigDecimal getDer30DaySecMilRt() {
		return der30DaySecMilRt;
	}

	/**
	 * Setter method for property <tt>der30DaySecMilRt</tt>.
	 * 
	 * @param der30DaySecMilRt
	 *            value to be assigned to property der30DaySecMilRt
	 */
	public void setDer30DaySecMilRt(BigDecimal der30DaySecMilRt) {
		this.der30DaySecMilRt = der30DaySecMilRt;
	}

	/**
	 * Getter method for property <tt>fdrDistributableCapstockQty</tt>.
	 * 
	 * @return property value of fdrDistributableCapstockQty
	 */
	public BigDecimal getFdrDistributableCapstockQty() {
		return fdrDistributableCapstockQty;
	}

	/**
	 * Setter method for property <tt>fdrDistributableCapstockQty</tt>.
	 * 
	 * @param fdrDistributableCapstockQty
	 *            value to be assigned to property fdrDistributableCapstockQty
	 */
	public void setFdrDistributableCapstockQty(BigDecimal fdrDistributableCapstockQty) {
		this.fdrDistributableCapstockQty = fdrDistributableCapstockQty;
	}
}
