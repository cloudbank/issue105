/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.service.impl;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csa.apex.fundyield.faya.Application;
import com.csa.apex.fundyield.faya.api.service.FAYADistYieldDataPersistenceService;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Instrument;
import com.csa.apex.fundyield.seccommons.entities.Portfolio;
import com.csa.apex.fundyield.seccommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.seccommons.entities.ShareClass;
import com.csa.apex.fundyield.seccommons.entities.ShareClassSnapshot;

/**
 * Test class for the FAYADistYieldDataPersistenceServiceImpl.
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
public class FAYADistYieldDataPersistenceServiceImplTest {

    /**
     * FAYADistYieldDataPersistenceService object.
     */
    @Autowired
    private FAYADistYieldDataPersistenceService fayaDistYieldDataPersistenceService;

    /**
     * Test for method getFAYADistributionFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void getFAYADistributionFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date businessDate = f.parse("2016-12-20");
        assertNotNull(fayaDistYieldDataPersistenceService.getFAYADistributionFundYieldData(businessDate));
    }

    /**
     * Test for method getFAYADistributionFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getFAYADistributionFundYieldDataInvalid() throws Exception {
        fayaDistYieldDataPersistenceService.getFAYADistributionFundYieldData(null);
    }

    /**
     * Test for method persistDistributionFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void persistDistributionFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date businessDate = f.parse("2017-01-08");
        FundAccountingYieldData data = new FundAccountingYieldData();
        data.setBusinessDate(businessDate);
        data.setReportDate(businessDate);
        data.setInstruments(new ArrayList<Instrument>());
        data.setPortfolios(new ArrayList<Portfolio>());
        Instrument i = new Instrument();
        i.setInstrumentSid(1);

        Portfolio p = new Portfolio();
        p.setPortfolioId(555);
        p.setPortfolioSid(222);
        p.setShareClasses(new ArrayList<ShareClass>());
        p.setPortfolioSnapshots(new ArrayList<PortfolioSnapshot>());

        ShareClass shareClass = new ShareClass();
        shareClass.setShareClassSid(111);
        shareClass.setPortfolioSid(222);
        shareClass.setShareClassSnapshots(new ArrayList<ShareClassSnapshot>());
        p.getShareClasses().add(shareClass);

        ShareClassSnapshot shareClassSnapshot = new ShareClassSnapshot();
        shareClassSnapshot.setShareClassSid(111);
        shareClassSnapshot.setReportDate(businessDate);
        shareClass.getShareClassSnapshots().add(shareClassSnapshot);

        PortfolioSnapshot snapshot = new PortfolioSnapshot();
        snapshot.setPortfolioSid(222);
        snapshot.setReportDate(businessDate);
        p.getPortfolioSnapshots().add(snapshot);

        data.getPortfolios().add(p);
        fayaDistYieldDataPersistenceService.persistDistributionFundYieldData(data, "100");
    }

    /**
     * Test for method persistDistributionFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void persistDistributionFundYieldDataInvalid() throws Exception {
        fayaDistYieldDataPersistenceService.persistDistributionFundYieldData(null, null);
    }

    /**
     * Test for method getCalculatedDistributionFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void getCalculatedDistributionFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date businessDate = f.parse("2016-12-10");
        assertNotNull(fayaDistYieldDataPersistenceService.getCalculatedDistributionFundYieldData(businessDate));
    }

    /**
     * Test for method getCalculatedDistributionFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedDistributionFundYieldDataInvalid() throws Exception {
        fayaDistYieldDataPersistenceService.getCalculatedDistributionFundYieldData(null);
    }
}
