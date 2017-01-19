package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level 1 Day Mil Rate calculation engine. Effectively thread safe after configuration.
 */
public class ClassLevel1DayMilRateCalculationEngine implements CalculationEngine {

    /**
     * Empty constructor.
     */
    public ClassLevel1DayMilRateCalculationEngine() {
    }

    /**
     * Calculates the Class Level 1 Day Mil Rate.
     * @param fundAccountingYieldData the input FundAccountingYieldData;
     * @param configuration the SECConfiguration to be used for config values; if the the config values are provided
     *            they will be used instead of default ones.
     */
    @LogMethod
    public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration) {
        // no need calculation
    }
}
