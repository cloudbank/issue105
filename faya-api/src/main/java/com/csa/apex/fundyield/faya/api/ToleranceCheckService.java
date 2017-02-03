package com.csa.apex.fundyield.faya.api;

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
import com.csa.apex.fundyield.seccommons.entities.ToleranceCheckResult;
import com.csa.apex.fundyield.utility.ApplicationConstant;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Tolerance check service.
 */
@Controller
public interface ToleranceCheckService {

    /**
     * Initiates the SEC fund level tolerance check results.
     * @param reportDate the report date
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "secFundLevelBatchToleranceCheck", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void initiateSECFundLevelBatchToleranceCheck(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Gets the fund level tolerance check results.
     * @param reportDate the report date
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "secFundLevelBatchToleranceCheck", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ToleranceCheckResult getSECFundLevelBatchToleranceCheckResult(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Initiates the MM fund level tolerance check results.
     * @param reportDate the report date
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "moneyMarketFundLevelBatchToleranceCheck", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void initiateMoneyMarketFundLevelBatchToleranceCheck(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Gets the MM fund level tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "moneyMarketFundLevelBatchToleranceCheck", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ToleranceCheckResult getMoneyMarketFundLevelBatchToleranceCheckResult(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Initiates the Distribution fund level tolerance check results.
     * @param reportDate the report date ;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "distributionFundLevelBatchToleranceCheck", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void initiateDistributionFundLevelBatchToleranceCheck(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Gets the Distribution fund level tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "distributionFundLevelBatchToleranceCheck", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ToleranceCheckResult getDistributionFundLevelBatchToleranceCheckResult(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Initiates the secutity level batch tolerance check results.
     * @param reportDate the report date ;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "securityLevelBatchToleranceCheck", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void initiateSecurityLevelBatchToleranceCheck(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Gets the secutity level tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "securityLevelBatchToleranceCheck", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ToleranceCheckResult getSecurityLevelBatchToleranceCheckResult(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate);

    /**
     * Initiates the secutity level what if tolerance check results.
     * @param reportDate the report date ;
     * @param cusip the cusip.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "securityLevelWhatIfToleranceCheck", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void initiateSecurityLevelWhatIfToleranceCheck(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(ApplicationConstant.CUSIP) String cusip);

    /**
     * Gets the secutity level what if tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "securityLevelWhatIfToleranceCheck", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ToleranceCheckResult getSecurityLevelWhatIfToleranceCheckResult(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(ApplicationConstant.CUSIP) String cusip);

    /**
     * Initiates the position level what if tolerance check results.
     * @param reportDate the report date ;
     * @param cusip the cusip.
     * @param portfolioHoldingSnapshotSid the holding snapshot id
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "positionLevelWhatIfToleranceCheck", method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void initiatePositionLevelWhatIfToleranceCheck(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(ApplicationConstant.CUSIP) String cusip,
            @RequestParam("portfolioHoldingSnapshotSid") int portfolioHoldingSnapshotSid);

    /**
     * Gets the position level what if tolerance check results.
     * @param reportDate the report date ;
     * @param cusip the cusip.
     * @param portfolioHoldingSnapshotSid the holding snapshot id
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @RequestMapping(value = "positionLevelWhatIfToleranceCheck", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ToleranceCheckResult getPositionLevelWhatIfToleranceCheckResult(
            @RequestParam(ApplicationConstant.REPORT_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate,
            @RequestParam(ApplicationConstant.CUSIP) String cusip,
            @RequestParam("portfolioHoldingSnapshotSid") int portfolioHoldingSnapshotSid);
}
