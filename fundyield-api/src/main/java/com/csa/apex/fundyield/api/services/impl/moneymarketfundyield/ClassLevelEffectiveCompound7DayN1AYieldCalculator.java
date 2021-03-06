package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The calculator.
 */
public class ClassLevelEffectiveCompound7DayN1AYieldCalculator {

    /**
     * Empty constructor.
     */
    public ClassLevelEffectiveCompound7DayN1AYieldCalculator() {
    }

    /**
     * Calculates the Class Level Effective Compound 7 Day N1A Yield.
     * @param input the calculation input
     * @return calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
	public ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput calculate(
			ClassLevelEffectiveCompound7DayN1AYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				Constants.PARAMETER_INPUT);

		BigDecimal derMnyMkt7DayN1AYieldPct = input.getDerMnyMkt7DayN1AYieldPct();
		BigDecimal dPrevious6Days = input.getSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days();
		// calculate y using formula y=(d+udPrevious6Days)/7 with the precision
		// and round mode specified in
		// configuration
		BigDecimal derMnyMktN1ACompound7DayYield = derMnyMkt7DayN1AYieldPct.add(dPrevious6Days);
		derMnyMktN1ACompound7DayYield = derMnyMktN1ACompound7DayYield.divide(BigDecimal.valueOf(7),
				input.getOperationScale(), BigDecimal.ROUND_HALF_UP);

		ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput output = new ClassLevelEffectiveCompound7DayN1AYieldCalculationOutput();
		output.setDerMnyMktN1ACompound7DayYield(derMnyMktN1ACompound7DayYield);
		return output;
	}
}
