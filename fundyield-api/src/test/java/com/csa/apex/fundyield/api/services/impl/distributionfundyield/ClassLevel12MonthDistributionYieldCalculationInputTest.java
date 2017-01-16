/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel12MonthDistributionYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel12MonthDistributionYieldCalculationInputTest {
    /**
     * Test for method getDist12MoMilRt.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDist12MoMilRt() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationInput instance = new ClassLevel12MonthDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(9);
        instance.setDist12MoMilRt(expected);
        assertEquals(expected, instance.getDist12MoMilRt());
    }
    /**
     * Test for method setDist12MoMilRt.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDist12MoMilRt() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationInput instance = new ClassLevel12MonthDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(77);
        instance.setDist12MoMilRt(expected);
        assertEquals(expected, instance.getDist12MoMilRt());
    }
    /**
     * Test for method getNavAmt.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getNavAmt() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationInput instance = new ClassLevel12MonthDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(95);
        instance.setNavAmt(expected);
        assertEquals(expected, instance.getNavAmt());
    }
    /**
     * Test for method setNavAmt.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setNavAmt() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationInput instance = new ClassLevel12MonthDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(54);
        instance.setNavAmt(expected);
        assertEquals(expected, instance.getNavAmt());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevel12MonthDistributionYieldCalculationInput instance = new ClassLevel12MonthDistributionYieldCalculationInput();
        int expected = 42;
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
        ClassLevel12MonthDistributionYieldCalculationInput instance = new ClassLevel12MonthDistributionYieldCalculationInput();
        int expected = 30;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
