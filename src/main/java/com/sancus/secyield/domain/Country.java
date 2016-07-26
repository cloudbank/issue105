package com.sancus.secyield.domain;

import java.util.Objects;

/**
 * The Country.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class Country {

	/**
     * The id.
     */
    private Long id;

    /**
     * The name.
     */
    private String name;
    
    /**
     * Default constructor.
     */
    public Country() {
    	//Overriding default constructor as public
    }
    
    /**
     * Custom constructor.
     * @param id the id to set
     */
    public Country(Long id) {
    	this.id = id;
    }
    
    /**
     * Custom constructor.
     * @param id the id to set
     * @param name the name to set
     */
    public Country(Long id, String name) {
    	this.id = id;
    	this.name = name;
    }
    
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
        Country country = (Country) o;
        if(country.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, country.id);
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
        return "Country{" +
            "id=" + id +
            ", name='" + name + "'" +
            '}';
    }
}
