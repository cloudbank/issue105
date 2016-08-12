package com.csa.apex.secyield.utility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.csa.apex.secyield.entities.SecuritySECData;

/**
 * Utility class for the CalculationEngine.
 *
 * @see TestUtility
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@Component
public class TestUtility {

	
	/**
	 * Returns value with scale 7 with ROUND_HALF_DOWN
	 * 
	 * @param val
	 *            value passed
	 * @return value with scale 7
	 */
	public BigDecimal getBigDecimalWithScale7(BigDecimal val) {
		val = val.setScale(7, BigDecimal.ROUND_HALF_DOWN);
		return val;
	}
	
	/**
     * Get Mock list of SecuritySECData
     * @return List<SecuritySECData>
	 * @throws ParseException 
     */
    public List<SecuritySECData> getSecuritySECData() throws ParseException
    {
    	return MockDataServiceUtility.getSecuritySECDataWithYieldAndIncomeData();
    }

}
