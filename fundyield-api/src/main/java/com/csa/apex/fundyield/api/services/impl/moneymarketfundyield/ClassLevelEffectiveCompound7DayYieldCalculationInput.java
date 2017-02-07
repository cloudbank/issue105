package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationInput;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevelEffectiveCompound7DayYieldCalculationInput extends BaseCalculationInput {

    /**
     * Der Mny Mkt 7 Day Yield Pct.
     */
    private BigDecimal derMnyMkt7DayYieldPct;

    /**
     * Sum of Der Mny Mkt 7 Day Yield Pct.
     */
    private BigDecimal sumOfDerMnyMkt7DayYieldPctForPrevious6Days;

    /**
     * Constructor.
     *
     * @param configuration The SEC configuration
     */
    public ClassLevelEffectiveCompound7DayYieldCalculationInput(SECConfiguration configuration) {
        super(configuration);
    }

    public BigDecimal getDerMnyMkt7DayYieldPct() {
        return derMnyMkt7DayYieldPct;
    }

    public void setDerMnyMkt7DayYieldPct(BigDecimal derMnyMkt7DayYieldPct) {
        this.derMnyMkt7DayYieldPct = derMnyMkt7DayYieldPct;
    }

    public BigDecimal getSumOfDerMnyMkt7DayYieldPctForPrevious6Days() {
        return sumOfDerMnyMkt7DayYieldPctForPrevious6Days;
    }

    public void setSumOfDerMnyMkt7DayYieldPctForPrevious6Days(BigDecimal sumOfDerMnyMkt7DayYieldPctForPrevious6Days) {
        this.sumOfDerMnyMkt7DayYieldPctForPrevious6Days = sumOfDerMnyMkt7DayYieldPctForPrevious6Days;
    }

}
