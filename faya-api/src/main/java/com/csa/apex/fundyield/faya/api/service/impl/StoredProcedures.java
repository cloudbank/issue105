/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.support.oracle.BeanPropertyStructMapper;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.PersistenceException;
import com.csa.apex.fundyield.fayacommons.entities.CallSchedule;
import com.csa.apex.fundyield.fayacommons.entities.CashDividendSchedule;
import com.csa.apex.fundyield.fayacommons.entities.ColumnName;
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
import com.csa.apex.fundyield.utility.DateUtility;

import oracle.jdbc.OracleTypes;

/**
 * Provides the access to all Oracle stored procedures used in the application.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class StoredProcedures {

    /**
     * User id. Hard coded as currently there is no user context.
     */
    private static final String USER_ID = "CALCULATOR";

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
     * The auto wired jdbcTemplate.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * The stored procedure call to get instruments.
     */
    private SimpleJdbcCall getInstrumentsCall;

    /**
     * The stored procedure call to get portfolios.
     */
    private SimpleJdbcCall getPortfoliosCall;

    /**
     * The stored procedure call to save instrument.
     */
    private SimpleJdbcCall saveInstrumentCall;

    /**
     * The stored procedure call to save tradable entity.
     */
    private SimpleJdbcCall saveTradableEntityCall;

    /**
     * The stored procedure call to save tradable entity snapshot.
     */
    private SimpleJdbcCall saveTradableEntitySnapshotCall;

    /**
     * The stored procedure call to save portfolio.
     */
    private SimpleJdbcCall savePortfolioCall;

    /**
     * The stored procedure call to save portfolio snapshot.
     */
    private SimpleJdbcCall savePortfolioSnapshotCall;

    /**
     * The stored procedure call to save portfolio holding snapshot.
     */
    private SimpleJdbcCall savePortfolioHoldingSnapshotCall;

    /**
     * The stored procedure call to save share class snapshot.
     */
    private SimpleJdbcCall saveShareClassSnapshotCall;

    /**
     * Empty constructor.
     */
    public StoredProcedures() {
        // Empty
    }

    /**
     * Checks the configuration.
     * @throws ConfigurationException if any required field is not initialized properly.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(dataSource, "dataSource");

        // Initialize the stored procedure calls to get data
        this.getPortfoliosCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("GET_PORTFOLIO_DATA")
                .declareParameters(new SqlParameter("BUSINESS_DATE_START", Types.DATE),
                        new SqlParameter("BUSINESS_DATE_END", Types.DATE))
                .returningResultSet("PORTFOLIO_CUR", PortfolioRowMapper.INSTANCE)
                .returningResultSet("TAX_EXCLUSION_CUR", TaxExclusionRowMapper.INSTANCE)
                .returningResultSet("PORTFOLIO_SNAPSHOT_CUR", PortfolioSnapshotRowMapper.INSTANCE)
                .returningResultSet("PORTFOLIO_HOLDING_SNAPSHOT_CUR", PortfolioHoldingSnapshotRowMapper.INSTANCE)
                .returningResultSet("SHARE_CLASS_CUR", ShareClassRowMapper.INSTANCE)
                .returningResultSet("SHARE_CLASS_SNAPSHOT_CUR", ShareClassSnapshotRowMapper.INSTANCE)
                .returningResultSet("TRADABLE_ENTITY_CUR", TradableEntityRowMapper.INSTANCE)
                .returningResultSet("TRADABLE_ENTITY_SNAPSHOT_CUR", TradableEntitySnapshotRowMapper.INSTANCE);

        this.getInstrumentsCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("GET_INSTRUMENT_DATA")
                .declareParameters(new SqlParameter("BUSINESS_DATE_START", Types.DATE),
                        new SqlParameter("BUSINESS_DATE_END", Types.DATE))
                .returningResultSet("INSTRUMENT_CUR", InstrumentRowMapper.INSTANCE)
                .returningResultSet("UNDERLYING_INSTRUMENT_CUR", InstrumentRowMapper.INSTANCE)
                .returningResultSet("INTEREST_RATE_SCHEDULE_CUR", InterestRateScheduleRowMapper.INSTANCE)
                .returningResultSet("CASH_DIVIDEND_SCHEDULE_CUR", CashDividendScheduleRowMapper.INSTANCE)
                .returningResultSet("CALL_SCHEDULE_CUR", CallScheduleRowMapper.INSTANCE)
                .returningResultSet("PUT_SCHEDULE_CUR", PutScheduleRowMapper.INSTANCE)
                .returningResultSet("UNDERLYING_INSTRUMENT_LINK_CUR", UnderlyingInstrumentLinkRowMapper.INSTANCE)
                .returningResultSet("TRADABLE_ENTITY_CUR", TradableEntityRowMapper.INSTANCE)
                .returningResultSet("TRADABLE_ENTITY_SNAPSHOT_CUR", TradableEntitySnapshotRowMapper.INSTANCE);

        // Initialize the stored procedure calls to save data
        this.saveInstrumentCall = new SimpleJdbcCall(dataSource).withProcedureName("SAVE_INSTRUMENT")
                .declareParameters(new SqlParameter(ENTITY_PARAM, OracleTypes.STRUCT, "INSTRUMENT_T"),
                        new SqlOutParameter(OSID_PARAM, Types.DECIMAL));
        this.saveTradableEntityCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SAVE_TRADABLE_ENTITY")
                .declareParameters(new SqlParameter(ENTITY_PARAM, OracleTypes.STRUCT, "TRADABLE_ENTITY_T"),
                        new SqlOutParameter(OSID_PARAM, Types.DECIMAL));
        this.savePortfolioCall = new SimpleJdbcCall(dataSource).withProcedureName("SAVE_PORTFOLIO")
                .declareParameters(new SqlParameter(ENTITY_PARAM, OracleTypes.STRUCT, "PORTFOLIO_T"),
                        new SqlOutParameter(OSID_PARAM, Types.DECIMAL));
        this.savePortfolioSnapshotCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SAVE_PORTFOLIO_SNAPSHOT")
                .declareParameters(new SqlParameter(ENTITY_PARAM, OracleTypes.STRUCT, "PORTFOLIO_SNAPSHOT_T"),
                        new SqlParameter(UPDATE_CALC_RESULT_PARAM, Types.SMALLINT),
                        new SqlOutParameter(OSID_PARAM, Types.DECIMAL));
        this.saveTradableEntitySnapshotCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SAVE_TRADABLE_ENTITY_SNAPSHOT")
                .declareParameters(new SqlParameter(ENTITY_PARAM, OracleTypes.STRUCT, "TRADABLE_ENTITY_SNAPSHOT_T"),
                        new SqlParameter(UPDATE_CALC_RESULT_PARAM, Types.SMALLINT),
                        new SqlOutParameter(OSID_PARAM, Types.DECIMAL));
        this.savePortfolioHoldingSnapshotCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SAVE_PORTFOLIO_HOLDING")
                .declareParameters(new SqlParameter(ENTITY_PARAM, OracleTypes.STRUCT, "PORTFOLIO_HOLDING_T"),
                        new SqlParameter(UPDATE_CALC_RESULT_PARAM, Types.SMALLINT),
                        new SqlOutParameter(OSID_PARAM, Types.DECIMAL));
        this.saveShareClassSnapshotCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SAVE_SHARE_CLASS_SNAPSHOT")
                .declareParameters(new SqlParameter(ENTITY_PARAM, OracleTypes.STRUCT, "SHARE_CLASS_SNAPSHOT_T"),
                        new SqlParameter(UPDATE_CALC_RESULT_PARAM, Types.SMALLINT),
                        new SqlOutParameter(OSID_PARAM, Types.DECIMAL));
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
     * @param portfolio The portfolio data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    private void persistPortfolio(Portfolio portfolio) throws PersistenceException {

        try {
            if (portfolio.getPortfolioSnapshots() != null) {
                portfolio.getPortfolioSnapshots().forEach(snapshot -> {
                    snapshot.setCreateId(USER_ID);
                    savePortfolioSnapshot(snapshot, true);
                });
            }
            if (portfolio.getPortfolioHoldings() != null) {
                portfolio.getPortfolioHoldings().forEach(snapshot -> {
                    snapshot.setCreateId(USER_ID);
                    savePortfolioHoldingSnapshot(snapshot, true);
                });
            }
            if (portfolio.getShareClasses() != null) {
                portfolio.getShareClasses().forEach(sc -> {
                    if (sc.getShareClassSnapshots() != null) {
                        sc.getShareClassSnapshots().forEach(snapshot -> {
                            snapshot.setCreateId(USER_ID);
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
     * @param fundAccountingYieldData The FAYA data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    public void saveFAYAPortfolioData(FundAccountingYieldData fundAccountingYieldData) throws PersistenceException {
        if (fundAccountingYieldData.getPortfolios() != null) {
            for (Portfolio portfolio : fundAccountingYieldData.getPortfolios()) {
                persistPortfolio(portfolio);
            }
        }
    }

    /**
     * Persist instrument data.
     * @param instrument The instrument data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    private void persistInstrument(Instrument instrument) throws PersistenceException {

        try {
            if (instrument.getTradableEntities() != null) {
                instrument.getTradableEntities().forEach(te -> {
                    if (te.getTradableEntitySnapshots() != null) {
                        te.getTradableEntitySnapshots().forEach(snapshot -> {
                            snapshot.setCreateId(USER_ID);
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
     * @param fundAccountingYieldData The FAYA data to be persisted
     * @throws PersistenceException in case any error occurred during persisting
     */
    public void saveFAYAInstrumentData(FundAccountingYieldData fundAccountingYieldData) throws PersistenceException {
        if (fundAccountingYieldData.getInstruments() != null) {
            for (Instrument instrument : fundAccountingYieldData.getInstruments()) {
                persistInstrument(instrument);
            }
        }
    }

    /**
     * Save Instrument.
     *
     * @param entity The Instrument to save
     * @param updateCalcResult Flag indicating whether should update the calculation result columns
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
        params.put(ENTITY_PARAM, new SqlStructValue<Instrument>(entity, INSTRUMENT_MAPPER));

        Map<String, Object> result = this.saveInstrumentCall.execute(params);
        entity.setInstrumentSid(((Number) result.get(OSID_PARAM)).longValue());
    }

    /**
     * Save Portfolio.
     *
     * @param entity The Portfolio to save
     * @param updateCalcResult Flag indicating whether should update the calculation result columns
     */
    public void savePortfolio(Portfolio entity) {
        entity.setHashKeyTxt(CommonUtility.getHashText(entity));

        entity.setEffectiveDate(DateUtility.convertToSqlDate(entity.getEffectiveDate()));
        entity.setExpirationDate(DateUtility.convertToSqlDate(entity.getExpirationDate()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<Portfolio>(entity, PORTFOLIO_MAPPER));

        Map<String, Object> result = this.savePortfolioCall.execute(params);
        entity.setPortfolioSid(((Number) result.get(OSID_PARAM)).longValue());
    }

    /**
     * Save TradableEntity.
     *
     * @param entity The TradableEntity to save
     * @param updateCalcResult Flag indicating whether should update the calculation result columns
     */
    public void saveTradableEntity(TradableEntity entity) {
        entity.setHashKeyTxt(CommonUtility.getHashText(entity));

        entity.setEffectiveDate(DateUtility.convertToSqlDate(entity.getEffectiveDate()));
        entity.setExpirationDate(DateUtility.convertToSqlDate(entity.getExpirationDate()));
        entity.setCreateTs(DateUtility.convertToSqlDate(entity.getCreateTs()));
        entity.setUpdateTs(DateUtility.convertToSqlDate(entity.getUpdateTs()));

        Map<String, Object> params = new HashMap<>();
        params.put(ENTITY_PARAM, new SqlStructValue<TradableEntity>(entity, TRADABLE_ENTITY_MAPPER));

        Map<String, Object> result = this.saveTradableEntityCall.execute(params);
        entity.setTradableEntitySid(((Number) result.get(OSID_PARAM)).longValue());
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
        params.put(ENTITY_PARAM, new SqlStructValue<TradableEntitySnapshot>(entity, TRADABLE_ENTITY_SNAMPSHOT_MAPPER));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        Map<String, Object> result = this.saveTradableEntitySnapshotCall.execute(params);
        entity.setTradableEntitySnapshotSid(((Number) result.get(OSID_PARAM)).longValue());
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
        params.put(ENTITY_PARAM, new SqlStructValue<PortfolioSnapshot>(entity, PORTFOLIO_SNAPSHOT_MAPPER));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        Map<String, Object> result = this.savePortfolioSnapshotCall.execute(params);
        entity.setPortfolioSnapshotSid(((Number) result.get(OSID_PARAM)).longValue());
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
        params.put(ENTITY_PARAM, new SqlStructValue<PortfolioHoldingSnapshot>(entity, PORTFOLIO_HOLDING_MAPPER));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        Map<String, Object> result = this.savePortfolioHoldingSnapshotCall.execute(params);
        entity.setPortfolioHoldingSnapshotSid(((Number) result.get(OSID_PARAM)).longValue());
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
        params.put(ENTITY_PARAM, new SqlStructValue<ShareClassSnapshot>(entity, SHARE_CLASS_SNAPSHOT_MAPPER));
        params.put(UPDATE_CALC_RESULT_PARAM, updateCalcResult ? 1 : 0);

        Map<String, Object> result = this.saveShareClassSnapshotCall.execute(params);
        entity.setShareClassSnapshotSid(((Number) result.get(OSID_PARAM)).longValue());
    }

    /**
     * Get instruments.
     *
     * @param dateParams The data parameters
     * @return instrument related entities
     */
    public Map<String, Object> getInstruments(Map<String, Date> dateParams) {
        return this.getInstrumentsCall.execute(dateParams);
    }

    /**
     * Get portfolios.
     *
     * @param dateParams The data parameters
     * @return portfolio related entities
     */
    public Map<String, Object> getPortfolios(Map<String, Date> dateParams) {
        return this.getPortfoliosCall.execute(dateParams);
    }

    /**
     * Inner row mapper to create the PortfolioSnapshot from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class PortfolioSnapshotRowMapper extends BaseRowMapper<PortfolioSnapshot> {

        /**
         * The instance.
         */
        private static final PortfolioSnapshotRowMapper INSTANCE = new PortfolioSnapshotRowMapper();

        /**
         * Constructor.
         */
        private PortfolioSnapshotRowMapper() {
            super(PortfolioSnapshot.class);
        }
    }

    /**
     * Inner row mapper to create the PortfolioHoldingSnapshot from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class PortfolioHoldingSnapshotRowMapper extends BaseRowMapper<PortfolioHoldingSnapshot> {

        /**
         * The instance.
         */
        private static final PortfolioHoldingSnapshotRowMapper INSTANCE = new PortfolioHoldingSnapshotRowMapper();

        /**
         * Constructor.
         */
        private PortfolioHoldingSnapshotRowMapper() {
            super(PortfolioHoldingSnapshot.class);
        }
    }

    /**
     * Inner row mapper to create the TaxExclusion from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class TaxExclusionRowMapper extends BaseRowMapper<TaxExclusion> {

        /**
         * The instance.
         */
        private static final TaxExclusionRowMapper INSTANCE = new TaxExclusionRowMapper();

        /**
         * Constructor.
         */
        private TaxExclusionRowMapper() {
            super(TaxExclusion.class);
        }
    }

    /**
     * Inner row mapper to create the ShareClassSnapshot from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class ShareClassSnapshotRowMapper extends BaseRowMapper<ShareClassSnapshot> {

        /**
         * The instance.
         */
        private static final ShareClassSnapshotRowMapper INSTANCE = new ShareClassSnapshotRowMapper();

        /**
         * Constructor.
         */
        private ShareClassSnapshotRowMapper() {
            super(ShareClassSnapshot.class);
        }
    }

    /**
     * Inner row mapper to create the ShareClass from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class ShareClassRowMapper extends BaseRowMapper<ShareClass> {

        /**
         * The instance.
         */
        private static final ShareClassRowMapper INSTANCE = new ShareClassRowMapper();

        /**
         * Constructor.
         */
        private ShareClassRowMapper() {
            super(ShareClass.class);
        }
    }

    /**
     * Inner row mapper to create the Portfolio from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class PortfolioRowMapper extends BaseRowMapper<Portfolio> {

        /**
         * The instance.
         */
        private static final PortfolioRowMapper INSTANCE = new PortfolioRowMapper();

        /**
         * Constructor.
         */
        private PortfolioRowMapper() {
            super(Portfolio.class);
        }
    }

    /**
     * Inner row mapper to create the UnderlyingInstrumentLink from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class UnderlyingInstrumentLinkRowMapper extends BaseRowMapper<UnderlyingInstrumentLink> {

        /**
         * The instance.
         */
        private static final UnderlyingInstrumentLinkRowMapper INSTANCE = new UnderlyingInstrumentLinkRowMapper();

        /**
         * Constructor.
         */
        private UnderlyingInstrumentLinkRowMapper() {
            super(UnderlyingInstrumentLink.class);
        }
    }

    /**
     * Inner row mapper to create the TradableEntitySnapshot from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class TradableEntitySnapshotRowMapper extends BaseRowMapper<TradableEntitySnapshot> {

        /**
         * The instance.
         */
        private static final TradableEntitySnapshotRowMapper INSTANCE = new TradableEntitySnapshotRowMapper();

        /**
         * Constructor.
         */
        private TradableEntitySnapshotRowMapper() {
            super(TradableEntitySnapshot.class);
        }
    }

    /**
     * Inner row mapper to create the TradableEntity from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class TradableEntityRowMapper extends BaseRowMapper<TradableEntity> {

        /**
         * The instance.
         */
        private static final TradableEntityRowMapper INSTANCE = new TradableEntityRowMapper();

        /**
         * Constructor.
         */
        private TradableEntityRowMapper() {
            super(TradableEntity.class);
        }
    }

    /**
     * Inner row mapper to create the PutSchedule from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class PutScheduleRowMapper extends BaseRowMapper<PutSchedule> {

        /**
         * The instance.
         */
        private static final PutScheduleRowMapper INSTANCE = new PutScheduleRowMapper();

        /**
         * Constructor.
         */
        private PutScheduleRowMapper() {
            super(PutSchedule.class);
        }
    }

    /**
     * Inner row mapper to create the CallSchedule from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class CallScheduleRowMapper extends BaseRowMapper<CallSchedule> {

        /**
         * The instance.
         */
        private static final CallScheduleRowMapper INSTANCE = new CallScheduleRowMapper();

        /**
         * Constructor.
         */
        private CallScheduleRowMapper() {
            super(CallSchedule.class);
        }
    }

    /**
     * Inner row mapper to create the CashDividendSchedule from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class CashDividendScheduleRowMapper extends BaseRowMapper<CashDividendSchedule> {

        /**
         * The instance.
         */
        private static final CashDividendScheduleRowMapper INSTANCE = new CashDividendScheduleRowMapper();

        /**
         * Constructor.
         */
        private CashDividendScheduleRowMapper() {
            super(CashDividendSchedule.class);
        }
    }

    /**
     * Inner row mapper to create the InterestRateSchedule from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class InterestRateScheduleRowMapper extends BaseRowMapper<InterestRateSchedule> {

        /**
         * The instance.
         */
        private static final InterestRateScheduleRowMapper INSTANCE = new InterestRateScheduleRowMapper();

        /**
         * Constructor.
         */
        private InterestRateScheduleRowMapper() {
            super(InterestRateSchedule.class);
        }
    }

    /**
     * Inner row mapper to create the Instrument from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class InstrumentRowMapper extends BaseRowMapper<Instrument> {

        /**
         * The instance.
         */
        private static final InstrumentRowMapper INSTANCE = new InstrumentRowMapper();

        /**
         * Constructor.
         * @param columnToPropertyMap the column name to property name map
         */
        private InstrumentRowMapper() {
            super(Instrument.class);
        }
    }

    /**
     * Base row mapper to create the entity from the row. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class BaseRowMapper<T> implements RowMapper<T> {

        /**
         * Bean processor.
         */
        private final BeanProcessor beanProcessor;

        /**
         * Entity type.
         */
        private final Class<T> entityType;

        /**
         * Constructor.
         * @param entityType the entity type
         */
        private BaseRowMapper(Class<T> entityType) {
            // Build the column name to field name mapping
            List<Field> fields = FieldUtils.getFieldsListWithAnnotation(entityType, ColumnName.class);
            Map<String, String> columnToFieldMap = fields.stream().collect(
                    Collectors.toMap(field -> field.getAnnotation(ColumnName.class).value(), field -> field.getName()));

            this.beanProcessor = new CustomBeanProcessor(columnToFieldMap);
            this.entityType = entityType;
        }

        /**
         * Map result row to <code>Instrument</code>.
         * @param rs the ResultSet to map (pre-initialized for the current row)
         * @param rowNum the number of the current row
         * @return the result object for the current row
         * @throws SQLException if a SQLException is encountered
         */
        @Override
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            return beanProcessor.toBean(rs, entityType);
        }
    }

    /**
     * Bean processor matches column names to bean property names
     * and converts <code>ResultSet</code> columns into objects for those bean
     * properties. This class is thread safe.
     * @author [es], TCSDEVELOPER
     * @version 1.0
     * @since FAYA Java App - Phase 1 Updates Code Challenge
     */
    private static class CustomBeanProcessor extends BeanProcessor {

        /**
         * Constructor with column to property name overrides.
         *
         * @param columnToPropertyOverrides ResultSet column to bean property name overrides
         */
        private CustomBeanProcessor(Map<String, String> columnToPropertyOverrides) {
            super(columnToPropertyOverrides);
        }

        /**
         * Override the <code>processColumn()</code> method to handle additional types
         * @param rs The <code>ResultSet</code> currently being processed. It is positioned on a valid row
         *            before being passed into this method.
         * @param index The current column index being processed.
         * @param propType The bean property type that this column needs to be converted into.
         * @return The object from the <code>ResultSet</code> at the given column index
         * @throws SQLException if a database access error occurs
         */
        @Override
        protected Object processColumn(ResultSet rs, int index, Class<?> propType) throws SQLException {
            if (propType.equals(Date.class)) {
                return rs.getDate(index);
            }
            return super.processColumn(rs, index, propType);
        }
    }
}
