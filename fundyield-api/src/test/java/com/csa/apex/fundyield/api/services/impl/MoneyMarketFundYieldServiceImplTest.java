/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;

/**
* Test class for the MoneyMarketFundYieldServiceImpl.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest(UriComponentsBuilder.class)
@IntegrationTest
public class MoneyMarketFundYieldServiceImplTest {
	/**
	 * MoneyMarketFundYieldServiceImpl object.
	 */
	private MoneyMarketFundYieldServiceImpl moneyMarketFundYieldServiceImpl;
	
	/**
	 * Mock Setup.
	 * 
	 * @throws ParseException
	 * @throws RestClientException
	 * @throws URISyntaxException 
	 */
	@Before
	public void setUp() throws RestClientException, URISyntaxException {
		RestTemplate restTemplate = mock(RestTemplate.class);
		moneyMarketFundYieldServiceImpl = new MoneyMarketFundYieldServiceImpl();
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
		when(restTemplate.getForObject(any(URI.class), eq(FundAccountingYieldData.class))).thenReturn(data);
		when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Boolean.class)))
				.thenReturn(new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.CREATED));

		moneyMarketFundYieldServiceImpl.setRestTemplate(restTemplate);
	}
    /**
     * Test for method processMoneyMarketFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void processMoneyMarketFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date businessDate = f.parse("2016-12-10");
        assertNotNull(moneyMarketFundYieldServiceImpl.processMoneyMarketFundYieldData(businessDate));
    }
    /**
     * Test for method processMoneyMarketFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void processMoneyMarketFundYieldDataInvalid() throws Exception {
    	moneyMarketFundYieldServiceImpl.processMoneyMarketFundYieldData(null);
    }
    /**
     * Test for method getCalculatedMoneyMarketFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getCalculatedMoneyMarketFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        Date businessDate = f.parse("2016-12-10");
        assertNotNull(moneyMarketFundYieldServiceImpl.getCalculatedMoneyMarketFundYieldData(businessDate));
    }
    /**
     * Test for method getCalculatedMoneyMarketFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedMoneyMarketFundYieldDataInvalid() throws Exception {
    	moneyMarketFundYieldServiceImpl.getCalculatedMoneyMarketFundYieldData(null);
    }
}
