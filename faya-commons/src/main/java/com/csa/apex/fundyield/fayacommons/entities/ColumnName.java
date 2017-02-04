/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The column name annotation.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnName {

	/**
	 * Define the column name.
	 * @return column name
	 */
	String value();
}
