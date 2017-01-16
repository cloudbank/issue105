package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel12MonthDistributionYieldCalculationInput {

    /**
     * Dist 12 Mo Mil Rt.
     */
    private BigDecimal dist12MoMilRt;

    /**
     * Nav amt.
     */
    private BigDecimal navAmt;

    /**
     * Operation scale.
     */
    private int operationScale;

    public BigDecimal getDist12MoMilRt() {
        return dist12MoMilRt;
    }

    public void setDist12MoMilRt(BigDecimal dist12MoMilRt) {
        this.dist12MoMilRt = dist12MoMilRt;
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

}
