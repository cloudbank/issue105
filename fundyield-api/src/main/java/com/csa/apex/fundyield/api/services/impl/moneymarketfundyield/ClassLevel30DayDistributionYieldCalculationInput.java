package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel30DayDistributionYieldCalculationInput {

    /**
     * Dist Unmod 30 Day Yield Pct;
     */
    private BigDecimal u;

    /**
     * Dist Yield Mil Rt
     */
    private BigDecimal m;

    /**
     * Dist Yield D
     */
    private int d;

    /**
     * Dist Yield R
     */
    private int r;

    /**
     * Nav Amt.
     */
    private BigDecimal n;

    /**
     * Operation scale.
     */
    private int operationScale;

    public ClassLevel30DayDistributionYieldCalculationInput() {
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public BigDecimal getU() {
        return u;
    }

    public void setU(BigDecimal u) {
        this.u = u;
    }

    public BigDecimal getM() {
        return m;
    }

    public void setM(BigDecimal m) {
        this.m = m;
    }

    public BigDecimal getN() {
        return n;
    }

    public void setN(BigDecimal n) {
        this.n = n;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }
}
