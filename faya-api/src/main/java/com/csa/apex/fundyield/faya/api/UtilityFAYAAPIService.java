package com.csa.apex.fundyield.faya.api;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Utility FAYA API to get needed data like average or sums for various fields.
 */
@Controller
public interface UtilityFAYAAPIService {

    /**
     * Gets the average of MM 1 Day Dist Yield Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "avgOfMm1DayDistYieldPctForPreviousDays", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BigDecimal getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(@RequestParam(Constants.SHARE_CLASS_SID) long shareClassSid,
            @RequestParam(Constants.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(Constants.NUM_OF_DAYS) int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the sum of Der 1 Day Yield N1A MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "sumOfDer1DayYieldN1AMmPctPreviousDays", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(@RequestParam(Constants.SHARE_CLASS_SID) long shareClassSid,
            @RequestParam(Constants.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(Constants.NUM_OF_DAYS) int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the sum of Der Restate 1 Day Yield MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "sumOfDerRestate1DayYieldMmPctPreviousDays", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(
            @RequestParam(Constants.SHARE_CLASS_SID) long shareClassSid,
            @RequestParam(Constants.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(Constants.NUM_OF_DAYS) int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the avg of MM 7 DayYield Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "avgOfMm7DayYieldPctForPreviousDays", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BigDecimal getAvgOfMnyMkt7DayYieldPctForPreviousDays(@RequestParam(Constants.SHARE_CLASS_SID) long shareClassSid,
            @RequestParam(Constants.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(Constants.NUM_OF_DAYS) int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets sum of Der 7 Day Yield N1A MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "sumOfDer7DayYieldN1AMmPctPreviousDays", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BigDecimal getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(@RequestParam(Constants.SHARE_CLASS_SID) long shareClassSid,
            @RequestParam(Constants.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(Constants.NUM_OF_DAYS) int numOfDays) throws FundAccountingYieldException;
}
