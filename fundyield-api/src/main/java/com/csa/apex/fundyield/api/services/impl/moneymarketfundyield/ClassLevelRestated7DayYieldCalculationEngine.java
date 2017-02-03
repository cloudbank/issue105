package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import com.csa.apex.fundyield.api.services.impl.BaseCalculationEngineImpl;
import com.csa.apex.fundyield.api.services.impl.utility.UtilityFAYAAPIClient;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
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
 * Class Level Restated 7 Day Yield calculation engine. Effectively thread safe
 * after configuration.
 */
@Component
public class ClassLevelRestated7DayYieldCalculationEngine extends BaseCalculationEngineImpl {

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
     * Calculates the Class Level Restated 7 Day Yield (per portfolio).
     * @param portfolio the portfolio;
     * @param reportDate the report date.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws CalculationException in case any error during calculation.
     */
    @Override
    protected Portfolio doCalculate(Portfolio portfolio, Date reportDate) throws Exception {
		ClassLevelRestated7DayYieldCalculator calculator = new ClassLevelRestated7DayYieldCalculator();
		PortfolioSnapshot portfolioSnapshot = portfolio.getPortfolioSnapshots().get(0);
		BigDecimal fdrPortfolioStateTaxRt = portfolioSnapshot.getFdrPortfolioStateTaxRt();
		BigDecimal fdrN1AOospGrosDistInc = portfolioSnapshot.getFdrN1AOospGrosDistInc();
		if (portfolio.getShareClasses() != null) {
			for (ShareClass shareClass : portfolio.getShareClasses()) {
				// get share class snapshot for the report date
				List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
				if (snapshots == null) {
					continue;
				}
				Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(reportDate);
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
						.getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(shareClass.getShareClassSid(),
								reportDate, 6);

				// set the data to input and calculate:
				ClassLevelRestated7DayYieldCalculationInput input = new ClassLevelRestated7DayYieldCalculationInput(configuration);
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
				snapshot.setDerMnyMktRestatedMilRt(output.getMoneyMarketRestartYield()
						.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
				snapshot.setDerMnyMktRestate1DayYieldPct(output.getMoneyMarketRestart1DayYield()
						.setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
				snapshot.setDerMnyMktRst7DayYieldPct(output.getDerMmRst7DayYieldPct()
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
