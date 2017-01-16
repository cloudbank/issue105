package com.csa.apex.fundyield.api.services.impl.utility;

import java.math.BigDecimal;
import java.util.*;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;

/**
 * Utility FAYA API to get needed data like average or sums for various fields.
 */
public interface UtilityFAYAAPIClient {
    /**
     * Gets the average of MM 1 Day Dist Yield Pct for previous days.
     *
     * @param  shareClassSid  the share class id;
     * @param  reportDate  the report date;
     * @param  numOfDays  the number of days;
     * @return  avg value;
     * @throws  IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(long shareClassSid, Date reportDate, int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the sum of Der 1 Day Yield N1A MM Pct for previous days.
     *
     * @param shareClassSid  the share class id;
     * @param reportDate  the report date;
     * @param numOfDays  the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(long shareClassSid, Date reportDate, int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the sum of Der Restate 1 Day Yield MM Pct for previous days.
     *
     * @param shareClassSid  the share class id;
     * @param reportDate  the report date;
     * @param numOfDays the number of days;
     * @return  sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(long shareClassSid, Date reportDate,
                                                                       int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets the avg of MM 7 DayYield Pct for previous days.
     *
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getAvgOfMnyMkt7DayYieldPctForPreviousDays(long shareClassSid, Date reportDate, int numOfDays) throws FundAccountingYieldException;

    /**
     * Gets sum of Der 7 Day Yield N1A MM Pct for previous days.
     *
     * @param shareClassSid  the share class id;
     * @param reportDate the report date;
     * @param numOfDays  the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public BigDecimal getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(long shareClassSid, Date reportDate, int numOfDays) throws FundAccountingYieldException;
}

