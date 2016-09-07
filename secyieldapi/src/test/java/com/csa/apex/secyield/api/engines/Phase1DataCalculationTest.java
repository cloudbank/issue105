/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.engines;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.csa.apex.secyield.api.engines.impl.CalculationEngineSelector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.CalculationException;
import com.csa.apex.secyield.utility.CommonUtility;

/**
 * Tests all the phase 1 data for calculations
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class Phase1DataCalculationTest {
    /**
     * CalculationEngineSelector object
     */
    @Autowired
    @Qualifier("calculationEngineSelector")
    private CalculationEngineSelector calculationEngineSelector;

    /**
     * Check all phase1testdata
     * 
     * @throws ParseException
     * @throws IOException
     * @throws CalculationException
     */
    @Test
    public void testAllCSVData() throws ParseException, IOException, CalculationException {
        List<SecuritySECData> data = CommonUtility.parsePhase1TestData();
        int count = 0;
        for (SecuritySECData securitySECData : data) {
            SecuritySECData cloneSecuritySECData = new SecuritySECData();
            SecurityReferenceData clonedSecurityReferenceData = new SecurityReferenceData();
            PositionData clonedPositionData = new PositionData();
            cloneSecuritySECData.setSecurityReferenceData(clonedSecurityReferenceData);
            cloneSecuritySECData.setPositionData(new PositionData[] { clonedPositionData });
            clonedSecurityReferenceData.setIvType(securitySECData.getSecurityReferenceData().getIvType());
            clonedSecurityReferenceData
                    .setSecurityIdentifier(securitySECData.getSecurityReferenceData().getSecurityIdentifier());
            clonedSecurityReferenceData.setSecurityRedemptionPrice(
                    securitySECData.getSecurityReferenceData().getSecurityRedemptionPrice());
            clonedSecurityReferenceData
                    .setDerStepIndicator(securitySECData.getSecurityReferenceData().isDerStepIndicator());
            clonedSecurityReferenceData
                    .setDerHybridIndicator(securitySECData.getSecurityReferenceData().isDerHybridIndicator());
            clonedSecurityReferenceData
                    .setDerHybridIndicator(securitySECData.getSecurityReferenceData().isDerHybridIndicator());
            clonedSecurityReferenceData.setInterestRt(
                    securitySECData.getSecurityReferenceData().getInterestRt().divide(BigDecimal.valueOf(100.00)));
            clonedSecurityReferenceData
                    .setFinalMaturityDate(securitySECData.getSecurityReferenceData().getFinalMaturityDate());
            cloneSecuritySECData.setDerYieldCalcEngine(securitySECData.getDerYieldCalcEngine());
            cloneSecuritySECData.setDerYieldCalcEngine(securitySECData.getDerYieldCalcEngine());
            cloneSecuritySECData.setSecurityPrice(securitySECData.getSecurityPrice());
            cloneSecuritySECData.setFxRate(securitySECData.getFxRate());
            cloneSecuritySECData.setDerTIPSInflationaryRatio(securitySECData.getDerTIPSInflationaryRatio());
            cloneSecuritySECData.setReportDate(securitySECData.getReportDate());
            clonedPositionData.setMarketValue(securitySECData.getPositionData()[0].getMarketValue());
            clonedPositionData.setAccruedIncome(securitySECData.getPositionData()[0].getAccruedIncome());
            clonedPositionData.setEarnedInflationaryCompensationBase(
                    securitySECData.getPositionData()[0].getEarnedInflationaryCompensationBase());
            clonedPositionData
                    .setEarnedAmortizationBase(securitySECData.getPositionData()[0].getEarnedAmortizationBase());
            clonedPositionData.setSharePerAmount(securitySECData.getPositionData()[0].getSharePerAmount());
            clonedPositionData.setPositionValInflationAdjShares(securitySECData.getPositionData()[0].getPositionValInflationAdjShares());
            SECConfiguration configuration = new SECConfiguration();
            calculationEngineSelector.calculate(cloneSecuritySECData, configuration);
            if ("VPS".equalsIgnoreCase(cloneSecuritySECData.getSecurityReferenceData().getIvType())) {
                calculationEngineSelector.getCalculationEngines().get("YTM").calculate(cloneSecuritySECData,
                        configuration);
            } else if ("VRDN".equalsIgnoreCase(cloneSecuritySECData.getSecurityReferenceData().getIvType())
                    || "DVRN".equalsIgnoreCase(cloneSecuritySECData.getSecurityReferenceData().getIvType())) {
                calculationEngineSelector.getCalculationEngines().get("COUPON").calculate(cloneSecuritySECData,
                        configuration);
            }

            BigDecimal correctYield = securitySECData.getDerOneDaySecurityYield().setScale(4, BigDecimal.ROUND_HALF_UP);
            BigDecimal calculatedYield = cloneSecuritySECData.getDerOneDaySecurityYield().setScale(4,
                    BigDecimal.ROUND_HALF_UP);
            BigDecimal correctIncome = securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(2,
                    BigDecimal.ROUND_HALF_UP);
            BigDecimal calculatedIncome = cloneSecuritySECData.getPositionData()[0].getDerOneDaySecurityIncome()
                    .setScale(2, BigDecimal.ROUND_HALF_UP);
            assert (Math.abs(correctYield.subtract(calculatedYield).doubleValue()) < 0.0002);
            assert (Math.abs(correctIncome.subtract(calculatedIncome).doubleValue()) < 0.4);
            count = count + 1;

        }

    }

}
