/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevel1DayN1AYieldCalculator.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel1DayN1AYieldCalculatorTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel1DayN1AYieldCalculator instance = new ClassLevel1DayN1AYieldCalculator();
        ClassLevel1DayN1AYieldCalculationOutput expected = new ClassLevel1DayN1AYieldCalculationOutput();
        ClassLevel1DayN1AYieldCalculationInput input = TestUtility.getClassLevel1DayN1AYieldCalculationInput();
        assertNotEquals(expected, instance.calculate(input));
    }
}
