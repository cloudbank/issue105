package com.csa.apex.secyield.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * The security reference data
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
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
	private Boolean defIndicator;
	
	/**
	 * The step indicator
	 */
	private Boolean derStepIndicator;
	
	/**
	 * The hybrid indicator
	 */
	private Boolean derHybridIndicator;
	
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
	public SecurityReferenceData()
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
	 * Getter ivType
	 * @return ivType
	 */
	public String getIVType() {
		return ivType;
	}

	/**
	 * Setter ivType
	 * @param ivType
	 */
	public void setIVType(String ivType) {
		this.ivType = ivType;
	}

	/**
	 * Getter securityName
	 * @return securityName
	 */
	public String getSecurityName() {
		return securityName;
	}
	
	/**
	 * Setter securityName
	 * @param securityName
	 */
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}
	
	/**
	 * Getter finalMaturityDate
	 * @return finalMaturityDate
	 */
	public Date getFinalMaturityDate() {
		return finalMaturityDate;
	}
	
	/**
	 * Setter finalMaturityDate
	 * @param finalMaturityDate
	 */
	public void setFinalMaturityDate(Date finalMaturityDate) {
		this.finalMaturityDate = finalMaturityDate;
	}
	
	/**
	 * Getter securityRedemptionPrice
	 * @return securityRedemptionPrice
	 */
	public BigDecimal getSecurityRedemptionPrice() {
		return securityRedemptionPrice;
	}
	
	/**
	 * Setter securityRedemptionPrice
	 * @param securityRedemptionPrice
	 */
	public void setSecurityRedemptionPrice(BigDecimal securityRedemptionPrice) {
		this.securityRedemptionPrice = securityRedemptionPrice;
	}
	
	/**
	 * Getter interestRt
	 * @return interestRt
	 */
	public BigDecimal getInterestRt() {
		return interestRt;
	}
	
	/**
	 * Setter interestRt
	 * @param interestRt
	 */
	public void setInterestRt(BigDecimal interestRt) {
		this.interestRt = interestRt;
	}
	
	/**
	 * Getter defIndicator
	 * @return defIndicator
	 */
	public Boolean getDefIndicator() {
		return defIndicator;
	}
	
	/**
	 * Setter defIndicator
	 * @param defIndicator
	 */
	public void setDefIndicator(Boolean defIndicator) {
		this.defIndicator = defIndicator;
	}
	
	/**
	 * Getter derStepIndicator
	 * @return derStepIndicator
	 */
	public Boolean getDerStepIndicator() {
		return derStepIndicator;
	}
	
	/**
	 * Setter derStepIndicator
	 * @param derStepIndicator
	 */
	public void setDerStepIndicator(Boolean derStepIndicator) {
		this.derStepIndicator = derStepIndicator;
	}
	
	/**
	 * Getter derHybridIndicator
	 * @return derHybridIndicator
	 */
	public Boolean getDerHybridIndicator() {
		return derHybridIndicator;
	}
	
	/**
	 * Setter derHybridIndicator
	 * @param derHybridIndicator
	 */
	public void setDerHybridIndicator(Boolean derHybridIndicator) {
		this.derHybridIndicator = derHybridIndicator;
	}
	
	/**
	 * Getter derHybridIndicator
	 * @return derHybridIndicator
	 */
	public String getIOHybridField() {
		return ioHybridField;
	}
	
	/**
	 * Setter derHybridIndicator
	 * @param ioHybridField
	 */
	public void setIOHybridField(String ioHybridField) {
		this.ioHybridField = ioHybridField;
	}
	
	/**
	 * Getter as400RateType
	 * @return as400RateType
	 */
	public String getAs400RateType() {
		return as400RateType;
	}
	
	/**
	 * Setter as400RateType
	 * @param as400RateType
	 */
	public void setAs400RateType(String as400RateType) {
		this.as400RateType = as400RateType;
	}
	
	/**
	 * Getter prospectiveMethod
	 * @return prospectiveMethod
	 */
	public String getProspectiveMethod() {
		return prospectiveMethod;
	}
	
	/**
	 * Setter prospectiveMethod
	 * @param prospectiveMethod
	 */
	public void setProspectiveMethod(String prospectiveMethod) {
		this.prospectiveMethod = prospectiveMethod;
	}
	
	
}
