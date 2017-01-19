package com.csa.apex.fundyield.faya.api.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
     * The autowired jdbcTemplate. Should be non-null after injection.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        CommonUtility.checkNullConfig(jdbcTemplate, "jdbcTemplate");
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
    public BigDecimal getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        return callStoredProcedure("AVG_MM1", shareClassSid, reportDate, numOfDays);
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
    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        return callStoredProcedure("SUM_D1", shareClassSid, reportDate, numOfDays);
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
    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(long shareClassSid, Date reportDate,
            int numOfDays) throws FundAccountingYieldException {
        return callStoredProcedure("SUM_DR1", shareClassSid, reportDate, numOfDays);
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
    public BigDecimal getAvgOfMnyMkt7DayYieldPctForPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        return callStoredProcedure("AVG_MM7", shareClassSid, reportDate, numOfDays);
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
    public BigDecimal getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        return callStoredProcedure("SUM_D7", shareClassSid, reportDate, numOfDays);
    }

    /**
     * Call stored procedure.
     * @param spName the stored procedure name;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    private BigDecimal callStoredProcedure(String spName, long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        CommonUtility.checkNumber(shareClassSid, "shareClassSid");
        CommonUtility.checkNull(reportDate, "reportDate");
        CommonUtility.checkNumber(numOfDays, "numOfDays");
        try {
            DateTime businessDateTime = new DateTime(reportDate).withTimeAtStartOfDay();
            Date endDate = businessDateTime.plusDays(numOfDays).withTimeAtStartOfDay().toDate();
            Map<String, Object> inParamMap = new HashMap<String, Object>();
            inParamMap.put("p_sid", shareClassSid);
            inParamMap.put("start_date", reportDate);
            inParamMap.put("end_date", endDate);
            Map<String, Object> result = FAYAPersistenceHelper.callStoredProcedure(spName, jdbcTemplate, inParamMap);
            return (BigDecimal) result.values().toArray()[0];
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Getter jdbcTemplate.
     * @return the jdbc template
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Setter jdbcTemplate.
     * @param jdbcTemplate the jdbc template to be set
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
