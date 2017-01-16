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
        instance.setMil(expected);
        assertEquals(expected, instance.getMil());
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
        instance.setMil(expected);
        assertEquals(expected, instance.getMil());
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
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(28);
        instance.setD(expected);
        assertEquals(expected, instance.getD());
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
        ClassLevelRestated7DayYieldCalculationOutput instance = new ClassLevelRestated7DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(82);
        instance.setY(expected);
        assertEquals(expected, instance.getY());
    }
}
