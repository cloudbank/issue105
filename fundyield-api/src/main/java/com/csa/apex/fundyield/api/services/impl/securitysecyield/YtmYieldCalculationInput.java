/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;
import java.util.Date;

import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;

/**
 * The YTM Yield calculation input.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class YtmYieldCalculationInput extends BaseCalculationInput {

	/**
	 * Number of days in period.
	 */
	private int numOfDaysInPeriod;

	/**
	 * Calculation step.
	 */
	private double calculationStep;

	/**
	 * Binary seach count.
	 */
	private int binarySearchCount;

	/**
	 * Frequency value.
	 */
	private int frequencyValue;

	/**
	 * Min yield when it is negative.
	 */
	private double minYield;

	/**
	 * Max yield when it is negative.
	 */
	private double maxYield;

	/**
	 * Market price.
	 */
	private BigDecimal mp;

	/**
	 * Maturity date.
	 */
	private Date maturityDate;

	/**
	 * Report date.
	 */
	private Date reportDate;

	/**
	 * Inflationary Index Ratio.
	 */
	private BigDecimal iir;

	/**
	 * Coupon (interest) Rate
	 */
	private BigDecimal cir;

	/**
	 * Maturity price.
	 */
	private BigDecimal maturityPrice;

	/**
	 * Constructor.
	 * @param configuration The SEC configuration
	 */
	public YtmYieldCalculationInput(SECConfiguration configuration) {
		super(configuration);
	}

	/**
	 * Getter method for property <tt>numOfDaysInPeriod</tt>.
	 * 
	 * @return property value of numOfDaysInPeriod
	 */
	public int getNumOfDaysInPeriod() {
		return numOfDaysInPeriod;
	}

	/**
	 * Setter method for property <tt>numOfDaysInPeriod</tt>.
	 * 
	 * @param numOfDaysInPeriod
	 *            value to be assigned to property numOfDaysInPeriod
	 */
	public void setNumOfDaysInPeriod(int numOfDaysInPeriod) {
		this.numOfDaysInPeriod = numOfDaysInPeriod;
	}

	/**
	 * Getter method for property <tt>calculationStep</tt>.
	 * 
	 * @return property value of calculationStep
	 */
	public double getCalculationStep() {
		return calculationStep;
	}

	/**
	 * Setter method for property <tt>calculationStep</tt>.
	 * 
	 * @param calculationStep
	 *            value to be assigned to property calculationStep
	 */
	public void setCalculationStep(double calculationStep) {
		this.calculationStep = calculationStep;
	}

	/**
	 * Getter method for property <tt>binarySearchCount</tt>.
	 * 
	 * @return property value of binarySearchCount
	 */
	public int getBinarySearchCount() {
		return binarySearchCount;
	}

	/**
	 * Setter method for property <tt>binarySearchCount</tt>.
	 * 
	 * @param binarySearchCount
	 *            value to be assigned to property binarySearchCount
	 */
	public void setBinarySearchCount(int binarySearchCount) {
		this.binarySearchCount = binarySearchCount;
	}

	/**
	 * Getter method for property <tt>frequencyValue</tt>.
	 * 
	 * @return property value of frequencyValue
	 */
	public int getFrequencyValue() {
		return frequencyValue;
	}

	/**
	 * Setter method for property <tt>frequencyValue</tt>.
	 * 
	 * @param frequencyValue
	 *            value to be assigned to property frequencyValue
	 */
	public void setFrequencyValue(int frequencyValue) {
		this.frequencyValue = frequencyValue;
	}

	/**
	 * Getter method for property <tt>minYield</tt>.
	 * 
	 * @return property value of minYield
	 */
	public double getMinYield() {
		return minYield;
	}

	/**
	 * Setter method for property <tt>minYield</tt>.
	 * 
	 * @param minYield
	 *            value to be assigned to property minYield
	 */
	public void setMinYield(double minYield) {
		this.minYield = minYield;
	}

	/**
	 * Getter method for property <tt>maxYield</tt>.
	 * 
	 * @return property value of maxYield
	 */
	public double getMaxYield() {
		return maxYield;
	}

	/**
	 * Setter method for property <tt>maxYield</tt>.
	 * 
	 * @param maxYield
	 *            value to be assigned to property maxYield
	 */
	public void setMaxYield(double maxYield) {
		this.maxYield = maxYield;
	}

	/**
	 * Getter method for property <tt>mp</tt>.
	 * 
	 * @return property value of mp
	 */
	public BigDecimal getMp() {
		return mp;
	}

	/**
	 * Setter method for property <tt>mp</tt>.
	 * 
	 * @param mp
	 *            value to be assigned to property mp
	 */
	public void setMp(BigDecimal mp) {
		this.mp = mp;
	}

	/**
	 * Getter method for property <tt>maturityDate</tt>.
	 * 
	 * @return property value of maturityDate
	 */
	public Date getMaturityDate() {
		return maturityDate;
	}

	/**
	 * Setter method for property <tt>maturityDate</tt>.
	 * 
	 * @param maturityDate
	 *            value to be assigned to property maturityDate
	 */
	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
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
	 * Getter method for property <tt>iir</tt>.
	 * 
	 * @return property value of iir
	 */
	public BigDecimal getIir() {
		return iir;
	}

	/**
	 * Setter method for property <tt>iir</tt>.
	 * 
	 * @param iir
	 *            value to be assigned to property iir
	 */
	public void setIir(BigDecimal iir) {
		this.iir = iir;
	}

	/**
	 * Getter method for property <tt>cir</tt>.
	 * 
	 * @return property value of cir
	 */
	public BigDecimal getCir() {
		return cir;
	}

	/**
	 * Setter method for property <tt>cir</tt>.
	 * 
	 * @param cir
	 *            value to be assigned to property cir
	 */
	public void setCir(BigDecimal cir) {
		this.cir = cir;
	}

	/**
	 * Getter method for property <tt>maturityPrice</tt>.
	 * 
	 * @return property value of maturityPrice
	 */
	public BigDecimal getMaturityPrice() {
		return maturityPrice;
	}

	/**
	 * Setter method for property <tt>maturityPrice</tt>.
	 * 
	 * @param maturityPrice
	 *            value to be assigned to property maturityPrice
	 */
	public void setMaturityPrice(BigDecimal maturityPrice) {
		this.maturityPrice = maturityPrice;
	}

}
