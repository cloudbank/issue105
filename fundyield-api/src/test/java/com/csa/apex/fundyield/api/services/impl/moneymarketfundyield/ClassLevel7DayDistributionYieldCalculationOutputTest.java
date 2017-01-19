/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel7DayDistributionYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel7DayDistributionYieldCalculationOutputTest {
    /**
     * Test for method getDerMnyMktRst7DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMktRst7DayYieldPct() throws Exception {
        ClassLevel7DayDistributionYieldCalculationOutput instance = new ClassLevel7DayDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(82);
        instance.setDerMnyMktRst7DayYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMktRst7DayYieldPct());
    }
    /**
     * Test for method setDerMnyMktRst7DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerMnyMktRst7DayYieldPct() throws Exception {
        ClassLevel7DayDistributionYieldCalculationOutput instance = new ClassLevel7DayDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(41);
        instance.setDerMnyMktRst7DayYieldPct(expected);
        assertEquals(expected, instance.getDerMnyMktRst7DayYieldPct());
    }
}
