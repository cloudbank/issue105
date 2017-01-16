package com.csa.apex.fundyield.faya.api.controllers;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.ToleranceCheckService;
import com.csa.apex.fundyield.seccommons.entities.ToleranceCheckResult;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Spring REST Controller for mock tolerance check. This class is effectively thread safe.
 */
@Controller
public class MockToleranceCheckController implements ToleranceCheckService {

    /**
     * The persistence service to perform operations on customer data. Should be non-null after injection.
     */
    @Autowired
    private ToleranceCheckService toleranceCheckService;

    /**
     * Empty constructor.
     */
    public MockToleranceCheckController() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details. Implementation: Check if the fields are initialized properly. If no, throw the config
     *             exception. See the variable docs for details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(toleranceCheckService, "toleranceCheckService");
    }

    /**
     * Initiates the fund level tolerance check results.
     * @param reportDate the report date ;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public void initiateSECFundLevelBatchToleranceCheck(
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate) {
        CommonUtility.checkNull(reportDate, "reportDate");
        toleranceCheckService.initiateSECFundLevelBatchToleranceCheck(reportDate);
    }

    /**
     * Initiates the MM fund level tolerance check results.
     * @param reportDate the report date ;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public void initiateMoneyMarketFundLevelBatchToleranceCheck(
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate) {
        CommonUtility.checkNull(reportDate, "reportDate");
        toleranceCheckService.initiateMoneyMarketFundLevelBatchToleranceCheck(reportDate);
    }

    /**
     * Initiates the security level batch tolerance check results.
     * @param reportDate the report date ;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public void initiateSecurityLevelBatchToleranceCheck(
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate) {
        CommonUtility.checkNull(reportDate, "reportDate");
        toleranceCheckService.initiateSecurityLevelBatchToleranceCheck(reportDate);
    }

    /**
     * Initiates the Distribution fund level tolerance check results.
     * @param reportDate the report date @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) ;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public void initiateDistributionFundLevelBatchToleranceCheck(Date reportDate) {
        CommonUtility.checkNull(reportDate, "reportDate");
        toleranceCheckService.initiateDistributionFundLevelBatchToleranceCheck(reportDate);
    }

    /**
     * Initiates the secutity level what if tolerance check results.
     * @param reportDate the report date ;
     * @param cusip the cusip.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public void initiateSecurityLevelWhatIfToleranceCheck(Date reportDate, String cusip) {
        CommonUtility.checkNull(reportDate, "reportDate");
        CommonUtility.checkString(cusip, "cusip");
        toleranceCheckService.initiateSecurityLevelWhatIfToleranceCheck(reportDate, cusip);
    }

    /**
     * Initiates the position level what if tolerance check results.
     * @param reportDate the report date ;
     * @param cusip the cusip.
     * @param portfolioHoldingSnapshotSid - the holding snapshot id
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public void initiatePositionLevelWhatIfToleranceCheck(
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, String cusip,
            int portfolioHoldingSnapshotSid) {
        CommonUtility.checkNull(reportDate, "reportDate");
        CommonUtility.checkString(cusip, "cusip");
        toleranceCheckService.initiatePositionLevelWhatIfToleranceCheck(reportDate, cusip, portfolioHoldingSnapshotSid);
    }

    /**
     * Gets the Distribution fund level tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public ToleranceCheckResult getDistributionFundLevelBatchToleranceCheckResult(Date reportDate) {
        return createCheckResult(reportDate);
    }

    /**
     * Gets the fund level tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public ToleranceCheckResult getSECFundLevelBatchToleranceCheckResult(Date reportDate) {
        return createCheckResult(reportDate);
    }

    /**
     * Gets the MM fund level tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public ToleranceCheckResult getMoneyMarketFundLevelBatchToleranceCheckResult(Date reportDate) {
        return createCheckResult(reportDate);
    }

    /**
     * Gets the secutity level tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing. Implementation: ToleranceCheckResult
     *             result = new ToleranceCheckResult(); result.setProcessedWithoutErrors(true); return result;
     */
    @Override
    @LogMethod
    public ToleranceCheckResult getSecurityLevelBatchToleranceCheckResult(Date reportDate) {
        return createCheckResult(reportDate);
    }

    /**
     * Gets the security level what if tolerance check results.
     * @param reportDate the report date ;
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public ToleranceCheckResult getSecurityLevelWhatIfToleranceCheckResult(
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, String cusip) {
        CommonUtility.checkString(cusip, "cusip");
        return createCheckResult(reportDate);
    }

    /**
     * Gets the position level what if tolerance check results.
     * @param reportDate the report date ;
     * @param cusip the cusip.
     * @param portfolioHoldingSnapshotSid the holding snapshot id
     * @return the results of the check
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public ToleranceCheckResult getPositionLevelWhatIfToleranceCheckResult(
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, String cusip,
            int portfolioHoldingSnapshotSid) {
        CommonUtility.checkString(cusip, "cusip");
        return createCheckResult(reportDate);
    }

    /**
     * Create the check result.
     * @param reportDate the report date
     * @return the results of the check
     */
    private ToleranceCheckResult createCheckResult(Date reportDate) {
        CommonUtility.checkNull(reportDate, "reportDate");
        ToleranceCheckResult result = new ToleranceCheckResult();
        result.setProcessedWithoutErrors(true);
        return result;
    }

    public ToleranceCheckService getToleranceCheckService() {
        return toleranceCheckService;
    }

    public void setToleranceCheckService(ToleranceCheckService toleranceCheckService) {
        this.toleranceCheckService = toleranceCheckService;
    }
}
