package com.csa.apex.secyield.api.services.impl.engines;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.secyield.Application;
import com.csa.apex.secyield.utility.TestUtility;
import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;

/**
 * Test class for the YtmIncomeCalculationEngine
 *
 * @see YtmIncomeCalculationEngine
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class YtmIncomeCalculationEngineTest {
	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * YtmIncomeCalculationEngine object
	 */
	@Autowired
	private YtmIncomeCalculationEngine ytmIncomeCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if SecuritySECData is
	 * null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		ytmIncomeCalculationEngine.calculate(null, configuration);
	}

	/**
	 * Tests IllegalArgumentException should be thrown if SECConfiguration is
	 * null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSECConfiguration() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		ytmIncomeCalculationEngine.calculate(securitySECData, null);
	}

	/**
	 * Ytm income calculation mv = 70135342.4 ai = 257693.72 y =
	 * 0.00948961511103637 infllnc = -4956.56
	 * 
	 * @throws Exception
	 */

	@Test
	public void checkIncomeCalculationTest1() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		PositionData positionData = new PositionData();
		positionData.setMarketValue(utility.getBigDecimalWithScale7(new BigDecimal(70135342.4)));
		positionData.setAccruedIncome(utility.getBigDecimalWithScale7(new BigDecimal(257693.72)));
		positionData.setEarnedInflationaryCompensationBase(utility.getBigDecimalWithScale7(new BigDecimal(-4956.56)));
		securitySECData.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.00948961511103637)));
		securitySECData.setPositionData(new PositionData[] { positionData });
		securitySECData.setFxRate(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		ytmIncomeCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(0,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(-3101).setScale(0, BigDecimal.ROUND_HALF_DOWN));
	}

	/**
	 * Ytm income calculation mv = 70135342.4 ai = 257693.72 y =
	 * 0.30948961511103637 infllnc = -4956.56 Y%/FX> threshhold
	 * 
	 * @throws Exception
	 */

	@Test
	public void checkIncomeCalculationTest2() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		PositionData positionData = new PositionData();
		positionData.setMarketValue(utility.getBigDecimalWithScale7(new BigDecimal(70135342.4)));
		positionData.setAccruedIncome(utility.getBigDecimalWithScale7(new BigDecimal(257693.72)));
		positionData.setEarnedInflationaryCompensationBase(utility.getBigDecimalWithScale7(new BigDecimal(-4956.56)));
		securitySECData.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.30948961511103637)));
		securitySECData.setPositionData(new PositionData[] { positionData });
		securitySECData.setFxRate(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		SECConfiguration configuration = new SECConfiguration();
		configuration.setOperationScale(7);
		ytmIncomeCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(34150.68).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

}
