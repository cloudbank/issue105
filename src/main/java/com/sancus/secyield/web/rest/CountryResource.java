package com.sancus.secyield.web.rest;

import com.sancus.secyield.domain.Country;
import com.sancus.secyield.exceptions.PersistenceException;
import com.sancus.secyield.exceptions.SecYieldException;
import com.sancus.secyield.repository.CountryRepository;
import com.sancus.secyield.web.rest.util.HeaderUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing countries.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@RestController
public class CountryResource {
	
	/**
     * The logger
     */
    private final Logger log = Logger.getLogger(CountryResource.class);
    
    /**
     * Country repository.
     */
    @Autowired
    private CountryRepository countryRepository;
    
    /**
     * POST  /countries : Create a new country.
     *
     * @param country the country to create
     * @return the ResponseEntity with status 201 (Created) and with body the new country, or with status 400 (Bad Request) if the country has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/countries",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> createCountry(@RequestBody Country country) throws URISyntaxException, SecYieldException {
        log.debug("REST request to save Country : " + country);
        if (country.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("country", "idexists", "A new country cannot already have an ID")).body(null);
        }
        Country result = null;
		try {
			result = countryRepository.save(country);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not create country");
            log.error(ex);
            throw ex;
		}
        return ResponseEntity.created(new URI("/api/countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("country", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /countries : Updates an existing country.
     *
     * @param country the country to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated country,
     * or with status 400 (Bad Request) if the country is not valid,
     * or with status 500 (Internal Server Error) if the country couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/countries",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) throws URISyntaxException, SecYieldException {
        log.debug("REST request to update Country : " + country);
        if (country.getId() == null) {
            return createCountry(country);
        }
        Country result = null;
		try {
			result = countryRepository.save(country);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not update country");
            log.error(ex);
            throw ex;
		}
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("country", country.getId().toString()))
            .body(result);
    }

    /**
     * GET  /countries : get all the countries.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of countries in body
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/countries",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getAllCountries() throws SecYieldException {
        log.debug("REST request to get all Countries");
        List<Country> countries;
		try {
			countries = countryRepository.findAll();
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not find countries");
            log.error(ex);
            throw ex;
		}
        return countries;
    }

    /**
     * GET  /countries/:id : get the "id" country.
     *
     * @param id the id of the country to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the country, or with status 404 (Not Found)
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/countries/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> getCountry(@PathVariable Long id) throws SecYieldException {
        log.debug("REST request to get Country : " + id);
        Country country;
		try {
			country = countryRepository.findOne(id);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not find country");
            log.error(ex);
            throw ex;
		}
        return Optional.ofNullable(country)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /countries/:id : delete the "id" country.
     *
     * @param id the id of the country to delete
     * @return the ResponseEntity with status 200 (OK)
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/countries/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) throws SecYieldException {
        log.debug("REST request to delete Country : " + id);
        try {
			countryRepository.delete(id);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not delete country");
            log.error(ex);
            throw ex;
		}
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("country", id.toString())).build();
    }

}
