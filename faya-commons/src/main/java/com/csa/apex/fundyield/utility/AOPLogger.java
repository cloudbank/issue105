/*
 * Copyright (C) 2017 TopCoder Inc., All Rights Reserved.
 */
package com.csa.apex.fundyield.utility;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * The AOP component to perform logging.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Aspect
@Component
public class AOPLogger {

	/**
	 * The logger package name.
	 */
	private static final Logger LOGGER = Logger.getLogger("FundyieldLogger");

	/**
	 * <p>
	 * Represents the entrance message.
	 * </p>
	 */
	private static final String MESSAGE_ENTRANCE = "Entering method [%1$s].";

	/**
	 * <p>
	 * Represents the exit message.
	 * </p>
	 */
	private static final String MESSAGE_EXIT = "Exiting method [%1$s].";

	/**
	 * <p>
	 * Represents the error message.
	 * </p>
	 */
	private static final String MESSAGE_ERROR = "Error in method [%1$s] : %2$s";

	/**
	 * <p>
	 * Converts the parameters to string.
	 * </p>
	 *
	 * @param paramNames
	 *            the names of parameters.
	 * @param params
	 *            the values of parameters.
	 * @return the string
	 */
	private static String toString(String[] paramNames, Object[] params) {
		StringBuilder sb = new StringBuilder(" Input parameters: {");
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(paramNames[i]).append(":").append(CommonUtility.toString(params[i]));
			}
		}
		sb.append("}.");
		return sb.toString();
	}

	/**
	 * Log method entrance.
	 *
	 * @param joinPoint
	 *            the joint point
	 */
	@Before("execution(* *(..)) && @annotation(com.csa.apex.fundyield.utility.LogMethod)")
	public void logMethodAccessBefore(JoinPoint joinPoint) {
		if (LOGGER.isDebugEnabled()) {
			String[] parameterNames = ((MethodSignature) (joinPoint.getSignature())).getParameterNames();
			if (parameterNames == null) {
				parameterNames = new String[joinPoint.getArgs().length];
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					parameterNames[i] = "arg" + i;
				}
			}
			String msg = String.format(MESSAGE_ENTRANCE, joinPoint.getSignature().toString())
					+ toString(parameterNames, joinPoint.getArgs());
			LOGGER.debug(msg);
		}
	}

	/**
	 * Log method exit.
	 *
	 * @param joinPoint
	 *            the join point
	 * @param result
	 *            the result
	 */
	@AfterReturning(pointcut = "execution(* *(..)) && @annotation(com.csa.apex.fundyield.utility.LogMethod)", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		if (LOGGER.isDebugEnabled()) {
			String msg = String.format(MESSAGE_EXIT, joinPoint.getSignature().toString());
			if (result != null) {
				msg += " Output parameter : " + CommonUtility.toString(result);
			}
			LOGGER.debug(msg);
		}
	}

	/**
	 * Log exception.
	 *
	 * @param joinPoint
	 *            the joint point
	 * @param ex
	 *            the exception
	 */
	@AfterThrowing(pointcut = "execution(* *(..)) && @annotation(com.csa.apex.fundyield.utility.LogMethod)", throwing = "ex")
	public void doRecoveryActions(JoinPoint joinPoint, Exception ex) {
		LOGGER.error(String.format(MESSAGE_ERROR, joinPoint.getSignature().toString(), ex.getMessage()), ex);
	}
}
