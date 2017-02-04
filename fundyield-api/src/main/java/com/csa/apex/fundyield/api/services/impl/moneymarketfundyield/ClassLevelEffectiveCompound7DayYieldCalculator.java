package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The calculator.
 */
public class ClassLevelEffectiveCompound7DayYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public ClassLevelEffectiveCompound7DayYieldCalculator() {
	}

	/**
	 * Calculates the Class Level Effective Compound 7 Day Yield.
	 * 
	 * @param input
	 *            the calculation input
	 * @return calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	public ClassLevelEffectiveCompound7DayYieldCalculationOutput calculate(
			ClassLevelEffectiveCompound7DayYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, Constants.PARAMETER_INPUT);
		BigDecimal derMnyMkt7DayYieldPct = input.getDerMnyMkt7DayYieldPct();
		BigDecimal dPrevious6Days = input.getSumOfDerMnyMkt7DayYieldPctForPrevious6Days();
		// calculate y using formula y=(d+dPrevious6Days)/7 with the precision
		// and round mode specified in configuration
		BigDecimal derMmCompound7dYieldPct = derMnyMkt7DayYieldPct.add(dPrevious6Days).divide(BigDecimal.valueOf(7),
				input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		ClassLevelEffectiveCompound7DayYieldCalculationOutput output = new ClassLevelEffectiveCompound7DayYieldCalculationOutput();
		output.setDerMnyMktRst7DayYieldPct(derMmCompound7dYieldPct);
		return output;
	}
}
