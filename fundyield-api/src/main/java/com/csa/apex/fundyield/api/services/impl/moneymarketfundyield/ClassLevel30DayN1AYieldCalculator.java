package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The calculator.
 */
public class ClassLevel30DayN1AYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public ClassLevel30DayN1AYieldCalculator() {
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
	public ClassLevel30DayN1AYieldCalculationOutput calculate(ClassLevel30DayN1AYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				Constants.PARAMETER_INPUT);

		BigDecimal derMnyMkt1DayN1AYieldPct = input.getDerMnyMkt1DayN1AYieldPct();
		BigDecimal dPrevios29Days = input.getSumOfDer1DayYieldN1AMnyMktPctPrevious29Days();
		BigDecimal derMm1DayN1aYieldPct = derMnyMkt1DayN1AYieldPct.add(dPrevios29Days);
		derMm1DayN1aYieldPct = derMm1DayN1aYieldPct.divide(BigDecimal.valueOf(30), input.getOperationScale(),
				BigDecimal.ROUND_HALF_UP);

		ClassLevel30DayN1AYieldCalculationOutput output = new ClassLevel30DayN1AYieldCalculationOutput();
		output.setDerMnyMkt1DayN1AYieldPct(derMm1DayN1aYieldPct);
		return output;
	}
}
