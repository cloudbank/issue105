/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.utility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.opencsv.CSVReader;

/**
 * CommonUtility Exposes useful function used through out the code
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class CommonUtility {
    /**
     * Private constructor
     */
    private CommonUtility() {

    }

    /**
     * Column mapping for test data
     */
    private static HashMap<String, Integer> csvTestDataColumns = new HashMap<String, Integer>();

    /**
     * Test data csv file name
     */
    private static volatile String csvTestDataFileName = "";

    /**
     * Set csv columns index
     * 
     * @param columns
     */
    public void setColumns(String columns) {
        String[] colIndex = columns.split(",");
        int i = 0;
        for (String col : colIndex) {
            csvTestDataColumns.put(col, i);
            i = i + 1;
        }

    }

    /**
     * Check if passed business date is invalid. More checks can be applied
     * later to check date validity
     * 
     * @param businessDate
     * @return boolean true if date invalid else false
     */
    public static Boolean checkBusinessDateInValid(Date businessDate) {
        if (businessDate == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Set file name
     * 
     * @param fileName
     */
    static public void setFileName(String fileName) {
        CommonUtility.csvTestDataFileName = fileName;
    }

    /**
     * Check passed parameter in engine implementations
     * 
     * @param securitySECData
     *            the passed SecuritySECData object
     * @param configuration
     *            the passed SECConfiguration object
     * @return true if both are not null else returns false
     */
    public static Boolean checkPassedParametersEngines(SecuritySECData securitySECData,
            SECConfiguration configuration) {
        Boolean isParamsNotNull = false;
        if (securitySECData != null && configuration != null) {
            isParamsNotNull = true;
        }
        return isParamsNotNull;
    }

    /**
     * Parse Test data CSV File
     * 
     * @return
     * @throws IOException
     * @throws ParseException
     * 
     */
    public static List<SecuritySECData> parsePhase1TestData() throws IOException, ParseException {
        // System.out.println(csvTestDataFileName);
        // System.out.println(csvTestDataColumns);
        CSVReader reader = new CSVReader(
                new InputStreamReader(CommonUtility.class.getResourceAsStream(csvTestDataFileName)), ',', '"', 1);
        List<SecuritySECData> entities = new ArrayList<SecuritySECData>();
        String[] nextLine;
        // map column to index;
        HashMap<String, Integer> columnIndex = new HashMap<String, Integer>();
        while ((nextLine = reader.readNext()) != null) {
            SecuritySECData securitySECData = new SecuritySECData();
            SecurityReferenceData securityReferenceData = new SecurityReferenceData();
            PositionData positionData = new PositionData();
            securityReferenceData.setIvType(nextLine[csvTestDataColumns.get("ivType")]);
            securitySECData.setSecurityIdentifier(nextLine[csvTestDataColumns.get("securityIdentifier")]);
            securityReferenceData.setSecurityIdentifier(nextLine[csvTestDataColumns.get("securityIdentifier")]);
            positionData.setSecurityIdentifier(nextLine[csvTestDataColumns.get("securityIdentifier")]);
            securityReferenceData.setSecurityName(nextLine[csvTestDataColumns.get("securityName")]);
            securityReferenceData.setSecurityRedemptionPrice(
                    getBigDecimalValue(nextLine[csvTestDataColumns.get("securityRedemptionPrice")]));
            securityReferenceData
                    .setDerStepIndicator(getBooleanValue((nextLine[csvTestDataColumns.get("derStepIndicator")])));
            securityReferenceData.setDerHybridIndicator(
                    getBooleanValue((nextLine[csvTestDataColumns.get("derHybridIndicator")])));
            securitySECData.setDerYieldCalcEngine((nextLine[csvTestDataColumns.get("deryieldCalcEngine")]));
            securityReferenceData.setInterestRt(getBigDecimalValue(nextLine[csvTestDataColumns.get("interestRt")]));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date maturityDate = format.parse(nextLine[csvTestDataColumns.get("finalMaturityDate")]);
            securityReferenceData.setFinalMaturityDate(maturityDate);
            securitySECData.setSecurityPrice(getBigDecimalValue(nextLine[csvTestDataColumns.get("securityPrice")]));
            securitySECData.setFxRate(getBigDecimalValue(nextLine[csvTestDataColumns.get("fxRate")]));
            securitySECData.setDerTIPSInflationaryRatio(
                    getBigDecimalValue(nextLine[csvTestDataColumns.get("derTIPSInflationaryRatio")]));
            securitySECData.setDerOneDaySecurityYield(
                    getBigDecimalValue(nextLine[csvTestDataColumns.get("derOneDaySecurityYield")]));
            securitySECData.setSecurityReferenceData(securityReferenceData);
            Date reportDate = format.parse(nextLine[csvTestDataColumns.get("reportDate")]);
            securitySECData.setReportDate(reportDate);
            securitySECData.setDerRedemptionDate(securityReferenceData.getFinalMaturityDate());
            positionData.setMarketValue(getBigDecimalValue(nextLine[csvTestDataColumns.get("marketValue")]));
            positionData.setAccruedIncome(getBigDecimalValue(nextLine[csvTestDataColumns.get("accruedIncome")]));
            positionData.setEarnedInflationaryCompensationBase(
                    getBigDecimalValue(nextLine[csvTestDataColumns.get("earnedInflationaryCompensationBase")]));
            positionData.setDerOneDaySecurityIncome(
                    getBigDecimalValue(nextLine[csvTestDataColumns.get("derOneDaySecurityIncome")]));
            positionData.setShareParAmount(getBigDecimalValue(nextLine[csvTestDataColumns.get("shareParAmount")]));
            positionData.setEarnedAmortizationBase(
                    getBigDecimalValue(nextLine[csvTestDataColumns.get("earnedAmortizationBase")]));
            positionData.setPositionValInflationAdjShares(
                    getBigDecimalValue(nextLine[csvTestDataColumns.get("positionValInflationAdjShares")]));
            securitySECData.setPositionData(new PositionData[] { positionData });
            entities.add(securitySECData);
        }
        reader.close();
        return entities;
    }

    /**
     * Parse number value in test data
     * 
     * @param value
     * @return parsedValue
     */
    private static BigDecimal getBigDecimalValue(String value) {
        // System.out.println(value);
        BigDecimal calcVal;
        if (value == null || value.isEmpty() || value.trim().compareTo("-") == 0) {
            calcVal = new BigDecimal("0");
        } else {
            calcVal = new BigDecimal(value);
        }
        return calcVal;
    }

    /**
     * Parse boolean value in test data
     * 
     * @param value
     * @return parsed value
     */
    private static Boolean getBooleanValue(String value) {
        if (value != null && value.compareTo("T") == 0) {
            return true;
        } else {
            return false;
        }
    }

}
