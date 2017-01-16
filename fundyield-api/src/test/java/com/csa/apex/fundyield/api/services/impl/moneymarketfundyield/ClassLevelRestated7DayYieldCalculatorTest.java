/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevelRestated7DayYieldCalculator.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelRestated7DayYieldCalculatorTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevelRestated7DayYieldCalculator instance = new ClassLevelRestated7DayYieldCalculator();
        ClassLevelRestated7DayYieldCalculationOutput expected = new ClassLevelRestated7DayYieldCalculationOutput();
        ClassLevelRestated7DayYieldCalculationInput input = TestUtility.getClassLevelRestated7DayYieldCalculationInput();
        assertNotEquals(expected, instance.calculate(input));
    }
}
