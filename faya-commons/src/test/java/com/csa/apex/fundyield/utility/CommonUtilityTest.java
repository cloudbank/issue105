/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;

/**
 * CommonUtility unit tests.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class CommonUtilityTest {

    /**
     * Test getTradableEntitySnapshot.
     */
    @Test
    public void getTradableEntitySnapshotTest() {
        Instrument instrument = new Instrument();
        Assert.assertNull(CommonUtility.getTradableEntitySnapshot(instrument));

        TradableEntity te = new TradableEntity();
        instrument.setTradableEntities(Arrays.asList(te));
        Assert.assertNull(CommonUtility.getTradableEntitySnapshot(instrument));

        TradableEntitySnapshot tes = new TradableEntitySnapshot();
        te.setTradableEntitySnapshots(Arrays.asList(tes));

        Assert.assertEquals(tes, CommonUtility.getTradableEntitySnapshot(instrument));
    }

    /**
     * Test getRelatedPortfolioHoldings.
     */
    @Test
    public void getRelatedPortfolioHoldingsTest() {
        long tradableEntitySid = 2;

        FundAccountingYieldData data = new FundAccountingYieldData();
        Portfolio portfolio1 = new Portfolio();
        PortfolioHoldingSnapshot phs1 = new PortfolioHoldingSnapshot();
        phs1.setTradableEntity(new TradableEntity());
        phs1.getTradableEntity().setTradableEntitySid(tradableEntitySid);
        portfolio1.setPortfolioHoldings(Arrays.asList(phs1));

        Portfolio portfolio2 = new Portfolio();
        PortfolioHoldingSnapshot phs2 = new PortfolioHoldingSnapshot();
        phs2.setTradableEntity(new TradableEntity());
        phs2.getTradableEntity().setTradableEntitySid(tradableEntitySid);
        portfolio2.setPortfolioHoldings(Arrays.asList(phs2, new PortfolioHoldingSnapshot()));

        data.setPortfolios(Arrays.asList(portfolio1, portfolio2));

        List<PortfolioHoldingSnapshot> phs = CommonUtility.getRelatedPortfolioHoldings(data, tradableEntitySid);

        Assert.assertEquals(2, phs.size());
        Assert.assertEquals(phs1, phs.get(0));
        Assert.assertEquals(phs2, phs.get(1));
    }

    /**
     * Test checkNull.
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNullTest() {
        CommonUtility.checkNull(null, this.getClass().getCanonicalName(), "checkNullTest", "Test object");
    }

    /**
     * Test checkNullConfig.
     */
    @Test(expected = ConfigurationException.class)
    public void checkNullConfigTest() {
        CommonUtility.checkNullConfig(null, this.getClass().getCanonicalName(), "Test object");
    }

    /**
     * Test checkStringConfig.
     */
    @Test(expected = ConfigurationException.class)
    public void checkStringConfigTest() {
        CommonUtility.checkStringConfig("  ", this.getClass().getCanonicalName(), "Test string");
    }

    /**
     * Test checkListConfig.
     */
    @Test(expected = ConfigurationException.class)
    public void checkListConfigTest1() {
        CommonUtility.checkListConfig(new ArrayList<>(), this.getClass().getCanonicalName(), "Test list");
    }

    /**
     * Test checkListConfig.
     */
    @Test(expected = ConfigurationException.class)
    public void checkListConfigTest2() {
        List<String> list = new ArrayList<>();
        list.add(null);
        CommonUtility.checkListConfig(list, this.getClass().getCanonicalName(), "Test list");
    }
}
