package com.csa.apex.fundyield.faya.api.service.impl;

import java.util.Map;

public interface StoredProcedure {

    public void getInstruments(Map<String,Object> params);

    public void getPortfolio(Map<String,Object> params);


    public void saveInstrument(Map<String,Object> params);

    public void savePortfolio(Map<String,Object> params);

    public void saveTradableEntity(Map<String,Object> params);

    public void saveTradableEntitySnapshot(Map<String,Object> params);

    public void savePortfolioHolding(Map<String,Object> params);

    public void savePortfolioSnapshot(Map<String,Object> params);

    public void saveShareClassSnapshot(Map<String,Object> params);


    public void avgMM1(Map<String,Object> params);

    public void sumD1(Map<String,Object> params);

    public void sumDR1(Map<String,Object> params);

    public void avgMM7(Map<String,Object> params);

    public void sumD7(Map<String,Object> params);


    public void queryDYPortfolio(Map<String,Object> params);

    public void queryMMPortfolio(Map<String,Object> params);

    public void queryDYInstrument(Map<String,Object> params);

    public void queryMMInstrument(Map<String,Object> params);

}
