package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationEngineImpl;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class Level 30 Day Distribution Yield calculation engine. Effectively thread safe after configuration.
 */
@Service
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
    protected Portfolio doCalculate(Portfolio portfolio, Date reportDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(reportDate);
		int dayOfReportingDate = cal.get(Calendar.DAY_OF_MONTH);
		int daysInYear = cal.getActualMaximum(Calendar.DAY_OF_YEAR);

		if (portfolio.getShareClasses() != null) {
			for (ShareClass shareClass : portfolio.getShareClasses()) {
				// get share class snapshot for the report date
				List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
				if (snapshots == null) {
					continue;
				}
				Predicate<ShareClassSnapshot> predicate = shareClassSnapshot -> shareClassSnapshot
						.getReportDate().equals(reportDate);
				ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();

				ClassLevel30DayDistributionYieldCalculationInput input = new ClassLevel30DayDistributionYieldCalculationInput(configuration);
				// set the data to input and calculate
				input.setDistUnmod30DayYieldPct(snapshot.getDistUnmod30DayYieldPct());
				input.setDistYieldMilRt((snapshot.getDistYieldMilRt() != null)
						? snapshot.getDistYieldMilRt() : BigDecimal.ONE);
				input.setNavAmt(snapshot.getNavAmt());
				input.setDayOfReportingDate(dayOfReportingDate);
				input.setDaysInYear(daysInYear);
				ClassLevel30DayDistributionYieldCalculator calculator = new ClassLevel30DayDistributionYieldCalculator();
				ClassLevel30DayDistributionYieldCalculationOutput output = calculator.calculate(input);
				snapshot.setDerDist30DayYieldPct(output.getDerDist30DayYieldPct());
			}
		}
        return portfolio;
    }

}
