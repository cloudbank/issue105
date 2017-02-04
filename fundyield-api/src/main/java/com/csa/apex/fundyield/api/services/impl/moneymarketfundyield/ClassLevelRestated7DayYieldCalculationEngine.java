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
 * Class Level Restated 7 Day Yield calculation engine. Effectively thread safe after configuration.
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
            ClassLevelRestated7DayYieldCalculator calculator = new ClassLevelRestated7DayYieldCalculator();
            // check the config values and if they are provided use them instead of default ones.
            for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
                Date reportDate = fundAccountingYieldData.getReportDate();
                PortfolioSnapshot portfolioSnapshot = portfolio.getPortfolioSnapshots().get(0);
                BigDecimal str = portfolioSnapshot.getFdrPortfolioStateTaxRt();
                BigDecimal opct = portfolioSnapshot.getFdrN1AOospGrosDistInc();
                for (ShareClass shareClass : portfolio.getShareClasses()) {
                    // get share class snapshot for the report date
                    List<ShareClassSnapshot> snapshots = shareClass.getShareClassSnapshots();
                    Predicate<ShareClassSnapshot> predicate = c -> c.getReportDate().equals(reportDate);
                    ShareClassSnapshot snapshot = snapshots.stream().filter(predicate).findFirst().get();
                    BigDecimal tni = snapshot.getN1ADistIncomeUnmodAmt();
                    BigDecimal da = snapshot.getN1ADistIncomeAdjAmt();
                    BigDecimal rda = snapshot.getN1ADistIncomeAdjRevAmt();
                    BigDecimal mda = snapshot.getN1ADistReimbursementAmt();
                    BigDecimal b = snapshot.getN1ADistIncomeBreakageAmt();
                    BigDecimal so = snapshot.getDistributableCapstockQty();
                    BigDecimal nv = snapshot.getNavAmt();
                    BigDecimal reim = snapshot.getN1AReimbursementEarnedAmt();
                    BigDecimal dPrevious6Days = utilityCustomerAPIClient
                            .getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(shareClass.getShareClassSid(), reportDate,
                                    6);

                    // set the data to input and calculate:
                    ClassLevelRestated7DayYieldCalculationInput input = new ClassLevelRestated7DayYieldCalculationInput();
                    input.setTni(tni);
                    input.setDa(da);
                    input.setRda(rda);
                    input.setMda(mda);
                    input.setB(b);
                    input.setSo(so);
                    input.setNv(nv);
                    input.setReim(reim);
                    input.setdPrevious6Days(dPrevious6Days);
                    input.setStr(str);
                    input.setOpct(opct);
                    ClassLevelRestated7DayYieldCalculationOutput output = calculator.calculate(input);
                    snapshot.setDerMnyMktRestatedMilRt(output.getMil().setScale(configuration.getOperationScale(),
                            configuration.getRoundingMode()));
                    snapshot.setDerMnyMktRestate1DayYieldPct(
                            output.getD().setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
                    snapshot.setDerMnyMktRst7DayYieldPct(
                            output.getY().setScale(configuration.getOperationScale(), configuration.getRoundingMode()));
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
