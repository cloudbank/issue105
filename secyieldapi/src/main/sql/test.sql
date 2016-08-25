DELETE FROM CUSTOMER;
DELETE FROM COUNTRY;

INSERT INTO COUNTRY (id, name) VALUES (1, "India");
INSERT INTO COUNTRY (id, name) VALUES (2, "USA");
INSERT INTO COUNTRY (id, name) VALUES (3, "UAE");
INSERT INTO COUNTRY (id, name) VALUES (4, "China");
INSERT INTO COUNTRY (id, name) VALUES (5, "Jordan");

INSERT INTO CUSTOMER (id, name, mobileNumber, language, country) 
	VALUES (10001, "Peter Parker", "+1 - (834) - (343) - (3434)", "English" , 2);
	
INSERT INTO CUSTOMER (id, name, mobileNumber, language, country) 
	VALUES (10002, "John Doe", "+91 - (897) - (343) - (3434)", "English" , 1);

INSERT INTO CUSTOMER (id, name, mobileNumber, language, country) 
	VALUES (10003, "Linda Parker", "+145 - (234) - (343) - (3434)", "Spanish" , 3);
	
INSERT INTO CUSTOMER (id, name, mobileNumber, language, country) 
	VALUES (10004, "John Duglys", "+241 - (834) - (343) - (3434)", "English" , 4);
			