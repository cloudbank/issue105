/*
 * Copyright (c) 2017 TopCoder, Inc. All rights reserved.
 */
package com.csa.apex.fundyield.api.services.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.fundyield.api.services.SecuritySECYieldService;
import com.csa.apex.fundyield.api.services.impl.securitysecyield.CalculationEngineSelector;
import com.csa.apex.fundyield.exceptions.ConfigurationException;
import com.csa.apex.fundyield.exceptions.DataNotFoundException;
import com.csa.apex.fundyield.exceptions.FundAccountingYieldException;
import com.csa.apex.fundyield.seccommons.entities.FundAccountingYieldData;
import com.csa.apex.fundyield.seccommons.entities.Instrument;
import com.csa.apex.fundyield.seccommons.entities.Portfolio;
import com.csa.apex.fundyield.seccommons.entities.PortfolioHoldingSnapshot;
import com.csa.apex.fundyield.seccommons.entities.SECConfiguration;
import com.csa.apex.fundyield.seccommons.entities.TradableEntitySnapshot;
import com.csa.apex.fundyield.utility.CommonUtility;
import com.csa.apex.fundyield.utility.Constants;
import com.csa.apex.fundyield.utility.LogMethod;

/**
 * Default implementation of the <code>SecuritySECYieldService</code>. Uses
 * pluggable calculation engines to calculate the security SEC data like yield
 * and income. This class is effectively thread safe.
 *
 * @author [es], TCSDEVELOPER
 * @version 1.0
 * @since FAYA Java App - Phase 1 Updates Code Challenge
 */
@Service
public class SecuritySECYieldServiceImpl implements SecuritySECYieldService {

	/**
	 * The field names to be exported.
	 */
	private static final String[] EXPORT_FIELD_NAMES = new String[] { "securityIdentifier", "reportDate",
			"derCleanPrice", "derYieldCalcEngine", "derIncomeCalcEngine", "derOneDaySecurityYield", "derRedemptionDate",
			"derRedemptionPrice", "derSecurityType", "derTIPSInflationaryRatio", "securityPrice", "fxRate", "ivType",
			"securityName", "finalMaturityDate", "securityRedemptionPrice", "interestRt", "defIndicator",
			"derStepIndicator", "derHybridIndicator", "ioHybridField", "as400RateType", "prospectiveMethod",
			"portfolioNumber", "portfolioName", "earnedInflationaryCompensationBase", "accruedIncome", "marketValue",
			"shareParAmount", "earnedAmortizationBase", "positionValInflationAdjShares", "derOneDaySecurityIncome" };

	/**
	 * The value to be exported for null values.
	 */
	private static final String NULL_CSV_VALUE = "null";

	/**
	 * The export file type.
	 */
	private static final String EXPORT_FILE_TYPE = "application/zip";

	/**
	 * The CSV extension.
	 */
	private static final String CSV_EXTENSION = ".csv";

	/**
	 * The ZIP extension.
	 */
	private static final String ZIP_EXTENSION = ".zip";

	/**
	 * The export file name prefix.
	 */
	private static final String EXPORT_FILE_NAME_PREFIX = "export-";

	/**
	 * The description message for business date parameter.
	 */
	private static final String PARAM_BUSINESS_DATE = "Parameter businessDate";

	/**
	 * CalcultionEngineSelector object.
	 */
	@Autowired
	private CalculationEngineSelector calculationEngineSelector;

	/**
	 * Creating restTemplate object helps in mock testing.
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * The REST API path to the endpoint used to obtain calc engine
	 * configurations. After injection should be non-null and non-empty.
	 */
	@Value("${getConfigApiPath}")
	private String getConfigApiPath;

	/**
	 * The REST API path to the endpoint used to get security SEC data. After
	 * injection should be non-null and non-empty.
	 */
	@Value("${getSecuritySECDataApiPath}")
	private String getSecuritySECDataApiPath;

	/**
	 * The REST API path to the endpoint used to save calculated security SEC
	 * data. After injection should be non-null and non-empty.
	 */
	@Value("${saveCalculatedSecuritySECDataApiPath}")
	private String saveCalculatedSecuritySECDataApiPath;

	/**
	 * The REST API path to the endpoint used to get already calculated security
	 * SEC data. After injection should be non-null and non-empty.
	 */
	@Value("${getCalculatedSecuritySECDataApiPath}")
	private String getCalculatedSecuritySECDataApiPath;

	/**
	 * Configuration object for the service.
	 */
	private SECConfiguration configuration;

	/**
	 * Constructor
	 */
	public SecuritySECYieldServiceImpl() {
		// default constructor
	}

	/**
	 * Setter method for property <tt>restTemplate</tt>.
	 * 
	 * @param restTemplate
	 *            value to be assigned to property restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Checks the configuration.
	 * 
	 * @throws ConfigurationException
	 *             if any required field is not initialized properly.
	 */
	@PostConstruct
	protected void checkConfiguration() {
		CommonUtility.checkNullConfig(restTemplate, "restTemplate");
		CommonUtility.checkNullConfig(calculationEngineSelector, "calculationEngineSelector");
		CommonUtility.checkStringConfig(getConfigApiPath, "getConfigApiPath");
		CommonUtility.checkStringConfig(getSecuritySECDataApiPath, "getSecuritySECDataApiPath");
		CommonUtility.checkStringConfig(getCalculatedSecuritySECDataApiPath, "getCalculatedSecuritySECDataApiPath");
		CommonUtility.checkStringConfig(saveCalculatedSecuritySECDataApiPath, "saveCalculatedSecuritySECDataApiPath");
	}

	/**
	 * Gets already calculated FundAccountingYieldData data for the given date.
	 *
	 * @param businessDate
	 *            the business date
	 * @return FundAccountingYieldData for the date with calculated result
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	public FundAccountingYieldData getCalculatedSecuritySECData(Date businessDate) throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, PARAM_BUSINESS_DATE);

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getCalculatedSecuritySECDataApiPath)
					.queryParam("businessDate", getFormattedDate(businessDate));

			FundAccountingYieldData data = restTemplate.getForObject(builder.build().encode().toUri(),
					FundAccountingYieldData.class);

			// Throw DataNotFoundException if no data is returned
			checkNullResponse(data, builder.toUriString());

			return data;
		} catch (FundAccountingYieldException e) {
			throw e;
		} catch (Exception e) {
			throw new FundAccountingYieldException(e.getMessage(), e);
		}
	}

	/**
	 * Get SECConfiguration.
	 *
	 * @return SECConfiguration
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	private SECConfiguration getSECConfiguration() throws FundAccountingYieldException {
		if (configuration == null) {
			try {
				configuration = restTemplate.getForObject(getConfigApiPath, SECConfiguration.class);
			} catch (Exception e) {
				throw new FundAccountingYieldException("Failed to get SEC configuration from path: " + getConfigApiPath,
						e);
			}

			// Check that configuration is not null
			checkNullResponse(configuration, getConfigApiPath);
		}
		return configuration;
	}

	/**
	 * Check data returned is not null.
	 *
	 * @param responseData
	 *            The response data to check
	 * @param apiPath
	 *            The API path
	 * @throws DataNotFoundException
	 *             if response data is null
	 */
	private void checkNullResponse(Object responseData, String apiPath) throws DataNotFoundException {
		if (responseData == null) {
			throw new DataNotFoundException(String.format("No data returned when calling FAYA API: {%s}", apiPath));
		}
	}

	/**
	 * Process SEC Security data for the business data. This method gets the
	 * securities and then process each security first to calculate the data,
	 * then to persist it using API.
	 * 
	 * @param businessDate
	 *            the business date
	 * @return FundAccountingYieldData with calculated result
	 * @throws IllegalArgumentException
	 *             in case the input is invalid (null).
	 * @throws FundAccountingYieldException
	 *             in case any error during processing.
	 */
	@Override
	@LogMethod
	public FundAccountingYieldData processSecuritySECData(Date businessDate) throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, PARAM_BUSINESS_DATE);

		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getSecuritySECDataApiPath)
					.queryParam("businessDate", getFormattedDate(businessDate));

			FundAccountingYieldData data = restTemplate.getForObject(builder.build().encode().toUri(),
					FundAccountingYieldData.class);

			// Throw DataNotFoundException if no data is returned
			checkNullResponse(data, builder.toUriString());

			// call engine selector
			calculationEngineSelector.calculate(data, getSECConfiguration());

			// calculate the data
			calculationEngineSelector.getOrderedCalculatorEngines().calculate(data, getSECConfiguration());

			// save calculated data
			Boolean saveResponse = restTemplate.exchange(saveCalculatedSecuritySECDataApiPath, HttpMethod.PUT,
					new HttpEntity<FundAccountingYieldData>(data), Boolean.class).getBody();

			// if response false
			if (saveResponse == null || !saveResponse) {
				throw new FundAccountingYieldException(
						"Failed to persist FundAccountingYieldData, URL: " + saveCalculatedSecuritySECDataApiPath);
			}

			return data;
		} catch (FundAccountingYieldException e) {
			throw e;
		} catch (Exception e) {
			throw new FundAccountingYieldException(e.getMessage(), e);
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
	 * @throws FundAccountingYieldException
	 *             any error during processing
	 */
	@Override
	@LogMethod
	public void exportCalculatedSecuritySECData(Date businessDate, HttpServletResponse response)
			throws FundAccountingYieldException {
		CommonUtility.checkNull(businessDate, PARAM_BUSINESS_DATE);
		CommonUtility.checkNull(response, "Parameter response");

		FundAccountingYieldData data = getCalculatedSecuritySECData(businessDate);

		try {
			createExportArchive(response, data, getFormattedDate(businessDate));
		} catch (Exception e) {
			throw new FundAccountingYieldException("Failed to export SEC data: " + e.getMessage(), e);
		}
	}

	/**
	 * Create the export zip file.
	 *
	 * @param response
	 *            the http response to which the archive will be written
	 * @param data
	 *            the FundAccountingYieldData
	 * @param businessDate
	 *            the business date string
	 * @throws IOException
	 *             if any IO exception occurs or if the CSV file is not found
	 */
	private void createExportArchive(HttpServletResponse response, FundAccountingYieldData data, String businessDate)
			throws IOException {

		String exportArchiveFileName = EXPORT_FILE_NAME_PREFIX + businessDate + ZIP_EXTENSION;
		String csvFileName = EXPORT_FILE_NAME_PREFIX + businessDate + CSV_EXTENSION;

		response.addHeader("Content-disposition", "attachment;filename=" + exportArchiveFileName);
		response.setContentType(EXPORT_FILE_TYPE);

		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		zos.putNextEntry(new ZipEntry(csvFileName));

		zos.write((Arrays.asList(EXPORT_FIELD_NAMES).stream().collect(Collectors.joining(",")) + "\n").getBytes());

		Map<Long, Portfolio> portfoliosMap = data.getPortfolios().stream()
				.collect(Collectors.toMap(Portfolio::getPortfolioSid, Function.identity()));

		for (Instrument instrument : data.getInstruments()) {
			TradableEntitySnapshot tes = CommonUtility.getTradableEntitySnapshot(instrument);
			if (tes != null) {
				List<PortfolioHoldingSnapshot> portfolioHoldings = CommonUtility.getRelatedPortfolioHoldings(data,
						tes.getTradableEntitySid());
				for (PortfolioHoldingSnapshot portfolioHolding : portfolioHoldings) {
					String[] csvColumns = convertSECDataToCSVRow(data, instrument, tes,
							portfoliosMap.get(portfolioHolding.getPortfolioSid()), portfolioHolding);
					String csvLine = Arrays.asList(csvColumns).stream().collect(Collectors.joining(",")) + "\n";
					zos.write(csvLine.getBytes());
				}
			}
		}
		zos.closeEntry();
		zos.close();
	}

	/**
	 * Convert the SEC data to an array of field values which compose a CSV row.
	 *
	 * @param data
	 *            the FundAccountingYieldData
	 * @param instrument
	 *            the Instrument
	 * @param tes
	 *            the TradableEntitySnapshot
	 * @param portfolio
	 *            the Portfolio
	 * @param portfolioHolding
	 *            the PortfolioHoldingSnapshot
	 * @return the array of position data field values
	 */
	private String[] convertSECDataToCSVRow(FundAccountingYieldData data, Instrument instrument,
			TradableEntitySnapshot tes, Portfolio portfolio, PortfolioHoldingSnapshot portfolioHolding) {
		String[] values = new String[EXPORT_FIELD_NAMES.length];
		int index = 0;
		values[index++] = String.valueOf(instrument.getInstrumentId());
		values[index++] = getFormattedDate(data.getReportDate());
		values[index++] = String.valueOf(tes.getFdrCleanPrice());
		values[index++] = tes.getDerYieldCalcEngineCode();
		values[index++] = tes.getDerIncomeCalcEngineCode();
		values[index++] = String.valueOf(tes.getDerOneDaySecurityYield());
		values[index++] = getFormattedDate(tes.getDerRedemptionDate());
		values[index++] = String.valueOf(tes.getDerRedemptionPrice());
		values[index++] = instrument.getDerFofSecurityCd();
		values[index++] = String.valueOf(tes.getFdrTipsInflationaryRatio());
		values[index++] = String.valueOf(tes.getMarketPrice());
		values[index++] = String.valueOf(portfolioHolding.getFxRt());

		values[index++] = instrument.getInstrumentTypeCode().toString();
		values[index++] = instrument.getInstrumentShortName();
		values[index++] = getFormattedDate(instrument.getFinalMaturityDate());
		values[index++] = String.valueOf(instrument.getMaturityPrc());
		values[index++] = String.valueOf(tes.getCurrentIncomeRate());
		values[index++] = instrument.getDefaultInd();
		values[index++] = instrument.getFdrStepBondInd();
		values[index++] = "HYBRID".equalsIgnoreCase(instrument.getHybridCalculationCd()) ? "Y" : "N";
		values[index++] = instrument.getHybridCalculationCd();
		values[index++] = instrument.getCouponRateTypeCode();
		values[index++] = instrument.getProspectiveYieldMethodCd();

		values[index++] = String.valueOf(portfolio.getPortfolioId());
		values[index++] = portfolio.getPortfolioShortName();
		values[index++] = String.valueOf(portfolioHolding.getEarnedInflCmpsBaseAmt());
		values[index++] = String.valueOf(portfolioHolding.getAccruedIncomeAmt());
		values[index++] = String.valueOf(portfolioHolding.getMarketValueBaseAmt());
		values[index++] = String.valueOf(portfolioHolding.getSettleShareCnt());
		values[index++] = String.valueOf(portfolioHolding.getEarnedAmortBaseAmt());
		values[index++] = String.valueOf(portfolioHolding.getInflationAdjShareCnt());
		values[index] = String.valueOf(portfolioHolding.getDerSecYield1DayIncomeAmt());

		return values;
	}

	/**
	 * Retrieve formatted date.
	 * 
	 * @param date
	 *            the date to be formatted
	 * @return the formatted date
	 */
	private String getFormattedDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.API_DATE_FORMAT);
		return date != null ? dateFormat.format(date) : NULL_CSV_VALUE;
	}

}
