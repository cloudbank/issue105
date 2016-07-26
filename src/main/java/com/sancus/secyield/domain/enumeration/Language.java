package com.sancus.secyield.domain.enumeration;

/**
 * The Language enumeration.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public enum Language {
	French("French"),
    English("English"),
    Spanish("Spanish");

    private String value;

    Language(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
