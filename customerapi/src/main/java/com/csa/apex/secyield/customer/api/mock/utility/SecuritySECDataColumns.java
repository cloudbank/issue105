/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock.utility;

/**
 * Enum with column names from calculated_security_sec_data table
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public enum SecuritySECDataColumns {

	SECURITY_IDENTIFIER("security_identifier"),
	REPORT_DATE("report_date"),
	IV_TYPE("iv_type"),
	SECURITY_NAME("security_name"),
	FINAL_MATURITY_DATE("final_maturity_date"),
	SECURITY_REDEMPTION_PRICE("security_redemption_price"),
	INTEREST_RT("interest_rt"),
	DEF_INDICATOR("def_indicator"),
	DER_STEP_INDICATOR("der_step_indicator"),
	DER_HYBRID_INDICATOR("der_hybrid_indicator"),
	SECURITY_PRICE("security_price"),
	FX_RATE("fx_rate"),
	DER_TIPS_INFLATIONARY_RATIO("der_tips_inflationary_ratio"),
	IO_HYBRID_FIELD("io_hybrid_field"),
	AS_400_RATE_TYPE("as_400_rate_type"),
	PROSPECTIVE_METHOD("prospective_method"),
	DER_YIELD_CALC_ENGINE("der_yield_calc_engine"),
	DER_INCOME_CALC_ENGINE("der_income_calc_engine"),
	DER_ONE_DAY_SECURITY_YIELD("der_one_day_security_yield"),
	DER_REDEMPTION_DATE("der_redemption_date"),
	DER_REDEMPTION_PRICE("der_redemption_price"),
	DER_CLEAN_PRICE("der_clean_price"),
	DER_SECURITY_TYPE("der_security_type");

	/**
	 * The column name
	 */
	private final String name;

	/**
	 * Constructor with column name
	 * 
	 * @param name
	 *            the column name
	 */
	private SecuritySECDataColumns(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the name of the column
	 * 
	 * @return the column name
	 */
	public String getName() {
		return name;
	}
}
