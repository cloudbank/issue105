package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;

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
		CommonUtility.checkNull(input, "input");
		// get parameters from input
		BigDecimal derMm1DayN1aYieldPct = input.getN1ADistIncomeUnmodAmt().add(input.getN1ADistIncomeAdjAmt())
				.add(input.getN1ADistIncomeAdjRevAmt()).add(input.getN1ADistReimbursementAmt());
		derMm1DayN1aYieldPct = derMm1DayN1aYieldPct.subtract(input.getN1ADistIncomeBreakageAmt())
				.subtract(input.getN1ADistIncomeUnmodAmt().multiply(input.getN1ADistIncomeStr())
						.multiply(input.getN1ADistIncomeOpct()));
		derMm1DayN1aYieldPct = derMm1DayN1aYieldPct.multiply(BigDecimal.valueOf(36500))
				.divide(input.getDistributableCapstockQty(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP)
				.divide(input.getNavAmount(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		ClassLevel1DayN1AYieldCalculationOutput output = new ClassLevel1DayN1AYieldCalculationOutput();
		output.setDerMm1DayN1aYieldPct(derMm1DayN1aYieldPct);
		return output;
	}
}
