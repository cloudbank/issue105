package com.csa.apex.fundyield.faya.api.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.InterestRateSchedule;
import com.csa.apex.fundyield.fayacommons.entities.PutSchedule;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;

/**
 * Inner result set extractor to create the Instrument from the result set.
 */
class InstrumentResultSetExtractor implements ResultSetExtractor<List<Instrument>> {
    /**
     * Empty constructor.
     * 
     */
    public InstrumentResultSetExtractor() {
    }

    /**
     * Extracts the Instruments from result set
     * 
     * @param rs the result set
     * @return the instruments from the result set.
     * @throws SQLException in case any error during processing.
     * 
     */
    public List<Instrument> extractData(ResultSet rs) throws SQLException {
    	InstrumentRowMapper instrumentRowMapper = new InstrumentRowMapper();
        PutScheduleRowMapper putScheduleRowMapper = new PutScheduleRowMapper();
        InterestRateScheduleRowMapper interestRateScheduleRowMapper = new InterestRateScheduleRowMapper();
        TradableEntityRowMapper tradableEntityRowMapper = new TradableEntityRowMapper();
        Map<Long,Instrument> instruments = new HashMap<Long,Instrument>();
        int index = 0;
        int indexPut = 0;
        int indexSchedule = 0;
        int indexTrade = 0;
        while(rs.next()) {
            long id = Long.parseLong(rs.getString("INSTRUMENT_SID"));
            Instrument instrument = instruments.get(id);
            if (instrument == null) {
               instrument = instrumentRowMapper.mapRow(rs, index++);
               instruments.put(id, instrument);
            }
            
            PutSchedule putSchedule = putScheduleRowMapper.mapRow(rs, indexPut++);
            if (instrument.getPutSchedules() == null) {
            	instrument.setPutSchedules(new ArrayList<PutSchedule>());
            }
            instrument.getPutSchedules().add(putSchedule);
            
            InterestRateSchedule interestRateSchedule = interestRateScheduleRowMapper.mapRow(rs, indexSchedule++);
            if (instrument.getInterestRateSchedules() == null) {
            	instrument.setInterestRateSchedules(new ArrayList<InterestRateSchedule>());
            }
            instrument.getInterestRateSchedules().add(interestRateSchedule);
            
            TradableEntity tradableEntity = tradableEntityRowMapper.mapRow(rs, indexTrade++);
            if (instrument.getTradableEntities() == null) {
            	instrument.setTradableEntities(new ArrayList<TradableEntity>());
            }
            instrument.getTradableEntities().add(tradableEntity);
        }
        return new ArrayList<Instrument>(instruments.values());
    }
}