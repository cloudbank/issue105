/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.controllers;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import com.csa.apex.fundyield.utility.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csa.apex.fundyield.faya.Application;
import com.csa.apex.fundyield.faya.api.ToleranceCheckService;
import com.csa.apex.fundyield.faya.api.utility.TestUtility;

/**
 * Test class for the MockToleranceCheckController.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MockToleranceCheckControllerTest {

    /**
     * MockToleranceCheckController to be tested.
     */
    @InjectMocks
    MockToleranceCheckController mockToleranceCheckController;

    /**
     * Mock service.
     */
    @Mock
    private ToleranceCheckService toleranceCheckService;

    /**
     * Mock controller.
     */
    private MockMvc mockMvc;

    /**
     * Mock Setup.
     * @throws Exception if any parsing exception occurs
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockToleranceCheckController).build();
    }

    /**
     * Test for method initiateSECFundLevelBatchToleranceCheck.
     * @throws Exception to JUnit
     */
    @Test
    public void initiateSECFundLevelBatchToleranceCheck1() throws Exception {
        this.mockMvc.perform(get("/secFundLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));
    }

    /**
     * Test for method initiateSECFundLevelBatchToleranceCheck with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateSECFundLevelBatchToleranceCheckInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateSECFundLevelBatchToleranceCheck(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method initiateSECFundLevelBatchToleranceCheck with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateSECFundLevelBatchToleranceCheckInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateSECFundLevelBatchToleranceCheck(null, new Date());
    }

    /**
     * Test for method initiateMoneyMarketFundLevelBatchToleranceCheck.
     * @throws Exception to JUnit
     */
    @Test
    public void initiateMoneyMarketFundLevelBatchToleranceCheck1() throws Exception {
        this.mockMvc.perform(get("/moneyMarketFundLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));
    }

    /**
     * Test for method initiateMoneyMarketFundLevelBatchToleranceCheck with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateMoneyMarketFundLevelBatchToleranceCheckInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateMoneyMarketFundLevelBatchToleranceCheck(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method initiateMoneyMarketFundLevelBatchToleranceCheck with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateMoneyMarketFundLevelBatchToleranceCheckInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateMoneyMarketFundLevelBatchToleranceCheck(null, new Date());
    }

    /**
     * Test for method initiateSecurityLevelBatchToleranceCheck.
     * @throws Exception to JUnit
     */
    @Test
    public void initiateSecurityLevelBatchToleranceCheck1() throws Exception {
        this.mockMvc.perform(get("/securityLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));
    }

    /**
     * Test for method initiateSecurityLevelBatchToleranceCheck with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateSecurityLevelBatchToleranceCheckInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateSecurityLevelBatchToleranceCheck(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method initiateSecurityLevelBatchToleranceCheck with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateSecurityLevelBatchToleranceCheckInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateSecurityLevelBatchToleranceCheck(null, new Date());
    }

    /**
     * Test for method getDistributionFundLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getDistributionFundLevelBatchToleranceCheckResult1() throws Exception {
        this.mockMvc.perform(get("/distributionFundLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));
    }

    /**
     * Test for method getDistributionFundLevelBatchToleranceCheckResult with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getDistributionFundLevelBatchToleranceCheckResultInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getDistributionFundLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method getDistributionFundLevelBatchToleranceCheckResult with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getDistributionFundLevelBatchToleranceCheckResultInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getDistributionFundLevelBatchToleranceCheckResult(null, new Date());
    }

    /**
     * Test for method initiateDistributionFundLevelBatchToleranceCheck.
     * @throws Exception to JUnit
     */
    @Test
    public void initiateDistributionFundLevelBatchToleranceCheck1() throws Exception {
        this.mockMvc.perform(get("/distributionFundLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));

    }

    /**
     * Test for method initiateDistributionFundLevelBatchToleranceCheck with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateDistributionFundLevelBatchToleranceCheckInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateDistributionFundLevelBatchToleranceCheck(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method initiateDistributionFundLevelBatchToleranceCheck with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateDistributionFundLevelBatchToleranceCheckInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateDistributionFundLevelBatchToleranceCheck(null, new Date());
    }

    /**
     * Test for method getSECFundLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getSECFundLevelBatchToleranceCheckResult1() throws Exception {
        this.mockMvc.perform(get("/secFundLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));

    }

    /**
     * Test for method getSECFundLevelBatchToleranceCheckResult with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSECFundLevelBatchToleranceCheckResultInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getSECFundLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method getSECFundLevelBatchToleranceCheckResult with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSECFundLevelBatchToleranceCheckResultInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getSECFundLevelBatchToleranceCheckResult(null, new Date());
    }

    /**
     * Test for method getMoneyMarketFundLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getMoneyMarketFundLevelBatchToleranceCheckResult1() throws Exception {
        this.mockMvc.perform(get("/moneyMarketFundLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));

    }

    /**
     * Test for method getMoneyMarketFundLevelBatchToleranceCheckResult with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getMoneyMarketFundLevelBatchToleranceCheckResultInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getMoneyMarketFundLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method getMoneyMarketFundLevelBatchToleranceCheckResult with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getMoneyMarketFundLevelBatchToleranceCheckResultInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getMoneyMarketFundLevelBatchToleranceCheckResult(null, new Date());
    }

    /**
     * Test for method getSecurityLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getSecurityLevelBatchToleranceCheckResult1() throws Exception {
        this.mockMvc.perform(get("/securityLevelBatchToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02"))
                .andExpect(status().is(400));

    }

    /**
     * Test for method getSecurityLevelBatchToleranceCheckResult with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSecurityLevelBatchToleranceCheckResultInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getSecurityLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method getSecurityLevelBatchToleranceCheckResult with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSecurityLevelBatchToleranceCheckResultInvalidUserId() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getSecurityLevelBatchToleranceCheckResult(null, new Date());
    }

    /**
     * Test for method initiateSecurityLevelWhatIfToleranceCheck.
     * @throws Exception to JUnit
     */
    @Test
    public void initiateSecurityLevelWhatIfToleranceCheck1() throws Exception {
        this.mockMvc.perform(
                put("/securityLevelWhatIfToleranceCheck").param(Constants.REPORT_DATE, "2016-05-02").param(Constants.CUSIP, "123"))
                .andExpect(status().is(400));

    }

    /**
     * Test for method initiateSecurityLevelWhatIfToleranceCheck with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void initiateSecurityLevelWhatIfToleranceCheckInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.initiateSecurityLevelWhatIfToleranceCheck(null, null, null);
    }

    /**
     * Test for method getSecurityLevelWhatIfToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getSecurityLevelWhatIfToleranceCheckResult1() throws Exception {
		this.mockMvc
				.perform(
						get("/securityLevelWhatIfToleranceCheck").param(Constants.USER_ID, TestUtility.DEFAULT_USER_ID)
								.param(Constants.REPORT_DATE, "2016-05-02").param(Constants.CUSIP, "123"))
				.andExpect(status().is(200));
    }

    /**
     * Test for method getSecurityLevelWhatIfToleranceCheckResult with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSecurityLevelWhatIfToleranceCheckResultInvalid() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        instance.getSecurityLevelWhatIfToleranceCheckResult(null, null, null);
    }

    /**
     * Test for method getDistributionFundLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getDistributionFundLevelBatchToleranceCheckResult() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        Date reportDate = new Date();
        assertNotNull(instance.getDistributionFundLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, reportDate));
    }

    /**
     * Test for method getSECFundLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getSECFundLevelBatchToleranceCheckResult() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        Date reportDate = new Date();
        assertNotNull(instance.getSECFundLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, reportDate));
    }

    /**
     * Test for method getMoneyMarketFundLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getMoneyMarketFundLevelBatchToleranceCheckResult() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        Date reportDate = new Date();
        assertNotNull(instance.getMoneyMarketFundLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, reportDate));
    }

    /**
     * Test for method getSecurityLevelBatchToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getSecurityLevelBatchToleranceCheckResult() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        Date reportDate = new Date();
        assertNotNull(instance.getSecurityLevelBatchToleranceCheckResult(TestUtility.DEFAULT_USER_ID, reportDate));
    }

    /**
     * Test for method getSecurityLevelWhatIfToleranceCheckResult.
     * @throws Exception to JUnit
     */
    @Test
    public void getSecurityLevelWhatIfToleranceCheckResult() throws Exception {
        MockToleranceCheckController instance = new MockToleranceCheckController();
        Date reportDate = new Date();
        String cusip = "test4";
        assertNotNull(instance.getSecurityLevelWhatIfToleranceCheckResult(TestUtility.DEFAULT_USER_ID, reportDate, cusip));
    }

}
