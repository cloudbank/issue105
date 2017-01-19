package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 */
public class ClassLevelRestated7DayYieldCalculator {

    /**
     * Empty constructor.
     */
    public ClassLevelRestated7DayYieldCalculator() {
    }

    /**
     * Calculates the Class Level Restated 7 Day Yield. Please see the
     * MMandDistFundandClassLevelYieldCalculations.docxfor formula details.
     * @param input the calculation input
     * @return calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @LogMethod
    public ClassLevelRestated7DayYieldCalculationOutput calculate(ClassLevelRestated7DayYieldCalculationInput input) {
        CommonUtility.checkNull(input, "input");
        // calculate mil using formula MIL=((TNI+DA+RDA+MDA-B-TNI*STR*OPCT-REIM))/SO with the precision and round mode
        // specified in configuration
        // calculate d using formula D=(MIL*36500)/NV with the precision and round mode specified in configuration
        // calculate y using formula y=(d+dPrevious6days)/7;
        BigDecimal mil = input.getTni().add(input.getDa()).add(input.getRda()).add(input.getMda());
        mil = mil.subtract(input.getB()).subtract(input.getTni().multiply(input.getStr()).multiply(input.getOpct()))
                .subtract(input.getReim()).divide(input.getSo(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
        BigDecimal d = mil.multiply(BigDecimal.valueOf(36500)).divide(input.getNv(), input.getOperationScale(),
                BigDecimal.ROUND_HALF_UP);
        BigDecimal y = d.add(input.getdPrevious6Days()).divide(BigDecimal.valueOf(7), input.getOperationScale(),
                BigDecimal.ROUND_HALF_UP);
        ClassLevelRestated7DayYieldCalculationOutput output = new ClassLevelRestated7DayYieldCalculationOutput();
        output.setMil(mil);
        output.setD(d);
        output.setY(y);
        return output;
    }
}
