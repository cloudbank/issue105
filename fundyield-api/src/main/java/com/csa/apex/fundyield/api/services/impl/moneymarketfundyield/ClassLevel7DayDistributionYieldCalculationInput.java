package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationInput;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel7DayDistributionYieldCalculationInput extends BaseCalculationInput {

    /**
     * Mny Mkt 1 Day Dist Yield Pct.
     */
    private BigDecimal mnyMkt1DayDistYieldPct;

    /**
     * Sum Of Mny Mkt 1 Day Dist Yield Pct For Previous 6 Days
     */
    private BigDecimal sumOfMnyMkt1DayDistYieldPctForPrevious6Days;

    /**
     * Constructor.
     *
     * @param configuration The SEC configuration
     */
    public ClassLevel7DayDistributionYieldCalculationInput(SECConfiguration configuration) {
        super(configuration);
    }

    public BigDecimal getMnyMkt1DayDistYieldPct() {
        return mnyMkt1DayDistYieldPct;
    }

    public void setMnyMkt1DayDistYieldPct(BigDecimal mnyMkt1DayDistYieldPct) {
        this.mnyMkt1DayDistYieldPct = mnyMkt1DayDistYieldPct;
    }

    public BigDecimal getSumOfMnyMkt1DayDistYieldPctForPrevious6Days() {
        return sumOfMnyMkt1DayDistYieldPctForPrevious6Days;
    }

    public void setSumOfMnyMkt1DayDistYieldPctForPrevious6Days(BigDecimal sumOfMnyMkt1DayDistYieldPctForPrevious6Days) {
        this.sumOfMnyMkt1DayDistYieldPctForPrevious6Days = sumOfMnyMkt1DayDistYieldPctForPrevious6Days;
    }

}
