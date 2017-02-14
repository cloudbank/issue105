package com.csa.apex.fundyield.faya.api.service;

import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
 * Persistence interface defining operations for customer data.
 * 
 * Update:
 * New methods for MM and Distribution Fund data were added. Also name was changed to "FAYA" from "Customer"
 */
public interface FAYAMoneyMarketDataPersistenceService {

    /**
     * Gets Money Market data for the business date. 
     * 
     * @param userId The user id
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws FundAccountingYieldException 
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getFAYAMoneyMarketFundYieldData(String userId, Date businessDate) throws FundAccountingYieldException;

    /**
     * Persists the calculated Money Market Fund Yield data.
     *
     * @param userId The user id.
     * @param fundAccountingYieldData FundAccountingYieldData @RequestParam;
     * @param userId The user id from user context.
     * @return the result of the execution.
     *
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public boolean persistMoneyMarketFundYieldData(String userId, FundAccountingYieldData fundAccountingYieldData) throws FundAccountingYieldException;

    /**
     * Persists the calculated Distribution Fund Yield data.
     * 
     * @param userId The user id.
     * @param businessDate the SecuritySECDatae @RequestParam;
     * @return the result of the execution.
     *
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(String userId, Date businessDate) throws FundAccountingYieldException;

}

