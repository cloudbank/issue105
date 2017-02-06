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
     * The auto wired StoredProcedures.
     */
    @Autowired
    private StoredProcedures storedProcedures;

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
        CommonUtility.checkNullConfig(storedProcedures, this.getClass().getCanonicalName(), "storedProcedures");
    }

    /**
     * Persists the calculated SEC security data.
     * @param fundAccountingYieldData The FundAccountingYieldData to be persisted
     * @return flag indicating whether The data was persisted or not
     * @throws IllegalArgumentException in case the input is invalid (null)
     * @throws FundAccountingYieldException in case any error occurred during processing
     */
    @Override
    @LogMethod
    @Transactional
    public boolean persistSecuritySECData(FundAccountingYieldData fundAccountingYieldData)
            throws FundAccountingYieldException {
        CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), "persistSecuritySECData", "Parameter fundAccountingYieldData");

        storedProcedures.saveFAYAInstrumentData(fundAccountingYieldData);
        storedProcedures.saveFAYAPortfolioData(fundAccountingYieldData);

        return true;
    }

    /**
     * Gets the SEC Security.
     * @param businessDate the business date
     * @return the list of security SEC data
     * @throws FundAccountingYieldException in case any error occurred during processing
     * @throws IllegalArgumentException in case the input is invalid (null)
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getFAYASECData(Date businessDate) throws FundAccountingYieldException {
        CommonUtility.checkNull(businessDate, this.getClass().getCanonicalName(), "getFAYASECData", "Parameter businessDate");

        // The business date input parameters
        DateTime businessDateTime = new DateTime(businessDate).withTimeAtStartOfDay();
        Map<String, Date> dateParams = new HashMap<>();
        dateParams.put("BUSINESS_DATE_START", businessDateTime.toDate());
        dateParams.put("BUSINESS_DATE_END", businessDateTime.plusDays(1).withTimeAtStartOfDay().toDate());

        FundAccountingYieldData data = new FundAccountingYieldData();
        data.setBusinessDate(businessDateTime.toDate());
        data.setReportDate(data.getBusinessDate());

        try {
            // Query instrument data
            data.setInstruments(populateInstrumentData(this.storedProcedures.getInstruments(dateParams)));

            // Query portfolio data
            data.setPortfolios(populatePortfolioData(this.storedProcedures.getPortfolios(dateParams)));
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
                .get("TRADABLE_ENTITY_SNAPSHOT_CUR")).stream()
                        .collect(Collectors.groupingBy(TradableEntitySnapshot::getTradableEntitySid));

        // Map from TradableEntity sid to TradableEntity
        Map<Long, TradableEntity> tradableEntitiesMap = ((List<TradableEntity>) results.get("TRADABLE_ENTITY_CUR"))
                .stream().collect(Collectors.toMap(TradableEntity::getTradableEntitySid, e -> {
                    e.setTradableEntitySnapshots(tradableEntitySnapshotsMap.get(e.getTradableEntitySid()));
                    return e;
                }));

        // Map from Portfolio sid to PortfolioHoldingSnapshots
        Map<Long, List<PortfolioHoldingSnapshot>> portfolioHoldingSnapshotsMap = ((List<PortfolioHoldingSnapshot>) results
                .get("PORTFOLIO_HOLDING_SNAPSHOT_CUR")).stream().collect(Collectors.groupingBy(e -> {
                    e.setTradableEntity(tradableEntitiesMap.get(e.getTradableEntitySid()));
                    return e.getPortfolioSid();
                }));

        // Map from ShareClass sid to ShareClassSnapshots
        Map<Long, List<ShareClassSnapshot>> shareClassSnapshotsMap = ((List<ShareClassSnapshot>) results
                .get("SHARE_CLASS_SNAPSHOT_CUR")).stream()
                        .collect(Collectors.groupingBy(ShareClassSnapshot::getShareClassSid));

        // Map from Portfolio sid to ShareClasses
        Map<Long, List<ShareClass>> shareClassesMap = ((List<ShareClass>) results.get("SHARE_CLASS_CUR")).stream()
                .collect(Collectors.groupingBy(e -> {
                    e.setShareClassSnapshots(shareClassSnapshotsMap.get(e.getShareClassSid()));
                    return e.getPortfolioSid();
                }));

        // Map from Portfolio sid to PortfolioSnapshots
        Map<Long, List<PortfolioSnapshot>> portfolioSnapshotsMap = ((List<PortfolioSnapshot>) results
                .get("PORTFOLIO_SNAPSHOT_CUR")).stream()
                        .collect(Collectors.groupingBy(PortfolioSnapshot::getPortfolioSid));

        // Map from Portfolio sid to TaxExclusions
        Map<Long, List<TaxExclusion>> taxExclusionsMap = ((List<TaxExclusion>) results.get("TAX_EXCLUSION_CUR"))
                .stream().collect(Collectors.groupingBy(TaxExclusion::getPortfolioSid));

        List<Portfolio> portfolios = (List<Portfolio>) results.get("PORTFOLIO_CUR");

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
    @SuppressWarnings("unchecked")
    private static List<Instrument> populateInstrumentData(Map<String, Object> results) {
        // Map from Instrument sid to InterestRateSchedules
        Map<Long, List<InterestRateSchedule>> interestRateSchedulesMap = ((List<InterestRateSchedule>) results
                .get("INTEREST_RATE_SCHEDULE_CUR")).stream()
                        .collect(Collectors.groupingBy(InterestRateSchedule::getInstrumentSid));

        // Map from Instrument sid to CashDividendSchedules
        Map<Long, List<CashDividendSchedule>> cashDividendSchedulesMap = ((List<CashDividendSchedule>) results
                .get("CASH_DIVIDEND_SCHEDULE_CUR")).stream()
                        .collect(Collectors.groupingBy(CashDividendSchedule::getInstrumentSid));

        // Map from Instrument sid to CallSchedules
        Map<Long, List<CallSchedule>> callSchedulesMap = ((List<CallSchedule>) results.get("CALL_SCHEDULE_CUR"))
                .stream().collect(Collectors.groupingBy(CallSchedule::getInstrumentSid));

        // Map from Instrument sid to PutSchedules
        Map<Long, List<PutSchedule>> putSchedulesMap = ((List<PutSchedule>) results.get("PUT_SCHEDULE_CUR")).stream()
                .collect(Collectors.groupingBy(PutSchedule::getInstrumentSid));

        // Map from TradableEntity sid to TradableEntitySnapshots
        Map<Long, List<TradableEntitySnapshot>> tradableEntitySnapshotsMap = ((List<TradableEntitySnapshot>) results
                .get("TRADABLE_ENTITY_SNAPSHOT_CUR")).stream()
                        .collect(Collectors.groupingBy(TradableEntitySnapshot::getTradableEntitySid));

        // Map from Instrument sid to TradableEntities
        Map<Long, List<TradableEntity>> tradableEntitiesMap = ((List<TradableEntity>) results
                .get("TRADABLE_ENTITY_CUR")).stream().collect(Collectors.groupingBy(e -> {
                    e.setTradableEntitySnapshots(tradableEntitySnapshotsMap.get(e.getTradableEntitySid()));
                    return e.getInstrumentSid();
                }));

        // Map from Instrument sid to underlying Instruments
        Map<Long, Instrument> underlyingInstrumentsMap = ((List<Instrument>) results.get("UNDERLYING_INSTRUMENT_CUR"))
                .stream().collect(Collectors.toMap(Instrument::getInstrumentSid, Function.identity()));

        // Map from Instrument sid to UnderlyingInstrumentLinks
        Map<Long, List<UnderlyingInstrumentLink>> underlyingInstrumentLinksMap = ((List<UnderlyingInstrumentLink>) results
                .get("UNDERLYING_INSTRUMENT_LINK_CUR")).stream().collect(Collectors.groupingBy(e -> {
                    long underlyingInstrumentSid = e.getUnderlyingInstrumentSid();
                    e.setUnderlyingInstrument(underlyingInstrumentsMap.get(underlyingInstrumentSid));
                    return e.getOverlayingInstrumentSid();
                }));

        List<Instrument> instruments = (List<Instrument>) results.get("INSTRUMENT_CUR");

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
     * @param businessDate the business date
     * @return the list of calculated SEC security data
     * @throws FundAccountingYieldException in case any error occurred during processing
     * @throws IllegalArgumentException in case the input is invalid (null)
     */
    @Override
    @LogMethod
    public FundAccountingYieldData getCalculatedSECData(Date businessDate) throws FundAccountingYieldException {
        return getFAYASECData(businessDate);
    }
}
