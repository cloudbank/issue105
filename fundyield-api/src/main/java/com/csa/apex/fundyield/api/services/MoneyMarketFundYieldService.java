package com.csa.apex.fundyield.api.services;

import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
 * The interface that defines API to process Money Market Fund Yield data.
 */
public interface MoneyMarketFundYieldService {

    /**
     * Process SEC Security data for the business date. This method gets the fund acccounting yield data and then
     * process each portfolio first to calculate the data, then to persist it using API.
     * @param userId The user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData processMoneyMarketFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException;

    /**
     * Gets already calculated Money Market Fund Yield data for the given date.
     * @param userId The user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException;
}
