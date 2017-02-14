/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.TestUtility;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.api.services.impl.DistributionFundYieldServiceImpl;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
 * Test class for the CustomerDataController.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UriComponentsBuilder.class)
@IntegrationTest
public class DistributionFundYiedControllerTest {
	/**
	 * CustomerDataController to be tested.
	 */
	DistributionFundYiedController distributionFundYiedController;

	/**
	 * Mock Setup.
	 * 
	 * @throws Exception
	 *             if any parsing exception occurs
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		RestTemplate restTemplate = mock(RestTemplate.class);
		DistributionFundYieldServiceImpl distributionFundYieldServiceImpl = new DistributionFundYieldServiceImpl();
		distributionFundYieldServiceImpl.setGetCalculatedDistributionFundDataApiPath("mock");
		distributionFundYieldServiceImpl.setGetConfigApiPath("mock");
		distributionFundYieldServiceImpl.setGetFAYADistributionFundDataApiPath("mock");
		distributionFundYieldServiceImpl.setSaveCalculatedDistributionFundDataApiPath("mock");
		distributionFundYieldServiceImpl.setCalculatorEngines(new ArrayList<CalculationEngine>());
		FundAccountingYieldData data = new FundAccountingYieldData();
		PowerMockito.mockStatic(UriComponentsBuilder.class);
		URI uri = new URI("");
		UriComponentsBuilder builder = mock(UriComponentsBuilder.class);
		when(UriComponentsBuilder.fromHttpUrl("mock")).thenReturn(builder);
		when(builder.queryParam(any(String.class), any(Date.class))).thenReturn(builder);
		UriComponents c = mock(UriComponents.class);
		when(builder.build()).thenReturn(c);
		when(c.encode()).thenReturn(c);
		when(c.toUri()).thenReturn(uri);
		when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(FundAccountingYieldData.class)))
				.thenReturn(new ResponseEntity<FundAccountingYieldData>(data, new HttpHeaders(), HttpStatus.CREATED));
		when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Boolean.class)))
				.thenReturn(new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.CREATED));

		distributionFundYieldServiceImpl.setRestTemplate(restTemplate);
		distributionFundYiedController = new DistributionFundYiedController();
		distributionFundYiedController.setDistributionFundYieldService(distributionFundYieldServiceImpl);
	}

	/**
	 * Test getDistributionFundYieldData.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getDistributionFundYieldData() throws Exception {
		DateFormat f = new SimpleDateFormat(Constants.API_DATE_FORMAT);
        Date businessDate = f.parse("2016-12-10");
		distributionFundYiedController.getDistributionFundYieldData(TestUtility.DEFAULT_USER_ID, businessDate);
	}

	/**
	 * Test getDistributionFundYieldData with invalid data.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getDistributionFundYieldDataInvalid() throws Exception {
		distributionFundYiedController.getDistributionFundYieldData(TestUtility.DEFAULT_USER_ID, null);
	}
	
	/**
	 * Test getDistributionFundYieldData with invalid user id.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getDistributionFundYieldDataInvalidUserId() throws Exception {
		distributionFundYiedController.getDistributionFundYieldData(null, new Date());
	}
	
	/**
	 * Test getCalculatedDistributionFundYieldData.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test
	public void getCalculatedDistributionFundYieldData() throws Exception {
		DateFormat f = new SimpleDateFormat(Constants.API_DATE_FORMAT);
        Date businessDate = f.parse("2016-12-10");
		distributionFundYiedController.getCalculatedDistributionFundYieldData(TestUtility.DEFAULT_USER_ID, businessDate);
	}
	
	/**
	 * Test getCalculatedDistributionFundYieldData with invalid data.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getCalculatedDistributionFundYieldDataInvalid() throws Exception {
		distributionFundYiedController.getCalculatedDistributionFundYieldData(TestUtility.DEFAULT_USER_ID, null);
	}
	
	/**
	 * Test getCalculatedDistributionFundYieldData with invalid user id.
	 * 
	 * @throws Exception
	 *             if any exception occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getCalculatedDistributionFundYieldDataInvalidUserId() throws Exception {
		distributionFundYiedController.getCalculatedDistributionFundYieldData(null, new Date());
	}
}