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
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level 7 Day Distribution Yield calculation engine. Effectively thread
 * safe after configuration.
 */
@Component
public class ClassLevel7DayDistributionYieldCalculationEngine implements CalculationEngine {

	/**
	 * The utility customer API client. Should be non-null after initialization.
	 */
	@Autowired
	private UtilityFAYAAPIClient utilityCustomerAPIClient;

	/**
	 * Empty constructor.
	 */
	public ClassLevel7DayDistributionYieldCalculationEngine() {
	}

	/**
	 * Calculates the Class Level 7 Day Distribution Yield.
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
			// check the config values and if they are provided use them instead
			// of default ones.
			if (fundAccountingYieldData.getPortfolios() != null) {
				Date reportDate = fundAccountingYieldData.getReportDate();
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
							BigDecimal derMm7DayDistYieldPct = utilityCustomerAPIClient
									.getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(shareClass.getShareClassSid(),
											reportDate, 7);
							snapshot.setDerMnyMktRst7DayYieldPct(derMm7DayDistYieldPct
									.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
						}
					}
				}
			}
		} catch (Exception e) {
			throw new CalculationException(e.getMessage(), e);
		}
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
