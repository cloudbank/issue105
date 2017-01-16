package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation output.
 */
public class ClassLevel1DayN1AYieldCalculationOutput {

    /**
     * Yield amount.
     */
    private BigDecimal y;

    public ClassLevel1DayN1AYieldCalculationOutput() {
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
