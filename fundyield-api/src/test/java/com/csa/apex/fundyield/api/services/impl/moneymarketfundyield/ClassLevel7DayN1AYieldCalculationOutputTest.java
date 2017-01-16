/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel7DayN1AYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel7DayN1AYieldCalculationOutputTest {
    /**
     * Test for method getDerMnyMkt7DayN1AYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMkt7DayN1AYieldPct() throws Exception {
        ClassLevel7DayN1AYieldCalculationOutput instance = new ClassLevel7DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(60);
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
        ClassLevel7DayN1AYieldCalculationOutput instance = new ClassLevel7DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(40);
        instance.setDerMnyMkt7DayN1AYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt7DayN1AYieldPct());
    }
}
