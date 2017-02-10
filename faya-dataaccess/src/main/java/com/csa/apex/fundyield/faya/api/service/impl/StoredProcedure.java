package com.csa.apex.fundyield.faya.api.service.impl;

import java.util.Map;


/**
 * This services calls a data access layer built in Mybatis. This layer consists of just an interface that will
 * be used with a dynamic proxy build by Mybatis at runtime and injected into the services by Spring.
 *
 * Refer to 'resource/mapper/StoredProcedures.xml' for the relevant config
 */
public interface StoredProcedure {

    /**
     * Proxy to the GET_INSTRUMENT_DATA stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void getInstruments(Map<String,Object> params);

    /**
     * Proxy to the GET_PORTFOLIO_DATA stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void getPortfolio(Map<String,Object> params);


    /**
     * Proxy to the SAVE_INSTRUMENT stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void saveInstrument(Map<String,Object> params);

    /**
     * Proxy to the SAVE_PORTFOLIO stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void savePortfolio(Map<String,Object> params);

    /**
     * Proxy to the SAVE_TRADABLE_ENTITY stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void saveTradableEntity(Map<String,Object> params);

    /**
     * Proxy to the SAVE_TRADABLE_ENTITY_SNAPSHOT stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void saveTradableEntitySnapshot(Map<String,Object> params);

    /**
     * Proxy to the SAVE_PORTFOLIO_HOLDING stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void savePortfolioHolding(Map<String,Object> params);

    /**
     * Proxy to the SAVE_PORTFOLIO_SNAPSHOT stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void savePortfolioSnapshot(Map<String,Object> params);

    /**
     * Proxy to the SAVE_SHARE_CLASS_SNAPSHOT stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void saveShareClassSnapshot(Map<String,Object> params);


    /**
     * Proxy to the AVG_MM1 stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void avgMM1(Map<String,Object> params);

    /**
     * Proxy to the SUM_D1 stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void sumD1(Map<String,Object> params);

    /**
     * Proxy to the SUM_DR1 stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void sumDR1(Map<String,Object> params);

    /**
     * Proxy to the AVG_MM7 stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void avgMM7(Map<String,Object> params);

    /**
     * Proxy to the SUM_D7 stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void sumD7(Map<String,Object> params);


    /**
     * Proxy to the QUERY_DY_PORTFOLIO stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void queryDYPortfolio(Map<String,Object> params);

    /**
     * Proxy to the QUERY_MM_PORTFOLIO stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void queryMMPortfolio(Map<String,Object> params);

    /**
     * Proxy to the QUERY_DY_INSTRUMENT stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void queryDYInstrument(Map<String,Object> params);

    /**
     * Proxy to the QUERY_MM_INSTRUMENT stored procedure. All IN and OUT parameters are enclosed in a Map.
     *
     * @param params IN and OUT parameters of the stored procedure
     */
    public void queryMMInstrument(Map<String,Object> params);

}
