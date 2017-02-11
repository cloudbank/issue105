/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
 * The interface that defines API to process SEC data.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public interface SecuritySECYieldService {

	/**
	 * Process SEC Security data for the business data. This method gets the securities and then process each security
	 * first to calculate the data, then to persist it using API.
	 *
	 * @param userId The user id;
	 * @param businessDate
	 *            the business date;
	 * @return securitySECData with calculated result
	 *
	 * @throws IllegalArgumentException
	 *             input is invalid
	 * @throws FundAccountingYieldException
	 *             any error during processing
	 */
	public FundAccountingYieldData processSecuritySECData(String userId, Date businessDate)
			throws FundAccountingYieldException;

	/**
	 * Gets already calculated SEC Security data for the given date.
	 *
	 * @param userId The user id
	 * @param businessDate
	 *            the business date
	 * @return already calculated securitySECData
	 *
	 * @throws IllegalArgumentException
	 *             input is invalid
	 * @throws FundAccountingYieldException
	 *             any error during processing
	 */
	public FundAccountingYieldData getCalculatedSecuritySECData(String userId, Date businessDate)
			throws FundAccountingYieldException;

	/**
	 * Exports SEC Security data in CSV format in an archive.
	 *
	 * @param userId The user id
	 * @param businessDate
	 *            the business date
	 * @param response
	 *            the http response
	 * @throws IllegalArgumentException
	 *             input is invalid
	 * @throws FundAccountingYieldException
	 *             any error during processing
	 */
	public void exportCalculatedSecuritySECData(String userId, Date businessDate, HttpServletResponse response)
			throws FundAccountingYieldException;
}
