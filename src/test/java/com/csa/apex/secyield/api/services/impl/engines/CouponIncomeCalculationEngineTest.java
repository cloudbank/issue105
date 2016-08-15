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
 * Test class for the CouponIncomeCalculationEngine.
 *
 * @see CouponIncomeCalculationEngine
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CouponIncomeCalculationEngineTest {
	/**
	 * Utility class
	 */
	@Autowired
	private TestUtility utility;

	/**
	 * CouponIncomeCalculationEngine object
	 */
	@Autowired
	private CouponIncomeCalculationEngine couponIncomeCalculationEngine;

	/**
	 * Tests IllegalArgumentException should be thrown if SecuritySECData is
	 * null
	 * 
	 * @throws Exception
	 */
	@Test(expected = IllegalArgumentException.class)
	public void checkParameterValidationSecuritySECData() throws Exception {
		SECConfiguration configuration = new SECConfiguration();
		couponIncomeCalculationEngine.calculate(null, configuration);
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
		couponIncomeCalculationEngine.calculate(securitySECData, null);
	}

	/**
	 * Checks coupon income value
	 * 
	 * Sh = 7000000 Y = 0.049592404 FX = 1 Am = -45.69 Income = 918.61
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkIncomeCalculationTest1() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		securitySECData.setFxRate(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		securitySECData.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.049592404)));
		PositionData positionData = new PositionData();
		positionData.setShareParAmount(utility.getBigDecimalWithScale7(new BigDecimal(7000000)));
		positionData.setEarnedAmortizationBase(utility.getBigDecimalWithScale7(new BigDecimal(-45.69)));
		securitySECData.setPositionData(new PositionData[] { positionData });
		SECConfiguration configuration = new SECConfiguration();
		couponIncomeCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(918.61).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

	/**
	 * Checks coupon income value
	 * 
	 * Sh = 4900000 Y = 0.004199982 FX = 1 Am = 0 Income = 57.17
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkIncomeCalculationTest2() throws Exception {
		SecuritySECData securitySECData = new SecuritySECData();
		securitySECData.setFxRate(utility.getBigDecimalWithScale7(new BigDecimal(1)));
		securitySECData.setDerOneDaySecurityYield(utility.getBigDecimalWithScale7(new BigDecimal(0.004199982)));
		PositionData positionData = new PositionData();
		positionData.setShareParAmount(utility.getBigDecimalWithScale7(new BigDecimal(4900000)));
		positionData.setEarnedAmortizationBase(utility.getBigDecimalWithScale7(new BigDecimal(0)));
		securitySECData.setPositionData(new PositionData[] { positionData });
		SECConfiguration configuration = new SECConfiguration();
		couponIncomeCalculationEngine.calculate(securitySECData, configuration);
		assertEquals(securitySECData.getPositionData()[0].getDerOneDaySecurityIncome().setScale(2,
				BigDecimal.ROUND_HALF_DOWN), new BigDecimal(57.17).setScale(2, BigDecimal.ROUND_HALF_DOWN));
	}

}
