/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.utility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;

/**
 * Utility class for tests.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Component
public class TestUtility {

	/**
	 * Get a mocked security SEC data
	 * 
	 * @return the security SEC data
	 * @throws ParseException
	 *             if any parsing issue occurs
	 */
	public static SecuritySECData getTestSecuritySECData() throws ParseException {

		SecuritySECData securitySECData = new SecuritySECData();
		String testSecurityIdentifier = "security_identifier_test";
		securitySECData.setSecurityIdentifier(testSecurityIdentifier);
		securitySECData.setDerTIPSInflationaryRatio(BigDecimal.TEN);
		securitySECData.setFxRate(BigDecimal.TEN);
		Date reportDate = new Date();
		securitySECData.setReportDate(reportDate);
		securitySECData.setSecurityPrice(BigDecimal.TEN);

		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setSecurityIdentifier(testSecurityIdentifier);
		securityReferenceData.setAs400RateType("as_400_rate_type_test");
		securityReferenceData.setDefIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setFinalMaturityDate(reportDate);
		securityReferenceData.setInterestRt(BigDecimal.TEN);
		securityReferenceData.setIoHybridField("io_hybrid_field_test");
		securityReferenceData.setIvType("iv_type_test");
		securityReferenceData.setSecurityName("security_name_test");
		securityReferenceData.setSecurityRedemptionPrice(BigDecimal.TEN);
		securitySECData.setSecurityReferenceData(securityReferenceData);

		PositionData[] positionDataArray = new PositionData[3];
		for (int i = 0; i < positionDataArray.length; i++) {
			PositionData positionData = new PositionData();
			positionData.setSecurityIdentifier(testSecurityIdentifier);
			positionData.setAccruedIncome(BigDecimal.TEN);
			positionData.setEarnedAmortizationBase(BigDecimal.TEN);
			positionData.setEarnedInflationaryCompensationBase(BigDecimal.TEN);
			positionData.setMarketValue(BigDecimal.TEN);
			positionData.setPortfolioNumber(BigDecimal.TEN.add(BigDecimal.valueOf(i)));
			positionData.setPositionValInflationAdjShare(BigDecimal.TEN);
			positionData.setReportDate(reportDate);
			positionData.setShareParAmount(BigDecimal.TEN);
			positionData.setDerOneDaySecurityIncome(BigDecimal.TEN);
			positionDataArray[i] = positionData;
		}

		securitySECData.setPositionData(positionDataArray);
		return securitySECData;
	}
}
