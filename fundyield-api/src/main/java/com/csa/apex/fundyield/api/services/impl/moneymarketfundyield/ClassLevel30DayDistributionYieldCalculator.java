package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;

/**
 */
public class ClassLevel30DayDistributionYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public ClassLevel30DayDistributionYieldCalculator() {
	}

	/**
	 * Calculates the Class Level 30 Day Distribution Yield. Please see the
	 * MMandDistFundandClassLevelYieldCalculations.docxfor formula details.
	 * 
	 * @param input
	 *            the calculation input
	 * @return calculated result;
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	public ClassLevel30DayDistributionYieldCalculationOutput calculate(
			ClassLevel30DayDistributionYieldCalculationInput input) {
        CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, Constants.PARAMETER_INPUT);
		// calculate y using formula y=u-(m*d)/(n*r)
		BigDecimal derDist30DayYieldPct = input.getDistUnmod30DayYieldPct()
				.subtract(input.getDistYieldMilRt().multiply(BigDecimal.valueOf(input.getDaysInYear())).divide(
						input.getNavAmt().multiply(BigDecimal.valueOf(input.getReportingDate())),
						input.getOperationScale(), BigDecimal.ROUND_HALF_UP));
		ClassLevel30DayDistributionYieldCalculationOutput output = new ClassLevel30DayDistributionYieldCalculationOutput();
		output.setDerDist30DayYieldPct(derDist30DayYieldPct);
		return output;
	}
}
