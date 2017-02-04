/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.fayacommons.entities;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for the SECConfiguration.
 * @see SECConfigurationTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class SECConfigurationTest {

    /**
     * SECConfiguration Test toString.
     * @throws ParseException
     */
    @Test
    public void toStringTest() throws ParseException {
        SECConfiguration secConfiguration = new SECConfiguration();
        secConfiguration.setOperationScale(1111);
        secConfiguration.setRoundingMode(1111);

        String expected = "{\"operationScale\":1111,\"roundingMode\":1111}";

        assertEquals(expected, secConfiguration.toString());

        SECConfiguration secConfiguration2 = new SECConfiguration();
        secConfiguration2.setOperationScale(1111);
        secConfiguration2.setRoundingMode(1111);

        Assert.assertTrue(secConfiguration.equals(secConfiguration));
        Assert.assertTrue(secConfiguration.equals(secConfiguration2));
        Assert.assertTrue(secConfiguration.hashCode() == secConfiguration2.hashCode());

        SECConfiguration secConfiguration3 = new SECConfiguration();
        secConfiguration3.setOperationScale(1111);
        secConfiguration3.setRoundingMode(1112);

        Assert.assertFalse(secConfiguration.equals(null));
        Assert.assertFalse(secConfiguration.equals(secConfiguration3));
        Assert.assertFalse(secConfiguration.hashCode() == secConfiguration3.hashCode());
    }

}
