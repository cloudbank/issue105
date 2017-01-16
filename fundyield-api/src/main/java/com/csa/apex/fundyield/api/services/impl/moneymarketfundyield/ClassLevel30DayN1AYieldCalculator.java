package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.utility.CommonUtility;

/**
 * The calculator.
 */
public class ClassLevel30DayN1AYieldCalculator {

    /**
     * Empty constructor.
     */
    public ClassLevel30DayN1AYieldCalculator() {
    }

    /**
     * Calculates the Class Level 1 Day N1A Yield.
     * @param input the calculation input
     * @return calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    public ClassLevel30DayN1AYieldCalculationOutput calculate(ClassLevel30DayN1AYieldCalculationInput input) {
        CommonUtility.checkNull(input, "input");
        BigDecimal d = input.getDerMnyMkt1DayN1AYieldPct();
        BigDecimal dPrevios29Days = input.getSumOfDer1DayYieldN1AMnyMktPctPrevious29Days();
        BigDecimal y = d.add(dPrevios29Days).divide(BigDecimal.valueOf(30), input.getOperationScale(),
                BigDecimal.ROUND_HALF_UP);
        ClassLevel30DayN1AYieldCalculationOutput output = new ClassLevel30DayN1AYieldCalculationOutput();
        output.setDerMnyMkt1DayN1AYieldPct(y);
        return output;
    }
}
