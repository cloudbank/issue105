/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.fayacommons.entities.CalculationEngineSubType;
import com.csa.apex.fundyield.fayacommons.entities.CalculationEngineType;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * The base calculation engine.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public abstract class BaseCalculationEngine implements CalculationEngine {

	/**
	 * Default scale for the BigDecimal operations.
	 */
	private int operationScale = 7;

	/**
	 * Default Rounding mode.
	 */
	private int roundingMode = 4;

	/**
	 * The engine type.
	 */
	private final CalculationEngineType engineType;

	/**
	 * The engine sub type.
	 */
	private final CalculationEngineSubType engineSubType;

	/**
	 * Constructor.
	 */
	protected BaseCalculationEngine(CalculationEngineType engineType, CalculationEngineSubType engineSubType) {
		this.engineType = engineType;
		this.engineSubType = engineSubType;
	}

	/**
	 * Getter method for property <tt>engineType</tt>.
	 * 
	 * @return property value of engineType
	 */
	public CalculationEngineType getEngineType() {
		return engineType;
	}

	/**
	 * Getter method for property <tt>engineSubType</tt>.
	 * 
	 * @return property value of engineSubType
	 */
	public CalculationEngineSubType getEngineSubType() {
		return engineSubType;
	}

	/**
	 * Calculates the data.
	 *
	 * @param fundAccountingYieldData
	 *            the input FundAccountingYieldData to calculate
	 * @param configuration
	 *            the SECConfiguration to be used for config values
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws CalculationException
	 *             in case any error during calculation.
	 */
	@Override
	@LogMethod
	public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration)
			throws CalculationException {
		CommonUtility.checkNull(fundAccountingYieldData, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				"Parameter fundAccountingYieldData");
		CommonUtility.checkNull(configuration, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE,
				"Parameter configuration");

		int passedOperationScale = configuration.getOperationScale();
		int passedRoundingMode = configuration.getRoundingMode();
		configuration.setOperationScale(passedOperationScale != 0 ? passedOperationScale : operationScale);
		configuration.setRoundingMode(passedRoundingMode != -1 ? passedRoundingMode : roundingMode);

		if (fundAccountingYieldData.getInstruments() != null) {
			for (Instrument instrument : fundAccountingYieldData.getInstruments()) {
				try {
					TradableEntitySnapshot tes = CommonUtility.getTradableEntitySnapshot(instrument);
					if (tes != null && canCalculate(tes)) {
						doCalculate(fundAccountingYieldData, instrument, tes, configuration);
					}
				} catch (Exception e) {
					throw new CalculationException(
							"Failed to calulate " + getEngineCode() + " for instrument: " + instrument, e);
				}
			}
		}
	}

	/**
	 * Checks whether can calculate a TradableEntitySnapshot.
	 *
	 * @param tes
	 *            The TradableEntitySnapshot to check
	 * @return true if can calculate, false otherwise
	 */
	private boolean canCalculate(TradableEntitySnapshot tes) {
		return (engineSubType == CalculationEngineSubType.YIELD
				&& getEngineCode().equals(tes.getDerYieldCalcEngineCode()))
				|| (engineSubType == CalculationEngineSubType.INCOME
						&& getEngineCode().equals(tes.getDerIncomeCalcEngineCode()));
	}

	/**
	 * Do calculation.
	 *
	 * @param fundAccountingYieldData
	 *            The FundAccountingYieldData to calculate
	 * @param instrument
	 *            The Instrument to calculate
	 * @param tes
	 *            The TradableEntitySnapshot to calculate
	 * @param configuration
	 *            The SECConfiguration to be used for config values
	 */
	protected abstract void doCalculate(FundAccountingYieldData fundAccountingYieldData, Instrument instrument, TradableEntitySnapshot tes,
			SECConfiguration configuration);
}
