package com.sancus.secyield.web.rest;

import com.sancus.secyield.domain.Country;
import com.sancus.secyield.domain.Customer;
import com.sancus.secyield.dto.CustomerDTO;
import com.sancus.secyield.exceptions.PersistenceException;
import com.sancus.secyield.exceptions.SecYieldException;
import com.sancus.secyield.repository.CustomerRepository;
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
 * REST controller for managing customers.
 * @author TCSDEVELOPER
 * @version 1.0
 */
@RestController
public class CustomerResource {

	/**
     * The logger.
     */
    private final Logger log = Logger.getLogger(CustomerResource.class);
    
    /**
     * Customer repository
     */
    @Autowired
    private CustomerRepository customerRepository;
    
    /**
     * POST  /customers : Create a new customer.
     *
     * @param customer the customer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customer, or with status 400 (Bad Request) if the customer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/customers",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerDTO customer) throws URISyntaxException, SecYieldException {
        log.debug("REST request to save Customer : " + customer);
        if (customer.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("customer", "idexists", "A new customer cannot already have an ID")).body(null);
        }
        Customer result;
		try {
			Customer newCustomer = new Customer();
			newCustomer.setName(customer.getName());
			newCustomer.setLanguage(customer.getLanguage());
			newCustomer.setMobileNumber(customer.getMobileNumber());
			newCustomer.setCountry(new Country(customer.getCountryId()));
			result = customerRepository.save(newCustomer);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not create customer");
            log.error(ex);
            throw ex;
		}
        return ResponseEntity.created(new URI("/api/customers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("customer", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customers : Updates an existing customer.
     *
     * @param customer the customer to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customer,
     * or with status 400 (Bad Request) if the customer is not valid,
     * or with status 500 (Internal Server Error) if the customer couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/customers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody CustomerDTO customer) throws URISyntaxException, SecYieldException {
        log.debug("REST request to update Customer : " + customer);
        if (customer.getId() == null) {
            return createCustomer(customer);
        }
        Customer result = null;
		try {
			Customer existingCustomer = new Customer();
			existingCustomer.setId(customer.getId());
			existingCustomer.setName(customer.getName());
			existingCustomer.setLanguage(customer.getLanguage());
			existingCustomer.setMobileNumber(customer.getMobileNumber());
			existingCustomer.setCountry(new Country(customer.getCountryId()));
			result = customerRepository.save(existingCustomer);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not update customer");
            log.error(ex);
            throw ex;
		}
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("customer", customer.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customers : get all the customers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of customers in body
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/customers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAllCustomers() throws SecYieldException {
        log.debug("REST request to get all Customers");
        List<Customer> customers;
		try {
			customers = customerRepository.findAll();
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not find customers");
            log.error(ex);
            throw ex;
		}
        return customers;
    }

    /**
     * GET  /customers/:id : get the "id" customer.
     *
     * @param id the id of the customer to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customer, or with status 404 (Not Found)
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/customers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) throws SecYieldException {
        log.debug("REST request to get Customer : " + id);
        Customer customer;
		try {
			customer = customerRepository.findOne(id);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not find customer");
            log.error(ex);
            throw ex;
		}
        return Optional.ofNullable(customer)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /customers/:id : delete the "id" customer.
     *
     * @param id the id of the customer to delete
     * @return the ResponseEntity with status 200 (OK)
     * @throws SecYieldException 
     */
    @RequestMapping(value = "/customers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws SecYieldException {
        log.debug("REST request to delete Customer : " + id);
        try {
			customerRepository.delete(id);
		} catch (PersistenceException e) {
			SecYieldException ex = new SecYieldException("Can not delete customer");
            log.error(ex);
            throw ex;
		}
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("customer", id.toString())).build();
    }

}
