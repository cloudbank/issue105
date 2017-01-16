package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;

/**
 */
public class ClassLevel30DayDistributionYieldCalculator {

    /**
     * Empty constructor.
     */
    public ClassLevel30DayDistributionYieldCalculator() {
    }

    /**
     * Calculates the Class Level Restated 7 Day Yield. Please see the
     * MMandDistFundandClassLevelYieldCalculations.docxfor formula details.
     * @param input the calculation input
     * @return calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    public ClassLevel30DayDistributionYieldCalculationOutput calculate(
            ClassLevel30DayDistributionYieldCalculationInput input) {
        // get parameters from input
        BigDecimal u = input.getDistUnmod30DayYieldPct();
        BigDecimal m = input.getDistYieldMilRt();
        BigDecimal n = input.getNavAmt();
        int d = input.getDaysInYear();
        int r = input.getDayOfReportingDate();
        ClassLevel30DayDistributionYieldCalculationOutput output = new ClassLevel30DayDistributionYieldCalculationOutput();
        // calculate y using formula y=u-(m*d)/(n*r) with the precision and round mode specified in configuration
        BigDecimal y = u.subtract(m.multiply(BigDecimal.valueOf(d)).divide(n.multiply(BigDecimal.valueOf(r)),
                input.getOperationScale(), BigDecimal.ROUND_HALF_UP));
        output.setDerDist30DayYieldPct(y);
        return output;
    }
}
