/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.SECYieldException;
import com.csa.apex.secyield.utility.Constants;

/**
 * Spring REST Interface for customer data operations.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Controller
public interface CustomerDataService {

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
	@RequestMapping(value = "customerSecuritySECData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SecuritySECData> getCustomerSECData(
			@RequestParam("businessDate") @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws SECYieldException;

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
	@RequestMapping(value = "persistSecuritySECData", method = RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public boolean persistSecuritySECData(@RequestBody SecuritySECData securitySECData) throws SECYieldException;

	/**
	 * Gets SEC security config for the calculations in engines.
	 * 
	 * @return the SEC security configuration
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 */
	@RequestMapping(value = "securitySECDataConfiguration", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public SECConfiguration getConfiguration() throws SECYieldException;

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
	@RequestMapping(value = "calculatedSecuritySECData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SecuritySECData> getCalculatedSECData(
			@RequestParam("businessDate") @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws SECYieldException;
}