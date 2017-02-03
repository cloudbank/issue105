package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level 1 Day N1A Yield calculation engine. Effectively thread safe after
 * configuration.
 */
public class ClassLevel1DayN1AYieldCalculationEngine implements CalculationEngine {

	/**
	 * Empty constructor.
	 */
	public ClassLevel1DayN1AYieldCalculationEngine() {
	}

	/**
	 * Calculates the Class Level 1 Day N1A Yield.
	 * 
	 * @param fundAccountingYieldData
	 *            the input FundAccountingYieldData;
	 * @param configuration
	 *            the SECConfiguration to be used for config values; if the the
	 *            config values are provided they will be used instead of
	 *            default ones.
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	@LogMethod
	public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration)
			throws CalculationException {
		CommonUtility.checkNull(fundAccountingYieldData, "Parameter fundAccountingYieldData");
		CommonUtility.checkNull(configuration, "Parameter configuration");
		try {
			ClassLevel1DayN1AYieldCalculator calculator = new ClassLevel1DayN1AYieldCalculator();
			for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
				Date reportDate = fundAccountingYieldData.getReportDate();
				PortfolioSnapshot portfolioSnapshot = portfolio.getPortfolioSnapshots().get(0);
				BigDecimal fdrPortfolioStateTaxRt = portfolioSnapshot.getFdrPortfolioStateTaxRt();
				BigDecimal fdrN1AOospGrosDistInc = portfolioSnapshot.getFdrN1AOospGrosDistInc();
				for (ShareClass shareClass : portfolio.getShareClasses()) {
					// get share class snapshot for the report date
					List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
					Predicate<ShareClassSnapshot> predicate = shareClassSnapshot -> shareClassSnapshot.getReportDate()
							.equals(reportDate);
					ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
					ClassLevel1DayN1AYieldCalculationInput input = new ClassLevel1DayN1AYieldCalculationInput();
					input.setN1ADistIncomeUnmodAmt(snapshot.getN1ADistIncomeUnmodAmt());
					input.setN1ADistIncomeAdjAmt(snapshot.getN1ADistIncomeAdjAmt());
					input.setN1ADistIncomeAdjRevAmt(snapshot.getN1ADistIncomeAdjRevAmt());
					input.setN1ADistReimbursementAmt(snapshot.getN1ADistReimbursementAmt());
					input.setN1ADistIncomeBreakageAmt(snapshot.getN1ADistIncomeBreakageAmt());
					input.setDistributableCapstockQty(snapshot.getDistributableCapstockQty());
					input.setNavAmount(snapshot.getNavAmt());
					input.setN1ADistIncomeOpct(fdrN1AOospGrosDistInc);
					input.setN1ADistIncomeStr(fdrPortfolioStateTaxRt);

					ClassLevel1DayN1AYieldCalculationOutput output = calculator.calculate(input);
					snapshot.setDerMnyMkt1DayN1AYieldPct(output.getDerMm1DayN1aYieldPct()
							.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
				}
			}
		} catch (Exception e) {
			throw new CalculationException(e.getMessage(), e);
		}
	}
}
