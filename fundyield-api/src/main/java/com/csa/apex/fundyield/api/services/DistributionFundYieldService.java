package com.csa.apex.fundyield.api.services;

import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
 * The interface that defines API to process Distribution Fund Yield data.
 */
public interface DistributionFundYieldService {

    /**
     * Process Distribution Fund data for the business date. This method gets the fund yield data and then process each
     * portfolio first to calculate the data, then to persist it using API.
     * @param userId the user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData processDistributionFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException;

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     * @param userId The user id;
     * @param businessDate -the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException;
}
