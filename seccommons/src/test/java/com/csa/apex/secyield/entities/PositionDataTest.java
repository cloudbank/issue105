/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.csa.apex.secyield.utility.Constants;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test class for the PositionData.
 *
 * @see PositionDataTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class PositionDataTest {

    /**
     * PositionData Test toString
     *
     * @throws ParseException
     */
    @Test
    public void toStringTest() throws ParseException {
        PositionData positionData = new PositionData();
        positionData.setSecurityIdentifier("SecId");
        positionData.setPortfolioNumber(new BigDecimal(1111));
        positionData.setPortfolioName("PORT1");

        SimpleDateFormat formatter = new SimpleDateFormat(Constants.API_DATE_FORMAT);
        Date reportDate = formatter.parse("2016-01-15");
        positionData.setReportDate(reportDate);

        positionData.setEarnedInflationaryCompensationBase(new BigDecimal(1111));
        positionData.setAccruedIncome(new BigDecimal(1111));
        positionData.setMarketValue(new BigDecimal(1111));
        positionData.setShareParAmount(new BigDecimal(1111));
        positionData.setEarnedAmortizationBase(new BigDecimal(1111));
        positionData.setPositionValInflationAdjShares(new BigDecimal(1111));
        positionData.setDerOneDaySecurityIncome(new BigDecimal(1111));

        String expected = "PositionData{securityIdentifier='SecId', portfolioNumber=1111, portfolioName='PORT1', reportDate=2016-01-15, earnedInflationaryCompensationBase=1111, accruedIncome=1111, marketValue=1111, shareParAmount=1111, earnedAmortizationBase=1111, positionValInflationAdjShares=1111, derOneDaySecurityIncome=1111}";

        assertEquals(expected, positionData.toString());
    }

}
