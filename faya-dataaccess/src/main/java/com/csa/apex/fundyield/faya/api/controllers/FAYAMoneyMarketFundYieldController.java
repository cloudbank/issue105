package com.csa.apex.fundyield.faya.api.controllers;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.FAYAMoneyMarketFundYieldService;
import com.csa.apex.fundyield.faya.api.service.FAYAMoneyMarketDataPersistenceService;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Spring REST Controller for FAYA Distribution Yield fund data operations. This class is effectively thread safe.
 */
@Controller
public class FAYAMoneyMarketFundYieldController implements FAYAMoneyMarketFundYieldService {

    /**
     * The persistence service to perform operations on customer data. Should be non-null after injection.
     */
    @Autowired
    private FAYAMoneyMarketDataPersistenceService fayaMoneyMarketDataPersistenceService;

    /**
     * Empty constructor.
     */
    public FAYAMoneyMarketFundYieldController() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(fayaMoneyMarketDataPersistenceService, this.getClass().getCanonicalName(), Constants.FAYA_MONEY_MARKET_DATA_PERSISTENCE_SERVICE);
    }

    /**
     * Gets Money Market data for the business date.
     * @param userId The user id passed in header.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYAMoneyMarketFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getFAYAMoneyMarketFundYieldData", Constants.USER_ID);
    	CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getFAYAMoneyMarketFundYieldData", Constants.BUSINESS_DATE);
        return fayaMoneyMarketDataPersistenceService.getFAYAMoneyMarketFundYieldData(userId, businessDate);
    }

    /**
     * Persists the calculated Money Market Fund Yield data.
     * @param userId The user id passed in header.
     * @param fundAccountingYieldData FundAccountingYieldData
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistMoneyMarketFundYieldData(String userId, FundAccountingYieldData fundAccountingYieldData) throws FundAccountingYieldException {
        CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), "persistMoneyMarketFundYieldData", Constants.FUND_ACCOUNTING_YIELD_DATA);
        CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "persistMoneyMarketFundYieldData", Constants.USER_ID);
        return fayaMoneyMarketDataPersistenceService.persistMoneyMarketFundYieldData(userId, fundAccountingYieldData);
    }

    /**
     * Gets already calculated Money Market Fund Yield data for the given date.
     * @param userId The user id.
     * @param businessDate the business date;
     * @returns FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getCalculatedMoneyMarketFundYieldData", Constants.USER_ID);
    	CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getCalculatedMoneyMarketFundYieldData", Constants.BUSINESS_DATE);
        return fayaMoneyMarketDataPersistenceService.getCalculatedMoneyMarketFundYieldData(userId, businessDate);
    }

    public FAYAMoneyMarketDataPersistenceService getFayaMoneyMarketDataPersistenceService() {
        return fayaMoneyMarketDataPersistenceService;
    }

    public void setFayaMoneyMarketDataPersistenceService(
            FAYAMoneyMarketDataPersistenceService fayaMoneyMarketDataPersistenceService) {
        this.fayaMoneyMarketDataPersistenceService = fayaMoneyMarketDataPersistenceService;
    }

}
