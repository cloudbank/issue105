package com.csa.apex.fundyield.faya.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Spring REST Interface for customer data operations. Update: New methods for MM and Distribution Fund data were added.
 * Annotations:
 * @Controller
 */
@Controller
public interface FAYADistributiontFundYieldService {

    /**
     * Gets Distribution Fund data for the business date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "FAYADistributionFundYieldData", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FundAccountingYieldData getFAYADistributionFundYieldData(
            @RequestParam(Constants.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
            throws FundAccountingYieldException;

    /**
     * Persists the calculated Distribution Fund Yield data.
     * @param fundAccountingYieldData FundAccountingYieldData
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "calculatedDistributionFundYieldPortfolio", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public boolean persistDistributionFundYieldData(@RequestBody FundAccountingYieldData fundAccountingYieldData,
            HttpServletRequest request) throws FundAccountingYieldException;

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "calculatedFAYADistributionFundYieldData", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(
            @RequestParam(Constants.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
            throws FundAccountingYieldException;
}
