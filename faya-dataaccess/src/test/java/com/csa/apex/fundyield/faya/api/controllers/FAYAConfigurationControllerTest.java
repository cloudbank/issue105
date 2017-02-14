/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.Application;
import com.csa.apex.fundyield.faya.api.FAYAConfigurationService;
import com.csa.apex.fundyield.faya.api.utility.TestUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Test class for the FAYAConfigurationController.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FAYAConfigurationControllerTest {

    /**
     * FAYAConfigurationController to be tested.
     */
    @Autowired
    private FAYAConfigurationService fayaConfigurationController;

    /**
     * Mock mvc container.
     */
    private MockMvc mockMvc;

    /**
     * Mock Setup.
     * @throws FundAccountingYieldException if any application specific exception occurs
     * @throws IllegalArgumentException in case the input is invalid
     * @throws ParseException if any parsing exception occurs
     */
    @Before
    public void setUp() throws FundAccountingYieldException, ParseException {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fayaConfigurationController).build();
    }

    /**
     * Test getConfiguration.
     * @throws Exception if any exception occurs
     */
    @Test
	public void getConfigurationTest() throws Exception {
		mockMvc.perform(get("/securitySECDataConfiguration").header(Constants.USER_ID, TestUtility.DEFAULT_USER_ID))
				.andExpect(status().isOk()).andExpect(content().contentType(TestUtility.APPLICATION_JSON_CONTENT_TYPE))
				.andExpect(jsonPath("$.operationScale", Matchers.is(7)))
				.andExpect(jsonPath("$.roundingMode", Matchers.is(4)));
	}
}
