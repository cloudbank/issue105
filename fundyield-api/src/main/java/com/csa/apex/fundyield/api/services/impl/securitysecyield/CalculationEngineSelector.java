/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineSubType;
import com.csa.apex.fundyield.seccommons.entities.CalculationEngineType;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Instrument;
import com.csa.apex.fundyield.seccommons.entities.InstrumentTypeCode;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * The default security calculator. Checks the security conditions and delegates
 * to the needed calculation engines. For phase 1 YTM and COUPON engines are
 * supported.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class CalculationEngineSelector implements CalculationEngine {

	/**
	 * Logger class instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(CalculationEngineSelector.class);

	/**
	 * The map from engine types to the calculation engines.
	 */
	@Resource
	@Qualifier("calculationEngines")
	private Map<CalculationEngineType, Map<CalculationEngineSubType, CalculationEngine>> calculationEngines;

	/**
	 * The ordered list of calculators. Initialized in post construct method.
	 */
	private SequenceSecurityCalculationEngine orderedCalculatorEngines;

	/**
	 * Constructor
	 */
	public CalculationEngineSelector() {
		// default constructor
	}

	/**
	 * Get engine code.
	 * 
	 * @return engine code
	 */
	@Override
	public String getEngineCode() {
		return "CalculationEngineSelector";
	}

	/**
	 * Get the ordered list of calculators.
	 *
	 * @return ordered list of calculators.
	 */
	public SequenceSecurityCalculationEngine getOrderedCalculatorEngines() {
		return orderedCalculatorEngines;
	}

	/**
	 * Checks the configuration.
	 *
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(calculationEngines, "calculationEngines");
		if (calculationEngines.get(CalculationEngineType.YTM) == null) {
			throw new ConfigurationException("Miss engines for YTM instruments");
		}
		if (calculationEngines.get(CalculationEngineType.COUPON) == null) {
			throw new ConfigurationException("Miss engines for COUPON instruments");
		}
		calculationEngines.entrySet().forEach(entry -> {
			if (entry.getValue().get(CalculationEngineSubType.YIELD) == null) {
				throw new ConfigurationException(
						"Miss Yield calculation engine for " + entry.getKey() + " instruments");
			}
			if (entry.getValue().get(CalculationEngineSubType.INCOME) == null) {
				throw new ConfigurationException(
						"Miss Income calculation engine for " + entry.getKey() + " instruments");
			}
		});

		// Initialize ordered list of calculators, by using
		// SequenceSecurityCalculationEngine

		SequenceSecurityCalculationEngine ytmSeqEngine = new SequenceSecurityCalculationEngine();
		ytmSeqEngine.setCalculationEngines(
				Arrays.asList(calculationEngines.get(CalculationEngineType.YTM).get(CalculationEngineSubType.YIELD),
						calculationEngines.get(CalculationEngineType.YTM).get(CalculationEngineSubType.INCOME)));

		SequenceSecurityCalculationEngine couponSeqEngine = new SequenceSecurityCalculationEngine();
		couponSeqEngine.setCalculationEngines(
				Arrays.asList(calculationEngines.get(CalculationEngineType.COUPON).get(CalculationEngineSubType.YIELD),
						calculationEngines.get(CalculationEngineType.COUPON).get(CalculationEngineSubType.INCOME)));

		orderedCalculatorEngines = new SequenceSecurityCalculationEngine();
		orderedCalculatorEngines.setCalculationEngines(Arrays.asList(ytmSeqEngine, couponSeqEngine));
	}

	/**
	 * Engine Calculate method implementation Checks whether IvType is VPS or
	 * VRDN or DVRN and accordingly calls respective engines.
	 *
	 * @param fundAccountingYieldData
	 *            the input FundAccountingYieldData
	 * @param configuration
	 *            the SECConfiguration, not used
	 * @throws IllegalArgumentException
	 *             if input FundAccountingYieldData is null
	 */
	@Override
	@LogMethod
	public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration) {
		CommonUtility.checkNull(fundAccountingYieldData, "Parameter fundAccountingYieldData");
		CommonUtility.checkNull(configuration, "Parameter configuration");

		for (Instrument instrument : fundAccountingYieldData.getInstruments()) {
			if ("Y".equalsIgnoreCase(instrument.getFdrStepBondInd())
					|| "HYBRID".equalsIgnoreCase(instrument.getHybridCalculationCd())) {
				LOGGER.warn(String.format(
						"Calculation is not supported for Instrument[%s]: stepBondInd: {%s}, hybrid: {%s}",
						instrument.getInstrumentSid(), instrument.getFdrStepBondInd(),
						instrument.getHybridCalculationCd()));
				continue;
			}

			InstrumentTypeCode type = instrument.getInstrumentTypeCode();
			TradableEntitySnapshot tes = CommonUtility.getTradableEntitySnapshot(instrument);
			if (tes != null) {
				if (type == InstrumentTypeCode.VPS) {
					// Select YTM engines
					tes.setDerYieldCalcEngineCode(calculationEngines.get(CalculationEngineType.YTM)
							.get(CalculationEngineSubType.YIELD).getEngineCode());
					tes.setDerIncomeCalcEngineCode(calculationEngines.get(CalculationEngineType.YTM)
							.get(CalculationEngineSubType.INCOME).getEngineCode());
				} else if (type == InstrumentTypeCode.VRDN || type == InstrumentTypeCode.DVRN) {
					// Select Coupon engines
					tes.setDerYieldCalcEngineCode(calculationEngines.get(CalculationEngineType.COUPON)
							.get(CalculationEngineSubType.YIELD).getEngineCode());
					tes.setDerIncomeCalcEngineCode(calculationEngines.get(CalculationEngineType.COUPON)
							.get(CalculationEngineSubType.INCOME).getEngineCode());
				} else {
					LOGGER.warn(String.format("Operation is not supported for Instrument[%s] with type {%s}",
							instrument.getInstrumentSid(), type));
				}
			}
		}
	}
}
