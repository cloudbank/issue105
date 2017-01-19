package com.csa.apex.fundyield.faya.api.service;

import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;

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
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws FundAccountingYieldException 
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getFAYAMoneyMarketFundYieldData(Date businessDate) throws FundAccountingYieldException;

    /**
     * Persists the calculated Money Market Fund Yield data.
     *
     * @param fundAccountingYieldData FundAccountingYieldData @RequestParam;
     * @return the result of the execution.
     *
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public boolean persistMoneyMarketFundYieldData(FundAccountingYieldData fundAccountingYieldData, String userId) throws FundAccountingYieldException;

    /**
     * Persists the calculated Distribution Fund Yield data.
     *
     * @param businessDate the SecuritySECDatae @RequestParam;
     * @return the result of the execution.
     *
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(Date businessDate) throws FundAccountingYieldException;

}

