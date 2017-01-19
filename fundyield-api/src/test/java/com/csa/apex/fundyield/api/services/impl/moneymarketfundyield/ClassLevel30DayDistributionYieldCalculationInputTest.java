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
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 39;
        instance.setD(expected);
        assertEquals(expected, instance.getD());
    }
    /**
     * Test for method setD.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setD() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 41;
        instance.setD(expected);
        assertEquals(expected, instance.getD());
    }
    /**
     * Test for method getR.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getR() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 53;
        instance.setR(expected);
        assertEquals(expected, instance.getR());
    }
    /**
     * Test for method setR.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setR() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 12;
        instance.setR(expected);
        assertEquals(expected, instance.getR());
    }
    /**
     * Test for method getU.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getU() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(79);
        instance.setU(expected);
        assertEquals(expected, instance.getU());
    }
    /**
     * Test for method setU.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setU() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(3);
        instance.setU(expected);
        assertEquals(expected, instance.getU());
    }
    /**
     * Test for method getM.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getM() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(11);
        instance.setM(expected);
        assertEquals(expected, instance.getM());
    }
    /**
     * Test for method setM.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setM() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(93);
        instance.setM(expected);
        assertEquals(expected, instance.getM());
    }
    /**
     * Test for method getN.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getN() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(25);
        instance.setN(expected);
        assertEquals(expected, instance.getN());
    }
    /**
     * Test for method setN.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setN() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        BigDecimal expected = new BigDecimal(61);
        instance.setN(expected);
        assertEquals(expected, instance.getN());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
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
        ClassLevel30DayDistributionYieldCalculationInput instance = new ClassLevel30DayDistributionYieldCalculationInput();
        int expected = 77;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
