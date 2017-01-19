/*
* Copyright (c) 2017 TopCoder, Inc. All rights reserved.
*/
package com.csa.apex.fundyield.api.services.impl.moneymarketfundyield;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.springframework.boot.test.IntegrationTest;
/**
* Test class for the ClassLevelRestated30DayYieldCalculationOutput.
*
* @author TCSDEVELOPER
* @version 1.0
*/
@IntegrationTest
public class ClassLevelRestated30DayYieldCalculationOutputTest {
    /**
     * Test for method getDerMnyMktRst30DayYieldPctBigDecimal.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void getDerMnyMktRst30DayYieldPctBigDecimal() throws Exception {
        ClassLevelRestated30DayYieldCalculationOutput instance = new ClassLevelRestated30DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(35);
        instance.setDerMnyMktRst30DayYieldPctBigDecimal(expected);
        assertEquals(expected, instance.getDerMnyMktRst30DayYieldPctBigDecimal());
    }
    /**
     * Test for method setDerMnyMktRst30DayYieldPctBigDecimal.
     *
     * @throws Exception to JUnit
     */
    @Test
    public void setDerMnyMktRst30DayYieldPctBigDecimal() throws Exception {
        ClassLevelRestated30DayYieldCalculationOutput instance = new ClassLevelRestated30DayYieldCalculationOutput();
        BigDecimal expected = new BigDecimal(75);
        instance.setDerMnyMktRst30DayYieldPctBigDecimal(expected);
        assertEquals(expected, instance.getDerMnyMktRst30DayYieldPctBigDecimal());
    }
}
