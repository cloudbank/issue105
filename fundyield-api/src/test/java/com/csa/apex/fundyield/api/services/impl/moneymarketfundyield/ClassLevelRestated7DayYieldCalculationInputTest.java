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
* Test class for the ClassLevelRestated7DayYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelRestated7DayYieldCalculationInputTest {
    /**
     * Test for method getStr.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getStr() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(93);
        instance.setN1AReimbursementStr(expected);
        assertEquals(expected, instance.getN1AReimbursementStr());
    }
    /**
     * Test for method setStr.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setStr() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(24);
        instance.setN1AReimbursementStr(expected);
        assertEquals(expected, instance.getN1AReimbursementStr());
    }
    /**
     * Test for method getOpct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOpct() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(100);
        instance.setN1AReimbursementOpct(expected);
        assertEquals(expected, instance.getN1AReimbursementOpct());
    }
    /**
     * Test for method setOpct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setOpct() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(96);
        instance.setN1AReimbursementOpct(expected);
        assertEquals(expected, instance.getN1AReimbursementOpct());
    }
    /**
     * Test for method getTni.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getTni() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(71);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(13);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(93);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(76);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(88);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(64);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(6);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(34);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(31);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(65);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(27);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(1);
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(28);
        instance.setNavAmt(expected);
        assertEquals(expected, instance.getNavAmt());
    }
    /**
     * Test for method setNv.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setNv() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(98);
        instance.setNavAmt(expected);
        assertEquals(expected, instance.getNavAmt());
    }
    /**
     * Test for method getReim.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getReim() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(2);
        instance.setN1AReimbursementEarnedAmt(expected);
        assertEquals(expected, instance.getN1AReimbursementEarnedAmt());
    }
    /**
     * Test for method setReim.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setReim() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(22);
        instance.setN1AReimbursementEarnedAmt(expected);
        assertEquals(expected, instance.getN1AReimbursementEarnedAmt());
    }
    /**
     * Test for method getdPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getdPrevious6Days() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(40);
        instance.setdPrevious6Days(expected);
        assertEquals(expected, instance.getdPrevious6Days());
    }
    /**
     * Test for method setdPrevious6Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setdPrevious6Days() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(34);
        instance.setdPrevious6Days(expected);
        assertEquals(expected, instance.getdPrevious6Days());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        int expected = 40;
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
        ClassLevelRestated7DayYieldCalculationInput instance = new ClassLevelRestated7DayYieldCalculationInput(new SECConfiguration());
        int expected = 23;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
