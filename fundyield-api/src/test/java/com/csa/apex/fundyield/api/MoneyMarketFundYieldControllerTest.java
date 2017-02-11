/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
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
import com.csa.apex.fundyield.api.services.impl.MoneyMarketFundYieldServiceImpl;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
* Test class for the MoneyMarketFundYieldController.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest(UriComponentsBuilder.class)
@IntegrationTest
public class MoneyMarketFundYieldControllerTest {
	/**
	 * MoneyMarketFundYieldController to be tested.
	 */
	MoneyMarketFundYieldController moneyMarketFundYieldController;

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
		MoneyMarketFundYieldServiceImpl moneyMarketFundYieldServiceImpl = new MoneyMarketFundYieldServiceImpl();
		moneyMarketFundYieldServiceImpl.setGetCalculatedMoneyMarketFundDataApiPath("mock");
		moneyMarketFundYieldServiceImpl.setGetConfigApiPath("mock");
		moneyMarketFundYieldServiceImpl.setGetFAYAMoneyMarketFundDataApiPath("mock");
		moneyMarketFundYieldServiceImpl.setSaveCalculatedMoneyMarketFundDataApiPath("mock");
		moneyMarketFundYieldServiceImpl.setCalculatorEngines(new ArrayList<CalculationEngine>());
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

		moneyMarketFundYieldServiceImpl.setRestTemplate(restTemplate);
		moneyMarketFundYieldController = new MoneyMarketFundYieldController();
		moneyMarketFundYieldController.setMoneyMarketFundYieldService(moneyMarketFundYieldServiceImpl);
	}

	
    /**
     * Test for method getMoneyMarketFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getMoneyMarketFundYieldData() throws Exception {
    	DateFormat f = new SimpleDateFormat(Constants.API_DATE_FORMAT);
        Date businessDate = f.parse("2016-12-10");
        moneyMarketFundYieldController.getMoneyMarketFundYieldData(TestUtility.DEFAULT_USER_ID, businessDate);
    }
    
    /**
     * Test for method getMoneyMarketFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getMoneyMarketFundYieldDataInvalid() throws Exception {
        moneyMarketFundYieldController.getMoneyMarketFundYieldData(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method getMoneyMarketFundYieldData with invalid user id.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getMoneyMarketFundYieldDataInvalidUserId() throws Exception {
        moneyMarketFundYieldController.getMoneyMarketFundYieldData(null, new Date());
    }
    
    /**
     * Test for method getCalculatedMoneyMarketFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getCalculatedMoneyMarketFundYieldData() throws Exception {
    	DateFormat f = new SimpleDateFormat(Constants.API_DATE_FORMAT);
        Date businessDate = f.parse("2016-12-10");
        moneyMarketFundYieldController.getCalculatedMoneyMarketFundYieldData(TestUtility.DEFAULT_USER_ID, businessDate);
    }
    
    /**
     * Test for method getCalculatedMoneyMarketFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedMoneyMarketFundYieldDataInvalid() throws Exception {
        moneyMarketFundYieldController.getCalculatedMoneyMarketFundYieldData(TestUtility.DEFAULT_USER_ID, null);
    }
    
    /**
     * Test for method getCalculatedMoneyMarketFundYieldData with invalid user id.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedMoneyMarketFundYieldDataInvalidUserId() throws Exception {
        moneyMarketFundYieldController.getCalculatedMoneyMarketFundYieldData(null, new Date());
    }
}
