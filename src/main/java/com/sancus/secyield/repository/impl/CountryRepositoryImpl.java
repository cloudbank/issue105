package com.sancus.secyield.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sancus.secyield.domain.Country;
import com.sancus.secyield.exceptions.PersistenceException;
import com.sancus.secyield.repository.CountryRepository;

/**
 * The Country Repository
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Repository("countryRepository")
public class CountryRepositoryImpl implements CountryRepository {
	
	/**
     * The query to get all countries.
     */
    private static final String GET_ALL_COUNTRIES = "SELECT * FROM COUNTRY";
    
    /**
     * The query to get country by id.
     */
    private static final String GET_COUNTRY = "SELECT * FROM COUNTRY WHERE id = ?";
    
    /**
     * The query to delete country by id.
     */
    private static final String DELETE_COUNTRY = "DELETE FROM COUNTRY WHERE id = ?";
    
    /**
     * The query to create country.
     */
    private static final String CREATE_COUNTRY = "INSERT INTO COUNTRY(name) VALUES (?)";
    
    /**
     * The query to update country.
     */
    private static final String UPDATE_COUNTRY = "UPDATE COUNTRY SET name = ? WHERE id = ?";
    
	/**
     * The JDBC template.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * The logger.
     */
    private Logger log = Logger.getLogger(CountryRepositoryImpl.class.getName());
    
    /**
     * Default constructor.
     */
    public CountryRepositoryImpl() {
    }
    
	/**
     * Gets all countries.
     * @return list of all countries.
     * @throws PersistenceException if error occurs when getting the countries
     */
    public List<Country> findAll() throws PersistenceException {
    	log.debug("Enter method CountryRepositoryImpl#findAll");
    	List<Country> countries = jdbcTemplate.query(GET_ALL_COUNTRIES, new BeanPropertyRowMapper<>(Country.class));
    	log.debug("Exit method  CountryRepositoryImpl#findAll");
    	return countries;
    }
    
    /**
     * Gets the country.
     * @return gets country by id.
     * @throws PersistenceException if error occurs when getting the country
     */
    public Country findOne(Long id) throws PersistenceException {
    	log.debug("Enter method CountryRepositoryImpl#findOne");
    	Country country = null;
    	List<Country> countries = jdbcTemplate.query(GET_COUNTRY, new Object[]
                { id }, new BeanPropertyRowMapper<>(Country.class));
    	if(countries.size() > 0) {
    		country = countries.get(0);
    	}
    	log.debug("Exit method  CountryRepositoryImpl#findOne");
    	return country;
    }
    
    /**
     * Deletes the country.
     * @throws PersistenceException if error occurs when deleting the country
     */
    public void delete(Long id) throws PersistenceException {
    	log.debug("Enter method CountryRepositoryImpl#delete");
    	jdbcTemplate.update(DELETE_COUNTRY, id);
    	log.debug("Exit method  CountryRepositoryImpl#delete");
    }
    
    /**
     * Saves the country.
     * @return return saved country.
     * @throws PersistenceException if error occurs when saving the country
     */
    public Country save(Country country) throws PersistenceException {
    	log.debug("Enter method CountryRepositoryImpl#save");
    	Country result = null;
    	if(country.getId() == null) {
    		KeyHolder keyHolder = new GeneratedKeyHolder();
    	    jdbcTemplate.update((Connection connection) -> {
    	        PreparedStatement ps = connection.prepareStatement(CREATE_COUNTRY, Statement.RETURN_GENERATED_KEYS);
    	        ps.setString(1, country.getName());
    	        return ps;
    	    }, keyHolder);
    	    result = findOne(keyHolder.getKey().longValue());
    	} else {
    		KeyHolder keyHolder = new GeneratedKeyHolder();
    	    jdbcTemplate.update((Connection connection) -> {
    	        PreparedStatement ps = connection.prepareStatement(UPDATE_COUNTRY, Statement.RETURN_GENERATED_KEYS);
    	        ps.setString(1, country.getName());
    	        ps.setLong(2, country.getId());
    	        return ps;
    	    }, keyHolder);
    	    result = findOne(country.getId());
    	}
    	log.debug("Exit method  CountryRepositoryImpl#save");
    	return result;
    }
}
