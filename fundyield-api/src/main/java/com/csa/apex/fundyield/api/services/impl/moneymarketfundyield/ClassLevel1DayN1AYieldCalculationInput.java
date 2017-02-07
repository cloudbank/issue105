package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationInput;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;

import java.math.BigDecimal;

/**
 * Calculation input.
 */
public class ClassLevel1DayN1AYieldCalculationInput extends BaseCalculationInput {

	/**
	 * N1A Dist Income Unmod Amt.
	 */
	private BigDecimal n1ADistIncomeUnmodAmt;

	/**
	 * N1A Dist Income Str.
	 */
	private BigDecimal n1ADistIncomeStr;

	/**
	 * N1A Dist Income Opct.
	 */
	private BigDecimal n1ADistIncomeOpct;

	/**
	 * N1A Dist Income Adj Amt.
	 */
	private BigDecimal n1ADistIncomeAdjAmt;

	/**
	 * N1A Dist Income Adj Rev Amt
	 */
	private BigDecimal n1ADistIncomeAdjRevAmt;

	/**
	 * N1A Dist Reimbursement Amt.
	 */
	private BigDecimal n1ADistReimbursementAmt;

	/**
	 * N1A Dist Income Breakage Amt
	 */
	private BigDecimal n1ADistIncomeBreakageAmt;

	/**
	 * Distributable Capstock Qty.
	 */
	private BigDecimal distributableCapstockQty;

	/**
	 * Nav Amt.
	 */
	private BigDecimal navAmount;

	/**
	 * Constructor.
	 *
	 * @param configuration The SEC configuration
	 */
	public ClassLevel1DayN1AYieldCalculationInput(SECConfiguration configuration) {
		super(configuration);
	}

	public BigDecimal getN1ADistIncomeStr() {
		return n1ADistIncomeStr;
	}

	public void setN1ADistIncomeStr(BigDecimal n1ADistIncomeStr) {
		this.n1ADistIncomeStr = n1ADistIncomeStr;
	}

	public BigDecimal getN1ADistIncomeOpct() {
		return n1ADistIncomeOpct;
	}

	public void setN1ADistIncomeOpct(BigDecimal n1ADistIncomeOpct) {
		this.n1ADistIncomeOpct = n1ADistIncomeOpct;
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

	public BigDecimal getNavAmount() {
		return navAmount;
	}

	public void setNavAmount(BigDecimal navAmount) {
		this.navAmount = navAmount;
	}

}
