/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevelRestated7DayYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelRestated7DayYieldCalculationOutputTest {
    /**
     * Test for method getMil.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getMil() throws Exception {
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(39);
        instance.setMoneyMarketRestartYield(expected);
        assertEquals(expected, instance.getMoneyMarketRestartYield());
    }
    /**
     * Test for method setMil.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setMil() throws Exception {
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(8);
        instance.setMoneyMarketRestartYield(expected);
        assertEquals(expected, instance.getMoneyMarketRestartYield());
    }
    /**
     * Test for method getD.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getD() throws Exception {
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(34);
        instance.setMoneyMarketRestart1DayYield(expected);
        assertEquals(expected, instance.getMoneyMarketRestart1DayYield());
    }
    /**
     * Test for method setD.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setD() throws Exception {
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(28);
        instance.setMoneyMarketRestart1DayYield(expected);
        assertEquals(expected, instance.getMoneyMarketRestart1DayYield());
    }
    /**
     * Test for method getY.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getY() throws Exception {
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(76);
        instance.setDerMmRst7DayYieldPct(expected);
        assertEquals(expected, instance.getDerMmRst7DayYieldPct());
    }
    /**
     * Test for method setY.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setY() throws Exception {
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(82);
        instance.setDerMmRst7DayYieldPct(expected);
        assertEquals(expected, instance.getDerMmRst7DayYieldPct());
    }
}
