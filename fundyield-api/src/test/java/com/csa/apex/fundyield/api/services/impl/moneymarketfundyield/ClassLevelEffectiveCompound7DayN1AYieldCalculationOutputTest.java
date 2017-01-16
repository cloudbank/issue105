/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelEffectiveCompound7DayN1AYieldCalculationOutputTest {
    /**
     * Test for method getDerMnyMktN1ACompound7DayYield.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMktN1ACompound7DayYield() throws Exception {
        ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(16);
        instance.setDerMnyMktN1ACompound7DayYield(expected);
        assertEquals(expected, instance.getDerMnyMktN1ACompound7DayYield());
    }
    /**
     * Test for method setDerMnyMktN1ACompound7DayYield.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerMnyMktN1ACompound7DayYield() throws Exception {
        ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput instance = new ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(10);
        instance.setDerMnyMktN1ACompound7DayYield(expected);
        assertEquals(expected, instance.getDerMnyMktN1ACompound7DayYield());
    }
}
