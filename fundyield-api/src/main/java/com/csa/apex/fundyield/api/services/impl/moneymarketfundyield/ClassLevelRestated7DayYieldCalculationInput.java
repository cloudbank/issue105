package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevelRestated7DayYieldCalculationInput {

	/**
	 * N1A Dist Income Unmod Amt
	 */
	private BigDecimal n1ADistIncomeUnmodAmt;

	/**
	 * N1A Dist Income Adj Amt
	 */
	private BigDecimal n1ADistIncomeAdjAmt;

	/**
	 * N1A Dist Income Adj Rev Amt
	 */
	private BigDecimal n1ADistIncomeAdjRevAmt;

	/**
	 * N1A Dist Reimbursement Amt
	 */
	private BigDecimal n1ADistReimbursementAmt;

	/**
	 * N1A Dist Income Breakage Amt
	 */
	private BigDecimal n1ADistIncomeBreakageAmt;

	/**
	 * Distributable Capstock Qty
	 */
	private BigDecimal distributableCapstockQty;

	/**
	 * Nav Amt
	 */
	private BigDecimal navAmt;

	/**
	 * N1A Reimbursement Earned Amt
	 */
	private BigDecimal n1AReimbursementEarnedAmt;

	/**
	 * Sum Of Der Restate 1 Day Yield Mny Mkt Pct Previous Days
	 */
	private BigDecimal dPrevious6Days;

	/**
	 * N1A Reimbursement str
	 */
	private BigDecimal n1AReimbursementStr;

	/**
	 * N1A Reimbursement opct
	 */
	private BigDecimal n1AReimbursementOpct;

	/**
	 * Operation scale.
	 */
	private int operationScale;

	public ClassLevelRestated7DayYieldCalculationInput() {
	}

	public BigDecimal getN1AReimbursementStr() {
		return n1AReimbursementStr;
	}

	public void setN1AReimbursementStr(BigDecimal n1AReimbursementStr) {
		this.n1AReimbursementStr = n1AReimbursementStr;
	}

	public BigDecimal getN1AReimbursementOpct() {
		return n1AReimbursementOpct;
	}

	public void setN1AReimbursementOpct(BigDecimal n1AReimbursementOpct) {
		this.n1AReimbursementOpct = n1AReimbursementOpct;
	}

	public BigDecimal getN1ADistIncomeUnmodAmt() {
		return n1ADistIncomeUnmodAmt;
	}

	public void setN1ADistIncomeUnmodAmt(BigDecimal n1ADistIncomeUnmodAmt) {
		this.n1ADistIncomeUnmodAmt = n1ADistIncomeUnmodAmt;
	}

	public BigDecimal getN1ADistIncomeAdjAmt() {
		return n1ADistIncomeAdjAmt;
	}

	public void setN1ADistIncomeAdjAmt(BigDecimal n1ADistIncomeAdjAmt) {
		this.n1ADistIncomeAdjAmt = n1ADistIncomeAdjAmt;
	}

	public BigDecimal getN1ADistIncomeAdjRevAmt() {
		return n1ADistIncomeAdjRevAmt;
	}

	public void setN1ADistIncomeAdjRevAmt(BigDecimal n1ADistIncomeAdjRevAmt) {
		this.n1ADistIncomeAdjRevAmt = n1ADistIncomeAdjRevAmt;
	}

	public BigDecimal getN1ADistReimbursementAmt() {
		return n1ADistReimbursementAmt;
	}

	public void setN1ADistReimbursementAmt(BigDecimal n1ADistReimbursementAmt) {
		this.n1ADistReimbursementAmt = n1ADistReimbursementAmt;
	}

	public BigDecimal getN1ADistIncomeBreakageAmt() {
		return n1ADistIncomeBreakageAmt;
	}

	public void setN1ADistIncomeBreakageAmt(BigDecimal n1ADistIncomeBreakageAmt) {
		this.n1ADistIncomeBreakageAmt = n1ADistIncomeBreakageAmt;
	}

	public BigDecimal getDistributableCapstockQty() {
		return distributableCapstockQty;
	}

	public void setDistributableCapstockQty(BigDecimal distributableCapstockQty) {
		this.distributableCapstockQty = distributableCapstockQty;
	}

	public BigDecimal getNavAmt() {
		return navAmt;
	}

	public void setNavAmt(BigDecimal navAmt) {
		this.navAmt = navAmt;
	}

	public BigDecimal getN1AReimbursementEarnedAmt() {
		return n1AReimbursementEarnedAmt;
	}

	public void setN1AReimbursementEarnedAmt(BigDecimal n1AReimbursementEarnedAmt) {
		this.n1AReimbursementEarnedAmt = n1AReimbursementEarnedAmt;
	}

	public BigDecimal getdPrevious6Days() {
		return dPrevious6Days;
	}

	public void setdPrevious6Days(BigDecimal dPrevious6Days) {
		this.dPrevious6Days = dPrevious6Days;
	}

	public int getOperationScale() {
		return operationScale;
	}

	public void setOperationScale(int operationScale) {
		this.operationScale = operationScale;
	}
}
