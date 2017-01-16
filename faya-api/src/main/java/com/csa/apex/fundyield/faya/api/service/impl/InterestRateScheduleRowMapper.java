package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.seccommons.entities.InterestRateSchedule;

/**
 * Inner row mapper to create the InterestRateSchedule from the row.
 */
class InterestRateScheduleRowMapper implements RowMapper<InterestRateSchedule>{
    /**
     * Empty constructor.
     * 
     */
    public InterestRateScheduleRowMapper() {
    }

    /**
     * Map the row to the InterestRateSchedule.
     * 
     * @param rs the result set
     * @param rowNum the row num
     * @return the InterestRateSchedule from the row.
     * @throws SQLException in case any error during processing.
     * 
     * Implementation:
     * Create new  InterestRateSchedule.
     * Get the data from rs and populate  
     * InterestRateSchedule with this data. Return InterestRateSchedule. 
     */
    public InterestRateSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
    	InterestRateSchedule rt = new InterestRateSchedule();
    	rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
    	rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
    	rt.setHashKeyTxt(rs.getString("HASH_KEY_TXT"));
    	rt.setInterestRateScheduleSid(rs.getLong("INTEREST_RATE_SCHEDULE_SID"));
    	rt.setInterestRateTypeCd(rs.getString("INTEREST_RATE_TYPE_CD"));
    	rt.setInterestRt(rs.getBigDecimal("INTEREST_RT"));
        return rt;
    }
}

