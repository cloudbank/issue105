package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationEngineImpl;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class Level 12 Month Distribution Yield calculation engine. Effectively thread safe after configuration.
 */
@Service
public class ClassLevel12MonthDistributionYieldCalculationEngine extends BaseCalculationEngineImpl {

    /**
     * Empty constructor.
     */
    public ClassLevel12MonthDistributionYieldCalculationEngine() {
    }

    /**
     * Calculates the Class Level 12 Month Distribution Yield (per portfolio).
     * @param portfolio the portfolio;
     * @param reportDate the report date.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @Override
    protected Portfolio doCalculate(Portfolio portfolio, Date reportDate) {
		Date pssReportDate = portfolio.getPortfolioSnapshots().get(0).getReportDate();
		if (portfolio.getShareClasses() != null) {
			for (ShareClass shareClass : portfolio.getShareClasses()) {
				// get share class snapshot for the report date
				List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
				if (snapshots == null) {
					continue;
				}
				Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(pssReportDate);
				ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
				ClassLevel12MonthDistributionYieldCalculationInput input = new ClassLevel12MonthDistributionYieldCalculationInput(configuration);
				input.setDist12MoMilRt(snapshot.getDist12MoMilRt());
				input.setNavAmt(snapshot.getNavAmt());
				ClassLevel12MonthDistributionYieldCalculator calculator = new ClassLevel12MonthDistributionYieldCalculator();
				ClassLevel12MonthDistributionYieldCalculationOutput output = calculator.calculate(input);
				snapshot.setDerDist12MoYieldPct(output.getDerDist12MoYieldPct());
			}
		}
        return portfolio;
    }
}
