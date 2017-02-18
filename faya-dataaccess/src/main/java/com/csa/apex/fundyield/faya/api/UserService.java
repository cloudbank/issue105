
package com.csa.apex.fundyield.faya.api;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.User;
import com.csa.apex.fundyield.utility.Constants;

/**
 * Spring REST Interface for customer data operations. Update: New methods for
 * MM and Distribution Fund data were added. Annotations:
 * 
 * @Controller
 */
@Controller
public interface UserService {

	/**
	 * Gets Distribution Fund data for the business date.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@RequestMapping(value = "/users/:id", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User getUser(@RequestHeader("userId") String userId,
			@RequestParam(Constants.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws FundAccountingYieldException;

	/**
	 * Persists the User.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 * @param fundAccountingYieldData
	 *            User
	 * @return the result of the execution.
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public boolean persistUser(@RequestHeader("userId") String userId,
			@RequestBody User fundAccountingYieldData) throws FundAccountingYieldException;

	/**
	 * Updates User.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@RequestMapping(value = "/users/:id", method = RequestMethod.PUT, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User updateUser(@RequestHeader("userId") String userId,
			@RequestParam(Constants.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws FundAccountingYieldException;
	
	
	/**
	 * Deletes User.
	 * 
	 * @param userId
	 *            The user id passed in header.
	 * @param businessDate
	 *            the business date;
	 * @return User with calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@RequestMapping(value = "users/:id", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User updateUser(@RequestHeader("userId") String userId,
			@RequestParam(Constants.BUSINESS_DATE) @DateTimeFormat(pattern = Constants.API_DATE_FORMAT) Date businessDate)
			throws FundAccountingYieldException;
}
	
	
	
	
	

