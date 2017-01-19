package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.seccommons.entities.PortfolioSnapshot;

/**
 * Inner row mapper to create the PortfolioSnapshot from the row.
 */
class PortfolioSnapshotRowMapper implements RowMapper<PortfolioSnapshot>{
    /**
     * Empty constructor.
     * 
     */
    public PortfolioSnapshotRowMapper() {
    }

    /**
     * Map the row to the PortfolioSnapshot.
     * 
     * @param rs the result set
     * @param rowNum the row num
     * @return the PortfolioSnapshot from the row.
     * @throws SQLException in case any error during processing.
     * 
     * Implementation:
     * Create new PortfolioSnapshot. Get the data from rs and populate PortfolioSnapshot with this data. Return PortfolioSnapshot. 
     */
    public PortfolioSnapshot mapRow(ResultSet rs, int rowNum) throws SQLException {
    	PortfolioSnapshot rt = new PortfolioSnapshot();
    	rt.setPortfolioSid(rs.getLong("PORTFOLIO_SID"));
        rt.setPortfolioSnapshotSid(rs.getLong("PORTFOLIO_SNAPSHOT_SID"));
        rt.setReportDate(rs.getDate("REPORTING_DT"));
        return rt;
    }
}

