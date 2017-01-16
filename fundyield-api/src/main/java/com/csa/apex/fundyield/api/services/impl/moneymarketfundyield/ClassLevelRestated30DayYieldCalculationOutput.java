package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation output.
 */
public class ClassLevelRestated30DayYieldCalculationOutput {

    /**
     * The yield amount.
     */
    private BigDecimal derMnyMktRst30DayYieldPctBigDecimal;

    public BigDecimal getDerMnyMktRst30DayYieldPctBigDecimal() {
        return derMnyMktRst30DayYieldPctBigDecimal;
    }

    public void setDerMnyMktRst30DayYieldPctBigDecimal(BigDecimal derMnyMktRst30DayYieldPctBigDecimal) {
        this.derMnyMktRst30DayYieldPctBigDecimal = derMnyMktRst30DayYieldPctBigDecimal;
    }

}
