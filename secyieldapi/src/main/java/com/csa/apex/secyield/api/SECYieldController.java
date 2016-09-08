/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csa.apex.secyield.api.services.SECYieldService;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.ConfigurationException;
import com.csa.apex.secyield.exceptions.SECYieldException;
import com.csa.apex.secyield.utility.Constants;

/**
 * SECYieldController Spring REST Controller for customer data operations. This class is effectively thread safe.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@RestController
public class SECYieldController {

	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(SECYieldController.class);

	/**
	 * SecYieldService object
	 */
	@Autowired()
	@Qualifier("secYieldServiceImpl")
	private SECYieldService secYieldService;

	/**
	 * Configuration exception message
	 */
	@Value("${messages.configurationargumentexception}")
	private String configurationArgumentExceptionMessage;

	/**
	 * The max number of security data to be retrieved
	 */
	@Value("${secyieldcontroller.maxNumberOfSecurityData}")
	private Integer maxNumberOfSecurityData;

	/**
	 * Constructor
	 */
	public SECYieldController() {
		// default constructor
	}

	/**
	 * Getter secYieldService
	 * 
	 * @return secYieldService
	 */
	public SECYieldService getSecYieldService() {
		return secYieldService;
	}

	/**
	 * Setter secYieldService
	 * 
	 * @param secYieldService
	 */
	public void setSecYieldService(SECYieldService secYieldService) {
		this.secYieldService = secYieldService;
	}

	/**
	 * Checks beans are injected properly on post construct
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly.
	 */
	@PostConstruct
	protected void checkConfiguration() {

		if (secYieldService == null) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
	}

	/**
	 * Gets SEC Security data with the calculated data for the business date. The securities are also persisted using
	 * customer REST API.
	 * 
	 * @param businessDate
	 *            The Business date
	 * @return List<SecuritySECData>
	 * @throws SECYieldException
	 */
	@RequestMapping(value = "securitySECData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SecuritySECData> getSecuritySECData(
			@RequestParam @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws SECYieldException {

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		final List<SecuritySECData> securitySECData = secYieldService.processSecuritySECData(businessDate);
		List<SecuritySECData> result = truncateResults(securitySECData);

		stopWatch.stop();
		logger.info("METHOD: SECYieldController.getSecuritySECData(), RUNTIME: " +
				stopWatch.getTotalTimeMillis() + "ms, # OF SECURITIES: " + securitySECData.size());

		return result;
	}

	/**
	 * Gets already calculated SEC Security data for the given date.
	 * 
	 * @param businessDate
	 *            the Business date
	 * @return already calculated securitySECData;
	 * @throws SECYieldException
	 */
	@RequestMapping(value = "calculatedSecuritySECData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SecuritySECData> getCalculatedSecuritySECData(
			@RequestParam @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws SECYieldException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		final List<SecuritySECData> calculatedSecuritySECData = secYieldService
				.getCalculatedSecuritySECData(businessDate);
		List<SecuritySECData> result = truncateResults(calculatedSecuritySECData);

		stopWatch.stop();
		logger.info("METHOD: SECYieldController.getCalculatedSecuritySECData(), RUNTIME: " +
				stopWatch.getTotalTimeMillis() + "ms, # OF SECURITIES: " + calculatedSecuritySECData.size());

		return result;
	}

	/**
	 * Exports SEC Security data in CSV format in a zip archive.
	 * 
	 * @param businessDate
	 *            the Business date
	 * @param response
	 *            the http response
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 */
	@RequestMapping(value = "exportCalculatedSecuritySECData", method = RequestMethod.GET, produces = "application/zip")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void exportCalculatedSecuritySECData(
			@RequestParam @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate,
			HttpServletResponse response) throws SECYieldException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		secYieldService.exportCalculatedSecuritySECData(businessDate, response);

		stopWatch.stop();
		logger.info("METHOD: SECYieldController.exportCalculatedSecuritySECData(), RUNTIME: " +
				stopWatch.getTotalTimeMillis() + "ms");
	}

	/**
	 * Truncates the result list based on a configured limit number
	 * 
	 * @param calculatedSecuritySECData
	 *            the list of security SEC data
	 * @return the truncated list of security SEC data
	 */
	private List<SecuritySECData> truncateResults(List<SecuritySECData> calculatedSecuritySECData) {
		return maxNumberOfSecurityData != null && calculatedSecuritySECData.size() > maxNumberOfSecurityData
				? calculatedSecuritySECData.subList(0, maxNumberOfSecurityData) : calculatedSecuritySECData;
	}
}
