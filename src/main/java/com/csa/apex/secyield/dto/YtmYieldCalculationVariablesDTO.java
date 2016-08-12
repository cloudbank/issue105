package com.csa.apex.secyield.dto;

import java.math.BigDecimal;

/**
 * YtmYieldCalculationVariablesDTO 
 * 
 * 
 * Used for storing variables for YTM yield calculation
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class YtmYieldCalculationVariablesDTO {
	/**
	 * Clean price
	 */
	private BigDecimal cleanPrice;
	
	/**
	 * Redemption value
	 */
	private BigDecimal redemptionValue;
	
	/**
	 * frequency
	 */
	private int frequency;
	
	/**
	 * Coupons between settlement and redemption date
	 */
	private int couponsBetSettlementRedemption;
	
	/**
	 * dsc
	 */
	private int dsc;
	
	/**
	 * No of days in the period
	 */
	private int noOfDaysInPeriod;
	
	/**
	 * Days between prior coupon date and settlement date
	 */
	private int daysBetPriorCouponDateSettlementDate;
	
	/**
	 * Annual Interest Rate
	 */
	private BigDecimal annualInterestRate;
	
	/**
	 * Getter cleanPrice
	 * @return cleanPrice
	 */
	public BigDecimal getCleanPrice() {
		return cleanPrice;
	}
	
	/**
	 * Setter cleanPrice
	 * @param cleanPrice
	 */
	public void setCleanPrice(BigDecimal cleanPrice) {
		this.cleanPrice = cleanPrice;
	}
	
	/**
	 * Getter redemptionValue
	 * @return redemptionValue
	 */
	public BigDecimal getRedemptionValue() {
		return redemptionValue;
	}
	
	/**
	 * Setter redemptionValue
	 * @param redemptionValue
	 */
	public void setRedemptionValue(BigDecimal redemptionValue) {
		this.redemptionValue = redemptionValue;
	}
	
	/**
	 * Getter frequency
	 * @return frequency
	 */
	public int getFrequency() {
		return frequency;
	}
	
	/**
	 * Setter frequency
	 * @param frequency
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	/**
	 * Getter couponsBetSettlementRedemption
	 * @return couponsBetSettlementRedemption
	 */
	public int getCouponsBetSettlementRedemption() {
		return couponsBetSettlementRedemption;
	}
	
	/**
	 * Setter couponsBetSettlementRedemption
	 * @param couponsBetSettlementRedemption
	 */
	public void setCouponsBetSettlementRedemption(int couponsBetSettlementRedemption) {
		this.couponsBetSettlementRedemption = couponsBetSettlementRedemption;
	}
	
	/**
	 * Getter dsc
	 * @return
	 */
	public int getDsc() {
		return dsc;
	}
	
	/**
	 * Setter dsc
	 * @param dsc
	 */
	public void setDsc(int dsc) {
		this.dsc = dsc;
	}
	
	/**
	 * Getter noOfDaysInPeriod
	 * @return noOfDaysInPeriod
	 */
	public int getNoOfDaysInPeriod() {
		return noOfDaysInPeriod;
	}
	
	/**
	 * Setter noOfDaysInPeriod
	 * @param noOfDaysInPeriod
	 */
	public void setNoOfDaysInPeriod(int noOfDaysInPeriod) {
		this.noOfDaysInPeriod = noOfDaysInPeriod;
	}
	
	/**
	 * Getter getDaysBetPriorCouponDateSettlementDate
	 * @return getDaysBetPriorCouponDateSettlementDate
	 */
	public int getDaysBetPriorCouponDateSettlementDate() {
		return daysBetPriorCouponDateSettlementDate;
	}
	
	/**
	 * Setter getDaysBetPriorCouponDateSettlementDate
	 * @param daysBetPriorCouponDateSettlementDate
	 */
	public void setDaysBetPriorCouponDateSettlementDate(int daysBetPriorCouponDateSettlementDate) {
		this.daysBetPriorCouponDateSettlementDate = daysBetPriorCouponDateSettlementDate;
	}
	
	/**
	 * Getter annualInterestRate
	 * @return annualInterestRate
	 */
	public BigDecimal getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	/**
	 * Setter annualInterestRate
	 * @param annualInterestRate
	 */
	public void setAnnualInterestRate(BigDecimal annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}
}
