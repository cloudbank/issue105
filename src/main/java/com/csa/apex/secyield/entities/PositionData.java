package com.csa.apex.secyield.entities;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

/**
 * 
 * A PositionData
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
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
	private String portfolioNumber;
	
	/**
	 * The portfolio name
	 */
	private String portfolioName;
	
	/**
	 * The report date
	 */
	private BigDecimal reportDate;
	
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
	private BigDecimal shareParAmount;
	
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
	public PositionData()
	{
		// default empty constructor
	}
	
	/**
	 * Getter securityIdentifier
	 * @return securityIdentifier
	 */
	public String getSecurityIdentifier() {
		return securityIdentifier;
	}

	/**
	 * Setter securityIdentifier
	 * @param securityIdentifier
	 */
	public void setSecurityIdentifier(String securityIdentifier) {
		this.securityIdentifier = securityIdentifier;
	}

	/**
	 * Getter portfolioNumber
	 * @return portfolioNumber
	 */
	public String getPortfolioNumber() {
		return portfolioNumber;
	}
	
	/**
	 * Setter portfolioNumber
	 * @param portfolioNumber
	 */
	public void setPortfolioNumber(String portfolioNumber) {
		this.portfolioNumber = portfolioNumber;
	}
	
	/**
	 * Getter portfolioName
	 * @return portfolioName
	 */
	public String getPortfolioName() {
		return portfolioName;
	}
	
	/**
	 * Setter portfolioName
	 * @param portfolioName
	 */
	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	/**
	 * Getter reportDate
	 * @return reportDate
	 */
	public BigDecimal getReportDate() {
		return reportDate;
	}
	
	/**
	 * Setter reportDate
	 * @param reportDate
	 */
	public void setReportDate(BigDecimal reportDate) {
		this.reportDate = reportDate;
	}
	
	/**
	 * Getter earnedInflationaryCompensationBase
	 * @return earnedInflationaryCompensationBase
	 */
	public BigDecimal getEarnedInflationaryCompensationBase() {
		return earnedInflationaryCompensationBase;
	}
	
	/**
	 * Setter earnedInflationaryCompensationBase
	 * @param earnedInflationaryCompensationBase
	 */
	public void setEarnedInflationaryCompensationBase(BigDecimal earnedInflationaryCompensationBase) {
		this.earnedInflationaryCompensationBase = earnedInflationaryCompensationBase;
	}

	/**
	 * Getter accruedIncome
	 * @return accruedIncome
	 */
	public BigDecimal getAccruedIncome() {
		return accruedIncome;
	}

	/**
	 * Setter accruedIncome
	 * @param accruedIncome
	 */
	public void setAccruedIncome(BigDecimal accruedIncome) {
		this.accruedIncome = accruedIncome;
	}

	/**
	 * Getter marketValue
	 * @return marketValue
	 */
	public BigDecimal getMarketValue() {
		return marketValue;
	}

	/**
	 * Setter marketValue
	 * @param marketValue
	 */
	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	/**
	 * Getter shareParAmount
	 * @return shareParAmount
	 */
	public BigDecimal getShareParAmount() {
		return shareParAmount;
	}

	/**
	 * Setter shareParAmount
	 * @param shareParAmount
	 */
	public void setShareParAmount(BigDecimal shareParAmount) {
		this.shareParAmount = shareParAmount;
	}

	/**
	 * Getter earnedAmortizationBase
	 * @return earnedAmortizationBase
	 */
	public BigDecimal getEarnedAmortizationBase() {
		return earnedAmortizationBase;
	}

	/**
	 * Setter earnedAmortizationBase
	 * @param earnedAmortizationBase
	 */
	public void setEarnedAmortizationBase(BigDecimal earnedAmortizationBase) {
		this.earnedAmortizationBase = earnedAmortizationBase;
	}

	/**
	 * Getter positionValInflationAdjShare
	 * @return positionValInflationAdjShare
	 */
	public BigDecimal getPositionValInflationAdjShare() {
		return positionValInflationAdjShare;
	}

	/**
	 * Setter positionValInflationAdjShare
	 * @param positionValInflationAdjShare
	 */
	public void setPositionValInflationAdjShare(BigDecimal positionValInflationAdjShare) {
		this.positionValInflationAdjShare = positionValInflationAdjShare;
	}
	
	/**
	 * Getter derOneDaySecurityIncome
	 * @return derOneDaySecurityIncome
	 */
	public BigDecimal getDerOneDaySecurityIncome() {
		return derOneDaySecurityIncome;
	}
	
	/**
	 * Setter derOneDaySecurityIncome
	 * @param derOneDaySecurityIncome
	 */
	public void setDerOneDaySecurityIncome(BigDecimal derOneDaySecurityIncome) {
		this.derOneDaySecurityIncome = derOneDaySecurityIncome;
	}
	

	
}
