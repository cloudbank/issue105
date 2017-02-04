package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Fund Level 1 Day Gross Yield calculation engine. Effectively thread safe after configuration.
 */
public class FundLevel1DayGrossYieldCalculationEngine implements CalculationEngine {

    /**
     * Empty constructor.
     */
    public FundLevel1DayGrossYieldCalculationEngine() {
    }

    /**
     * Calculates the Fund Level 1 Day Gross Yield.
     * @param fundAccountingYieldData the input portfolio;
     * @param configuration the SECConfiguration to be used for config values; if the the config values are provided
     *            they will be used instead of default ones.
     * @return portfolio with calculated result;
     */
    @LogMethod
    public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration) {
        // no need calculation
    }
}
