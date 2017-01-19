package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;

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
        // get parameters from input
        BigDecimal m = input.getDist12MoMilRt();
        BigDecimal n = input.getNavAmt();
        // calculate y using formula y = m / n with the precision and round mode specified in configuration
        BigDecimal y = m.divide(n, input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
        ClassLevel12MonthDistributionYieldCalculationOutput output = new ClassLevel12MonthDistributionYieldCalculationOutput();
        output.setDerDist12MoYieldPct(y);
        return output;
    }
}
