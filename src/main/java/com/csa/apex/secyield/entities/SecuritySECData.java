package com.csa.apex.secyield.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * The Security SEC data
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 *
 */
@Component
public class SecuritySECData {
	
	/**
	 * Security identifier (CUSIP, ISIN, SEDOL, Other ID, etc.)
	 */
	private String securityIdentifier;
	
	/**
	 * Report Date
	 */
	private Date reportDate;
	
	/**
	 * The security reference data
	 */
	private SecurityReferenceData securityReferenceData;
	
	/**
	 * The clean price
	 */
	private BigDecimal derCleanPrice;
	
	/**
	 * The position data
	 */
	private PositionData[] positionData;
	
	/**
	 * The yield calculation engine name
	 */
	private String derYieldCalcEngine;
	
	/**
	 * The Income calculation engine name
	 */
	private String derIncomeCalcEngine;
	
	/**
	 * The one day security income
	 */
	private BigDecimal derOneDaySecurityYield;
	
	/**
	 * The redemption date
	 */
	private Date derRedemptionDate;
	
	/**
	 * The redemption price
	 */
	private BigDecimal derRedemptionPrice;
	
	/**
	 * The security type
	 */
	private String derSecurityType;
	
	/**
	 * TIPS inflationary ratio
	 */
	private BigDecimal derTIPSInflationaryRatio;
	
	/**
	 * The security (market) price
	 */
	private BigDecimal securityPrice;
	
	/**
	 * The FX rate
	 */
	private BigDecimal fxRate;
	
	
	/**
	 * Constructor
	 */
	public SecuritySECData()
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
	 * Getter reportDate
	 * @return reportDate
	 */
	public Date getReportDate() {
		return reportDate;
	}
	
	/**
	 * Setter reportDate
	 * @param reportDate
	 */
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	
	/**
	 * Getter securityReferenceData
	 * @return securityReferenceData
	 */
	public SecurityReferenceData getSecurityReferenceData() {
		return securityReferenceData;
	}
	
	/**
	 * Setter securityReferenceData
	 * @param securityReferenceData
	 */
	public void setSecurityReferenceData(SecurityReferenceData securityReferenceData) {
		this.securityReferenceData = securityReferenceData;
	}
	
	/**
	 * Getter derCleanPrice
	 * @return derCleanPrice
	 */
	public BigDecimal getDerCleanPrice() {
		return derCleanPrice;
	}
	
	/**
	 * Setter derCleanPrice
	 * @param derCleanPrice
	 */
	public void setDerCleanPrice(BigDecimal derCleanPrice) {
		this.derCleanPrice = derCleanPrice;
	}
	
	/**
	 * Getter positionData
	 * @return
	 */
	public PositionData[] getPositionData() {
		return positionData;
	}
	
	/**
	 * Setter positionData
	 * @param positionData
	 */
	public void setPositionData(PositionData[] positionData) {
		this.positionData = positionData;
	}
	
	/**
	 * Getter derYieldCalcEngine
	 * @return derYieldCalcEngine
	 */
	public String getDerYieldCalcEngine() {
		return derYieldCalcEngine;
	}
	
	/**
	 * Setter derYieldCalcEngine
	 * @param derYieldCalcEngine
	 */
	public void setDerYieldCalcEngine(String derYieldCalcEngine) {
		this.derYieldCalcEngine = derYieldCalcEngine;
	}
	
	/**
	 * Getter derIncomeCalcEngine
	 * @return derIncomeCalcEngine
	 */
	public String getDerIncomeCalcEngine() {
		return derIncomeCalcEngine;
	}
	
	/**
	 * Setter derIncomeCalcEngine
	 * @param derIncomeCalcEngine
	 */
	public void setDerIncomeCalcEngine(String derIncomeCalcEngine) {
		this.derIncomeCalcEngine = derIncomeCalcEngine;
	}
	
	/**
	 * Getter derOneDaySecurityYield
	 * @return derOneDaySecurityYield
	 */
	public BigDecimal getDerOneDaySecurityYield() {
		return derOneDaySecurityYield;
	}
	
	/**
	 * Setter derOneDaySecurityYield
	 * @param derOneDaySecurityYield
	 */
	public void setDerOneDaySecurityYield(BigDecimal derOneDaySecurityYield) {
		this.derOneDaySecurityYield = derOneDaySecurityYield;
	}
	
	/**
	 * Getter derRedemptionDate
	 * @return derRedemptionDate
	 */
	public Date getDerRedemptionDate() {
		return derRedemptionDate;
	}
	
	/**
	 * Setter derRedemptionDate
	 * @param derRedemptionDate
	 */
	public void setDerRedemptionDate(Date derRedemptionDate) {
		this.derRedemptionDate = derRedemptionDate;
	}
	
	/**
	 * Getter derRedemptionPrice
	 * @return derRedemptionPrice
	 */
	public BigDecimal getDerRedemptionPrice() {
		return derRedemptionPrice;
	}
	
	/**
	 * Setter derRedemptionPrice
	 * @param derRedemptionPrice
	 */
	public void setDerRedemptionPrice(BigDecimal derRedemptionPrice) {
		this.derRedemptionPrice = derRedemptionPrice;
	}
	
	/**
	 * Getter derSecurityType
	 * @return derSecurityType
	 */
	public String getDerSecurityType() {
		return derSecurityType;
	}
	
	/**
	 * Setter derSecurityType
	 * @param derSecurityType
	 */
	public void setDerSecurityType(String derSecurityType) {
		this.derSecurityType = derSecurityType;
	}
	
	/**
	 * Getter derTIPSInflationaryRatio
	 * @return derTIPSInflationaryRatio
	 */
	public BigDecimal getDerTIPSInflationaryRatio() {
		return derTIPSInflationaryRatio;
	}
	
	/**
	 * Setter derTIPSInflationaryRatio
	 * @param derTIPSInflationaryRatio
	 */
	public void setDerTIPSInflationaryRatio(BigDecimal derTIPSInflationaryRatio) {
		this.derTIPSInflationaryRatio = derTIPSInflationaryRatio;
	}
	
	/**
	 * Getter securityPrice
	 * @return securityPrice
	 */
	public BigDecimal getSecurityPrice() {
		return securityPrice;
	}
	
	/**
	 * Setter securityPrice
	 * @param securityPrice
	 */
	public void setSecurityPrice(BigDecimal securityPrice) {
		this.securityPrice = securityPrice;
	}
	
	/**
	 * Getter fxRate
	 * @return fxRate
	 */
	public BigDecimal getFxRate() {
		return fxRate;
	}
	
	/**
	 * Setter fxRate
	 * @param fxRate
	 */
	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}
	
	
	
}
