package com.sancus.secyield.domain;

import java.util.Objects;

import com.sancus.secyield.domain.enumeration.Language;

/**
 * A Customer.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class Customer {

	/**
     * The id.
     */
	private Long id;

	/**
     * The name.
     */
    private String name;

    /**
     * The mobile number.
     */
    private String mobileNumber;

    /**
     * The language.
     */
    private Language language;

    /**
     * The country.
     */
    private Country country;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
	 * @param id the id to set
	 */
    public void setId(Long id) {
        this.id = id;
    }

    /**
	 * @param the name
	 */
    public String getName() {
        return name;
    }

    /**
	 * @param name the name to set
	 */
    public void setName(String name) {
        this.name = name;
    }

    /**
	 * @return the mobile number
	 */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
	 * @param mobileNumber the mobile number to set
	 */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
	 * @return the language
	 */
    public Language getLanguage() {
        return language;
    }

    /**
	 * @param language the language to set
	 */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
	 * @return the country
	 */
    public Country getCountry() {
        return country;
    }

    /**
	 * @param country the country to set
	 */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Override default equals method.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if(customer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customer.id);
    }

    /**
     * Override default hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
    
    /**
     * Override default toString method.
     */
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", mobileNumber='" + mobileNumber + "'" +
            ", language='" + language + "'" +
            '}';
    }
}
