package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.csa.apex.fundyield.seccommons.entities.ShareClassSnapshot;

/**
 * Inner row mapper to create the ShareClassSnapshot from the row.
 */
class ShareClassSnapshotRowMapper implements RowMapper<ShareClassSnapshot>{
    /**
     * Empty constructor.
     * 
     */
    public ShareClassSnapshotRowMapper() {
    }

    /**
     * Map the row to the ShareClassSnapshot.
     * 
     * @param rs the result set
     * @param rowNum the row num
     * @return the ShareClassSnapshot from the row.
     * @throws SQLException in case any error during processing.
     * 
     */
    public ShareClassSnapshot mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ShareClassSnapshot rt = new ShareClassSnapshot();
        rt.setReportDate(rs.getDate("REPORTING_DT"));
        rt.setShareClassSid(rs.getLong("SHARE_CLASS_SID"));
        rt.setShareClassSnapshotSid(rs.getLong("SHARE_CLASS_SNAPSHOT_SID"));  
    	return rt;
    }
}

