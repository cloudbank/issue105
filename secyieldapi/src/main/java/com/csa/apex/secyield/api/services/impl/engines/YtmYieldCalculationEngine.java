/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services.impl.engines;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.csa.apex.secyield.api.services.impl.CalculationEngine;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.CalculationException;
import com.csa.apex.secyield.utility.CommonUtility;
import com.csa.apex.secyield.utility.DateUtility;

/**
 * CouponYieldCalculationEngine
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@Component
public class YtmYieldCalculationEngine implements CalculationEngine {
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
		 * Clean price
		 */
		private BigDecimal cleanPrice;

		/**
		 * Redemption value
		 */
		private BigDecimal redemptionValue;

		/**
		 * frequency
		 */
		private int frequency;

		/**
		 * Coupons between settlement and redemption date
		 */
		private int couponsBetSettlementRedemption;

		/**
		 * dsc
		 */
		private int dsc;

		/**
		 * No of days in the period
		 */
		private int noOfDaysInPeriod;

		/**
		 * Days between prior coupon date and settlement date
		 */
		private int daysBetPriorCouponDateSettlementDate;

		/**
		 * Annual Interest Rate
		 */
		private BigDecimal annualInterestRate;

		/**
		 * Getter cleanPrice
		 * 
		 * @return cleanPrice
		 */
		public BigDecimal getCleanPrice() {
			return cleanPrice;
		}

		/**
		 * Setter cleanPrice
		 * 
		 * @param cleanPrice
		 */
		public void setCleanPrice(BigDecimal cleanPrice) {
			this.cleanPrice = cleanPrice;
		}

		/**
		 * Getter redemptionValue
		 * 
		 * @return redemptionValue
		 */
		public BigDecimal getRedemptionValue() {
			return redemptionValue;
		}

		/**
		 * Setter redemptionValue
		 * 
		 * @param redemptionValue
		 */
		public void setRedemptionValue(BigDecimal redemptionValue) {
			this.redemptionValue = redemptionValue;
		}

		/**
		 * Getter frequency
		 * 
		 * @return frequency
		 */
		public int getFrequency() {
			return frequency;
		}

		/**
		 * Setter frequency
		 * 
		 * @param frequency
		 */
		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

		/**
		 * Getter couponsBetSettlementRedemption
		 * 
		 * @return couponsBetSettlementRedemption
		 */
		public int getCouponsBetSettlementRedemption() {
			return couponsBetSettlementRedemption;
		}

		/**
		 * Setter couponsBetSettlementRedemption
		 * 
		 * @param couponsBetSettlementRedemption
		 */
		public void setCouponsBetSettlementRedemption(int couponsBetSettlementRedemption) {
			this.couponsBetSettlementRedemption = couponsBetSettlementRedemption;
		}

		/**
		 * Getter dsc
		 * 
		 * @return
		 */
		public int getDsc() {
			return dsc;
		}

		/**
		 * Setter dsc
		 * 
		 * @param dsc
		 */
		public void setDsc(int dsc) {
			this.dsc = dsc;
		}

		/**
		 * Getter noOfDaysInPeriod
		 * 
		 * @return noOfDaysInPeriod
		 */
		public int getNoOfDaysInPeriod() {
			return noOfDaysInPeriod;
		}

		/**
		 * Setter noOfDaysInPeriod
		 * 
		 * @param noOfDaysInPeriod
		 */
		public void setNoOfDaysInPeriod(int noOfDaysInPeriod) {
			this.noOfDaysInPeriod = noOfDaysInPeriod;
		}

		/**
		 * Getter getDaysBetPriorCouponDateSettlementDate
		 * 
		 * @return getDaysBetPriorCouponDateSettlementDate
		 */
		public int getDaysBetPriorCouponDateSettlementDate() {
			return daysBetPriorCouponDateSettlementDate;
		}

		/**
		 * Setter getDaysBetPriorCouponDateSettlementDate
		 * 
		 * @param daysBetPriorCouponDateSettlementDate
		 */
		public void setDaysBetPriorCouponDateSettlementDate(int daysBetPriorCouponDateSettlementDate) {
			this.daysBetPriorCouponDateSettlementDate = daysBetPriorCouponDateSettlementDate;
		}

		/**
		 * Getter annualInterestRate
		 * 
		 * @return annualInterestRate
		 */
		public BigDecimal getAnnualInterestRate() {
			return annualInterestRate;
		}

		/**
		 * Setter annualInterestRate
		 * 
		 * @param annualInterestRate
		 */
		public void setAnnualInterestRate(BigDecimal annualInterestRate) {
			this.annualInterestRate = annualInterestRate;
		}
	}

	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(CouponYieldCalculationEngine.class);

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.illegalargumentexception}")
	private String illegalArgumentExceptionMessage;

	/**
	 * Error log message format
	 */
	@Value("${messages.errorlogmessage}")
	private String logErrorFormat;

	/**
	 * Calculate method name
	 */
	@Value("${calculationengine.calculatemethodname}")
	private String calculateMethodName;

	/**
	 * Calculation engine name
	 */
	public static final String ENGINE_NAME = "YtmYieldCalculationEngine";

	/**
	 * The scale for the BigDecimal operations. Has the default value.
	 */
	private int operationScale = 7;

	/**
	 * Default Rounding mode
	 */
	private int roundingMode = 4;

	/**
	 * The YTM (TIPS) Yield calculation engine. Uses symja to evaluate equation and find the unknown value of Y (Yield)
	 */
	private int frequencyValue = 2;

	/**
	 * The num of days in period used for calculation. Has the default value.
	 */
	private int numOfDaysInPeriod = 180;

	/**
	 * It defines the least error in the calculation There can be max error = ephsilon More the value accurate is result
	 */
	private double ephsilon = 0.000000001;

	/**
	 * Min yield when it is negative
	 */
	private double minYield = -0.02;
	

    /**
     * Max yield when it is negative
     */
    private double maxYield = 0.02;


	/**
	 * It is count to perform binary search. The larger value will result in more precise outcome yield.
	 */
	private int binarySearchCount;

	/**
	 * Constructor
	 */
	public YtmYieldCalculationEngine() {
		// default constructor
	}

	/**
	 * Getter ephsilon
	 * 
	 * @return ephsilon
	 */
	public double getEphsilon() {
		return ephsilon;
	}

	/**
	 * Setter ephsilon
	 * 
	 * @param ephsilon
	 */
	public void setEphsilon(double ephsilon) {
		this.ephsilon = ephsilon;
	}

	/**
	 * Getter minYield
	 * 
	 * @return minYield
	 */
	public double getMinYield() {
		return minYield;
	}

	/**
	 * Setter minYield
	 * 
	 * @param minYield
	 */
	public void setMinYield(double minYield) {
		this.minYield = minYield;
	}


    /**
     * Getter maxYield
     * 
     * @return maxYield
     */
    public double getMaxYield() {
        return maxYield;
    }

    /**
     * Setter maxYield
     * 
     * @param maxYield
     */
    public void setMaxYield(double maxYield) {
        this.maxYield = maxYield;
    }

	/**
	 * Getter for binarySearchCount
	 * 
	 * @return
	 */
	public int getBinarySearchCount() {
		return binarySearchCount;
	}

	/**
	 * Setter for binarySearchCount
	 * 
	 * @param binarySearchCount
	 */
	public void setBinarySearchCount(int binarySearchCount) {
		this.binarySearchCount = binarySearchCount;
	}

	/**
	 * Check passed parameter should not be null
	 * 
	 * @param securitySECData
	 *            the passed SecuritySECData object
	 * @param configuration
	 *            the passed SECConfiguration object
	 * @return true if both are not null else returns false
	 */
	private Boolean checkPassedParameters(SecuritySECData securitySECData, SECConfiguration configuration) {
		return CommonUtility.checkPassedParametersEngines(securitySECData, configuration);
	}

	/**
	 * Read from configuraion object and override the operationScale default value
	 * 
	 * @param configuration
	 *            the configuration object
	 */
	private void setConfiguration(SECConfiguration configuration) {
		int passedOperationScale = configuration.getOperationScale();
		int passedRoundingMode = configuration.getRoundingMode();
		operationScale = passedOperationScale != 0 ? passedOperationScale : operationScale;
		roundingMode = passedRoundingMode != 0 ? passedRoundingMode : roundingMode;
	}

	/**
	 * Gets previous coupon date of the security
	 * 
	 * @param securitySECData
	 *            the SecuritySECData object
	 * @return previous coupon date
	 */
	private Date getPreviousCouponDate(SecuritySECData securitySECData) {
		Calendar maturityDateCal = Calendar.getInstance();
		maturityDateCal.setTime(securitySECData.getSecurityReferenceData().getFinalMaturityDate());
		Calendar businessDateCal = Calendar.getInstance();
		businessDateCal.setTime(securitySECData.getReportDate());
		int yearBusiness = businessDateCal.get(Calendar.YEAR);
		int monthBusiness = businessDateCal.get(Calendar.MONTH);
		int dayBusiness = businessDateCal.get(Calendar.DAY_OF_MONTH);
		int monthMaturity = maturityDateCal.get(Calendar.MONTH);
		int dayMaturity = maturityDateCal.get(Calendar.DAY_OF_MONTH);
		DateTime compareDate = new DateTime(yearBusiness, monthMaturity + 1, dayMaturity, 0, 0, 0, 0);
		DateTime reportDate = new DateTime(yearBusiness, monthBusiness + 1, dayBusiness, 0, 0, 0, 0);
		if (compareDate.compareTo(reportDate) < 0) {
			Period p = new Period(compareDate, reportDate, PeriodType.months().withDaysRemoved());
			int months = p.getMonths() + 1;
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
	 * Calculates DSC for the security
	 * 
	 * @param securitySECData
	 *            the SecuritySECData object
	 * @param previousCouponDate
	 *            the PreviousCouponDate object
	 * @return DSC value
	 */
	private int getDSC(SecuritySECData securitySECData, Date previousCouponDate) {
		Calendar previousCouponDateCal = Calendar.getInstance();
		previousCouponDateCal.setTime(previousCouponDate);
		previousCouponDateCal.add(Calendar.MONTH, 6);
		// calculate next coupon date
		Date nextCouponDate = previousCouponDateCal.getTime();
		return DateUtility.days360(securitySECData.getReportDate(), nextCouponDate);
	}

	/**
	 * Gets value of yield function
	 * 
	 * @param ytmYieldCalculationVariablesDTO
	 *            YtmYieldCalculationVariablesDTO object contains data for calculation
	 * @param yield
	 *            Yield value for calculation
	 * @return function value for passed yield value
	 */
	private Double getYieldFormulaVal(YtmYieldCalculationVariablesDTO ytmYieldCalculationVariablesDTO,
			BigDecimal yield) {
		Double rv = ytmYieldCalculationVariablesDTO.getRedemptionValue().doubleValue();
		int m = ytmYieldCalculationVariablesDTO.getFrequency();
		int n = ytmYieldCalculationVariablesDTO.getCouponsBetSettlementRedemption();
		int dsc = ytmYieldCalculationVariablesDTO.getDsc();
		int e = ytmYieldCalculationVariablesDTO.getNoOfDaysInPeriod();
		Double r = ytmYieldCalculationVariablesDTO.getAnnualInterestRate().doubleValue();
		int a = ytmYieldCalculationVariablesDTO.getDaysBetPriorCouponDateSettlementDate();
		Double p = ytmYieldCalculationVariablesDTO.getCleanPrice().doubleValue();
		Double yByM = yield.divide(new BigDecimal(m)).doubleValue();
		Double onePlusYByM = 1 + yByM;
		Double comp1 = rv / Math.pow(onePlusYByM, n - 1 + ((double) dsc / e));
		Double comp2first = (100 * r / m) / Math.pow(onePlusYByM, (double) dsc / e);
		Double comp2Second = (Math.pow(onePlusYByM, n) - 1) / (yByM * Math.pow(onePlusYByM, (double) n - 1));
		Double comp2 = comp2first * comp2Second;
		Double comp3 = (100 * (r / m) * ((double) a / e)) + p;
		return comp1 + comp2 - comp3;
	}

	/**
	 * Uses bisection method to calculate yield value for which function value is 0
	 * 
	 * @param ytmYieldCalculationVariablesDTO
	 *            YtmYieldCalculationVariablesDTO object contains value for calculation
	 * @param startYield startYield F(startYield) < 0
	 * @param endYield endYield F(endYield) > 0
	 * @return yield value
	 */
	private BigDecimal bisection(YtmYieldCalculationVariablesDTO ytmYieldCalculationVariablesDTO,
			Double startYield, Double endYield) {
		int countSteps = 0;
		Double mid = (endYield + startYield) / 2;
		while (endYield > startYield && countSteps < binarySearchCount) {
			mid = (endYield + startYield) / 2;
			Double funcVal = getYieldFormulaVal(ytmYieldCalculationVariablesDTO, new BigDecimal(mid));
			if (funcVal < 0) {
				endYield = mid - ephsilon;
			} else if (funcVal > 0) {
				startYield = mid + ephsilon;
			}
			countSteps = countSteps + 1;
		}
		return new BigDecimal(mid);

	}

	/**
	 * Calculates yield value for YtmYieldCalculationVariablesDTO object It increases yield value from ephsilon and
	 * checks whether yield function gives zero value. Then it uses binary search to imprive accuracy of calculated
	 * yield value
	 * 
	 * @param ytmYieldCalculationVariablesDTO
	 *            the passed YtmYieldCalculationVariablesDTO object
	 * @return yield value
	 */
	private BigDecimal getYield(YtmYieldCalculationVariablesDTO ytmYieldCalculationVariablesDTO) {
		BigDecimal yield = BigDecimal.valueOf(ephsilon);
		Double funcVal = getYieldFormulaVal(ytmYieldCalculationVariablesDTO, yield);
		// yield is negative
		if (funcVal < 0) {
		    // find between -m to 0
		    yield =  bisection(ytmYieldCalculationVariablesDTO,-1.0 * ytmYieldCalculationVariablesDTO.getFrequency(), -ephsilon);
		    // if yield < minYield (20%) then return 0.02
		    if(yield.doubleValue() < minYield)
		    {
		        yield = BigDecimal.valueOf(minYield);
		    }
		    
		   
		}
		// yield is positive
		else {
		    // check yield value at maxYield
		    Double maxYieldFuncValue = getYieldFormulaVal(ytmYieldCalculationVariablesDTO,BigDecimal.valueOf(maxYield));
		    // if yield at maxYield < 0 then yield lies between 0 and maxYield
		    // We can do this when y > = 0 beacause in this case
		    // d F(y) / d (y) < 0.
		    // therefore if F(maxYield) > 0 then there will be no y for which F(y) = 0
		    if(maxYieldFuncValue < 0)
		    {
		        // find between 0 and maxYield
		        yield = bisection(ytmYieldCalculationVariablesDTO, yield.doubleValue(), maxYield);
		    }
		    else
		    {
		        // else return maxYield
		        yield = BigDecimal.valueOf(maxYield);
		    }
			
		}
		return yield;
	}

	/**
	 * Engine Calculate method implementation
	 * 
	 * @param securitySECData
	 *            the SecuritySECData object
	 * @param configuration
	 *            the Configuration Object
	 * @return securitySECData with calculated result
	 * @throws CalculationException
	 */
	@Override
	public SecuritySECData calculate(SecuritySECData securitySECData, SECConfiguration configuration)
			throws CalculationException {
		if (!checkPassedParameters(securitySECData, configuration)) {
			logger.error(String.format(logErrorFormat, calculateMethodName, illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		setConfiguration(configuration);
		try {
			// initialize parameters for the expression
			// set P (clean price) as Market Price/Inflationary Index Ratio
			BigDecimal cleanPrice = securitySECData.getSecurityPrice()
					.divide(securitySECData.getDerTIPSInflationaryRatio(), operationScale, BigDecimal.ROUND_HALF_UP);
			securitySECData.setDerCleanPrice(cleanPrice);
			// set M to the preconfigured frequency.
			int m = frequencyValue;

			// set E to the preconfigured numOfDaysInPeriod.
			int e = numOfDaysInPeriod;

			// set RV (Redemption Value) equal to maturity price
			BigDecimal rv = securitySECData.getSecurityReferenceData().getSecurityRedemptionPrice();
			securitySECData.setDerRedemptionPrice(rv);
			// set R (Annual Interest Rate) equal to Coupon (interest) Rate
			BigDecimal r = securitySECData.getSecurityReferenceData().getInterestRt();

			// calculate N (Number of coupons payable between settlement date
			// and redemption date, rounded up)
			BigDecimal diffInMillis = new BigDecimal(
					Math.abs(securitySECData.getSecurityReferenceData().getFinalMaturityDate().getTime()
							- securitySECData.getReportDate().getTime()));
			BigDecimal millisInYear = new BigDecimal(31556952000L);
			int n = diffInMillis.divide(millisInYear, operationScale, BigDecimal.ROUND_HALF_UP)
					.multiply(new BigDecimal(2)).setScale(0, RoundingMode.UP).intValue();
			// calculate DSC
			// calculate previous coupon and next coupon dates first.
			// calculate the diff in months between maturity date and current
			// date
			Date previousCouponDate = getPreviousCouponDate(securitySECData);
			int dsc = getDSC(securitySECData, previousCouponDate);
			// calculate A as Number of 30/360 Days from PREVIOUS COUPON DATE to
			int a = DateUtility.days360(previousCouponDate, securitySECData.getReportDate());
			// create DTO YtmYieldCalculationVariables
			YtmYieldCalculationVariablesDTO ytmYieldCalculationVariablesDTO = new YtmYieldCalculationVariablesDTO();
			ytmYieldCalculationVariablesDTO.setCleanPrice(cleanPrice);
			ytmYieldCalculationVariablesDTO.setRedemptionValue(rv);
			ytmYieldCalculationVariablesDTO.setFrequency(m);
			ytmYieldCalculationVariablesDTO.setCouponsBetSettlementRedemption(n);
			ytmYieldCalculationVariablesDTO.setAnnualInterestRate(r);
			ytmYieldCalculationVariablesDTO.setDaysBetPriorCouponDateSettlementDate(a);
			ytmYieldCalculationVariablesDTO.setDsc(dsc);
			ytmYieldCalculationVariablesDTO.setNoOfDaysInPeriod(e);
			BigDecimal yield = getYield(ytmYieldCalculationVariablesDTO);
			yield = yield.setScale(operationScale, roundingMode);
			securitySECData.setDerOneDaySecurityYield(yield);
			return securitySECData;

		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, calculateMethodName, e.getMessage()));
			throw new CalculationException(e.getMessage(), e);
		}
	}
}
