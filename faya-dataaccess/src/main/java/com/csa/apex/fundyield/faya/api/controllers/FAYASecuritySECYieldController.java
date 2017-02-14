/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.controllers;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.FAYASecuritySECYieldService;
import com.csa.apex.fundyield.faya.api.service.FAYASecuritySECYieldPersistenceService;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Spring REST Controller for FAYA SEC security data operations. This class is effectively thread safe.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Controller
public class FAYASecuritySECYieldController implements FAYASecuritySECYieldService {

	/**
	 * The persistence service to perform operations on FAYA SEC security data.
	 */
    @Autowired
	private FAYASecuritySECYieldPersistenceService fayaSecuritySECYieldPersistenceService;

	/**
	 * Constructor.
	 */
	public FAYASecuritySECYieldController() {
		// default constructor
	}

	/**
	 * Checks the configuration.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly.
	 */
	@PostConstruct
	protected void checkConfiguration() {
        CommonUtility.checkNullConfig(fayaSecuritySECYieldPersistenceService, this.getClass().getCanonicalName(), Constants.FAYA_SECURITY_SEC_YIELD_PERSISTENCE_SERVICE);
	}

	/**
	 * Gets the SEC security data.
	 * 
	 * @param userId The user id passed in header.
	 * @param businessDate
	 *            the business date
	 * @return the list of security SEC data
	 * @throws FundAccountingYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
    @LogMethod
	public FundAccountingYieldData getFAYASECData(String userId, Date businessDate) throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getFAYASECData", Constants.BUSINESS_DATE);
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getFAYASECData", Constants.USER_ID);
		return fayaSecuritySECYieldPersistenceService.getFAYASECData(userId, businessDate);
	}

	/**
	 * Persists the calculated SEC security data.
	 * 
	 * @param userId The user id passed in header.
	 * @param fundAccountingYieldData
	 *            the SEC security data to be persisted
	 * @return flag indicating whether the data was persisted or not
	 * @throws FundAccountingYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
    @LogMethod
	@Transactional
	public boolean persistSecuritySECData(String userId, FundAccountingYieldData fundAccountingYieldData) throws FundAccountingYieldException {
		CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "persistSecuritySECData", Constants.USER_ID);
		CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), "persistSecuritySECData", Constants.FUND_ACCOUNTING_YIELD_DATA);
		return fayaSecuritySECYieldPersistenceService.persistSecuritySECData(userId, fundAccountingYieldData);
	}

	/**
	 * Gets the calculated SEC security data.
	 * 
	 * @param userId The user id passed in header.
	 * @param businessDate
	 *            the business date
	 * @return the list of calculated SEC security data
	 * @throws FundAccountingYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
    @LogMethod
	public FundAccountingYieldData getCalculatedSECData(String userId, Date businessDate) throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getCalculatedSECData", Constants.BUSINESS_DATE);
		return fayaSecuritySECYieldPersistenceService.getCalculatedSECData(userId, businessDate);
	}
}
