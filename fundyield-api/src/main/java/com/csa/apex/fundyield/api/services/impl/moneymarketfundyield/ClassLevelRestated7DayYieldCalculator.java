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
        BigDecimal derMmRestatedMilRt = input.getN1ADistIncomeUnmodAmt().add(input.getN1ADistIncomeAdjAmt()).add(input.getN1ADistIncomeAdjRevAmt()).add(input.getN1ADistReimbursementAmt());
        derMmRestatedMilRt = derMmRestatedMilRt.subtract(input.getN1ADistIncomeBreakageAmt()).subtract(input.getN1ADistIncomeUnmodAmt().multiply(input.getN1AReimbursementStr()).multiply(input.getN1AReimbursementOpct()))
                .subtract(input.getN1AReimbursementEarnedAmt()).divide(input.getDistributableCapstockQty(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
        BigDecimal derMmRestate1DayYieldPct = derMmRestatedMilRt.multiply(BigDecimal.valueOf(36500)).divide(input.getNavAmt(), input.getOperationScale(),
                BigDecimal.ROUND_HALF_UP);
        BigDecimal derMmRst7DayYieldPOct = derMmRestate1DayYieldPct.add(input.getdPrevious6Days()).divide(BigDecimal.valueOf(7), input.getOperationScale(),
                BigDecimal.ROUND_HALF_UP);
        ClassLevelRestated7DayYieldCalculationOutput output = new ClassLevelRestated7DayYieldCalculationOutput();
        output.setMoneyMarketRestartYield(derMmRestatedMilRt);
        output.setD(derMmRestate1DayYieldPct);
        output.setDerMmRst7DayYieldPct(derMmRst7DayYieldPOct);
        return output;
    }
}
