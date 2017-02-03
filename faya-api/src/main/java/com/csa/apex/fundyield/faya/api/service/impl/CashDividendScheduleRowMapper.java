package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.fayacommons.entities.CashDividendSchedule;

/**
 * Inner row mapper to create the CashDividendSchedule from the row.
 */
class CashDividendScheduleRowMapper implements RowMapper<CashDividendSchedule>{
    /**
     * Empty constructor.
     * 
     */
    public CashDividendScheduleRowMapper() {
    }

    /**
     * Map the row to the CashDividendSchedule.
     * 
     * @param rs  the result set
     * @param rowNum  the row num
     * @return the CashDividendSchedule from the row.
     * @throws SQLException in case any error during processing.
     * 
     */
    public CashDividendSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CashDividendSchedule rt = new CashDividendSchedule();
    	rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
    	rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
        return rt;
    }
}

