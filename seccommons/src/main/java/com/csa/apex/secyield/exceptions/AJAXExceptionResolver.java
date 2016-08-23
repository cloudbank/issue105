/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * This is a subclass of SimpleMappingExceptionResolver which resolve exceptions to HTTP status code.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class AJAXExceptionResolver extends SimpleMappingExceptionResolver {

	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(AJAXExceptionResolver.class);

	/**
	 * Empty constructor.
	 */
	public AJAXExceptionResolver() {
		// default constructor
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			try {
				if (exception instanceof IllegalArgumentException) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
				} else if (exception instanceof EmptyResultDataAccessException) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, exception.getMessage());
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage());
				}
			} catch (IOException e) {
				logger.error("An I/O error occured while resolving exception.");
			}
			return null;
		} else {
			return super.resolveException(request, response, handler, exception);
		}
	}
}