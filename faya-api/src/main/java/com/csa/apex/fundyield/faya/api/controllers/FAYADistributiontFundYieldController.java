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
import com.csa.apex.fundyield.faya.api.FAYADistributiontFundYieldService;
import com.csa.apex.fundyield.faya.api.service.FAYADistYieldDataPersistenceService;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Spring REST Controller for FAYA Distribution Yield fund data operations. This class is effectively thread safe.
 */
@Controller
public class FAYADistributiontFundYieldController implements FAYADistributiontFundYieldService {

    /**
     * The persistence service to perform operations on customer data. Should be non-null after injection.
     */
    @Autowired
    private FAYADistYieldDataPersistenceService fayaDistYieldDataPersistenceService;

    /**
     * Empty constructor.
     */
    public FAYADistributiontFundYieldController() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details. Implementation: Check if the fields are initialized properly. If no, throw the config
     *             exception. See the variable docs for details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(fayaDistYieldDataPersistenceService, Constants.FAYA_DIST_YIELD_DATA_PERSISTENCE_SERVICE);
    }

    /**
     * Gets Distribution Fund data for the business date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYADistributionFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, Constants.BUSINESS_DATE);
        return fayaDistYieldDataPersistenceService.getFAYADistributionFundYieldData(businessDate);
    }

    /**
     * Persists the calculated Distribution Fund Yield data.
     * @param fundAccountingYieldData FundAccountingYieldData
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistDistributionFundYieldData(FundAccountingYieldData fundAccountingYieldData,
            HttpServletRequest request) throws FundAccountingYieldException {
        CommonUtility.checkNull(fundAccountingYieldData, Constants.FUND_ACCOUNTING_YIELD_DATA);
        CommonUtility.checkNull(request, Constants.REQUEST);
        HttpSession session = request.getSession();
        String currentUserId = (String) session.getAttribute(Constants.CURRENT_USER_ID);
        return fayaDistYieldDataPersistenceService.persistDistributionFundYieldData(fundAccountingYieldData,
                currentUserId);
    }

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     * @param businessDate - the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, Constants.BUSINESS_DATE);
        return fayaDistYieldDataPersistenceService.getCalculatedDistributionFundYieldData(businessDate);
    }

    public FAYADistYieldDataPersistenceService getFayaDistYieldDataPersistenceService() {
        return fayaDistYieldDataPersistenceService;
    }

    public void setFayaDistYieldDataPersistenceService(
            FAYADistYieldDataPersistenceService fayaDistYieldDataPersistenceService) {
        this.fayaDistYieldDataPersistenceService = fayaDistYieldDataPersistenceService;
    }

}
