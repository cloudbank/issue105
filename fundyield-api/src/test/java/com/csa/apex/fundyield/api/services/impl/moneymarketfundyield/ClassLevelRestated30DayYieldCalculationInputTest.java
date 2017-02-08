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
* Test class for the ClassLevelRestated30DayYieldCalculationInput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelRestated30DayYieldCalculationInputTest {
    /**
     * Test for method getDerMmRestate1DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMmRestate1DayYieldPct() throws Exception {
        ClassLevelRestated30DayYieldCalculationInput instance = new ClassLevelRestated30DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(79);
        instance.setDerMmRestate1DayYieldPct(expected);
        assertEquals(expected, instance.getDerMmRestate1DayYieldPct());
    }
    /**
     * Test for method setDerMmRestate1DayYieldPct.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerMmRestate1DayYieldPct() throws Exception {
        ClassLevelRestated30DayYieldCalculationInput instance = new ClassLevelRestated30DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(17);
        instance.setDerMmRestate1DayYieldPct(expected);
        assertEquals(expected, instance.getDerMmRestate1DayYieldPct());
    }
    /**
     * Test for method getSumOfDerRestate1DayYieldMnyMktPctPrevious29Days.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDerRestate1DayYieldMnyMktPctPrevious29Days() throws Exception {
        ClassLevelRestated30DayYieldCalculationInput instance = new ClassLevelRestated30DayYieldCalculationInput(new SECConfiguration());
        BigDecimal expected = new BigDecimal(89);
        instance.setSumOfDerRestate1DayYieldMnyMktPctPrevious29Days(expected);
        assertEquals(expected, instance.getSumOfDerRestate1DayYieldMnyMktPctPrevious29Days());
    }
    /**
     * Test for method getOperationScale.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getOperationScale() throws Exception {
        ClassLevelRestated30DayYieldCalculationInput instance = new ClassLevelRestated30DayYieldCalculationInput(new SECConfiguration());
        int expected = 92;
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
        ClassLevelRestated30DayYieldCalculationInput instance = new ClassLevelRestated30DayYieldCalculationInput(new SECConfiguration());
        int expected = 31;
        instance.setOperationScale(expected);
        assertEquals(expected, instance.getOperationScale());
    }
}
