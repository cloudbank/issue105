package com.csa.apex.secyield.api.services.impl;

import java.net.URI;
import java.text.ParseException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.csa.apex.secyield.entities.SECConfiguration;
import com.csa.apex.secyield.utility.MockDataServiceUtility;

/**
 * MockCustomerApiServer Mock RestTemplate class which mocks customer api This
 * class is temporary and should be removed once customer api is developed It is
 * for testing purpose through postman Proper mocking has been done separately
 * using mockito for unit testing
 *
 * @author [es],TCSDEVELOPER
 * @version 1.0
 */
public class MockCustomerApiServer extends RestTemplate {

	/**
	 * Overrides exchange method of RestTemplate class
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
			Class<T> responseType, Object... uriVariables) {
		return (ResponseEntity<T>) new ResponseEntity<>(true, new HttpHeaders(), HttpStatus.CREATED);
	}

	/**
	 * Overrides getForObject method of RestTemplate class
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) {
		if (url.contains("getConfiguration")) {
			return (T) new SECConfiguration();
		}
		return null;
	}

	/**
	 * Overrides getForObject method of RestTemplate class
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getForObject(URI url, Class<T> responseType) {
		if (url.toString().contains("getCustomerSECData")) {
			try {
				return (T) MockDataServiceUtility.getSecuritySECDataWithoutYieldAndIncomeData();
			} catch (ParseException e) {
				// handle parse exception
			}
		} else if (url.toString().contains("getCalculatedSECData")) {
			try {
				return (T) MockDataServiceUtility.getSecuritySECDataWithYieldAndIncomeData();
			} catch (ParseException e) {
				// handle parse exception
			}
		}
		return null;
	}

}
