/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import com.csa.apex.secyield.customer.api.mock.service.CustomerDataPersistenceService;
import com.csa.apex.secyield.customer.api.mock.utility.PositionDataColumns;
import com.csa.apex.secyield.customer.api.mock.utility.SecuritySECDataColumns;
import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.ConfigurationException;
import com.csa.apex.secyield.exceptions.SECYieldException;

/**
 * Persistence service for customer data operations implementing the persistence interface. This class is effectively
 * thread safe.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class CustomerDataPersistenceServiceImpl implements CustomerDataPersistenceService {

	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(CustomerDataPersistenceServiceImpl.class);

	/**
	 * The autowired jdbcTemplate
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * The injected SECConfiguration
	 */
	private SECConfiguration secConfiguration;

	/**
	 * Configuration exception message
	 */
	@Value("${messages.configurationargumentexception}")
	private String configurationArgumentExceptionMessage;

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.illegalargumentexception}")
	private String illegalArgumentExceptionMessage;

	/**
	 * Error log message format
	 */
	@Value("${messages.errorlogmessage}")
	private String logErrorFormat;

	/**
	 * extractSecuritySECData method name
	 */
	@Value("${customerdatapersistenceserviceimpl.extractsecuritysecdatamethodname}")
	private String extractSecuritySECDataMethodName;

	/**
	 * persistSecuritySECData method name
	 */
	@Value("${customerdatapersistenceserviceimpl.persistsecuritysecdata}")
	private String persistSecuritySECDataMethodName;

	/**
	 * The jdbc insert utility for security SEC data
	 */
	private SimpleJdbcInsert insertSecuritySECData;

	/**
	 * The jdbc insert utility for position data
	 */
	private SimpleJdbcInsert insertPositionData;

	/**
	 * The db date format
	 */
	private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd");

	/**
	 * The table name of calculated_position_data
	 */
	public static final String TABLE_CALCULATED_POSITION_DATA = "calculated_position_data";

	/**
	 * The table name of calculated_security_sec_data
	 */
	public static final String TABLE_CALCULATED_SECURITY_SEC_DATA = "calculated_security_sec_data";

	/**
	 * The table name of customer_position_data
	 */
	public static final String TABLE_CUSTOMER_POSITION_DATA = "customer_position_data";

	/**
	 * The table name of customer_security_sec_data
	 */
	public static final String TABLE_CUSTOMER_SECURITY_SEC_DATA = "customer_security_sec_data";

	/**
	 * The select query for retrieving all calculated security SEC data with their position data
	 */
	private static final String CALCULATED_SECURITY_SEC_DATA_QUERY = "SELECT * FROM "
			+ TABLE_CALCULATED_SECURITY_SEC_DATA + " cssd " + "LEFT JOIN " + TABLE_CALCULATED_POSITION_DATA
			+ " cpd ON cssd.security_identifier = cpd.security_identifier AND cssd.report_date = cpd.report_date "
			+ "WHERE cssd.report_date >= ? AND cssd.report_date < ?";

	/**
	 * The select query for retrieving all security SEC data with their position data
	 */
	private static final String SECURITY_SEC_DATA_QUERY = "SELECT * FROM " + TABLE_CUSTOMER_SECURITY_SEC_DATA
			+ " cssd LEFT JOIN " + "" + TABLE_CUSTOMER_POSITION_DATA + " cpd ON cssd.security_identifier = "
			+ "cpd.security_identifier AND cssd.report_date = cpd.report_date WHERE cssd.report_date >= ? AND "
			+ "cssd.report_date < ?";

	/**
	 * The select query for retrieving a security SEC data by security identifier
	 */
	private static final String FIND_SECURITY_SEC_DATA_QUERY = "SELECT * FROM " + TABLE_CALCULATED_SECURITY_SEC_DATA
			+ " WHERE security_identifier = ?";

	/**
	 * Constructor
	 */
	public CustomerDataPersistenceServiceImpl() {
		// default constructor
	}

	/**
	 * Getter jdbcTemplate
	 * 
	 * @return the jdbc template
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Setter jdbcTemplate
	 * 
	 * @param jdbcTemplate
	 *            the jdbc template to be set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Getter secConfiguration
	 * 
	 * @return the sec configuration
	 */
	public SECConfiguration getSecConfiguration() {
		return secConfiguration;
	}

	/**
	 * Setter secConfiguration
	 * 
	 * @param secConfiguration
	 *            the sec configuration to be set
	 */
	public void setSecConfiguration(SECConfiguration secConfiguration) {
		this.secConfiguration = secConfiguration;
	}

	/**
	 * Checks the configuration.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly.
	 */
	@PostConstruct
	protected void checkConfiguration() {
		if (secConfiguration == null) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
		if (jdbcTemplate == null) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
		insertSecuritySECData = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(TABLE_CALCULATED_SECURITY_SEC_DATA);
		insertPositionData = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
				.withTableName(TABLE_CALCULATED_POSITION_DATA);
	}

	/**
	 * Gets the customer SEC Security.
	 * 
	 * @param businessDate
	 *            the business date
	 * @return the list of customer security SEC data
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
	public List<SecuritySECData> getCustomerSECData(Date businessDate) throws SECYieldException {
		return extractSecuritySECData(businessDate, SECURITY_SEC_DATA_QUERY);
	}

	/**
	 * Persists the calculated SEC security data.
	 * 
	 * @param securitySECData
	 *            the SEC security data to be persisted
	 * @return flag indicating whether the data was persisted or not
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
	@Transactional
	public boolean persistSecuritySECData(SecuritySECData securitySECData) throws SECYieldException {
		if (securitySECData == null) {
			logger.error(
					String.format(logErrorFormat, persistSecuritySECDataMethodName, illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		try {
			jdbcTemplate.queryForObject(FIND_SECURITY_SEC_DATA_QUERY, new SecuritySECDataRowMapper(),
					securitySECData.getSecurityIdentifier());
			logger.info(String.format("Security SEC data with identifier {%s} was already calculated.",
					securitySECData.getSecurityIdentifier()));
			return true;
		} catch (EmptyResultDataAccessException e) {
			logger.info(String.format("Persisting Security SEC data with identifier {%s}.",
					securitySECData.getSecurityIdentifier()));
			SecurityReferenceData securityReferenceData = securitySECData.getSecurityReferenceData();
			SqlParameterSource securitySECDataParameters = new MapSqlParameterSource()
					.addValue(SecuritySECDataColumns.AS_400_RATE_TYPE.getName(),
							securityReferenceData.getAs400RateType())
					.addValue(SecuritySECDataColumns.DEF_INDICATOR.getName(), securityReferenceData.isDefIndicator())
					.addValue(SecuritySECDataColumns.DER_CLEAN_PRICE.getName(), securitySECData.getDerCleanPrice())
					.addValue(SecuritySECDataColumns.DER_HYBRID_INDICATOR.getName(),
							securityReferenceData.isDerHybridIndicator())
					.addValue(SecuritySECDataColumns.DER_INCOME_CALC_ENGINE.getName(),
							securitySECData.getDerIncomeCalcEngine())
					.addValue(SecuritySECDataColumns.DER_ONE_DAY_SECURITY_YIELD.getName(),
							securitySECData.getDerOneDaySecurityYield())
					.addValue(SecuritySECDataColumns.DER_REDEMPTION_DATE.getName(),
							securitySECData.getDerRedemptionDate())
					.addValue(SecuritySECDataColumns.DER_REDEMPTION_PRICE.getName(),
							securitySECData.getDerRedemptionPrice())
					.addValue(SecuritySECDataColumns.DER_SECURITY_TYPE.getName(), securitySECData.getDerSecurityType())
					.addValue(SecuritySECDataColumns.DER_STEP_INDICATOR.getName(),
							securityReferenceData.isDerStepIndicator())
					.addValue(SecuritySECDataColumns.DER_TIPS_INFLATIONARY_RATIO.getName(),
							securitySECData.getDerTIPSInflationaryRatio())
					.addValue(SecuritySECDataColumns.DER_YIELD_CALC_ENGINE.getName(),
							securitySECData.getDerYieldCalcEngine())
					.addValue(SecuritySECDataColumns.FINAL_MATURITY_DATE.getName(),
							securityReferenceData.getFinalMaturityDate())
					.addValue(SecuritySECDataColumns.FX_RATE.getName(), securitySECData.getFxRate())
					.addValue(SecuritySECDataColumns.INTEREST_RT.getName(), securityReferenceData.getInterestRt())
					.addValue(SecuritySECDataColumns.IO_HYBRID_FIELD.getName(),
							securityReferenceData.getIoHybridField())
					.addValue(SecuritySECDataColumns.IV_TYPE.getName(), securityReferenceData.getIvType())
					.addValue(SecuritySECDataColumns.PROSPECTIVE_METHOD.getName(),
							securityReferenceData.getProspectiveMethod())
					.addValue(SecuritySECDataColumns.REPORT_DATE.getName(), securitySECData.getReportDate())
					.addValue(SecuritySECDataColumns.SECURITY_IDENTIFIER.getName(),
							securitySECData.getSecurityIdentifier())
					.addValue(SecuritySECDataColumns.SECURITY_NAME.getName(), securityReferenceData.getSecurityName())
					.addValue(SecuritySECDataColumns.SECURITY_PRICE.getName(), securitySECData.getSecurityPrice())
					.addValue(SecuritySECDataColumns.SECURITY_REDEMPTION_PRICE.getName(),
							securityReferenceData.getSecurityRedemptionPrice());
			int executed = insertSecuritySECData.execute(securitySECDataParameters);
			final PositionData[] positionData = securitySECData.getPositionData();
			if (positionData != null) {
				for (int index = 0; index < positionData.length; index++) {
					PositionData posData = positionData[index];
					SqlParameterSource positionDataParameters = new MapSqlParameterSource()
							.addValue(PositionDataColumns.SECURITY_IDENTIFIER.getName(),
									posData.getSecurityIdentifier())
							.addValue(PositionDataColumns.ACCRUED_INCOME.getName(), posData.getAccruedIncome())
							.addValue(PositionDataColumns.EARNED_AMORTIZATION_BASE.getName(),
									posData.getEarnedAmortizationBase())
							.addValue(PositionDataColumns.EARNED_INFLATIONARY_COMPENSATION_BASE.getName(),
									posData.getEarnedInflationaryCompensationBase())
							.addValue(PositionDataColumns.MARKET_VALUE.getName(), posData.getMarketValue())
							.addValue(PositionDataColumns.PORTFOLIO_NUMBER.getName(), posData.getPortfolioNumber())
							.addValue(PositionDataColumns.POSITION_VAL_INFLATION_ADJ_SHARES.getName(),
									posData.getPositionValInflationAdjShares())
							.addValue(PositionDataColumns.REPORT_DATE.getName(), posData.getReportDate())
							.addValue(PositionDataColumns.SHARE_PAR_AMOUNT.getName(), posData.getShareParAmount())
							.addValue(PositionDataColumns.DER_ONE_DAY_SECURITY_INCOME.getName(),
									posData.getDerOneDaySecurityIncome());
					insertPositionData.execute(positionDataParameters);
				}
			}
			return executed > 0;
		}
	}

	/**
	 * Gets SEC security config for the calculations in engines.
	 * 
	 * @return the SEC security configuration
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 */
	@Override
	public SECConfiguration getConfiguration() throws SECYieldException {
		return secConfiguration;
	}

	/**
	 * Gets the calculated SEC security data.
	 * 
	 * @param businessDate
	 *            the business date
	 * @return the list of calculated SEC security data
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	@Override
	public List<SecuritySECData> getCalculatedSECData(Date businessDate) throws SECYieldException {
		return extractSecuritySECData(businessDate, CALCULATED_SECURITY_SEC_DATA_QUERY);
	}

	/**
	 * Extracts security SEC data from db.
	 * 
	 * @param businessDate
	 *            the business date
	 * @param query
	 *            the select query
	 * @return the list of SEC security data
	 * @throws SECYieldException
	 *             in case any error occurred during processing
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 */
	private List<SecuritySECData> extractSecuritySECData(Date businessDate, String query) throws SECYieldException {
		if (businessDate == null) {
			logger.error(
					String.format(logErrorFormat, extractSecuritySECDataMethodName, illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		final DateTime businessDateTime = new DateTime(businessDate).withTimeAtStartOfDay();
		// Get the data for a single business date (from the start to the end of the day)
		return jdbcTemplate.query(query,
				new Object[] { DB_DATE_FORMAT.format(businessDateTime.toDate()),
						DB_DATE_FORMAT.format(businessDateTime.plusDays(1).withTimeAtStartOfDay().toDate()) },
				new SecuritySECDataResultSetExtractor());
	}

	/**
	 * Inner row mapper to create the PositionData from the row.
	 */
	private class PositionDataRowMapper implements RowMapper<PositionData> {

		/**
		 * Empty constructor.
		 */
		public PositionDataRowMapper() {
			// default constructor
		}

		@Override
		public PositionData mapRow(ResultSet rs, int index) throws SQLException {
			PositionData positionData = new PositionData();
			positionData.setSecurityIdentifier(rs.getString(PositionDataColumns.SECURITY_IDENTIFIER.getName()));
			positionData.setAccruedIncome(rs.getBigDecimal(PositionDataColumns.ACCRUED_INCOME.getName()));
			positionData.setEarnedAmortizationBase(
					rs.getBigDecimal(PositionDataColumns.EARNED_AMORTIZATION_BASE.getName()));
			positionData.setEarnedInflationaryCompensationBase(
					rs.getBigDecimal(PositionDataColumns.EARNED_INFLATIONARY_COMPENSATION_BASE.getName()));
			positionData.setMarketValue(rs.getBigDecimal(PositionDataColumns.MARKET_VALUE.getName()));
			positionData.setPortfolioNumber(rs.getBigDecimal(PositionDataColumns.PORTFOLIO_NUMBER.getName()));
			positionData.setPositionValInflationAdjShares(
					rs.getBigDecimal(PositionDataColumns.POSITION_VAL_INFLATION_ADJ_SHARES.getName()));
			positionData.setReportDate(rs.getTimestamp(PositionDataColumns.REPORT_DATE.getName()));
			positionData.setShareParAmount(rs.getBigDecimal(PositionDataColumns.SHARE_PAR_AMOUNT.getName()));

			// Calculated fields
			if (isColumnInResultSet(rs, PositionDataColumns.DER_ONE_DAY_SECURITY_INCOME.getName())) {
				positionData.setDerOneDaySecurityIncome(
						rs.getBigDecimal(PositionDataColumns.DER_ONE_DAY_SECURITY_INCOME.getName()));
			}
			return positionData;
		}
	}

	/**
	 * Inner row mapper to create the SecuritySECData from the row.
	 */
	private class SecuritySECDataRowMapper implements RowMapper<SecuritySECData> {

		/**
		 * Empty constructor.
		 */
		public SecuritySECDataRowMapper() {
			// default constructor
		}

		@Override
		public SecuritySECData mapRow(ResultSet rs, int arg1) throws SQLException {

			SecuritySECData securitySECData = new SecuritySECData();
			securitySECData.setSecurityIdentifier(rs.getString(SecuritySECDataColumns.SECURITY_IDENTIFIER.getName()));
			securitySECData.setDerTIPSInflationaryRatio(
					rs.getBigDecimal(SecuritySECDataColumns.DER_TIPS_INFLATIONARY_RATIO.getName()));
			securitySECData.setFxRate(rs.getBigDecimal(SecuritySECDataColumns.FX_RATE.getName()));
			securitySECData.setReportDate(rs.getTimestamp(SecuritySECDataColumns.REPORT_DATE.getName()));
			securitySECData.setSecurityPrice(rs.getBigDecimal(SecuritySECDataColumns.SECURITY_PRICE.getName()));

			SecurityReferenceData securityReferenceData = new SecurityReferenceData();
			securityReferenceData
					.setSecurityIdentifier(rs.getString(SecuritySECDataColumns.SECURITY_IDENTIFIER.getName()));
			securityReferenceData.setAs400RateType(rs.getString(SecuritySECDataColumns.AS_400_RATE_TYPE.getName()));
			securityReferenceData.setDefIndicator(rs.getBoolean(SecuritySECDataColumns.DEF_INDICATOR.getName()));
			securityReferenceData
					.setDerHybridIndicator(rs.getBoolean(SecuritySECDataColumns.DER_HYBRID_INDICATOR.getName()));
			securityReferenceData
					.setDerStepIndicator(rs.getBoolean(SecuritySECDataColumns.DER_STEP_INDICATOR.getName()));
			securityReferenceData
					.setFinalMaturityDate(rs.getTimestamp(SecuritySECDataColumns.FINAL_MATURITY_DATE.getName()));
			securityReferenceData.setInterestRt(rs.getBigDecimal(SecuritySECDataColumns.INTEREST_RT.getName()));
			securityReferenceData.setIoHybridField(rs.getString(SecuritySECDataColumns.IO_HYBRID_FIELD.getName()));
			securityReferenceData.setIvType(rs.getString(SecuritySECDataColumns.IV_TYPE.getName()));
			securityReferenceData
					.setProspectiveMethod(rs.getString(SecuritySECDataColumns.PROSPECTIVE_METHOD.getName()));
			securityReferenceData.setSecurityName(rs.getString(SecuritySECDataColumns.SECURITY_NAME.getName()));
			securityReferenceData.setSecurityRedemptionPrice(
					rs.getBigDecimal(SecuritySECDataColumns.SECURITY_REDEMPTION_PRICE.getName()));

			// Calculated fields
			if (isColumnInResultSet(rs, SecuritySECDataColumns.DER_CLEAN_PRICE.getName())) {
				securitySECData.setDerCleanPrice(rs.getBigDecimal(SecuritySECDataColumns.DER_CLEAN_PRICE.getName()));
				securitySECData
						.setDerIncomeCalcEngine(rs.getString(SecuritySECDataColumns.DER_INCOME_CALC_ENGINE.getName()));
				securitySECData.setDerOneDaySecurityYield(
						rs.getBigDecimal(SecuritySECDataColumns.DER_ONE_DAY_SECURITY_YIELD.getName()));
				securitySECData
						.setDerRedemptionDate(rs.getTimestamp(SecuritySECDataColumns.DER_REDEMPTION_DATE.getName()));
				securitySECData
						.setDerRedemptionPrice(rs.getBigDecimal(SecuritySECDataColumns.DER_REDEMPTION_PRICE.getName()));
				securitySECData.setDerSecurityType(rs.getString(SecuritySECDataColumns.DER_SECURITY_TYPE.getName()));

				securitySECData
						.setDerYieldCalcEngine(rs.getString(SecuritySECDataColumns.DER_YIELD_CALC_ENGINE.getName()));
			}
			securitySECData.setSecurityReferenceData(securityReferenceData);
			return securitySECData;
		}

	}

	/**
	 * Determines if a column name is present in a result set
	 * 
	 * @param rs
	 *            the result set
	 * @param column
	 *            the column name
	 * @return true if the column name was found; false otherwise
	 */
	private boolean isColumnInResultSet(ResultSet rs, String column) {
		try {
			rs.findColumn(column);
			return true;
		} catch (SQLException ex) {
			return false;
		}
	}

	/**
	 * Inner result set extractor to create the SecuritySECData from the result set.
	 */
	private class SecuritySECDataResultSetExtractor implements ResultSetExtractor<List<SecuritySECData>> {

		/**
		 * Empty constructor.
		 */
		public SecuritySECDataResultSetExtractor() {
			// default constructor
		}

		@Override
		public List<SecuritySECData> extractData(ResultSet rs) throws SQLException, DataAccessException {
			SecuritySECDataRowMapper securitySECDataRowMapper = new SecuritySECDataRowMapper();
			PositionDataRowMapper positionDataRowMapper = new PositionDataRowMapper();
			Map<String, SecuritySECData> secData = new HashMap<String, SecuritySECData>();
			Map<String, List<PositionData>> positionData = new HashMap<String, List<PositionData>>();

			int index = 0;
			while (rs.next()) {
				String securityIdentifier = rs.getString(SecuritySECDataColumns.SECURITY_IDENTIFIER.getName());

				if (secData.containsKey(securityIdentifier)) {
					if (positionData.containsKey(securityIdentifier)) {
						positionData.get(securityIdentifier).add(positionDataRowMapper.mapRow(rs, index));
					} else {
						List<PositionData> positions = new ArrayList<PositionData>();
						positions.add(positionDataRowMapper.mapRow(rs, index));
						positionData.put(securityIdentifier, positions);
					}
				} else {
					secData.put(securityIdentifier, securitySECDataRowMapper.mapRow(rs, index));
					List<PositionData> positions = new ArrayList<PositionData>();
					positions.add(positionDataRowMapper.mapRow(rs, index));
					positionData.put(securityIdentifier, positions);
				}
				index++;
			}

			List<SecuritySECData> result = new ArrayList<SecuritySECData>();
			Iterator<Entry<String, SecuritySECData>> iterator = secData.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, SecuritySECData> entry = iterator.next();
				final List<PositionData> positionList = positionData.get(entry.getKey());
				if (positionList != null) {
					entry.getValue().setPositionData(
							(PositionData[]) positionList.toArray(new PositionData[positionList.size()]));
				}
				result.add(entry.getValue());
			}

			return result;
		}
	}

}
