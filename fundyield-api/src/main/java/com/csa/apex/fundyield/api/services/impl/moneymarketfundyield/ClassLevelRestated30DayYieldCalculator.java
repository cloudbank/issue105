package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The calculator.
 */
public class ClassLevelRestated30DayYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public ClassLevelRestated30DayYieldCalculator() {
	}

	/**
	 * Calculates the Class Level Restated 30 Day Yield.
	 * 
	 * @param input
	 *            the calculation input
	 * @return calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	public ClassLevelRestated30DayYieldCalculationOutput calculate(ClassLevelRestated30DayYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, Constants.PARAMETER_INPUT);
		BigDecimal derMmRestate1DayYieldPct = input.getDerMmRestate1DayYieldPct();
		BigDecimal dPrevious29Days = input.getSumOfDerRestate1DayYieldMnyMktPctPrevious29Days();
		// calculate y using formula y=(d+dPrevious29Days)/30;
		BigDecimal derMmRst30DayYieldPct = derMmRestate1DayYieldPct.add(dPrevious29Days).divide(BigDecimal.valueOf(30),
				input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		ClassLevelRestated30DayYieldCalculationOutput output = new ClassLevelRestated30DayYieldCalculationOutput();
		output.setDerMnyMktRst30DayYieldPctBigDecimal(derMmRst30DayYieldPct);
		return output;
	}
}
