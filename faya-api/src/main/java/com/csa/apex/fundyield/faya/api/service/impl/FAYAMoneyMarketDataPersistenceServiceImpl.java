package com.csa.apex.fundyield.faya.api.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.csa.apex.fundyield.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.faya.api.service.FAYAMoneyMarketDataPersistenceService;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Persistence service for customer data operations implementing the persistence interface. This class is effectively
 * thread safe. Update: New methods for MM and Distribution Fund data were added. Also name was changed to "FAYA" from
 * "Customer".
 */
@Service
public class FAYAMoneyMarketDataPersistenceServiceImpl implements FAYAMoneyMarketDataPersistenceService {

    /**
     * The autowired jdbcTemplate. Should be non-null after injection.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * The auto wired StoredProcedures.
     */
    @Autowired
    private StoredProcedures storedProcedures;

    /**
     * Empty constructor.
     */
    public FAYAMoneyMarketDataPersistenceServiceImpl() {
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly. Refer to field docs for
     *             details.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(jdbcTemplate, Constants.JDBC_TEMPLATE);
    }

    /**
     * Gets Money Market data for the business date.
     * @param businessDate the business date;
     * @return FundAccountingYieldData;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYAMoneyMarketFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        return retrieveFundAccountingYieldData(businessDate);
    }

    /**
     * Persists the calculated Money Market Fund Yield data.
     * @param fundAccountingYieldData FundAccountingYieldData
     * @return the result of the execution.
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistMoneyMarketFundYieldData(FundAccountingYieldData fundAccountingYieldData, String userId)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(fundAccountingYieldData, Constants.FUND_ACCOUNTING_YIELD_DATA);
//        CommonUtility.checkString(userId, "userId");
        storedProcedures.saveFAYAPortfolioData(fundAccountingYieldData);
        return true;
    }

    /**
     * Persists the calculated Distribution Fund Yield data.
     * @param securitySECData - the SecuritySECDatae @RequestParam;
     * @return the result of the execution.
     * @throws FundAccountingYieldException
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedMoneyMarketFundYieldData(Date businessDate)
            throws FundAccountingYieldException {
        return retrieveFundAccountingYieldData(businessDate);
    }

    /**
     * Gets FundAccountingYieldData from persistence.
     * @param businessDate the business date;
     * @return FundAccountingYieldData with calculated result;
     * @throws IllegalArgumentException in case the input is invalid (null).
     * @throws FundAccountingYieldException in case any error during processing.
     */
    private FundAccountingYieldData retrieveFundAccountingYieldData(Date businessDate)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, Constants.BUSINESS_DATE);
        try {
            List<Portfolio> portfolios = FAYAPersistenceHelper.getPortfolios(jdbcTemplate, "QUERY_MM_PORTFOLIO",
                    businessDate);
            List<Instrument> instruments = FAYAPersistenceHelper.getInstruments(jdbcTemplate, "QUERY_MM_INSTRUMENT",
                    businessDate);
            FundAccountingYieldData data = new FundAccountingYieldData();
            data.setBusinessDate(businessDate);
            data.setReportDate(businessDate);
            data.setPortfolios(portfolios);
            data.setInstruments(instruments);
            return data;
        } catch (Exception e) {
            throw new FundAccountingYieldException(e.getMessage());
        }
    }

    /**
     * Getter jdbcTemplate.
     * @return the jdbc template
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Setter jdbcTemplate.
     * @param jdbcTemplate the jdbc template to be set
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
