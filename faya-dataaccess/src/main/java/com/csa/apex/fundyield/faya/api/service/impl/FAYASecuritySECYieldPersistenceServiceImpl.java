/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.exceptions.PersistenceException;
import com.csa.apex.fundyield.faya.api.service.FAYASecuritySECYieldPersistenceService;
import com.csa.apex.fundyield.fayacommons.entities.CallSchedule;
import com.csa.apex.fundyield.fayacommons.entities.CashDividendSchedule;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.InterestRateSchedule;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.PutSchedule;
import com.csa.apex.fundyield.fayacommons.entities.ShareClass;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.TaxExclusion;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.fayacommons.entities.UnderlyingInstrumentLink;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Persistence service for FAYA data operations implementing the persistence interface. This class is effectively
 * thread safe.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class FAYASecuritySECYieldPersistenceServiceImpl implements FAYASecuritySECYieldPersistenceService {

    /**
     * The auto wired StoredProcedureHelper.
     */
    @Autowired
    private StoredProcedureHelper storedProcedureHelper;

    /**
     * The auto wired StoredProcedureHelper.
     */
    @Autowired
    private StoredProcedure storedProcedure;

    /**
     * Constructor
     */
    public FAYASecuritySECYieldPersistenceServiceImpl() {
        // default constructor
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(storedProcedureHelper, this.getClass().getCanonicalName(), "storedProcedureHelper");
    }

    /**
     * Persists the calculated SEC security data.
     * @param userId The user id.
     * @param fundAccountingYieldData The FundAccountingYieldData to be persisted
     * @return flag indicating whether The data was persisted or not
     * @throws IllegalArgumentException in case the input is invalid (null)
     * @throws FundAccountingYieldException in case any error occurred during processing
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistSecuritySECData(String userId, FundAccountingYieldData fundAccountingYieldData)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), "persistSecuritySECData", "Parameter fundAccountingYieldData");
        CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "persistSecuritySECData", Constants.USER_ID);

        storedProcedureHelper.saveFAYAInstrumentData(userId, fundAccountingYieldData);
        storedProcedureHelper.saveFAYAPortfolioData(userId, fundAccountingYieldData);

        return true;
    }

    /**
     * Gets the SEC Security.
     * @param userId The user id
     * @param businessDate the business date
     * @return the list of security SEC data
     * @throws FundAccountingYieldException in case any error occurred during processing
     * @throws IllegalArgumentException in case the input is invalid (null)
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYASECData(String userId, Date businessDate) throws FundAccountingYieldException {
    	CommonUtility.checkString(userId, this.getClass().getCanonicalName(), "getFAYASECData", Constants.USER_ID);
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getFAYASECData", "Parameter businessDate");

        // The business date input parameters
        DateTime businessDateTime = new DateTime(businessDate).withTimeAtStartOfDay();

        Map<String, Object> storedProc1 = new HashMap<>();
        storedProc1.put("businessDateStart", businessDateTime.toDate());
        storedProc1.put("businessDateEnd", businessDateTime.plusDays(1).withTimeAtStartOfDay().toDate());

        Map<String, Object> storedProc2 = new HashMap<>();
        storedProc2.put("businessDateStart", businessDateTime.toDate());
        storedProc2.put("businessDateEnd", businessDateTime.plusDays(1).withTimeAtStartOfDay().toDate());

        FundAccountingYieldData data = new FundAccountingYieldData();
        data.setBusinessDate(businessDateTime.toDate());
        data.setReportDate(data.getBusinessDate());

        try {
            // Query instrument data
            this.storedProcedure.getInstruments(storedProc1);
            data.setInstruments(populateInstrumentData(storedProc1));

            // Query portfolio data
            this.storedProcedure.getPortfolio(storedProc2);
            data.setPortfolios(populatePortfolioData(storedProc2));
        } catch (DataAccessException dae) {
            throw new PersistenceException("Failed to get SEC yield data", dae);
        }

        return data;
    }

    /**
     * Populate portfolios data.
     * @param results The results map from stored procedure call
     * @return list of portfolios
     */
    @SuppressWarnings("unchecked")
    private static List<Portfolio> populatePortfolioData(Map<String, Object> results) {

        // Map from TradableEntity sid to TradableEntitySnapshots
        Map<Long, List<TradableEntitySnapshot>> tradableEntitySnapshotsMap = ((List<TradableEntitySnapshot>) results
                .get("tradableEntitySnapshots")).stream()
                        .collect(Collectors.groupingBy(TradableEntitySnapshot::getTradableEntitySid));

        // Map from TradableEntity sid to TradableEntity
        Map<Long, TradableEntity> tradableEntitiesMap = ((List<TradableEntity>) results.get("tradableEntities"))
                .stream().collect(Collectors.toMap(TradableEntity::getTradableEntitySid, e -> {
                    e.setTradableEntitySnapshots(tradableEntitySnapshotsMap.get(e.getTradableEntitySid()));
                    return e;
                }));

        // Map from Portfolio sid to PortfolioHoldingSnapshots
        Map<Long, List<PortfolioHoldingSnapshot>> portfolioHoldingSnapshotsMap = ((List<PortfolioHoldingSnapshot>) results
                .get("portfolioHoldingSnapshots")).stream().collect(Collectors.groupingBy(e -> {
                    e.setTradableEntity(tradableEntitiesMap.get(e.getTradableEntitySid()));
                    return e.getPortfolioSid();
                }));

        // Map from ShareClass sid to ShareClassSnapshots
        Map<Long, List<ShareClassSnapshot>> shareClassSnapshotsMap = ((List<ShareClassSnapshot>) results
                .get("shareClassSnapshots")).stream()
                        .collect(Collectors.groupingBy(ShareClassSnapshot::getShareClassSid));

        // Map from Portfolio sid to ShareClasses
        Map<Long, List<ShareClass>> shareClassesMap = ((List<ShareClass>) results.get("shareClasses")).stream()
                .collect(Collectors.groupingBy(e -> {
                    e.setShareClassSnapshots(shareClassSnapshotsMap.get(e.getShareClassSid()));
                    return e.getPortfolioSid();
                }));

        // Map from Portfolio sid to PortfolioSnapshots
        Map<Long, List<PortfolioSnapshot>> portfolioSnapshotsMap = ((List<PortfolioSnapshot>) results
                .get("portfolioSnapshots")).stream()
                        .collect(Collectors.groupingBy(PortfolioSnapshot::getPortfolioSid));

        // Map from Portfolio sid to TaxExclusions
        Map<Long, List<TaxExclusion>> taxExclusionsMap = ((List<TaxExclusion>) results.get("taxExclusions"))
                .stream().collect(Collectors.groupingBy(TaxExclusion::getPortfolioSid));

        List<Portfolio> portfolios = (List<Portfolio>) results.get("portfolio");

        for (Portfolio portfolio : portfolios) {
            long portfolioSid = portfolio.getPortfolioSid();
            portfolio.setShareClasses(shareClassesMap.get(portfolioSid));
            portfolio.setPortfolioTaxExclusions(taxExclusionsMap.get(portfolioSid));
            portfolio.setPortfolioSnapshots(portfolioSnapshotsMap.get(portfolioSid));
            portfolio.setPortfolioHoldings(portfolioHoldingSnapshotsMap.get(portfolioSid));
        }

        return portfolios;
    }

    /**
     * Populate instruments data.
     * @param results The results map from stored procedure call
     * @return list of instruments
     */
    private static List<Instrument> populateInstrumentData(Map<String, Object> results) {
        // Map from Instrument sid to InterestRateSchedules
        Map<Long, List<InterestRateSchedule>> interestRateSchedulesMap = ((List<InterestRateSchedule>) results
                .get("interestRateSchedules")).stream()
                        .collect(Collectors.groupingBy(InterestRateSchedule::getInstrumentSid));

        // Map from Instrument sid to CashDividendSchedules
        Map<Long, List<CashDividendSchedule>> cashDividendSchedulesMap = ((List<CashDividendSchedule>) results
                .get("cashDividendSchedules")).stream()
                        .collect(Collectors.groupingBy(CashDividendSchedule::getInstrumentSid));

        // Map from Instrument sid to CallSchedules
        Map<Long, List<CallSchedule>> callSchedulesMap = ((List<CallSchedule>) results.get("callSchedules"))
                .stream().collect(Collectors.groupingBy(CallSchedule::getInstrumentSid));

        // Map from Instrument sid to PutSchedules
        Map<Long, List<PutSchedule>> putSchedulesMap = ((List<PutSchedule>) results.get("putSchedules")).stream()
                .collect(Collectors.groupingBy(PutSchedule::getInstrumentSid));

        // Map from TradableEntity sid to TradableEntitySnapshots
        Map<Long, List<TradableEntitySnapshot>> tradableEntitySnapshotsMap = ((List<TradableEntitySnapshot>) results
                .get("tradableEntitySnapshots")).stream()
                        .collect(Collectors.groupingBy(TradableEntitySnapshot::getTradableEntitySid));

        // Map from Instrument sid to TradableEntities
        Map<Long, List<TradableEntity>> tradableEntitiesMap = ((List<TradableEntity>) results
                .get("tradableEntities")).stream().collect(Collectors.groupingBy(e -> {
                    e.setTradableEntitySnapshots(tradableEntitySnapshotsMap.get(e.getTradableEntitySid()));
                    return e.getInstrumentSid();
                }));

        // Map from Instrument sid to underlying Instruments
        Map<Long, Instrument> underlyingInstrumentsMap = ((List<Instrument>) results.get("underlyingInstruments"))
                .stream().collect(Collectors.toMap(Instrument::getInstrumentSid, Function.identity()));

        // Map from Instrument sid to UnderlyingInstrumentLinks
        Map<Long, List<UnderlyingInstrumentLink>> underlyingInstrumentLinksMap = ((List<UnderlyingInstrumentLink>) results
                .get("underlyingInstrumentLinks")).stream().collect(Collectors.groupingBy(e -> {
                    long underlyingInstrumentSid = e.getUnderlyingInstrumentSid();
                    e.setUnderlyingInstrument(underlyingInstrumentsMap.get(underlyingInstrumentSid));
                    return e.getOverlayingInstrumentSid();
                }));

        List<Instrument> instruments = (List<Instrument>) results.get("instruments");

        for (Instrument instrument : instruments) {
            long instrumentSid = instrument.getInstrumentSid();
            instrument.setPutSchedules(putSchedulesMap.get(instrumentSid));
            instrument.setCallSchedules(callSchedulesMap.get(instrumentSid));
            instrument.setInterestRateSchedules(interestRateSchedulesMap.get(instrumentSid));
            instrument.setCashDividendSchedules(cashDividendSchedulesMap.get(instrumentSid));
            instrument.setUnderlyingInstruments(underlyingInstrumentLinksMap.get(instrumentSid));
            instrument.setTradableEntities(tradableEntitiesMap.get(instrumentSid));
        }

        return instruments;
    }

    /**
     * Gets the calculated SEC security data.
     * @param userId The user id.
     * @param businessDate the business date
     * @return the list of calculated SEC security data
     * @throws FundAccountingYieldException in case any error occurred during processing
     * @throws IllegalArgumentException in case the input is invalid (null)
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedSECData(String userId, Date businessDate) throws FundAccountingYieldException {
        return getFAYASECData(userId, businessDate);
    }
}
