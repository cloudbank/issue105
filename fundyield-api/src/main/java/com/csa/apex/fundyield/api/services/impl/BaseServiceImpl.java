/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.DataNotFoundException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Base service implementation.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public abstract class BaseServiceImpl {

    /**
     * Creating restTemplate object helps in mock testing.
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * The REST API path to the endpoint used to obtain calc engine configurations. After injection should be non-null
     * and non-empty.
     */
    @Value("${getConfigApiPath}")
    private String getConfigApiPath;

    /**
     * Configuration object for the service.
     */
    private SECConfiguration configuration;

    /**
     * Empty constructor.
     */
    protected BaseServiceImpl() {
        // Empty
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(restTemplate, this.getClass().getCanonicalName(), "restTemplate");
        CommonUtility.checkStringConfig(getConfigApiPath, this.getClass().getCanonicalName(), "getConfigApiPath");
    }

    /**
     * Getter method for property <tt>restTemplate</tt>.
     * @return property value of restTemplate
     */
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    /**
     * Setter method for property <tt>restTemplate</tt>.
     * @param restTemplate value to be assigned to property restTemplate
     */
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Getter method for property <tt>getConfigApiPath</tt>.
     * @return property value of getConfigApiPath
     */
    public String getGetConfigApiPath() {
        return getConfigApiPath;
    }

    /**
     * Setter method for property <tt>getConfigApiPath</tt>.
     * @param getConfigApiPath value to be assigned to property getConfigApiPath
     */
    public void setGetConfigApiPath(String getConfigApiPath) {
        this.getConfigApiPath = getConfigApiPath;
    }

    /**
     * Check data returned is not null.
     * @param responseData The response data to check
     * @param apiPath The API path
     * @throws DataNotFoundException if response data is null
     */
    protected void checkNullResponse(Object responseData, String apiPath) throws DataNotFoundException {
        if (responseData == null) {
            throw new DataNotFoundException(String.format("No data returned when calling FAYA API: {%s}", apiPath));
        }
    }

    /**
     * Get SECConfiguration.
     * @return SECConfiguration
     * @throws FundAccountingYieldException in case any error during processing.
     */
    protected SECConfiguration getSECConfiguration() throws FundAccountingYieldException {
        if (configuration == null) {
            try {
                configuration = restTemplate.getForObject(getConfigApiPath, SECConfiguration.class);
            } catch (Exception e) {
                throw new FundAccountingYieldException("Failed to get SEC configuration from path: " + getConfigApiPath,
                        e);
            }

            // Check that configuration is not null
            checkNullResponse(configuration, getConfigApiPath);
        }
        return configuration;
    }

    /**
     * Retrieve formatted date.
     * 
     * @param date
     *            the date to be formatted
     * @return the formatted date
     */
    protected String getFormattedDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.API_DATE_FORMAT);
        return date != null ? dateFormat.format(date) : "null";
    }

}
