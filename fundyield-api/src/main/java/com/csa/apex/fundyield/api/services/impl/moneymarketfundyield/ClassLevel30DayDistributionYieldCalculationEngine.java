package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationEngineImpl;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;


/**
 * Class Level 30 Day Distribution Yield calculation engine. Effectively thread
 * safe after configuration.
 */
public class ClassLevel30DayDistributionYieldCalculationEngine extends BaseCalculationEngineImpl {

	/**
	 * Empty constructor.
	 */
	public ClassLevel30DayDistributionYieldCalculationEngine() {
	}

    /**
     * Calculates the Class Level 30 Day Distribution Yield (per portfolio).
     * @param portfolio the portfolio;
     * @param reportDate the report date.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @Override
    protected Portfolio doCalculate(Portfolio portfolio, Date reportDate) throws Exception {
		ClassLevel30DayDistributionYieldCalculator calculator = new ClassLevel30DayDistributionYieldCalculator();
		Calendar cal = Calendar.getInstance();
		cal.setTime(reportDate);
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int dayOfYear = cal.getActualMaximum(Calendar.DAY_OF_YEAR);

		if (portfolio.getShareClasses() != null) {
			for (ShareClass shareClass : portfolio.getShareClasses()) {
				// get share class snapshot for the report date
				List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
				if (snapshots == null) {
					continue;
				}
				Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(reportDate);
				ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
				BigDecimal distUnmod30DayYieldPct = snapshot.getDistUnmod30DayYieldPct();
				BigDecimal distYieldMilRt = (snapshot.getDistYieldMilRt() == null)
						? snapshot.getDistYieldMilRt() : BigDecimal.ONE;
				BigDecimal navAmt = snapshot.getNavAmt();
				// set the data to input and calculate:
				ClassLevel30DayDistributionYieldCalculationInput input = new ClassLevel30DayDistributionYieldCalculationInput(configuration);
				input.setDistUnmod30DayYieldPct(distUnmod30DayYieldPct);
				input.setDistYieldMilRt(distYieldMilRt);
				input.setReportingDate(dayOfMonth);
				input.setDaysInYear(dayOfYear);
				input.setNavAmt(navAmt);
				ClassLevel30DayDistributionYieldCalculationOutput output = calculator.calculate(input);
				snapshot.setDist30DayYieldPct(output.getDerDist30DayYieldPct()
						.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
			}
		}
        return portfolio;
    }

}
