package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation output.
 */
public class ClassLevelRestated7DayYieldCalculationOutput {

    /**
     * Money market restart yield.
     */
    private BigDecimal mil;

    /**
     * Mny Mkt restart 1 day yield.
     */
    private BigDecimal d;

    /**
     * Yield amount.
     */
    private BigDecimal y;

    public ClassLevelRestated7DayYieldCalculationOutput() {
    }

    public BigDecimal getMil() {
        return mil;
    }

    public void setMil(BigDecimal mil) {
        this.mil = mil;
    }

    public BigDecimal getD() {
        return d;
    }

    public void setD(BigDecimal d) {
        this.d = d;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
