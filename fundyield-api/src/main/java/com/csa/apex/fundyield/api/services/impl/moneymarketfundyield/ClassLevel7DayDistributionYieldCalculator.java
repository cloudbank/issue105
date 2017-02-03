package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * The calculator.
 */
public class ClassLevel7DayDistributionYieldCalculator {

	/**
	 */
	public ClassLevel7DayDistributionYieldCalculator() {
	}

	/**
	 * Calculates the Class Level 7 Day Distribution Yield.
	 * 
	 * @param input
	 *            - the calculation input
	 * @return calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	public ClassLevel7DayDistributionYieldCalculationOutput calculate(
			ClassLevel7DayDistributionYieldCalculationInput input) {
		CommonUtility.checkNull(input, "input");
		BigDecimal mnyMkt1DayDistYieldPct = input.getMnyMkt1DayDistYieldPct();
		BigDecimal dPrevious6Days = input.getSumOfMnyMkt1DayDistYieldPctForPrevious6Days();
		// calculate y using formula y=(d+udPrevious6Days)/7 with the precision
		// and round mode specified in
		// configuration
		BigDecimal derMm7DayDistYieldPct = mnyMkt1DayDistYieldPct.add(dPrevious6Days).divide(BigDecimal.valueOf(7),
				input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		ClassLevel7DayDistributionYieldCalculationOutput output = new ClassLevel7DayDistributionYieldCalculationOutput();
		output.setDerMnyMktRst7DayYieldPct(derMm7DayDistYieldPct);
		return output;
	}
}
