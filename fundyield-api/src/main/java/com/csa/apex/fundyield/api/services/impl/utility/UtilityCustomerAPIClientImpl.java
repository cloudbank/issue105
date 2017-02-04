package com.csa.apex.fundyield.api.services.impl.utility;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.csa.apex.fundyield.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Utility FAYA API client implementation. Effectively thread safe after configuration.
 */
@Service
public class UtilityCustomerAPIClientImpl implements UtilityFAYAAPIClient {

    /**
     * The REST API path to the endpoint used to get data. After injection should be non-null and non-empty.
     */
    @Value("${getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath}")
    private String getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath;

    /**
     * The REST API path to the endpoint used to get data. After injection should be non-null and non-empty.
     */
    @Value("${getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath}")
    private String getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath;

    /**
     * The REST API path to the endpoint used to get data. After injection should be non-null and non-empty.
     */
    @Value("${getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath}")
    private String getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath;

    /**
     * The REST API path to the endpoint used to get data. After injection should be non-null and non-empty.
     */
    @Value("${getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath}")
    private String getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath;

    /**
     * The REST API path to the endpoint used to get data. After injection should be non-null and non-empty.
     */
    @Value("${getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath}")
    private String getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Empty constructor.
     */
    public UtilityCustomerAPIClientImpl() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkStringConfig(getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath,
                "getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath");
        CommonUtility.checkStringConfig(getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath,
                "getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath");
        CommonUtility.checkStringConfig(getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath,
                "getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath");
        CommonUtility.checkStringConfig(getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath,
                "getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath");
        CommonUtility.checkStringConfig(getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath,
                "getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath");
    }

    /**
     * Gets the average of MM 1 Day Dist Yield Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @LogMethod
    public BigDecimal getAvgOfMnyMkt1DayDistYieldPctForPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        try {
            return getResultFromApiPath(getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath, shareClassSid, reportDate,
                    numOfDays);
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Gets the sum of Der 1 Day Yield N1A MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @LogMethod
    public BigDecimal getSumOfDer1DayYieldN1AMnyMktPctPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        try {
            return getResultFromApiPath(getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath, shareClassSid, reportDate,
                    numOfDays);
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Gets the sum of Der Restate 1 Day Yield MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @LogMethod
    public BigDecimal getSumOfDerRestate1DayYieldMnyMktPctPreviousDays(long shareClassSid, Date reportDate,
            int numOfDays) throws FundAccountingYieldException {
        try {
            return getResultFromApiPath(getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath, shareClassSid,
                    reportDate, numOfDays);
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Gets the avg of MM 7 DayYield Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return avg value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @LogMethod
    public BigDecimal getAvgOfMnyMkt7DayYieldPctForPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        try {
            return getResultFromApiPath(getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath, shareClassSid, reportDate,
                    numOfDays);
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Gets sum of Der 7 Day Yield N1A MM Pct for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return sum value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @LogMethod
    public BigDecimal getSumOfDer7DayYieldN1AMnyMktPctPreviousDays(long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        try {
            return getResultFromApiPath(getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath, shareClassSid, reportDate,
                    numOfDays);
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Gets the result for previous days.
     * @param shareClassSid the share class id;
     * @param reportDate the report date;
     * @param numOfDays the number of days;
     * @return result value;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    private BigDecimal getResultFromApiPath(String path, long shareClassSid, Date reportDate, int numOfDays)
            throws FundAccountingYieldException {
        // RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path).queryParam(Constants.REPORT_DATE, reportDate)
                .queryParam(Constants.SHARE_CLASS_SID, shareClassSid).queryParam(Constants.NUM_OF_DAYS, numOfDays);
        BigDecimal result = restTemplate.getForObject(builder.build().encode().toUri(), BigDecimal.class);
        return result;
    }

    public String getGetAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath() {
        return getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath;
    }

    public void setGetAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath(
            String getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath) {
        this.getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath = getAvgOfMnyMkt1DayDistYieldPctForPreviousDaysApiPath;
    }

    public String getGetSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath() {
        return getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath;
    }

    public void setGetSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath(
            String getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath) {
        this.getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath = getSumOfDer1DayYieldN1AMnyMktPctPreviousDaysApiPath;
    }

    public String getGetSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath() {
        return getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath;
    }

    public void setGetSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath(
            String getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath) {
        this.getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath = getSumOfDerRestate1DayYieldMnyMktPctPreviousDaysApiPath;
    }

    public String getGetAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath() {
        return getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath;
    }

    public void setGetAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath(
            String getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath) {
        this.getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath = getAvgOfMnyMkt7DayYieldPctForPreviousDaysApiPath;
    }

    public String getGetSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath() {
        return getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath;
    }

    public void setGetSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath(
            String getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath) {
        this.getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath = getSumOfDer7DayYieldN1AMnyMktPctPreviousDaysApiPath;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
