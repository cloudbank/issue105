/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel1DayN1AYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel1DayN1AYieldCalculationOutputTest {
    /**
     * Test for method getY.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getY() throws Exception {
        ClassLevel1DayN1AYieldCalculationOutput instance = new ClassLevel1DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(86);
        instance.setDerMm1DayN1aYieldPct(expected);
        assertEquals(expected, instance.getDerMm1DayN1aYieldPct());
    }
    /**
     * Test for method setY.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setY() throws Exception {
        ClassLevel1DayN1AYieldCalculationOutput instance = new ClassLevel1DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(80);
        instance.setDerMm1DayN1aYieldPct(expected);
        assertEquals(expected, instance.getDerMm1DayN1aYieldPct());
    }
}
