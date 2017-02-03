package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation output.
 */
public class ClassLevel30DayDistributionYieldCalculationOutput {

	/**
	 * Yield amount.
	 */
	private BigDecimal derDist30DayYieldPct;

	public ClassLevel30DayDistributionYieldCalculationOutput() {
	}

	public BigDecimal getDerDist30DayYieldPct() {
		return derDist30DayYieldPct;
	}

	public void setDerDist30DayYieldPct(BigDecimal derDist30DayYieldPct) {
		this.derDist30DayYieldPct = derDist30DayYieldPct;
	}
}
