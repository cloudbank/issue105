package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level 30 Day Distribution Yield calculation engine. Effectively thread
 * safe after configuration.
 */
public class ClassLevel30DayDistributionYieldCalculationEngine implements CalculationEngine {

	/**
	 * Empty constructor.
	 */
	public ClassLevel30DayDistributionYieldCalculationEngine() {
	}

	/**
	 * Calculates the Class Level 30 Day Distribution Yield.
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
		CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				"Parameter fundAccountingYieldData");
		CommonUtility.checkNull(configuration, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				"Parameter configuration");

		try {
			ClassLevel30DayDistributionYieldCalculator calculator = new ClassLevel30DayDistributionYieldCalculator();
			// check the config values and if they are provided use them instead
			// of default ones.
			if (fundAccountingYieldData.getPortfolios() != null) {
				Date reportDate = fundAccountingYieldData.getReportDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(reportDate);
				int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
				int dayOfYear = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
				for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
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
				}
			}
		} catch (Exception e) {
			throw new CalculationException(e.getMessage(), e);
		}
	}

}
