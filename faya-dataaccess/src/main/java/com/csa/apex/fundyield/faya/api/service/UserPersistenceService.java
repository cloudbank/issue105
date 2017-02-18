
package com.csa.apex.fundyield.faya.api.service;

import java.util.Date;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.User;

/**
 * Created by topcoder on 12/28/16.
 */
public interface UserPersistenceService {
	/**
	 * Gets Distribution Fund data for the business date.
	 *
	 * @param userId
	 *            The user id;
	 *
	
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	public User getUser(String userId)
			throws FundAccountingYieldException;

	/**
	 * Persists the calculated Distribution Fund Yield data.
	 *
	 * @param userId
	 *            The user id;
	 * @param fundAccountingYieldData
	 *            User @RequestParam;
	 * @return the result of the execution.
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	public boolean persistUser(User user)
			throws FundAccountingYieldException;
	

	/**
	 * Persists the calculated Distribution Fund Yield data.
	 *
	 * @param userId
	 *            The user id;
	 * @param fundAccountingYieldData
	 *            User @RequestParam;
	 * @return the result of the execution.
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	public boolean updateUser(User user)
			throws FundAccountingYieldException;

	/**
	 * Gets already calculated Distribution Fund Yield data for the given date.
	 *
	 * @param userId
	 *            the user id;
	 * @param businessDate
	
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	public boolean deletetUser(String userId)
			throws FundAccountingYieldException;
}
