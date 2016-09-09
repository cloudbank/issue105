/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csa.apex.secyield.TestApplication;
import com.csa.apex.secyield.customer.api.CustomerDataService;
import com.csa.apex.secyield.exceptions.SECYieldException;
import com.csa.apex.secyield.utility.Constants;
import com.csa.apex.secyield.utility.TestUtility;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Test class for the CustomerDataController.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@WebAppConfiguration
@IntegrationTest
public class CustomerDataControllerTest {

	private static final String BUSINESS_DATE_PARAM_NAME = "businessDate";

	/**
	 * The export file type
	 */
	private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";

	/**
	 * CustomerDataController to be tested
	 */
	@Autowired
	CustomerDataService customerDataController;

	/**
	 * Mock controller
	 */
	private MockMvc mockMvc;

	/**
	 * Mock Setup
	 * 
	 * @throws SECYieldException
	 *             if any application specific exception occurs
	 * @throws IllegalArgumentException
	 *             in case the input is invalid
	 * @throws ParseException
	 *             if any parsing exception occurs
	 */
	@Before
	public void setUp() throws SECYieldException, ParseException {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerDataController).build();
	}

	/**
	 * Test getCustomerSECData
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCustomerSECDataTest() throws Exception {
		mockMvc.perform(get("/customerSecuritySECData").param(BUSINESS_DATE_PARAM_NAME, "2016-05-02"))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_CONTENT_TYPE));
	}

	/**
	 * Test getCustomerSECData with invalid business date
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCustomerSECDataInvalidTest() throws Exception {
		this.mockMvc.perform(get("/customerSecuritySECData").param("businessDate", "invalid"))
				.andExpect(status().is(400));
	}

	/**
	 * Test getCustomerSECData
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void persistSecuritySECDataTest() throws Exception {
		Gson gson = new GsonBuilder().setDateFormat(Constants.API_DATE_FORMAT).create();
		String json = gson.toJson(TestUtility.getTestSecuritySECData());
		mockMvc.perform(put("/persistSecuritySECData").contentType(APPLICATION_JSON_CONTENT_TYPE).content(json))
				.andExpect(status().isOk());
	}

	/**
	 * Test persistSecuritySECData with invalid input data
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void persistSecuritySECDataInvalidTest() throws Exception {
		mockMvc.perform(put("/persistSecuritySECData").contentType(APPLICATION_JSON_CONTENT_TYPE).content(""))
				.andExpect(status().is(400));
	}

	/**
	 * Test getConfiguration
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getConfigurationTest() throws Exception {
		mockMvc.perform(get("/securitySECDataConfiguration")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CONTENT_TYPE))
				.andExpect(jsonPath("$.operationScale", is(1))).andExpect(jsonPath("$.roundingMode", is(2)));
	}

	/**
	 * Test getCalculatedSECData
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCalculatedSECDataTest() throws Exception {
		mockMvc.perform(get("/calculatedSecuritySECData").param(BUSINESS_DATE_PARAM_NAME, "2016-05-02"))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_CONTENT_TYPE));
	}

	/**
	 * Test getCalculatedSECData with invalid input data
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCalculatedSECDataInvalidTest() throws Exception {
		mockMvc.perform(get("/calculatedSecuritySECData").param(BUSINESS_DATE_PARAM_NAME, "invalid"))
				.andExpect(status().is(400));
	}
}