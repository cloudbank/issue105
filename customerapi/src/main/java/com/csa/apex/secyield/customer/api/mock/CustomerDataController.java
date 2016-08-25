/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.secyield.customer.api.CustomerDataService;
import com.csa.apex.secyield.customer.api.mock.service.CustomerDataPersistenceService;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.ConfigurationException;
import com.csa.apex.secyield.exceptions.SECYieldException;

/**
 * Spring REST Controller for customer data operations. This class is effectively thread safe.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Controller
public class CustomerDataController implements CustomerDataService {

	/**
	 * The persistence service to perform operations on customer data.
	 */
	private CustomerDataPersistenceService customerDataPersistenceService;

	/**
	 * Configuration exception message
	 */
	@Value("${messages.configurationargumentexception}")
	private String configurationArgumentExceptionMessage;

	/**
	 * Constructor
	 */
	public CustomerDataController() {
		// default constructor
	}

	/**
	 * Getter customerDataPersistenceService
	 * 
	 * @return the customer data persistence service
	 */
	public CustomerDataPersistenceService getCustomerDataPersistenceService() {
		return customerDataPersistenceService;
	}

	/**
	 * Setter customerDataPersistenceService
	 * 
	 * @param customerDataPersistenceService
	 *            the customer data persistence service to be set
	 */
	public void setCustomerDataPersistenceService(CustomerDataPersistenceService customerDataPersistenceService) {
		this.customerDataPersistenceService = customerDataPersistenceService;
	}

	/**
	 * Checks the configuration.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly.
	 */
	@PostConstruct
	protected void checkConfiguration() {
		if (customerDataPersistenceService == null) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
	}

	/**
	 * Gets the customer SEC security data (without yield and income calculated).
	 * 
	 * @param businessDate
	 *            the business date
	 * @return the list of customer security SEC data
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
	public List<SecuritySECData> getCustomerSECData(Date businessDate) throws SECYieldException {
		return customerDataPersistenceService.getCustomerSECData(businessDate);
	}

	/**
	 * Persists the calculated SEC security data.
	 * 
	 * @param securitySECData
	 *            the SEC security data to be persisted
	 * @return flag indicating whether the data was persisted or not
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
	@Transactional
	public boolean persistSecuritySECData(SecuritySECData securitySECData) throws SECYieldException {
		return customerDataPersistenceService.persistSecuritySECData(securitySECData);
	}

	/**
	 * Gets SEC security config for the calculations in engines.
	 * 
	 * @return the SEC security configuration
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 */
	@Override
	public SECConfiguration getConfiguration() throws SECYieldException {
		return customerDataPersistenceService.getConfiguration();
	}

	/**
	 * Gets the calculated SEC security data.
	 * 
	 * @param businessDate
	 *            the business date
	 * @return the list of calculated SEC security data
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
	public List<SecuritySECData> getCalculatedSECData(Date businessDate) throws SECYieldException {
		return customerDataPersistenceService.getCalculatedSECData(businessDate);
	}
}
