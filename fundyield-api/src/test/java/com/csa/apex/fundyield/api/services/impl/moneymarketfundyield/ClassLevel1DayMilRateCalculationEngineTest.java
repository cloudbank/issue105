/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevel1DayMilRateCalculationEngine.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel1DayMilRateCalculationEngineTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel1DayMilRateCalculationEngine instance = new ClassLevel1DayMilRateCalculationEngine();
        FundAccountingYieldData fundAccountingYieldData = TestUtility.getFundAccountingYieldData();
        SECConfiguration configuration = new SECConfiguration();
        configuration.setOperationScale(2);
        configuration.setRoundingMode(2);
        instance.calculate(fundAccountingYieldData, configuration);
    }
}
