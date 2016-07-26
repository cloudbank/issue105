DROP TABLE IF EXISTS CUSTOMER;
DROP TABLE IF EXISTS COUNTRY;

CREATE TABLE COUNTRY (
  id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR (64) NOT NULL
);

CREATE TABLE CUSTOMER (
  id BIGINT UNIQUE PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR (64) NOT NULL,
  mobileNumber VARCHAR(40),
  language VARCHAR(32) NOT NULL,
  country BIGINT NOT NULL REFERENCES COUNTRY(id)
);