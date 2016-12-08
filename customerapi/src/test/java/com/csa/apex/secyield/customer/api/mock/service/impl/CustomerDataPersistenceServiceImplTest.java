/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.csa.apex.secyield.TestApplication;
import com.csa.apex.secyield.customer.api.mock.service.CustomerDataPersistenceService;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.utility.TestUtility;

/**
 * Test class for the CustomerDataPersistenceServiceImpl.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplication.class)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql") })
public class CustomerDataPersistenceServiceImplTest {

	/**
	 * SECYieldServiceImpl object.
	 */
	@Autowired
	CustomerDataPersistenceService customerDataPersistenceService;

	/**
	 * Test getCustomerSECData retrieves correct data.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCustomerSECDataTestSuccess() throws Exception {
		List<SecuritySECData> data = customerDataPersistenceService
				.getCustomerSECData(DateTime.parse("2016-08-19").toDate());
		assertEquals(data.size(), 3);
	}

	/**
	 * Test getCustomerSECData retrieves no data for date that is not present in the db data.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCustomerSECDataTestNoData() throws Exception {
		List<SecuritySECData> data = customerDataPersistenceService
				.getCustomerSECData(DateTime.parse("2017-08-19").toDate());
		assertEquals(data.size(), 0);
	}

	/**
	 * Test getCustomerSECData throws IllegalArgumentException in case the provided date is null.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getCustomerSECDataTestNullDate() throws Exception {
		customerDataPersistenceService.getCustomerSECData(null);
	}

	/**
	 * Test persistSecuritySECData persists data successfully.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void persistSecuritySECDataTestSuccess() throws Exception {
		SecuritySECData securitySECData = TestUtility.getTestSecuritySECData();
		customerDataPersistenceService.persistSecuritySECData(securitySECData);
		List<SecuritySECData> data = customerDataPersistenceService
				.getCalculatedSECData(securitySECData.getReportDate());
		assertEquals(data.size(), 1);
		final SecuritySECData persistedSecuritySECData = data.get(0);
		assertEquals(persistedSecuritySECData.getSecurityIdentifier(), securitySECData.getSecurityIdentifier());
		assertEquals(persistedSecuritySECData.getPositionData().length, securitySECData.getPositionData().length);
	}

 	/**
 	 * Test performRedemptionDateAndMaturityDateEqualityTest().
 	 *
 	 * @throws Exception
 	 */
 	@Test
 	public void performRedemptionDateAndMaturityDateEqualityTest() throws Exception {
 		SecuritySECData securitySECData = TestUtility.getTestSecuritySECData();
 		assertEquals(securitySECData.getDerRedemptionDate(), securitySECData.getSecurityReferenceData().getFinalMaturityDate());
 	}

	/**
	 * Test persistSecuritySECData throws IllegalArgumentException in case the provided security SEC data is null.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void persistSecuritySECDataTestNullInput() throws Exception {
		customerDataPersistenceService.persistSecuritySECData(null);
	}

	/**
	 * Test getConfiguration retrieves the correct config.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getConfigurationTestSuccess() throws Exception {
		SECConfiguration data = customerDataPersistenceService.getConfiguration();
		assertEquals(data.getOperationScale(), 1);
		assertEquals(data.getRoundingMode(), 2);
	}

	/**
	 * Test getCalculatedSECData retrieves correct data.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCalculatedSECDataTestSuccess() throws Exception {
		List<SecuritySECData> data = customerDataPersistenceService
				.getCalculatedSECData(DateTime.parse("2016-08-20").toDate());
		assertEquals(data.size(), 2);
	}

	/**
	 * Test getCalculatedSECData retrieves no data for date that is not present in the db data.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCalculatedSECDataTestNoData() throws Exception {
		List<SecuritySECData> data = customerDataPersistenceService
				.getCalculatedSECData(DateTime.parse("2017-08-19").toDate());
		assertEquals(data.size(), 0);
	}

	/**
	 * Test getCalculatedSECData throws IllegalArgumentException in case the provided date is null.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getCalculatedSECDataTestNullDate() throws Exception {
		customerDataPersistenceService.getCalculatedSECData(null);
	}
}