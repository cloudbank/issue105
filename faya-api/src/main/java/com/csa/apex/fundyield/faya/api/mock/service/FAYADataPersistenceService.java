/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.mock.service;

import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;

/**
 * Persistence interface defining operations for FAYA SEC security data.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public interface FAYADataPersistenceService {

    /**
     * Gets the customer SEC Security.
     * 
     * @param businessDate
     *        the business date
     * @return the list of customer security SEC data
     * @throws FundAccountingYieldException
     *         in case any error occurred during processing
     * @throws IllegalArgumentException
     *         in case the input is invalid (null)
     */
    public FundAccountingYieldData getCustomerSECData(Date businessDate) throws FundAccountingYieldException;

    /**
     * Persists the calculated SEC security data.
     * 
     * @param fundAccountingYieldData
     *        the SEC security data to be persisted
     * @return flag indicating whether the data was persisted or not
     * @throws FundAccountingYieldException
     *         in case any error occurred during processing
     * @throws IllegalArgumentException
     *         in case the input is invalid (null)
     */
    public boolean persistSecuritySECData(FundAccountingYieldData fundAccountingYieldData) throws FundAccountingYieldException;

    /**
     * Gets the calculated SEC security data.
     * 
     * @param businessDate
     *        the business date
     * @return the list of calculated SEC security data
     * @throws FundAccountingYieldException
     *         in case any error occurred during processing
     * @throws IllegalArgumentException
     *         in case the input is invalid (null)
     */
    public FundAccountingYieldData getCalculatedSECData(Date businessDate) throws FundAccountingYieldException;
}
