package com.csa.apex.fundyield.api;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csa.apex.fundyield.api.services.MoneyMarketFundYieldService;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Spring REST Controller for customer data operations. This class is effectively thread safe. Update: new methods for
 * MM and distribution funds are added.
 */
@Controller
public class MoneyMarketFundYieldController {

    /**
     * The service used to perform money market yield data processing. Will be injected by Spring. Required, non-null
     * after injection.
     */
    @Autowired
    private MoneyMarketFundYieldService moneyMarketFundYieldService;

    /**
     * Empty constructor.
     */
    public MoneyMarketFundYieldController() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details. Implementation: Check if the fields are initialized properly. If no, throw the config
     *             exception. See the variable docs for details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(moneyMarketFundYieldService, this.getClass().getCanonicalName(), "moneyMarketFundYieldService");
    }

    /**
     * Gets Money Market data for the business date. In service data is obtained from customer REST API, calculated and
     * then persisted using customer REST API. using API.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "moneyMarketFundYieldData", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FundAccountingYieldData getMoneyMarketFundYieldData(
            @RequestParam(Constants.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getMoneyMarketFundYieldData", Constants.BUSINESS_DATE);
        return moneyMarketFundYieldService.processMoneyMarketFundYieldData(businessDate);
    }

    /**
     * Gets already calculated Money Market Fund Yield data for the given date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "calculatedMoneyMarketFundYieldData", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(
            @RequestParam(Constants.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getCalculatedMoneyMarketFundYieldData", Constants.BUSINESS_DATE);
        return moneyMarketFundYieldService.getCalculatedMoneyMarketFundYieldData(businessDate);
    }

    public MoneyMarketFundYieldService getMoneyMarketFundYieldService() {
        return moneyMarketFundYieldService;
    }

    public void setMoneyMarketFundYieldService(MoneyMarketFundYieldService moneyMarketFundYieldService) {
        this.moneyMarketFundYieldService = moneyMarketFundYieldService;
    }

}
