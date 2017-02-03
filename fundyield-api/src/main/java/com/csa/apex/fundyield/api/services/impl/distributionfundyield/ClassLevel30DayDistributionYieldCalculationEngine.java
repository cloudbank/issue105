package com.csa.apex.fundyield.api.services.impl.distributionfundyield;

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
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level 30 Day Distribution Yield calculation engine. Effectively thread safe after configuration.
 */
public class ClassLevel30DayDistributionYieldCalculationEngine implements CalculationEngine {

    /**
     * Empty constructor.
     */
    public ClassLevel30DayDistributionYieldCalculationEngine() {
    }

    /**
     * Calculates the Class Level 30 Day Distribution Yield.
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
                Date reportDate = fundAccountingYieldData.getReportDate();
                Calendar cal = Calendar.getInstance();
                cal.setTime(reportDate);
                int dayOfReportingDate = cal.get(Calendar.DAY_OF_MONTH);
                int daysInYear = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
                for (ShareClass shareClass : portfolio.getShareClasses()) {
                    // get share class snapshot for the report date
                    List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
                    if (snapshots == null) {
                        continue;
                    }
                    Predicate<ShareClassSnapshot> predicate = shareClassSnapshot -> shareClassSnapshot.getReportDate().equals(reportDate);
                    ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();

                    ClassLevel30DayDistributionYieldCalculationInput input = new ClassLevel30DayDistributionYieldCalculationInput();
                    // set the data to input and calculate
                    input.setDistUnmod30DayYieldPct(snapshot.getDistUnmod30DayYieldPct());
                    input.setDistYieldMilRt(
                            (snapshot.getDistYieldMilRt() != null) ? snapshot.getDistYieldMilRt() : BigDecimal.ONE);
                    input.setNavAmt(snapshot.getNavAmt());
                    input.setDayOfReportingDate(dayOfReportingDate);
                    input.setDaysInYear(daysInYear);
                    ClassLevel30DayDistributionYieldCalculator calculator = new ClassLevel30DayDistributionYieldCalculator();
                    ClassLevel30DayDistributionYieldCalculationOutput output = calculator.calculate(input);
                    snapshot.setDerDist30DayYieldPct(output.getDerDist30DayYieldPct());
                }
            }
        } catch (Exception e) {
            throw new CalculationException(e.getMessage(), e);
        }
    }
}
