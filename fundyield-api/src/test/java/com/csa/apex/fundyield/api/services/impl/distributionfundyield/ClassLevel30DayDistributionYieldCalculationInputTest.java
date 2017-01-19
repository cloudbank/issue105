/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel30DayDistributionYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel30DayDistributionYieldCalculationInputTest {
    /**
     * Test for method getDistUnmod30DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDistUnmod30DayYieldPct() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(22);
        instance.setDistUnmod30DayYieldPct(expected);
        assertEquals(expected, instance.getDistUnmod30DayYieldPct());
    }
    /**
     * Test for method setDistUnmod30DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDistUnmod30DayYieldPct() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(84);
        instance.setDistUnmod30DayYieldPct(expected);
        assertEquals(expected, instance.getDistUnmod30DayYieldPct());
    }
    /**
     * Test for method getDistYieldMilRt.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDistYieldMilRt() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(72);
        instance.setDistYieldMilRt(expected);
        assertEquals(expected, instance.getDistYieldMilRt());
    }
    /**
     * Test for method setDistYieldMilRt.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDistYieldMilRt() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(27);
        instance.setDistYieldMilRt(expected);
        assertEquals(expected, instance.getDistYieldMilRt());
    }
    /**
     * Test for method getNavAmt.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getNavAmt() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(91);
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
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(20);
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
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 87;
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
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 70;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
    /**
     * Test for method getDayOfReportingDate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDayOfReportingDate() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 20;
        instance.setDayOfReportingDate(expected);
        assertEquals(expected, instance.getDayOfReportingDate());
    }
    /**
     * Test for method setDayOfReportingDate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDayOfReportingDate() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 27;
        instance.setDayOfReportingDate(expected);
        assertEquals(expected, instance.getDayOfReportingDate());
    }
    /**
     * Test for method getDaysInYear.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDaysInYear() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 46;
        instance.setDaysInYear(expected);
        assertEquals(expected, instance.getDaysInYear());
    }
    /**
     * Test for method setDaysInYear.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDaysInYear() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 71;
        instance.setDaysInYear(expected);
        assertEquals(expected, instance.getDaysInYear());
    }
}
