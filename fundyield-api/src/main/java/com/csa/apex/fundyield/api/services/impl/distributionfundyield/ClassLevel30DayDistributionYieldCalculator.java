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
        BigDecimal distUnmod30DayYieldPct = input.getDistUnmod30DayYieldPct();
        BigDecimal adjDistMilSpikeRt = input.getDistYieldMilRt();
        BigDecimal n = input.getNavAmt();
        int daysInYear = input.getDaysInYear();
        int dayOfReporting = input.getDayOfReportingDate();
        ClassLevel30DayDistributionYieldCalculationOutput output = new ClassLevel30DayDistributionYieldCalculationOutput();
        // calculate y using formula y=u-(m*d)/(n*r) with the precision and round mode specified in configuration
        BigDecimal y = distUnmod30DayYieldPct.subtract(adjDistMilSpikeRt.multiply(BigDecimal.valueOf(daysInYear)).divide(n.multiply(BigDecimal.valueOf(dayOfReporting)),
                input.getOperationScale(), BigDecimal.ROUND_HALF_UP));
        output.setDerDist30DayYieldPct(y);
        return output;
    }
}
