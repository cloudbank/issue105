/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel30DayDistributionYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel30DayDistributionYieldCalculationOutputTest {
    /**
     * Test for method getDerDist30DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerDist30DayYieldPct() throws Exception {
        ClassLevel30DayDistributionYieldCalculationOutput instance = new ClassLevel30DayDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(87);
        instance.setDerDist30DayYieldPct(expected);
        assertEquals(expected, instance.getDerDist30DayYieldPct());
    }
    /**
     * Test for method setDerDist30DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerDist30DayYieldPct() throws Exception {
        ClassLevel30DayDistributionYieldCalculationOutput instance = new ClassLevel30DayDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(14);
        instance.setDerDist30DayYieldPct(expected);
        assertEquals(expected, instance.getDerDist30DayYieldPct());
    }
}
