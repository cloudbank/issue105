/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

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
     * Test for method getD.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getD() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        int expected = 39;
        instance.setDaysInYear(expected);
        assertEquals(expected, instance.getDaysInYear());
    }
    /**
     * Test for method setD.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setD() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        int expected = 41;
        instance.setDaysInYear(expected);
        assertEquals(expected, instance.getDaysInYear());
    }
    /**
     * Test for method getR.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getR() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        int expected = 53;
        instance.setReportingDate(expected);
        assertEquals(expected, instance.getReportingDate());
    }
    /**
     * Test for method setR.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setR() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        int expected = 12;
        instance.setReportingDate(expected);
        assertEquals(expected, instance.getReportingDate());
    }
    /**
     * Test for method getU.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getU() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(79);
        instance.setDistUnmod30DayYieldPct(expected);
        assertEquals(expected, instance.getDistUnmod30DayYieldPct());
    }
    /**
     * Test for method setU.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setU() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(3);
        instance.setDistUnmod30DayYieldPct(expected);
        assertEquals(expected, instance.getDistUnmod30DayYieldPct());
    }
    /**
     * Test for method getM.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getM() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(11);
        instance.setDistYieldMilRt(expected);
        assertEquals(expected, instance.getDistYieldMilRt());
    }
    /**
     * Test for method setM.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setM() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(93);
        instance.setDistYieldMilRt(expected);
        assertEquals(expected, instance.getDistYieldMilRt());
    }
    /**
     * Test for method getN.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getN() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(25);
        instance.setNavAmt(expected);
        assertEquals(expected, instance.getNavAmt());
    }
    /**
     * Test for method setN.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setN() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        BigDecimal expected = new BigDecimal(61);
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
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        int expected = 73;
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
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput(null);
        int expected = 77;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
