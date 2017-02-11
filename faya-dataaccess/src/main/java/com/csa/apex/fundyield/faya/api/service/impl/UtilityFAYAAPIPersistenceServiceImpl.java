package com.csa.apex.fundyield.faya.api.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.csa.apex.fundyield.utility.Constants;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.service.UtilityFAYAAPIPersistenceService;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Created by topcoder on 12/28/16.
 */
@Service
public class UtilityFAYAAPIPersistenceServiceImpl implements UtilityFAYAAPIPersistenceService {

    /**
     * The auto wired storedProcedure.
     */
    @Autowired
    private StoredProcedure storedProcedure;

    /**
     * Empty constructor.
     */
    public UtilityFAYAAPIPersistenceServiceImpl() {

    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(storedProcedure, this.getClass().getCanonicalName(), Constants.STORED_PROCEDURE);
    }

    /**
     * Gets the average of MM 1 Day Dist Yield Pct for previous days.
     * @param userId The user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(String userId, long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        Map<String,Object> params = buildParameters(userId, shareClassSid, reportDate, numOfDays);
        try {
            storedProcedure.avgMM1(params);
        } catch(Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
        return (BigDecimal) params.get("s");
    }

    /**
     * Gets the sum of Der 1 Day Yield N1A MM Pct for previous days.
     * @param userId The user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(String userId, long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        Map<String,Object> params = buildParameters(userId, shareClassSid, reportDate, numOfDays);
        try {
            storedProcedure.sumD1(params);
        } catch(Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
        return (BigDecimal) params.get("s");
    }

    /**
     * Gets the sum of Der Restate 1 Day Yield MM Pct for previous days.
     * @param userId The user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(String userId, long shareClassSid, Date reportDate,
            int numOfDays) throws FundAccountingYieldException {
        Map<String,Object> params = buildParameters(userId, shareClassSid, reportDate, numOfDays);
        try {
            storedProcedure.sumDR1(params);
        } catch(Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
        return (BigDecimal) params.get("s");
    }

    /**
     * Gets the avg of MM 7 DayYield Pct for previous days.
     * @param userId the user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getAvgOfMnyMkt7DayYieldPctForPreviousDays(String userId, long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        Map<String,Object> params = buildParameters(userId, shareClassSid, reportDate, numOfDays);
        try {
            storedProcedure.avgMM7(params);
        } catch(Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
        return (BigDecimal) params.get("s");
    }

    /**
     * Gets sum of Der 7 Day Yield N1A MM Pct for previous days.
     * @param userId The user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public BigDecimal getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(String userId, long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        Map<String,Object> params = buildParameters(userId, shareClassSid, reportDate, numOfDays);
        try {
            storedProcedure.sumD7(params);
        } catch(Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
        return (BigDecimal) params.get("s");
    }

    /**
     * Build parameters to Map.
     * @param userId teh user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    private Map<String,Object> buildParameters(String userId, long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "callStoredProcedure", Constants.USER_ID);
        CommonUtility.checkNumber(shareClassSid, this.getClass().getCanonicalName(), "callStoredProcedure", Constants.SHARE_CLASS_SID);
        CommonUtility.checkNull(reportDate, this.getClass().getCanonicalName(), "callStoredProcedure", Constants.REPORT_DATE);
        CommonUtility.checkNumber(numOfDays, this.getClass().getCanonicalName(), "callStoredProcedure", Constants.NUM_OF_DAYS);

        DateTime businessDateTime = new DateTime(reportDate).withTimeAtStartOfDay();
        Date endDate = businessDateTime.plusDays(numOfDays).withTimeAtStartOfDay().toDate();
        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("p_sid", shareClassSid);
        inParamMap.put("start_date", reportDate);
        inParamMap.put("end_date", endDate);
        return inParamMap;
    }

}
