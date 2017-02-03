package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationEngineImpl;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class Level 1 Day N1A Yield calculation engine. Effectively thread safe after
 * configuration.
 */
@Service
public class ClassLevel1DayN1AYieldCalculationEngine extends BaseCalculationEngineImpl {

	/**
	 * Empty constructor.
	 */
	public ClassLevel1DayN1AYieldCalculationEngine() {
	}

    /**
     * Calculates the Class Level 1 Day N1A Yield (per portfolio).
     * @param portfolio the portfolio;
     * @param reportDate the report date.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @Override
    protected Portfolio doCalculate(Portfolio portfolio, Date reportDate) {
		ClassLevel1DayN1AYieldCalculator calculator = new ClassLevel1DayN1AYieldCalculator();
		PortfolioSnapshot portfolioSnapshot = portfolio.getPortfolioSnapshots().get(0);
		BigDecimal fdrPortfolioStateTaxRt = portfolioSnapshot.getFdrPortfolioStateTaxRt();
		BigDecimal fdrN1AOospGrosDistInc = portfolioSnapshot.getFdrN1AOospGrosDistInc();
		if (portfolio.getShareClasses() != null) {
			for (ShareClass shareClass : portfolio.getShareClasses()) {
				// get share class snapshot for the report date
				List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
				if (snapshots == null) {
					continue;
				}
				Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(reportDate);
				ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
				ClassLevel1DayN1AYieldCalculationInput input = new ClassLevel1DayN1AYieldCalculationInput(configuration);
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
        return portfolio;
    }

}
