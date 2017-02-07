package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 * The calculator.
 */
public class ClassLevel12MonthDistributionYieldCalculator {

    /**
     * Empty constructor.
     */
    public ClassLevel12MonthDistributionYieldCalculator() {
    }

    /**
     * Calculates the Class Level 12 Month Distribution Yield.. Please see the
     * MMandDistFundandClassLevelYieldCalculations.docxfor formula details.
     * @param input the calculation input
     * @return calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
	public ClassLevel12MonthDistributionYieldCalculationOutput calculate(
			ClassLevel12MonthDistributionYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				Constants.PARAMETER_INPUT);

		// get parameters from input
		BigDecimal dist12MoMilRt = input.getDist12MoMilRt();
		BigDecimal classNavAmount = input.getNavAmt();
		// calculate y using formula y = m / n with the precision and round mode
		// specified in configuration
		BigDecimal der12MonthYieldDistPct = dist12MoMilRt.divide(classNavAmount, input.getOperationScale(),
				BigDecimal.ROUND_HALF_UP);

		ClassLevel12MonthDistributionYieldCalculationOutput output = new ClassLevel12MonthDistributionYieldCalculationOutput();
		output.setDerDist12MoYieldPct(der12MonthYieldDistPct);
		return output;
	}
}
