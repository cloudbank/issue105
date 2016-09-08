/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield;

import com.csa.apex.secyield.customer.api.mock.service.CustomerDataPersistenceService;
import com.csa.apex.secyield.customer.api.mock.service.impl.CustomerDataPersistenceServiceImpl;
import com.csa.apex.secyield.customer.api.mock.utility.FundDataColumns;
import com.csa.apex.secyield.customer.api.mock.utility.PositionDataColumns;
import com.csa.apex.secyield.customer.api.mock.utility.SecuritySECDataColumns;
import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Import data from excel into customer sec table in DB
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ImportExcel {
    /**
     * The logger
     */
    private static Logger logger = LogManager.getLogger(ImportExcel.class);

    /**
     * The command usage text
     */
    private static final String COMMAND_USAGE = "ImportExcel [-c/--clean][-h/--help][-e/--excel][-m/--mapping]";

    /**
     * The table name of calculated_fund_data
     */
    public static final String TABLE_CALCULATED_FUND_DATA = "calculated_fund_data";

    /**
     * The table name of customer_fund_data
     */
    public static final String TABLE_CUSTOMER_FUND_DATA = "customer_fund_data";

    /**
     * The delete query to delete customer fund data by security identifiers
     */
    private static final String DELETE_CUSTOMER_FUND_DATA = "DELETE FROM "+ TABLE_CUSTOMER_FUND_DATA
            +" WHERE portfolio_number=? and report_date=?;";

    /**
     * The delete query to delete calculated fund data by security identifiers
     */
    private static final String DELETE_CALCULATED_FUND_DATA = "DELETE FROM "+ TABLE_CALCULATED_FUND_DATA+
            " WHERE portfolio_number=? and report_date=?;";

    /**
     * The delete query to delete customer position data by security identifiers
     */
    private static final String DELETE_CUSTOMER_POSITION_DATA = "DELETE FROM "+ CustomerDataPersistenceServiceImpl.TABLE_CUSTOMER_POSITION_DATA
            +" WHERE security_identifier=? and report_date=?;";

    /**
     * The delete query to delete customer security sec data by security identifiers
     */
    private static final String DELETE_CUSTOMER_SECURITY_SEC_DATA = "DELETE FROM "+ CustomerDataPersistenceServiceImpl.TABLE_CUSTOMER_SECURITY_SEC_DATA
            +" WHERE security_identifier=? and report_date=?";

    /**
     * The delete query to delete calculated position data by security identifiers
     */
    private static final String DELETE_CALCULATED_POSITION_DATA = "DELETE FROM "+ CustomerDataPersistenceServiceImpl.TABLE_CALCULATED_POSITION_DATA+
            " WHERE security_identifier=? and report_date=?;";

    /**
     * The delete query to delete calculated security sec data by security identifiers
     */
    private static final String DELETE_CALCULATED_SECURITY_SEC_DATA = "DELETE FROM "+ CustomerDataPersistenceServiceImpl.TABLE_CALCULATED_SECURITY_SEC_DATA
            +" WHERE security_identifier=? and report_date=?";


    /**
     * Load mapping properties
     * @param path the path of mapping properties
     * @return the mapping properties
     * @throws IOException throws if error to load mapping properties
     */
    private static Properties loadProperties(String path) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = path == null ? ImportExcel.class.getResourceAsStream("/excelMapping.properties"):
        new FileInputStream(path)) {
            properties.load(inputStream);
            return properties;
        }
    }

    /**
     * Get string cell value
     * @param row the row
     * @param cellNum the cell number
     * @return the string cell value
     */
    private static String getStringCellValue(XSSFRow row, int cellNum) {
        XSSFCell cell = row.getCell(cellNum);
        if (cell != null) {
            return cell.getStringCellValue();
        }
        return null;
    }

    /**
     * Get date cell value
     * @param row the row
     * @param cellNum the cell number
     * @return the date cell value
     */
    private static Date getDateCellValue(XSSFRow row, int cellNum) {
        XSSFCell cell = row.getCell(cellNum);
        if (cell != null) {
            return cell.getDateCellValue();
        }
        return null;
    }

    /**
     * Get BigDecimal cell value with rounding configuration
     * @param row the row
     * @param cellNum the cell number
     * @param conf the rounding configuration
     * @return the BigDecimal cell value
     */
    private static BigDecimal getNumericCellValue(XSSFRow row, int cellNum, SECConfiguration conf) {
        XSSFCell cell = row.getCell(cellNum);
        BigDecimal value = null;
        if (cell != null) {
            value = BigDecimal.valueOf(cell.getNumericCellValue());
        }
        if (conf != null && value != null) {
            value = value.setScale(conf.getOperationScale(), conf.getRoundingMode());
        }
        return value;
    }

    /**
     * Get BigDecimal cell value
     * @param row the row
     * @param cellNum the cell number
     * @return the BigDecimal cell value
     */
    private static BigDecimal getNumericCellValue(XSSFRow row, int cellNum) {
        return getNumericCellValue(row, cellNum, null);
    }

    /**
     * Get boolean cell value
     * @param row the row
     * @param cellNum the cell number
     * @return the boolean cell value
     */
    private static boolean getBooleanCellValue(XSSFRow row, int cellNum) {
        XSSFCell cell = row.getCell(cellNum);
        boolean value = false;
        if (cell != null) {
            value = !("F".equals(cell.getStringCellValue()) || "N".equals(cell.getStringCellValue()));
        }
        return value;
    }

    /**
     * Parse security reference data
     * @param row the row
     * @param props the mapping properties
     * @param conf the rounding configurations
     * @return the security reference data
     */
    private static SecurityReferenceData parseSecurityReferenceData(XSSFRow row, Map<String, Integer> props,SECConfiguration conf) {
        SecurityReferenceData securityReferenceData = new SecurityReferenceData();
        securityReferenceData.setSecurityIdentifier(getStringCellValue(row, props.get("securityReferenceData.securityIdentifier")));
        securityReferenceData.setIvType(getStringCellValue(row, props.get("securityReferenceData.ivType")));
        securityReferenceData.setSecurityName(getStringCellValue(row, props.get("securityReferenceData.securityName")));
        securityReferenceData.setFinalMaturityDate(getDateCellValue(row, props.get("securityReferenceData.finalMaturityDate")));
        securityReferenceData.setSecurityRedemptionPrice(getNumericCellValue(row, props.get("securityReferenceData.securityRedemptionPrice"), conf));
        securityReferenceData.setInterestRt(getNumericCellValue(row, props.get("securityReferenceData.interestRt"), conf));
        securityReferenceData.setDefIndicator(getBooleanCellValue(row, props.get("securityReferenceData.defIndicator")));
        securityReferenceData.setDerStepIndicator(getBooleanCellValue(row, props.get("securityReferenceData.derStepIndicator")));
        securityReferenceData.setDerHybridIndicator(getBooleanCellValue(row, props.get("securityReferenceData.derHybridIndicator")));
        securityReferenceData.setIoHybridField(getStringCellValue(row, props.get("securityReferenceData.ioHybridField")));
        securityReferenceData.setAs400RateType(getStringCellValue(row, props.get("securityReferenceData.as400RateType")));
        securityReferenceData.setProspectiveMethod(getStringCellValue(row, props.get("securityReferenceData.prospectiveMethod")));
        return securityReferenceData;
    }

    /**
     * Parse position data
     * @param row the row
     * @param props the mapping properties
     * @param conf the rounding configurations
     * @return the position data
     */
    private static PositionData parsePositionData(XSSFRow row, Map<String, Integer> props,SECConfiguration conf) {
        PositionData positionData = new PositionData();
        positionData.setSecurityIdentifier(getStringCellValue(row, props.get("positionData.securityIdentifier")));
        positionData.setPortfolioNumber(getNumericCellValue(row, props.get("positionData.portfolioNumber"), conf));
        positionData.setPortfolioName(getStringCellValue(row, props.get("positionData.portfolioName")));
        positionData.setReportDate(getDateCellValue(row, props.get("positionData.reportDate")));
        positionData.setEarnedInflationaryCompensationBase(getNumericCellValue(row, props.get("positionData.earnedInflationaryCompensationBase"), conf));
        positionData.setAccruedIncome(getNumericCellValue(row, props.get("positionData.accruedIncome"), conf));
        positionData.setMarketValue(getNumericCellValue(row, props.get("positionData.marketValue"), conf));
        positionData.setShareParAmount(getNumericCellValue(row, props.get("positionData.shareParAmount"), conf));
        positionData.setEarnedAmortizationBase(getNumericCellValue(row, props.get("positionData.earnedAmortizationBase"), conf));
        positionData.setPositionValInflationAdjShares(getNumericCellValue(row, props.get("positionData.positionValInflationAdjShares"), conf));
        return positionData;
    }

    /**
     * Load excel
     * @param path the path of excel
     * @param props the mapping properties
     * @param conf the rounding configurations
     * @return the security sec data map, first key is security identifier, second map key is report date
     * @throws IOException throws if error to parse excel
     */
    private static Map<String, Map<Date, SecuritySECData>> loadExcel(String path, Map<String, Integer> props,SECConfiguration conf) throws IOException {
        Map<String, Map<Date, SecuritySECData>> result = new HashMap<>();
        Map<String, Map<Date, List<SecuritySECData>>> dataMap = new HashMap<>();
        try (InputStream inputStream = new FileInputStream(path)) {
            try (XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {
                int startRow = props.getOrDefault("startRow", 0);
                XSSFSheet sheet = workBook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                for (int i = startRow; i <= lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);
                    SecuritySECData data = new SecuritySECData();
                    String identifier = getStringCellValue(row, props.get("securityIdentifier"));
                    Date reportDate = getDateCellValue(row, props.get("reportDate"));
                    data.setSecurityIdentifier(identifier);
                    data.setReportDate(reportDate);
                    SecurityReferenceData securityReferenceData = parseSecurityReferenceData(row, props,conf);
                    data.setSecurityReferenceData(securityReferenceData);
                    PositionData fundData = parsePositionData(row, props,conf);
                    data.setPositionData(new PositionData[]{fundData});
                    data.setDerTIPSInflationaryRatio(getNumericCellValue(row, props.get("derTIPSInflationaryRatio")));
                    data.setSecurityPrice(getNumericCellValue(row, props.get("securityPrice")));
                    data.setFxRate(getNumericCellValue(row, props.get("fxRate")));
                    dataMap.putIfAbsent(identifier, new HashMap<>());
                    dataMap.get(identifier).putIfAbsent(reportDate, new ArrayList<>());
                    dataMap.get(identifier).get(reportDate).add(data);
                }
            }
        }
        int operationScale = conf.getOperationScale();
        int roundingMode = conf.getRoundingMode();
        dataMap.forEach((i, m) -> {
            m.forEach((r,v)->{
                SecuritySECData firstData = v.get(0);
                int size = v.size();
                BigDecimal bigDecimalSize = BigDecimal.valueOf(size);
                if (size == 1) {
                    result.put(i, Collections.singletonMap(r, firstData));
                } else {
                    SecuritySECData data = new SecuritySECData();
                    data.setSecurityIdentifier(i);
                    data.setReportDate(r);
                    data.setSecurityReferenceData(firstData.getSecurityReferenceData());
                    data.setPositionData(v.stream().map(x -> x.getPositionData()[0]).toArray(PositionData[]::new));
                    data.setDerSecurityType(firstData.getDerSecurityType());
                    data.setDerTIPSInflationaryRatio(v.stream().map(SecuritySECData::getDerTIPSInflationaryRatio).reduce(BigDecimal::add).get().divide(bigDecimalSize, operationScale, roundingMode));
                    data.setSecurityPrice(v.stream().map(SecuritySECData::getSecurityPrice).reduce(BigDecimal::add).get().divide(bigDecimalSize, operationScale, roundingMode));
                    data.setFxRate(v.stream().map(SecuritySECData::getFxRate).reduce(BigDecimal::add).get().divide(bigDecimalSize, operationScale, roundingMode));
                    result.put(i, Collections.singletonMap(r, data));
                }
            });
        });
        return result;
    }

    /**
     * Main function
     * @param args the arguments
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("c", "clean", false, "clean up calculation tables");
        options.addOption("h", "help", false, "print help");
        options.addOption(Option.builder("e").required(true).desc("path for excel to import").hasArg().longOpt("excel").build());
        options.addOption(Option.builder("m").required(false).desc("path for mapping properties of excel").hasArg().longOpt("mapping").build());
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        String path = null;
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                formatter.printHelp(COMMAND_USAGE, "", options, "");
                return;
            }
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-cli.xml");
            context.registerShutdownHook();
            CustomerDataPersistenceService customerDataPersistenceService = context.getBean("customerDataPersistenceServiceImpl", CustomerDataPersistenceService.class);
            SECConfiguration conf = customerDataPersistenceService.getConfiguration();
            PlatformTransactionManager transactionManager = context.getBean("transactionManager", PlatformTransactionManager.class);
            JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
            Properties properties = loadProperties(cmd.getOptionValue("m"));
            path = cmd.getOptionValue("e");
            Map<String, Integer> props = properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> String.valueOf(e.getKey()),
                            e -> Integer.valueOf(String.valueOf(e.getValue()))));
            Map<String, Map<Date, SecuritySECData>> result = loadExcel(path, props, conf);
            Supplier<Stream<SecuritySECData>> dataStreamSupplier = ()-> result.entrySet().stream().map(c->c.getValue().values())
                    .flatMap(Collection::stream);
            // primary key is portfolio_number, report_date
            Map<BigDecimal, Map<Date, List<PositionData>>> positionDataMap =
                    dataStreamSupplier.get()
                            .map(SecuritySECData::getPositionData)
                            .flatMap(Arrays::stream)
                            .collect(Collectors.groupingBy(PositionData::getPortfolioNumber,
                                    Collectors.groupingBy(PositionData::getReportDate)));
            // find all security identifier and report date
            List<Object[]> batchArgs1 =  new ArrayList<>();
            result.forEach((i, m) -> {
                m.forEach((r,v)->{
                    batchArgs1.add(new Object[]{
                            i, r
                    });
                });
            });
            // find all portfolio_number and report date
            List<Object[]> batchArgs2 =  new ArrayList<>();
            positionDataMap.forEach((p, m) -> {
                m.forEach((r,v)->{
                    batchArgs2.add(new Object[]{
                            p, r
                    });
                });
            });
            jdbcTemplate.batchUpdate(DELETE_CUSTOMER_FUND_DATA, batchArgs2);
            jdbcTemplate.batchUpdate(DELETE_CUSTOMER_POSITION_DATA, batchArgs1);
            jdbcTemplate.batchUpdate(DELETE_CUSTOMER_SECURITY_SEC_DATA, batchArgs1);
            if(cmd.hasOption("c")){
                logger.debug("Clean up calculation tables");
                jdbcTemplate.batchUpdate(DELETE_CALCULATED_FUND_DATA, batchArgs2);
                jdbcTemplate.batchUpdate(DELETE_CALCULATED_POSITION_DATA, batchArgs1);
                jdbcTemplate.batchUpdate(DELETE_CALCULATED_SECURITY_SEC_DATA, batchArgs1);
            }
            SimpleJdbcInsert insertFundData = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                    .withTableName(TABLE_CUSTOMER_FUND_DATA);
            SimpleJdbcInsert insertPositionData = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                    .withTableName(CustomerDataPersistenceServiceImpl.TABLE_CUSTOMER_POSITION_DATA);
            SimpleJdbcInsert insertSecuritySECData = new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                    .withTableName(CustomerDataPersistenceServiceImpl.TABLE_CUSTOMER_SECURITY_SEC_DATA);

            TransactionTemplate tt = new TransactionTemplate(transactionManager);
            tt.execute(transactionStatus -> {
                dataStreamSupplier.get().forEach((securitySECData) -> {
                    logger.info(String.format("Persisting Security SEC data with identifier {%s}.",
                            securitySECData.getSecurityIdentifier()));
                    SecurityReferenceData securityReferenceData = securitySECData.getSecurityReferenceData();
                    SqlParameterSource securitySECDataParameters = new MapSqlParameterSource()
                            .addValue(SecuritySECDataColumns.AS_400_RATE_TYPE.getName(),
                                    securityReferenceData.getAs400RateType())
                            .addValue(SecuritySECDataColumns.DEF_INDICATOR.getName(), securityReferenceData.isDefIndicator())
                            .addValue(SecuritySECDataColumns.DER_CLEAN_PRICE.getName(), securitySECData.getDerCleanPrice())
                            .addValue(SecuritySECDataColumns.DER_HYBRID_INDICATOR.getName(),
                                    securityReferenceData.isDerHybridIndicator())
                            .addValue(SecuritySECDataColumns.DER_INCOME_CALC_ENGINE.getName(),
                                    securitySECData.getDerIncomeCalcEngine())
                            .addValue(SecuritySECDataColumns.DER_ONE_DAY_SECURITY_YIELD.getName(),
                                    securitySECData.getDerOneDaySecurityYield())
                            .addValue(SecuritySECDataColumns.DER_REDEMPTION_DATE.getName(),
                                    securitySECData.getDerRedemptionDate())
                            .addValue(SecuritySECDataColumns.DER_REDEMPTION_PRICE.getName(),
                                    securitySECData.getDerRedemptionPrice())
                            .addValue(SecuritySECDataColumns.DER_SECURITY_TYPE.getName(), securitySECData.getDerSecurityType())
                            .addValue(SecuritySECDataColumns.DER_STEP_INDICATOR.getName(),
                                    securityReferenceData.isDerStepIndicator())
                            .addValue(SecuritySECDataColumns.DER_TIPS_INFLATIONARY_RATIO.getName(),
                                    securitySECData.getDerTIPSInflationaryRatio())
                            .addValue(SecuritySECDataColumns.DER_YIELD_CALC_ENGINE.getName(),
                                    securitySECData.getDerYieldCalcEngine())
                            .addValue(SecuritySECDataColumns.FINAL_MATURITY_DATE.getName(),
                                    securityReferenceData.getFinalMaturityDate())
                            .addValue(SecuritySECDataColumns.FX_RATE.getName(), securitySECData.getFxRate())
                            .addValue(SecuritySECDataColumns.INTEREST_RT.getName(), securityReferenceData.getInterestRt())
                            .addValue(SecuritySECDataColumns.IO_HYBRID_FIELD.getName(),
                                    securityReferenceData.getIoHybridField())
                            .addValue(SecuritySECDataColumns.IV_TYPE.getName(), securityReferenceData.getIvType())
                            .addValue(SecuritySECDataColumns.PROSPECTIVE_METHOD.getName(),
                                    securityReferenceData.getProspectiveMethod())
                            .addValue(SecuritySECDataColumns.REPORT_DATE.getName(), securitySECData.getReportDate())
                            .addValue(SecuritySECDataColumns.SECURITY_IDENTIFIER.getName(),
                                    securitySECData.getSecurityIdentifier())
                            .addValue(SecuritySECDataColumns.SECURITY_NAME.getName(), securityReferenceData.getSecurityName())
                            .addValue(SecuritySECDataColumns.SECURITY_PRICE.getName(), securitySECData.getSecurityPrice())
                            .addValue(SecuritySECDataColumns.SECURITY_REDEMPTION_PRICE.getName(),
                                    securityReferenceData.getSecurityRedemptionPrice())
                            .addValue(SecuritySECDataColumns.PROSPECTIVE_METHOD.getName(),
                                    securityReferenceData.getProspectiveMethod());
                    insertSecuritySECData.execute(securitySECDataParameters);
                    final PositionData[] positionData = securitySECData.getPositionData();
                    if (positionData != null) {
                        for (int index = 0; index < positionData.length; index++) {
                            PositionData posData = positionData[index];
                            SqlParameterSource positionDataParameters = new MapSqlParameterSource()
                                    .addValue(PositionDataColumns.SECURITY_IDENTIFIER.getName(),
                                            posData.getSecurityIdentifier())
                                    .addValue(PositionDataColumns.ACCRUED_INCOME.getName(), posData.getAccruedIncome())
                                    .addValue(PositionDataColumns.EARNED_AMORTIZATION_BASE.getName(),
                                            posData.getEarnedAmortizationBase())
                                    .addValue(PositionDataColumns.EARNED_INFLATIONARY_COMPENSATION_BASE.getName(),
                                            posData.getEarnedInflationaryCompensationBase())
                                    .addValue(PositionDataColumns.MARKET_VALUE.getName(), posData.getMarketValue())
                                    .addValue(PositionDataColumns.PORTFOLIO_NUMBER.getName(), posData.getPortfolioNumber())
                                    .addValue(PositionDataColumns.POSITION_VAL_INFLATION_ADJ_SHARES.getName(),
                                            posData.getPositionValInflationAdjShares())
                                    .addValue(PositionDataColumns.REPORT_DATE.getName(), posData.getReportDate())
                                    .addValue(PositionDataColumns.SHARE_PAR_AMOUNT.getName(), posData.getShareParAmount())
                                    .addValue(PositionDataColumns.DER_ONE_DAY_SECURITY_INCOME.getName(),
                                            posData.getDerOneDaySecurityIncome());
                            insertPositionData.execute(positionDataParameters);
                        }
                    }
                });

                positionDataMap.forEach((k, v) -> {
                    v.forEach((d, p) -> {
                        PositionData posData = p.get(0);
                        SqlParameterSource fundDataParameters = new MapSqlParameterSource()
                                .addValue(FundDataColumns.SECURITY_IDENTIFIER.getName(), posData.getSecurityIdentifier())
                                .addValue(FundDataColumns.PORTFOLIO_NUMBER.getName(), posData.getPortfolioNumber())
                                .addValue(FundDataColumns.PORTFOLIO_NAME.getName(), posData.getPortfolioName())
                                .addValue(FundDataColumns.REPORT_DATE.getName(), posData.getReportDate());
                        insertFundData.execute(fundDataParameters);
                    });
                });
                return null;
            });
            System.exit(0);
        } catch (ParseException e) {
            logger.error("command parse error", e);
            formatter.printHelp(COMMAND_USAGE, "", options, "");
        } catch (NumberFormatException e) {
            logger.error("Error to parse excelMapping.properties", e);
        } catch (FileNotFoundException e) {
            logger.error("Not found excel file " + path, e);
        } catch (IOException e) {
            logger.error("Error to process excel " + path, e);
        } catch (Exception e) {
            logger.error("Error to persist security sec data", e);
        }
    }
}
