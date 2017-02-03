package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationEngineImpl;
import com.csa.apex.fundyield.api.services.impl.utility.UtilityFAYAAPIClient;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class Level 1 Day N1A Yield calculation engine. Effectively thread safe after
 * configuration.
 */
@Component
public class ClassLevel7DayN1AYieldCalculationEngine extends BaseCalculationEngineImpl {

	/**
	 * The utility customer API client. Should be non-null after initialization.
	 */
	@Autowired
	private UtilityFAYAAPIClient utilityCustomerAPIClient;

	/**
	 * Empty constructor.
	 */
	public ClassLevel7DayN1AYieldCalculationEngine() {
	}

    /**
     * Calculates the Class Level 1 Day N1A Yield (per portfolio).
     * @param portfolio the portfolio;
     * @param reportDate the report date.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @Override
    protected Portfolio doCalculate(Portfolio portfolio, Date reportDate) throws Exception {
		if (portfolio.getShareClasses() != null) {
			for (ShareClass shareClass : portfolio.getShareClasses()) {
				// get share class snapshot for the report date
				List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
				if (snapshots == null) {
					continue;
				}
				Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(reportDate);
				ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
				BigDecimal derMnyMkt1DayN1AYieldPct = snapshot.getDerMnyMkt1DayN1AYieldPct();
				// calculate y using formula
				// y=(d+utilityCustomerAPIClient.getSumOfDer1DayYieldN1AMnyMktPct(shareClass.getShareClassSid(),reportDate,30))/7
				// with the precision and round mode specified in
				// configuration
				BigDecimal derMm1NayN1aYieldPct = derMnyMkt1DayN1AYieldPct
						.add(utilityCustomerAPIClient.getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(
								shareClass.getShareClassSid(), reportDate, 30))
						.divide(BigDecimal.valueOf(7), configuration.getOperationScale(),
								configuration.getRoundingMode());
				snapshot.setDerMnyMkt7DayN1AYieldPct(derMm1NayN1aYieldPct
						.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
			}
		}
        return portfolio;
    }

	/**
	 * Checks the configuration.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly. Refer to
	 *             field docs for details.
	 */
	@PostConstruct
	protected void checkConfiguration() {
        CommonUtility.checkNullConfig(utilityCustomerAPIClient, this.getClass().getCanonicalName(), "utilityCustomerAPIClient");
	}

	/**
	 * Set utilityCustomerAPIClient.
	 * 
	 * @return utilityCustomerAPIClient
	 */
	public UtilityFAYAAPIClient getUtilityCustomerAPIClient() {
		return utilityCustomerAPIClient;
	}

	/**
	 * Get utilityCustomerAPIClient.
	 * 
	 * @param utilityCustomerAPIClient
	 */
	public void setUtilityCustomerAPIClient(UtilityFAYAAPIClient utilityCustomerAPIClient) {
		this.utilityCustomerAPIClient = utilityCustomerAPIClient;
	}
}
