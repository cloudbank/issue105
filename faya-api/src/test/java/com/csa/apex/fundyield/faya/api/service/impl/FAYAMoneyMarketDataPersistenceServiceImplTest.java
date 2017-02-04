/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.service.impl;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.csa.apex.fundyield.faya.api.service.FAYAMoneyMarketDataPersistenceService;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;

/**
 * Test class for the FAYAMoneyMarketDataPersistenceServiceImpl.
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
public class FAYAMoneyMarketDataPersistenceServiceImplTest {

    /**
     * FAYAMoneyMarketDataPersistenceService object.
     */
    @Autowired
    private FAYAMoneyMarketDataPersistenceService fayaMoneyMarketDataPersistenceService;

    /**
     * Test for method getFAYAMoneyMarketFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void getFAYAMoneyMarketFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date businessDate = f.parse("2016-12-20");
        assertNotNull(fayaMoneyMarketDataPersistenceService.getFAYAMoneyMarketFundYieldData(businessDate));
    }

    /**
     * Test for method getFAYAMoneyMarketFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getFAYAMoneyMarketFundYieldDataInvalid() throws Exception {
        fayaMoneyMarketDataPersistenceService.getFAYAMoneyMarketFundYieldData(null);
    }

    /**
     * Test for method persistMoneyMarketFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void persistMoneyMarketFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
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
        fayaMoneyMarketDataPersistenceService.persistMoneyMarketFundYieldData(data, "100");
    }

    /**
     * Test for method persistMoneyMarketFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void persistMoneyMarketFundYieldDataInvalid() throws Exception {
        fayaMoneyMarketDataPersistenceService.persistMoneyMarketFundYieldData(null, null);
    }

    /**
     * Test for method getCalculatedMoneyMarketFundYieldData.
     * @throws Exception to JUnit
     */
    @Test
    public void getCalculatedMoneyMarketFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date businessDate = f.parse("2016-12-10");
        assertNotNull(fayaMoneyMarketDataPersistenceService.getCalculatedMoneyMarketFundYieldData(businessDate));
    }

    /**
     * Test for method getCalculatedMoneyMarketFundYieldData with invalid data.
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedMoneyMarketFundYieldDataInvalid() throws Exception {
        fayaMoneyMarketDataPersistenceService.getCalculatedMoneyMarketFundYieldData(null);
    }
}
