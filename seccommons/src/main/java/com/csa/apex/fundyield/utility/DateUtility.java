/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.utility;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

/**
 * DateUtility Exposes useful function not available in Joda.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class DateUtility {
	/**
	 * Private constructor.
	 */
	private DateUtility() {
	}

	/**
	 * Checks whether year is leap year or not.
	 * 
	 * @param year
	 *            year to be checked
	 * @return true, if it is leap year, false otherwise.
	 */
	private static Boolean isLeapYear(int year) {
		GregorianCalendar cal = new GregorianCalendar();
		return cal.isLeapYear(year);
	}

	/**
	 * Get next month and year for passed values.
	 * 
	 * @param month
	 *            current month
	 * @param year
	 *            current year
	 * @return DateTime
	 */
	private static DateTime getNextMonthDate(int month, int year) {
		DateTime next = new DateTime(year, month, 1, 0, 0);
		next = next.plusMonths(1);
		return next;
	}

	/**
	 * Compares date for month and year.
	 * 
	 * So 12 July 2016 and 18 July 2016 will return true as both month and year matches.
	 * 
	 * @param startDate
	 *            start date
	 * @param endDate
	 *            end date
	 * 
	 * @return true, if dates year/month are equals, false otherwise.
	 */
	private static Boolean compareMonthYear(DateTime startDate, DateTime endDate) {
		Boolean isSame;
		if ((startDate.getMonthOfYear() == endDate.getMonthOfYear()) && (startDate.getYear() == endDate.getYear())) {
			isSame = true;
		} else {
			isSame = false;
		}
		return isSame;
	}

	/**
	 * Calculate difference between startdate and end date using 360 day calendar.
	 * 
	 * @param startDate the starting date
	 * @param endDate the end date
	 * @return difference between startdate and end date using 360 day calendar.
	 */
	private static int getDiff360(DateTime startDate, DateTime endDate) {

		// loop from start date to end date and check the values
		DateTime loopStartDate = new DateTime(startDate.getYear(), startDate.getMonthOfYear(), 1, 0, 0);
		DateTime loopEndDate = new DateTime(endDate.getYear(), endDate.getMonthOfYear(), 1, 0, 0);
		DateTime tempDate = loopStartDate;
		int count = 0;
		// when tempDate <= loopEndDate
		while (tempDate.compareTo(loopEndDate) <= 0) {
			// set default start and end day
			int startDay = 1;
			int endDay = 30;

			// if tempdate is same month and year as startDate
			// update startDay
			if (compareMonthYear(tempDate, startDate)) {
				startDay = startDate.getDayOfMonth();
			}

			// if tempdate is same month and year as endDate
			// update endDay
			if (compareMonthYear(tempDate, endDate)) {
				endDay = endDate.getDayOfMonth();
			}


			// no extra days are added if the start date is the last day of February
			if (isLeapYear(tempDate.getYear()) && tempDate.getMonthOfYear() == 2 && startDay == 29) {
				// if year is a leap year
				endDay = 29;
				startDay = 29;
			} else if (tempDate.getMonthOfYear() == 2 && startDay == 28 && !isLeapYear(tempDate.getYear())) {
				endDay = 28;
				startDay = 28;
			}
			count = count + endDay - startDay + 1;
			tempDate = tempDate.plusMonths(1);
		}

		return count - 1;
	}

	/**
	 * Implementation of the day360 method.
	 *
	 * This computes the difference in days between the startDate and the endDate, based on a 360-day year, divided into
	 * twelve 30-day months.
	 *
	 * This implementation uses the US(NASD) method (quoted from https://support.microsoft.com/en-us/kb/235575):
	 *  - If the starting date is the 31st of a month, it becomes equal to the 30th of the same month.
	 *  - If the ending date is the 31st of a month and the starting date is NOT the last day of a month,
	 * the ending date becomes equal to the 1st of the next month, otherwise the ending date becomes equal
	 * to the 30th of the same month.
	 *  - If the start date is the last day of February, then extra days added to February are ignored.
	 * 
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return diff in days
	 */
	public static int days360(Date startDate, Date endDate) {

		Calendar startDateCal = Calendar.getInstance();
		startDateCal.setTime(startDate);
		Calendar endDateCal = Calendar.getInstance();
		endDateCal.setTime(endDate);
		int yearStartDate = startDateCal.get(Calendar.YEAR);
		int monthStartDate = startDateCal.get(Calendar.MONTH) + 1;
		int dayStartDate = startDateCal.get(Calendar.DAY_OF_MONTH);

		int yearEndDate = endDateCal.get(Calendar.YEAR);
		int monthEndDate = endDateCal.get(Calendar.MONTH) + 1;
		int dayEndDate = endDateCal.get(Calendar.DAY_OF_MONTH);

		// adjust start date and end date

		// check the day of start date
		if (dayStartDate == 31) {
			dayStartDate = 30;
		}

		// Special handling for end dates on the 31st of a month:
		// The following if-statements will convert the 31st end-date into a 30th end-date
		// if the start-date is the last day of its month.  Otherwise, the end-date is moved to the
		// 1st of the next month.
		if (dayEndDate == 31) {
			if (dayStartDate == 29 && isLeapYear(yearStartDate) && monthStartDate == 2) {
				// If the start date is the last day of February for leap years.
				dayEndDate = 30;
			} else if (dayStartDate == 28 && monthStartDate == 2 && !isLeapYear(yearStartDate)) {
				// If the start date is the last day of February - for non-leap years.
				dayEndDate = 30;
			} else if (dayStartDate < 30) {
				// For non-February months:
				// We can assume that the start date is not the last day of its month if its value
				// is less than 30.  (31st is converted to 30th in a previous step)
				DateTime next = getNextMonthDate(monthEndDate, yearEndDate);
				monthEndDate = next.getMonthOfYear();
				yearEndDate = next.getYear();
				dayEndDate = 1;

			} else {
				// For non-February months:
				// The start date is not less than 30, so we determine it is the last day of its month.
				dayEndDate = 30;
			}
		}
		DateTime cal360StartDate = new DateTime(yearStartDate, monthStartDate, dayStartDate, 0, 0);
		DateTime cal360EndDate = new DateTime(yearEndDate, monthEndDate, dayEndDate, 0, 0);
		return getDiff360(cal360StartDate, cal360EndDate);

	}

	/**
	 * Convert to sql date.
	 *
	 * @param date The date to convert
	 * @return Sql date
	 */
	public static Date convertToSqlDate(Date date) {
		if (date == null) {
			return null;
		}

		if (date instanceof java.sql.Date) {
			return date;
		}

		return new java.sql.Date(date.getTime());
	}
}
