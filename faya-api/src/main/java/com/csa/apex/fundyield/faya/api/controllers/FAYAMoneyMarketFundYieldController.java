package com.csa.apex.fundyield.faya.api.controllers;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.csa.apex.fundyield.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.FAYAMoneyMarketFundYieldService;
import com.csa.apex.fundyield.faya.api.service.FAYAMoneyMarketDataPersistenceService;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
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
        CommonUtility.checkNullConfig(fayaMoneyMarketDataPersistenceService, Constants.FAYA_MONEY_MARKET_DATA_PERSISTENCE_SERVICE);
    }

    /**
     * Gets Money Market data for the business date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYAMoneyMarketFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, Constants.BUSINESS_DATE);
        return fayaMoneyMarketDataPersistenceService.getFAYAMoneyMarketFundYieldData(businessDate);
    }

    /**
     * Persists the calculated Money Market Fund Yield data.
     * @param fundAccountingYieldData FundAccountingYieldData
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistMoneyMarketFundYieldData(FundAccountingYieldData fundAccountingYieldData,
            HttpServletRequest request) throws FundAccountingYieldException {
        CommonUtility.checkNull(fundAccountingYieldData, Constants.FUND_ACCOUNTING_YIELD_DATA);
        CommonUtility.checkNull(request, Constants.REQUEST);
        HttpSession session = request.getSession();
        String currentUserId = (String) session.getAttribute(Constants.CURRENT_USER_ID);
        return fayaMoneyMarketDataPersistenceService.persistMoneyMarketFundYieldData(fundAccountingYieldData,
                currentUserId);
    }

    /**
     * Gets already calculated Money Market Fund Yield data for the given date.
     * @param businessDate the business date;
     * @returns FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, Constants.BUSINESS_DATE);
        return fayaMoneyMarketDataPersistenceService.getCalculatedMoneyMarketFundYieldData(businessDate);
    }

    public FAYAMoneyMarketDataPersistenceService getFayaMoneyMarketDataPersistenceService() {
        return fayaMoneyMarketDataPersistenceService;
    }

    public void setFayaMoneyMarketDataPersistenceService(
            FAYAMoneyMarketDataPersistenceService fayaMoneyMarketDataPersistenceService) {
        this.fayaMoneyMarketDataPersistenceService = fayaMoneyMarketDataPersistenceService;
    }

}
