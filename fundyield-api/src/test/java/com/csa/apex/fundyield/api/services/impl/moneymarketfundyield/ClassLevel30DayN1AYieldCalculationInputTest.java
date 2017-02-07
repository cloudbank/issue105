/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel30DayN1AYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel30DayN1AYieldCalculationInputTest {
    /**
     * Test for method getDerMnyMkt1DayN1AYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMkt1DayN1AYieldPct() throws Exception {
        ClassLevel30DayN1AYieldCalculationInput instance = new ClassLevel30DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(58);
        instance.setDerMnyMkt1DayN1AYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt1DayN1AYieldPct());
    }
    /**
     * Test for method setDerMnyMkt1DayN1AYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerMnyMkt1DayN1AYieldPct() throws Exception {
        ClassLevel30DayN1AYieldCalculationInput instance = new ClassLevel30DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(93);
        instance.setDerMnyMkt1DayN1AYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt1DayN1AYieldPct());
    }
    /**
     * Test for method getSumOfDer1DayYieldN1AMnyMktPctPrevious29Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDer1DayYieldN1AMnyMktPctPrevious29Days() throws Exception {
        ClassLevel30DayN1AYieldCalculationInput instance = new ClassLevel30DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(66);
        instance.setSumOfDer1DayYieldN1AMnyMktPctPrevious29Days(expected);
        assertEquals(expected, instance.getSumOfDer1DayYieldN1AMnyMktPctPrevious29Days());
    }
    /**
     * Test for method setSumOfDer1DayYieldN1AMnyMktPctPrevious29Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setSumOfDer1DayYieldN1AMnyMktPctPrevious29Days() throws Exception {
        ClassLevel30DayN1AYieldCalculationInput instance = new ClassLevel30DayN1AYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(0);
        instance.setSumOfDer1DayYieldN1AMnyMktPctPrevious29Days(expected);
        assertEquals(expected, instance.getSumOfDer1DayYieldN1AMnyMktPctPrevious29Days());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevel30DayN1AYieldCalculationInput instance = new ClassLevel30DayN1AYieldCalculationInput(null);
        int expected = 69;
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
        ClassLevel30DayN1AYieldCalculationInput instance = new ClassLevel30DayN1AYieldCalculationInput(null);
        int expected = 99;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
