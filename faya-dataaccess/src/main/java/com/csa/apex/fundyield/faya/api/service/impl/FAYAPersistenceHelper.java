package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;

import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;

import oracle.jdbc.OracleTypes;

class FAYAPersistenceHelper {

    /**
     * Constructor.
     */
    private FAYAPersistenceHelper() {
    }

    /**
     * Call stored procedure.
     * @param spName the stored procedure name;
     * @param jdbcTemplate the jdbcTemplate
     * @param inParamMap the inParamMap
     * @return the result
     */
    public static Map<String, Object> callStoredProcedure(String spName, JdbcTemplate jdbcTemplate,
            Map<String, Object> inParamMap) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(spName);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> result = jdbcCall.execute(in);
        return result;
    }

    /**
     * Get portfolio.
     * @param jdbcTemplate
     * @param spName
     * @param businessDate
     * @return portfolios
     */
    @SuppressWarnings("unchecked")
    public static List<Portfolio> getPortfolios(JdbcTemplate jdbcTemplate, String spName, Date businessDate) {
        FAYAStoredProcedure portfolioStoredProcedure = new FAYAStoredProcedure(jdbcTemplate, spName);
        portfolioStoredProcedure.setParameters(new SqlParameter[] {new SqlParameter("business_date", Types.DATE),
                new SqlOutParameter("rs", OracleTypes.CURSOR, new PortfolioResultSetExtractor())});
        portfolioStoredProcedure.compile();
        List<Portfolio> portfolios = (List<Portfolio>) portfolioStoredProcedure.execute(businessDate).get("rs");
        return portfolios;
    }

    /**
     * Get Instruments
     * @param jdbcTemplate
     * @param spName
     * @param businessDate
     * @return Instruments
     */
    @SuppressWarnings("unchecked")
    public static List<Instrument> getInstruments(JdbcTemplate jdbcTemplate, String spName, Date businessDate) {
        FAYAStoredProcedure instrumentStoredProcedure = new FAYAStoredProcedure(jdbcTemplate, spName);
        instrumentStoredProcedure.setParameters(new SqlParameter[] {new SqlParameter("business_date", Types.DATE),
                new SqlOutParameter("rs", OracleTypes.CURSOR, new InstrumentResultSetExtractor())});
        instrumentStoredProcedure.compile();
        List<Instrument> instruments = (List<Instrument>) instrumentStoredProcedure.execute(businessDate).get("rs");
        return instruments;
    }

    /**
     * Helper class.
     */
    private static class FAYAStoredProcedure extends StoredProcedure {

        public FAYAStoredProcedure(JdbcTemplate jdbcTemplate, String name) {
            super(jdbcTemplate, name);
            setFunction(false);
        }
    }
}
