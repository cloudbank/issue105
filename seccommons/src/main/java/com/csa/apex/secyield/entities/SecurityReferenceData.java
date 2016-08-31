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
 * The security reference data
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
 *
 */
@Component
public class SecurityReferenceData {

	/**
	 * Security identifier (CUSIP, ISIN, SEDOL, Other ID, etc.)
	 */
	private String securityIdentifier;

	/**
	 * The specific security type (eg: CMO, STEP, MUNI - to be computed by the rules flow)
	 */
	private String ivType;

	/**
	 * Security Short Name
	 */
	private String securityName;

	/**
	 * Final Maturity Date
	 */
	private Date finalMaturityDate;

	/**
	 * The security redemption price (maturity price)
	 */
	private BigDecimal securityRedemptionPrice;

	/**
	 * The interest (coupon) rate
	 */
	private BigDecimal interestRt;

	/**
	 * The default indicator
	 */
	private boolean defIndicator;

	/**
	 * The step indicator
	 */
	private boolean derStepIndicator;

	/**
	 * The hybrid indicator
	 */
	private boolean derHybridIndicator;

	/**
	 * Io hybrid field
	 */
	private String ioHybridField;

	/**
	 * As 400 rate type
	 */
	private String as400RateType;

	/**
	 * The prospective method
	 */
	private String prospectiveMethod;

	/**
	 * Constructor
	 */
	public SecurityReferenceData() {
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
	 * Getter ivType
	 * 
	 * @return ivType
	 */
	public String getIvType() {
		return ivType;
	}

	/**
	 * Setter ivType
	 * 
	 * @param ivType
	 */
	public void setIvType(String ivType) {
		this.ivType = ivType;
	}

	/**
	 * Getter securityName
	 * 
	 * @return securityName
	 */
	public String getSecurityName() {
		return securityName;
	}

	/**
	 * Setter securityName
	 * 
	 * @param securityName
	 */
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	/**
	 * Getter finalMaturityDate
	 * 
	 * @return finalMaturityDate
	 */
	public Date getFinalMaturityDate() {
		return finalMaturityDate;
	}

	/**
	 * Setter finalMaturityDate
	 * 
	 * @param finalMaturityDate
	 */
	public void setFinalMaturityDate(Date finalMaturityDate) {
		this.finalMaturityDate = finalMaturityDate;
	}

	/**
	 * Getter securityRedemptionPrice
	 * 
	 * @return securityRedemptionPrice
	 */
	public BigDecimal getSecurityRedemptionPrice() {
		return securityRedemptionPrice;
	}

	/**
	 * Setter securityRedemptionPrice
	 * 
	 * @param securityRedemptionPrice
	 */
	public void setSecurityRedemptionPrice(BigDecimal securityRedemptionPrice) {
		this.securityRedemptionPrice = securityRedemptionPrice;
	}

	/**
	 * Getter interestRt
	 * 
	 * @return interestRt
	 */
	public BigDecimal getInterestRt() {
		return interestRt;
	}

	/**
	 * Setter interestRt
	 * 
	 * @param interestRt
	 */
	public void setInterestRt(BigDecimal interestRt) {
		this.interestRt = interestRt;
	}

	/**
	 * Getter defIndicator
	 * 
	 * @return defIndicator
	 */
	public boolean isDefIndicator() {
		return defIndicator;
	}

	/**
	 * Setter defIndicator
	 * 
	 * @param defIndicator
	 */
	public void setDefIndicator(boolean defIndicator) {
		this.defIndicator = defIndicator;
	}

	/**
	 * Getter derStepIndicator
	 * 
	 * @return derStepIndicator
	 */
	public boolean isDerStepIndicator() {
		return derStepIndicator;
	}

	/**
	 * Setter derStepIndicator
	 * 
	 * @param derStepIndicator
	 */
	public void setDerStepIndicator(boolean derStepIndicator) {
		this.derStepIndicator = derStepIndicator;
	}

	/**
	 * Getter derHybridIndicator
	 * 
	 * @return derHybridIndicator
	 */
	public boolean isDerHybridIndicator() {
		return derHybridIndicator;
	}

	/**
	 * Setter derHybridIndicator
	 * 
	 * @param derHybridIndicator
	 */
	public void setDerHybridIndicator(boolean derHybridIndicator) {
		this.derHybridIndicator = derHybridIndicator;
	}

	/**
	 * Getter derHybridIndicator
	 * 
	 * @return derHybridIndicator
	 */
	public String getIoHybridField() {
		return ioHybridField;
	}

	/**
	 * Setter derHybridIndicator
	 * 
	 * @param ioHybridField
	 */
	public void setIoHybridField(String ioHybridField) {
		this.ioHybridField = ioHybridField;
	}

	/**
	 * Getter as400RateType
	 * 
	 * @return as400RateType
	 */
	public String getAs400RateType() {
		return as400RateType;
	}

	/**
	 * Setter as400RateType
	 * 
	 * @param as400RateType
	 */
	public void setAs400RateType(String as400RateType) {
		this.as400RateType = as400RateType;
	}

	/**
	 * Getter prospectiveMethod
	 * 
	 * @return prospectiveMethod
	 */
	public String getProspectiveMethod() {
		return prospectiveMethod;
	}

	/**
	 * Setter prospectiveMethod
	 * 
	 * @param prospectiveMethod
	 */
	public void setProspectiveMethod(String prospectiveMethod) {
		this.prospectiveMethod = prospectiveMethod;
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof SecurityReferenceData)) {
			return false;
		}
		SecurityReferenceData castOther = (SecurityReferenceData) other;
		return new EqualsBuilder().append(securityIdentifier, castOther.securityIdentifier)
				.append(ivType, castOther.ivType).append(securityName, castOther.securityName)
				.append(finalMaturityDate, castOther.finalMaturityDate)
				.append(securityRedemptionPrice, castOther.securityRedemptionPrice)
				.append(interestRt, castOther.interestRt).append(defIndicator, castOther.defIndicator)
				.append(derStepIndicator, castOther.derStepIndicator)
				.append(derHybridIndicator, castOther.derHybridIndicator).append(ioHybridField, castOther.ioHybridField)
				.append(as400RateType, castOther.as400RateType).append(prospectiveMethod, castOther.prospectiveMethod)
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(securityIdentifier).append(ivType).append(securityName)
				.append(finalMaturityDate).append(securityRedemptionPrice).append(interestRt).append(defIndicator)
				.append(derStepIndicator).append(derHybridIndicator).append(ioHybridField).append(as400RateType)
				.append(prospectiveMethod).toHashCode();
	}

	@Override
	public String toString() {
		return "SecurityReferenceData{" +
				"securityIdentifier='" + securityIdentifier + '\'' +
				", ivType='" + ivType + '\'' +
				", securityName='" + securityName + '\'' +
				", finalMaturityDate=" + finalMaturityDate +
				", securityRedemptionPrice=" + securityRedemptionPrice +
				", interestRt=" + interestRt +
				", defIndicator=" + defIndicator +
				", derStepIndicator=" + derStepIndicator +
				", derHybridIndicator=" + derHybridIndicator +
				", ioHybridField='" + ioHybridField + '\'' +
				", as400RateType='" + as400RateType + '\'' +
				", prospectiveMethod='" + prospectiveMethod + '\'' +
				'}';
	}
}
