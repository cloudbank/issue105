/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

/**
* Test class for the ClassLevel1DayDistributionYieldCalculator.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel1DayDistributionYieldCalculatorTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel1DayDistributionYieldCalculator instance = new ClassLevel1DayDistributionYieldCalculator();
        instance.calculate();
    }
}
