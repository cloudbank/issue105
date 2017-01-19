/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The share class snapshot for the given report date.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class ShareClassSnapshot extends Auditable {

    /**
     * Surrogate id to uniquely identify a share class snapshot.
     */
	@ColumnName("SHARE_CLASS_SNAPSHOT_SID")
    private long shareClassSnapshotSid;

    /**
     * Surrogate id to uniquely identify a share class.
     */
	@ColumnName("SHARE_CLASS_SID")
    private long shareClassSid;

    /**
     * Date for which calculations are being done and reported (e.g: yield/income, etc). (Usually closest business day,
     * prior to the current business day).
     */
	@ColumnName("REPORTING_DT")
    private Date reportDate;

    /**
     * The calendar date.
     */
	@ColumnName("CALENDAR_DT")
    private Date calendarDate;

    /**
     * Daily interest income rate for a class. Comes from Distributable Income.
     */
	@ColumnName("MM_1_DAY_MIL_RT")
    private BigDecimal mm1DayMilRt;

    /**
     * Daily interest income rate for a class, plus daily reimbursement. Comes from Distributable Income. If a fund is
     * in reimbursement, they have to show the what the yield would look like if the fund was not distributing.
     */
	@ColumnName("DER_MM_RESTATED_MIL_RT")
    private BigDecimal derMnyMktRestatedMilRt;

    /**
     * The Mil Rate from InvestOne usable for Distribution Yields. Pulled from upstream, not calculated in SEC Yield
     * application.
     */
	@ColumnName("DIST_YIELD_MIL_RT")
    private BigDecimal distYieldMilRt;

    /**
     * Expenses specific to an individual class, non-allocated from fund level.
     */
	@ColumnName("FDR_SEC_EXPENSE_AMT")
    private BigDecimal expenseAmt;

    /**
     * Class level expenses summed up to the portfolio.
     */
	@ColumnName("FDR_SEC_PORT_CLASS_EXP_TOT_AMT")
    private BigDecimal derTotalExpenseAmt;

    /**
     * 1 Day Yield from calculations for Money Market, pulled from upstream(Mostly IRA), not calculated in SEC Yield
     * application.
     */
	@ColumnName("MM_1_DAY_YIELD_PCT")
    private BigDecimal mnyMkt1DayYieldPct;

    /**
     * 7 day average yield derived from past 7 day yields, pulled from upstream(Mostly IRA), not calculated in SEC Yield
     * application.
     */
	@ColumnName("DER_MM_7_DAY_YIELD_PCT")
    private BigDecimal derMnyMkt7DayYieldPct;

    /**
     * 30 day average yield derived from past 30 day yields, pulled from upstream(Mostly IRA), not calculated in SEC
     * Yield application.
     */
	@ColumnName("DER_MM_30_DAY_YIELD_PCT")
    private BigDecimal derMnyMkt30DayYieldPct;

    /**
     * 7 day average yield of the 7 day average yields from the past 7 days.
     */
	@ColumnName("DER_MM_COMPOUND_7D_YIELD_PCT")
    private BigDecimal derMnyMktCompound7DayYieldPct;

    /**
     * 1 day N1-A Yield calculation, derived from 1 day money market yield.
     */
	@ColumnName("DER_MM_1_DAY_N1A_YIELD_PCT")
    private BigDecimal derMnyMkt1DayN1AYieldPct;

    /**
     * 7 day average derived from previous 7 day N1-A Yield calculations.
     */
	@ColumnName("DER_MM_7_DAY_N1A_YIELD_PCT")
    private BigDecimal derMnyMkt7DayN1AYieldPct;

    /**
     * 30 day average yield derived from past 30 day N1-A Yield calculations.
     */
	@ColumnName("DER_MM_N1A_30_DAY_YIELD_PCT")
    private BigDecimal derMnyMkt30DayN1AYieldPct;

    /**
     * The amount of Expense reimbursements pulled for a class on a given day.
     */
	@ColumnName("N1A_DIST_REIMBURSEMENT_AMT")
    private BigDecimal n1ADistReimbursementAmt;

    /**
     * Updated 7 day money market yield, based on calculations in engine, reimbursements included in calculations.
     */
	@ColumnName("DER_MM_RST_7_DAY_YIELD_PCT")
    private BigDecimal derMnyMktRst7DayYieldPct;

    /**
     * Updated 30 day money market yield, based on calculations in engine, reimbursements included in calculations.
     */
	@ColumnName("DER_MM_RST_30_DAY_YIELD_PCT")
    private BigDecimal derMnyMktRst30DayYieldPct;

    /**
     * Distributable income on Money Market fund Share classes.
     */
	@ColumnName("DER_N1A_DIST_INCOME_AMT")
    private BigDecimal derN1ADistIncomeAmt;

    /**
     * Der N1A daily mil rt.
     */
	@ColumnName("DER_N1A_DAILY_MIL_RT")
    private BigDecimal derN1ADailyMilRt;

    /**
     * Starting Distributable Income. Component in DER_DISTRIBUTABLE_INCOME, pulled from upstream(Mostly IRA), not
     * calculated in SEC Yield application.
     */
	@ColumnName("N1A_DIST_INCOME_UNMOD_AMT")
    private BigDecimal n1ADistIncomeUnmodAmt;

    /**
     * Adjustment to Distributable Income. Component in DER_DISTRIBUTABLE_INCOME, pulled from upstream(Mostly IRA), not
     * calculated in SEC Yield application.
     */
	@ColumnName("N1A_DIST_INCOME_ADJ_AMT")
    private BigDecimal n1ADistIncomeAdjAmt;

    /**
     * Breakage to Distributable Income. Component in DER_DISTRIBUTABLE_INCOME, pulled from upstream(Mostly IRA), not
     * calculated in SEC Yield application.
     */
	@ColumnName("N1A_DIST_INCOME_BREAKAGE_AMT")
    private BigDecimal n1ADistIncomeBreakageAmt;

    /**
     * Reversal to Adjustment to Distributable Income. Component in DER_DISTRIBUTABLE_INCOME, pulled from
     * upstream(Mostly IRA), not calculated in SEC Yield application.
     */
	@ColumnName("N1A_DIST_INCOME_ADJ_REV_AMT")
    private BigDecimal n1ADistIncomeAdjRevAmt;

    /**
     * 1 day distributable yield on non-mm funds, based on DAL values, allocated to class.
     */
	@ColumnName("DIST_1_DAY_YIELD_PCT")
    private BigDecimal dist1DayYieldPct;

    /**
     * Dist 30 day yield pct.
     */
	@ColumnName("DIST_30_DAY_YIELD_PCT")
    private BigDecimal dist30DayYieldPct;

    /**
     * Net Asset Value at the class level.
     */
	@ColumnName("NAV_AMT")
    private BigDecimal navAmt;

    /**
     * SEC Yield allocated down to the class level from the Portfolio/fund.
     */
	@ColumnName("DER_SEC_1_DAY_YIELD_PCT")
    private BigDecimal derSec1DayYieldPct;

    /**
     * Average of past 30 days of the 1 day class yield.
     */
	@ColumnName("DER_SEC_30_DAY_YIELD_PCT")
    private BigDecimal derSec30DayYieldPct;

    /**
     * Adjusted Average of past 30 days of the 1 day class yield.
     */
	@ColumnName("DER_SEC_RESTATED_YIELD_PCT")
    private BigDecimal derSecRestatedYieldPct;

    /**
     * Class level shares - field is called 'Dividend Earning Shares' in InvestOne/Accounting system and is tag code UT.
     */
	@ColumnName("DISTRIBUTABLE_CAPSTOCK_QTY")
    private BigDecimal distributableCapstockQty;

    /**
     * Related to DISTRIBUTABLE_INCOME_UNMODIFIED. Used with State Tax Rates and income calculations for N1-A Mil rates.
     */
	@ColumnName("N1A_GROSS_INCOME_UNMOD_AMT")
    private BigDecimal n1AGrossIncomeUnmodAmt;

    /**
     * The amount of Expense reimbursements pulled for a class on a given day.
     */
	@ColumnName("N1A_REIMBURSEMENT_EARNED_AMT")
    private BigDecimal n1AReimbursementEarnedAmt;

    /**
     * The amount of expense waivers pulled for a class on a given day.
     */
	@ColumnName("N1A_WAIVER_EARNED_AMT")
    private BigDecimal n1AWaiverEarnedAmt;

    /**
     * Class level distributable income tax generated. This is a component of multiple calculations used downstream.
     */
	@ColumnName("FDR_N1A_TAX_AMT")
    private BigDecimal fdrN1ATaxAmt;

    /**
     * Class level distributable gross income. This is gross income at a class level, less adjustments and state tax.
     */
	@ColumnName("FDR_N1A_GROSS_DIST_INCOME_AMT")
    private BigDecimal fdrN1AGrossDistIncomeAmt;

    /**
     * A user input Y/N indicator value to show if a given share_class should be excluded from the fund level
     * calculations. Y=Exclude from fund level calculations.
     */
	@ColumnName("ADJ_EXCL_SHARE_CLASS_CALC_IND")
    private String adjExclShareClassCalcInd;

    /**
     * Class level One Day Yield. Earned on a per share basis.
     */
	@ColumnName("DER_N1A_DAILY_YIELD_PCT")
    private BigDecimal derN1ADailyYieldPct;

    /**
     * The percent change between today's Class Level SEC Yield and prior month end's SEC YIELD.
     */
	@ColumnName("DER_SEC_YIELD_PME_CHG_PCT")
    private BigDecimal derSecYieldPmeChgPct;

    /**
     * Day Over Day Class Level 1 Day Distribution Yield Change.
     */
	@ColumnName("DER_MM_1D_DIST_YLD_DOD_CHG_PCT")
    private BigDecimal derMnyMkt1DayDistYieldDodChgPct;

    /**
     * Day Over Day Class Level 7 Day Distribution Yield Change.
     */
	@ColumnName("DER_MM_7D_DIST_YLD_DOD_CHG_PCT")
    private BigDecimal derMnyMkt7DayDistYieldDodChgPct;

    /**
     * Day Over Day Class Level 1 Day N1A Yield Change.
     */
	@ColumnName("DER_MM_1D_N1A_YLD_DOD_CHG_PCT")
    private BigDecimal derMnyMkt1DayN1AYieldDodChgPct;

    /**
     * Day Over Day Class Level 7 Day N1A Yield Change.
     */
	@ColumnName("DER_MM_7D_N1A_YLD_DOD_CHG_PCT")
    private BigDecimal derMnyMkt7DayN1AYieldDodChgPct;

    /**
     * This is the average of distribution yield over a 12 month period.
     */
	@ColumnName("DER_DIST_12_MO_YIELD_PCT")
    private BigDecimal derDist12MoYieldPct;

    /**
     * Modified 30 Day distribution yield. This is the number to be submitted upstream.
     */
	@ColumnName("DER_DIST_30_DAY_YIELD_PCT")
    private BigDecimal derDist30DayYieldPct;

    /**
     * 1 Day Money Market Yield. Includes Reimbursements.
     */
	@ColumnName("DER_MM_RESTATE_1_DAY_YIELD_PCT")
    private BigDecimal derMnyMktRestate1DayYieldPct;

    /**
     * The average of the previous 7 day's seven day rates.
     */
	@ColumnName("DER_MM_N1A_COMPOUND_7DAY_YIELD")
    private BigDecimal derMnyMktN1ACompound7dayYield;

    /**
     * Total of class level reimbursements. Needs to be pulled separately via DAL.
     */
	@ColumnName("SEC_REIMBURSEMENT_EARNED_AMT")
    private BigDecimal secReimbursementEarnedAmt;

    /**
     * 30 day average of past 1 day distributable yields on non-mm funds.
     */
	@ColumnName("DIST_UNMOD_30_DAY_YIELD_PCT")
    private BigDecimal distUnmod30DayYieldPct;

    /**
     * Unmodified 12 month Distribution Mil. This is a key component in the 12 month Yield.
     */
	@ColumnName("DIST_12_MO_MIL_RT")
    private BigDecimal dist12MoMilRt;

    /**
     * Constructor.
     */
    public ShareClassSnapshot() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>shareClassSnapshotSid</tt>.
     * @return property value of shareClassSnapshotSid
     */
    public long getShareClassSnapshotSid() {
        return shareClassSnapshotSid;
    }

    /**
     * Setter method for property <tt>shareClassSnapshotSid</tt>.
     * @param shareClassSnapshotSid value to be assigned to property shareClassSnapshotSid
     */
    public void setShareClassSnapshotSid(long shareClassSnapshotSid) {
        this.shareClassSnapshotSid = shareClassSnapshotSid;
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
     * Getter method for property <tt>calendarDate</tt>.
     * @return property value of calendarDate
     */
    public Date getCalendarDate() {
        return calendarDate;
    }

    /**
     * Setter method for property <tt>calendarDate</tt>.
     * @param calendarDate value to be assigned to property calendarDate
     */
    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    /**
     * Getter method for property <tt>mm1DayMilRt</tt>.
     * @return property value of mm1DayMilRt
     */
    public BigDecimal getMm1DayMilRt() {
        return mm1DayMilRt;
    }

    /**
     * Setter method for property <tt>mm1DayMilRt</tt>.
     * @param mm1DayMilRt value to be assigned to property mm1DayMilRt
     */
    public void setMm1DayMilRt(BigDecimal mm1DayMilRt) {
        this.mm1DayMilRt = mm1DayMilRt;
    }

    /**
     * Getter method for property <tt>derMnyMktRestatedMilRt</tt>.
     * @return property value of derMnyMktRestatedMilRt
     */
    public BigDecimal getDerMnyMktRestatedMilRt() {
        return derMnyMktRestatedMilRt;
    }

    /**
     * Setter method for property <tt>derMnyMktRestatedMilRt</tt>.
     * @param derMnyMktRestatedMilRt value to be assigned to property derMnyMktRestatedMilRt
     */
    public void setDerMnyMktRestatedMilRt(BigDecimal derMnyMktRestatedMilRt) {
        this.derMnyMktRestatedMilRt = derMnyMktRestatedMilRt;
    }

    /**
     * Getter method for property <tt>distYieldMilRt</tt>.
     * @return property value of distYieldMilRt
     */
    public BigDecimal getDistYieldMilRt() {
        return distYieldMilRt;
    }

    /**
     * Setter method for property <tt>distYieldMilRt</tt>.
     * @param distYieldMilRt value to be assigned to property distYieldMilRt
     */
    public void setDistYieldMilRt(BigDecimal distYieldMilRt) {
        this.distYieldMilRt = distYieldMilRt;
    }

    /**
     * Getter method for property <tt>expenseAmt</tt>.
     * @return property value of expenseAmt
     */
    public BigDecimal getExpenseAmt() {
        return expenseAmt;
    }

    /**
     * Setter method for property <tt>expenseAmt</tt>.
     * @param expenseAmt value to be assigned to property expenseAmt
     */
    public void setExpenseAmt(BigDecimal expenseAmt) {
        this.expenseAmt = expenseAmt;
    }

    /**
     * Getter method for property <tt>derTotalExpenseAmt</tt>.
     * @return property value of derTotalExpenseAmt
     */
    public BigDecimal getDerTotalExpenseAmt() {
        return derTotalExpenseAmt;
    }

    /**
     * Setter method for property <tt>derTotalExpenseAmt</tt>.
     * @param derTotalExpenseAmt value to be assigned to property derTotalExpenseAmt
     */
    public void setDerTotalExpenseAmt(BigDecimal derTotalExpenseAmt) {
        this.derTotalExpenseAmt = derTotalExpenseAmt;
    }

    /**
     * Getter method for property <tt>mnyMkt1DayYieldPct</tt>.
     * @return property value of mnyMkt1DayYieldPct
     */
    public BigDecimal getMnyMkt1DayYieldPct() {
        return mnyMkt1DayYieldPct;
    }

    /**
     * Setter method for property <tt>mnyMkt1DayYieldPct</tt>.
     * @param mnyMkt1DayYieldPct value to be assigned to property mnyMkt1DayYieldPct
     */
    public void setMnyMkt1DayYieldPct(BigDecimal mnyMkt1DayYieldPct) {
        this.mnyMkt1DayYieldPct = mnyMkt1DayYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt7DayYieldPct</tt>.
     * @return property value of derMnyMkt7DayYieldPct
     */
    public BigDecimal getDerMnyMkt7DayYieldPct() {
        return derMnyMkt7DayYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt7DayYieldPct</tt>.
     * @param derMnyMkt7DayYieldPct value to be assigned to property derMnyMkt7DayYieldPct
     */
    public void setDerMnyMkt7DayYieldPct(BigDecimal derMnyMkt7DayYieldPct) {
        this.derMnyMkt7DayYieldPct = derMnyMkt7DayYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt30DayYieldPct</tt>.
     * @return property value of derMnyMkt30DayYieldPct
     */
    public BigDecimal getDerMnyMkt30DayYieldPct() {
        return derMnyMkt30DayYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt30DayYieldPct</tt>.
     * @param derMnyMkt30DayYieldPct value to be assigned to property derMnyMkt30DayYieldPct
     */
    public void setDerMnyMkt30DayYieldPct(BigDecimal derMnyMkt30DayYieldPct) {
        this.derMnyMkt30DayYieldPct = derMnyMkt30DayYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMktCompound7DayYieldPct</tt>.
     * @return property value of derMnyMktCompound7DayYieldPct
     */
    public BigDecimal getDerMnyMktCompound7DayYieldPct() {
        return derMnyMktCompound7DayYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMktCompound7DayYieldPct</tt>.
     * @param derMnyMktCompound7DayYieldPct value to be assigned to property derMnyMktCompound7DayYieldPct
     */
    public void setDerMnyMktCompound7DayYieldPct(BigDecimal derMnyMktCompound7DayYieldPct) {
        this.derMnyMktCompound7DayYieldPct = derMnyMktCompound7DayYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt1DayN1AYieldPct</tt>.
     * @return property value of derMnyMkt1DayN1AYieldPct
     */
    public BigDecimal getDerMnyMkt1DayN1AYieldPct() {
        return derMnyMkt1DayN1AYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt1DayN1AYieldPct</tt>.
     * @param derMnyMkt1DayN1AYieldPct value to be assigned to property derMnyMkt1DayN1AYieldPct
     */
    public void setDerMnyMkt1DayN1AYieldPct(BigDecimal derMnyMkt1DayN1AYieldPct) {
        this.derMnyMkt1DayN1AYieldPct = derMnyMkt1DayN1AYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt7DayN1AYieldPct</tt>.
     * @return property value of derMnyMkt7DayN1AYieldPct
     */
    public BigDecimal getDerMnyMkt7DayN1AYieldPct() {
        return derMnyMkt7DayN1AYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt7DayN1AYieldPct</tt>.
     * @param derMnyMkt7DayN1AYieldPct value to be assigned to property derMnyMkt7DayN1AYieldPct
     */
    public void setDerMnyMkt7DayN1AYieldPct(BigDecimal derMnyMkt7DayN1AYieldPct) {
        this.derMnyMkt7DayN1AYieldPct = derMnyMkt7DayN1AYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt30DayN1AYieldPct</tt>.
     * @return property value of derMnyMkt30DayN1AYieldPct
     */
    public BigDecimal getDerMnyMkt30DayN1AYieldPct() {
        return derMnyMkt30DayN1AYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt30DayN1AYieldPct</tt>.
     * @param derMnyMkt30DayN1AYieldPct value to be assigned to property derMnyMkt30DayN1AYieldPct
     */
    public void setDerMnyMkt30DayN1AYieldPct(BigDecimal derMnyMkt30DayN1AYieldPct) {
        this.derMnyMkt30DayN1AYieldPct = derMnyMkt30DayN1AYieldPct;
    }

    /**
     * Getter method for property <tt>n1ADistReimbursementAmt</tt>.
     * @return property value of n1ADistReimbursementAmt
     */
    public BigDecimal getN1ADistReimbursementAmt() {
        return n1ADistReimbursementAmt;
    }

    /**
     * Setter method for property <tt>n1ADistReimbursementAmt</tt>.
     * @param n1ADistReimbursementAmt value to be assigned to property n1ADistReimbursementAmt
     */
    public void setN1ADistReimbursementAmt(BigDecimal n1aDistReimbursementAmt) {
        n1ADistReimbursementAmt = n1aDistReimbursementAmt;
    }

    /**
     * Getter method for property <tt>derMnyMktRst7DayYieldPct</tt>.
     * @return property value of derMnyMktRst7DayYieldPct
     */
    public BigDecimal getDerMnyMktRst7DayYieldPct() {
        return derMnyMktRst7DayYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMktRst7DayYieldPct</tt>.
     * @param derMnyMktRst7DayYieldPct value to be assigned to property derMnyMktRst7DayYieldPct
     */
    public void setDerMnyMktRst7DayYieldPct(BigDecimal derMnyMktRst7DayYieldPct) {
        this.derMnyMktRst7DayYieldPct = derMnyMktRst7DayYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMktRst30DayYieldPct</tt>.
     * @return property value of derMnyMktRst30DayYieldPct
     */
    public BigDecimal getDerMnyMktRst30DayYieldPct() {
        return derMnyMktRst30DayYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMktRst30DayYieldPct</tt>.
     * @param derMnyMktRst30DayYieldPct value to be assigned to property derMnyMktRst30DayYieldPct
     */
    public void setDerMnyMktRst30DayYieldPct(BigDecimal derMnyMktRst30DayYieldPct) {
        this.derMnyMktRst30DayYieldPct = derMnyMktRst30DayYieldPct;
    }

    /**
     * Getter method for property <tt>derN1ADistIncomeAmt</tt>.
     * @return property value of derN1ADistIncomeAmt
     */
    public BigDecimal getDerN1ADistIncomeAmt() {
        return derN1ADistIncomeAmt;
    }

    /**
     * Setter method for property <tt>derN1ADistIncomeAmt</tt>.
     * @param derN1ADistIncomeAmt value to be assigned to property derN1ADistIncomeAmt
     */
    public void setDerN1ADistIncomeAmt(BigDecimal derN1ADistIncomeAmt) {
        this.derN1ADistIncomeAmt = derN1ADistIncomeAmt;
    }

    /**
     * Getter method for property <tt>derN1ADailyMilRt</tt>.
     * @return property value of derN1ADailyMilRt
     */
    public BigDecimal getDerN1ADailyMilRt() {
        return derN1ADailyMilRt;
    }

    /**
     * Setter method for property <tt>derN1ADailyMilRt</tt>.
     * @param derN1ADailyMilRt value to be assigned to property derN1ADailyMilRt
     */
    public void setDerN1ADailyMilRt(BigDecimal derN1ADailyMilRt) {
        this.derN1ADailyMilRt = derN1ADailyMilRt;
    }

    /**
     * Getter method for property <tt>n1ADistIncomeUnmodAmt</tt>.
     * @return property value of n1ADistIncomeUnmodAmt
     */
    public BigDecimal getN1ADistIncomeUnmodAmt() {
        return n1ADistIncomeUnmodAmt;
    }

    /**
     * Setter method for property <tt>n1ADistIncomeUnmodAmt</tt>.
     * @param n1ADistIncomeUnmodAmt value to be assigned to property n1ADistIncomeUnmodAmt
     */
    public void setN1ADistIncomeUnmodAmt(BigDecimal n1aDistIncomeUnmodAmt) {
        n1ADistIncomeUnmodAmt = n1aDistIncomeUnmodAmt;
    }

    /**
     * Getter method for property <tt>n1ADistIncomeAdjAmt</tt>.
     * @return property value of n1ADistIncomeAdjAmt
     */
    public BigDecimal getN1ADistIncomeAdjAmt() {
        return n1ADistIncomeAdjAmt;
    }

    /**
     * Setter method for property <tt>n1ADistIncomeAdjAmt</tt>.
     * @param n1ADistIncomeAdjAmt value to be assigned to property n1ADistIncomeAdjAmt
     */
    public void setN1ADistIncomeAdjAmt(BigDecimal n1aDistIncomeAdjAmt) {
        n1ADistIncomeAdjAmt = n1aDistIncomeAdjAmt;
    }

    /**
     * Getter method for property <tt>n1ADistIncomeBreakageAmt</tt>.
     * @return property value of n1ADistIncomeBreakageAmt
     */
    public BigDecimal getN1ADistIncomeBreakageAmt() {
        return n1ADistIncomeBreakageAmt;
    }

    /**
     * Setter method for property <tt>n1ADistIncomeBreakageAmt</tt>.
     * @param n1ADistIncomeBreakageAmt value to be assigned to property n1ADistIncomeBreakageAmt
     */
    public void setN1ADistIncomeBreakageAmt(BigDecimal n1aDistIncomeBreakageAmt) {
        n1ADistIncomeBreakageAmt = n1aDistIncomeBreakageAmt;
    }

    /**
     * Getter method for property <tt>n1ADistIncomeAdjRevAmt</tt>.
     * @return property value of n1ADistIncomeAdjRevAmt
     */
    public BigDecimal getN1ADistIncomeAdjRevAmt() {
        return n1ADistIncomeAdjRevAmt;
    }

    /**
     * Setter method for property <tt>n1ADistIncomeAdjRevAmt</tt>.
     * @param n1ADistIncomeAdjRevAmt value to be assigned to property n1ADistIncomeAdjRevAmt
     */
    public void setN1ADistIncomeAdjRevAmt(BigDecimal n1aDistIncomeAdjRevAmt) {
        n1ADistIncomeAdjRevAmt = n1aDistIncomeAdjRevAmt;
    }

    /**
     * Getter method for property <tt>dist1DayYieldPct</tt>.
     * @return property value of dist1DayYieldPct
     */
    public BigDecimal getDist1DayYieldPct() {
        return dist1DayYieldPct;
    }

    /**
     * Setter method for property <tt>dist1DayYieldPct</tt>.
     * @param dist1DayYieldPct value to be assigned to property dist1DayYieldPct
     */
    public void setDist1DayYieldPct(BigDecimal dist1DayYieldPct) {
        this.dist1DayYieldPct = dist1DayYieldPct;
    }

    /**
     * Getter method for property <tt>dist30DayYieldPct</tt>.
     * @return property value of dist30DayYieldPct
     */
    public BigDecimal getDist30DayYieldPct() {
        return dist30DayYieldPct;
    }

    /**
     * Setter method for property <tt>dist30DayYieldPct</tt>.
     * @param dist30DayYieldPct value to be assigned to property dist30DayYieldPct
     */
    public void setDist30DayYieldPct(BigDecimal dist30DayYieldPct) {
        this.dist30DayYieldPct = dist30DayYieldPct;
    }

    /**
     * Getter method for property <tt>navAmt</tt>.
     * @return property value of navAmt
     */
    public BigDecimal getNavAmt() {
        return navAmt;
    }

    /**
     * Setter method for property <tt>navAmt</tt>.
     * @param navAmt value to be assigned to property navAmt
     */
    public void setNavAmt(BigDecimal navAmt) {
        this.navAmt = navAmt;
    }

    /**
     * Getter method for property <tt>derSec1DayYieldPct</tt>.
     * @return property value of derSec1DayYieldPct
     */
    public BigDecimal getDerSec1DayYieldPct() {
        return derSec1DayYieldPct;
    }

    /**
     * Setter method for property <tt>derSec1DayYieldPct</tt>.
     * @param derSec1DayYieldPct value to be assigned to property derSec1DayYieldPct
     */
    public void setDerSec1DayYieldPct(BigDecimal derSec1DayYieldPct) {
        this.derSec1DayYieldPct = derSec1DayYieldPct;
    }

    /**
     * Getter method for property <tt>derSec30DayYieldPct</tt>.
     * @return property value of derSec30DayYieldPct
     */
    public BigDecimal getDerSec30DayYieldPct() {
        return derSec30DayYieldPct;
    }

    /**
     * Setter method for property <tt>derSec30DayYieldPct</tt>.
     * @param derSec30DayYieldPct value to be assigned to property derSec30DayYieldPct
     */
    public void setDerSec30DayYieldPct(BigDecimal derSec30DayYieldPct) {
        this.derSec30DayYieldPct = derSec30DayYieldPct;
    }

    /**
     * Getter method for property <tt>derSecRestatedYieldPct</tt>.
     * @return property value of derSecRestatedYieldPct
     */
    public BigDecimal getDerSecRestatedYieldPct() {
        return derSecRestatedYieldPct;
    }

    /**
     * Setter method for property <tt>derSecRestatedYieldPct</tt>.
     * @param derSecRestatedYieldPct value to be assigned to property derSecRestatedYieldPct
     */
    public void setDerSecRestatedYieldPct(BigDecimal derSecRestatedYieldPct) {
        this.derSecRestatedYieldPct = derSecRestatedYieldPct;
    }

    /**
     * Getter method for property <tt>distributableCapstockQty</tt>.
     * @return property value of distributableCapstockQty
     */
    public BigDecimal getDistributableCapstockQty() {
        return distributableCapstockQty;
    }

    /**
     * Setter method for property <tt>distributableCapstockQty</tt>.
     * @param distributableCapstockQty value to be assigned to property distributableCapstockQty
     */
    public void setDistributableCapstockQty(BigDecimal distributableCapstockQty) {
        this.distributableCapstockQty = distributableCapstockQty;
    }

    /**
     * Getter method for property <tt>n1AGrossIncomeUnmodAmt</tt>.
     * @return property value of n1AGrossIncomeUnmodAmt
     */
    public BigDecimal getN1AGrossIncomeUnmodAmt() {
        return n1AGrossIncomeUnmodAmt;
    }

    /**
     * Setter method for property <tt>n1AGrossIncomeUnmodAmt</tt>.
     * @param n1AGrossIncomeUnmodAmt value to be assigned to property n1AGrossIncomeUnmodAmt
     */
    public void setN1AGrossIncomeUnmodAmt(BigDecimal n1aGrossIncomeUnmodAmt) {
        n1AGrossIncomeUnmodAmt = n1aGrossIncomeUnmodAmt;
    }

    /**
     * Getter method for property <tt>n1AReimbursementEarnedAmt</tt>.
     * @return property value of n1AReimbursementEarnedAmt
     */
    public BigDecimal getN1AReimbursementEarnedAmt() {
        return n1AReimbursementEarnedAmt;
    }

    /**
     * Setter method for property <tt>n1AReimbursementEarnedAmt</tt>.
     * @param n1AReimbursementEarnedAmt value to be assigned to property n1AReimbursementEarnedAmt
     */
    public void setN1AReimbursementEarnedAmt(BigDecimal n1aReimbursementEarnedAmt) {
        n1AReimbursementEarnedAmt = n1aReimbursementEarnedAmt;
    }

    /**
     * Getter method for property <tt>n1AWaiverEarnedAmt</tt>.
     * @return property value of n1AWaiverEarnedAmt
     */
    public BigDecimal getN1AWaiverEarnedAmt() {
        return n1AWaiverEarnedAmt;
    }

    /**
     * Setter method for property <tt>n1AWaiverEarnedAmt</tt>.
     * @param n1AWaiverEarnedAmt value to be assigned to property n1AWaiverEarnedAmt
     */
    public void setN1AWaiverEarnedAmt(BigDecimal n1aWaiverEarnedAmt) {
        n1AWaiverEarnedAmt = n1aWaiverEarnedAmt;
    }

    /**
     * Getter method for property <tt>fdrN1ATaxAmt</tt>.
     * @return property value of fdrN1ATaxAmt
     */
    public BigDecimal getFdrN1ATaxAmt() {
        return fdrN1ATaxAmt;
    }

    /**
     * Setter method for property <tt>fdrN1ATaxAmt</tt>.
     * @param fdrN1ATaxAmt value to be assigned to property fdrN1ATaxAmt
     */
    public void setFdrN1ATaxAmt(BigDecimal fdrN1ATaxAmt) {
        this.fdrN1ATaxAmt = fdrN1ATaxAmt;
    }

    /**
     * Getter method for property <tt>fdrN1AGrossDistIncomeAmt</tt>.
     * @return property value of fdrN1AGrossDistIncomeAmt
     */
    public BigDecimal getFdrN1AGrossDistIncomeAmt() {
        return fdrN1AGrossDistIncomeAmt;
    }

    /**
     * Setter method for property <tt>fdrN1AGrossDistIncomeAmt</tt>.
     * @param fdrN1AGrossDistIncomeAmt value to be assigned to property fdrN1AGrossDistIncomeAmt
     */
    public void setFdrN1AGrossDistIncomeAmt(BigDecimal fdrN1AGrossDistIncomeAmt) {
        this.fdrN1AGrossDistIncomeAmt = fdrN1AGrossDistIncomeAmt;
    }

    /**
     * Getter method for property <tt>adjExclShareClassCalcInd</tt>.
     * @return property value of adjExclShareClassCalcInd
     */
    public String getAdjExclShareClassCalcInd() {
        return adjExclShareClassCalcInd;
    }

    /**
     * Setter method for property <tt>adjExclShareClassCalcInd</tt>.
     * @param adjExclShareClassCalcInd value to be assigned to property adjExclShareClassCalcInd
     */
    public void setAdjExclShareClassCalcInd(String adjExclShareClassCalcInd) {
        this.adjExclShareClassCalcInd = adjExclShareClassCalcInd;
    }

    /**
     * Getter method for property <tt>derN1ADailyYieldPct</tt>.
     * @return property value of derN1ADailyYieldPct
     */
    public BigDecimal getDerN1ADailyYieldPct() {
        return derN1ADailyYieldPct;
    }

    /**
     * Setter method for property <tt>derN1ADailyYieldPct</tt>.
     * @param derN1ADailyYieldPct value to be assigned to property derN1ADailyYieldPct
     */
    public void setDerN1ADailyYieldPct(BigDecimal derN1ADailyYieldPct) {
        this.derN1ADailyYieldPct = derN1ADailyYieldPct;
    }

    /**
     * Getter method for property <tt>derSecYieldPmeChgPct</tt>.
     * @return property value of derSecYieldPmeChgPct
     */
    public BigDecimal getDerSecYieldPmeChgPct() {
        return derSecYieldPmeChgPct;
    }

    /**
     * Setter method for property <tt>derSecYieldPmeChgPct</tt>.
     * @param derSecYieldPmeChgPct value to be assigned to property derSecYieldPmeChgPct
     */
    public void setDerSecYieldPmeChgPct(BigDecimal derSecYieldPmeChgPct) {
        this.derSecYieldPmeChgPct = derSecYieldPmeChgPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt1DayDistYieldDodChgPct</tt>.
     * @return property value of derMnyMkt1DayDistYieldDodChgPct
     */
    public BigDecimal getDerMnyMkt1DayDistYieldDodChgPct() {
        return derMnyMkt1DayDistYieldDodChgPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt1DayDistYieldDodChgPct</tt>.
     * @param derMnyMkt1DayDistYieldDodChgPct value to be assigned to property derMnyMkt1DayDistYieldDodChgPct
     */
    public void setDerMnyMkt1DayDistYieldDodChgPct(BigDecimal derMnyMkt1DayDistYieldDodChgPct) {
        this.derMnyMkt1DayDistYieldDodChgPct = derMnyMkt1DayDistYieldDodChgPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt7DayDistYieldDodChgPct</tt>.
     * @return property value of derMnyMkt7DayDistYieldDodChgPct
     */
    public BigDecimal getDerMnyMkt7DayDistYieldDodChgPct() {
        return derMnyMkt7DayDistYieldDodChgPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt7DayDistYieldDodChgPct</tt>.
     * @param derMnyMkt7DayDistYieldDodChgPct value to be assigned to property derMnyMkt7DayDistYieldDodChgPct
     */
    public void setDerMnyMkt7DayDistYieldDodChgPct(BigDecimal derMnyMkt7DayDistYieldDodChgPct) {
        this.derMnyMkt7DayDistYieldDodChgPct = derMnyMkt7DayDistYieldDodChgPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt1DayN1AYieldDodChgPct</tt>.
     * @return property value of derMnyMkt1DayN1AYieldDodChgPct
     */
    public BigDecimal getDerMnyMkt1DayN1AYieldDodChgPct() {
        return derMnyMkt1DayN1AYieldDodChgPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt1DayN1AYieldDodChgPct</tt>.
     * @param derMnyMkt1DayN1AYieldDodChgPct value to be assigned to property derMnyMkt1DayN1AYieldDodChgPct
     */
    public void setDerMnyMkt1DayN1AYieldDodChgPct(BigDecimal derMnyMkt1DayN1AYieldDodChgPct) {
        this.derMnyMkt1DayN1AYieldDodChgPct = derMnyMkt1DayN1AYieldDodChgPct;
    }

    /**
     * Getter method for property <tt>derMnyMkt7DayN1AYieldDodChgPct</tt>.
     * @return property value of derMnyMkt7DayN1AYieldDodChgPct
     */
    public BigDecimal getDerMnyMkt7DayN1AYieldDodChgPct() {
        return derMnyMkt7DayN1AYieldDodChgPct;
    }

    /**
     * Setter method for property <tt>derMnyMkt7DayN1AYieldDodChgPct</tt>.
     * @param derMnyMkt7DayN1AYieldDodChgPct value to be assigned to property derMnyMkt7DayN1AYieldDodChgPct
     */
    public void setDerMnyMkt7DayN1AYieldDodChgPct(BigDecimal derMnyMkt7DayN1AYieldDodChgPct) {
        this.derMnyMkt7DayN1AYieldDodChgPct = derMnyMkt7DayN1AYieldDodChgPct;
    }

    /**
     * Getter method for property <tt>derDist12MoYieldPct</tt>.
     * @return property value of derDist12MoYieldPct
     */
    public BigDecimal getDerDist12MoYieldPct() {
        return derDist12MoYieldPct;
    }

    /**
     * Setter method for property <tt>derDist12MoYieldPct</tt>.
     * @param derDist12MoYieldPct value to be assigned to property derDist12MoYieldPct
     */
    public void setDerDist12MoYieldPct(BigDecimal derDist12MoYieldPct) {
        this.derDist12MoYieldPct = derDist12MoYieldPct;
    }

    /**
     * Getter method for property <tt>derDist30DayYieldPct</tt>.
     * @return property value of derDist30DayYieldPct
     */
    public BigDecimal getDerDist30DayYieldPct() {
        return derDist30DayYieldPct;
    }

    /**
     * Setter method for property <tt>derDist30DayYieldPct</tt>.
     * @param derDist30DayYieldPct value to be assigned to property derDist30DayYieldPct
     */
    public void setDerDist30DayYieldPct(BigDecimal derDist30DayYieldPct) {
        this.derDist30DayYieldPct = derDist30DayYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMktRestate1DayYieldPct</tt>.
     * @return property value of derMnyMktRestate1DayYieldPct
     */
    public BigDecimal getDerMnyMktRestate1DayYieldPct() {
        return derMnyMktRestate1DayYieldPct;
    }

    /**
     * Setter method for property <tt>derMnyMktRestate1DayYieldPct</tt>.
     * @param derMnyMktRestate1DayYieldPct value to be assigned to property derMnyMktRestate1DayYieldPct
     */
    public void setDerMnyMktRestate1DayYieldPct(BigDecimal derMnyMktRestate1DayYieldPct) {
        this.derMnyMktRestate1DayYieldPct = derMnyMktRestate1DayYieldPct;
    }

    /**
     * Getter method for property <tt>derMnyMktN1ACompound7dayYield</tt>.
     * @return property value of derMnyMktN1ACompound7dayYield
     */
    public BigDecimal getDerMnyMktN1ACompound7dayYield() {
        return derMnyMktN1ACompound7dayYield;
    }

    /**
     * Setter method for property <tt>derMnyMktN1ACompound7dayYield</tt>.
     * @param derMnyMktN1ACompound7dayYield value to be assigned to property derMnyMktN1ACompound7dayYield
     */
    public void setDerMnyMktN1ACompound7dayYield(BigDecimal derMnyMktN1ACompound7dayYield) {
        this.derMnyMktN1ACompound7dayYield = derMnyMktN1ACompound7dayYield;
    }

    /**
     * Getter method for property <tt>secReimbursementEarnedAmt</tt>.
     * @return property value of secReimbursementEarnedAmt
     */
    public BigDecimal getSecReimbursementEarnedAmt() {
        return secReimbursementEarnedAmt;
    }

    /**
     * Setter method for property <tt>secReimbursementEarnedAmt</tt>.
     * @param secReimbursementEarnedAmt value to be assigned to property secReimbursementEarnedAmt
     */
    public void setSecReimbursementEarnedAmt(BigDecimal secReimbursementEarnedAmt) {
        this.secReimbursementEarnedAmt = secReimbursementEarnedAmt;
    }

    /**
     * Getter method for property <tt>distUnmod30DayYieldPct</tt>.
     * @return property value of distUnmod30DayYieldPct
     */
    public BigDecimal getDistUnmod30DayYieldPct() {
        return distUnmod30DayYieldPct;
    }

    /**
     * Setter method for property <tt>distUnmod30DayYieldPct</tt>.
     * @param distUnmod30DayYieldPct value to be assigned to property distUnmod30DayYieldPct
     */
    public void setDistUnmod30DayYieldPct(BigDecimal distUnmod30DayYieldPct) {
        this.distUnmod30DayYieldPct = distUnmod30DayYieldPct;
    }

    /**
     * Getter method for property <tt>dist12MoMilRt</tt>.
     * @return property value of dist12MoMilRt
     */
    public BigDecimal getDist12MoMilRt() {
        return dist12MoMilRt;
    }

    /**
     * Setter method for property <tt>dist12MoMilRt</tt>.
     * @param dist12MoMilRt value to be assigned to property dist12MoMilRt
     */
    public void setDist12MoMilRt(BigDecimal dist12MoMilRt) {
        this.dist12MoMilRt = dist12MoMilRt;
    }
}
