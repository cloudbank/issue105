package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation output.
 */
public class ClassLevelRestated7DayYieldCalculationOutput {

	/**
	 * Money market restart yield.
	 */
	private BigDecimal moneyMarketRestartYield;

	/**
	 * Mny Mkt restart 1 day yield.
	 */
	private BigDecimal moneyMarketRestart1DayYield;

	/**
	 * Yield amount.
	 */
	private BigDecimal derMmRst7DayYieldPct;

	public ClassLevelRestated7DayYieldCalculationOutput() {
	}

	public BigDecimal getMoneyMarketRestartYield() {
		return moneyMarketRestartYield;
	}

	public void setMoneyMarketRestartYield(BigDecimal moneyMarketRestartYield) {
		this.moneyMarketRestartYield = moneyMarketRestartYield;
	}

	public BigDecimal getMoneyMarketRestart1DayYield() {
		return moneyMarketRestart1DayYield;
	}

	public void setMoneyMarketRestart1DayYield(BigDecimal moneyMarketRestart1DayYield) {
		this.moneyMarketRestart1DayYield = moneyMarketRestart1DayYield;
	}

	public BigDecimal getDerMmRst7DayYieldPct() {
		return derMmRst7DayYieldPct;
	}

	public void setDerMmRst7DayYieldPct(BigDecimal derMmRst7DayYieldPct) {
		this.derMmRst7DayYieldPct = derMmRst7DayYieldPct;
	}
}
