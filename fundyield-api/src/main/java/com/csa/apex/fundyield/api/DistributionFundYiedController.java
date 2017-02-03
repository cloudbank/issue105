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

import com.csa.apex.fundyield.api.services.DistributionFundYieldService;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.ApplicationConstant;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Spring REST Controller for distribution fund yield data operations. This class is effectively thread safe.
 */
@Controller
public class DistributionFundYiedController {

    /**
     * The service used to perform fund yield data processing. Will be injected by Spring. Required, non-null after
     * injection.
     */
    @Autowired
    private DistributionFundYieldService distributionFundYieldService;

    /**
     * Empty constructor.
     */
    public DistributionFundYiedController() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details. Implementation: Check if the fields are initialized properly. If no, throw the config
     *             exception. See the variable docs for details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(distributionFundYieldService, "distributionFundYieldService");
    }

    /**
     * Gets Distribution Fund data for the business date. In service data is obtained from customer REST API, calculated
     * and then persisted using customer REST API. using API.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "distributionFundYieldData", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FundAccountingYieldData getDistributionFundYieldData(
            @RequestParam(ApplicationConstant.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, ApplicationConstant.BUSINESS_DATE);
        return distributionFundYieldService.processDistributionFundYieldData(businessDate);
    }

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "calculatedDistributionFundYieldData", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(
            @RequestParam(ApplicationConstant.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, ApplicationConstant.BUSINESS_DATE);
        return distributionFundYieldService.getCalculatedDistributionFundYieldData(businessDate);
    }

    public DistributionFundYieldService getDistributionFundYieldService() {
        return distributionFundYieldService;
    }

    public void setDistributionFundYieldService(DistributionFundYieldService distributionFundYieldService) {
        this.distributionFundYieldService = distributionFundYieldService;
    }

}
