/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.mock.service.impl;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csa.apex.fundyield.faya.Application;
import com.csa.apex.fundyield.faya.api.mock.service.FAYADataPersistenceService;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;

/**
 * Test class for the FAYADataPersistenceServiceImpl.
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
public class FAYADataPersistenceServiceImplTest {

    /**
     * FAYADataPersistenceService object.
     */
    @Autowired
    private FAYADataPersistenceService fayaDataPersistenceService;

    /**
     * Test getFAYASECData retrieves correct data.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getFAYASECDataTestSuccess() throws Exception {
        FundAccountingYieldData data = fayaDataPersistenceService
                .getFAYASECData(DateTime.parse("2014-12-01").toDate());
        assertEquals(12, data.getInstruments().size());
        assertEquals(13, data.getPortfolios().size());

        assertEquals(12,
                data.getInstruments().stream().map(e -> e.getTradableEntities()).flatMap(Collection::stream).count());
        assertEquals(12, data.getInstruments().stream().map(e -> e.getTradableEntities()).flatMap(Collection::stream)
                .map(e -> e.getTradableEntitySnapshots()).flatMap(Collection::stream).count());
        assertEquals(20,
                data.getPortfolios().stream().map(e -> e.getPortfolioHoldings()).flatMap(Collection::stream).count());

        assertEquals(1, data.getPortfolios().stream().filter(e -> e.getPortfolioSnapshots() != null)
                .map(e -> e.getPortfolioSnapshots()).flatMap(Collection::stream).count());
        assertEquals(1,
                data.getPortfolios().stream().filter(e -> e.getShareClasses() != null).map(e -> e.getShareClasses())
                        .flatMap(Collection::stream).map(e -> e.getShareClassSnapshots()).flatMap(Collection::stream)
                        .count());
    }

    /**
     * Test getFAYASECData retrieves no data for date that is not present in the db data.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getFAYASECDataTestNoData() throws Exception {
        FundAccountingYieldData data = fayaDataPersistenceService
                .getFAYASECData(DateTime.parse("2000-12-01").toDate());
        assertEquals(data.getInstruments().size(), 0);
        assertEquals(data.getPortfolios().size(), 0);
    }

    /**
     * Test getFAYASECData throws IllegalArgumentException in case the provided date is null.
     * @throws Exception if any exception occurs
     */
    @Test(expected = IllegalArgumentException.class)
    public void getFAYASECDataTestNullDate() throws Exception {
        fayaDataPersistenceService.getFAYASECData(null);
    }

    /**
     * Test persistSecuritySECData persists data successfully.
     * @throws Exception if any exception occurs
     */
    @Test
    public void persistSecuritySECDataTestSuccess() throws Exception {

        FundAccountingYieldData data = fayaDataPersistenceService
                .getFAYASECData(DateTime.parse("2014-12-01").toDate());

        BigDecimal yield = new BigDecimal(23.55);
        BigDecimal income = new BigDecimal(0.532);

        // Set some calculation result
        data.getInstruments().get(0).getTradableEntities().get(0).getTradableEntitySnapshots().get(0)
                .setDerOneDaySecurityYield(yield);
        data.getPortfolios().get(0).getPortfolioHoldings().get(0).setDerSecYield1DayIncomeAmt(income);

        // Persist
        fayaDataPersistenceService.persistSecuritySECData(data);

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
     * Test persistSecuritySECData throws IllegalArgumentException in case the provided security SEC data is null.
     * @throws Exception if any exception occurs
     */
    @Test(expected = IllegalArgumentException.class)
    public void persistSecuritySECDataTestNullInput() throws Exception {
        fayaDataPersistenceService.persistSecuritySECData(null);
    }

    /**
     * Test getCalculatedSECData retrieves correct data.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getCalculatedSECDataTestSuccess() throws Exception {
        FundAccountingYieldData data = fayaDataPersistenceService
                .getCalculatedSECData(DateTime.parse("2014-12-01").toDate());
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
     * Test getCalculatedSECData retrieves no data for date that is not present in the db data.
     * @throws Exception if any exception occurs
     */
    @Test
    public void getCalculatedSECDataTestNoData() throws Exception {
        FundAccountingYieldData data = fayaDataPersistenceService
                .getCalculatedSECData(DateTime.parse("2000-12-01").toDate());
        assertEquals(data.getInstruments().size(), 0);
        assertEquals(data.getPortfolios().size(), 0);
    }

    /**
     * Test getCalculatedSECData throws IllegalArgumentException in case the provided date is null.
     * @throws Exception if any exception occurs
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedSECDataTestNullDate() throws Exception {
        fayaDataPersistenceService.getCalculatedSECData(null);
    }
}