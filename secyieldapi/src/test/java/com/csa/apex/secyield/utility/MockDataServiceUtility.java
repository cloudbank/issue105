/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.utility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;

/**
 * MockDataServiceUtility Used for mocking customer API
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class MockDataServiceUtility {

	/**
	 * Private constructor
	 */
	private MockDataServiceUtility() {

	}

	/**
	 * Get mock list of data Yield and Income is not calcualted
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static List<SecuritySECData> getSecuritySECDataWithoutYieldAndIncomeData() throws ParseException {

		ArrayList<SecuritySECData> data = new ArrayList<>();
		SecuritySECData securitySECData = new SecuritySECData();
		securitySECData.setSecurityIdentifier("USTN TII 1.375% 02/15/44");
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("VRDN");
		securityReferenceData.setInterestRt(BigDecimal.valueOf(0.049592404));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setFxRate(new BigDecimal(1));
		PositionData positionData = new PositionData();
		positionData.setShareParAmount(BigDecimal.valueOf(7000000));
		positionData.setEarnedAmortizationBase(BigDecimal.valueOf(-45.69));
		securitySECData.setPositionData(new PositionData[] { positionData });
		securityReferenceData.setSecurityRedemptionPrice(BigDecimal.valueOf(100));
		securityReferenceData.setInterestRt(BigDecimal.valueOf(1.375));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("01/15/2026"));
		securitySECData.setSecurityPrice(BigDecimal.valueOf(104.7185855));
		securitySECData.setDerTIPSInflationaryRatio(BigDecimal.valueOf(0.001));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		data.add(securitySECData);

		securitySECData = new SecuritySECData();
		securitySECData.setSecurityIdentifier("USTN TII 1.375% 02/15/44");
		securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("VPS");
		securityReferenceData.setInterestRt(BigDecimal.valueOf(0.049592404));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setFxRate(BigDecimal.valueOf(1));
		positionData = new PositionData();
		positionData.setMarketValue(BigDecimal.valueOf(70135342.4));
		positionData.setAccruedIncome(BigDecimal.valueOf(257693.72));
		positionData.setEarnedInflationaryCompensationBase(BigDecimal.valueOf(-4956.56));
		securitySECData.setPositionData(new PositionData[] { positionData });
		securityReferenceData.setSecurityRedemptionPrice(BigDecimal.valueOf(100));
		securityReferenceData.setInterestRt(BigDecimal.valueOf(1.375));
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("01/15/2026"));
		securitySECData.setSecurityPrice(BigDecimal.valueOf(104.7185855));
		securitySECData.setDerTIPSInflationaryRatio(BigDecimal.valueOf(0.001));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		data.add(securitySECData);
		return data;
	}

	/**
	 * Get mock list of data Yield and Income is calcualted
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static List<SecuritySECData> getSecuritySECDataWithYieldAndIncomeData() throws ParseException {

		ArrayList<SecuritySECData> data = new ArrayList<>();
		SecuritySECData securitySECData = new SecuritySECData();
		securitySECData.setSecurityIdentifier("USTN TII 1.375% 01/15/2026");
		SecurityReferenceData securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("VRDN");
		securityReferenceData.setInterestRt(BigDecimal.valueOf(0.049592404));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setFxRate(new BigDecimal(1));
		PositionData positionData = new PositionData();
		positionData.setShareParAmount(BigDecimal.valueOf(7000000));
		positionData.setEarnedAmortizationBase(BigDecimal.valueOf(-45.69));
		positionData.setDerOneDaySecurityIncome(BigDecimal.valueOf(26690.4211111));
		securitySECData.setPositionData(new PositionData[] { positionData });
		securityReferenceData.setSecurityRedemptionPrice(BigDecimal.valueOf(100));
		securityReferenceData.setInterestRt(BigDecimal.valueOf(1.375));
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("01/15/2026"));
		securitySECData.setSecurityPrice(BigDecimal.valueOf(104.7185855));
		securitySECData.setDerTIPSInflationaryRatio(BigDecimal.valueOf(0.001));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		securitySECData.setDerOneDaySecurityYield(BigDecimal.valueOf(1.375));
		data.add(securitySECData);

		securitySECData = new SecuritySECData();
		securitySECData.setSecurityIdentifier("USTN 1.375% 01/15/2026");
		securityReferenceData = new SecurityReferenceData();
		securityReferenceData.setDerStepIndicator(false);
		securityReferenceData.setDerHybridIndicator(false);
		securityReferenceData.setIvType("VPS");
		securityReferenceData.setInterestRt(BigDecimal.valueOf(0.049592404));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setFxRate(BigDecimal.valueOf(1));
		positionData = new PositionData();
		positionData.setMarketValue(BigDecimal.valueOf(70135342.4));
		positionData.setAccruedIncome(BigDecimal.valueOf(257693.72));
		positionData.setDerOneDaySecurityIncome(BigDecimal.valueOf(-4954.1549046));
		positionData.setEarnedInflationaryCompensationBase(BigDecimal.valueOf(-4956.56));
		securitySECData.setPositionData(new PositionData[] { positionData });
		securityReferenceData.setSecurityRedemptionPrice(BigDecimal.valueOf(100));
		securityReferenceData.setInterestRt(BigDecimal.valueOf(1.375));
		formatter = new SimpleDateFormat("MM/dd/yyyy");
		securityReferenceData.setFinalMaturityDate(formatter.parse("01/15/2026"));
		securitySECData.setSecurityPrice(BigDecimal.valueOf(104.7185855));
		securitySECData.setDerTIPSInflationaryRatio(BigDecimal.valueOf(0.001));
		securitySECData.setSecurityReferenceData(securityReferenceData);
		securitySECData.setReportDate(formatter.parse("06/03/2016"));
		securitySECData.setDerOneDaySecurityYield(BigDecimal.valueOf(0.0000123));
		data.add(securitySECData);
		return data;
	}
}
