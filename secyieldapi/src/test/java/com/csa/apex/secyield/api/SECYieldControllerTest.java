/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.text.ParseException;
import java.util.Date;

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

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.api.services.impl.SECYieldServiceImpl;
import com.csa.apex.secyield.exceptions.SECYieldException;
import com.csa.apex.secyield.utility.TestUtility;

/**
 * Test class for the SECYieldController.
 *
 * @see SECYieldController
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SECYieldControllerTest {

	/**
	 * The export file type
	 */
	private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";

	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	// Create Mock
	@Mock
	private SECYieldServiceImpl secYieldServiceImpl;

	/**
	 * SECYieldServiceImpl object
	 */
	@InjectMocks
	@Autowired
	SECYieldController seyYieldController;

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
	public void setUp() throws IllegalArgumentException, SECYieldException, ParseException {
		MockitoAnnotations.initMocks(this);
		when(secYieldServiceImpl.processSecuritySECData(any(Date.class))).thenReturn(utility.getSecuritySECData());
		when(secYieldServiceImpl.getCalculatedSecuritySECData(any(Date.class)))
				.thenReturn(utility.getSecuritySECData());
		this.mockMvc = MockMvcBuilders.standaloneSetup(seyYieldController).build();
	}

	/**
	 * Test securitySECData
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getSecuritySECDataTest() throws Exception {
		mockMvc.perform(get("/securitySECData").param("businessDate", "2016-05-02")).andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_CONTENT_TYPE));
	}

	/**
	 * Test calcualtedSecuritySECData
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCalculatedSecuritySECDataTest() throws Exception {
		mockMvc.perform(get("/calcualtedSecuritySECData").param("businessDate", "2016-05-02"))
				.andExpect(status().isOk()).andExpect(content().contentType(APPLICATION_JSON_CONTENT_TYPE));
	}

	/**
	 * Test exportCalculatedSecuritySECData with valid business date
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void exportCalculatedSecuritySECDataSuccessTest() throws Exception {
		mockMvc.perform(get("/exportCalculatedSecuritySECData").param("businessDate", "2016-05-02"))
				.andExpect(status().isOk());
	}

	/**
	 * Test exportCalculatedSecuritySECData with invalid business date
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void exportCalculatedSecuritySECDataInvalidTest() throws Exception {
		this.mockMvc.perform(get("/exportCalculatedSecuritySECData").param("businessDate", "invalid"))
				.andExpect(status().is(400));
	}
}