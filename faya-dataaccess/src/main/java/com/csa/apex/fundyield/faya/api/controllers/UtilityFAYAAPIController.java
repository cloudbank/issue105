package com.csa.apex.fundyield.faya.api.controllers;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.UtilityFAYAAPIService;
import com.csa.apex.fundyield.faya.api.service.UtilityFAYAAPIPersistenceService;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Spring REST Controller for FAYA utility operations. This class is effectively thread safe.
 */
@Controller
public class UtilityFAYAAPIController implements UtilityFAYAAPIService {

    /**
     * The persistence service to perform operations on customer data. Should be non-null after injection.
     */
    @Autowired
    private UtilityFAYAAPIPersistenceService utilityFAYAAPIPersistenceService;

    /**
     * Empty constructor.
     */
    public UtilityFAYAAPIController() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details. Implementation: Check if the fields are initialized properly. If no, throw the config
     *             exception. See the variable docs for details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(utilityFAYAAPIPersistenceService,  this.getClass().getCanonicalName(), Constants.UTILITY_FAYA_API_PERSISTENCE_SERVICE);
    }

    /**
     * Gets the average of MM 1 Day Dist Yield Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(long shareClassSid,
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        CommonUtility.checkNumber(shareClassSid, this.getClass().getCanonicalName(), "getAvgOfMnyMkt1DayDistYieldPctForPreviousDays", Constants.SHARE_CLASS_SID);
        CommonUtility.checkNull(reportDate, this.getClass().getCanonicalName(), "getAvgOfMnyMkt1DayDistYieldPctForPreviousDays", Constants.REPORT_DATE);
        CommonUtility.checkNumber(numOfDays, this.getClass().getCanonicalName(), "getAvgOfMnyMkt1DayDistYieldPctForPreviousDays", Constants.NUM_OF_DAYS);
        return utilityFAYAAPIPersistenceService.getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(shareClassSid, reportDate,
                numOfDays);
    }

    /**
     * Gets the sum of Der 1 Day Yield N1A MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(long shareClassSid,
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        CommonUtility.checkNumber(shareClassSid, this.getClass().getCanonicalName(), "getSumOfDer1DayYieldN1AMnyMktPctPreviousDays", Constants.SHARE_CLASS_SID);
        CommonUtility.checkNull(reportDate, this.getClass().getCanonicalName(), "getSumOfDer1DayYieldN1AMnyMktPctPreviousDays", Constants.REPORT_DATE);
        CommonUtility.checkNumber(numOfDays, this.getClass().getCanonicalName(), "getSumOfDer1DayYieldN1AMnyMktPctPreviousDays", Constants.NUM_OF_DAYS);
        return utilityFAYAAPIPersistenceService.getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(shareClassSid, reportDate,
                numOfDays);
    }

    /**
     * Gets the sum of Der Restate 1 Day Yield MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(long shareClassSid,
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        CommonUtility.checkNumber(shareClassSid, this.getClass().getCanonicalName(), "getSumOfDerRestate1DayYieldMnyMktPctPreviousDays", Constants.SHARE_CLASS_SID);
        CommonUtility.checkNull(reportDate, this.getClass().getCanonicalName(), "getSumOfDerRestate1DayYieldMnyMktPctPreviousDays", Constants.REPORT_DATE);
        CommonUtility.checkNumber(numOfDays, this.getClass().getCanonicalName(), "getSumOfDerRestate1DayYieldMnyMktPctPreviousDays", Constants.NUM_OF_DAYS);
        return utilityFAYAAPIPersistenceService.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(shareClassSid,
                reportDate, numOfDays);
    }

    /**
     * Gets the avg of MM 7 DayYield Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getAvgOfMnyMkt7DayYieldPctForPreviousDays(long shareClassSid,
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        CommonUtility.checkNumber(shareClassSid, this.getClass().getCanonicalName(), "getAvgOfMnyMkt7DayYieldPctForPreviousDays", Constants.SHARE_CLASS_SID);
        CommonUtility.checkNull(reportDate, this.getClass().getCanonicalName(), "getAvgOfMnyMkt7DayYieldPctForPreviousDays", Constants.REPORT_DATE);
        CommonUtility.checkNumber(numOfDays, this.getClass().getCanonicalName(), "getAvgOfMnyMkt7DayYieldPctForPreviousDays", Constants.NUM_OF_DAYS);
        return utilityFAYAAPIPersistenceService.getAvgOfMnyMkt7DayYieldPctForPreviousDays(shareClassSid, reportDate,
                numOfDays);
    }

    /**
     * Gets sum of Der 7 Day Yield N1A MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(long shareClassSid,
            @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        CommonUtility.checkNumber(shareClassSid, this.getClass().getCanonicalName(), "getSumOfDer7DayYieldN1AMnyMktPctPreviousDays", Constants.SHARE_CLASS_SID);
        CommonUtility.checkNull(reportDate, this.getClass().getCanonicalName(), "getSumOfDer7DayYieldN1AMnyMktPctPreviousDays", Constants.REPORT_DATE);
        CommonUtility.checkNumber(numOfDays, this.getClass().getCanonicalName(), "getSumOfDer7DayYieldN1AMnyMktPctPreviousDays", Constants.NUM_OF_DAYS);
        return utilityFAYAAPIPersistenceService.getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(shareClassSid, reportDate,
                numOfDays);
    }

    public UtilityFAYAAPIPersistenceService getUtilityFAYAAPIPersistenceService() {
        return utilityFAYAAPIPersistenceService;
    }

    public void setUtilityFAYAAPIPersistenceService(UtilityFAYAAPIPersistenceService utilityFAYAAPIPersistenceService) {
        this.utilityFAYAAPIPersistenceService = utilityFAYAAPIPersistenceService;
    }

}
