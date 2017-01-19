/*
 * Copyright (C) 2017 TopCoder Inc., All Rights Reserved.
 */
package com.csa.apex.fundyield.utility;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Target;

/**
 * The annotation marking the need of logging.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Target(value = {METHOD})
public @interface LogMethod {
}
