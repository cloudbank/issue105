package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.fayacommons.entities.Portfolio;

/**
 * Inner row mapper to create the Portfolio from the row.
 */
class PortfolioRowMapper implements RowMapper<Portfolio>{
    /**
     * Empty constructor.
     * 
     */
    public PortfolioRowMapper() {
    }

    /**
     * Map the row to the Portfolio.
     * 
     * @param rs the result set
     * @param rowNum the row num
     * @return the portfolio from the row.
     * 
     * @throws SQLException in case any error during processing.
     * 
     * Implementation:
     * Create new Portfolio. Get the data from rs and populate portfolio with this data. Return portfolio.
     */
    public Portfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Portfolio rt = new Portfolio();
        rt.setCusip(rs.getString("CUSIP"));
        rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
        rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
        rt.setPortfolioId(rs.getLong("PORTFOLIO_ID"));
        rt.setPortfolioShortName(rs.getString("PORTFOLIO_SHORT_NM"));
        rt.setPortfolioSid(rs.getLong("PORTFOLIO_SID"));
        return rt;
    }
}

