package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevelRestated30DayYieldCalculationInput {

    /**
     * Der Mm Restate 1 Day Yield Pct.
     */
    private BigDecimal derMmRestate1DayYieldPct;

    /**
     * Sum Of Der Restate 1 Day Yield Mny Mkt Pct for 29 Previous Days.
     */
    private BigDecimal sumOfDerRestate1DayYieldMnyMktPctPrevious29Days;

    /**
     * Operation scale.
     */
    private int operationScale;

    public BigDecimal getDerMmRestate1DayYieldPct() {
        return derMmRestate1DayYieldPct;
    }

    public void setDerMmRestate1DayYieldPct(BigDecimal derMmRestate1DayYieldPct) {
        this.derMmRestate1DayYieldPct = derMmRestate1DayYieldPct;
    }

    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPrevious29Days() {
        return sumOfDerRestate1DayYieldMnyMktPctPrevious29Days;
    }

    public void setSumOfDerRestate1DayYieldMnyMktPctPrevious29Days(
            BigDecimal sumOfDerRestate1DayYieldMnyMktPctPrevious29Days) {
        this.sumOfDerRestate1DayYieldMnyMktPctPrevious29Days = sumOfDerRestate1DayYieldMnyMktPctPrevious29Days;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }

}
