/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevelEffectiveCompound7DayN1AYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelEffectiveCompound7DayN1AYieldCalculationInputTest {
    /**
     * Test for method getDerMnyMkt7DayN1AYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMkt7DayN1AYieldPct() throws Exception {
        ClassLevelEffectiveCompound7DayN1AYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(70);
        instance.setDerMnyMkt7DayN1AYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt7DayN1AYieldPct());
    }
    /**
     * Test for method setDerMnyMkt7DayN1AYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerMnyMkt7DayN1AYieldPct() throws Exception {
        ClassLevelEffectiveCompound7DayN1AYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(33);
        instance.setDerMnyMkt7DayN1AYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt7DayN1AYieldPct());
    }
    /**
     * Test for method getSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days() throws Exception {
        ClassLevelEffectiveCompound7DayN1AYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(38);
        instance.setSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days());
    }
    /**
     * Test for method setSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days() throws Exception {
        ClassLevelEffectiveCompound7DayN1AYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(44);
        instance.setSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevelEffectiveCompound7DayN1AYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationInput(null);
        int expected = 45;
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
        ClassLevelEffectiveCompound7DayN1AYieldCalculationInput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationInput(null);
        int expected = 70;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
