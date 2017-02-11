package com.csa.apex.fundyield.api.services.impl;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.csa.apex.fundyield.utility.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.api.services.MoneyMarketFundYieldService;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Default implementation of the MoneyMarketFundYieldService. Uses pluggable calculation engines to calculate the data.
 * This class is effectively thread safe.
 */
@Service
public class MoneyMarketFundYieldServiceImpl extends BaseServiceImpl implements MoneyMarketFundYieldService {

    /**
     * The sequensive calculators. Should be non-null after initialization, shouldn't contain nulls. For phase 2 it's
     * supposed following engines will be configured in order: 1) Class Level 1 Day Distribution Yield Calculator 2)
     * Class Level 30 Day Distribution Yield Calculator 3) Class Level 12 Month Distribution Yield Calculator
     */
    @Resource(name="moneyMarketCalculationEngines")
    private List<CalculationEngine> calculatorEngines;

    /**
     * The REST API path to the endpoint used to get customer Money Market fund data. After injection should be non-null
     * and non-empty.
     */
    @Value("${getFAYAMoneyMarketFundDataApiPath}")
    private String getFAYAMoneyMarketFundDataApiPath;

    /**
     * The REST API path to the endpoint used to save calculated Money Market fund data. After injection should be
     * non-null and non-empty.
     */
    @Value("${saveCalculatedMoneyMarketFundDataApiPath}")
    private String saveCalculatedMoneyMarketFundDataApiPath;

    /**
     * The REST API path to the endpoint used to get calculated Money Market fund data. After injection should be
     * non-null and non-empty.
     */
    @Value("${getCalculatedMoneyMarketFundDataApiPath}")
    private String getCalculatedMoneyMarketFundDataApiPath;

    /**
     * Empty constructor.
     */
    public MoneyMarketFundYieldServiceImpl() {
    }

    /**
     * Checks the configuration and configures the calc engines with the config values obtained from REST API.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        super.checkConfiguration();
        CommonUtility.checkStringConfig(getFAYAMoneyMarketFundDataApiPath, this.getClass().getCanonicalName(), "getFAYAMoneyMarketFundDataApiPath");
        CommonUtility.checkStringConfig(saveCalculatedMoneyMarketFundDataApiPath, this.getClass().getCanonicalName(),
                "saveCalculatedMoneyMarketFundDataApiPath");
        CommonUtility.checkStringConfig(getCalculatedMoneyMarketFundDataApiPath, this.getClass().getCanonicalName(),
                "getCalculatedMoneyMarketFundDataApiPath");
        CommonUtility.checkListConfig(calculatorEngines, this.getClass().getCanonicalName(), "calculatorEngines");
    }

    /**
     * Process Money Market Fund data for the business date. This method gets the fund yield data and then process it by
     * each engine and finally persists data using API.
     * @param userId The user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException - in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData processMoneyMarketFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "processMoneyMarketFundYieldData", Constants.USER_ID);
    	CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(),  "processMoneyMarketFundYieldData", Constants.BUSINESS_DATE);
        try {
        	HttpHeaders headers = new HttpHeaders();
			headers.set(Constants.USER_ID, userId);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getFAYAMoneyMarketFundDataApiPath)
					.queryParam(Constants.BUSINESS_DATE, getFormattedDate(businessDate));
			URI uri = builder.build().encode().toUri();
			ResponseEntity<FundAccountingYieldData> responseEntity = getRestTemplate().exchange(uri, HttpMethod.GET,
					new HttpEntity<>(headers), FundAccountingYieldData.class);
			FundAccountingYieldData fundAccountingYieldData = responseEntity.getBody();
        	
            for (CalculationEngine calcEngine : calculatorEngines) {
                calcEngine.calculate(fundAccountingYieldData, getSECConfiguration());
            }
            Boolean saveResponse = getRestTemplate().exchange(saveCalculatedMoneyMarketFundDataApiPath, HttpMethod.PUT,
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
     * @param userId The user id;
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException - in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(String userId, Date businessDate)
            throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getCalculatedMoneyMarketFundYieldDatas", Constants.USER_ID);
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getCalculatedMoneyMarketFundYieldData", Constants.BUSINESS_DATE);
        try {
        	HttpHeaders headers = new HttpHeaders();
			headers.set(Constants.USER_ID, userId);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getCalculatedMoneyMarketFundDataApiPath)
					.queryParam(Constants.BUSINESS_DATE, getFormattedDate(businessDate));
			URI uri = builder.build().encode().toUri();
			ResponseEntity<FundAccountingYieldData> responseEntity = getRestTemplate().exchange(uri, HttpMethod.GET,
					new HttpEntity<>(headers), FundAccountingYieldData.class);
			return responseEntity.getBody();
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

    public String getGetFAYAMoneyMarketFundDataApiPath() {
        return getFAYAMoneyMarketFundDataApiPath;
    }

    public void setGetFAYAMoneyMarketFundDataApiPath(String getFAYAMoneyMarketFundDataApiPath) {
        this.getFAYAMoneyMarketFundDataApiPath = getFAYAMoneyMarketFundDataApiPath;
    }

    public String getSaveCalculatedMoneyMarketFundDataApiPath() {
        return saveCalculatedMoneyMarketFundDataApiPath;
    }

    public void setSaveCalculatedMoneyMarketFundDataApiPath(String saveCalculatedMoneyMarketFundDataApiPath) {
        this.saveCalculatedMoneyMarketFundDataApiPath = saveCalculatedMoneyMarketFundDataApiPath;
    }

    public String getGetCalculatedMoneyMarketFundDataApiPath() {
        return getCalculatedMoneyMarketFundDataApiPath;
    }

    public void setGetCalculatedMoneyMarketFundDataApiPath(String getCalculatedMoneyMarketFundDataApiPath) {
        this.getCalculatedMoneyMarketFundDataApiPath = getCalculatedMoneyMarketFundDataApiPath;
    }
}
