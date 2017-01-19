package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.csa.apex.fundyield.seccommons.entities.Portfolio;
import com.csa.apex.fundyield.seccommons.entities.PortfolioSnapshot;
import com.csa.apex.fundyield.seccommons.entities.ShareClass;

/**
 * Inner result set extractor to create the Portfolio from the result set.
 */
class PortfolioResultSetExtractor  implements ResultSetExtractor<List<Portfolio>> {
    /**
     * Empty constructor.
     * 
     */
    public PortfolioResultSetExtractor() {
    }

    /**
     * Extracts the Portfolios from result set
     * 
     * @param rs the result set
     * @return the portfolios from the result set.
     * @throws SQLException in case any error during processing.
     * 
     */
    public List<Portfolio> extractData(ResultSet rs) throws SQLException {
    	PortfolioRowMapper portfolioRowMapper = new PortfolioRowMapper();
        PortfolioSnapshotRowMapper portfolioSnapshotRowMapper = new PortfolioSnapshotRowMapper();
        ShareClassRowMapper shareClassRowMapper = new ShareClassRowMapper();
        Map<Long, Portfolio> portfolios = new HashMap<Long, Portfolio>();
        int index = 0;
        int indexSnap = 0;
        int indexShare = 0;
        while(rs.next()) {
           long id = rs.getLong("PORTFOLIO_SID");
           Portfolio portfolio = portfolios.get(id);
           if (portfolio == null) {
              portfolio = portfolioRowMapper.mapRow(rs, index++);
              portfolios.put(id, portfolio);
           }
           PortfolioSnapshot portfolioSnapshot = portfolioSnapshotRowMapper.mapRow(rs, indexSnap++);
           if (portfolio.getPortfolioSnapshots() == null) {
        	   portfolio.setPortfolioSnapshots(new ArrayList<PortfolioSnapshot>());
           }
           portfolio.getPortfolioSnapshots().add(portfolioSnapshot);
           ShareClass shareClass = shareClassRowMapper.mapRow(rs, indexShare++);
           if (portfolio.getShareClasses() == null) {
        	   portfolio.setShareClasses(new ArrayList<ShareClass>());
           }
           portfolio.getShareClasses().add(shareClass);
        }
        return new ArrayList<Portfolio>(portfolios.values()); 
    }
}

