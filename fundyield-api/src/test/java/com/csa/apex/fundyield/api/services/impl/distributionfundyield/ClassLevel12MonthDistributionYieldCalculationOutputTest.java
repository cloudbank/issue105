/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel12MonthDistributionYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel12MonthDistributionYieldCalculationOutputTest {
    /**
     * Test for method getDerDist12MoYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerDist12MoYieldPct() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationOutput instance = new ClassLevel12MonthDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(44);
        instance.setDerDist12MoYieldPct(expected);
        assertEquals(expected, instance.getDerDist12MoYieldPct());
    }
    /**
     * Test for method setDerDist12MoYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerDist12MoYieldPct() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationOutput instance = new ClassLevel12MonthDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(36);
        instance.setDerDist12MoYieldPct(expected);
        assertEquals(expected, instance.getDerDist12MoYieldPct());
    }
}
