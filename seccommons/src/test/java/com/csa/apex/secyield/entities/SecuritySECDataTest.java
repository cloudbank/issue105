/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.entities;

import org.junit.Test;

import com.csa.apex.secyield.utility.Constants;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the SecuritySECData.
 *
 * @see SecuritySECDataTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class SecuritySECDataTest {

    /**
     * SecuritySECData Test toString.
     *
     * @throws ParseException
     */
    @Test
    public void toStringTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.API_DATE_FORMAT);
        Date dt = formatter.parse("2016-01-15");

        PositionData positionData = new PositionData();
        positionData.setSecurityIdentifier("SecId");
        positionData.setPortfolioNumber(new BigDecimal(1111));
        positionData.setPortfolioName("PORT1");
        positionData.setReportDate(dt);
        positionData.setEarnedInflationaryCompensationBase(new BigDecimal(1111));
        positionData.setAccruedIncome(new BigDecimal(1111));
        positionData.setMarketValue(new BigDecimal(1111));
        positionData.setShareParAmount(new BigDecimal(1111));
        positionData.setEarnedAmortizationBase(new BigDecimal(1111));
        positionData.setPositionValInflationAdjShares(new BigDecimal(1111));
        positionData.setDerOneDaySecurityIncome(new BigDecimal(1111));

        SecuritySECData securitySECData = new SecuritySECData();
        securitySECData.setSecurityIdentifier("1111");
        securitySECData.setReportDate(dt);
        securitySECData.setDerCleanPrice(new BigDecimal(1111));
        securitySECData.setPositionData(new PositionData[]{positionData});
        securitySECData.setDerYieldCalcEngine("1111");
        securitySECData.setDerIncomeCalcEngine("1111");
        securitySECData.setDerOneDaySecurityYield(new BigDecimal(1111));
        securitySECData.setDerRedemptionDate(dt);
        securitySECData.setDerRedemptionPrice(new BigDecimal(1111));
        securitySECData.setDerSecurityType("SEC1111");
        securitySECData.setDerTIPSInflationaryRatio(new BigDecimal(1111));
        securitySECData.setSecurityPrice(new BigDecimal(1111.11));
        securitySECData.setFxRate(new BigDecimal(2222.50));

        String expected = "SecuritySECData{securityIdentifier='1111', reportDate=2016-01-15, securityReferenceData=null, derCleanPrice=1111, positionData=[PositionData{securityIdentifier='SecId', portfolioNumber=1111, portfolioName='PORT1', reportDate=2016-01-15, earnedInflationaryCompensationBase=1111, accruedIncome=1111, marketValue=1111, shareParAmount=1111, earnedAmortizationBase=1111, positionValInflationAdjShares=1111, derOneDaySecurityIncome=1111}], derYieldCalcEngine='1111', derIncomeCalcEngine='1111', derOneDaySecurityYield=1111, derRedemptionDate=2016-01-15, derRedemptionPrice=1111, derSecurityType='SEC1111', derTIPSInflationaryRatio=1111, securityPrice=1111.109999999999899955582804977893829345703125, fxRate=2222.5}";

        assertEquals(expected, securitySECData.toString());
    }

}
