package com.sancus.secyield.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sancus.secyield.domain.Country;
import com.sancus.secyield.exceptions.PersistenceException;

/**
 * The Country Repository
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Repository
public interface CountryRepository {
	
	/**
     * Gets all countries.
     * @return list of all countries.
     * @throws PersistenceException if error occurs when getting the countries
     */
    public List<Country> findAll() throws PersistenceException;
    
    /**
     * Gets the country.
     * @return gets country by id.
     * @throws PersistenceException if error occurs when getting the country
     */
    public Country findOne(Long id) throws PersistenceException;
    
    /**
     * Deletes the country.
     * @throws PersistenceException if error occurs when deleting the country
     */
    public void delete(Long id) throws PersistenceException;
    
    /**
     * Saves the country.
     * @return return saved country.
     * @throws PersistenceException if error occurs when saving the country
     */
    public Country save(Country country) throws PersistenceException;
}
