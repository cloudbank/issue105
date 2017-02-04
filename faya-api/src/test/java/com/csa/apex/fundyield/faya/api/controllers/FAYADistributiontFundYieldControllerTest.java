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
import com.csa.apex.fundyield.faya.api.service.FAYADistYieldDataPersistenceService;

/**
* Test class for the FAYADistributiontFundYieldController.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class FAYADistributiontFundYieldControllerTest {
	/**
	 * CustomerDataController to be tested.
	 */
	@InjectMocks
	FAYADistributiontFundYieldController fayaDistributiontFundYieldController;

	/**
	 * Mock service.
	 */
	@Mock
    private FAYADistYieldDataPersistenceService fayaDistYieldDataPersistenceService;
	/**
	 * Mock controller.
	 */
	private MockMvc mockMvc;
	
	/**
	 * Mock Setup.
	 * 
	 * @throws Exception
	 *             if any parsing exception occurs
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(fayaDistributiontFundYieldController).build();
	}
    /**
     * Test for method getFAYADistributionFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getFAYADistributionFundYieldData() throws Exception {
    	this.mockMvc.perform(get("/FAYADistributionFundYieldData").param(Constants.BUSINESS_DATE, "2016-05-02"))
		.andExpect(status().is(400));
    }
    /**
     * Test for method getFAYADistributionFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getFAYADistributionFundYieldDataInvalid() throws Exception {
        FAYADistributiontFundYieldController instance = new FAYADistributiontFundYieldController();
        instance.getFAYADistributionFundYieldData(null);
    }
    /**
     * Test for method persistDistributionFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void persistDistributionFundYieldData() throws Exception {
    	Map<String, Object> sessionAttrs = new HashMap<String, Object>();
    	sessionAttrs.put(Constants.CURRENT_USER_ID, "123");
    	this.mockMvc.perform(put("/calculatedDistributionFundYieldPortfolio").param(Constants.BUSINESS_DATE, "2016-05-02").sessionAttrs(sessionAttrs))
		.andExpect(status().is(400));
    }
    /**
     * Test for method persistDistributionFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void persistDistributionFundYieldDataInvalid() throws Exception {
        FAYADistributiontFundYieldController instance = new FAYADistributiontFundYieldController();
        instance.persistDistributionFundYieldData(null, null);
    }
    /**
     * Test for method getCalculatedDistributionFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getCalculatedDistributionFundYieldData() throws Exception {
    	this.mockMvc.perform(get("/calculatedFAYADistributionFundYieldData").param(Constants.BUSINESS_DATE, "2016-05-02"))
		.andExpect(status().is(400));
    }
    /**
     * Test for method getCalculatedDistributionFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedDistributionFundYieldDataInvalid() throws Exception {
        FAYADistributiontFundYieldController instance = new FAYADistributiontFundYieldController();
        instance.getCalculatedDistributionFundYieldData(null);
    }
}
