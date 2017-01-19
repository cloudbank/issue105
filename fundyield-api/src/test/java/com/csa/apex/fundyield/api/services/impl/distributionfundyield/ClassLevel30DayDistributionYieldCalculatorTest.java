/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevel30DayDistributionYieldCalculator.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel30DayDistributionYieldCalculatorTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel30DayDistributionYieldCalculator instance = new ClassLevel30DayDistributionYieldCalculator();
        ClassLevel30DayDistributionYieldCalculationOutput expected = new ClassLevel30DayDistributionYieldCalculationOutput();
        ClassLevel30DayDistributionYieldCalculationInput input = TestUtility.getClassLevel30DayDistributionYieldCalculationInput2();
        assertNotEquals(expected, instance.calculate(input));
    }
}
