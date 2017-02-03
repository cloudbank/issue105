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
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level Effective Compound 7 Day N1A Yield calculation engine.
 * Effectively thread safe after configuration.
 */
@Component
public class ClassLevelEffectiveCompound7DayN1AYieldCalculationEngine implements CalculationEngine {

	/**
	 * The utility customer API client. Should be non-null after initialization.
	 */
	@Autowired
	private UtilityFAYAAPIClient utilityCustomerAPIClient;

	/**
	 * Empty constructor.
	 */
	public ClassLevelEffectiveCompound7DayN1AYieldCalculationEngine() {
	}

	/**
	 * Calculates the Class Level Effective Compound 7 Day N1A Yield.
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
		CommonUtility.checkNull(fundAccountingYieldData, "Parameter fundAccountingYieldData");
		CommonUtility.checkNull(configuration, "Parameter configuration");
		try {
			for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
				Date reportDate = portfolio.getPortfolioSnapshots().get(0).getReportDate();
				for (ShareClass shareClass : portfolio.getShareClasses()) {
					// get share class snapshot for the report date
					List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
					Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(reportDate);
					ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
					BigDecimal derMnyMkt7DayN1AYieldPct = snapshot.getDerMnyMkt7DayN1AYieldPct();
					BigDecimal derMmN1aCompound7DayYieldPct = derMnyMkt7DayN1AYieldPct.add(utilityCustomerAPIClient
							.getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(shareClass.getShareClassSid(), reportDate, 6))
							.divide(BigDecimal.valueOf(7), configuration.getOperationScale(),
									configuration.getRoundingMode());
					snapshot.setDerMnyMktN1ACompound7dayYield(derMmN1aCompound7DayYieldPct
							.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
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
		CommonUtility.checkNullConfig(utilityCustomerAPIClient, "utilityCustomerAPIClient");
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
