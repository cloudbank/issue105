package com.csa.apex.secyield.api.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.utility.TestUtility;

/**
 * Test class for the SECYieldServiceImpl.
 *
 * @see SECYieldServiceImpl
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SECYieldServiceImplTest {

	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	// Create Mock
	@Mock
	private RestTemplate restTemplate;

	/**
	 * Mock Setup
	 * 
	 * @throws ParseException
	 * @throws RestClientException
	 */
	@Before
	public void setUp() throws RestClientException, ParseException {
		MockitoAnnotations.initMocks(this);
		when(restTemplate.getForObject(any(URI.class), eq(SECConfiguration.class))).thenReturn(new SECConfiguration());
		when(restTemplate.getForObject(any(URI.class), eq(List.class))).thenReturn(utility.getSecuritySECData());
		when(restTemplate.exchange(any(String.class), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Boolean.class)))
				.thenReturn(new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.CREATED));
	}

	/**
	 * SECYieldServiceImpl object
	 */
	@InjectMocks
	@Autowired
	@Qualifier("secYieldServiceImpl")
	SECYieldServiceImpl secyYieldServiceImpl;

	/**
	 * Test processSecuritySECData Yield and income is asserted to check
	 * calculation has been done
	 * 
	 * @throws Exception
	 */
	@Test
	public void processSecuritySECDataTest() throws Exception {
		List<SecuritySECData> data = secyYieldServiceImpl.processSecuritySECData(new Date());
		SecuritySECData securitySECData = data.get(0);
		assertEquals(securitySECData.getDerOneDaySecurityYield(),
				securitySECData.getSecurityReferenceData().getInterestRt().setScale(7, BigDecimal.ROUND_HALF_DOWN));
		assertEquals(securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(26690.42).setScale(2, BigDecimal.ROUND_HALF_DOWN));

	}

	/**
	 * Test getCalculatedSecuritySECData
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCalculatedSecuritySECDataMockTest() throws Exception {
		List<SecuritySECData> data = secyYieldServiceImpl.getCalculatedSecuritySECData(new Date());
		assertEquals(data.size(), 2);

	}

}