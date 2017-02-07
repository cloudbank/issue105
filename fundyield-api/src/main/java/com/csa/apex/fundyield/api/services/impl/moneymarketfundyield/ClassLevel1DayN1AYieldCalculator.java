package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The calculator.
 */
public class ClassLevel1DayN1AYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public ClassLevel1DayN1AYieldCalculator() {
	}

	/**
	 * Calculates the Class Level 1 Day N1A Yield. Please see the
	 * MMandDistFundandClassLevelYieldCalculations.docxfor formula details.
	 * Formula y=((tni+da+rda+mda-b-tni*str*opct)*36500)/(so*nv)
	 * 
	 * @param input
	 *            the calculation input
	 * @return calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	public ClassLevel1DayN1AYieldCalculationOutput calculate(ClassLevel1DayN1AYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, Constants.PARAMETER_INPUT);

		// get parameters from input
		BigDecimal dividend = input.getN1ADistIncomeUnmodAmt(); // TNI
		dividend = dividend.add(input.getN1ADistIncomeAdjAmt()); // DA
		dividend = dividend.add(input.getN1ADistIncomeAdjRevAmt()); // RDA
		dividend = dividend.add(input.getN1ADistReimbursementAmt()); // MDA
		dividend = dividend.subtract(input.getN1ADistIncomeBreakageAmt()); // B
		
		BigDecimal multiplyTmpValue = input.getN1ADistIncomeUnmodAmt(); // TNI
		multiplyTmpValue = multiplyTmpValue.multiply(input.getN1ADistIncomeStr()); // STR
		multiplyTmpValue = multiplyTmpValue.multiply(input.getN1ADistIncomeOpct()); // OPCT
		
		dividend = dividend.subtract(multiplyTmpValue);
		dividend = dividend.multiply(BigDecimal.valueOf(36500));
		
		BigDecimal divisor = input.getDistributableCapstockQty(); // SO
		divisor = divisor.multiply(input.getNavAmount()); // NV
				
		BigDecimal derMm1DayN1aYieldPct = dividend.divide(divisor,input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		ClassLevel1DayN1AYieldCalculationOutput output = new ClassLevel1DayN1AYieldCalculationOutput();
		output.setDerMm1DayN1aYieldPct(derMm1DayN1aYieldPct);
		return output;
	}
}
