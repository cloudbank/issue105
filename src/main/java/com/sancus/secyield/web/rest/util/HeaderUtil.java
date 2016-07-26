package com.sancus.secyield.web.rest.util;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class HeaderUtil {

	/**
     * The logger
     */
    private static final Logger log = Logger.getLogger(HeaderUtil.class);

    /**
     * Set alert in http header.
     * @param message the message to set
     * @param param the param to set
     * @return the http headers.
     */
    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-secyieldApp-alert", message);
        headers.add("X-secyieldApp-params", param);
        return headers;
    }

    /**
     * Create entity creation alerts.
     * @param entityName the entity name to set
     * @param param the param to set
     * @return the http headers
     */
    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("A new " + entityName + " is created with identifier " + param, param);
    }

    /**
     * Create entity update alerts.
     * @param entityName the entity name to set
     * @param param the param to set
     * @return the http headers
     */
    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("A " + entityName + " is updated with identifier " + param, param);
    }

    /**
     * Create entity deletion alerts.
     * @param entityName the entity name to set
     * @param param the param to set
     * @return the http headers
     */
    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("A " + entityName + " is deleted with identifier " + param, param);
    }

    /**
     * Create failure creation alerts.
     * @param entityName the entity name to set
     * @param param the param to set
     * @return the http headers
     */
    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        log.error("Entity creation failed, " + defaultMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-secyieldApp-error", defaultMessage);
        headers.add("X-secyieldApp-params", entityName);
        return headers;
    }
}
