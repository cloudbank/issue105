package com.csa.apex.fundyield.api.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.csa.apex.fundyield.utility.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.api.services.DistributionFundYieldService;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Default implementation of the DistributionFundYieldService. Uses pluggable calculation engines to calculate the data.
 * This class is effectively thread safe.
 */
@Service
public class DistributionFundYieldServiceImpl extends BaseServiceImpl implements DistributionFundYieldService {

    /**
     * The sequensive calculators. Should be non-null after initialization, shouldn't contain nulls. For phase 2 it's
     * supposed following engines will be configured in order: 1) Class Level 1 Day Distribution Yield Calculator 2)
     * Class Level 30 Day Distribution Yield Calculator 3) Class Level 12 Month Distribution Yield Calculator
     */
    @Resource(name="distributionCalculationEngines")
    private List<CalculationEngine> calculatorEngines;

    /**
     * The REST API path to the endpoint used to get customer distribution fund data. After injection should be non-null
     * and non-empty.
     */
    @Value("${getFAYADistributionFundDataApiPath}")
    private String getFAYADistributionFundDataApiPath;

    /**
     * The REST API path to the endpoint used to save calculated distribution fund data. After injection should be
     * non-null and non-empty.
     */
    @Value("${saveCalculatedDistributionFundDataApiPath}")
    private String saveCalculatedDistributionFundDataApiPath;

    /**
     * The REST API path to the endpoint used to get calculated distribution fund data. After injection should be
     * non-null and non-empty.
     */
    @Value("${getCalculatedDistributionFundDataApiPath}")
    private String getCalculatedDistributionFundDataApiPath;

    /**
     * Empty constructor.
     */
    public DistributionFundYieldServiceImpl() {
    }

    /**
     * Checks the configuration and configures the calc engines with the config values obtained from REST API.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        super.checkConfiguration();
        CommonUtility.checkStringConfig(getFAYADistributionFundDataApiPath, "getFAYADistributionFundDataApiPath");
        CommonUtility.checkStringConfig(saveCalculatedDistributionFundDataApiPath, "saveCalculatedDistributionFundDataApiPath");
        CommonUtility.checkStringConfig(getCalculatedDistributionFundDataApiPath, "getCalculatedDistributionFundDataApiPath");
        CommonUtility.checkListConfig(calculatorEngines, "calculatorEngines");
    }

    /**
     * Process Distribution Fund data for the business date. This method gets the fund yield data and then process it by
     * each engine and finally persists data using API.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData processDistributionFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, Constants.BUSINESS_DATE);
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getFAYADistributionFundDataApiPath)
                    .queryParam(Constants.BUSINESS_DATE, getFormattedDate(businessDate));
            FundAccountingYieldData fundAccountingYieldData = getRestTemplate()
                    .getForObject(builder.build().encode().toUri(), FundAccountingYieldData.class);
            for (CalculationEngine calcEngine : calculatorEngines) {
                calcEngine.calculate(fundAccountingYieldData, getSECConfiguration());
            }
            Boolean saveResponse = getRestTemplate().exchange(saveCalculatedDistributionFundDataApiPath, HttpMethod.PUT,
                    new HttpEntity<FundAccountingYieldData>(fundAccountingYieldData), Boolean.class).getBody();
            if (saveResponse == null || !saveResponse) {
                throw new FundAccountingYieldException("Fail to save fundAccountingYieldData");
            }
            return fundAccountingYieldData;
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Gets already calculated Distribution Fund Yield data for the given date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedDistributionFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, Constants.BUSINESS_DATE);
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getCalculatedDistributionFundDataApiPath)
                    .queryParam(Constants.BUSINESS_DATE, getFormattedDate(businessDate));
            return getRestTemplate().getForObject(builder.build().encode().toUri(), FundAccountingYieldData.class);
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    public List<CalculationEngine> getCalculatorEngines() {
        return calculatorEngines;
    }

    public void setCalculatorEngines(List<CalculationEngine> calculatorEngines) {
        this.calculatorEngines = calculatorEngines;
    }

    public String getGetFAYADistributionFundDataApiPath() {
        return getFAYADistributionFundDataApiPath;
    }

    public void setGetFAYADistributionFundDataApiPath(String getFAYADistributionFundDataApiPath) {
        this.getFAYADistributionFundDataApiPath = getFAYADistributionFundDataApiPath;
    }

    public String getSaveCalculatedDistributionFundDataApiPath() {
        return saveCalculatedDistributionFundDataApiPath;
    }

    public void setSaveCalculatedDistributionFundDataApiPath(String saveCalculatedDistributionFundDataApiPath) {
        this.saveCalculatedDistributionFundDataApiPath = saveCalculatedDistributionFundDataApiPath;
    }

    public String getGetCalculatedDistributionFundDataApiPath() {
        return getCalculatedDistributionFundDataApiPath;
    }

    public void setGetCalculatedDistributionFundDataApiPath(String getCalculatedDistributionFundDataApiPath) {
        this.getCalculatedDistributionFundDataApiPath = getCalculatedDistributionFundDataApiPath;
    }
}
