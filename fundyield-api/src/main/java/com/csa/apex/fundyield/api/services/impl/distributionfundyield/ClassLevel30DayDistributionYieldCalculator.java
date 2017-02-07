package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;

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
     * @param input the calculation input
     * @return calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    public ClassLevel30DayDistributionYieldCalculationOutput calculate(
            ClassLevel30DayDistributionYieldCalculationInput input) {
    	CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, Constants.PARAMETER_INPUT);
        
    	// get parameters from input
        BigDecimal distUnmod30DayYieldPct = input.getDistUnmod30DayYieldPct(); // U
        BigDecimal adjDistMilSpikeRt = input.getDistYieldMilRt(); // M
        BigDecimal navAmt = input.getNavAmt(); // N
        int daysInYear = input.getDaysInYear(); // D
        int dayOfReporting = input.getDayOfReportingDate(); // R
        BigDecimal dividend = adjDistMilSpikeRt.multiply(BigDecimal.valueOf(daysInYear));
        BigDecimal divisor = navAmt.multiply(BigDecimal.valueOf(dayOfReporting));
        BigDecimal divideTmpValue = dividend.divide(divisor, input.getOperationScale(), BigDecimal.ROUND_HALF_UP);

        // calculate y using formula y=u-(m*d)/(n*r) with the precision and round mode specified in configuration
        BigDecimal derDist30DayYieldPct = distUnmod30DayYieldPct.subtract(divideTmpValue);

        ClassLevel30DayDistributionYieldCalculationOutput output = new ClassLevel30DayDistributionYieldCalculationOutput();
        output.setDerDist30DayYieldPct(derDist30DayYieldPct);
        return output;
    }
}
