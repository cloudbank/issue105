/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.csa.apex.fundyield.faya.api.service.UtilityFAYAAPIPersistenceService;
import com.csa.apex.fundyield.faya.api.utility.TestUtility;

/**
 * Test class for the UtilityFAYAAPIController.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UtilityFAYAAPIControllerTest {

    /**
     * CustomerDataController to be tested.
     */
    @InjectMocks
    UtilityFAYAAPIController utilityFAYAAPIController;

    /**
     * Mock service.
     */
    @Mock
    private UtilityFAYAAPIPersistenceService utilityFAYAAPIPersistenceService;

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
        mockMvc = MockMvcBuilders.standaloneSetup(utilityFAYAAPIController).build();
    }

    /**
     * Test for method getAvgOfMnyMkt1DayDistYieldPctForPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getAvgOfMnyMkt1DayDistYieldPctForPreviousDays() throws Exception {
		this.mockMvc
				.perform(get("/avgOfMm1DayDistYieldPctForPreviousDays")
						.param(Constants.USER_ID, TestUtility.DEFAULT_USER_ID).param(Constants.SHARE_CLASS_SID, "111")
						.param(Constants.REPORT_DATE, "2016-12-20").param(Constants.NUM_OF_DAYS, "2"))
				.andExpect(status().is(200));
    }

    /**
     * Test for method getAvgOfMnyMkt1DayDistYieldPctForPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(TestUtility.DEFAULT_USER_ID, -1, new Date(), 1);
    }
    
    /**
     * Test for method getAvgOfMnyMkt1DayDistYieldPctForPreviousDays with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysInvalidUserId() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(null, 1, new Date(), 1);
    }

    /**
     * Test for method getSumOfDer1DayYieldN1AMnyMktPctPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDer1DayYieldN1AMnyMktPctPreviousDays() throws Exception {
		this.mockMvc
				.perform(get("/sumOfDer1DayYieldN1AMmPctPreviousDays")
						.param(Constants.USER_ID, TestUtility.DEFAULT_USER_ID).param(Constants.SHARE_CLASS_SID, "111")
						.param(Constants.REPORT_DATE, "2016-12-20").param(Constants.NUM_OF_DAYS, "2"))
				.andExpect(status().is(200));
    }

    /**
     * Test for method getSumOfDer1DayYieldN1AMnyMktPctPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(TestUtility.DEFAULT_USER_ID, 1, null, 1);
    }
    
    /**
     * Test for method getSumOfDer1DayYieldN1AMnyMktPctPreviousDays with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysInvalidUserId() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(null, 1, new Date(), 1);
    }

    /**
     * Test for method getSumOfDerRestate1DayYieldMnyMktPctPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDerRestate1DayYieldMnyMktPctPreviousDays() throws Exception {
		this.mockMvc
				.perform(get("/sumOfDerRestate1DayYieldMmPctPreviousDays")
						.param(Constants.USER_ID, TestUtility.DEFAULT_USER_ID).param(Constants.SHARE_CLASS_SID, "111")
						.param(Constants.REPORT_DATE, "2016-12-20").param(Constants.NUM_OF_DAYS, "2"))
				.andExpect(status().is(200));
    }

    /**
     * Test for method getSumOfDerRestate1DayYieldMnyMktPctPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(TestUtility.DEFAULT_USER_ID, 1, null, 1);
    }
    
    /**
     * Test for method getSumOfDerRestate1DayYieldMnyMktPctPreviousDays with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysInvalidUserId() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(null, 1, new Date(), 1);
    }

    /**
     * Test for method getAvgOfMnyMkt7DayYieldPctForPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getAvgOfMnyMkt7DayYieldPctForPreviousDays() throws Exception {
		this.mockMvc
				.perform(get("/avgOfMm7DayYieldPctForPreviousDays")
						.param(Constants.USER_ID, TestUtility.DEFAULT_USER_ID).param(Constants.SHARE_CLASS_SID, "111")
						.param(Constants.REPORT_DATE, "2016-12-20").param(Constants.NUM_OF_DAYS, "2"))
				.andExpect(status().is(200));
    }

    /**
     * Test for method getAvgOfMnyMkt7DayYieldPctForPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAvgOfMnyMkt7DayYieldPctForPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getAvgOfMnyMkt7DayYieldPctForPreviousDays(TestUtility.DEFAULT_USER_ID, 1, null, 1);
    }
    
    /**
     * Test for method getAvgOfMnyMkt7DayYieldPctForPreviousDays with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAvgOfMnyMkt7DayYieldPctForPreviousDaysInvalidUserId() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getAvgOfMnyMkt7DayYieldPctForPreviousDays(null, 1, new Date(), 1);
    }

    /**
     * Test for method getSumOfDer7DayYieldN1AMnyMktPctPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDer7DayYieldN1AMnyMktPctPreviousDays() throws Exception {
		this.mockMvc
				.perform(get("/sumOfDer7DayYieldN1AMmPctPreviousDays")
						.param(Constants.USER_ID, TestUtility.DEFAULT_USER_ID).param(Constants.SHARE_CLASS_SID, "111")
						.param(Constants.REPORT_DATE, "2016-12-20").param(Constants.NUM_OF_DAYS, "2"))
				.andExpect(status().is(200));
    }

    /**
     * Test for method getSumOfDer7DayYieldN1AMnyMktPctPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(TestUtility.DEFAULT_USER_ID, 1, null, 1);
    }
    
    /**
     * Test for method getSumOfDer7DayYieldN1AMnyMktPctPreviousDays with invalid user id.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysInvalidUserId() throws Exception {
        UtilityFAYAAPIController instance = new UtilityFAYAAPIController();
        instance.getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(null, 1, new Date(), 1);
    }
}
