package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationInput;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel7DayN1AYieldCalculationInput extends BaseCalculationInput {

    /**
     * Der Mny Mkt 1 Day N1A Yield Pct
     */
    private BigDecimal derMnyMkt1DayN1AYieldPct;

    /**
     * Sum Of Der 1 Day Yield N1A Mny Mkt Pct Previous 6 Days.
     */
    private BigDecimal sumOfDer1DayYieldN1AMnyMktPctPrevious6Days;

    /**
     * Constructor.
     *
     * @param configuration The SEC configuration
     */
    public ClassLevel7DayN1AYieldCalculationInput(SECConfiguration configuration) {
        super(configuration);
    }

    public BigDecimal getDerMnyMkt1DayN1AYieldPct() {
        return derMnyMkt1DayN1AYieldPct;
    }

    public void setDerMnyMkt1DayN1AYieldPct(BigDecimal derMnyMkt1DayN1AYieldPct) {
        this.derMnyMkt1DayN1AYieldPct = derMnyMkt1DayN1AYieldPct;
    }

    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPrevious6Days() {
        return sumOfDer1DayYieldN1AMnyMktPctPrevious6Days;
    }

    public void setSumOfDer1DayYieldN1AMnyMktPctPrevious6Days(BigDecimal sumOfDer1DayYieldN1AMnyMktPctPrevious6Days) {
        this.sumOfDer1DayYieldN1AMnyMktPctPrevious6Days = sumOfDer1DayYieldN1AMnyMktPctPrevious6Days;
    }

}
