package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationInput;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevelRestated30DayYieldCalculationInput extends BaseCalculationInput {

    /**
     * Der Mm Restate 1 Day Yield Pct.
     */
    private BigDecimal derMmRestate1DayYieldPct;

    /**
     * Sum Of Der Restate 1 Day Yield Mny Mkt Pct for 29 Previous Days.
     */
    private BigDecimal sumOfDerRestate1DayYieldMnyMktPctPrevious29Days;

    /**
     * Constructor.
     *
     * @param configuration The SEC configuration
     */
    public ClassLevelRestated30DayYieldCalculationInput(SECConfiguration configuration) {
        super(configuration);
    }

    public BigDecimal getDerMmRestate1DayYieldPct() {
        return derMmRestate1DayYieldPct;
    }

    public void setDerMmRestate1DayYieldPct(BigDecimal derMmRestate1DayYieldPct) {
        this.derMmRestate1DayYieldPct = derMmRestate1DayYieldPct;
    }

    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPrevious29Days() {
        return sumOfDerRestate1DayYieldMnyMktPctPrevious29Days;
    }

    public void setSumOfDerRestate1DayYieldMnyMktPctPrevious29Days(
            BigDecimal sumOfDerRestate1DayYieldMnyMktPctPrevious29Days) {
        this.sumOfDerRestate1DayYieldMnyMktPctPrevious29Days = sumOfDerRestate1DayYieldMnyMktPctPrevious29Days;
    }

}
