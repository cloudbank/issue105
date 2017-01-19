package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevelEffectiveCompound7DayN1AYieldCalculationInput {

    /**
     * Der Mny Mkt 7 Day N1A Yield Pct.
     */
    private BigDecimal derMnyMkt7DayN1AYieldPct;

    /**
     * Sum Of Der Mny Mkt 7 Day N1A Yield Pct For Previous 6 Days
     */
    private BigDecimal sumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days;

    /**
     * Operation scale.
     */
    private int operationScale;

    public BigDecimal getDerMnyMkt7DayN1AYieldPct() {
        return derMnyMkt7DayN1AYieldPct;
    }

    public void setDerMnyMkt7DayN1AYieldPct(BigDecimal derMnyMkt7DayN1AYieldPct) {
        this.derMnyMkt7DayN1AYieldPct = derMnyMkt7DayN1AYieldPct;
    }

    public BigDecimal getSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days() {
        return sumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days;
    }

    public void setSumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days(
            BigDecimal sumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days) {
        this.sumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days = sumOfDerMnyMkt7DayN1AYieldPctForPrevious6Days;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }

}
