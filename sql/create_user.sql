-- -----------------------
--  User secyield
-- -----------------------
DROP USER secyield CASCADE;
CREATE USER secyield IDENTIFIED BY password;
GRANT CONNECT, CREATE TABLE, CREATE TYPE, CREATE PROCEDURE, CREATE SEQUENCE TO secyield;
ALTER USER secyield QUOTA UNLIMITED ON SYSTEM;

-- ------------------------
--  User test_secyield
-- ------------------------
DROP USER test_secyield CASCADE;
CREATE USER test_secyield IDENTIFIED BY password;
GRANT CONNECT, CREATE TABLE, CREATE TYPE, CREATE PROCEDURE, CREATE SEQUENCE TO test_secyield;
ALTER USER test_secyield QUOTA UNLIMITED ON SYSTEM;
