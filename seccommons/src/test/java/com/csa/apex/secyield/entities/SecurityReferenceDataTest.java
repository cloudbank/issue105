package com.csa.apex.secyield.entities;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the SecurityReferenceData.
 *
 * @see SecurityReferenceDataTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class SecurityReferenceDataTest {

    /**
     * SecurityReferenceData Test toString
     *
     * @throws ParseException
     */
    @Test
    public void toStringTest() throws ParseException {
        SecurityReferenceData securityReferenceData = new SecurityReferenceData();
        securityReferenceData.setSecurityIdentifier("1111");
        securityReferenceData.setIvType("1111");
        securityReferenceData.setSecurityName("SEC1111");

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dt = formatter.parse("01/15/2016");
        securityReferenceData.setFinalMaturityDate(dt);

        securityReferenceData.setSecurityRedemptionPrice(new BigDecimal(1111));
        securityReferenceData.setInterestRt(new BigDecimal(1.5));
        securityReferenceData.setDefIndicator(false);
        securityReferenceData.setDerStepIndicator(true);
        securityReferenceData.setDerHybridIndicator(true);
        securityReferenceData.setIoHybridField("IO1111");
        securityReferenceData.setAs400RateType("AS1111");
        securityReferenceData.setProspectiveMethod("PROS1111");

        String expected = "SecurityReferenceData{securityIdentifier='1111', ivType='1111', securityName='SEC1111', finalMaturityDate=01/15/2016, securityRedemptionPrice=1111, interestRt=1.5, defIndicator=false, derStepIndicator=true, derHybridIndicator=true, ioHybridField='IO1111', as400RateType='AS1111', prospectiveMethod='PROS1111'}";

        assertEquals(expected, securityReferenceData.toString());
    }

}
