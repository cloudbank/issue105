package com.csa.apex.fundyield.faya.api.service;

import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
 * Created by topcoder on 12/28/16.
 */
public interface FAYADistYieldDataPersistenceService {
    /**
     * Gets Distribution Fund data for the business date.
     *
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws  IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getFAYADistributionFundYieldData(Date businessDate) throws FundAccountingYieldException;
    /**
     * Persists the calculated Distribution Fund Yield data.
     *
     * @param fundAccountingYieldData FundAccountingYieldData @RequestParam;
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public boolean persistDistributionFundYieldData(FundAccountingYieldData fundAccountingYieldData, String userId) throws FundAccountingYieldException;

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     *
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(Date businessDate) throws FundAccountingYieldException;
}
