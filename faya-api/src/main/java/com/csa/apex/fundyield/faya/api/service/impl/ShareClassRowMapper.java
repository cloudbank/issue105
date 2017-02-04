package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.fayacommons.entities.ShareClass;

/**
 * Inner row mapper to create the ShareClass from the row.
 */
class ShareClassRowMapper implements RowMapper<ShareClass>{
    /**
     * Empty constructor.
     * 
     */
    public ShareClassRowMapper() {
    }

    /**
     * Map the row to the ShareClass.
     * 
     * @param rs the result set
     * @param rowNum  the row num
     * @return the ShareClass from the row.
     * 
     * @throws SQLException in case any error during processing.
     * 
     * Implementation:
     * Create new ShareClass. Get the data from rs and populate ShareClass with this data. Return ShareClass. 
     */
    public ShareClass mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ShareClass rt = new ShareClass();
        rt.setEffectiveDate(rs.getDate("EFFECTIVE_DT"));
        rt.setExpirationDate(rs.getDate("EXPIRATION_DT"));
        rt.setShareClassSid(rs.getLong("SHARE_CLASS_SID")); 
    	return rt;
    }
}

