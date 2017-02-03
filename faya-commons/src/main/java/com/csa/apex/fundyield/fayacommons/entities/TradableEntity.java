/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import java.util.List;

/**
 * Tradable entity.
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class TradableEntity extends Versionable {

	/**
	 * Surrogate id to uniquely identify an instrument.
	 */
	@ColumnName("INSTRUMENT_SID")
	private long instrumentSid;

    /**
     * Surrogate id to uniquely identify a tradable entity.
     */
	@ColumnName("TRADABLE_ENTITY_SID")
    private long tradableEntitySid;

    /**
     * An value to uniquely identify a given Tradable Entity as used in the source system (i.e. MDM).
     */
	@ColumnName("TRADABLE_ENTITY_ID")
    private long tradableEntityId;

    /**
     * This value represents a normalized value of the GRD Market Exchange Code (C0#0042) from MDM. The GRD Market
     * Exchange Code value is translated into the appropriate MIC (Market Identifier Code) of the listingøs exchange and
     * populated in this field.
     */
	@ColumnName("MARKET_EXCHANGE_CD")
    private String marketExchangeCd;

    /**
     * An alternate id for a given Tradable Entity.
     */
	@ColumnName("TRADABLE_ENTITY_INTERNAL_ID")
    private String tradableEntityInternalId;

    /**
     * Tradable entity snapshots.
     */
    private List<TradableEntitySnapshot> tradableEntitySnapshots;

    /**
     * Constructor.
     */
    public TradableEntity() {
        // default empty constructor
    }

	/**
	 * Getter method for property <tt>instrumentSid</tt>.
	 * 
	 * @return property value of instrumentSid
	 */
	public long getInstrumentSid() {
		return instrumentSid;
	}

	/**
	 * Setter method for property <tt>instrumentSid</tt>.
	 * 
	 * @param instrumentSid
	 *            value to be assigned to property instrumentSid
	 */
	public void setInstrumentSid(long instrumentSid) {
		this.instrumentSid = instrumentSid;
	}

    /**
     * Getter method for property <tt>tradableEntitySid</tt>.
     * @return property value of tradableEntitySid
     */
    public long getTradableEntitySid() {
        return tradableEntitySid;
    }

    /**
     * Setter method for property <tt>tradableEntitySid</tt>.
     * @param tradableEntitySid value to be assigned to property tradableEntitySid
     */
    public void setTradableEntitySid(long tradableEntitySid) {
        this.tradableEntitySid = tradableEntitySid;
    }

    /**
     * Getter method for property <tt>tradableEntityId</tt>.
     * @return property value of tradableEntityId
     */
    public long getTradableEntityId() {
        return tradableEntityId;
    }

    /**
     * Setter method for property <tt>tradableEntityId</tt>.
     * @param tradableEntityId value to be assigned to property tradableEntityId
     */
    public void setTradableEntityId(long tradableEntityId) {
        this.tradableEntityId = tradableEntityId;
    }

    /**
     * Getter method for property <tt>marketExchangeCd</tt>.
     * @return property value of marketExchangeCd
     */
    public String getMarketExchangeCd() {
        return marketExchangeCd;
    }

    /**
     * Setter method for property <tt>marketExchangeCd</tt>.
     * @param marketExchangeCd value to be assigned to property marketExchangeCd
     */
    public void setMarketExchangeCd(String marketExchangeCd) {
        this.marketExchangeCd = marketExchangeCd;
    }

    /**
     * Getter method for property <tt>tradableEntityInternalId</tt>.
     * @return property value of tradableEntityInternalId
     */
    public String getTradableEntityInternalId() {
        return tradableEntityInternalId;
    }

    /**
     * Setter method for property <tt>tradableEntityInternalId</tt>.
     * @param tradableEntityInternalId value to be assigned to property tradableEntityInternalId
     */
    public void setTradableEntityInternalId(String tradableEntityInternalId) {
        this.tradableEntityInternalId = tradableEntityInternalId;
    }

    /**
     * Getter method for property <tt>tradableEntitySnapshots</tt>.
     * @return property value of tradableEntitySnapshots
     */
    public List<TradableEntitySnapshot> getTradableEntitySnapshots() {
        return tradableEntitySnapshots;
    }

    /**
     * Setter method for property <tt>tradableEntitySnapshots</tt>.
     * @param tradableEntitySnapshots value to be assigned to property tradableEntitySnapshots
     */
    public void setTradableEntitySnapshots(List<TradableEntitySnapshot> tradableEntitySnapshots) {
        this.tradableEntitySnapshots = tradableEntitySnapshots;
    }
}
