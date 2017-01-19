package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.api.services.impl.utility.UtilityFAYAAPIClient;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Portfolio;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.ShareClass;
import com.csa.apex.fundyield.seccommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level Restated 30 Day Yield calculation engine. Effectively thread safe after configuration.
 */
@Component
public class ClassLevelRestated30DayYieldCalculationEngine implements CalculationEngine {

    /**
     * The utility customer API client. Should be non-null after initialization.
     */
    @Autowired
    private UtilityFAYAAPIClient utilityCustomerAPIClient;

    /**
     * Empty constructor.
     */
    public ClassLevelRestated30DayYieldCalculationEngine() {
    }

    /**
     * Calculates the Class Level Restated 7 Day Yield.
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
            // check the config values and if they are provided use them instead of default ones.
            for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
                Date reportDate = fundAccountingYieldData.getReportDate();
                for (ShareClass shareClass : portfolio.getShareClasses()) {
                    // get share class snapshot for the report date
                    List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
                    Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(reportDate);
                    ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
                    BigDecimal d = snapshot.getDerMnyMktRestate1DayYieldPct();
                    BigDecimal y = (d.add(utilityCustomerAPIClient.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(
                            shareClass.getShareClassSid(), reportDate, 29))).divide(BigDecimal.valueOf(30),
                                    configuration.getOperationScale(), configuration.getRoundingMode());
                    snapshot.setDerMnyMktRst30DayYieldPct(
                            y.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
                }
            }
        } catch (Exception e) {
            throw new CalculationException(e.getMessage(), e);
        }
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(utilityCustomerAPIClient, "utilityCustomerAPIClient");
    }

    /**
     * Set utilityCustomerAPIClient.
     * @return utilityCustomerAPIClient
     */
    public UtilityFAYAAPIClient getUtilityCustomerAPIClient() {
        return utilityCustomerAPIClient;
    }

    /**
     * Get utilityCustomerAPIClient.
     * @param utilityCustomerAPIClient
     */
    public void setUtilityCustomerAPIClient(UtilityFAYAAPIClient utilityCustomerAPIClient) {
        this.utilityCustomerAPIClient = utilityCustomerAPIClient;
    }
}