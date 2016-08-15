package com.csa.apex.secyield.utility;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.csa.apex.secyield.Application;

/**
 * Test class for the DateUtility.
 *
 * @see DateUtilityTest
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DateUtilityTest {
	/**
	 * Date Utility Test leap year feb month included
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test1() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/15/2016");
		Date endDate = formatter.parse("12/10/2016");
		assertEquals(DateUtility.days360(startDate, endDate), 325);
	}

	/**
	 * Date Utility Test leap year feb month excluded
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test2() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("03/05/2016");
		Date endDate = formatter.parse("11/09/2016");
		assertEquals(DateUtility.days360(startDate, endDate), 244);
	}

	/**
	 * Date Utility Test leap year feb month included Start day 31 End day 31
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test3() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/31/2016");
		Date endDate = formatter.parse("10/31/2016");
		assertEquals(DateUtility.days360(startDate, endDate), 270);
	}

	/**
	 * Date Utility Test leap year feb month included Start day 28 End day 31
	 * (start day < 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test4() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/28/2016");
		Date endDate = formatter.parse("10/31/2016");
		assertEquals(DateUtility.days360(startDate, endDate), 273);
	}

	/**
	 * Date Utility Test leap year feb month included Start day 30 End day 31
	 * (start day >= 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test5() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/30/2016");
		Date endDate = formatter.parse("10/31/2016");
		assertEquals(DateUtility.days360(startDate, endDate), 270);
	}

	/**
	 * Date Utility Test leap year feb month included Start day 29 feb End day
	 * 31 (start day < 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test6() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("02/29/2016");
		Date endDate = formatter.parse("10/31/2016");
		assertEquals(DateUtility.days360(startDate, endDate), 240);
	}

	/**
	 * Date Utility Test leap year feb month included Start day 28 feb End day
	 * 31 (start day < 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test7() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("02/28/2016");
		Date endDate = formatter.parse("10/31/2016");
		assertEquals(DateUtility.days360(startDate, endDate), 243);
	}

	/**
	 * Date Utility Test non leap year feb month included
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test8() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/15/2015");
		Date endDate = formatter.parse("12/10/2015");
		assertEquals(DateUtility.days360(startDate, endDate), 325);
	}

	/**
	 * Date Utility Test non leap year feb month excluded
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test9() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("03/05/2015");
		Date endDate = formatter.parse("11/09/2015");
		assertEquals(DateUtility.days360(startDate, endDate), 244);
	}

	/**
	 * Date Utility Test non leap year feb month included Start day 31 End day
	 * 31
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test10() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/31/2015");
		Date endDate = formatter.parse("10/31/2015");
		assertEquals(DateUtility.days360(startDate, endDate), 270);
	}

	/**
	 * Date Utility Test non leap year feb month included Start day 27 End day
	 * 31 (start day < 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test11() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/27/2015");
		Date endDate = formatter.parse("10/31/2015");
		assertEquals(DateUtility.days360(startDate, endDate), 274);
	}

	/**
	 * Date Utility Test non leap year feb month included Start day 30 End day
	 * 31 (start day >= 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test12() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("01/30/2015");
		Date endDate = formatter.parse("10/31/2015");
		assertEquals(DateUtility.days360(startDate, endDate), 270);
	}

	/**
	 * Date Utility Test non year feb month included Start day 28 feb End day 31
	 * (start day < 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test13() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("02/28/2015");
		Date endDate = formatter.parse("10/31/2015");
		assertEquals(DateUtility.days360(startDate, endDate), 240);
	}

	/**
	 * Date Utility Test non leap year feb month included Start day 27 feb End
	 * day 31 (start day < 30)
	 * 
	 * @throws ParseException
	 */
	@Test
	public void checkDays360Test14() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("02/27/2015");
		Date endDate = formatter.parse("10/31/2015");
		assertEquals(DateUtility.days360(startDate, endDate), 244);
	}

	/**
	 * Date Utility Test Diff in start date and end date > 3 years
	 */
	@Test
	public void checkDays360Test15() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = formatter.parse("02/01/2015");
		Date endDate = formatter.parse("10/31/2018");
		assertEquals(DateUtility.days360(startDate, endDate), 1350);
	}
}
