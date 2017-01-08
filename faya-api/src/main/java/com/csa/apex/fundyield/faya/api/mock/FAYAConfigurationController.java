/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.api.mock;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.faya.api.FAYAConfigurationService;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.LogMethod;


/**
 * Spring REST Controller for FAYA configuration. This class is effectively thread safe.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Controller
public class FAYAConfigurationController implements FAYAConfigurationService {

    /**
     * The injected SECConfiguration.
     */
    @Autowired
    private SECConfiguration secConfiguration;

    /**
     * Constructor.
     */
    public FAYAConfigurationController() {
        // default constructor
    }

    /**
     * Checks the configuration.
     * 
     * @throws ConfigurationException
     *             if any required field is not initialized properly.
     */
    @PostConstruct
    protected void checkConfiguration() {
        CommonUtility.checkNullConfig(secConfiguration, "secConfiguration");
    }

    /**
     * Gets SEC security config for the calculations in engines.
     * 
     * @return the SEC security configuration
     */
    @Override
    @LogMethod
    public SECConfiguration getConfiguration() {
        return secConfiguration;
    }
}
