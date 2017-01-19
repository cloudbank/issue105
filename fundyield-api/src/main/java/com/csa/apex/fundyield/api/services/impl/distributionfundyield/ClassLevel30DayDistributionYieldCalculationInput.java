package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel30DayDistributionYieldCalculationInput {

    /**
     * Dist Unmod 30 Day Yield Pct;
     */
    private BigDecimal distUnmod30DayYieldPct;

    /**
     * Dist Yield Mil Rt
     */
    private BigDecimal distYieldMilRt;

    /**
     * Nav Amt.
     */
    private BigDecimal navAmt;

    /**
     * Operation scale.
     */
    private int operationScale;

    /**
     * Day of reporting date.
     */
    private int dayOfReportingDate;

    /**
     * Days in year.
     */
    private int daysInYear;

    public BigDecimal getDistUnmod30DayYieldPct() {
        return distUnmod30DayYieldPct;
    }

    public void setDistUnmod30DayYieldPct(BigDecimal distUnmod30DayYieldPct) {
        this.distUnmod30DayYieldPct = distUnmod30DayYieldPct;
    }

    public BigDecimal getDistYieldMilRt() {
        return distYieldMilRt;
    }

    public void setDistYieldMilRt(BigDecimal distYieldMilRt) {
        this.distYieldMilRt = distYieldMilRt;
    }

    public BigDecimal getNavAmt() {
        return navAmt;
    }

    public void setNavAmt(BigDecimal navAmt) {
        this.navAmt = navAmt;
    }

    public int getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(int operationScale) {
        this.operationScale = operationScale;
    }

    public int getDayOfReportingDate() {
        return dayOfReportingDate;
    }

    public void setDayOfReportingDate(int dayOfReportingDate) {
        this.dayOfReportingDate = dayOfReportingDate;
    }

    public int getDaysInYear() {
        return daysInYear;
    }

    public void setDaysInYear(int daysInYear) {
        this.daysInYear = daysInYear;
    }

}
