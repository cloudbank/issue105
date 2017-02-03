package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;

/**
 * Inner row mapper to create the TradableEntity from the row.
 */
class TradableEntityRowMapper implements RowMapper<TradableEntity>{
    /**
     * Empty constructor.
     * 
     */
    public TradableEntityRowMapper() {
    }

    /**
     * Map the row to the TradableEntity.
     * 
     * @param rs the result set
     * @param rowNum the row num
     * @return the TradableEntity from the row.
     * 
     * @throws SQLException in case any error during processing.
     * 
     * Implementation:
     * Create new TradableEntity. Get the data from rs and populate TradableEntity with this data. Return TradableEntity. 
     */
    public TradableEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    	TradableEntity rt = new TradableEntity();
    	rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
        rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
        rt.setTradableEntityId(rs.getLong("TRADABLE_ENTITY_ID"));
        rt.setTradableEntitySid(rs.getLong("TRADABLE_ENTITY_SID"));
        return rt;
    }
}

