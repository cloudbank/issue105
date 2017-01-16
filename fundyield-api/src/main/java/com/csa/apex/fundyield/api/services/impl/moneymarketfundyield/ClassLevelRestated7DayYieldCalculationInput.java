package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevelRestated7DayYieldCalculationInput {

    /**
     * N1A Dist Income Unmod Amt
     */
    private BigDecimal tni;

    /**
     * N1A Dist Income Adj Amt
     */
    private BigDecimal da;

    /**
     * N1A Dist Income Adj Rev Amt
     */
    private BigDecimal rda;

    /**
     * N1A Dist Reimbursement Amt
     */
    private BigDecimal mda;

    /**
     * N1A Dist Income Breakage Amt
     */
    private BigDecimal b;

    /**
     * Distributable Capstock Qty
     */
    private BigDecimal so;

    /**
     * Nav Amt
     */
    private BigDecimal nv;

    /**
     * N1A Reimbursement Earned Amt
     */
    private BigDecimal reim;

    /**
     * Sum Of Der Restate 1 Day Yield Mny Mkt Pct Previous Days
     */
    private BigDecimal dPrevious6Days;

    /**
     * N1A Reimbursement str
     */
    private BigDecimal str;

    /**
     * N1A Reimbursement opct
     */
    private BigDecimal opct;

    /**
     * Operation scale.
     */
    private int operationScale;

    public ClassLevelRestated7DayYieldCalculationInput() {
    }

    public BigDecimal getStr() {
        return str;
    }

    public void setStr(BigDecimal str) {
        this.str = str;
    }

    public BigDecimal getOpct() {
        return opct;
    }

    public void setOpct(BigDecimal opct) {
        this.opct = opct;
    }

    public BigDecimal getTni() {
        return tni;
    }

    public void setTni(BigDecimal tni) {
        this.tni = tni;
    }

    public BigDecimal getDa() {
        return da;
    }

    public void setDa(BigDecimal da) {
        this.da = da;
    }

    public BigDecimal getRda() {
        return rda;
    }

    public void setRda(BigDecimal rda) {
        this.rda = rda;
    }

    public BigDecimal getMda() {
        return mda;
    }

    public void setMda(BigDecimal mda) {
        this.mda = mda;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }

    public BigDecimal getSo() {
        return so;
    }

    public void setSo(BigDecimal so) {
        this.so = so;
    }

    public BigDecimal getNv() {
        return nv;
    }

    public void setNv(BigDecimal nv) {
        this.nv = nv;
    }

    public BigDecimal getReim() {
        return reim;
    }

    public void setReim(BigDecimal reim) {
        this.reim = reim;
    }

    public BigDecimal getdPrevious6Days() {
        return dPrevious6Days;
    }

    public void setdPrevious6Days(BigDecimal dPrevious6Days) {
        this.dPrevious6Days = dPrevious6Days;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }
}
