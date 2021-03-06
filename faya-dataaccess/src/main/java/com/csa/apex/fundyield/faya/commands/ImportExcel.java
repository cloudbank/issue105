/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.faya.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import com.csa.apex.fundyield.utility.Constants;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import com.csa.apex.fundyield.faya.api.service.impl.StoredProcedureHelper;
import com.csa.apex.fundyield.fayacommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.fayacommons.entities.Instrument;
import com.csa.apex.fundyield.fayacommons.entities.InstrumentTypeCode;
import com.csa.apex.fundyield.fayacommons.entities.Portfolio;
import com.csa.apex.fundyield.fayacommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.fayacommons.entities.SECConfiguration;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntity;
import com.csa.apex.fundyield.fayacommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.DateUtility;

/**
 * Import data from excel into DB.
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ImportExcel {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(ImportExcel.class);

    /**
     * The command usage text.
     */
    private static final String COMMAND_USAGE = "ImportExcel [-c/--clean][-h/--help][-e/--excel][-m/--mapping]";

    /**
     * Represents the EXCEL_IMPORTOR user.
     */
    private static final String EXCEL_IMPORTOR = "EXCEL_IMPORTOR";

    /**
     * Private constructor.
     */
    private ImportExcel() {
        // Empty
    }

    /**
     * Get string cell value.
     * @param row the row
     * @param cellNum the cell number
     * @return the string cell value
     */
    private static String getStringCellValue(XSSFRow row, int cellNum) {
        XSSFCell cell = row.getCell(cellNum);
        return cell != null ? StringUtils.trimToNull(cell.getStringCellValue()) : null;
    }

    /**
     * Get date cell value.
     * @param row the row
     * @param cellNum the cell number
     * @return the date cell value
     */
    private static Date getDateCellValue(XSSFRow row, int cellNum) {
        XSSFCell cell = row.getCell(cellNum);
        return cell != null ? cell.getDateCellValue() : null;
    }

    /**
     * Get BigDecimal cell value with rounding configuration.
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
     * Get BigDecimal cell value.
     * @param row the row
     * @param cellNum the cell number
     * @return the BigDecimal cell value
     */
    private static BigDecimal getNumericCellValue(XSSFRow row, int cellNum) {
        return getNumericCellValue(row, cellNum, null);
    }

    /**
     * Parse Instrument data.
     * @param row the row
     * @param columnMapping the mapping properties
     * @param conf the rounding configurations
     * @return the Instrument data
     */
    private static Instrument parseInstrument(XSSFRow row, Map<String, Integer> columnMapping, SECConfiguration conf) {

        Instrument instrument = new Instrument();
        instrument.setInstrumentId(getNumericCellValue(row, columnMapping.get("instrument.id")).longValue());
        instrument.setInstrumentTypeCode(Enum.valueOf(InstrumentTypeCode.class,
                getStringCellValue(row, columnMapping.get("instrument.instrumentTypeCode"))));
        instrument.setInstrumentShortName(getStringCellValue(row, columnMapping.get("instrument.instrumentShortName")));
        instrument.setFinalMaturityDate(getDateCellValue(row, columnMapping.get("instrument.finalMaturityDate")));
        instrument.setMaturityPrc(getNumericCellValue(row, columnMapping.get("instrument.maturityPrc"), conf));
        instrument.setDefaultInd(getStringCellValue(row, columnMapping.get("instrument.defaultInd")));
        instrument.setFdrStepBondInd(getStringCellValue(row, columnMapping.get("instrument.fdrStepBondInd")));
        instrument.setHybridCalculationCd(getStringCellValue(row, columnMapping.get("instrument.hybridCalculationCd")));
        instrument.setCouponRateTypeCode(getStringCellValue(row, columnMapping.get("instrument.couponRateTypeCode")));
        instrument.setProspectiveYieldMethodCd(
                getStringCellValue(row, columnMapping.get("instrument.prospectiveYieldMethodCd")));

        instrument.setCreateId(EXCEL_IMPORTOR);
        return instrument;
    }

    /**
     * Parse portfolio data.
     * @param row the row
     * @param columnMapping the mapping properties
     * @return the portfolio data
     */
    private static Portfolio parsePortfolio(XSSFRow row, Map<String, Integer> columnMapping) {
        Portfolio portfolio = new Portfolio();
        portfolio.setPortfolioId(getNumericCellValue(row, columnMapping.get("portfolio.portfolioId")).longValue());
        portfolio.setPortfolioShortName(getStringCellValue(row, columnMapping.get("portfolio.portfolioShortName")));

        portfolio.setCreateId(EXCEL_IMPORTOR);
        return portfolio;
    }

    /**
     * Parse portfolio holding snapshot data.
     * @param row the row
     * @param columnMapping the mapping properties
     * @param conf the rounding configurations
     * @return the portfolio holding snapshot data
     */
    private static PortfolioHoldingSnapshot parsePortfolioHoldingSnapshot(XSSFRow row,
            Map<String, Integer> columnMapping, SECConfiguration conf) {
        PortfolioHoldingSnapshot holding = new PortfolioHoldingSnapshot();

        holding.setFxRt(getNumericCellValue(row, columnMapping.get("portfolioHoldingSnapshot.fxRt"), conf));
        holding.setEarnedInflCmpsBaseAmt(
                getNumericCellValue(row, columnMapping.get("portfolioHoldingSnapshot.earnedInflCmpsBaseAmt"), conf));
        holding.setAccruedIncomeAmt(
                getNumericCellValue(row, columnMapping.get("portfolioHoldingSnapshot.accruedIncomeAmt"), conf));
        holding.setMarketValueBaseAmt(
                getNumericCellValue(row, columnMapping.get("portfolioHoldingSnapshot.marketValueBaseAmt"), conf));
        holding.setSettleShareCnt(
                getNumericCellValue(row, columnMapping.get("portfolioHoldingSnapshot.settleShareCnt"), conf));
        holding.setEarnedAmortBaseAmt(
                getNumericCellValue(row, columnMapping.get("portfolioHoldingSnapshot.earnedAmortBaseAmt"), conf));
        holding.setInflationAdjShareCnt(
                getNumericCellValue(row, columnMapping.get("portfolioHoldingSnapshot.inflationAdjShareCnt"), conf));

        // Default value
        holding.setHoldingBusinessGroupViewCd("FA");
        holding.setHoldingViewCd("EOD");
        holding.setPositionCd("LONG");
        holding.setLastAdjUserId(EXCEL_IMPORTOR);
        holding.setLastAdjTs(new Date());
        holding.setLastAdjApproverUserId(EXCEL_IMPORTOR);
        holding.setCreateId(EXCEL_IMPORTOR);
        return holding;
    }

    /**
     * Parse tradable entity data.
     * @return the tradable entity data
     */
    private static TradableEntity parseTradableEntity() {
        TradableEntity entity = new TradableEntity();
        entity.setTradableEntityId(1); // Currently not in excel
        entity.setCreateId(EXCEL_IMPORTOR);
        return entity;
    }

    /**
     * Parse tradable entity snapshot data.
     * @param row the row
     * @param columnMapping the mapping properties
     * @param conf the rounding configurations
     * @return the tradable entity snapshot data
     */
    private static TradableEntitySnapshot parseTradableEntitySnapshot(XSSFRow row, Map<String, Integer> columnMapping,
            SECConfiguration conf) {
        TradableEntitySnapshot entity = new TradableEntitySnapshot();

        entity.setCurrentIncomeRate(
                getNumericCellValue(row, columnMapping.get("tradableEntitySnapshot.currentIncomeRate"), conf));
        entity.setMarketPrice(getNumericCellValue(row, columnMapping.get("tradableEntitySnapshot.marketPrice"), conf));
        entity.setFdrTipsInflationaryRatio(
                getNumericCellValue(row, columnMapping.get("tradableEntitySnapshot.fdrTipsInflationaryRatio"), conf));

        entity.setLastAdjUserId(EXCEL_IMPORTOR);
        entity.setLastAdjTs(new Date());
        entity.setLastAdjApproverUserId(EXCEL_IMPORTOR);
        entity.setCreateId(EXCEL_IMPORTOR);

        return entity;
    }

    /**
     * Load excel.
     * @param path The path to excel file
     * @param columnMapping The column mapping
     * @param conf The configuration
     * @return List of FundAccountingYieldData
     * @throws IOException if I/O error occurs
     */
    private static List<FundAccountingYieldData> loadExcel(String path, Map<String, Integer> columnMapping,
            SECConfiguration conf) throws IOException {
        List<FundAccountingYieldData> result = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream(path)) {
            try (XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {
                int startRow = columnMapping.getOrDefault("startRow", 0);
                XSSFSheet sheet = workBook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                for (int i = startRow; i <= lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);

                    // Parse excel row
                    Instrument instrument = parseInstrument(row, columnMapping, conf);
                    TradableEntity tradableEntity = parseTradableEntity();
                    TradableEntitySnapshot tes = parseTradableEntitySnapshot(row, columnMapping, conf);
                    Portfolio portfolio = parsePortfolio(row, columnMapping);
                    PortfolioHoldingSnapshot holding = parsePortfolioHoldingSnapshot(row, columnMapping, conf);

                    Date reportDate = DateUtility.convertToSqlDate(getDateCellValue(row, columnMapping.get(Constants.REPORT_DATE)));

                    // Build FundAccountingYieldData for each row
                    FundAccountingYieldData data = new FundAccountingYieldData();
                    data.setReportDate(reportDate);
                    data.setInstruments(Arrays.asList(instrument));
                    data.setPortfolios(Arrays.asList(portfolio));

                    tes.setReportDate(reportDate);
                    tes.setDerRedemptionDate(instrument.getFinalMaturityDate());

                    tradableEntity.setTradableEntitySnapshots(Arrays.asList(tes));
                    instrument.setTradableEntities(Arrays.asList(tradableEntity));

                    holding.setReportDate(reportDate);
                    holding.setTradableEntity(tradableEntity);
                    holding.setTradableEntityId(tradableEntity.getTradableEntityId());
                    portfolio.setPortfolioHoldings(Arrays.asList(holding));

                    result.add(data);
                }
            }
        }
        return result;
    }

    /**
     * Load SEC configuration.
     * @return SEC configuration.
     * @throws IOException if I/O error occurs
     */
    private static SECConfiguration loadSECConfiguration() throws IOException {

        SECConfiguration secConfiguration = new SECConfiguration();
        String secConfigurationFileName = "constants.properties";
        try (InputStream inputStream = ImportExcel.class.getClassLoader()
                .getResourceAsStream(secConfigurationFileName)) {
            if (inputStream == null) {
                throw new FileNotFoundException(
                        "SECConfiguration file '" + secConfigurationFileName + "' not found in the classpath");
            }
            Properties prop = new Properties();
            prop.load(inputStream);
            secConfiguration.setOperationScale(Integer.parseInt(prop.getProperty("operationScale")));
            secConfiguration.setRoundingMode(Integer.parseInt(prop.getProperty("roundingMode")));
        }
        return secConfiguration;
    }

    /**
     * Load excel column mapping.
     * @param path the path of mapping properties file
     * @return the excel column mapping
     * @throws IOException throws if error to load mapping
     */
    private static Map<String, Integer> loadColumnMapping(String path) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = path == null ? ImportExcel.class.getResourceAsStream("/excelMapping.properties")
                : new FileInputStream(path)) {
            properties.load(inputStream);
        }

        return properties.entrySet().stream().collect(
                Collectors.toMap(e -> String.valueOf(e.getKey()), e -> Integer.valueOf(String.valueOf(e.getValue()))));
    }

    /**
     * Main function.
     * @param args the arguments
     */
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("c", "clean", false, "clean up calculation results");
        options.addOption("h", "help", false, "print help");
        options.addOption("e", "excel", true, "path for excel to import");
        options.addOption("m", "mapping", true, "path for mapping properties of excel");
        options.addOption("x", "xml", true, "path for spring xml config file");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        String path = null;
        try {
            // Parse command line arguments
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                formatter.printHelp(COMMAND_USAGE, "", options, "");
                return;
            }

            path = cmd.getOptionValue("e");

            if (StringUtils.isBlank(path)) {
                throw new ParseException("Missing required option: excel");
            }

            // Load SECConfiguration
            SECConfiguration secConfiguration = loadSECConfiguration();

            // Load excel column mapping
            Map<String, Integer> columnMapping = loadColumnMapping(cmd.getOptionValue("m"));

            String xmlPath = cmd.getOptionValue("x");

            // Load Spring context
            try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    xmlPath == null ? "applicationContext-cli.xml" : xmlPath)) {
                context.registerShutdownHook();
                PlatformTransactionManager transactionManager = context.getBean("transactionManager",
                        PlatformTransactionManager.class);
                StoredProcedureHelper storedProcedureHelper = context.getBean("storedProcedureHelper", StoredProcedureHelper.class);

                // Load excel
                List<FundAccountingYieldData> result = loadExcel(path, columnMapping, secConfiguration);

                // Should clean up calculation results
                boolean cleanCalcResult = cmd.hasOption("c");

                // Save data
                result.forEach(data -> saveData(data, transactionManager, storedProcedureHelper, cleanCalcResult));
            }
        } catch (ParseException e) {
            LOGGER.error("command parse error", e);
            formatter.printHelp(COMMAND_USAGE, "", options, "");
        } catch (NumberFormatException e) {
            LOGGER.error("Error to parse excelMapping.properties", e);
        } catch (IOException e) {
            LOGGER.error("Error to process excel " + path, e);
        } catch (Exception e) {
            LOGGER.error("Error to persist security sec data", e);
        }
    }

    /**
     * Save data.
     * @param fundAccountingYieldData The FundAccountingYieldData to save
     * @param transactionManager The transactionManager
     * @param cleanCalcResult Flag indicating whether should clean up calculation results
     */
    private static void saveData(FundAccountingYieldData fundAccountingYieldData, PlatformTransactionManager transactionManager,
            StoredProcedureHelper storedProcedureHelper, boolean cleanCalcResult) {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
		if (fundAccountingYieldData.getInstruments() != null) {
            tt.execute(transactionStatus -> {
                storedProcedureHelper.saveInstrument(fundAccountingYieldData.getInstruments().get(0));

                TradableEntity te = null;
                if (fundAccountingYieldData.getInstruments().get(0).getTradableEntities() != null
                        && !fundAccountingYieldData.getInstruments().get(0).getTradableEntities().isEmpty()) {
                    te = fundAccountingYieldData.getInstruments().get(0).getTradableEntities().get(0);
                    te.setInstrumentSid(fundAccountingYieldData.getInstruments().get(0).getInstrumentSid());
                    storedProcedureHelper.saveTradableEntity(te);
                }

                if (te != null) {
                    if (te.getTradableEntitySnapshots() != null && !te.getTradableEntitySnapshots().isEmpty()) {
                        TradableEntitySnapshot tes = te.getTradableEntitySnapshots().get(0);
                        tes.setTradableEntitySid(te.getTradableEntitySid());
                        storedProcedureHelper.saveTradableEntitySnapshot(tes, cleanCalcResult);
                    }
                }

                if (fundAccountingYieldData.getPortfolios() != null) {
                    storedProcedureHelper.savePortfolio(fundAccountingYieldData.getPortfolios().get(0));
                }

                if (fundAccountingYieldData.getPortfolios() != null && !fundAccountingYieldData.getPortfolios().isEmpty()) {
                    List<PortfolioHoldingSnapshot> portfolioHoldings = fundAccountingYieldData.getPortfolios().get(0)
                            .getPortfolioHoldings();
                    if (portfolioHoldings != null && !portfolioHoldings.isEmpty()) {
                        PortfolioHoldingSnapshot holding = portfolioHoldings.get(0);
                        holding.setPortfolioSid(fundAccountingYieldData.getPortfolios().get(0).getPortfolioSid());
                        if (te != null) {
                            holding.setTradableEntitySid(te.getTradableEntitySid());
                        }
                        storedProcedureHelper.savePortfolioHoldingSnapshot(holding, cleanCalcResult);
                    }
                }

                LOGGER.info("Row imported:\n" + fundAccountingYieldData.toString());
                return null;
            });
    }
}
}
