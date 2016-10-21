INSERT INTO Cars (Car_ID, VIN, Brand, Model, Year_of_Manufacture, Price_per_Day, Description) 
VALUES (1, 'WAUZZZ4FO8N012345', 'Audi', 'A8', 2012, 450, 'Navigation system, ESP, On-board computer, Electric windows');

INSERT INTO Cars (Car_ID, VIN, Brand, Model, Year_of_Manufacture, Price_per_Day, Description) 
VALUES (2, 'WAUZXX4FO8N543210', 'Audi', 'A4', 2011, 320, 'Climatronic, ESP, On-board computer, Electric windows');


INSERT INTO CUSTOMERS (CUSTOMER_ID, STREET_ADDRESS, CITY, POSTAL_CODE, PERSON_NAME, PERSON_SURNAME, ID_CARD, CUSTOMER_TYPE, EMAIL, PASSWORD) 
VALUES (1, 'Some Street 22', 'Warsaw', '00-000', 'John', 'Klamka', 'ABC123', 'PERS', 'john.klamka@com', 'passMe');

INSERT INTO CUSTOMERS (CUSTOMER_ID, STREET_ADDRESS, CITY, POSTAL_CODE, PERSON_NAME, PERSON_SURNAME, ID_CARD, CUSTOMER_TYPE, EMAIL, PASSWORD)
VALUES (2, 'JAVA Avenue 8', 'NY', '88-888', 'James', 'Gosling', '123ABC', 'PERS', 'jg@java.com', 'passMe');

INSERT INTO CUSTOMERS (CUSTOMER_ID, STREET_ADDRESS, CITY, POSTAL_CODE, COMPANY_NAME, TAX_ID, CUSTOMER_TYPE, EMAIL, PASSWORD) 
VALUES (3, 'Test Street 1', 'Warsaw', '55-555', 'Some Company c.o.', '123456', 'COMP', 'some@compay.com' ,'passCompany');

INSERT INTO CUSTOMERS (CUSTOMER_ID, STREET_ADDRESS, CITY, POSTAL_CODE, COMPANY_NAME, TAX_ID, CUSTOMER_TYPE, EMAIL, PASSWORD) 
VALUES (4, 'Some Address 90', 'Berlin', '11-555', 'IT Company c.o.', '100200', 'COMP', 'it@compay.com', 'passCompany');