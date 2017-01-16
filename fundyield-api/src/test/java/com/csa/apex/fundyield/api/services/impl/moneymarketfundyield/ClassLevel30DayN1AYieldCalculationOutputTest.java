/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel30DayN1AYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel30DayN1AYieldCalculationOutputTest {
    /**
     * Test for method getDerMnyMkt1DayN1AYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMkt1DayN1AYieldPct() throws Exception {
        ClassLevel30DayN1AYieldCalculationOutput instance = new ClassLevel30DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(67);
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
        ClassLevel30DayN1AYieldCalculationOutput instance = new ClassLevel30DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(82);
        instance.setDerMnyMkt1DayN1AYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMkt1DayN1AYieldPct());
    }
}
