package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel1DayN1AYieldCalculationInput {

    /**
     * N1A Dist Income Unmod Amt.
     */
    private BigDecimal tni;

    /**
     * N1A Dist Income Str.
     */
    private BigDecimal str;

    /**
     * N1A Dist Income Opct.
     */
    private BigDecimal opct;

    /**
     * N1A Dist Income Adj Amt.
     */
    private BigDecimal da;

    /**
     * N1A Dist Income Adj Rev Amt
     */
    private BigDecimal rda;

    /**
     * N1A Dist Reimbursement Amt.
     */
    private BigDecimal mda;

    /**
     * N1A Dist Income Breakage Amt
     */
    private BigDecimal b;

    /**
     * Distributable Capstock Qty.
     */
    private BigDecimal so;

    /**
     * Nav Amt.
     */
    private BigDecimal nv;

    /**
     * Operation scale.
     */
    private int operationScale;

    public ClassLevel1DayN1AYieldCalculationInput() {
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

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }
}