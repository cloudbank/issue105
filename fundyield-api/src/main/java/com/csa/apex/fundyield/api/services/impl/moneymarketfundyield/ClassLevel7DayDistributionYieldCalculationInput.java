package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel7DayDistributionYieldCalculationInput {

    /**
     * Mny Mkt 1 Day Dist Yield Pct.
     */
    private BigDecimal mnyMkt1DayDistYieldPct;

    /**
     * Sum Of Mny Mkt 1 Day Dist Yield Pct For Previous 6 Days
     */
    private BigDecimal sumOfMnyMkt1DayDistYieldPctForPrevious6Days;

    /**
     * Operation scale.
     */
    private int operationScale;

    public BigDecimal getMnyMkt1DayDistYieldPct() {
        return mnyMkt1DayDistYieldPct;
    }

    public void setMnyMkt1DayDistYieldPct(BigDecimal mnyMkt1DayDistYieldPct) {
        this.mnyMkt1DayDistYieldPct = mnyMkt1DayDistYieldPct;
    }

    public BigDecimal getSumOfMnyMkt1DayDistYieldPctForPrevious6Days() {
        return sumOfMnyMkt1DayDistYieldPctForPrevious6Days;
    }

    public void setSumOfMnyMkt1DayDistYieldPctForPrevious6Days(BigDecimal sumOfMnyMkt1DayDistYieldPctForPrevious6Days) {
        this.sumOfMnyMkt1DayDistYieldPctForPrevious6Days = sumOfMnyMkt1DayDistYieldPctForPrevious6Days;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }

}
