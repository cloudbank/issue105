package com.csa.apex.secyield.entities;

import static org.junit.Assert.*;

import org.junit.Test;

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

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date reportDate = formatter.parse("01/15/2016");
        positionData.setReportDate(reportDate);

        positionData.setEarnedInflationaryCompensationBase(new BigDecimal(1111));
        positionData.setAccruedIncome(new BigDecimal(1111));
        positionData.setMarketValue(new BigDecimal(1111));
        positionData.setSharePerAmount(new BigDecimal(1111));
        positionData.setEarnedAmortizationBase(new BigDecimal(1111));
        positionData.setPositionValInflationAdjShare(new BigDecimal(1111));
        positionData.setDerOneDaySecurityIncome(new BigDecimal(1111));

        String expected = "PositionData{securityIdentifier='SecId', portfolioNumber=1111, portfolioName='PORT1', reportDate=01/15/2016, earnedInflationaryCompensationBase=1111, accruedIncome=1111, marketValue=1111, sharePerAmount=1111, earnedAmortizationBase=1111, positionValInflationAdjShare=1111, derOneDaySecurityIncome=1111}";

        assertEquals(expected, positionData.toString());
    }

}
