/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Tradable entity snapshot.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class TradableEntitySnapshot extends Adjustable {

    /**
     * Surrogate id to uniquely identify a tradable entity snapshot.
     */
	@ColumnName("TRADABLE_ENTITY_SNAPSHOT_SID")
    private long tradableEntitySnapshotSid;

    /**
     * Surrogate id to uniquely identify a tradable entity.
     */
	@ColumnName("TRADABLE_ENTITY_SID")
    private long tradableEntitySid;

    /**
     * Date for which calculations are being done and reported (e.g: yield/income, etc). (Usually closest business day,
     * prior to the current business day).
     */
	@ColumnName("REPORTING_DT")
    private Date reportDate;

    /**
     * Code of the engine used for the calculation of Yield.
     */
	@ColumnName("DER_YIELD_CALC_ENGINE_CD")
    private String derYieldCalcEngineCode;

    /**
     * Code of the engine used for the calculation of Income.
     */
	@ColumnName("DER_INCOME_CALC_ENGINE_CD")
    private String derIncomeCalcEngineCode;

    /**
     * Redemption price for redeeming a share as derived in the SEC yield application. Usually same as maturity price
     * for some securities, but in future some other securities redemption price may be different than maturity date and
     * maturity price. TheDER_REDEMPTION_PRC specific to SEC Yield application, which is derived based on CALL_DT or
     * PUT_DT and is used as MATURITY_DT for SEC yield calculation purposes. This DER_REDEMPTION_PRC is different than
     * the MDM-Redemption price which relates more to Shareholder RedemptionPrice.
     */
	@ColumnName("DER_REDEMPTION_PRC")
    private BigDecimal derRedemptionPrice;

    /**
     * Redemption date of the security as derived in SEC Yield application.
     */
	@ColumnName("DER_REDEMPTION_DT")
    private Date derRedemptionDate;

    /**
     * Market Price used by Pricing system in valuation of a fund. The price for a given security is same across funds
     * when all the funds are in the same fund family. But, when the funds belong to different fund family, the price
     * for same security could be different across funds. SEC Yield scope is limited to a single fund family, So Market
     * price could be tracked on Security level table or to allow for future, could be tracked at Position level
     * table(fund+TradableEntity).
     */
	@ColumnName("MARKET_PRC")
    private BigDecimal marketPrice;

    /**
     * The current interest rate expressed as a percentage. If the security has a fixed rate, the value applies for the
     * full life of the security. Refers to either Cash or PIK rate depending on the payment type, which will be
     * dictated by CURRENT COUPON ELECTION CODE in MDM.
     */
	@ColumnName("CURRENT_INCOME_RT")
    private BigDecimal currentIncomeRate;

    /**
     * For TIPS securities, #shares held flucates because of inflation. If this can be sourced from other systems in
     * FPMCS, then straight move, else Derived based on #Par_amt and Inflation Adjusted Shares. Per Data Governance
     * team, TIPS shares don't get traded on multiple stock exchanges, hence can be at instrument level.
     */
	@ColumnName("FDR_TIPS_INFLATIONARY_RATIO_RT")
    private BigDecimal fdrTipsInflationaryRatio;

    /**
     * for 99% of securities, clean price is same as market price. For TIPS, we take market price and multiply by tips
     * ratio to get clean price.
     */
	@ColumnName("FDR_CLEAN_PRC")
    private BigDecimal fdrCleanPrice;

    /**
     * One day Security Yield rate as calculated in SEC yield application. Yield is calculated at Security level.
     */
	@ColumnName("DER_SEC_1_DAY_YIELD_RT")
    private BigDecimal derOneDaySecurityYield;

    /**
     * Constructor.
     */
    public TradableEntitySnapshot() {
        // default empty constructor
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
     * Getter method for property <tt>tradableEntitySid</tt>.
     * @return property value of tradableEntitySid
     */
    public long getTradableEntitySid() {
        return tradableEntitySid;
    }

    /**
     * Setter method for property <tt>tradableEntitySid</tt>.
     * @param tradableEntitySid value to be assigned to property tradableEntitySid
     */
    public void setTradableEntitySid(long tradableEntitySid) {
        this.tradableEntitySid = tradableEntitySid;
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
     * Getter method for property <tt>derYieldCalcEngineCode</tt>.
     * @return property value of derYieldCalcEngineCode
     */
    public String getDerYieldCalcEngineCode() {
        return derYieldCalcEngineCode;
    }

    /**
     * Setter method for property <tt>derYieldCalcEngineCode</tt>.
     * @param derYieldCalcEngineCode value to be assigned to property derYieldCalcEngineCode
     */
    public void setDerYieldCalcEngineCode(String derYieldCalcEngineCode) {
        this.derYieldCalcEngineCode = derYieldCalcEngineCode;
    }

    /**
     * Getter method for property <tt>derIncomeCalcEngineCode</tt>.
     * @return property value of derIncomeCalcEngineCode
     */
    public String getDerIncomeCalcEngineCode() {
        return derIncomeCalcEngineCode;
    }

    /**
     * Setter method for property <tt>derIncomeCalcEngineCode</tt>.
     * @param derIncomeCalcEngineCode value to be assigned to property derIncomeCalcEngineCode
     */
    public void setDerIncomeCalcEngineCode(String derIncomeCalcEngineCode) {
        this.derIncomeCalcEngineCode = derIncomeCalcEngineCode;
    }

    /**
     * Getter method for property <tt>derRedemptionPrice</tt>.
     * @return property value of derRedemptionPrice
     */
    public BigDecimal getDerRedemptionPrice() {
        return derRedemptionPrice;
    }

    /**
     * Setter method for property <tt>derRedemptionPrice</tt>.
     * @param derRedemptionPrice value to be assigned to property derRedemptionPrice
     */
    public void setDerRedemptionPrice(BigDecimal derRedemptionPrice) {
        this.derRedemptionPrice = derRedemptionPrice;
    }

    /**
     * Getter method for property <tt>derRedemptionDate</tt>.
     * @return property value of derRedemptionDate
     */
    public Date getDerRedemptionDate() {
        return derRedemptionDate;
    }

    /**
     * Setter method for property <tt>derRedemptionDate</tt>.
     * @param derRedemptionDate value to be assigned to property derRedemptionDate
     */
    public void setDerRedemptionDate(Date derRedemptionDate) {
        this.derRedemptionDate = derRedemptionDate;
    }

    /**
     * Getter method for property <tt>marketPrice</tt>.
     * @return property value of marketPrice
     */
    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    /**
     * Setter method for property <tt>marketPrice</tt>.
     * @param marketPrice value to be assigned to property marketPrice
     */
    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * Getter method for property <tt>currentIncomeRate</tt>.
     * @return property value of currentIncomeRate
     */
    public BigDecimal getCurrentIncomeRate() {
        return currentIncomeRate;
    }

    /**
     * Setter method for property <tt>currentIncomeRate</tt>.
     * @param currentIncomeRate value to be assigned to property currentIncomeRate
     */
    public void setCurrentIncomeRate(BigDecimal currentIncomeRate) {
        this.currentIncomeRate = currentIncomeRate;
    }

    /**
     * Getter method for property <tt>fdrTipsInflationaryRatio</tt>.
     * @return property value of fdrTipsInflationaryRatio
     */
    public BigDecimal getFdrTipsInflationaryRatio() {
        return fdrTipsInflationaryRatio;
    }

    /**
     * Setter method for property <tt>fdrTipsInflationaryRatio</tt>.
     * @param fdrTipsInflationaryRatio value to be assigned to property fdrTipsInflationaryRatio
     */
    public void setFdrTipsInflationaryRatio(BigDecimal fdrTipsInflationaryRatio) {
        this.fdrTipsInflationaryRatio = fdrTipsInflationaryRatio;
    }

    /**
     * Getter method for property <tt>fdrCleanPrice</tt>.
     * @return property value of fdrCleanPrice
     */
    public BigDecimal getFdrCleanPrice() {
        return fdrCleanPrice;
    }

    /**
     * Setter method for property <tt>fdrCleanPrice</tt>.
     * @param fdrCleanPrice value to be assigned to property fdrCleanPrice
     */
    public void setFdrCleanPrice(BigDecimal fdrCleanPrice) {
        this.fdrCleanPrice = fdrCleanPrice;
    }

    /**
     * Getter method for property <tt>derOneDaySecurityYield</tt>.
     * @return property value of derOneDaySecurityYield
     */
    public BigDecimal getDerOneDaySecurityYield() {
        return derOneDaySecurityYield;
    }

    /**
     * Setter method for property <tt>derOneDaySecurityYield</tt>.
     * @param derOneDaySecurityYield value to be assigned to property derOneDaySecurityYield
     */
    public void setDerOneDaySecurityYield(BigDecimal derOneDaySecurityYield) {
        this.derOneDaySecurityYield = derOneDaySecurityYield;
    }
}
