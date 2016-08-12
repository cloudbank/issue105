package com.csa.apex.secyield.api.services.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.csa.apex.secyield.api.services.SECYieldService;
import com.csa.apex.secyield.api.services.impl.engines.CalculationEngineSelector;
import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.ConfigurationException;
import com.csa.apex.secyield.exceptions.SECYieldException;
import com.csa.apex.secyield.utility.MockDataServiceUtility;

/**
 * SECYieldServiceImpl
 * implementation of the SECYieldService. Uses pluggable calculation engines to
 * calculate the security SEC data like yield and income. This class is
 * effectively thread safe.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@Service
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
	 * The REST API path to the endpoint used to obtain calc engine
	 * configurations. After injection should be non-null and non-empty.
	 */
	private String getConfigApiPath;

	/**
	 * The REST API path to the endpoint used to get security SEC data. After
	 * injection should be non-null and non-empty.
	 */
	private String getCustomerDataApiPath;

	/**
	 * The REST API path to the endpoint used to save calculated security SEC
	 * data. After injection should be non-null and non-empty.
	 */
	private String saveCalculatedSecuritySECDataApiPath;

	/**
	 * The REST API path to the endpoint used to get already calculated security
	 * SEC data. After injection should be non-null and non-empty.
	 */
	private String getCalculatedSecuritySECDataApiPath;

	
	/**
	 * Creating restTemplate object helps in  mock testing
	 * 
	 */
	private RestTemplate restTemplate = new RestTemplate();
	

	private SECConfiguration configuration;

	/**
	 * Illegal Argument Exception Message
	 */
	@Value("${messages.illegalargumentexception}")
	private String illegalArgumentExceptionMessage;
	
	/**
	 * It mocks all the restTemplate class
	 * Value should be false in prod
	 */
	private Boolean doMock;

	
	
	
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
	 * processSingleSecurity method name
	 */
	@Value("${secyieldserviceimpl.processsinglesecuritymethodname}")
	private String processingSingeSecurityMethodName;
	
	/**
	 * processSecuritySECData method name
	 */
	@Value("${secyieldserviceimpl.processSecuritySECDataMethodName}")
	private String processSecuritySECDataMethodName;

	/**
	 * Constructor
	 */
	public SECYieldServiceImpl() {
		//default constructor
	}

	/**
	 * Getter configuration
	 * @return configuration
	 */
	public SECConfiguration getConfiguration() {
		return configuration;
	}

	/**
	 * Setter configuration
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
	 * @return restTemplate
	 */
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	/**
	 * Setter restTemplate
	 * @param restTemplate
	 */
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	/**
	 * Getter docMock
	 * @return doMock
	 */
	public Boolean getDoMock() {
		return doMock;
	}

	/**
	 * Setter doMock
	 * @param doMock
	 */
	public void setDoMock(Boolean doMock) {
		this.doMock = doMock;
	}

	
	
	/**
	 * Checks beans are injected properly on postconstruct throws
	 * ConfigurationException
	 */
	@PostConstruct
	protected void checkConfiguration() {
		
		if (calculationEngineSelector == null && getConfigApiPath != null && getCustomerDataApiPath != null
				&& getCalculatedSecuritySECDataApiPath != null) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
	}

	/**
	 * Process SEC Security data for the business data. This method gets the
	 * securites and then process each security first to calculate the data,
	 * then to persist it using API.
	 * 
	 * @param Date
	 *            businessDate of the security
	 * @return List<SecuritySECData> list of SecuritySECData for the date
	 * @throws SECYieldException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SecuritySECData> processSecuritySECData(Date businessDate)
			throws  SECYieldException {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getCustomerDataApiPath)
					.queryParam("businessDate", businessDate);
			
			List<SecuritySECData> securities;
			// if mocking on it will return mock data
			if(doMock)
			{
				securities = MockDataServiceUtility.getSecuritySECDataWithoutYieldAndIncomeData();
			}
			else
			{
				securities = (List<SecuritySECData>) restTemplate
						.getForObject(builder.build().encode().toUri(), List.class);
				
			}
			
			for(SecuritySECData security : securities)
			{
				processSingleSecurity(security);
			}


			return securities;
		} catch (SECYieldException e) {
			logger.error(String.format(logErrorFormat, processSecuritySECDataMethodName, e.getMessage()));
			throw e;
		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, processSecuritySECDataMethodName, e.getMessage()));
			throw new SECYieldException(e.getMessage(), e);
		}

	}

	/**
	 * Gets already calculated SEC Security data for the given date.
	 *
	 * @param Date
	 *            businessDate of the security
	 * @return List<SecuritySECData> list of SecuritySECData for the date
	 * @throws SECYieldException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SecuritySECData> getCalculatedSecuritySECData(Date businessDate)
			throws SECYieldException {
		if (businessDate == null) {
			logger.error(
					String.format(logErrorFormat, "getCalculatedSecuritySECData", illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getCalculatedSecuritySECDataApiPath)
					.queryParam("businessDate", businessDate);
			List<SecuritySECData> securities;
			if(doMock)
			{
				securities = MockDataServiceUtility.getSecuritySECDataWithYieldAndIncomeData();
			}
			else
			{
				securities = (List<SecuritySECData>) restTemplate
						.getForObject(builder.build().encode().toUri(), List.class);
			}
			return securities;
		} catch (Exception e) {
			logger.error(String.format(logErrorFormat, "processSingleSecurity", e.getMessage()));
			throw new SECYieldException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * method is used to process each security, first to calculate the data,
	 * then to persist it using API.
	 * 
	 * @param securitySECData
	 *            the input securitySECData that is updated in the method.
	 * @throws SECYieldException
	 */
	private void processSingleSecurity(SecuritySECData securitySECData)
			throws SECYieldException {

		// checks passed value should not be null
		if (securitySECData == null) {
			logger.error(String.format(logErrorFormat, processingSingeSecurityMethodName, illegalArgumentExceptionMessage));
			throw new IllegalArgumentException(illegalArgumentExceptionMessage);
		}
		try {
			// call engine calculator
			SecuritySECData updatedSecuritySECData = calculationEngineSelector.calculate(securitySECData, configuration);
	
			// calculate the data
			if ("VPS".equalsIgnoreCase(updatedSecuritySECData.getSecurityReferenceData().getIVType())) {
				updatedSecuritySECData = calculationEngineSelector.getCalculationEngines().get("YTM")
						.calculate(updatedSecuritySECData, configuration);
			} else if ("VRDN".equalsIgnoreCase(updatedSecuritySECData.getSecurityReferenceData().getIVType())
					|| "DVRN".equalsIgnoreCase(updatedSecuritySECData.getSecurityReferenceData().getIVType())) {
				updatedSecuritySECData = calculationEngineSelector.getCalculationEngines().get("COUPON")
						.calculate(updatedSecuritySECData, configuration);
			} else {
				throw new UnsupportedOperationException(unSupportedOperationException);
			}
			// call the api
			Boolean saveResponse;
			if(doMock)
			{
				saveResponse = true;
			}
			else{
				 saveResponse = restTemplate.exchange(saveCalculatedSecuritySECDataApiPath, HttpMethod.PUT,
							new HttpEntity<SecuritySECData>(updatedSecuritySECData), Boolean.class).getBody();
			}
			
			// if response false
			if (!saveResponse) {
				logger.error(String.format(logErrorFormat, processingSingeSecurityMethodName, secYieldExceptionMessage));
				throw new SECYieldException(secYieldExceptionMessage);
			}
		} catch (UnsupportedOperationException|SECYieldException e) {
			logger.error(String.format(logErrorFormat, processingSingeSecurityMethodName, e.getMessage()));
			throw e;
		} catch (Exception e) {
			// add throw cause for exception chaining
			logger.error(String.format(logErrorFormat, processingSingeSecurityMethodName, e.getMessage()));
			throw new SECYieldException(secYieldExceptionMessage, e);
		}

	}

}
