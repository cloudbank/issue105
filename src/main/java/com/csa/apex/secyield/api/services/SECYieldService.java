package com.csa.apex.secyield.api.services;

import java.util.Date;
import java.util.List;

import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.SECYieldException;

/**
 * SECYieldService Interface
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public interface SECYieldService {

	/**
	 * Process SEC Security data for the business data. This method gets the
	 * securities and then process each security first to calculate the data,
	 * then to persist it using API.
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
}
