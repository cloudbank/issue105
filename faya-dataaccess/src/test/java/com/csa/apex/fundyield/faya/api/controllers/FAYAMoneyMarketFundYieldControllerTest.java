/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

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
import com.csa.apex.fundyield.faya.api.service.FAYAMoneyMarketDataPersistenceService;

/**
 * Test class for the FAYAMoneyMarketFundYieldController.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FAYAMoneyMarketFundYieldControllerTest {

    /**
     * CustomerDataController to be tested.
     */
    @InjectMocks
    FAYAMoneyMarketFundYieldController fayaMoneyMarketFundYieldController;

    /**
     * Mock service.
     */
    @Mock
    private FAYAMoneyMarketDataPersistenceService fayaMoneyMarketDataPersistenceService;

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
        mockMvc = MockMvcBuilders.standaloneSetup(fayaMoneyMarketFundYieldController).build();
    }

    /**
     * Test for method getFAYAMoneyMarketFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void getFAYAMoneyMarketFundYieldData() throws Exception {
        this.mockMvc.perform(get("/FAYAMoneyMarketFundYieldData").param(Constants.BUSINESS_DATE, "2016-05-02"))
                .andExpect(status().is(400));

    }

    /**
     * Test for method getFAYAMoneyMarketFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getFAYAMoneyMarketFundYieldDataInvalid() throws Exception {
        FAYAMoneyMarketFundYieldController instance = new FAYAMoneyMarketFundYieldController();
        instance.getFAYAMoneyMarketFundYieldData(null);
    }

    /**
     * Test for method persistMoneyMarketFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void persistMoneyMarketFundYieldData() throws Exception {
        Map<String, Object> sessionAttrs = new HashMap<String, Object>();
        sessionAttrs.put(Constants.CURRENT_USER_ID, "123");
        this.mockMvc.perform(put("/calculatedMoneyMarketFundYieldPortfolio").param(Constants.BUSINESS_DATE, "2016-05-02")
                .sessionAttrs(sessionAttrs)).andExpect(status().is(400));

    }

    /**
     * Test for method persistMoneyMarketFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void persistMoneyMarketFundYieldDataInvalid() throws Exception {
        FAYAMoneyMarketFundYieldController instance = new FAYAMoneyMarketFundYieldController();
        instance.persistMoneyMarketFundYieldData(null, null);
    }

    /**
     * Test for method getCalculatedMoneyMarketFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void getCalculatedMoneyMarketFundYieldData() throws Exception {
        this.mockMvc.perform(get("/calculatedFAYAMoneyMarketFundYieldData").param(Constants.BUSINESS_DATE, "2016-05-02"))
                .andExpect(status().is(400));

    }

    /**
     * Test for method getCalculatedMoneyMarketFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedMoneyMarketFundYieldDataInvalid() throws Exception {
        FAYAMoneyMarketFundYieldController instance = new FAYAMoneyMarketFundYieldController();
        instance.getCalculatedMoneyMarketFundYieldData(null);
    }
}
