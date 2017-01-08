/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.seccommons.entities;

import org.junit.Test;

import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the SECConfiguration.
 *
 * @see SECConfigurationTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class SECConfigurationTest {

    /**
     * SECConfiguration Test toString.
     *
     * @throws ParseException
     */
    @Test
    public void toStringTest() throws ParseException {
        SECConfiguration secConfiguration = new SECConfiguration();
        secConfiguration.setOperationScale(1111);
        secConfiguration.setRoundingMode(1111);

        String expected = "{\"operationScale\":1111,\"roundingMode\":1111}";

        assertEquals(expected, secConfiguration.toString());
    }

}
