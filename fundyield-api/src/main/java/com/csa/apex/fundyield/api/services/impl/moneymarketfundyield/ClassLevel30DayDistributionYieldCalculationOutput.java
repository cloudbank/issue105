package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation output.
 */
public class ClassLevel30DayDistributionYieldCalculationOutput {

    /**
     * Yield amount.
     */
    private BigDecimal y;

    public ClassLevel30DayDistributionYieldCalculationOutput() {
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
