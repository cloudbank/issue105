/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The instrument.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class Instrument extends Versionable {

	/**
	 * Surrogate id to uniquely identify an instrument.
	 */
	@ColumnName("INSTRUMENT_SID")
	private long instrumentSid;

    /**
     * A unique key derived in source system, i.e. MDM system and used to identify an individual instrument.
     */
	@ColumnName("INSTRUMENT_ID")
    private long instrumentId;

    /**
     * Identifies an instrument by its full legal name plus descriptive information.
     */
	@ColumnName("INSTRUMENT_SHORT_NM")
    private String instrumentShortName;

    /**
     * Code that can be used to classify instruments based on an internal Company Internal classification scheme. An
     * INSTRUMENT TYPE CODE can be further refined by a INSTRUMENT STRUCTURE TYPE CODE.
     */
	@ColumnName("INSTRUMENT_TYPE_CD")
    private InstrumentTypeCode instrumentTypeCode;

    /**
     * Codes that can be used to define the income (interest etc.) rate type (e.g., fixed, variable, etc) . Also known
     * as coupon Rate in MDM particularly for bonds. Valid Values are:
     * <ul>
     *   <li>F FIXED RATE (NON-ZERO COUPON)</li>
     *   <li>L FLOATING RATE</li>
     *   <li>S STEPPED COUPON</li>
     *   <li>V VARIABLE RATE</li>
     *   <li>Z ZERO COUPON</li>
     * </ul>
     */
	@ColumnName("COUPON_RATE_TYPE_CD")
    private String couponRateTypeCode;

    /**
     * This is the LIVE or real CUSIP number assigned to the instrument.
     */
	@ColumnName("CUSIP")
    private String cusip;

    /**
     * In cases where overlaying securities have the underlying bonds (co's bonds insure the overlaying security).
     * Pre_refund_date is the call_date for the Overlaying Security (and thus used as modified Maturity date). YieldCalc
     * does not differentiate between Call_dt and Maturity date. It uses both as redemption date.
     */
	@ColumnName("PRE_REFUNDED_DT")
    private Date preRefundedDate;

    /**
     * A numeric value indicating how many times a year dividend is paid for that security.
     */
	@ColumnName("DIVIDEND_PAYMENT_FREQUENCY_NBR")
    private BigDecimal dividendPaymentFrequencyNbr;

    /**
     * A Y/N field Derived in the local application to indicate if an Equity has an MLP or ROC obligation.
     */
	@ColumnName("FDR_EQUITY_IND")
    private String fdrEquityInd;

    /**
     * Price of the security when it was originally issued.
     */
	@ColumnName("ORIGINAL_ISSUE_PRC")
    private BigDecimal originalIssuePrc;

    /**
     * Indicates what type of a bond the security is. Step bonds go thru a diff calculation engine. derived in SecYield.
     */
	@ColumnName("FDR_STEP_BOND_IND")
    private String fdrStepBondInd;

    /**
     * SEC Yield specific classification of Securities/instruments as derived by the rules engine. Valid
     * Values:MunicipalBonds, MortgageBackedSecurities etc.
     */
	@ColumnName("DER_INSTRUMENT_CLASS_NM")
    private String fdrInstrumentClassNm;

    /**
     * Indicates the yield calculation method used by a security and comes from Upstream accounting system
     * Security_Cache table Valid Values: Y, B, RVN, N.
     */
	@ColumnName("PROSPECTIVE_YIELD_METHOD_CD")
    private String prospectiveYieldMethodCd;

    /**
     * The final date on which the principal amount of a debt instrument is due and payable.
     */
	@ColumnName("FINAL_MATURITY_DT")
    private Date finalMaturityDate;

    /**
     * A Code from accounting system to indicate if the hybrid method should be used for yield/income calculation. It
     * will have values "HYBRID" and other text or null values. derHybridInd is derived from this field. i.e. when this
     * value is HYBRID, then derHybridInd=Y.
     */
	@ColumnName("HYBRID_CALCULATION_CD")
    private String hybridCalculationCd;

    /**
     * Date when the security was originally issued.
     */
	@ColumnName("ORIGINAL_ISSUE_DT")
    private Date originalIssueDate;

    /**
     * "Price of Security at the time of Maturity of the security. mostly $100.00. Price at maturity is almost always
     * $100. (Common Ref has it as REDEMPTION_PRC, but definition says it is the price at maturity, so it is the same.
     * Also, commonRef has it on INSTRUMENT_REDEMPTION table and this table is 1-1 with instrument. Putting this on
     * instrument table for SECYield might be ok. There is a separate DER_REDEMPTION_PRC specific to this application,
     * which is derived based on CALL_DT or PUT_DT and is used as MATURITY_DT for SEC yield calculation purposes. This
     * DER_REDEMPTION_PRC is different than the MDM-Redemption price.
     */
	@ColumnName("MATURITY_PRC")
    private BigDecimal maturityPrc;

    /**
     * A Y/N value to indicate if a given security is in default or not. Data come from Accounting system, usually based
     * on if a i.e. Security has a default date filled out ,then that security (only for Bonds) will not be paying out
     * interest. (Dividend not applicable for Bond).
     */
	@ColumnName("DEFAULT_IND")
    private String defaultInd;

    /**
     * A marker for securities to designate whether the security represents a mutual fund. This will be derived using
     * FOF_SECURITY_ID, a fund level field.
     */
	@ColumnName("DER_FOF_SECURITY_CD")
    private String derFofSecurityCd;

    /**
     * Interest rate schedules.
     */
    private List<InterestRateSchedule> interestRateSchedules;

    /**
     * Cash dividend schedules.
     */
    private List<CashDividendSchedule> cashDividendSchedules;

    /**
     * Call schedules.
     */
    private List<CallSchedule> callSchedules;

    /**
     * Put schedules.
     */
    private List<PutSchedule> putSchedules;

    /**
     * Underlying instruments.
     */
    private List<UnderlyingInstrumentLink> underlyingInstruments;

    /**
     * Tradable entities.
     */
    private List<TradableEntity> tradableEntities;

    /**
     * Constructor.
     */
    public Instrument() {
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
     * Getter method for property <tt>instrumentId</tt>.
     * @return property value of instrumentId
     */
    public long getInstrumentId() {
        return instrumentId;
    }

    /**
     * Setter method for property <tt>instrumentId</tt>.
     * @param instrumentId value to be assigned to property instrumentId
     */
    public void setInstrumentId(long instrumentId) {
        this.instrumentId = instrumentId;
    }

    /**
     * Getter method for property <tt>instrumentShortName</tt>.
     * @return property value of instrumentShortName
     */
    public String getInstrumentShortName() {
        return instrumentShortName;
    }

    /**
     * Setter method for property <tt>instrumentShortName</tt>.
     * @param instrumentShortName value to be assigned to property instrumentShortName
     */
    public void setInstrumentShortName(String instrumentShortName) {
        this.instrumentShortName = instrumentShortName;
    }

    /**
     * Getter method for property <tt>instrumentTypeCode</tt>.
     * @return property value of instrumentTypeCode
     */
    public InstrumentTypeCode getInstrumentTypeCode() {
        return instrumentTypeCode;
    }

    /**
     * Setter method for property <tt>instrumentTypeCode</tt>.
     * @param instrumentTypeCode value to be assigned to property instrumentTypeCode
     */
    public void setInstrumentTypeCode(InstrumentTypeCode instrumentTypeCode) {
        this.instrumentTypeCode = instrumentTypeCode;
    }

    /**
     * Getter method for property <tt>couponRateTypeCode</tt>.
     * @return property value of couponRateTypeCode
     */
    public String getCouponRateTypeCode() {
        return couponRateTypeCode;
    }

    /**
     * Setter method for property <tt>couponRateTypeCode</tt>.
     * @param couponRateTypeCode value to be assigned to property couponRateTypeCode
     */
    public void setCouponRateTypeCode(String couponRateTypeCode) {
        this.couponRateTypeCode = couponRateTypeCode;
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
     * Getter method for property <tt>preRefundedDate</tt>.
     * @return property value of preRefundedDate
     */
    public Date getPreRefundedDate() {
        return preRefundedDate;
    }

    /**
     * Setter method for property <tt>preRefundedDate</tt>.
     * @param preRefundedDate value to be assigned to property preRefundedDate
     */
    public void setPreRefundedDate(Date preRefundedDate) {
        this.preRefundedDate = preRefundedDate;
    }

    /**
     * Getter method for property <tt>dividendPaymentFrequencyNbr</tt>.
     * @return property value of dividendPaymentFrequencyNbr
     */
    public BigDecimal getDividendPaymentFrequencyNbr() {
        return dividendPaymentFrequencyNbr;
    }

    /**
     * Setter method for property <tt>dividendPaymentFrequencyNbr</tt>.
     * @param dividendPaymentFrequencyNbr value to be assigned to property dividendPaymentFrequencyNbr
     */
    public void setDividendPaymentFrequencyNbr(BigDecimal dividendPaymentFrequencyNbr) {
        this.dividendPaymentFrequencyNbr = dividendPaymentFrequencyNbr;
    }

    /**
     * Getter method for property <tt>fdrEquityInd</tt>.
     * @return property value of fdrEquityInd
     */
    public String getFdrEquityInd() {
        return fdrEquityInd;
    }

    /**
     * Setter method for property <tt>fdrEquityInd</tt>.
     * @param fdrEquityInd value to be assigned to property fdrEquityInd
     */
    public void setFdrEquityInd(String fdrEquityInd) {
        this.fdrEquityInd = fdrEquityInd;
    }

    /**
     * Getter method for property <tt>originalIssuePrc</tt>.
     * @return property value of originalIssuePrc
     */
    public BigDecimal getOriginalIssuePrc() {
        return originalIssuePrc;
    }

    /**
     * Setter method for property <tt>originalIssuePrc</tt>.
     * @param originalIssuePrc value to be assigned to property originalIssuePrc
     */
    public void setOriginalIssuePrc(BigDecimal originalIssuePrc) {
        this.originalIssuePrc = originalIssuePrc;
    }

    /**
     * Getter method for property <tt>fdrStepBondInd</tt>.
     * @return property value of fdrStepBondInd
     */
    public String getFdrStepBondInd() {
        return fdrStepBondInd;
    }

    /**
     * Setter method for property <tt>fdrStepBondInd</tt>.
     * @param fdrStepBondInd value to be assigned to property fdrStepBondInd
     */
    public void setFdrStepBondInd(String fdrStepBondInd) {
        this.fdrStepBondInd = fdrStepBondInd;
    }

    /**
     * Getter method for property <tt>fdrInstrumentClassNm</tt>.
     * @return property value of fdrInstrumentClassNm
     */
    public String getFdrInstrumentClassNm() {
        return fdrInstrumentClassNm;
    }

    /**
     * Setter method for property <tt>fdrInstrumentClassNm</tt>.
     * @param fdrInstrumentClassNm value to be assigned to property fdrInstrumentClassNm
     */
    public void setFdrInstrumentClassNm(String fdrInstrumentClassNm) {
        this.fdrInstrumentClassNm = fdrInstrumentClassNm;
    }

    /**
     * Getter method for property <tt>prospectiveYieldMethodCd</tt>.
     * @return property value of prospectiveYieldMethodCd
     */
    public String getProspectiveYieldMethodCd() {
        return prospectiveYieldMethodCd;
    }

    /**
     * Setter method for property <tt>prospectiveYieldMethodCd</tt>.
     * @param prospectiveYieldMethodCd value to be assigned to property prospectiveYieldMethodCd
     */
    public void setProspectiveYieldMethodCd(String prospectiveYieldMethodCd) {
        this.prospectiveYieldMethodCd = prospectiveYieldMethodCd;
    }

    /**
     * Getter method for property <tt>finalMaturityDate</tt>.
     * @return property value of finalMaturityDate
     */
    public Date getFinalMaturityDate() {
        return finalMaturityDate;
    }

    /**
     * Setter method for property <tt>finalMaturityDate</tt>.
     * @param finalMaturityDate value to be assigned to property finalMaturityDate
     */
    public void setFinalMaturityDate(Date finalMaturityDate) {
        this.finalMaturityDate = finalMaturityDate;
    }

    /**
     * Getter method for property <tt>hybridCalculationCd</tt>.
     * @return property value of hybridCalculationCd
     */
    public String getHybridCalculationCd() {
        return hybridCalculationCd;
    }

    /**
     * Setter method for property <tt>hybridCalculationCd</tt>.
     * @param hybridCalculationCd value to be assigned to property hybridCalculationCd
     */
    public void setHybridCalculationCd(String hybridCalculationCd) {
        this.hybridCalculationCd = hybridCalculationCd;
    }

    /**
     * Getter method for property <tt>originalIssueDate</tt>.
     * @return property value of originalIssueDate
     */
    public Date getOriginalIssueDate() {
        return originalIssueDate;
    }

    /**
     * Setter method for property <tt>originalIssueDate</tt>.
     * @param originalIssueDate value to be assigned to property originalIssueDate
     */
    public void setOriginalIssueDate(Date originalIssueDate) {
        this.originalIssueDate = originalIssueDate;
    }

    /**
     * Getter method for property <tt>maturityPrc</tt>.
     * @return property value of maturityPrc
     */
    public BigDecimal getMaturityPrc() {
        return maturityPrc;
    }

    /**
     * Setter method for property <tt>maturityPrc</tt>.
     * @param maturityPrc value to be assigned to property maturityPrc
     */
    public void setMaturityPrc(BigDecimal maturityPrc) {
        this.maturityPrc = maturityPrc;
    }

    /**
     * Getter method for property <tt>defaultInd</tt>.
     * @return property value of defaultInd
     */
    public String getDefaultInd() {
        return defaultInd;
    }

    /**
     * Setter method for property <tt>defaultInd</tt>.
     * @param defaultInd value to be assigned to property defaultInd
     */
    public void setDefaultInd(String defaultInd) {
        this.defaultInd = defaultInd;
    }

    /**
     * Getter method for property <tt>derFofSecurityCd</tt>.
     * @return property value of derFofSecurityCd
     */
    public String getDerFofSecurityCd() {
        return derFofSecurityCd;
    }

    /**
     * Setter method for property <tt>derFofSecurityCd</tt>.
     * @param derFofSecurityCd value to be assigned to property derFofSecurityCd
     */
    public void setDerFofSecurityCd(String derFofSecurityCd) {
        this.derFofSecurityCd = derFofSecurityCd;
    }

    /**
     * Getter method for property <tt>interestRateSchedules</tt>.
     * @return property value of interestRateSchedules
     */
    public List<InterestRateSchedule> getInterestRateSchedules() {
        return interestRateSchedules;
    }

    /**
     * Setter method for property <tt>interestRateSchedules</tt>.
     * @param interestRateSchedules value to be assigned to property interestRateSchedules
     */
    public void setInterestRateSchedules(List<InterestRateSchedule> interestRateSchedules) {
        this.interestRateSchedules = interestRateSchedules;
    }

    /**
     * Getter method for property <tt>cashDividendSchedules</tt>.
     * @return property value of cashDividendSchedules
     */
    public List<CashDividendSchedule> getCashDividendSchedules() {
        return cashDividendSchedules;
    }

    /**
     * Setter method for property <tt>cashDividendSchedules</tt>.
     * @param cashDividendSchedules value to be assigned to property cashDividendSchedules
     */
    public void setCashDividendSchedules(List<CashDividendSchedule> cashDividendSchedules) {
        this.cashDividendSchedules = cashDividendSchedules;
    }

    /**
     * Getter method for property <tt>callSchedules</tt>.
     * @return property value of callSchedules
     */
    public List<CallSchedule> getCallSchedules() {
        return callSchedules;
    }

    /**
     * Setter method for property <tt>callSchedules</tt>.
     * @param callSchedules value to be assigned to property callSchedules
     */
    public void setCallSchedules(List<CallSchedule> callSchedules) {
        this.callSchedules = callSchedules;
    }

    /**
     * Getter method for property <tt>putSchedules</tt>.
     * @return property value of putSchedules
     */
    public List<PutSchedule> getPutSchedules() {
        return putSchedules;
    }

    /**
     * Setter method for property <tt>putSchedules</tt>.
     * @param putSchedules value to be assigned to property putSchedules
     */
    public void setPutSchedules(List<PutSchedule> putSchedules) {
        this.putSchedules = putSchedules;
    }

    /**
     * Getter method for property <tt>underlyingInstruments</tt>.
     * @return property value of underlyingInstruments
     */
    public List<UnderlyingInstrumentLink> getUnderlyingInstruments() {
        return underlyingInstruments;
    }

    /**
     * Setter method for property <tt>underlyingInstruments</tt>.
     * @param underlyingInstruments value to be assigned to property underlyingInstruments
     */
    public void setUnderlyingInstruments(List<UnderlyingInstrumentLink> underlyingInstruments) {
        this.underlyingInstruments = underlyingInstruments;
    }

    /**
     * Getter method for property <tt>tradableEntities</tt>.
     * @return property value of tradableEntities
     */
    public List<TradableEntity> getTradableEntities() {
        return tradableEntities;
    }

    /**
     * Setter method for property <tt>tradableEntities</tt>.
     * @param tradableEntities value to be assigned to property tradableEntities
     */
    public void setTradableEntities(List<TradableEntity> tradableEntities) {
        this.tradableEntities = tradableEntities;
    }
}
