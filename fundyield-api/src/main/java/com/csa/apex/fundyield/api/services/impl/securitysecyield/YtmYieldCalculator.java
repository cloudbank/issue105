/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl.securitysecyield;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.stereotype.Service;

import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.DateUtility;

/**
 * The YTM (TIPS) Yield calculator.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class YtmYieldCalculator {

	/**
	 * Empty constructor.
	 */
	public YtmYieldCalculator() {
		// Empty
	}

	/**
	 * Gets previous coupon date of the security.
	 *
	 * @param input
	 *            the YtmYieldCalculationInput object
	 * @return previous coupon date
	 */
	private Date getPreviousCouponDate(YtmYieldCalculationInput input) {
		Calendar maturityDateCal = Calendar.getInstance();
		maturityDateCal.setTime(input.getMaturityDate());
		Calendar businessDateCal = Calendar.getInstance();
		businessDateCal.setTime(input.getReportDate());
		int yearBusiness = businessDateCal.get(Calendar.YEAR);
		int monthBusiness = businessDateCal.get(Calendar.MONTH);
		int dayBusiness = businessDateCal.get(Calendar.DAY_OF_MONTH);
		int monthMaturity = maturityDateCal.get(Calendar.MONTH);
		int dayMaturity = maturityDateCal.get(Calendar.DAY_OF_MONTH);
		DateTime compareDate = new DateTime(yearBusiness, monthMaturity + 1, dayMaturity, 0, 0, 0, 0);
		DateTime reportDate = new DateTime(yearBusiness, monthBusiness + 1, dayBusiness, 0, 0, 0, 0);
		if (compareDate.compareTo(reportDate) < 0) {
			Period period = new Period(compareDate, reportDate, PeriodType.months().withDaysRemoved());
			int months = period.getMonths() + 1;
			if (months > 6) {
				compareDate = compareDate.plusMonths(6);
			}
		} else {
			Period p = new Period(reportDate, compareDate, PeriodType.months().withDaysRemoved());
			int months = p.getMonths() + 1;
			if (months > 6) {
				compareDate = compareDate.plusMonths(-12);
			} else {
				compareDate = compareDate.plusMonths(-6);
			}
		}
		return compareDate.toDate();
	}

	/**
	 * Calculates DSC for the security.
	 * 
	 * @param input
	 *            the YtmYieldCalculationInput object
	 * @param previousCouponDate
	 *            the previous Coupon Date
	 * @return DSC value
	 */
	private int getDSC(YtmYieldCalculationInput input, Date previousCouponDate) {
		Calendar previousCouponDateCal = Calendar.getInstance();
		previousCouponDateCal.setTime(previousCouponDate);
		previousCouponDateCal.add(Calendar.MONTH, 6);
		// calculate next coupon date
		Date nextCouponDate = previousCouponDateCal.getTime();
		return DateUtility.days360(input.getReportDate(), nextCouponDate);
	}

	/**
	 * Gets value of yield function.
	 *
	 * @param input
	 *            YtmYieldCalculationInput object contains input data for
	 *            calculation
	 * @param dto
	 *            YtmYieldCalculationVariablesDTO object contains data for
	 *            calculation
	 * @param yield
	 *            Yield value for calculation
	 * @return function value for passed yield value
	 */
	private Double getYieldFormulaVal(YtmYieldCalculationInput input, YtmYieldCalculationVariablesDTO dto,
			BigDecimal yield) {
		// (Redemption Value) equal to maturity price
		Double rv = input.getMaturityPrice().doubleValue();
		int frequencyValue = input.getFrequencyValue();
		int betSettlementRedemption = dto.couponsBetSettlementRedemption;
		int dsc = dto.dsc;
		int numberOfDays = input.getNumOfDaysInPeriod();
		// (Annual Interest Rate) equal to Coupon (interest) Rate
		Double currentIncomeRate = input.getCurrentIncomeRate().doubleValue();
		int dbpCouponDateSettlementDate = dto.daysBetPriorCouponDateSettlementDate;
		Double cleanPrice = dto.cleanPrice.doubleValue();
		Double yByM = yield.divide(new BigDecimal(frequencyValue)).doubleValue();
		Double onePlusYByM = 1 + yByM;
		Double comp1 = rv / Math.pow(onePlusYByM, betSettlementRedemption - 1 + ((double) dsc / numberOfDays));
		Double comp2first = (100 * currentIncomeRate / frequencyValue) / Math.pow(onePlusYByM, (double) dsc / numberOfDays);
		Double comp2Second = (Math.pow(onePlusYByM, betSettlementRedemption) - 1) / (yByM * Math.pow(onePlusYByM, (double) betSettlementRedemption - 1));
		Double comp2 = comp2first * comp2Second;
		Double comp3 = (100 * (currentIncomeRate / frequencyValue) * ((double) dbpCouponDateSettlementDate / numberOfDays)) + cleanPrice;
		return comp1 + comp2 - comp3;
	}

	/**
	 * Uses bisection method to calculate yield value for which function value
	 * is 0.
	 *
	 * @param input
	 *            YtmYieldCalculationInput object contains input data for
	 *            calculation
	 * @param dto
	 *            YtmYieldCalculationVariablesDTO object contains data for
	 *            calculation
	 * @param startYield
	 *            startYield F(startYield) < 0
	 * @param endYield
	 *            endYield F(endYield) > 0
	 * @return yield value
	 */
	private BigDecimal bisection(YtmYieldCalculationInput input, YtmYieldCalculationVariablesDTO dto, Double startYield,
			Double endYield) {
		int countSteps = 0;
		Double start = startYield;
		Double end = endYield;
		Double mid = (end + start) / 2;
		while (end > start && countSteps < input.getBinarySearchCount()) {
			mid = (end + start) / 2;
			Double funcVal = getYieldFormulaVal(input, dto, new BigDecimal(mid));
			if (funcVal < 0) {
				end = mid - input.getCalculationStep();
			} else if (funcVal > 0) {
				start = mid + input.getCalculationStep();
			}
			countSteps = countSteps + 1;
		}
		return new BigDecimal(mid);

	}

	/**
	 * Calculates yield value for YtmYieldCalculationVariablesDTO object. It
	 * uses bisection algo to calculate value.
	 *
	 * @param ytmYieldCalculationVariablesDTO
	 *            the passed YtmYieldCalculationVariablesDTO object
	 * @return yield value
	 */
	private BigDecimal getYield(YtmYieldCalculationInput input, YtmYieldCalculationVariablesDTO dto) {
		BigDecimal yield = BigDecimal.valueOf(input.getCalculationStep());
		Double funcVal = getYieldFormulaVal(input, dto, yield);
		// yield is negative
		if (funcVal < 0) {
			// find between -m to 0
			yield = bisection(input, dto, -1.0 * input.getFrequencyValue(),
					- input.getCalculationStep());
			// if yield < minYield (20%) then return 0.02
			if (yield.doubleValue() < input.getMinYield()) {
				yield = BigDecimal.valueOf(input.getMinYield());
			}
		}
		// yield is positive
		else {
			// check yield value at maxYield
			Double maxYieldFuncValue = getYieldFormulaVal(input, dto,
					BigDecimal.valueOf(input.getMaxYield()));
			// if yield at maxYield < 0 then yield lies between 0 and maxYield
			// We can do this when y > = 0 because in this case
			// d F(y) / d (y) < 0.
			// therefore if F(maxYield) > 0 then there will be no y for which
			// F(y) = 0
			if (maxYieldFuncValue < 0) {
				// find between 0 and maxYield
				yield = bisection(input, dto, yield.doubleValue(), input.getMaxYield());
			} else {
				// else return maxYield
				yield = BigDecimal.valueOf(input.getMaxYield());
			}

		}
		return yield;
	}

	/**
	 * Calculates the YTM (TIPS) Yield.
	 *
	 * @param input the calculation input
	 * @return calculated result
	 * @throws IllegalArgumentException in case any error during calculation
	 */
	public YtmYieldCalculationOutput calculate(YtmYieldCalculationInput input) {
		CommonUtility.checkNull(input, this.getClass().getCanonicalName(), Constants.METHOD_CALCULATE, "Parameter YtmYieldCalculationInput");

		// set P (clean price) as Market Price/Inflationary Index Ratio
		BigDecimal cleanPrice = input.getMarketPrice().divide(input.getFdrTipsInsflationaryRatio(), input.getOperationScale(), BigDecimal.ROUND_HALF_UP);

		// calculate N (Number of coupons payable between settlement date and
		// redemption date, rounded up)
		BigDecimal diffInMillis = new BigDecimal(
				Math.abs(input.getMaturityDate().getTime() - input.getReportDate().getTime()));
		BigDecimal millisInYear = new BigDecimal(31556952000L);
		BigDecimal numberOfCouponsPayable = diffInMillis.divide(millisInYear, input.getOperationScale(), BigDecimal.ROUND_HALF_UP);
		numberOfCouponsPayable = numberOfCouponsPayable.multiply(new BigDecimal(2)).setScale(0, RoundingMode.UP);

		// calculate DSC
		// calculate previous coupon and next coupon dates first.
		// calculate the diff in months between maturity date and current date
		Date previousCouponDate = getPreviousCouponDate(input);
		int dsc = getDSC(input, previousCouponDate);
		// calculate A as Number of 30/360 Days from PREVIOUS COUPON DATE to report date
		int a = DateUtility.days360(previousCouponDate, input.getReportDate());

		YtmYieldCalculationVariablesDTO dto = new YtmYieldCalculationVariablesDTO();
		dto.cleanPrice = cleanPrice;
		dto.couponsBetSettlementRedemption = numberOfCouponsPayable.intValue();
		dto.daysBetPriorCouponDateSettlementDate = a;
		dto.dsc = dsc;

		BigDecimal yield = getYield(input, dto);
		yield = yield.setScale(input.getOperationScale(), input.getRoundingMode());

		// set the output values
		YtmYieldCalculationOutput output = new YtmYieldCalculationOutput();
		output.setFdrCleanPrice(cleanPrice);
		output.setDerOneDaySecurityYield(yield);
		output.setDerRedemptionPrice(input.getMaturityPrice());
		return output;
	}

	/**
	 * YtmYieldCalculationVariablesDTO
	 * 
	 * 
	 * Used for storing variables for YTM yield calculation
	 *
	 * @author [es],TCSDEVELOPER
	 * @version 1.0
	 */
	private class YtmYieldCalculationVariablesDTO {

		/**
		 * Clean price.
		 */
		private BigDecimal cleanPrice;

		/**
		 * Coupons between settlement and redemption date.
		 */
		private int couponsBetSettlementRedemption;

		/**
		 * The DSC.
		 */
		private int dsc;

		/**
		 * Days between prior coupon date and settlement date.
		 */
		private int daysBetPriorCouponDateSettlementDate;
	}

}
