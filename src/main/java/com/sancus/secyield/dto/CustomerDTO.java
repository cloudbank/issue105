package com.sancus.secyield.dto;

import java.util.Objects;

import com.sancus.secyield.domain.enumeration.Language;

/**
 * A CustomerDTO.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class CustomerDTO {

	/**
	 * Customer id
	 */
	private Long id;

    /**
     * Customer name
     */
    private String name;

    /**
     * Customer mobile number
     */
    private String mobileNumber;

    /**
     * Customer language
     */
    private Language language;

    /**
     * Customer's country id
     */
    private Long countryId;

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
	 * @return the country's id
	 */
    public Long getCountryId() {
        return countryId;
    }

    /**
	 * @param countryId the country's id to set
	 */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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
        CustomerDTO customer = (CustomerDTO) o;
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
        return "CustomerDTO{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", mobileNumber='" + mobileNumber + "'" +
            ", language='" + language + "'" +
            ", countryId='" + countryId + "'" +
            '}';
    }
}