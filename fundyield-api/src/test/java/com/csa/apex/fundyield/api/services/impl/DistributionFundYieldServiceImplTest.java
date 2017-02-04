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

import com.csa.apex.fundyield.utility.Constants;
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
* Test class for the DistributionFundYieldServiceImpl.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@RunWith(PowerMockRunner.class)
@PrepareForTest(UriComponentsBuilder.class)
@IntegrationTest
public class DistributionFundYieldServiceImplTest {

	/**
	 * DistributionFundYieldServiceImpl object.
	 */
	private DistributionFundYieldServiceImpl distributionFundYieldServiceImpl;
	
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
		distributionFundYieldServiceImpl = new DistributionFundYieldServiceImpl();
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
		when(restTemplate.getForObject(any(URI.class), eq(FundAccountingYieldData.class))).thenReturn(data);
		when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Boolean.class)))
				.thenReturn(new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.CREATED));

		distributionFundYieldServiceImpl.setRestTemplate(restTemplate);
	}
    /**
     * Test for method processDistributionFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void processDistributionFundYieldData() throws Exception {
		DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date businessDate = f.parse("2016-12-10");
		assertNotNull(distributionFundYieldServiceImpl.processDistributionFundYieldData(businessDate));
    }
    /**
     * Test for method processDistributionFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void processDistributionFundYieldDataInvalid() throws Exception {
        distributionFundYieldServiceImpl.processDistributionFundYieldData(null);
    }
    
    /**
     * Test for method getCalculatedDistributionFundYieldData.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getCalculatedDistributionFundYieldData() throws Exception {
        DateFormat f = new SimpleDateFormat(Constants.DATE_MASK_YYYY_MM_DD);
        Date businessDate = f.parse("2016-12-10");
        assertNotNull(distributionFundYieldServiceImpl.getCalculatedDistributionFundYieldData(businessDate));
    }
    /**
     * Test for method getCalculatedDistributionFundYieldData with invalid data.
     *
     * @throws Exception to JUnit
     */
    @Test(expected = IllegalArgumentException.class)
    public void getCalculatedDistributionFundYieldDataInvalid() throws Exception {
    	distributionFundYieldServiceImpl.getCalculatedDistributionFundYieldData(null);
    }
  
}
