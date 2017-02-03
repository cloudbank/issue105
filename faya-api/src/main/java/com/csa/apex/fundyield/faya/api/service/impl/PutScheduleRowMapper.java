package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.fayacommons.entities.PutSchedule;

/**
 * Inner row mapper to create the PutSchedule from the row.
 */
class PutScheduleRowMapper implements RowMapper<PutSchedule>{
    /**
     * Empty constructor.
     * 
     */
    public PutScheduleRowMapper() {
    }

    /**
     * Map the row to the PutSchedule.
     * 
     * @param rs the result set
     * @param rowNum the row num
     * @return  the PutSchedule from the row.
     * 
     * @throws SQLException in case any error during processing.
     * 
     * Implementation:
     * Create new PutSchedule. Get the data from rs and populate CallSchedule with this data. Return CallSchedule. 
     */
    public PutSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
    	PutSchedule rt = new PutSchedule();
        rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
        rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
        rt.setHashKeyTxt(rs.getString("HASH_KEY_TXT"));
        rt.setPutDate(rs.getDate("PUT_DT"));
        rt.setPutScheduleSid(rs.getLong("PUT_SCHEDULE_SID"));
    	return rt;
    }
}

