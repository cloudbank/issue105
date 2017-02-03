package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation output.
 */
public class ClassLevel1DayN1AYieldCalculationOutput {

	/**
	 * Yield amount.
	 */
	private BigDecimal derMm1DayN1aYieldPct;

	public ClassLevel1DayN1AYieldCalculationOutput() {
	}

	public BigDecimal getDerMm1DayN1aYieldPct() {
		return derMm1DayN1aYieldPct;
	}

	public void setDerMm1DayN1aYieldPct(BigDecimal derMm1DayN1aYieldPct) {
		this.derMm1DayN1aYieldPct = derMm1DayN1aYieldPct;
	}
}
