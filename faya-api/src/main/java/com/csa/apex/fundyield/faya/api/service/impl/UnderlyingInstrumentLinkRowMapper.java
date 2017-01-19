package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.seccommons.entities.UnderlyingInstrumentLink;

/**
 * Inner row mapper to create the UnderlyingInstrumentLink from the row.
 */
class UnderlyingInstrumentLinkRowMapper implements RowMapper<UnderlyingInstrumentLink>{
    /**
     * Empty constructor.
     * 
     */
    public UnderlyingInstrumentLinkRowMapper() {
    }

    /**
     * Map the row to the UnderlyingInstrumentLink.
     * 
     * @param rs the result set
     * @param rowNum  the row num
     * @return  the UnderlyingInstrumentLink from the row.
     * @throws SQLException in case any error during processing.
     * 
     */
    public UnderlyingInstrumentLink mapRow(ResultSet rs, int rowNum) throws SQLException {
    	UnderlyingInstrumentLink rt = new UnderlyingInstrumentLink();
        rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
        rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
        rt.setHashKeyTxt(rs.getString("HASH_KEY_TXT"));
        rt.setUnderlyingInstrumentLinkSid(rs.getLong("UNDERLYING_INSTRUMENT_LINK_SID"));
    	return rt;
    }
}

