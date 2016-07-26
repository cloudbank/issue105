package com.sancus.secyield.repository;

import com.sancus.secyield.domain.Customer;
import com.sancus.secyield.exceptions.PersistenceException;

import java.util.List;

/**
 * The Country Repository
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public interface CustomerRepository {
	
	/**
     * Gets all customers.
     * @return list of all customers.
     * @throws PersistenceException if error occurs when getting the customers
     */
    public List<Customer> findAll() throws PersistenceException;
    
    /**
     * Gets the customer.
     * @return gets customer by id.
     * @throws PersistenceException if error occurs when getting the customer
     */
    public Customer findOne(Long id) throws PersistenceException;
    
    /**
     * Deletes the customer.
     * @throws PersistenceException if error occurs when deleting the customer
     */
    public void delete(Long id) throws PersistenceException;
    
    /**
     * Saves the customer.
     * @return return saved customer.
     * @throws PersistenceException if error occurs when saving the customer
     */
    public Customer save(Customer customer) throws PersistenceException;
}
