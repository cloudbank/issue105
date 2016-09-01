package com.csa.apex.secyield.entities;

import org.junit.Test;
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
     * SECConfiguration Test toString
     *
     * @throws ParseException
     */
    @Test
    public void toStringTest() throws ParseException {
        SECConfiguration secConfiguration = new SECConfiguration();
        secConfiguration.setOperationScale(1111);
        secConfiguration.setRoundingMode(1111);

        String expected = "SECConfiguration{operationScale=1111, roundingMode=1111}";

        assertEquals(expected, secConfiguration.toString());
    }

}
