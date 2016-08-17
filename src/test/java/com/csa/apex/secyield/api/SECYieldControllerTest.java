package com.csa.apex.secyield.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.api.services.impl.SECYieldServiceImpl;
import com.csa.apex.secyield.exceptions.SECYieldException;
import com.csa.apex.secyield.utility.TestUtility;

/**
 * Test class for the SECYieldController.
 *
 * @see SECYieldController
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SECYieldControllerTest {

	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	// Create Mock
	@Mock
	private SECYieldServiceImpl secYieldServiceImpl;

	/**
	 * SECYieldServiceImpl object
	 */
	@InjectMocks
	@Autowired
	SECYieldController seyYieldController;

	/**
	 * Mock controller
	 */
	private MockMvc mockMvc;

	/**
	 * Mock Setup
	 * 
	 * @throws SECYieldException
	 * @throws IllegalArgumentException
	 * @throws SECYieldException
	 * @throws ParseException
	 */
	@Before
	public void setUp() throws IllegalArgumentException, SECYieldException, ParseException {
		MockitoAnnotations.initMocks(this);
		when(secYieldServiceImpl.processSecuritySECData(any(Date.class))).thenReturn(utility.getSecuritySECData());
		when(secYieldServiceImpl.getCalculatedSecuritySECData(any(Date.class)))
				.thenReturn(utility.getSecuritySECData());
		this.mockMvc = MockMvcBuilders.standaloneSetup(seyYieldController).build();
	}

	/**
	 * Test securitySECData
	 * 
	 * @throws Exception
	 */
	@Test
	public void getSecuritySECDataTest() throws Exception {
		this.mockMvc.perform(get("/securitySECData").param("businessDate", "2016-05-02")).andExpect(status().isOk());
	}

	/**
	 * Test calcualtedSecuritySECData
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCalculatedSecuritySECDataTest() throws Exception {
		this.mockMvc.perform(get("/calcualtedSecuritySECData").param("businessDate", "2016-05-02"))
				.andExpect(status().isOk());
	}

}