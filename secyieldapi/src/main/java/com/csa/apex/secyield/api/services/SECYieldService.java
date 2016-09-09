/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.SECYieldException;

/**
 * SECYieldService Interface.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
public interface SECYieldService {

	/**
	 * Process SEC Security data for the business data. This method gets the securities and then process each security
	 * first to calculate the data, then to persist it using API.
	 *
	 * @param businessDate
	 *            the business date;
	 * @return securitySECData with calculated result
	 *
	 * @throws IllegalArgumentException
	 *             input is invalid
	 * @throws SECYieldException
	 *             any error during processing
	 */
	public List<SecuritySECData> processSecuritySECData(Date businessDate)
			throws IllegalArgumentException, SECYieldException;

	/**
	 * Gets already calculated SEC Security data for the given date.
	 *
	 * @param businessDate
	 *            the business date
	 * @return already calculated securitySECData
	 *
	 * @throws IllegalArgumentException
	 *             input is invalid
	 * @throws SECYieldException
	 *             any error during processing
	 */
	public List<SecuritySECData> getCalculatedSecuritySECData(Date businessDate)
			throws IllegalArgumentException, SECYieldException;

	/**
	 * Exports SEC Security data in CSV format in an archive.
	 *
	 * @param businessDate
	 *            the business date
	 * @param response
	 *            the http response
	 * @throws IllegalArgumentException
	 *             input is invalid
	 * @throws SECYieldException
	 *             any error during processing
	 */
	public void exportCalculatedSecuritySECData(Date businessDate, HttpServletResponse response)
			throws IllegalArgumentException, SECYieldException;
}
