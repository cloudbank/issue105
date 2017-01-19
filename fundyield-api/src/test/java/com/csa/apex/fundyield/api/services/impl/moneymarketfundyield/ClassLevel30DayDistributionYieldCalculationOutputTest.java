/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

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
     * Test for method getY.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getY() throws Exception {
        ClassLevel30DayDistributionYieldCalculationOutput instance = new ClassLevel30DayDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(71);
        instance.setY(expected);
        assertEquals(expected, instance.getY());
    }
    /**
     * Test for method setY.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setY() throws Exception {
        ClassLevel30DayDistributionYieldCalculationOutput instance = new ClassLevel30DayDistributionYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(44);
        instance.setY(expected);
        assertEquals(expected, instance.getY());
    }
}
