package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.seccommons.entities.Instrument;

/**
 * Inner row mapper to create the Instrument from the row.
 */
class InstrumentRowMapper implements RowMapper<Instrument>{
    /**
     * Empty constructor.
     * 
     */
    public InstrumentRowMapper() {
    }

    /**
     * Map the row to the Instrument.
     * 
     * @param rs the result set
     * @param rowNum the row num
     * @return the instrument data from the row.
     * @throws SQLException in case any error during processing.
     * 
     */
    public Instrument mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Instrument rt = new Instrument();
    	rt.setCouponRateTypeCode(rs.getString("COUPON_RATE_TYPE_CD"));
    	rt.setCusip(rs.getString("CUSIP"));
    	rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
    	rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
    	rt.setInstrumentId(rs.getLong("INSTRUMENT_ID"));
    	rt.setInstrumentShortName(rs.getString("INSTRUMENT_SHORT_NM"));
    	rt.setInstrumentSid(rs.getLong("INSTRUMENT_SID"));
    	return rt;
    }
       
}

