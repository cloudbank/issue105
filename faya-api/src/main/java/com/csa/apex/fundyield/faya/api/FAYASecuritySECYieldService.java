/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Spring REST Interface for FAYA SEC security data operations.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Controller
public interface FAYASecuritySECYieldService {

	/**
	 * Gets the SEC security data.
	 *
	 * @param businessDate
	 *            the business date
	 * @return the list of security SEC data
	 * @throws FundAccountingYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@RequestMapping(value = "fayaFundAccountingSECYieldData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public FundAccountingYieldData getFAYASECData(
			@RequestParam("businessDate") @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws FundAccountingYieldException;

	/**
	 * Persists the calculated SEC security data.
	 *
	 * @param fundAccountingYieldData
	 *            the SEC security data to be persisted
	 * @return flag indicating whether the data was persisted or not
	 * @throws FundAccountingYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@RequestMapping(value = "calculatedFundAccountingSECYieldData", method = RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public boolean persistSecuritySECData(@RequestBody FundAccountingYieldData fundAccountingYieldData) throws FundAccountingYieldException;

	/**
	 * Gets the calculated SEC security data.
	 *
	 * @param businessDate
	 *            the business date
	 * @return the list of calculated SEC security data
	 * @throws FundAccountingYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@RequestMapping(value = "calculatedFundAccountingSECYieldData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public FundAccountingYieldData getCalculatedSECData(
			@RequestParam("businessDate") @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws FundAccountingYieldException;
}