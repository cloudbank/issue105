/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.exceptions.resolver;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.csa.apex.fundyield.exceptions.DataNotFoundException;

/**
 * This is a subclass of SimpleMappingExceptionResolver which resolve exceptions to HTTP status code.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class AJAXExceptionResolver extends SimpleMappingExceptionResolver {

	/**
	 * Logger class instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(AJAXExceptionResolver.class);

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
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, getMessage("INVALID_REQUEST"));
				} else if (exception instanceof DataNotFoundException) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, getMessage("EMPTY_RESULT_ON_REQUEST"));
				} else {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, getMessage("UNKNOWN_ERROR_ON_REQUEST"));
				}
			} catch (IOException e) {
				LOGGER.error("An I/O error occured while resolving exception.", e);
			}
			return null;
		} else {
			return super.resolveException(request, response, handler, exception);
		}
	}

	private String getMessage(String key) {
		ResourceBundle messages = ResourceBundle.getBundle("errorMessage");
		return messages.getString(key);
	}
}
