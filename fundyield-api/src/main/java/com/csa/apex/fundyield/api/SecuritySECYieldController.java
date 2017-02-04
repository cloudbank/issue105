/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.csa.apex.fundyield.api.services.SecuritySECYieldService;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * SECYieldController Spring REST Controller for security SEC data operations. This class is effectively thread safe.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@RestController
public class SecuritySECYieldController {

	/**
	 * logger class instance.
	 */
	private final Logger logger = Logger.getLogger(SecuritySECYieldController.class);

	/**
	 * SecYieldService object.
	 */
	@Autowired
	private SecuritySECYieldService secYieldService;

	/**
	 * The max number of security data to be retrieved.
	 */
	@Value("${secyieldcontroller.maxNumberOfSecurityData}")
	private Integer maxNumberOfSecurityData;

	/**
	 * Constructor
	 */
	public SecuritySECYieldController() {
		// default constructor
	}

	/**
	 * Getter secYieldService.
	 * 
	 * @return secYieldService
	 */
	public SecuritySECYieldService getSecYieldService() {
		return secYieldService;
	}

	/**
	 * Setter secYieldService.
	 * 
	 * @param secYieldService
	 */
	public void setSecYieldService(SecuritySECYieldService secYieldService) {
		this.secYieldService = secYieldService;
	}

	/**
	 * Checks beans are injected properly on post construct.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly.
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(secYieldService , this.getClass().getCanonicalName(), "secYieldService");
	}

	/**
	 * Gets SEC Security data with the calculated data for the business date. The securities are also persisted using
	 * FAYA REST API.
	 * 
	 * @param businessDate
	 *            The Business date
	 * @return List<SecuritySECData>
	 * @throws FundAccountingYieldException
	 */
	@RequestMapping(value = "fundAccountingSECYieldData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
    @LogMethod
	public FundAccountingYieldData getSecuritySECData(
			@RequestParam @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getSecuritySECData", Constants.BUSINESS_DATE);

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		final FundAccountingYieldData securitySECData = secYieldService.processSecuritySECData(businessDate);
		truncateResults(securitySECData);

		stopWatch.stop();
		logger.info("METHOD: SecuritySECYieldController.getSecuritySECData(), RUNTIME: " +
				stopWatch.getTotalTimeMillis() + "ms, # OF SECURITIES: " + securitySECData.getPortfolios().size());

		return securitySECData;
	}

	/**
	 * Gets already calculated SEC Security data for the given date.
	 * 
	 * @param businessDate
	 *            the Business date
	 * @return already calculated securitySECData;
	 * @throws FundAccountingYieldException
	 */
	@RequestMapping(value = "calculatedFundAccountingSECYieldData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
    @LogMethod
	public FundAccountingYieldData getCalculatedSecuritySECData(
			@RequestParam @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getCalculatedSecuritySECData", Constants.BUSINESS_DATE);
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		final FundAccountingYieldData securitySECData = secYieldService.getCalculatedSecuritySECData(businessDate);
		truncateResults(securitySECData);

		stopWatch.stop();
		logger.info("METHOD: SecuritySECYieldController.getCalculatedSecuritySECData(), RUNTIME: " +
				stopWatch.getTotalTimeMillis() + "ms, # OF SECURITIES: " + securitySECData.getPortfolios().size());

		return securitySECData;
	}

	/**
	 * Exports SEC Security data in CSV format in a zip archive.
	 * 
	 * @param businessDate
	 *            the Business date
	 * @param response
	 *            the http response
	 * @throws FundAccountingYieldException
	 *             in case any error occurred during processing
	 */
	@RequestMapping(value = "exportCalculatedFundAccountingSECYieldData", method = RequestMethod.GET, produces = "application/zip")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
    @LogMethod
	public void exportCalculatedSecuritySECData(
			@RequestParam @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate,
			HttpServletResponse response) throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "exportCalculatedSecuritySECData", Constants.BUSINESS_DATE);
		CommonUtility.checkNull(response, this.getClass().getCanonicalName(), "exportCalculatedSecuritySECData", "response");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		secYieldService.exportCalculatedSecuritySECData(businessDate, response);

		stopWatch.stop();
		logger.info("METHOD: SecuritySECYieldController.exportCalculatedSecuritySECData(), RUNTIME: " +
				stopWatch.getTotalTimeMillis() + "ms");
	}

	/**
	 * Truncates the result list based on a configured limit number.
	 * 
	 * @param calculatedSecuritySECData
	 *            the security SEC data
	 */
	private void truncateResults(FundAccountingYieldData calculatedSecuritySECData) {
		if (maxNumberOfSecurityData != null && calculatedSecuritySECData.getInstruments() != null && calculatedSecuritySECData.getInstruments().size() > maxNumberOfSecurityData) {
			calculatedSecuritySECData.setInstruments(calculatedSecuritySECData.getInstruments().subList(0, maxNumberOfSecurityData));
		}
	}
}
