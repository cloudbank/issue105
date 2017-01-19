/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevel30DayN1AYieldCalculator.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel30DayN1AYieldCalculatorTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel30DayN1AYieldCalculator instance = new ClassLevel30DayN1AYieldCalculator();
        ClassLevel30DayN1AYieldCalculationOutput expected = new ClassLevel30DayN1AYieldCalculationOutput();
        ClassLevel30DayN1AYieldCalculationInput input = TestUtility.getClassLevel30DayN1AYieldCalculationInput();
        assertNotEquals(expected, instance.calculate(input));
    }

}
