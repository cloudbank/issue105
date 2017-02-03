/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.api.services.impl.utility.UtilityFAYAAPIClient;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.TestUtility;

/**
* Test class for the ClassLevel7DayN1AYieldCalculationEngine.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest(UriComponentsBuilder.class)
@IntegrationTest
public class ClassLevel7DayN1AYieldCalculationEngineTest {
    /**
     * Test for method calculate.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void calculate() throws Exception {
        ClassLevel7DayN1AYieldCalculationEngine instance = new ClassLevel7DayN1AYieldCalculationEngine();
        instance.setThreadCount(20);
        instance.setUtilityCustomerAPIClient(TestUtility.getUtilityFAYAAPIClient());
        FundAccountingYieldData fundAccountingYieldData = TestUtility.getFundAccountingYieldData();
        SECConfiguration configuration = new SECConfiguration();
        configuration.setOperationScale(2);
        configuration.setRoundingMode(2);
        instance.calculate(fundAccountingYieldData, configuration);
    }
    /**
     * Test for method calculate with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void calculateInvalid() throws Exception {
        ClassLevel7DayN1AYieldCalculationEngine instance = new ClassLevel7DayN1AYieldCalculationEngine();
        instance.calculate(null, null);
    }
    /**
     * Test for method getUtilityCustomerAPIClient.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getUtilityCustomerAPIClient() throws Exception {
        ClassLevel7DayN1AYieldCalculationEngine instance = new ClassLevel7DayN1AYieldCalculationEngine();
        UtilityFAYAAPIClient expected = TestUtility.getUtilityFAYAAPIClient();
        instance.setUtilityCustomerAPIClient(expected);
        assertEquals(expected, instance.getUtilityCustomerAPIClient());
    }
}
