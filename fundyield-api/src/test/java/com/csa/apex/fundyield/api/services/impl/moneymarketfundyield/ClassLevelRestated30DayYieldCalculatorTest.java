/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;

import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevelRestated30DayYieldCalculator.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelRestated30DayYieldCalculatorTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevelRestated30DayYieldCalculator instance = new ClassLevelRestated30DayYieldCalculator();
        ClassLevelRestated30DayYieldCalculationOutput expected = new ClassLevelRestated30DayYieldCalculationOutput();
        ClassLevelRestated30DayYieldCalculationInput input = TestUtility.getClassLevelRestated30DayYieldCalculationInput();
        assertNotEquals(expected, instance.calculate(input));
    }
}
