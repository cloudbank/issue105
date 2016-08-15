package com.csa.apex.secyield.api;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csa.apex.secyield.api.services.SECYieldService;
import com.csa.apex.secyield.entities.SecuritySECData;
import com.csa.apex.secyield.exceptions.ConfigurationException;
import com.csa.apex.secyield.exceptions.SECYieldException;

/**
 * SECYieldController Spring REST Controller for customer data operations. This
 * class is effectively thread safe.
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
@RestController
public class SECYieldController {
	/**
	 * SecYieldService object
	 */
	@Autowired()
	@Qualifier("secYieldServiceImpl")
	private SECYieldService secYieldService;

	/**
	 * Configuration exception message
	 */
	@Value("${messages.configurationargumentexception}")
	private String configurationArgumentExceptionMessage;

	/**
	 * Constructor
	 */
	public SECYieldController() {
		// default constructor

	}

	/**
	 * Getter secYieldService
	 * 
	 * @return secYieldService
	 */
	public SECYieldService getSecYieldService() {
		return secYieldService;
	}

	/**
	 * Setter secYieldService
	 * 
	 * @param secYieldService
	 */
	public void setSecYieldService(SECYieldService secYieldService) {
		this.secYieldService = secYieldService;
	}

	/**
	 * Checks beans are injected properly on postconstruct throws
	 * ConfigurationException
	 */
	@PostConstruct
	protected void checkConfiguration() {

		if (secYieldService == null) {
			throw new ConfigurationException(configurationArgumentExceptionMessage);
		}
	}

	/**
	 * Gets SEC Security data with the calculated data for the business date.
	 * The securities are also persisted using customer REST API.
	 * 
	 * @param businessDate
	 *            The Business date
	 * @return List<SecuritySECData>
	 * @throws SECYieldException
	 */
	@RequestMapping(value = "securitySECData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<SecuritySECData> getSecuritySECData(@ModelAttribute Date businessDate) throws SECYieldException {
		return secYieldService.processSecuritySECData(businessDate);
	}

	/**
	 * Gets already calculated SEC Security data for the given date.
	 * 
	 * @param businessDate
	 *            the Business date
	 * @return already calculated securitySECData;
	 * @throws SECYieldException
	 */
	@RequestMapping(value = "calcualtedSecuritySECData", method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody

	public List<SecuritySECData> getCalculatedSecuritySECData(@ModelAttribute Date businessDate)
			throws SECYieldException {
		return secYieldService.getCalculatedSecuritySECData(businessDate);

	}

}
