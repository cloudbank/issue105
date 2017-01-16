/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevel7DayN1AYieldCalculator.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel7DayN1AYieldCalculatorTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel7DayN1AYieldCalculator instance = new ClassLevel7DayN1AYieldCalculator();
        ClassLevel7DayN1AYieldCalculationOutput expected = new ClassLevel7DayN1AYieldCalculationOutput();
        ClassLevel7DayN1AYieldCalculationInput input = TestUtility.getClassLevel7DayN1AYieldCalculationInput();
        assertNotEquals(expected, instance.calculate(input));
    }
}
