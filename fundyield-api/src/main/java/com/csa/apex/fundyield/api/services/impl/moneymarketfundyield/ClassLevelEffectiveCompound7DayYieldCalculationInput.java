package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevelEffectiveCompound7DayYieldCalculationInput {

    /**
     * Der Mny Mkt 7 Day Yield Pct.
     */
    private BigDecimal derMnyMkt7DayYieldPct;

    /**
     * Sum of Der Mny Mkt 7 Day Yield Pct.
     */
    private BigDecimal sumOfDerMnyMkt7DayYieldPctForPrevious6Days;

    /**
     * Operation scale.
     */
    private int operationScale;

    public BigDecimal getDerMnyMkt7DayYieldPct() {
        return derMnyMkt7DayYieldPct;
    }

    public void setDerMnyMkt7DayYieldPct(BigDecimal derMnyMkt7DayYieldPct) {
        this.derMnyMkt7DayYieldPct = derMnyMkt7DayYieldPct;
    }

    public BigDecimal getSumOfDerMnyMkt7DayYieldPctForPrevious6Days() {
        return sumOfDerMnyMkt7DayYieldPctForPrevious6Days;
    }

    public void setSumOfDerMnyMkt7DayYieldPctForPrevious6Days(BigDecimal sumOfDerMnyMkt7DayYieldPctForPrevious6Days) {
        this.sumOfDerMnyMkt7DayYieldPctForPrevious6Days = sumOfDerMnyMkt7DayYieldPctForPrevious6Days;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }

}
