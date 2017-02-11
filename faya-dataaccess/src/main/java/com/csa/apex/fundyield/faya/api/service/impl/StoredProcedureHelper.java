/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.csa.apex.fundyield.utility.Constants;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.support.oracle.BeanPropertyStructMapper;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.PersistenceException;
import com.csa.apex.fundyield.fayacommons.entities.ColumnName;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.ShareClassSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.DateUtility;

/**
 * Provides the access to all Oracle stored procedures used in the application.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class StoredProcedureHelper {

    /**
     * The auto wired storedProcedure.
     */
    @Autowired
    private StoredProcedure storedProcedure;

    /**
     * Struct mapper for Instrument.
     */
    private static final StructMapper<Instrument> INSTRUMENT_MAPPER = createStructMapper(Instrument.class);

    /**
     * Struct mapper for Portfolio.
     */
    private static final StructMapper<Portfolio> PORTFOLIO_MAPPER = createStructMapper(Portfolio.class);

    /**
     * Struct mapper for TradableEntity.
     */
    private static final StructMapper<TradableEntity> TRADABLE_ENTITY_MAPPER = createStructMapper(TradableEntity.class);

    /**
     * Struct mapper for TradableEntitySnapshot.
     */
    private static final StructMapper<TradableEntitySnapshot> TRADABLE_ENTITY_SNAMPSHOT_MAPPER = createStructMapper(
            TradableEntitySnapshot.class);

    /**
     * Struct mapper for PortfolioSnapshot.
     */
    private static final StructMapper<PortfolioSnapshot> PORTFOLIO_SNAPSHOT_MAPPER = createStructMapper(
            PortfolioSnapshot.class);

    /**
     * Struct mapper for PortfolioHoldingSnapshot.
     */
    private static final StructMapper<PortfolioHoldingSnapshot> PORTFOLIO_HOLDING_MAPPER = createStructMapper(
            PortfolioHoldingSnapshot.class);

    /**
     * Struct mapper for ShareClassSnapshot.
     */
    private static final StructMapper<ShareClassSnapshot> SHARE_CLASS_SNAPSHOT_MAPPER = createStructMapper(
            ShareClassSnapshot.class);

    /**
     * The "entity" input parameter name.
     */
    private static final String ENTITY_PARAM = "entity";

    /**
     * The "updateCalcResult" input parameter name.
     */
    private static final String UPDATE_CALC_RESULT_PARAM = "updateCalcResult";

    /**
     * The "oSid" output parameter name.
     */
    private static final String OSID_PARAM = "oSid";

    /**
     * Empty constructor.
     */
    public StoredProcedureHelper() {
        // Empty
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(storedProcedure, this.getClass().getCanonicalName(), Constants.STORED_PROCEDURE);
    }

    /**
     * Create oracle struct mapper.
     * @param entityType the entity type
     * @return struct mapper
     */
    private static <T> StructMapper<T> createStructMapper(Class<T> entityType) {
        BeanPropertyStructMapper<T> mapper = new BeanPropertyStructMapper<>(entityType);
        Map<String, PropertyDescriptor> mappedFields = mapper.getMappedFields();

        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(entityType, ColumnName.class);
        fields.forEach(field -> {
            String columnName = field.getAnnotation(ColumnName.class).value().toLowerCase();
            mappedFields.put(columnName, mappedFields.get(field.getName().toLowerCase()));
        });
        return mapper;
    }

    /**
     * Persist portfolio data.
     * @param userId The user id
     * @param portfolio The portfolio data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    private void persistPortfolio(String userId, Portfolio portfolio) throws PersistenceException {

        try {
            if (portfolio.getPortfolioSnapshots() != null) {
                portfolio.getPortfolioSnapshots().forEach(snapshot -> {
                    snapshot.setCreateId(userId);
                    savePortfolioSnapshot(snapshot, true);
                });
            }
            if (portfolio.getPortfolioHoldings() != null) {
                portfolio.getPortfolioHoldings().forEach(snapshot -> {
                    snapshot.setCreateId(userId);
                    savePortfolioHoldingSnapshot(snapshot, true);
                });
            }
            if (portfolio.getShareClasses() != null) {
                portfolio.getShareClasses().forEach(sc -> {
                    if (sc.getShareClassSnapshots() != null) {
                        sc.getShareClassSnapshots().forEach(snapshot -> {
                            snapshot.setCreateId(userId);
                            saveShareClassSnapshot(snapshot, true);
                        });
                    }
                });
            }
        } catch (DataAccessException dae) {
            throw new PersistenceException("Failed to save portfolio: " + portfolio, dae);
        }
    }

    /**
     * Persist portfolio data.
     * @param userId The user id
     * @param fundAccountingYieldData The FAYA data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    public void saveFAYAPortfolioData(String userId, FundAccountingYieldData fundAccountingYieldData) throws PersistenceException {
        if (fundAccountingYieldData.getPortfolios() != null) {
            for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
                persistPortfolio(userId, portfolio);
            }
        }
    }

    /**
     * Persist instrument data.
     * @param userId The user id
     * @param instrument The instrument data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    private void persistInstrument(String userId, Instrument instrument) throws PersistenceException {

        try {
            if (instrument.getTradableEntities() != null) {
                instrument.getTradableEntities().forEach(te -> {
                    if (te.getTradableEntitySnapshots() != null) {
                        te.getTradableEntitySnapshots().forEach(snapshot -> {
                            snapshot.setCreateId(userId);
                            saveTradableEntitySnapshot(snapshot, true);
                        });
                    }
                });
            }
        } catch (DataAccessException dae) {
            throw new PersistenceException("Failed to save instrument: " + instrument, dae);
        }
    }


    /**
     * Persist instrument data.
     * @param userId The user id
     * @param fundAccountingYieldData The FAYA data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    public void saveFAYAInstrumentData(String userId, FundAccountingYieldData fundAccountingYieldData) throws PersistenceException {
        if (fundAccountingYieldData.getInstruments() != null) {
            for (Instrument instrument : fundAccountingYieldData.getInstruments()) {
                persistInstrument(userId, instrument);
            }
        }
    }

    /**
     * Save Instrument.
     *
     * @param entity The Instrument to save
     */
    public void saveInstrument(Instrument entity) {
        entity.setHashKeyTxt(CommonUtility.getHashText(entity));

        entity.setEffectiveDate(DateUtility.convertToSqlDate(entity.getEffectiveDate()));
        entity.setExpirationDate(DateUtility.convertToSqlDate(entity.getExpirationDate()));
        entity.setFinalMaturityDate(DateUtility.convertToSqlDate(entity.getFinalMaturityDate()));
        entity.setOriginalIssueDate(DateUtility.convertToSqlDate(entity.getOriginalIssueDate()));
        entity.setPreRefundedDate(DateUtility.convertToSqlDate(entity.getPreRefundedDate()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<>(entity, INSTRUMENT_MAPPER, "INSTRUMENT_T"));

        this.storedProcedure.saveInstrument(params);
        entity.setInstrumentSid(((Number) params.get(OSID_PARAM)).longValue());
    }

    /**
     * Save Portfolio.
     *
     * @param entity The Portfolio to save
     */
    public void savePortfolio(Portfolio entity) {
        entity.setHashKeyTxt(CommonUtility.getHashText(entity));

        entity.setEffectiveDate(DateUtility.convertToSqlDate(entity.getEffectiveDate()));
        entity.setExpirationDate(DateUtility.convertToSqlDate(entity.getExpirationDate()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<>(entity, PORTFOLIO_MAPPER, "PORTFOLIO_T"));

        storedProcedure.savePortfolio(params);
        entity.setPortfolioSid(((Number) params.get(OSID_PARAM)).longValue());
    }

    /**
     * Save TradableEntity.
     *
     * @param entity The TradableEntity to save
     */
    public void saveTradableEntity(TradableEntity entity) {
        entity.setHashKeyTxt(CommonUtility.getHashText(entity));

        entity.setEffectiveDate(DateUtility.convertToSqlDate(entity.getEffectiveDate()));
        entity.setExpirationDate(DateUtility.convertToSqlDate(entity.getExpirationDate()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<>(entity, TRADABLE_ENTITY_MAPPER, "TRADABLE_ENTITY_T"));

        this.storedProcedure.saveTradableEntity(params);
        entity.setTradableEntitySid(((Number) params.get(OSID_PARAM)).longValue());
    }

    /**
     * Save TradableEntitySnapshot.
     *
     * @param entity The TradableEntitySnapshot to save
     * @param updateCalcResult Flag indicating whether should update the calculation result columns
     */
    public void saveTradableEntitySnapshot(TradableEntitySnapshot entity, boolean updateCalcResult) {
        entity.setReportDate(DateUtility.convertToSqlDate(entity.getReportDate()));
        entity.setDerRedemptionDate(DateUtility.convertToSqlDate(entity.getDerRedemptionDate()));
        entity.setLastAdjTs(DateUtility.convertToSqlDate(entity.getLastAdjTs()));
        entity.setLastAdjApprovalTs(DateUtility.convertToSqlDate(entity.getLastAdjApprovalTs()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<>(entity, TRADABLE_ENTITY_SNAMPSHOT_MAPPER, "TRADABLE_ENTITY_SNAPSHOT_T"));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        this.storedProcedure.saveTradableEntitySnapshot(params);
        entity.setTradableEntitySnapshotSid(((Number) params.get(OSID_PARAM)).longValue());
    }

    /**
     * Save PortfolioSnapshot.
     *
     * @param entity The PortfolioSnapshot to save
     * @param updateCalcResult Flag indicating whether should update the calculation result columns
     */
    public void savePortfolioSnapshot(PortfolioSnapshot entity, boolean updateCalcResult) {
        entity.setReportDate(DateUtility.convertToSqlDate(entity.getReportDate()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<>(entity, PORTFOLIO_SNAPSHOT_MAPPER, "PORTFOLIO_SNAPSHOT_T"));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        this.storedProcedure.savePortfolioSnapshot(params);
        entity.setPortfolioSnapshotSid(((Number) params.get(OSID_PARAM)).longValue());
    }

    /**
     * Save PortfolioHoldingSnapshot.
     *
     * @param entity The PortfolioHoldingSnapshot to save
     * @param updateCalcResult Flag indicating whether should update the calculation result columns
     */
    public void savePortfolioHoldingSnapshot(PortfolioHoldingSnapshot entity, boolean updateCalcResult) {
        entity.setReportDate(DateUtility.convertToSqlDate(entity.getReportDate()));
        entity.setLastAdjTs(DateUtility.convertToSqlDate(entity.getLastAdjTs()));
        entity.setLastAdjApprovalTs(DateUtility.convertToSqlDate(entity.getLastAdjApprovalTs()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<>(entity, PORTFOLIO_HOLDING_MAPPER, "PORTFOLIO_HOLDING_T"));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        this.storedProcedure.savePortfolioHolding(params);
        entity.setPortfolioHoldingSnapshotSid(((Number) params.get(OSID_PARAM)).longValue());
    }

    /**
     * Save ShareClassSnapshot.
     *
     * @param entity The ShareClassSnapshot to save
     * @param updateCalcResult Flag indicating whether should update the calculation result columns
     */
    public void saveShareClassSnapshot(ShareClassSnapshot entity, boolean updateCalcResult) {
        entity.setReportDate(DateUtility.convertToSqlDate(entity.getReportDate()));
        entity.setCalendarDate(DateUtility.convertToSqlDate(entity.getCalendarDate()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<>(entity, SHARE_CLASS_SNAPSHOT_MAPPER, "SHARE_CLASS_SNAPSHOT_T"));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        this.storedProcedure.saveShareClassSnapshot(params);
        entity.setShareClassSnapshotSid(((Number) params.get(OSID_PARAM)).longValue());
    }

}
