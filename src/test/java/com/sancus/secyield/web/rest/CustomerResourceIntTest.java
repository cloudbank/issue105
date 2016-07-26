package com.sancus.secyield.web.rest;

import com.sancus.secyield.Application;
import com.sancus.secyield.domain.Country;
import com.sancus.secyield.domain.Customer;
import com.sancus.secyield.repository.CountryRepository;
import com.sancus.secyield.repository.CustomerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sancus.secyield.domain.enumeration.Language;
import com.sancus.secyield.dto.CustomerDTO;
import com.sancus.secyield.exceptions.PersistenceException;

/**
 * Test class for the CustomerResource REST controller.
 *
 * @see CustomerResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CustomerResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_MOBILE_NUMBER = "AAAAA";
    private static final String UPDATED_MOBILE_NUMBER = "BBBBB";
    private static final String DEFAULT_COUNTRY = "CCCCC";

    private static final Language DEFAULT_LANGUAGE = Language.French;
    private static final Language UPDATED_LANGUAGE = Language.English;

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCustomerMockMvc;
    
    private CustomerDTO customerDTO;
    
    private Customer customer;
    
    private Country country;

    @PostConstruct
    public void setup() throws PersistenceException {
        MockitoAnnotations.initMocks(this);
        
        CustomerResource customerResource = new CustomerResource();
        ReflectionTestUtils.setField(customerResource, "customerRepository", customerRepository);
        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() throws PersistenceException {
    	
    	//Create country, will be deleted after running tests
        country = new Country();
        country.setName(DEFAULT_COUNTRY);
        country = countryRepository.save(country);
    	
        customerDTO = new CustomerDTO();
        customerDTO.setName(DEFAULT_NAME);
        customerDTO.setMobileNumber(DEFAULT_MOBILE_NUMBER);
        customerDTO.setLanguage(DEFAULT_LANGUAGE);
        customerDTO.setCountryId(country.getId());
        
        customer = new Customer();
        customer.setName(DEFAULT_NAME);
        customer.setMobileNumber(DEFAULT_MOBILE_NUMBER);
        customer.setLanguage(DEFAULT_LANGUAGE);
        customer.setCountry(country);
    }
    
    @After
    public void cleanTest() throws PersistenceException {
    	countryRepository.delete(country.getId());
    }

    @Test
    @Transactional
    public void createCustomer() throws Exception {
    	
    	int databaseSizeBeforeCreate = customerRepository.findAll().size();
    	
        // Create the Customer
        restCustomerMockMvc.perform(post("/customers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customerDTO)))
                .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customers.get(customers.size() - 1);
        assertThat(testCustomer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCustomer.getMobileNumber()).isEqualTo(DEFAULT_MOBILE_NUMBER);
        assertThat(testCustomer.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testCustomer.getCountry().getName()).isEqualTo(DEFAULT_COUNTRY);
    }

    @Test
    @Transactional
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customer = customerRepository.save(customer);

        // Get all the customers
        restCustomerMockMvc.perform(get("/customers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].mobileNumber").value(hasItem(DEFAULT_MOBILE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())));
    }

    @Test
    @Transactional
    public void getCustomer() throws Exception {
        // Initialize the database
        customer = customerRepository.save(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.mobileNumber").value(DEFAULT_MOBILE_NUMBER.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/customers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomer() throws Exception {
        // Initialize the database
        customer = customerRepository.save(customer);
        int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        CustomerDTO updatedCustomer = new CustomerDTO();
        updatedCustomer.setId(customer.getId());
        updatedCustomer.setName(UPDATED_NAME);
        updatedCustomer.setMobileNumber(UPDATED_MOBILE_NUMBER);
        updatedCustomer.setLanguage(UPDATED_LANGUAGE);
        updatedCustomer.setCountryId(customer.getCountry().getId());

        restCustomerMockMvc.perform(put("/customers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedCustomer)))
                .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customers.get(customers.size() - 1);
        assertThat(testCustomer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCustomer.getMobileNumber()).isEqualTo(UPDATED_MOBILE_NUMBER);
        assertThat(testCustomer.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
    }

    @Test
    @Transactional
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customer = customerRepository.save(customer);
        int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Get the customer
        restCustomerMockMvc.perform(delete("/customers/{id}", customer.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeDelete - 1);
    }
	
}
