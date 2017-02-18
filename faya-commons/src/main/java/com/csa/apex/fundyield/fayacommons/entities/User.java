
/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

/**
 * The user.
 * 
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class User extends Versionable {

	/**
	 * Surrogate id to uniquely identify an user.
	 */
	@ColumnName("APPLICATION_USER_ID")
	private long userSid;

	/**
	 * Identifies an user by its full legal name plus descriptive information.
	 */
	@ColumnName("APPLICATION_USER_NM")
	private String userShortName;

	/**
	 * Code that can be used to classify users based on an internal Company
	 * Internal classification scheme. An user TYPE CODE can be further refined
	 * by a user STRUCTURE TYPE CODE.
	 */
	@ColumnName("APPLICATION_USER_TYPE_CD")
	private UserTypeCode userTypeCode;

	/**
	 * Codes that can be used to define the income (interest etc.) rate type
	 * (e.g., fixed, variable, etc) . Also known as coupon Rate in MDM
	 * particularly for bonds. Valid Values are: unknown!
	 * <ul>
	 * <li>F FIXED RATE (NON-ZERO COUPON)</li>
	 * <li>L FLOATING RATE</li>
	 * <li>S STEPPED COUPON</li>
	 * <li>V VARIABLE RATE</li>
	 * <li>Z ZERO COUPON</li>
	 * </ul>
	 */

	/**
	 * Constructor.
	 */
	public User() {
		// default empty constructor
	}

	public long getUserSid() {
		return userSid;
	}

	public void setUserSid(long userSid) {
		this.userSid = userSid;
	}

	public String getUserShortName() {
		return userShortName;
	}

	public void setUserShortName(String userShortName) {
		this.userShortName = userShortName;
	}

	public UserTypeCode getUserTypeCode() {
		return userTypeCode;
	}

	public void setUserTypeCode(UserTypeCode userTypeCode) {
		this.userTypeCode = userTypeCode;
	}

}
