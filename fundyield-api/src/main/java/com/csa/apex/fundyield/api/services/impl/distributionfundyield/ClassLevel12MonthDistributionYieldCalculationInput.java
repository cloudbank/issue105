package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationInput;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel12MonthDistributionYieldCalculationInput extends BaseCalculationInput {

    /**
     * Dist 12 Mo Mil Rt.
     */
    private BigDecimal dist12MoMilRt;

    /**
     * Nav amt.
     */
    private BigDecimal navAmt;

    /**
     * Constructor.
     *
     * @param configuration The SEC configuration
     */
    public ClassLevel12MonthDistributionYieldCalculationInput(SECConfiguration configuration) {
        super(configuration);
    }

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

}
