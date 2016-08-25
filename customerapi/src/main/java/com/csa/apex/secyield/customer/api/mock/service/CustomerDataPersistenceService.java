/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock.service;

import java.util.Date;
import java.util.List;

import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.SECYieldException;

/**
 * Persistence interface defining operations for customer data.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public interface CustomerDataPersistenceService {

    /**
     * Gets the customer SEC Security.
     * 
     * @param businessDate
     *        the business date
     * @return the list of customer security SEC data
     * @throws SECYieldException
     *         in case any error occurred during processing
     * @throws IllegalArgumentException
     *         in case the input is invalid (null)
     */
    public List<SecuritySECData> getCustomerSECData(Date businessDate) throws SECYieldException;

    /**
     * Persists the calculated SEC security data.
     * 
     * @param securitySECData
     *        the SEC security data to be persisted
     * @return flag indicating whether the data was persisted or not
     * @throws SECYieldException
     *         in case any error occurred during processing
     * @throws IllegalArgumentException
     *         in case the input is invalid (null)
     */
    public boolean persistSecuritySECData(SecuritySECData securitySECData) throws SECYieldException;

    /**
     * Gets SEC security config for the calculations in engines.
     * 
     * @return the SEC security configuration
     * @throws SECYieldException
     *         in case any error occurred during processing
     */
    public SECConfiguration getConfiguration() throws SECYieldException;

    /**
     * Gets the calculated SEC security data.
     * 
     * @param businessDate
     *        the business date
     * @return the list of calculated SEC security data
     * @throws SECYieldException
     *         in case any error occurred during processing
     * @throws IllegalArgumentException
     *         in case the input is invalid (null)
     */
    public List<SecuritySECData> getCalculatedSECData(Date businessDate) throws SECYieldException;
}
