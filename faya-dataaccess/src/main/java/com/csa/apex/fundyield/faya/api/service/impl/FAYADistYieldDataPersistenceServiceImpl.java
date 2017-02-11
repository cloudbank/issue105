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
     * @param userId The user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws FundAccountingYieldException
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYADistributionFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getFAYADistributionFundYieldData", Constants.USER_ID);
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getFAYADistributionFundYieldData", Constants.BUSINESS_DATE);
        return retrieveFundAccountingYieldData(userId, businessDate);
    }

    /**
     * Persists the calculated Distribution Fund Yield data.
     * @param userId The user id.
     * @param fundAccountingYieldData FundAccountingYieldData.
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistDistributionFundYieldData(String userId, FundAccountingYieldData fundAccountingYieldData)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "persistDistributionFundYieldData", Constants.USER_ID);
        CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), "persistDistributionFundYieldData", Constants.FUND_ACCOUNTING_YIELD_DATA);
        storedProcedureHelper.saveFAYAPortfolioData(userId, fundAccountingYieldData);
        return true;
    }

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     * @param userId The user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getCalculatedDistributionFundYieldData", Constants.USER_ID);
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getCalculatedDistributionFundYieldData", Constants.BUSINESS_DATE);
        return retrieveFundAccountingYieldData(userId, businessDate);
    }

    /**
     * Gets FundAccountingYieldData from persistence.
     * @param userId The user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    private FundAccountingYieldData retrieveFundAccountingYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "retrieveFundAccountingYieldData", Constants.BUSINESS_DATE);
        CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "retrieveFundAccountingYieldData", Constants.USER_ID);
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
