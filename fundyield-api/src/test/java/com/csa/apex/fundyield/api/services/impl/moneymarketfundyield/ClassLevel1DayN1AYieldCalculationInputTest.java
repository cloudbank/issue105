/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevel1DayN1AYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevel1DayN1AYieldCalculationInputTest {
    /**
     * Test for method getStr.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getStr() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(12);
        instance.setN1ADistIncomeStr(expected);
        assertEquals(expected, instance.getN1ADistIncomeStr());
    }
    /**
     * Test for method setStr.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setStr() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(75);
        instance.setN1ADistIncomeStr(expected);
        assertEquals(expected, instance.getN1ADistIncomeStr());
    }
    /**
     * Test for method getOpct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOpct() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(25);
        instance.setN1ADistIncomeOpct(expected);
        assertEquals(expected, instance.getN1ADistIncomeOpct());
    }
    /**
     * Test for method setOpct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setOpct() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(77);
        instance.setN1ADistIncomeOpct(expected);
        assertEquals(expected, instance.getN1ADistIncomeOpct());
    }
    /**
     * Test for method getTni.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getTni() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(93);
        instance.setN1ADistIncomeUnmodAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeUnmodAmt());
    }
    /**
     * Test for method setTni.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setTni() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(10);
        instance.setN1ADistIncomeUnmodAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeUnmodAmt());
    }
    /**
     * Test for method getDa.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDa() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(4);
        instance.setN1ADistIncomeAdjAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeAdjAmt());
    }
    /**
     * Test for method setDa.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDa() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(82);
        instance.setN1ADistIncomeAdjAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeAdjAmt());
    }
    /**
     * Test for method getRda.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getRda() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(49);
        instance.setN1ADistIncomeAdjRevAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeAdjRevAmt());
    }
    /**
     * Test for method setRda.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setRda() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(94);
        instance.setN1ADistIncomeAdjRevAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeAdjRevAmt());
    }
    /**
     * Test for method getMda.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getMda() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(63);
        instance.setN1ADistReimbursementAmt(expected);
        assertEquals(expected, instance.getN1ADistReimbursementAmt());
    }
    /**
     * Test for method setMda.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setMda() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(60);
        instance.setN1ADistReimbursementAmt(expected);
        assertEquals(expected, instance.getN1ADistReimbursementAmt());
    }
    /**
     * Test for method getB.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getB() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(36);
        instance.setN1ADistIncomeBreakageAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeBreakageAmt());
    }
    /**
     * Test for method setB.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setB() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(47);
        instance.setN1ADistIncomeBreakageAmt(expected);
        assertEquals(expected, instance.getN1ADistIncomeBreakageAmt());
    }
    /**
     * Test for method getSo.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getSo() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(99);
        instance.setDistributableCapstockQty(expected);
        assertEquals(expected, instance.getDistributableCapstockQty());
    }
    /**
     * Test for method setSo.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setSo() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(2);
        instance.setDistributableCapstockQty(expected);
        assertEquals(expected, instance.getDistributableCapstockQty());
    }
    /**
     * Test for method getNv.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getNv() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(13);
        instance.setNavAmount(expected);
        assertEquals(expected, instance.getNavAmount());
    }
    /**
     * Test for method setNv.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setNv() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(26);
        instance.setNavAmount(expected);
        assertEquals(expected, instance.getNavAmount());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        int expected = 1;
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
        ClassLevel1DayN1AYieldCalculationInput instance = new ClassLevel1DayN1AYieldCalculationInput(new SECConfiguration());
        int expected = 1;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
