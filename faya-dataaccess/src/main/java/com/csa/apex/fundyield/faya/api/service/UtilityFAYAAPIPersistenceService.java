package com.csa.apex.fundyield.faya.api.service;

import java.math.BigDecimal;
import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;

/**
 * Created by topcoder on 12/28/16.
 */
public interface UtilityFAYAAPIPersistenceService {

    /**
     * Gets the average of MM 1 Day Dist Yield Pct for previous days.
     *
     * @param userId The user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return  avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(String userId, long shareClassSid, Date reportDate, int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the sum of Der 1 Day Yield N1A MM Pct for previous days.
     *
     * @param userId the user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(String userId, long shareClassSid, Date reportDate, int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the sum of Der Restate 1 Day Yield MM Pct for previous days.
     *
     * @param userId the user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return  sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
	public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(String userId, long shareClassSid,
			Date reportDate, int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the avg of MM 7 DayYield Pct for previous days.
     *
     * @param userId The user id;
     * @param  shareClassSid the share class id;
     * @param  reportDate the report date;
     * @param  numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
	public BigDecimal getAvgOfMnyMkt7DayYieldPctForPreviousDays(String userId, long shareClassSid, Date reportDate,
			int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets sum of Der 7 Day Yield N1A MM Pct for previous days.
     *
     * @param userId The user id;
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(String userId, long shareClassSid, Date reportDate, int numOfDays) throws FundAccountingYieldException;
}
