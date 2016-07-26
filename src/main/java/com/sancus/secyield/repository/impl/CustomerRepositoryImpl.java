package com.sancus.secyield.repository.impl;

import com.sancus.secyield.domain.Customer;
import com.sancus.secyield.exceptions.PersistenceException;
import com.sancus.secyield.repository.CustomerRepository;
import com.sancus.secyield.util.NestedRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * The Country Repository
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Repository("customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {
	
	/**
     * The query to get all customers.
     */
    private static final String GET_ALL_CUSTOMERS = "SELECT CUSTOMER.*, CUSTOMER.name AS 'customer.name', COUNTRY.id AS 'country.id', COUNTRY.name AS 'country.name' FROM CUSTOMER, COUNTRY WHERE CUSTOMER.country = COUNTRY.id";
    
    /**
     * The query to get customer by id.
     */
    private static final String GET_CUSTOMER = "SELECT CUSTOMER.*, CUSTOMER.name AS 'customer.name', COUNTRY.id AS 'country.id', COUNTRY.name AS 'country.name' FROM CUSTOMER, COUNTRY WHERE CUSTOMER.country = COUNTRY.id AND CUSTOMER.id = ?";
    
    /**
     * The query to delete customer by id.
     */
    private static final String DELETE_CUSTOMER = "DELETE FROM CUSTOMER WHERE id = ?";
    
    /**
     * The query to create customer.
     */
    private static final String CREATE_CUSTOMER = "INSERT INTO CUSTOMER(name, mobileNumber, language, country) VALUES (?, ?, ?, ?)";
    
    /**
     * The query to update customer.
     */
    private static final String UPDATE_CUSTOMER = "UPDATE CUSTOMER SET name = ?, mobileNumber = ?, language = ?, country = ? WHERE id = ?";
    
	/**
     * The JDBC template.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * The logger.
     */
    private Logger log = Logger.getLogger(CustomerRepositoryImpl.class.getName());
    
	/**
     * Gets all customers.
     * @return list of all customers.
     * @throws PersistenceException if error occurs when getting the customers
     */
    public List<Customer> findAll() throws PersistenceException {
    	log.debug("Enter method CustomerRepositoryImpl#findAll");
    	List<Customer> customers = jdbcTemplate.query(GET_ALL_CUSTOMERS, new NestedRowMapper<>(Customer.class));
    	log.debug("Exit method  CustomerRepositoryImpl#findAll");
    	return customers;
    }
    
    /**
     * Gets the customer.
     * @return gets customer by id.
     * @throws PersistenceException if error occurs when getting the customer
     */
    public Customer findOne(Long id) throws PersistenceException {
    	log.debug("Enter method CustomerRepositoryImpl#findOne");
    	Customer customer = null;
    	List<Customer> customers = jdbcTemplate.query(GET_CUSTOMER, new Object[]
                { id }, new NestedRowMapper<>(Customer.class));
    	if(customers.size() > 0) {
    		customer = customers.get(0);
    	}
    	log.debug("Exit method  CustomerRepositoryImpl#findOne");
    	return customer;
    }
    
    /**
     * Deletes the customer.
     * @throws PersistenceException if error occurs when deleting the customer
     */
    public void delete(Long id) throws PersistenceException {
    	log.debug("Enter method CustomerRepositoryImpl#delete");
    	jdbcTemplate.update(DELETE_CUSTOMER, id);
    	log.debug("Exit method  CustomerRepositoryImpl#delete");
    }
    
    /**
     * Saves the customer.
     * @return return saved customer.
     * @throws PersistenceException if error occurs when saving the customer
     */
    public Customer save(Customer customer) throws PersistenceException {
    	log.debug("Enter method CustomerRepositoryImpl#save");
    	Customer result = null;
    	if(customer.getId() == null) {
    		KeyHolder keyHolder = new GeneratedKeyHolder();
    	    jdbcTemplate.update((Connection connection) -> {
    	        PreparedStatement ps = connection.prepareStatement(CREATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
    	        ps.setString(1, customer.getName());
    	        ps.setString(2, customer.getMobileNumber());
    	        ps.setString(3, customer.getLanguage().toString());
    	        ps.setLong(4, customer.getCountry().getId());
    	        return ps;
    	    }, keyHolder);
    	    result = findOne(keyHolder.getKey().longValue());
    	} else {
    		KeyHolder keyHolder = new GeneratedKeyHolder();
    	    jdbcTemplate.update((Connection connection) -> {
    	        PreparedStatement ps = connection.prepareStatement(UPDATE_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
    	        ps.setString(1, customer.getName());
    	        ps.setString(2, customer.getMobileNumber());
    	        ps.setString(3, customer.getLanguage().toString());
    	        ps.setLong(4, customer.getCountry().getId());
    	        ps.setLong(5, customer.getId());
    	        return ps;
    	    }, keyHolder);
    	    result = findOne(customer.getId());
    	}
    	log.debug("Exit method  CustomerRepositoryImpl#save");
    	return result;
    }
}
