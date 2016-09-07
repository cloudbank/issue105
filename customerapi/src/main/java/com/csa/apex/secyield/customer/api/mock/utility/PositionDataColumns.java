/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock.utility;

/**
 * Enum with column names from calculated_position_data table
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public enum PositionDataColumns {

	SECURITY_IDENTIFIER("security_identifier"),
	PORTFOLIO_NUMBER("portfolio_number"),
	REPORT_DATE("report_date"),
	EARNED_INFLATIONARY_COMPENSATION_BASE("earned_inflationary_compensation_base"),
	ACCRUED_INCOME("accrued_income"),
	MARKET_VALUE("market_value"),
	SHARE_PER_AMOUNT("share_per_amount"),
	EARNED_AMORTIZATION_BASE("earned_amortization_base"),
	POSITION_VAL_INFLATION_ADJ_SHARES("position_val_inflation_adj_shares"),
	DER_ONE_DAY_SECURITY_INCOME("der_one_day_security_income");

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
	private PositionDataColumns(String name) {
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
