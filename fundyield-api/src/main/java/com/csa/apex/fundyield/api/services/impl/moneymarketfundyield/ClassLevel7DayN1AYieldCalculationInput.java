package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel7DayN1AYieldCalculationInput {

    /**
     * Der Mny Mkt 1 Day N1A Yield Pct
     */
    private BigDecimal derMnyMkt1DayN1AYieldPct;

    /**
     * Sum Of Der 1 Day Yield N1A Mny Mkt Pct Previous 6 Days.
     */
    private BigDecimal sumOfDer1DayYieldN1AMnyMktPctPrevious6Days;

    /**
     * Operation scale.
     */
    private int operationScale;

    public BigDecimal getDerMnyMkt1DayN1AYieldPct() {
        return derMnyMkt1DayN1AYieldPct;
    }

    public void setDerMnyMkt1DayN1AYieldPct(BigDecimal derMnyMkt1DayN1AYieldPct) {
        this.derMnyMkt1DayN1AYieldPct = derMnyMkt1DayN1AYieldPct;
    }

    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPrevious6Days() {
        return sumOfDer1DayYieldN1AMnyMktPctPrevious6Days;
    }

    public void setSumOfDer1DayYieldN1AMnyMktPctPrevious6Days(BigDecimal sumOfDer1DayYieldN1AMnyMktPctPrevious6Days) {
        this.sumOfDer1DayYieldN1AMnyMktPctPrevious6Days = sumOfDer1DayYieldN1AMnyMktPctPrevious6Days;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }

}
