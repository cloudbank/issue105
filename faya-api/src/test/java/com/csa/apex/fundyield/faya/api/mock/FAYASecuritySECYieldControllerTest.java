/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.mock;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.Application;
import com.csa.apex.fundyield.faya.api.FAYASecuritySECYieldService;
import com.csa.apex.fundyield.faya.api.controllers.service.FAYADataPersistenceService;
import com.csa.apex.fundyield.faya.api.utility.TestUtility;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Test class for the FAYASecuritySECYieldController.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:clean.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:lookup.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test_data.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clean.sql")})
public class FAYASecuritySECYieldControllerTest {

    /**
     * The business date parameter name.
     */
    private static final String BUSINESS_DATE_PARAM_NAME = "businessDate";

    /**
     * FAYADataPersistenceService object.
     */
    @Autowired
    private FAYADataPersistenceService fayaDataPersistenceService;

    /**
     * FAYASecuritySECYieldController to be tested.
     */
    @Autowired
    private FAYASecuritySECYieldService fayaSecuritySECYieldController;

    /**
     * Mock controller.
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
        mockMvc = MockMvcBuilders.standaloneSetup(fayaSecuritySECYieldController).build();
    }

    /**
     * Test getFAYASECData.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getFAYASECDataTest() throws Exception {
        mockMvc.perform(get("/fayaFundAccountingSECYieldData").param(BUSINESS_DATE_PARAM_NAME, "2014-12-01"))
                .andExpect(status().isOk()).andExpect(content().contentType(TestUtility.APPLICATION_JSON_CONTENT_TYPE));
    }

    /**
     * Test getFAYASECData with invalid business date.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getFAYASECDataInvalidTest() throws Exception {
        this.mockMvc.perform(get("/fayaFundAccountingSECYieldData").param("businessDate", "invalid"))
                .andExpect(status().is(400));
    }

    /**
     * Test persistSecuritySECData.
     * @throws Exception if any exception occurs
     */
    @Test
    public void persistSecuritySECDataTest() throws Exception {

        FundAccountingYieldData data = fayaDataPersistenceService
                .getFAYASECData(DateTime.parse("2014-12-01").toDate());

        // Set some calculation result
        BigDecimal yield = new BigDecimal(23.55);
        BigDecimal income = new BigDecimal(0.532);
        data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0)
                .setDerOneDaySecurityYield(yield);
        data.getPortfolios().get(0).getPortfolioHoldings().get(0).setDerSecYield1DayIncomeAmt(income);

        Gson gson = new GsonBuilder().setDateFormat(Constants.API_DATE_FORMAT).create();
        String json = gson.toJson(data);

        mockMvc.perform(put("/calculatedFundAccountingSECYieldData")
                .contentType(TestUtility.APPLICATION_JSON_CONTENT_TYPE).content(json)).andExpect(status().isOk());

        data = fayaDataPersistenceService.getFAYASECData(DateTime.parse("2014-12-01").toDate());

        assertEquals(yield.setScale(2, BigDecimal.ROUND_HALF_DOWN),
                data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0)
                        .getDerOneDaySecurityYield().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        assertEquals(income.setScale(2, BigDecimal.ROUND_HALF_DOWN), data.getPortfolios().get(0).getPortfolioHoldings()
                .get(0).getDerSecYield1DayIncomeAmt().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        assertEquals(12, data.getInstruments().size());
        assertEquals(13, data.getPortfolios().size());
        assertEquals(12,
                data.getInstruments().stream().map(e -> e.getTradableEntities()).flatMap(Collection::stream).count());
        assertEquals(12, data.getInstruments().stream().map(e -> e.getTradableEntities()).flatMap(Collection::stream)
                .map(e -> e.getTradableEntitySnapshots()).flatMap(Collection::stream).count());
        assertEquals(20,
                data.getPortfolios().stream().map(e -> e.getPortfolioHoldings()).flatMap(Collection::stream).count());
    }

    /**
     * Test persistSecuritySECData with invalid input data.
     * @throws Exception if any exception occurs
     */
    @Test
    public void persistSecuritySECDataInvalidTest() throws Exception {
        mockMvc.perform(put("/calculatedFundAccountingSECYieldData")
                .contentType(TestUtility.APPLICATION_JSON_CONTENT_TYPE).content("")).andExpect(status().is(400));
    }

    /**
     * Test getCalculatedSECData.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getCalculatedSECDataTest() throws Exception {
        mockMvc.perform(get("/calculatedFundAccountingSECYieldData").param(BUSINESS_DATE_PARAM_NAME, "2016-05-02"))
                .andExpect(status().isOk()).andExpect(content().contentType(TestUtility.APPLICATION_JSON_CONTENT_TYPE));
    }

    /**
     * Test getCalculatedSECData with invalid input data.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getCalculatedSECDataInvalidTest() throws Exception {
        mockMvc.perform(get("/calculatedFundAccountingSECYieldData").param(BUSINESS_DATE_PARAM_NAME, "invalid"))
                .andExpect(status().is(400));
    }
}