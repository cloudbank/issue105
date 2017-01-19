/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import java.math.BigDecimal;

/**
 * Tolerance.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class Tolerance extends Versionable {

    /**
     * Surrogate id to uniquely identify a tolerance.
     */
    private long toleranceSid;

    /**
     * Tolerance type code, fund_yield, sec_yield, etc.
     */
    private String toleranceTypeCd;

    /**
     * Description corresponding to the Tolerance Type.
     */
    private String toleranceTypeDsc;

    /**
     * If there is a lower and upper limit, they will show up as two diff rows.
     */
    private BigDecimal toleranceThresholdVal;

    /**
     * Constructor.
     */
    public Tolerance() {
        // default empty constructor
    }

    /**
     * Getter method for property <tt>toleranceSid</tt>.
     * @return property value of toleranceSid
     */
    public long getToleranceSid() {
        return toleranceSid;
    }

    /**
     * Setter method for property <tt>toleranceSid</tt>.
     * @param toleranceSid value to be assigned to property toleranceSid
     */
    public void setToleranceSid(long toleranceSid) {
        this.toleranceSid = toleranceSid;
    }

    /**
     * Getter method for property <tt>toleranceTypeCd</tt>.
     * @return property value of toleranceTypeCd
     */
    public String getToleranceTypeCd() {
        return toleranceTypeCd;
    }

    /**
     * Setter method for property <tt>toleranceTypeCd</tt>.
     * @param toleranceTypeCd value to be assigned to property toleranceTypeCd
     */
    public void setToleranceTypeCd(String toleranceTypeCd) {
        this.toleranceTypeCd = toleranceTypeCd;
    }

    /**
     * Getter method for property <tt>toleranceTypeDsc</tt>.
     * @return property value of toleranceTypeDsc
     */
    public String getToleranceTypeDsc() {
        return toleranceTypeDsc;
    }

    /**
     * Setter method for property <tt>toleranceTypeDsc</tt>.
     * @param toleranceTypeDsc value to be assigned to property toleranceTypeDsc
     */
    public void setToleranceTypeDsc(String toleranceTypeDsc) {
        this.toleranceTypeDsc = toleranceTypeDsc;
    }

    /**
     * Getter method for property <tt>toleranceThresholdVal</tt>.
     * @return property value of toleranceThresholdVal
     */
    public BigDecimal getToleranceThresholdVal() {
        return toleranceThresholdVal;
    }

    /**
     * Setter method for property <tt>toleranceThresholdVal</tt>.
     * @param toleranceThresholdVal value to be assigned to property toleranceThresholdVal
     */
    public void setToleranceThresholdVal(BigDecimal toleranceThresholdVal) {
        this.toleranceThresholdVal = toleranceThresholdVal;
    }
}
