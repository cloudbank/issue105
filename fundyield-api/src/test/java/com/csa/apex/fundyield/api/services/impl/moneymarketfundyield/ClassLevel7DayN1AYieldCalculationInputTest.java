/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel7DayN1AYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel7DayN1AYieldCalculationInputTest {
    /**
     * Test for method getDerMnyMkt1DayN1AYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMkt1DayN1AYieldPct() throws Exception {
        ClassLevel7DayN1AYieldCalculationInput instance = new ClassLevel7DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(48);
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
        ClassLevel7DayN1AYieldCalculationInput instance = new ClassLevel7DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(83);
        instance.setDerMnyMkt1DayN1AYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt1DayN1AYieldPct());
    }
    /**
     * Test for method getSumOfDer1DayYieldN1AMnyMktPctPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDer1DayYieldN1AMnyMktPctPrevious6Days() throws Exception {
        ClassLevel7DayN1AYieldCalculationInput instance = new ClassLevel7DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(77);
        instance.setSumOfDer1DayYieldN1AMnyMktPctPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfDer1DayYieldN1AMnyMktPctPrevious6Days());
    }
    /**
     * Test for method setSumOfDer1DayYieldN1AMnyMktPctPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setSumOfDer1DayYieldN1AMnyMktPctPrevious6Days() throws Exception {
        ClassLevel7DayN1AYieldCalculationInput instance = new ClassLevel7DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(33);
        instance.setSumOfDer1DayYieldN1AMnyMktPctPrevious6Days(expected);
        assertEquals(expected, instance.getSumOfDer1DayYieldN1AMnyMktPctPrevious6Days());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevel7DayN1AYieldCalculationInput instance = new ClassLevel7DayN1AYieldCalculationInput(new SECConfiguration());
        int expected = 0;
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
        ClassLevel7DayN1AYieldCalculationInput instance = new ClassLevel7DayN1AYieldCalculationInput(new SECConfiguration());
        int expected = 27;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
