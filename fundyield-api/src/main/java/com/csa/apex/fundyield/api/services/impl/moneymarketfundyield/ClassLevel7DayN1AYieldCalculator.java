package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 */
public class ClassLevel7DayN1AYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public ClassLevel7DayN1AYieldCalculator() {
	}

	/**
	 * Calculates the Class Level 1 Day N1A Yield.
	 * 
	 * @param input
	 *            the calculation input
	 * @return calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	public ClassLevel7DayN1AYieldCalculationOutput calculate(ClassLevel7DayN1AYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				Constants.PARAMETER_INPUT);

		BigDecimal derMnyMkt1DayN1AYieldPct = input.getDerMnyMkt1DayN1AYieldPct();
		BigDecimal dPrevious6Days = input.getSumOfDer1DayYieldN1AMnyMktPctPrevious6Days();
		// calculate y using formula y=(d+udPrevious6Days)/7 with the precision
		// and round mode specified in configuration
		BigDecimal derMm1DayN1aYieldPct = derMnyMkt1DayN1AYieldPct.add(dPrevious6Days);
		derMm1DayN1aYieldPct = derMm1DayN1aYieldPct.divide(BigDecimal.valueOf(7), input.getOperationScale(),
				BigDecimal.ROUND_HALF_UP);

		ClassLevel7DayN1AYieldCalculationOutput output = new ClassLevel7DayN1AYieldCalculationOutput();
		output.setDerMnyMkt7DayN1AYieldPct(derMm1DayN1aYieldPct);
		return output;
	}
}
