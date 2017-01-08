/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.utility;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;

import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Instrument;
import com.csa.apex.fundyield.seccommons.entities.Portfolio;
import com.csa.apex.fundyield.seccommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.seccommons.entities.TradableEntity;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;

/**
 * Utility class for the CalculationEngine.
 *
 * @see TestUtility
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
@Component
public class TestUtility {

	/**
	 * Inject field value.
	 *
	 * @param obj The object
	 * @param fieldName The field name
	 * @param fieldValue The field value
	 * @throws Exception
	 */
	public void injectField(Object obj, String fieldName, Object fieldValue) throws Exception {
		Field field = FieldUtils.getDeclaredField(obj.getClass(), fieldName, true);
		field.set(obj, fieldValue);
	}

	/**
	 * Construct FundAccountingYieldData for test.
	 *
	 * @return FundAccountingYieldData
	 */
	public FundAccountingYieldData constructFAYAData() {
		FundAccountingYieldData data = new FundAccountingYieldData();
		Instrument instrument= new Instrument();
		TradableEntity te = new TradableEntity();
		TradableEntitySnapshot tes = new TradableEntitySnapshot();
		te.setTradableEntitySnapshots(Arrays.asList(tes));
		instrument.setTradableEntities(Arrays.asList(te));

		Portfolio portfolio = new Portfolio();
		PortfolioHoldingSnapshot holding = new PortfolioHoldingSnapshot();
		holding.setTradableEntity(te);

		portfolio.setPortfolioHoldings(Arrays.asList(holding));

		data.setInstruments(Arrays.asList(instrument));
		data.setPortfolios(Arrays.asList(portfolio));
		return data;
	}

	/**
	 * Returns value with scale 7 with ROUND_HALF_DOWN.
	 * 
	 * @param val
	 *            value passed
	 * @return value with scale 7
	 */
	public BigDecimal getBigDecimalWithScale7(BigDecimal val) {
		val = val.setScale(7, BigDecimal.ROUND_HALF_DOWN);
		return val;
	}
}
