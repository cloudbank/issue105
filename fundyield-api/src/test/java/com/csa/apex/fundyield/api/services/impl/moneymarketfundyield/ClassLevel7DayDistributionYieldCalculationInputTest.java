/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel7DayDistributionYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel7DayDistributionYieldCalculationInputTest {
    /**
     * Test for method getMnyMkt1DayDistYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getMnyMkt1DayDistYieldPct() throws Exception {
        ClassLevel7DayDistributionYieldCalculationInput instance = new ClassLevel7DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(12);
        instance.setMnyMkt1DayDistYieldPct(expected);
        assertEquals(expected, instance.getMnyMkt1DayDistYieldPct());
    }
    /**
     * Test for method setMnyMkt1DayDistYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setMnyMkt1DayDistYieldPct() throws Exception {
        ClassLevel7DayDistributionYieldCalculationInput instance = new ClassLevel7DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(99);
        instance.setMnyMkt1DayDistYieldPct(expected);
        assertEquals(expected, instance.getMnyMkt1DayDistYieldPct());
    }
    /**
     * Test for method getSumOfMnyMkt1DayDistYieldPctForPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfMnyMkt1DayDistYieldPctForPrevious6Days() throws Exception {
        ClassLevel7DayDistributionYieldCalculationInput instance = new ClassLevel7DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(86);
        instance.setSumOfMnyMkt1DayDistYieldPctForPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfMnyMkt1DayDistYieldPctForPrevious6Days());
    }
    /**
     * Test for method setSumOfMnyMkt1DayDistYieldPctForPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setSumOfMnyMkt1DayDistYieldPctForPrevious6Days() throws Exception {
        ClassLevel7DayDistributionYieldCalculationInput instance = new ClassLevel7DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(45);
        instance.setSumOfMnyMkt1DayDistYieldPctForPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfMnyMkt1DayDistYieldPctForPrevious6Days());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevel7DayDistributionYieldCalculationInput instance = new ClassLevel7DayDistributionYieldCalculationInput(null);
        int expected = 3;
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
        ClassLevel7DayDistributionYieldCalculationInput instance = new ClassLevel7DayDistributionYieldCalculationInput(null);
        int expected = 80;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
