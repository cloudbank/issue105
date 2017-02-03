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
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class Level 1 Day N1A Yield calculation engine. Effectively thread safe after
 * configuration.
 */
@Component
public class ClassLevel30DayN1AYieldCalculationEngine extends BaseCalculationEngineImpl {

	/**
	 * The utility customer API client. Should be non-null after initialization.
	 */
	@Autowired
	private UtilityFAYAAPIClient utilityCustomerAPIClient;

	/**
	 * Empty constructor.
	 */
	public ClassLevel30DayN1AYieldCalculationEngine() {
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
				ClassLevel30DayN1AYieldCalculator calculator = new ClassLevel30DayN1AYieldCalculator();
				ClassLevel30DayN1AYieldCalculationInput input = new ClassLevel30DayN1AYieldCalculationInput(configuration);
				input.setDerMnyMkt1DayN1AYieldPct(snapshot.getDerMnyMkt1DayN1AYieldPct());
				input.setSumOfDer1DayYieldN1AMnyMktPctPrevious29Days(
						utilityCustomerAPIClient.getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(
								shareClass.getShareClassSid(), reportDate, 29));
				ClassLevel30DayN1AYieldCalculationOutput output = calculator.calculate(input);
				snapshot.setDerMnyMkt30DayN1AYieldPct(output.getDerMnyMkt1DayN1AYieldPct());
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
