/*
 * Copyright (c) 2016 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.secyield.api.services.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.secyield.api.services.SECYieldService;
import com.csa.apex.secyield.api.engines.impl.CalculationEngineSelector;
import com.csa.apex.secyield.entities.PositionData;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecurityReferenceData;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.ConfigurationException;
import com.csa.apex.secyield.exceptions.SECYieldException;
import com.csa.apex.secyield.utility.CommonUtility;
import com.csa.apex.secyield.utility.Constants;

/**
 * SECYieldServiceImpl implementation of the SECYieldService. Uses pluggable calculation engines to calculate the
 * security SEC data like yield and income. This class is effectively thread safe.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.1
 */
public class SECYieldServiceImpl implements SECYieldService {

	/**
	 * logger class instance
	 */
	private final Logger logger = Logger.getLogger(SECYieldServiceImpl.class);

	/**
	 * CalcultionEngineSelector object
	 */
	private CalculationEngineSelector calculationEngineSelector;

	/**
	 * Error log message format
	 */
	@Value("${messages.errorlogmessage}")
	private String logErrorFormat;

	/**
	 * Configuration exception message
	 */
	@Value("${messages.configurationargumentexception}")
	private String configurationArgumentExceptionMessage;

	/**
	 * The REST API path to the endpoint used to obtain calc engine configurations. After injection should be non-null
	 * and non-empty.
	 */
	private String getConfigApiPath;

	/**
	 * The REST API path to the endpoint used to get security SEC data. After injection should be non-null and
	 * non-empty.
	 */
	private String getCustomerDataApiPath;

	/**
	 * The REST API path to the endpoint used to save calculated security SEC data. After injection should be non-null
	 * and non-empty.
	 */
	private String saveCalculatedSecuritySECDataApiPath;

	/**
	 * The REST API path to the endpoint used to get already calculated security SEC data. After injection should be
	 * non-null and non-empty.
	 */
	private String getCalculatedSecuritySECDataApiPath;

	/**
	 * Creating restTemplate object helps in mock testing
	 * 
	 */
	private RestTemplate restTemplate;

	/**
	 * Configuration object for the service
	 */
	private SECConfiguration configuration;

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.illegalargumentexception}")
	private String illegalArgumentExceptionMessage;

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.secyieldexception}")
	private String secYieldExceptionMessage;

	/**
	 * Unsupported operation exception message
	 */
	@Value("${messages.unsupportedoperationexception}")
	private String unSupportedOperationException;

	/**
	 * Customer API exception message
	 */
	@Value("${messages.customerapiexception}")
	private String customerApiException;

	/**
	 * processSingleSecurity method name
	 */
	@Value("${secyieldserviceimpl.processsinglesecuritymethodname}")
	private String processingSingeSecurityMethodName;

	/**
	 * exportCalculatedSecuritySECData method name
	 */
	@Value("${secyieldserviceimpl.exportCalculatedSecuritySECDataMethodName}")
	private String exportCalculatedSecuritySECDataMethodName;

	/**
	 * processSecuritySECData method name
	 */
	@Value("${secyieldserviceimpl.processSecuritySECDataMethodName}")
	private String processSecuritySECDataMethodName;

	/**
	 * The field names to be exported
	 */
	private static final String[] EXPORT_FIELD_NAMES = new String[] { "securityIdentifier", "reportDate",
			"derCleanPrice", "derYieldCalcEngine", "derIncomeCalcEngine", "derOneDaySecurityYield", "derRedemptionDate",
			"derRedemptionPrice", "derSecurityType", "derTIPSInflationaryRatio", "securityPrice", "fxRate", "ivType",
			"securityName", "finalMaturityDate", "securityRedemptionPrice", "interestRt", "defIndicator",
			"derStepIndicator", "derHybridIndicator", "ioHybridField", "as400RateType", "prospectiveMethod",
			"portfolioNumber", "portfolioName", "earnedInflationaryCompensationBase", "accruedIncome", "marketValue",
			"shareParAmount", "earnedAmortizationBase", "positionValInflationAdjShares", "derOneDaySecurityIncome" };

	/**
	 * Checks error in parallel processing
	 */
	private boolean isProcessed = true;

	/**
	 * The API date format
	 */
	private SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.API_DATE_FORMAT);

	/**
	 * The value to be exported for null values
	 */
	private static final String NULL_CSV_VALUE = "null";

	/**
	 * The export file type
	 */
	private static final String EXPORT_FILE_TYPE = "application/zip";

	/**
	 * The CSV extension
	 */
	private static final String CSV_EXTENSION = ".csv";

	/**
	 * The ZIP extension
	 */
	private static final String ZIP_EXTENSION = ".zip";

	/**
	 * The export file name prefix
	 */
	private static final String EXPORT_FILE_NAME_PREFIX = "export-";

	/**
	 * Constructor
	 */
	public SECYieldServiceImpl() {
		// default constructor
	}

	/**
	 * Getter configuration
	 * 
	 * @return configuration
	 */
	public SECConfiguration getConfiguration() throws SECYieldException {
		if (configuration == null) {
			try {
				configuration = restTemplate.getForObject(getConfigApiPath, SECConfiguration.class);
			} catch(Exception e) {
				logger.error(String.format(customerApiException, e.getMessage()));
				throw new SECYieldException(e.getMessage(), e);
			}
		}
		return configuration;
	}

	/**
	 * Setter configuration
	 * 
	 * @param configuration
	 */
	public void setConfiguration(SECConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Getter calculationEngineSelector
	 * 
	 * @return calculationEngineSelector
	 */
	public CalculationEngineSelector getCalculationEngineSelector() {
		return calculationEngineSelector;
	}

	/**
	 * Setter calculationEngineSelector
	 * 
	 * @param calculationEngineSelector
	 */
	public void setCalculationEngineSelector(CalculationEngineSelector calculationEngineSelector) {
		this.calculationEngineSelector = calculationEngineSelector;
	}

	/**
	 * Getter getConfigApiPath
	 * 
	 * @return getConfigApiPath
	 */
	public String getGetConfigApiPath() {
		return getConfigApiPath;
	}

	/**
	 * Setter getConfigApiPath
	 * 
	 * @param getConfigApiPath
	 */
	public void setGetConfigApiPath(String getConfigApiPath) {
		this.getConfigApiPath = getConfigApiPath;
	}

	/**
	 * Getter getCustomerDataApiPath
	 * 
	 * @return getCustomerDataApiPath
	 */
	public String getGetCustomerDataApiPath() {
		return getCustomerDataApiPath;
	}

	/**
	 * Setter getCustomerDataApiPath
	 * 
	 * @param getCustomerDataApiPath
	 */
	public void setGetCustomerDataApiPath(String getCustomerDataApiPath) {
		this.getCustomerDataApiPath = getCustomerDataApiPath;
	}

	/**
	 * Getter saveCalculatedSecuritySECDataApiPath
	 * 
	 * @return saveCalculatedSecuritySECDataApiPath
	 */
	public String getSaveCalculatedSecuritySECDataApiPath() {
		return saveCalculatedSecuritySECDataApiPath;
	}

	/**
	 * Setter saveCalculatedSecuritySECDataApiPath
	 * 
	 * @param saveCalculatedSecuritySECDataApiPath
	 */
	public void setSaveCalculatedSecuritySECDataApiPath(String saveCalculatedSecuritySECDataApiPath) {
		this.saveCalculatedSecuritySECDataApiPath = saveCalculatedSecuritySECDataApiPath;
	}

	/**
	 * Getter getCalculatedSecuritySECDataApiPath
	 * 
	 * @return getCalculatedSecuritySECDataApiPath
	 */
	public String getGetCalculatedSecuritySECDataApiPath() {
		return getCalculatedSecuritySECDataApiPath;
	}

	/**
	 * Setter getCalculatedSecuritySECDataApiPath
	 * 
	 * @param getCalculatedSecuritySECDataApiPath
	 */
	public void setGetCalculatedSecuritySECDataApiPath(String getCalculatedSecuritySECDataApiPath) {
		this.getCalculatedSecuritySECDataApiPath = getCalculatedSecuritySECDataApiPath;
	}

	/**
	 * Getter restTemplate
	 * 
	 * @return restTemplate
	 */
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	/**
	 * Setter restTemplate
	 * 
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Checks beans are injected properly on post construct throws ConfigurationException
	 */
	@PostConstruct
	protected void checkConfiguration() {
		if (calculationEngineSelector == null) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}

		if (getConfigApiPath == null || getConfigApiPath.isEmpty())
			throw new ConfigurationException(configurationArgumentExceptionMessage);

		if (getCustomerDataApiPath == null || getCustomerDataApiPath.isEmpty())
			throw new ConfigurationException(configurationArgumentExceptionMessage);

		if (getCalculatedSecuritySECDataApiPath == null || getCalculatedSecuritySECDataApiPath.isEmpty())
			throw new ConfigurationException(configurationArgumentExceptionMessage);
	}

	/**
	 * Process SEC Security data for the business data. This method gets the securities and then process each security
	 * first to calculate the data, then to persist it using API.
	 * 
	 * @param businessDate
	 *            businessDate of the security
	 * @return List<SecuritySECData> list of SecuritySECData for the date
	 * @throws SECYieldException
	 */
	@Override
	public List<SecuritySECData> processSecuritySECData(Date businessDate) throws SECYieldException {
	    if (CommonUtility.checkBusinessDateInValid(businessDate)) {
            logger.error(String.format(logErrorFormat, "processSecuritySECData",
                    illegalArgumentExceptionMessage));
            throw new IllegalArgumentException(illegalArgumentExceptionMessage);
        }
		List<SecuritySECData> failedSecuritySECDataList = new ArrayList<SecuritySECData>();
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getCustomerDataApiPath)
					.queryParam("businessDate", dateFormat.format(businessDate));

			SecuritySECData[] securitiesArray = restTemplate.getForObject(builder.build().encode().toUri(),
					SecuritySECData[].class);

			List<SecuritySECData> securities = Arrays.asList(securitiesArray);

			securities.parallelStream().forEach(s -> {
				try {
					processSingleSecurity(s);
				} catch (SECYieldException e) {
					logger.error(String.format(logErrorFormat, processSecuritySECDataMethodName, e.getMessage()), e);
					failedSecuritySECDataList.add(s);
					isProcessed = false;
				}
			});

			// if there is any error in parallel processing
			// throw SECYieldException
			if (!isProcessed) {
				isProcessed = true;
				throw new SECYieldException(secYieldExceptionMessage);
			}

			return securities;
		} catch (SECYieldException e) {
			logger.error(String.format(logErrorFormat, processSecuritySECDataMethodName, e.getMessage() + ", URL:" + saveCalculatedSecuritySECDataApiPath));
			if(!failedSecuritySECDataList.isEmpty()) logger.info(failedSecuritySECDataList);
			throw e;
		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, processSecuritySECDataMethodName, e.getMessage() + ", URL:" + saveCalculatedSecuritySECDataApiPath));
			if(!failedSecuritySECDataList.isEmpty()) logger.info(failedSecuritySECDataList);
			throw new SECYieldException(e.getMessage(), e);
		}

	}

	/**
	 * Gets already calculated SEC Security data for the given date.
	 *
	 * @param businessDate
	 *            businessDate of the security
	 * @return List<SecuritySECData> list of SecuritySECData for the date
	 * @throws SECYieldException
	 */
	@Override
	public List<SecuritySECData> getCalculatedSecuritySECData(Date businessDate) throws SECYieldException {
	    if (CommonUtility.checkBusinessDateInValid(businessDate)) {
            logger.error(String.format(logErrorFormat, "getCalculatedSecuritySECData",
                    illegalArgumentExceptionMessage));
            throw new IllegalArgumentException(illegalArgumentExceptionMessage);
        }
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getCalculatedSecuritySECDataApiPath)
					.queryParam("businessDate", dateFormat.format(businessDate));

			SecuritySECData[] securitiesArray = restTemplate.getForObject(builder.build().encode().toUri(),
					SecuritySECData[].class);

			return Arrays.asList(securitiesArray);
		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, "getCalculatedSecuritySECData", e.getMessage()));
			throw new SECYieldException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * method is used to process each security, first to calculate the data, then to persist it using API.
	 * 
	 * @param securitySECData
	 *            the input securitySECData that is updated in the method.
	 * @throws SECYieldException
	 */
	private void processSingleSecurity(SecuritySECData securitySECData) throws SECYieldException {
		// checks passed value should not be null
		if (securitySECData == null) {
			logger.error(
					String.format(logErrorFormat, processingSingeSecurityMethodName, illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		try {
			// call engine calculator
			SecuritySECData updatedSecuritySECData = calculationEngineSelector.calculate(securitySECData,
					getConfiguration());
			// calculate the data
			if ("VPS".equalsIgnoreCase(updatedSecuritySECData.getSecurityReferenceData().getIvType())) {
				updatedSecuritySECData = calculationEngineSelector.getCalculationEngines().get("YTM")
						.calculate(updatedSecuritySECData, getConfiguration());
			} else if ("VRDN".equalsIgnoreCase(updatedSecuritySECData.getSecurityReferenceData().getIvType())
					|| "DVRN".equalsIgnoreCase(updatedSecuritySECData.getSecurityReferenceData().getIvType())) {
				updatedSecuritySECData = calculationEngineSelector.getCalculationEngines().get("COUPON")
						.calculate(updatedSecuritySECData, getConfiguration());
			} else {
				throw new UnsupportedOperationException(unSupportedOperationException);
			}
			// call the api
			Boolean saveResponse;

			saveResponse = restTemplate.exchange(saveCalculatedSecuritySECDataApiPath, HttpMethod.PUT,
					new HttpEntity<SecuritySECData>(updatedSecuritySECData), Boolean.class).getBody();
			// if response false
			if (!saveResponse) {
				logger.error(
						String.format(logErrorFormat, processingSingeSecurityMethodName, secYieldExceptionMessage));
				throw new SECYieldException(secYieldExceptionMessage);
			}
		} catch (UnsupportedOperationException | SECYieldException e) {
			logger.error(String.format(logErrorFormat, processingSingeSecurityMethodName, e.getMessage()));
			throw e;
		} catch (Exception e) {
			// add throw cause for exception chaining
			logger.error(String.format(logErrorFormat, processingSingeSecurityMethodName, e.getMessage()));
			throw new SECYieldException(secYieldExceptionMessage, e);
		}
	}

	/**
	 * Exports SEC Security data in CSV format in an archive.
	 * 
	 * @param businessDate
	 *            the business date;
	 * @param response
	 *            the http response
	 * @throws IllegalArgumentException
	 *             input is invalid
	 * @throws SECYieldException
	 *             any error during processing
	 */
	@Override
	public void exportCalculatedSecuritySECData(Date businessDate, HttpServletResponse response)
			throws IllegalArgumentException, SECYieldException {
	    if (CommonUtility.checkBusinessDateInValid(businessDate)) {
            logger.error(String.format(logErrorFormat, exportCalculatedSecuritySECDataMethodName,
                    illegalArgumentExceptionMessage));
            throw new IllegalArgumentException(illegalArgumentExceptionMessage);
        }
		try {
			final List<SecuritySECData> calculatedSecuritySECData = getCalculatedSecuritySECData(businessDate);
			createExportArchive(response,
					calculatedSecuritySECData.toArray(new SecuritySECData[calculatedSecuritySECData.size()]),
					dateFormat.format(businessDate));
		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, exportCalculatedSecuritySECDataMethodName, e.getMessage()));
			throw new SECYieldException(e.getMessage(), e);
		}
	}

	/**
	 * Create the export zip file
	 * 
	 * @param response
	 *            the http response to which the archive will be written
	 * @param securitiesArray
	 *            the array of security SEC data
	 * @param businessDate
	 *            the business date string
	 * @throws IOException
	 *             if any IO exception occurs or if the CSV file is not found
	 */
	private void createExportArchive(HttpServletResponse response, SecuritySECData[] securitiesArray,
			String businessDate) throws IOException {

		String exportArchiveFileName = EXPORT_FILE_NAME_PREFIX + businessDate + ZIP_EXTENSION;
		String csvFileName = EXPORT_FILE_NAME_PREFIX + businessDate + CSV_EXTENSION;

		response.addHeader("Content-disposition", "attachment;filename=" + exportArchiveFileName);
		response.setContentType(EXPORT_FILE_TYPE);

		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		zos.putNextEntry(new ZipEntry(csvFileName));

		zos.write((Arrays.stream(EXPORT_FIELD_NAMES).collect(Collectors.joining(",")) + "\n").getBytes());

		for (SecuritySECData securitySECData : securitiesArray) {
			final PositionData[] positionData = securitySECData.getPositionData();
			if (positionData != null) {
				for (PositionData item : positionData) {
					String[] csvColumns = convertPositionDataToCSVRow(securitySECData, item);
					String csvLine = Arrays.stream(csvColumns).collect(Collectors.joining(",")) + "\n";
					zos.write(csvLine.getBytes());
				}
			}
		}
		zos.closeEntry();
		zos.close();
	}

	/**
	 * Convert the position data to an array of position data field values
	 * 
	 * @param securitySECData
	 *            the security SEC data
	 * @param positionData
	 *            the position data
	 * @return the array of position data field values
	 */
	private String[] convertPositionDataToCSVRow(SecuritySECData securitySECData, PositionData positionData) {
		String[] values = new String[EXPORT_FIELD_NAMES.length];
		int index = 0;
		values[index++] = securitySECData.getSecurityIdentifier();
		values[index++] = getFormattedDate(securitySECData.getReportDate());
		values[index++] = String.valueOf(securitySECData.getDerCleanPrice());
		values[index++] = securitySECData.getDerYieldCalcEngine();
		values[index++] = securitySECData.getDerIncomeCalcEngine();
		values[index++] = String.valueOf(securitySECData.getDerOneDaySecurityYield());
		values[index++] = getFormattedDate(securitySECData.getDerRedemptionDate());
		values[index++] = String.valueOf(securitySECData.getDerRedemptionPrice());
		values[index++] = securitySECData.getDerSecurityType();
		values[index++] = String.valueOf(securitySECData.getDerTIPSInflationaryRatio());
		values[index++] = String.valueOf(securitySECData.getSecurityPrice());
		values[index++] = String.valueOf(securitySECData.getFxRate());

		SecurityReferenceData securityReferenceData = securitySECData.getSecurityReferenceData();

		values[index++] = securityReferenceData.getIvType();
		values[index++] = securityReferenceData.getSecurityName();
		values[index++] = getFormattedDate(securityReferenceData.getFinalMaturityDate());
		values[index++] = String.valueOf(securityReferenceData.getSecurityRedemptionPrice());
		values[index++] = String.valueOf(securityReferenceData.getInterestRt());
		values[index++] = String.valueOf(securityReferenceData.isDefIndicator());
		values[index++] = String.valueOf(securityReferenceData.isDerStepIndicator());
		values[index++] = String.valueOf(securityReferenceData.isDerHybridIndicator());
		values[index++] = securityReferenceData.getIoHybridField();
		values[index++] = securityReferenceData.getAs400RateType();
		values[index++] = securityReferenceData.getProspectiveMethod();

		values[index++] = String.valueOf(positionData.getPortfolioNumber());
		values[index++] = positionData.getPortfolioName();
		values[index++] = String.valueOf(positionData.getEarnedInflationaryCompensationBase());
		values[index++] = String.valueOf(positionData.getAccruedIncome());
		values[index++] = String.valueOf(positionData.getMarketValue());
		values[index++] = String.valueOf(positionData.getShareParAmount());
		values[index++] = String.valueOf(positionData.getEarnedAmortizationBase());
		values[index++] = String.valueOf(positionData.getPositionValInflationAdjShares());
		values[index] = String.valueOf(positionData.getDerOneDaySecurityIncome());

		return values;
	}

	/**
	 * Retrieve formatted date
	 * 
	 * @param date
	 *            the date to be formatted
	 * @return the formatted date
	 */
	private String getFormattedDate(Date date) {
		return date != null ? dateFormat.format(date) : NULL_CSV_VALUE;
	}

}
