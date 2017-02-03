package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

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
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level 12 Month Distribution Yield calculation engine. Effectively thread safe after configuration.
 */
public class ClassLevel12MonthDistributionYieldCalculationEngine implements CalculationEngine {

    /**
     * Empty constructor.
     */
    public ClassLevel12MonthDistributionYieldCalculationEngine() {
    }

    /**
     * Calculates the Class Level 12 Month Distribution Yield.
     * @param fundAccountingYieldData the input FundAccountingYieldData;
     * @param configuration the SECConfiguration to be used for config values; if the the config values are provided
     *            they will be used instead of default ones.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @LogMethod
    public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration)
            throws CalculationException {
        CommonUtility.checkNull(fundAccountingYieldData, "Parameter fundAccountingYieldData");
        CommonUtility.checkNull(configuration, "Parameter configuration");
        try {
            for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
                Date reportDate = portfolio.getPortfolioSnapshots().get(0).getReportDate();
                for (ShareClass shareClass : portfolio.getShareClasses()) {
                    // get share class snapshot for the report date
                    List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
                    if (snapshots == null) {
                        continue;
                    }
                    Predicate<ShareClassSnapshot> predicate = shareClassSnapShot -> shareClassSnapShot.getReportDate().equals(reportDate);
                    ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
                    ClassLevel12MonthDistributionYieldCalculationInput input = new ClassLevel12MonthDistributionYieldCalculationInput();
                    input.setDist12MoMilRt(snapshot.getDist12MoMilRt());
                    input.setNavAmt(snapshot.getNavAmt());
                    ClassLevel12MonthDistributionYieldCalculator calculator = new ClassLevel12MonthDistributionYieldCalculator();
                    ClassLevel12MonthDistributionYieldCalculationOutput output = calculator.calculate(input);
                    snapshot.setDerDist12MoYieldPct(output.getDerDist12MoYieldPct());
                }
            }
        } catch (Exception e) {
            throw new CalculationException(e.getMessage(), e);
        }
    }
}
