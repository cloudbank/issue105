/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.mock;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.FAYASecuritySECYieldService;
import com.csa.apex.fundyield.faya.api.mock.service.FAYADataPersistenceService;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
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
	private FAYADataPersistenceService fayaDataPersistenceService;

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
        CommonUtility.checkNullConfig(fayaDataPersistenceService, "fayaDataPersistenceService");
	}

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
	@Override
    @LogMethod
	public FundAccountingYieldData getFAYASECData(Date businessDate) throws FundAccountingYieldException {
		return fayaDataPersistenceService.getFAYASECData(businessDate);
	}

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
	@Override
    @LogMethod
	@Transactional
	public boolean persistSecuritySECData(FundAccountingYieldData fundAccountingYieldData) throws FundAccountingYieldException {
		return fayaDataPersistenceService.persistSecuritySECData(fundAccountingYieldData);
	}

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
	@Override
    @LogMethod
	public FundAccountingYieldData getCalculatedSECData(Date businessDate) throws FundAccountingYieldException {
		return fayaDataPersistenceService.getCalculatedSECData(businessDate);
	}
}
