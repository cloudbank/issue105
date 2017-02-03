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
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Class Level Restated 7 Day Yield calculation engine. Effectively thread safe
 * after configuration.
 */
@Component
public class ClassLevelRestated7DayYieldCalculationEngine implements CalculationEngine {

	/**
	 * The utility customer API client. Should be non-null after initialization.
	 */
	@Autowired
	private UtilityFAYAAPIClient utilityCustomerAPIClient;

	/**
	 * Empty constructor.
	 */
	public ClassLevelRestated7DayYieldCalculationEngine() {
	}

	/**
	 * Calculates the Class Level Restated 7 Day Yield.
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
			ClassLevelRestated7DayYieldCalculator calculator = new ClassLevelRestated7DayYieldCalculator();
			// check the config values and if they are provided use them instead
			// of default ones.
			for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
				Date reportDate = fundAccountingYieldData.getReportDate();
				PortfolioSnapshot portfolioSnapshot = portfolio.getPortfolioSnapshots().get(0);
				BigDecimal fdrPortfolioStateTaxRt = portfolioSnapshot.getFdrPortfolioStateTaxRt();
				BigDecimal fdrN1AOospGrosDistInc = portfolioSnapshot.getFdrN1AOospGrosDistInc();
				for (ShareClass shareClass : portfolio.getShareClasses()) {
					// get share class snapshot for the report date
					List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
					Predicate<ShareClassSnapshot> predicate = shareClassSnapshot -> shareClassSnapshot.getReportDate()
							.equals(reportDate);
					ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
					BigDecimal n1ADistIncomeUnmodAmt = snapshot.getN1ADistIncomeUnmodAmt();
					BigDecimal n1ADistIncomeAdjAmt = snapshot.getN1ADistIncomeAdjAmt();
					BigDecimal n1ADistIncomeAdjRevAmt = snapshot.getN1ADistIncomeAdjRevAmt();
					BigDecimal n1ADistReimbursementAmt = snapshot.getN1ADistReimbursementAmt();
					BigDecimal n1ADistIncomeBreakageAmt = snapshot.getN1ADistIncomeBreakageAmt();
					BigDecimal distributableCapstockQty = snapshot.getDistributableCapstockQty();
					BigDecimal navAmt = snapshot.getNavAmt();
					BigDecimal n1AReimbursementEarnedAmt = snapshot.getN1AReimbursementEarnedAmt();
					BigDecimal dPrevious6Days = utilityCustomerAPIClient
							.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(shareClass.getShareClassSid(), reportDate,
									6);

					// set the data to input and calculate:
					ClassLevelRestated7DayYieldCalculationInput input = new ClassLevelRestated7DayYieldCalculationInput();
					input.setN1ADistIncomeUnmodAmt(n1ADistIncomeUnmodAmt);
					input.setN1ADistIncomeAdjAmt(n1ADistIncomeAdjAmt);
					input.setN1ADistIncomeAdjRevAmt(n1ADistIncomeAdjRevAmt);
					input.setN1ADistReimbursementAmt(n1ADistReimbursementAmt);
					input.setN1ADistIncomeBreakageAmt(n1ADistIncomeBreakageAmt);
					input.setDistributableCapstockQty(distributableCapstockQty);
					input.setNavAmt(navAmt);
					input.setN1AReimbursementEarnedAmt(n1AReimbursementEarnedAmt);
					input.setdPrevious6Days(dPrevious6Days);
					input.setN1AReimbursementStr(fdrPortfolioStateTaxRt);
					input.setN1AReimbursementOpct(fdrN1AOospGrosDistInc);
					ClassLevelRestated7DayYieldCalculationOutput output = calculator.calculate(input);
					snapshot.setDerMnyMktRestatedMilRt(output.getMoneyMarketRestartYield().setScale(configuration.getOperationScale(),
							configuration.getRoundingMode()));
					snapshot.setDerMnyMktRestate1DayYieldPct(
							output.getD().setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
					snapshot.setDerMnyMktRst7DayYieldPct(
							output.getDerMmRst7DayYieldPct().setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
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
