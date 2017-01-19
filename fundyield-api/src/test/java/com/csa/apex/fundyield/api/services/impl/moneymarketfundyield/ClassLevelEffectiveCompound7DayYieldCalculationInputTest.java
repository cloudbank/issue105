/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevelEffectiveCompound7DayYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelEffectiveCompound7DayYieldCalculationInputTest {
    /**
     * Test for method getDerMnyMkt7DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMkt7DayYieldPct() throws Exception {
        ClassLevelEffectiveCompound7DayYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayYieldCalculationInput();
        BigDecimal expected = new BigDecimal(98);
        instance.setDerMnyMkt7DayYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt7DayYieldPct());
    }
    /**
     * Test for method setDerMnyMkt7DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerMnyMkt7DayYieldPct() throws Exception {
        ClassLevelEffectiveCompound7DayYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayYieldCalculationInput();
        BigDecimal expected = new BigDecimal(62);
        instance.setDerMnyMkt7DayYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt7DayYieldPct());
    }
    /**
     * Test for method getSumOfDerMnyMkt7DayYieldPctForPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDerMnyMkt7DayYieldPctForPrevious6Days() throws Exception {
        ClassLevelEffectiveCompound7DayYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayYieldCalculationInput();
        BigDecimal expected = new BigDecimal(50);
        instance.setSumOfDerMnyMkt7DayYieldPctForPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfDerMnyMkt7DayYieldPctForPrevious6Days());
    }
    /**
     * Test for method setSumOfDerMnyMkt7DayYieldPctForPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setSumOfDerMnyMkt7DayYieldPctForPrevious6Days() throws Exception {
        ClassLevelEffectiveCompound7DayYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayYieldCalculationInput();
        BigDecimal expected = new BigDecimal(16);
        instance.setSumOfDerMnyMkt7DayYieldPctForPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfDerMnyMkt7DayYieldPctForPrevious6Days());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevelEffectiveCompound7DayYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayYieldCalculationInput();
        int expected = 99;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
    /**
     * Test for method setOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setOperationScale() throws Exception {
        ClassLevelEffectiveCompound7DayYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayYieldCalculationInput();
        int expected = 73;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
