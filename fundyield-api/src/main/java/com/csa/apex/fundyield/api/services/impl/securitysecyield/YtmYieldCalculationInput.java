/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;
import java.util.Date;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationInput;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

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
	private BigDecimal marketPrice;

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
	private BigDecimal fdrTipsInsflationaryRatio;

	/**
	 * Current income rate.
	 */
	private BigDecimal currentIncomeRate;

	/**
	 * Maturity price.
	 */
	private BigDecimal maturityPrice;

	/**
	 * Constructor.
	 * 
	 * @param configuration
	 *            The SEC configuration
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
	 * Getter method for property <tt>marketPrice</tt>.
	 * 
	 * @return property value of marketPrice
	 */
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	/**
	 * Setter method for property <tt>marketPrice</tt>.
	 * 
	 * @param marketPrice
	 *            value to be assigned to property marketPrice
	 */
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
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
	 * Getter method for property <tt>fdrTipsInsflationaryRatio</tt>.
	 * 
	 * @return property value of fdrTipsInsflationaryRatio
	 */
	public BigDecimal getFdrTipsInsflationaryRatio() {
		return fdrTipsInsflationaryRatio;
	}

	/**
	 * Setter method for property <tt>fdrTipsInsflationaryRatio</tt>.
	 * 
	 * @param fdrTipsInsflationaryRatio
	 *            value to be assigned to property fdrTipsInsflationaryRatio
	 */
	public void setFdrTipsInsflationaryRatio(BigDecimal fdrTipsInsflationaryRatio) {
		this.fdrTipsInsflationaryRatio = fdrTipsInsflationaryRatio;
	}

	/**
	 * Getter method for property <tt>currentIncomeRate</tt>.
	 * 
	 * @return property value of currentIncomeRate
	 */
	public BigDecimal getCurrentIncomeRate() {
		return currentIncomeRate;
	}

	/**
	 * Setter method for property <tt>currentIncomeRate</tt>.
	 * 
	 * @param currentIncomeRate
	 *            value to be assigned to property currentIncomeRate
	 */
	public void setCurrentIncomeRate(BigDecimal currentIncomeRate) {
		this.currentIncomeRate = currentIncomeRate;
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
