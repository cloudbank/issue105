/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.Date;

import com.csa.apex.fundyield.utility.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csa.apex.fundyield.Application;
import com.csa.apex.fundyield.api.SecuritySECYieldController;
import com.csa.apex.fundyield.api.services.impl.SecuritySECYieldServiceImpl;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.utility.TestUtility;

/**
 * Test class for the SECYieldController.
 *
 * @see SecuritySECYieldController
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SecuritySECYieldControllerTest {

	/**
	 * The export file type.
	 */
	private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";

	/**
	 * Utility class.
	 */
	@Autowired
	private TestUtility utility;

	// Create Mock
	@Mock
	private SecuritySECYieldServiceImpl secYieldServiceImpl;

	/**
	 * SECYieldServiceImpl object.
	 */
	@InjectMocks
	@Autowired
	private SecuritySECYieldController seyYieldController;

	/**
	 * Mock controller.
	 */
	private MockMvc mockMvc;

	/**
	 * Mock Setup.
	 * 
	 * @throws FundAccountingYieldException
	 *             if any application specific exception occurs
	 * @throws IllegalArgumentException
	 *             in case the input is invalid
	 * @throws ParseException
	 *             if any parsing exception occurs
	 */
	@Before
	public void setUp() throws FundAccountingYieldException, ParseException {
		MockitoAnnotations.initMocks(this);
		when(secYieldServiceImpl.processSecuritySECData(any(Date.class))).thenReturn(utility.constructFAYAData());
		when(secYieldServiceImpl.getCalculatedSecuritySECData(any(Date.class)))
				.thenReturn(utility.constructFAYAData());
		this.mockMvc = MockMvcBuilders.standaloneSetup(seyYieldController).build();
	}

	/**
	 * Test securitySECData.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getSecuritySECDataTest() throws Exception {
		mockMvc.perform(get("/fundAccountingSECYieldData").param(Constants.BUSINESS_DATE, "2016-05-02")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CONTENT_TYPE));
	}

	/**
	 * Test calculatedSecuritySECData.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCalculatedSecuritySECDataTest() throws Exception {
		mockMvc.perform(get("/calculatedFundAccountingSECYieldData").param(Constants.BUSINESS_DATE, "2016-05-02"))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_CONTENT_TYPE));
	}

	/**
	 * Test exportCalculatedSecuritySECData with valid business date.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void exportCalculatedSecuritySECDataSuccessTest() throws Exception {
		mockMvc.perform(get("/exportCalculatedFundAccountingSECYieldData").param(Constants.BUSINESS_DATE, "2016-05-02"))
				.andExpect(status().isOk());
	}

	/**
	 * Test exportCalculatedSecuritySECData with invalid business date.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void exportCalculatedSecuritySECDataInvalidTest() throws Exception {
		this.mockMvc.perform(get("/exportCalculatedFundAccountingSECYieldData").param(Constants.BUSINESS_DATE, "invalid"))
				.andExpect(status().is(400));
	}
}