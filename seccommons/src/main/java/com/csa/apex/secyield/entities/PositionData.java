/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

/**
 * 
 * A PositionData
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
 *
 */
@Component
public class PositionData {

	/**
	 * Security identifier (CUSIP, ISIN, SEDOL, Other ID, etc.)
	 */
	private String securityIdentifier;

	/**
	 * The portfolio number
	 */
	private BigDecimal portfolioNumber;

	/**
	 * The portfolio name
	 */
	private String portfolioName;

	/**
	 * The report date
	 */
	private Date reportDate;

	/**
	 * The earned inflationary compensation base (inflation income)
	 */
	private BigDecimal earnedInflationaryCompensationBase;

	/**
	 * The accrued income
	 */
	private BigDecimal accruedIncome;

	/**
	 * The market value
	 */
	private BigDecimal marketValue;

	/**
	 * The share par amount (security shares)
	 */
	private BigDecimal sharePerAmount;

	/**
	 * The earned amortization base (daily earned amount)
	 */
	private BigDecimal earnedAmortizationBase;

	/**
	 * The position val inflation adj shares (inflationary shares)
	 */
	private BigDecimal positionValInflationAdjShare;

	/**
	 * The one day security income.
	 */
	private BigDecimal derOneDaySecurityIncome;

	/**
	 * Constructor
	 */
	public PositionData() {
		// default empty constructor
	}

	/**
	 * Getter securityIdentifier
	 * 
	 * @return securityIdentifier
	 */
	public String getSecurityIdentifier() {
		return securityIdentifier;
	}

	/**
	 * Setter securityIdentifier
	 * 
	 * @param securityIdentifier
	 */
	public void setSecurityIdentifier(String securityIdentifier) {
		this.securityIdentifier = securityIdentifier;
	}

	/**
	 * Getter portfolioNumber
	 * 
	 * @return portfolioNumber
	 */
	public BigDecimal getPortfolioNumber() {
		return portfolioNumber;
	}

	/**
	 * Setter portfolioNumber
	 * 
	 * @param portfolioNumber
	 */
	public void setPortfolioNumber(BigDecimal portfolioNumber) {
		this.portfolioNumber = portfolioNumber;
	}

	/**
	 * Getter portfolioName
	 * 
	 * @return portfolioName
	 */
	public String getPortfolioName() {
		return portfolioName;
	}

	/**
	 * Setter portfolioName
	 * 
	 * @param portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * Getter reportDate
	 * 
	 * @return reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}

	/**
	 * Setter reportDate
	 * 
	 * @param reportDate
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	/**
	 * Getter earnedInflationaryCompensationBase
	 * 
	 * @return earnedInflationaryCompensationBase
	 */
	public BigDecimal getEarnedInflationaryCompensationBase() {
		return earnedInflationaryCompensationBase;
	}

	/**
	 * Setter earnedInflationaryCompensationBase
	 * 
	 * @param earnedInflationaryCompensationBase
	 */
	public void setEarnedInflationaryCompensationBase(BigDecimal earnedInflationaryCompensationBase) {
		this.earnedInflationaryCompensationBase = earnedInflationaryCompensationBase;
	}

	/**
	 * Getter accruedIncome
	 * 
	 * @return accruedIncome
	 */
	public BigDecimal getAccruedIncome() {
		return accruedIncome;
	}

	/**
	 * Setter accruedIncome
	 * 
	 * @param accruedIncome
	 */
	public void setAccruedIncome(BigDecimal accruedIncome) {
		this.accruedIncome = accruedIncome;
	}

	/**
	 * Getter marketValue
	 * 
	 * @return marketValue
	 */
	public BigDecimal getMarketValue() {
		return marketValue;
	}

	/**
	 * Setter marketValue
	 * 
	 * @param marketValue
	 */
	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	/**
	 * Getter sharePerAmount
	 * 
	 * @return sharePerAmount
	 */
	public BigDecimal getSharePerAmount() {
		return sharePerAmount;
	}

	/**
	 * Setter sharePerAmount
	 * 
	 * @param sharePerAmount
	 */
	public void setSharePerAmount(BigDecimal sharePerAmount) {
		this.sharePerAmount = sharePerAmount;
	}

	/**
	 * Getter earnedAmortizationBase
	 * 
	 * @return earnedAmortizationBase
	 */
	public BigDecimal getEarnedAmortizationBase() {
		return earnedAmortizationBase;
	}

	/**
	 * Setter earnedAmortizationBase
	 * 
	 * @param earnedAmortizationBase
	 */
	public void setEarnedAmortizationBase(BigDecimal earnedAmortizationBase) {
		this.earnedAmortizationBase = earnedAmortizationBase;
	}

	/**
	 * Getter positionValInflationAdjShare
	 * 
	 * @return positionValInflationAdjShare
	 */
	public BigDecimal getPositionValInflationAdjShare() {
		return positionValInflationAdjShare;
	}

	/**
	 * Setter positionValInflationAdjShare
	 * 
	 * @param positionValInflationAdjShare
	 */
	public void setPositionValInflationAdjShare(BigDecimal positionValInflationAdjShare) {
		this.positionValInflationAdjShare = positionValInflationAdjShare;
	}

	/**
	 * Getter derOneDaySecurityIncome
	 * 
	 * @return derOneDaySecurityIncome
	 */
	public BigDecimal getDerOneDaySecurityIncome() {
		return derOneDaySecurityIncome;
	}

	/**
	 * Setter derOneDaySecurityIncome
	 * 
	 * @param derOneDaySecurityIncome
	 */
	public void setDerOneDaySecurityIncome(BigDecimal derOneDaySecurityIncome) {
		this.derOneDaySecurityIncome = derOneDaySecurityIncome;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof PositionData)) {
			return false;
		}
		PositionData castOther = (PositionData) other;
		return new EqualsBuilder().append(securityIdentifier, castOther.securityIdentifier)
				.append(portfolioNumber, castOther.portfolioNumber).append(portfolioName, castOther.portfolioName)
				.append(reportDate, castOther.reportDate)
				.append(earnedInflationaryCompensationBase, castOther.earnedInflationaryCompensationBase)
				.append(accruedIncome, castOther.accruedIncome).append(marketValue, castOther.marketValue)
				.append(sharePerAmount, castOther.sharePerAmount)
				.append(earnedAmortizationBase, castOther.earnedAmortizationBase)
				.append(positionValInflationAdjShare, castOther.positionValInflationAdjShare)
				.append(derOneDaySecurityIncome, castOther.derOneDaySecurityIncome).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(securityIdentifier).append(portfolioNumber).append(portfolioName)
				.append(reportDate).append(earnedInflationaryCompensationBase).append(accruedIncome).append(marketValue)
				.append(sharePerAmount).append(earnedAmortizationBase).append(positionValInflationAdjShare)
				.append(derOneDaySecurityIncome).toHashCode();
	}

	@Override
	public String toString() {
		return "PositionData{" +
				"securityIdentifier='" + securityIdentifier + '\'' +
				", portfolioNumber=" + portfolioNumber +
				", portfolioName='" + portfolioName + '\'' +
				", reportDate=" + reportDate +
				", earnedInflationaryCompensationBase=" + earnedInflationaryCompensationBase +
				", accruedIncome=" + accruedIncome +
				", marketValue=" + marketValue +
				", sharePerAmount=" + sharePerAmount +
				", earnedAmortizationBase=" + earnedAmortizationBase +
				", positionValInflationAdjShare=" + positionValInflationAdjShare +
				", derOneDaySecurityIncome=" + derOneDaySecurityIncome +
				'}';
	}
}
