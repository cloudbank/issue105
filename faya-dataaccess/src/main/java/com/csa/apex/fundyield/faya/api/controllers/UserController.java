package com.csa.apex.fundyield.faya.api.controllers;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.DataNotFoundException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.service.UserService;
import com.csa.apex.fundyield.fayacommons.entities.User;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Spring REST Controller for FAYA Distribution Yield fund data operations. This
 * class is effectively thread safe.
 */
@Controller
public class UserController implements UserService {

	/**
	 * The persistence service to perform operations on user data. Should be
	 * non-null after injection.
	 */
	@Autowired
	private UserPersistenceService userPersistenceService;

	/**
	     * Empty constructor.
	     */
	    public UserController() {
	    }

	/**
	 * Checks the configuration.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly. Refer to
	 *             field docs for details. Implementation: Check if the fields
	 *             are initialized properly. If no, throw the config exception.
	 *             See the variable docs for details.
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(userPersistenceService, this.getClass().getCanonicalName(),
				Constants.USER_SERVICE);
	}

	/**
	 * Gets Distribution Fund data for the business date.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 *
	 * @return User 
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws DataNotFoundException
	 *             in case any data not found.
	 */
	@Override
	@LogMethod
	//GET
	public User getUser(String userId)
			throws DataNotFoundException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getUser",
				Constants.USER_ID);
		
		return userPersistenceService.getUser(userId);
	}

	/**
	 * Persists the calculated Distribution Fund Yield data.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 * 
	 *            
	 * @return User
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	//SAVE
	@Transactional
	public boolean persistUser(User user)
			throws FundAccountingYieldException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "persistUser",
				Constants.USER_ID);
		
		return userPersistenceService.persistUser(user);
	}

	/**
	 * Gets already calculated Distribution Fund Yield data for the given date.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 * @param businessDate
	 *            - the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	//UPDATE
	public boolean updateUser(User user)
			throws FundAccountingYieldException {
		CommonUtility.checkString(user.getUserSid().toString(), this.getClass().getCanonicalName(), "getCalculatedDistributionFundYieldData",
				Constants.USER_ID);
		
				"getCalculatedDistributionFundYieldData", Constants.BUSINESS_DATE);
		return userPersistenceService.getCalculatedDistributionFundYieldData(userId, businessDate);
	}

	public UserPersistenceService getUserPersistenceService() {
		return userPersistenceService;
	}

	public void setUserPersistenceService(
			UserPersistenceService userPersistenceService) {
		this.userPersistenceService = userPersistenceService;
	}
	/**
	 * Gets already calculated Distribution Fund Yield data for the given date.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 * @param businessDate
	 *            - the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	//DELETE
	public boolean deleteUser(String userId)
			throws FundAccountingYieldException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getCalculatedDistributionFundYieldData",
				Constants.USER_ID);
		CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(),
				"getCalculatedDistributionFundYieldData", Constants.BUSINESS_DATE);
		return userPersistenceService.getCalculatedDistributionFundYieldData(userId, businessDate);
	}

	public UserService getuserPersistenceService() {
		return userPersistenceService;
	}

	public void setuserPersistenceService(
			UserService userService) {
		this.userPersistenceService = userService;
	}

}
