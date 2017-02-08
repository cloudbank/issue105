package com.csa.apex.fundyield.faya.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.csa.apex.fundyield.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.service.FAYADistYieldDataPersistenceService;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;


@Service
public class FAYADistYieldDataPersistenceServiceImpl implements FAYADistYieldDataPersistenceService {

    /**
     * The auto wired storedProcedure.
     */
    @Autowired
    private StoredProcedure storedProcedure;

    /**
     * The auto wired StoredProcedureHelper.
     */
    @Autowired
    private StoredProcedureHelper storedProcedureHelper;

    /**
     * Empty constructor.
     */
    public FAYADistYieldDataPersistenceServiceImpl() {

    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(storedProcedure, this.getClass().getCanonicalName(), "storedProcedure");
    }

    /**
     * Gets Distribution Fund data for the business date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws FundAccountingYieldException
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYADistributionFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        return retrieveFundAccountingYieldData(businessDate);
    }

    /**
     * Persists the calculated Distribution Fund Yield data.
     * @param fundAccountingYieldData FundAccountingYieldData.
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistDistributionFundYieldData(FundAccountingYieldData fundAccountingYieldData, String userId)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), "persistDistributionFundYieldData", Constants.FUND_ACCOUNTING_YIELD_DATA);
        storedProcedureHelper.saveFAYAPortfolioData(fundAccountingYieldData);
        return true;
    }

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        return retrieveFundAccountingYieldData(businessDate);
    }

    /**
     * Gets FundAccountingYieldData from persistence.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    private FundAccountingYieldData retrieveFundAccountingYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "retrieveFundAccountingYieldData", Constants.BUSINESS_DATE);
        try {
            Map<String,Object> param1 = new HashMap<String,Object>(){{ put("business_date", businessDate); }};
            storedProcedure.queryDYPortfolio(param1);
            List<Portfolio> portfolios = (List<Portfolio>) param1.get("rs");

            Map<String,Object> param2 = new HashMap<String,Object>(){{ put("business_date", businessDate); }};
            storedProcedure.queryDYInstrument(param2);
            List<Instrument> instruments = (List<Instrument>) param2.get("rs");

            FundAccountingYieldData data = new FundAccountingYieldData();
            data.setBusinessDate(businessDate);
            data.setReportDate(businessDate);
            data.setPortfolios(portfolios);
            data.setInstruments(instruments);
            return data;
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

}
