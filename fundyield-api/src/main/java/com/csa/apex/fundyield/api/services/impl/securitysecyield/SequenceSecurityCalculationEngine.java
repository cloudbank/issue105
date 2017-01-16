/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.util.List;

import javax.annotation.PostConstruct;

import com.csa.apex.fundyield.api.services.impl.CalculationEngine;
import com.csa.apex.fundyield.exceptions.CalculationException;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * The sequence security calculator. It is used to run an ordered list of
 * calculators in sequence.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
public class SequenceSecurityCalculationEngine implements CalculationEngine {

	/**
	 * List of calculation engines to be executed in sequence.
	 */
	private List<CalculationEngine> calculationEngines;

	/**
	 * Constructor.
	 */
	public SequenceSecurityCalculationEngine() {
		// Empty
	}

	/**
	 * Setter method for property <tt>calculationEngines</tt>.
	 * @param calculationEngines value to be assigned to property calculationEngines
	 */
	public void setCalculationEngines(List<CalculationEngine> calculationEngines) {
		this.calculationEngines = calculationEngines;
	}

	/**
	 * Checks the configuration.
	 *
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkListConfig(calculationEngines, "calculationEngines");
	}

	/**
	 * Calculates the security data by sequential execution of the calculation
	 * engines.
	 *
	 * @param fundAccountingYieldData
	 *            the input FundAccountingYieldData
	 * @param configuration
	 *            the SECConfiguration to be used for config values
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null)
	 * @throws CalculationException
	 *             in case any error during calculation
	 */
	@Override
    @LogMethod
	public void calculate(FundAccountingYieldData fundAccountingYieldData, SECConfiguration configuration)
			throws CalculationException {
		CommonUtility.checkNull(fundAccountingYieldData, "Parameter fundAccountingYieldData");
		CommonUtility.checkNull(configuration, "Parameter configuration");

		for (CalculationEngine calcEngine : calculationEngines) {
			calcEngine.calculate(fundAccountingYieldData, configuration);
		}
	}
}
