/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.csa.apex.fundyield.utility.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csa.apex.fundyield.faya.Application;
import com.csa.apex.fundyield.faya.api.service.UtilityFAYAAPIPersistenceService;

/**
 * Test class for the UtilityFAYAAPIPersistenceServiceImpl.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:clean.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:lookup.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:test_data_faya.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:clean.sql")})
public class UtilityFAYAAPIPersistenceServiceImplTest {

    /**
     * UtilityFAYAAPIPersistenceService object.
     */
    @Autowired
    private UtilityFAYAAPIPersistenceService utilityFAYAAPIPersistenceService;

    /**
     * Test for method getAvgOfMnyMkt1DayDistYieldPctForPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getAvgOfMnyMkt1DayDistYieldPctForPreviousDays() throws Exception {
        BigDecimal expected = new BigDecimal(10);
        long shareClassSid = 111;
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date reportDate = f.parse("2016-12-10");
        int numOfDays = 41;
        assertEquals(expected,
                utilityFAYAAPIPersistenceService.getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(shareClassSid, reportDate, numOfDays));
    }

    /**
     * Test for method getAvgOfMnyMkt1DayDistYieldPctForPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysInvalid() throws Exception {
        utilityFAYAAPIPersistenceService.getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(-1, null, 1);
    }

    /**
     * Test for method getSumOfDer1DayYieldN1AMnyMktPctPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDer1DayYieldN1AMnyMktPctPreviousDays() throws Exception {
        BigDecimal expected = new BigDecimal(30);
        long shareClassSid = 111;
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date reportDate = f.parse("2016-12-10");
        int numOfDays = 68;
        assertEquals(expected,
                utilityFAYAAPIPersistenceService.getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(shareClassSid, reportDate, numOfDays));
    }

    /**
     * Test for method getSumOfDer1DayYieldN1AMnyMktPctPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIPersistenceServiceImpl instance = new UtilityFAYAAPIPersistenceServiceImpl();
        instance.getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(1, null, -1);
    }

    /**
     * Test for method getSumOfDerRestate1DayYieldMnyMktPctPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDerRestate1DayYieldMnyMktPctPreviousDays() throws Exception {
        BigDecimal expected = new BigDecimal(40);
        long shareClassSid = 111;
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date reportDate = f.parse("2016-12-10");
        int numOfDays = 47;
        assertEquals(expected,
                utilityFAYAAPIPersistenceService.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(shareClassSid, reportDate, numOfDays));
    }

    /**
     * Test for method getSumOfDerRestate1DayYieldMnyMktPctPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIPersistenceServiceImpl instance = new UtilityFAYAAPIPersistenceServiceImpl();
        instance.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(-1, new Date(), 1);
    }

    /**
     * Test for method getAvgOfMnyMkt7DayYieldPctForPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getAvgOfMnyMkt7DayYieldPctForPreviousDays() throws Exception {
        BigDecimal expected = new BigDecimal(10);
        long shareClassSid = 111;
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date reportDate = f.parse("2016-12-10");
        int numOfDays = 100;
        assertEquals(expected,
                utilityFAYAAPIPersistenceService.getAvgOfMnyMkt7DayYieldPctForPreviousDays(shareClassSid, reportDate, numOfDays));
    }

    /**
     * Test for method getAvgOfMnyMkt7DayYieldPctForPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getAvgOfMnyMkt7DayYieldPctForPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIPersistenceServiceImpl instance = new UtilityFAYAAPIPersistenceServiceImpl();
        instance.getAvgOfMnyMkt7DayYieldPctForPreviousDays(1, new Date(), -1);
    }

    /**
     * Test for method getSumOfDer7DayYieldN1AMnyMktPctPreviousDays.
     * @throws Exception to JUnit
     */
    @Test
    public void getSumOfDer7DayYieldN1AMnyMktPctPreviousDays() throws Exception {
        BigDecimal expected = new BigDecimal(20);
        long shareClassSid = 111;
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date reportDate = f.parse("2016-12-10");
        int numOfDays = 99;
        assertEquals(expected,
                utilityFAYAAPIPersistenceService.getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(shareClassSid, reportDate, numOfDays));
    }

    /**
     * Test for method getSumOfDer7DayYieldN1AMnyMktPctPreviousDays with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysInvalid() throws Exception {
        UtilityFAYAAPIPersistenceServiceImpl instance = new UtilityFAYAAPIPersistenceServiceImpl();
        instance.getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(-1, new Date(), 1);
    }
}
