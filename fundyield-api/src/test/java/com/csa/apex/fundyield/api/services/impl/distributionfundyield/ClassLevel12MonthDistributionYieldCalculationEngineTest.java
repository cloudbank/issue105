/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevel12MonthDistributionYieldCalculationEngine.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel12MonthDistributionYieldCalculationEngineTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationEngine instance = new ClassLevel12MonthDistributionYieldCalculationEngine();
        FundAccountingYieldData fundAccountingYieldData = TestUtility.getFundAccountingYieldData();
        SECConfiguration configuration = new SECConfiguration();
        configuration.setOperationScale(2);
        configuration.setRoundingMode(2);
        instance.calculate(fundAccountingYieldData, configuration);
    }
    /**
     * Test for method calculate with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void calculateInvalid() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationEngine instance = new ClassLevel12MonthDistributionYieldCalculationEngine();
        instance.calculate(null, null);
    }
}
