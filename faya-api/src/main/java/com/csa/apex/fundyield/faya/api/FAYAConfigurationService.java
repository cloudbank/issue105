/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;

/**
 * Spring REST Interface for configuration.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Controller
@FunctionalInterface
public interface FAYAConfigurationService {

    /**
     * Gets SEC security config for the calculations in engines.
     * 
     * @return the SEC security configuration
     * @throws FundAccountingYieldException
     *             in case any error occurred during processing
     */
    @RequestMapping(value = "securitySECDataConfiguration", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SECConfiguration getConfiguration() throws FundAccountingYieldException;
}
