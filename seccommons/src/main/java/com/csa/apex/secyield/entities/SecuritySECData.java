/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.entities;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.stereotype.Component;

/**
 * 
 * The Security SEC data
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
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
	public SecuritySECData() {
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
	 * Getter securityReferenceData
	 * 
	 * @return securityReferenceData
	 */
	public SecurityReferenceData getSecurityReferenceData() {
		return securityReferenceData;
	}

	/**
	 * Setter securityReferenceData
	 * 
	 * @param securityReferenceData
	 */
	public void setSecurityReferenceData(SecurityReferenceData securityReferenceData) {
		this.securityReferenceData = securityReferenceData;
	}

	/**
	 * Getter derCleanPrice
	 * 
	 * @return derCleanPrice
	 */
	public BigDecimal getDerCleanPrice() {
		return derCleanPrice;
	}

	/**
	 * Setter derCleanPrice
	 * 
	 * @param derCleanPrice
	 */
	public void setDerCleanPrice(BigDecimal derCleanPrice) {
		this.derCleanPrice = derCleanPrice;
	}

	/**
	 * Getter positionData
	 * 
	 * @return
	 */
	public PositionData[] getPositionData() {
		return positionData;
	}

	/**
	 * Setter positionData
	 * 
	 * @param positionData
	 */
	public void setPositionData(PositionData[] positionData) {
		this.positionData = positionData;
	}

	/**
	 * Getter derYieldCalcEngine
	 * 
	 * @return derYieldCalcEngine
	 */
	public String getDerYieldCalcEngine() {
		return derYieldCalcEngine;
	}

	/**
	 * Setter derYieldCalcEngine
	 * 
	 * @param derYieldCalcEngine
	 */
	public void setDerYieldCalcEngine(String derYieldCalcEngine) {
		this.derYieldCalcEngine = derYieldCalcEngine;
	}

	/**
	 * Getter derIncomeCalcEngine
	 * 
	 * @return derIncomeCalcEngine
	 */
	public String getDerIncomeCalcEngine() {
		return derIncomeCalcEngine;
	}

	/**
	 * Setter derIncomeCalcEngine
	 * 
	 * @param derIncomeCalcEngine
	 */
	public void setDerIncomeCalcEngine(String derIncomeCalcEngine) {
		this.derIncomeCalcEngine = derIncomeCalcEngine;
	}

	/**
	 * Getter derOneDaySecurityYield
	 * 
	 * @return derOneDaySecurityYield
	 */
	public BigDecimal getDerOneDaySecurityYield() {
		return derOneDaySecurityYield;
	}

	/**
	 * Setter derOneDaySecurityYield
	 * 
	 * @param derOneDaySecurityYield
	 */
	public void setDerOneDaySecurityYield(BigDecimal derOneDaySecurityYield) {
		this.derOneDaySecurityYield = derOneDaySecurityYield;
	}

	/**
	 * Getter derRedemptionDate
	 * 
	 * @return derRedemptionDate
	 */
	public Date getDerRedemptionDate() {
		return derRedemptionDate;
	}

	/**
	 * Setter derRedemptionDate
	 * 
	 * @param derRedemptionDate
	 */
	public void setDerRedemptionDate(Date derRedemptionDate) {
		this.derRedemptionDate = derRedemptionDate;
	}

	/**
	 * Getter derRedemptionPrice
	 * 
	 * @return derRedemptionPrice
	 */
	public BigDecimal getDerRedemptionPrice() {
		return derRedemptionPrice;
	}

	/**
	 * Setter derRedemptionPrice
	 * 
	 * @param derRedemptionPrice
	 */
	public void setDerRedemptionPrice(BigDecimal derRedemptionPrice) {
		this.derRedemptionPrice = derRedemptionPrice;
	}

	/**
	 * Getter derSecurityType
	 * 
	 * @return derSecurityType
	 */
	public String getDerSecurityType() {
		return derSecurityType;
	}

	/**
	 * Setter derSecurityType
	 * 
	 * @param derSecurityType
	 */
	public void setDerSecurityType(String derSecurityType) {
		this.derSecurityType = derSecurityType;
	}

	/**
	 * Getter derTIPSInflationaryRatio
	 * 
	 * @return derTIPSInflationaryRatio
	 */
	public BigDecimal getDerTIPSInflationaryRatio() {
		return derTIPSInflationaryRatio;
	}

	/**
	 * Setter derTIPSInflationaryRatio
	 * 
	 * @param derTIPSInflationaryRatio
	 */
	public void setDerTIPSInflationaryRatio(BigDecimal derTIPSInflationaryRatio) {
		this.derTIPSInflationaryRatio = derTIPSInflationaryRatio;
	}

	/**
	 * Getter securityPrice
	 * 
	 * @return securityPrice
	 */
	public BigDecimal getSecurityPrice() {
		return securityPrice;
	}

	/**
	 * Setter securityPrice
	 * 
	 * @param securityPrice
	 */
	public void setSecurityPrice(BigDecimal securityPrice) {
		this.securityPrice = securityPrice;
	}

	/**
	 * Getter fxRate
	 * 
	 * @return fxRate
	 */
	public BigDecimal getFxRate() {
		return fxRate;
	}

	/**
	 * Setter fxRate
	 * 
	 * @param fxRate
	 */
	public void setFxRate(BigDecimal fxRate) {
		this.fxRate = fxRate;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SecuritySECData)) {
			return false;
		}
		SecuritySECData castOther = (SecuritySECData) other;
		final boolean equals = new EqualsBuilder().append(securityIdentifier, castOther.securityIdentifier)
				.append(reportDate, castOther.reportDate).append(securityReferenceData, castOther.securityReferenceData)
				.append(derCleanPrice, castOther.derCleanPrice).append(positionData, castOther.positionData)
				.append(derYieldCalcEngine, castOther.derYieldCalcEngine)
				.append(derIncomeCalcEngine, castOther.derIncomeCalcEngine)
				.append(derOneDaySecurityYield, castOther.derOneDaySecurityYield)
				.append(derRedemptionDate, castOther.derRedemptionDate)
				.append(derRedemptionPrice, castOther.derRedemptionPrice)
				.append(derSecurityType, castOther.derSecurityType)
				.append(derTIPSInflationaryRatio, castOther.derTIPSInflationaryRatio)
				.append(securityPrice, castOther.securityPrice).append(fxRate, castOther.fxRate).isEquals();
		return equals;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(securityIdentifier).append(reportDate).append(securityReferenceData)
				.append(derCleanPrice).append(positionData).append(derYieldCalcEngine).append(derIncomeCalcEngine)
				.append(derOneDaySecurityYield).append(derRedemptionDate).append(derRedemptionPrice)
				.append(derSecurityType).append(derTIPSInflationaryRatio).append(securityPrice).append(fxRate)
				.toHashCode();
	}

	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return "SecuritySECData{" +
				"securityIdentifier='" + securityIdentifier + '\'' +
				", reportDate=" + formatter.format(reportDate) +
				", securityReferenceData=" + securityReferenceData +
				", derCleanPrice=" + derCleanPrice +
				", positionData=" + Arrays.toString(positionData) +
				", derYieldCalcEngine='" + derYieldCalcEngine + '\'' +
				", derIncomeCalcEngine='" + derIncomeCalcEngine + '\'' +
				", derOneDaySecurityYield=" + derOneDaySecurityYield +
				", derRedemptionDate=" + formatter.format(derRedemptionDate) +
				", derRedemptionPrice=" + derRedemptionPrice +
				", derSecurityType='" + derSecurityType + '\'' +
				", derTIPSInflationaryRatio=" + derTIPSInflationaryRatio +
				", securityPrice=" + securityPrice +
				", fxRate=" + fxRate +
				'}';
	}
}
