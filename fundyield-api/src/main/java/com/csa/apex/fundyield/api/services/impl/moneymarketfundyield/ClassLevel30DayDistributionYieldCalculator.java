package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;

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
        CommonUtility.checkNull(input, "input");
        // calculate y using formula y=u-(m*d)/(n*r)
        BigDecimal y = input.getU()
                .subtract(input.getM().multiply(BigDecimal.valueOf(input.getD())).divide(
                        input.getN().multiply(BigDecimal.valueOf(input.getR())), input.getOperationScale(),
                        BigDecimal.ROUND_HALF_UP));
        ClassLevel30DayDistributionYieldCalculationOutput output = new ClassLevel30DayDistributionYieldCalculationOutput();
        output.setY(y);
        return output;
    }
}
