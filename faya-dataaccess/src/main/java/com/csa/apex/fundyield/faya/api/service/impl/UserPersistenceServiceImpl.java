
package com.csa.apex.fundyield.faya.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.User;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

@Service
public class UserPersistenceServiceImpl implements UserPersistenceService {

	/**
	 * The auto wired storedProcedure.
	 */
	@Autowired
	private StoredProcedure storedProcedure;

	/**
	 * The auto wired StoredProcedureHelper.
	 */
	@Autowired
	private StoredProcedureHelper storedProcedureHelper;

	/**
	     * Empty constructor.
	     */
	    public UserPersistenceServiceImpl() {

	    }

	/**
	 * Checks the configuration.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly. Refer to
	 *             field docs for details.
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(storedProcedure, this.getClass().getCanonicalName(), "storedProcedure");
	}

	/**
	 * 
	 * 
	 * @param userId
	 *            The user id;
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws FundAccountingYieldException
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	public User getUser(String userId)
			throws FundAccountingYieldException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getFAYAUser",
				Constants.USER_ID);
		
		return retrieveUser(userId);
	}

	/**
	 * Persists the calculated Distribution Fund Yield user.
	 * 
	 * @param userId
	 *            The user id.
	 * @param fundAccountingYieldData
	 *            User.
	 * @return the result of the execution.
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	@Transactional
	public User persistUser(User user)
			throws FundAccountingYieldException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "persistUser",
				Constants.USER_ID);
		
	
		storedProcedureHelper.saveUser(user);
		return user;
	}
	
	
	/**
	 * 
	 * 
	 * @param userId
	 *            The user id;
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws FundAccountingYieldException
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	public boolean deleteUser(String userId)
			throws FundAccountingYieldException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getFAYAUser",
				Constants.USER_ID);
		
		return deleteUser(userId);
	}
	
	

	/**
	 * Gets already calculated Distribution Fund Yield user for the given date.
	 * 
	 * @param userId
	 *            The user id;
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	public User updateUser(String userId)
			throws FundAccountingYieldException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getCalculatedUser",
				Constants.USER_ID);
		CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(),
				"getCalculatedUser", Constants.BUSINESS_DATE);
		return updateUser(userId);
	}

	
	/**
	 * Gets User from persistence.
	 * 
	 * @param userId
	 *            The user id;
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	private User updateUser(User user)
			throws FundAccountingYieldException {
		
			
		CommonUtility.checkString(user.userId, this.getClass().getCanonicalName(), "retrieveUser",
				Constants.USER_ID);
		try {
			Map<String, Object> param1 = new HashMap<String, Object>() {
				{
					put("user_id", user.user_id);
				}
			};
			storedProcedure.getuser(param1);
			User user = param1.get("rs");
			
//do set props
			//User user = new User();
			//user.setBusinessDate(businessDate);
			//user.setReportDate(businessDate);
			
			
			storedProcedureHelper.saveUser(user);
			return user;
			return user;
		} catch (Exception e) {
			throw new FundAccountingYieldException(e.getMessage());
		}
	}
	
	
	/**
	 * Gets User from persistence.
	 * 
	 * @param userId
	 *            The user id;
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	private User retrieveUser(String userId)
			throws FundAccountingYieldException {
		
			
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "retrieveUser",
				Constants.USER_ID);
		try {
			Map<String, Object> param1 = new HashMap<String, Object>() {
				{
					put("user_id", user_id);
				}
			};
			storedProcedure.getuser(param1);
			User user = param1.get("rs");

			//User user = new User();
			//user.setBusinessDate(businessDate);
			//user.setReportDate(businessDate);
			
			return user;
		} catch (Exception e) {
			throw new FundAccountingYieldException(e.getMessage());
		}
	}

}
