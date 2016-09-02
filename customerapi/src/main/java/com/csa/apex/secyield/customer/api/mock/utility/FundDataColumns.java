/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.customer.api.mock.utility;

/**
 * Enum with column names from _fund_data table
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public enum FundDataColumns {

	SECURITY_IDENTIFIER("security_identifier"),
	PORTFOLIO_NUMBER("portfolio_number"),
	PORTFOLIO_NAME("portfolio_name"),
	REPORT_DATE("report_date");

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
	private FundDataColumns(String name) {
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
