--DDL and a bunch of constraints, sequences and triggers

CREATE TABLE CARS
  (
    Car_Id              NUMBER (7) NOT NULL ,
    VIN                 CHAR (20 CHAR) NOT NULL ,
    Brand               VARCHAR2 (20 CHAR) NOT NULL ,
    Model               VARCHAR2 (20 CHAR) NOT NULL ,
    Year_of_Manufacture NUMBER (4) NOT NULL ,
    Price_Per_Day       NUMBER (5) NOT NULL ,
    Description         VARCHAR2 (700 CHAR)
  ) ;

ALTER TABLE CARS ADD CONSTRAINT CARS_PK PRIMARY KEY ( Car_Id ) ;
ALTER TABLE CARS ADD CONSTRAINT CARS_VIN_UN UNIQUE ( VIN ) ;


CREATE TABLE CUSTOMERS
  (
    Customer_Id    NUMBER (7) NOT NULL ,
    Street_Address VARCHAR2 (40 CHAR) NOT NULL ,
    City           VARCHAR2 (25 CHAR) NOT NULL ,
    Postal_Code    VARCHAR2 (8 CHAR) NOT NULL ,
    Company_Name   VARCHAR2 (30 CHAR) ,
    Tax_Id         VARCHAR2 (10 CHAR) ,
    Person_Name    VARCHAR2 (25 CHAR) ,
    Person_Surname VARCHAR2 (25 CHAR) ,
    Id_Card        VARCHAR2 (25 CHAR) ,
    Customer_Type  VARCHAR2 (4) NOT NULL ,
    email          VARCHAR2 (50 CHAR) NOT NULL ,
    password       VARCHAR2 (70 CHAR) NOT NULL
  ) ;
ALTER TABLE CUSTOMERS ADD CONSTRAINT CH_INH_CUSTOM CHECK ( Customer_Type IN ('COMP', 'PERS')) ;

ALTER TABLE CUSTOMERS ADD CONSTRAINT CUSTOM_ExDep CHECK 
( (Customer_Type = 'COMP' AND Company_Name IS NOT NULL AND Tax_Id IS NOT NULL AND Person_Name IS NULL AND Person_Surname IS NULL AND Id_Card IS NULL) 
OR (Customer_Type = 'PERS' AND Company_Name IS NULL AND Tax_Id IS NULL AND Person_Name IS NOT NULL AND Person_Surname IS NOT NULL AND Id_Card IS NOT NULL)) ;

CREATE UNIQUE INDEX CUSTOMERS__Email_IDX ON CUSTOMERS ( email ASC )  ;

ALTER TABLE CUSTOMERS ADD CONSTRAINT CUSTOM_PK PRIMARY KEY ( Customer_Id ) ;
ALTER TABLE CUSTOMERS ADD CONSTRAINT CUSTOMERS_Id_Card_UN UNIQUE ( Id_Card ) ;
ALTER TABLE CUSTOMERS ADD CONSTRAINT CUSTOMERS_Tax_Id_UN UNIQUE ( Tax_Id ) ;


CREATE TABLE TRANSACTIONS
  (
    Transaction_Id NUMBER (7) NOT NULL ,
    Start_Date     DATE NOT NULL ,
    End_Date       DATE NOT NULL ,
    Car_Id         NUMBER (7) NOT NULL ,
    Customer_Id    NUMBER (7) NOT NULL
  );
 
CREATE INDEX TRANS_FK_IDX ON TRANSACTIONS
  (
    Car_Id ASC ,
    Customer_Id ASC
  );
 
ALTER TABLE TRANSACTIONS ADD CONSTRAINT TRANSACTIONS_CK_Dates CHECK (Start_Date < End_Date) ;
ALTER TABLE TRANSACTIONS ADD CONSTRAINT TRANS_PK PRIMARY KEY ( Transaction_Id ) ;


ALTER TABLE TRANSACTIONS ADD CONSTRAINT TRANS_CARS_FK FOREIGN KEY ( Car_Id ) REFERENCES CARS ( Car_Id ) NOT DEFERRABLE ;
ALTER TABLE TRANSACTIONS ADD CONSTRAINT TRANS_CUSTOM_FK FOREIGN KEY ( Customer_Id ) REFERENCES CUSTOMERS ( Customer_Id ) NOT DEFERRABLE ;

CREATE OR REPLACE TRIGGER FKNTM_TRANSACTIONS 
BEFORE  UPDATE OF Car_Id, Customer_Id ON TRANSACTIONS 
BEGIN 
raise_application_error(-20225,'Non Transferable FK constraint  on table TRANSACTIONS is violated');
END;
/

CREATE SEQUENCE SEQ_CARS START WITH 1 NOCACHE ;
CREATE OR REPLACE TRIGGER AUTO_SEQ_CARS_ID BEFORE
  INSERT ON CARS FOR EACH ROW WHEN (NEW.Car_Id IS NULL) BEGIN :NEW.Car_Id := SEQ_CARS.NEXTVAL;
END;
/

CREATE SEQUENCE SEQ_CUSTOMERS START WITH 1 NOCACHE;
CREATE OR REPLACE TRIGGER AUTO_SEQ_CUSTOMERS_ID BEFORE
  INSERT ON CUSTOMERS FOR EACH ROW WHEN (NEW.Customer_Id IS NULL) BEGIN :NEW.Customer_Id := SEQ_CUSTOMERS.NEXTVAL;
END;
/

CREATE SEQUENCE SEQ_TRANSACTIONS START WITH 1 NOCACHE ;
CREATE OR REPLACE TRIGGER AUTO_SEQ_TRANSACTIONS_ID BEFORE
  INSERT ON TRANSACTIONS FOR EACH ROW WHEN (NEW.Transaction_Id IS NULL) BEGIN :NEW.Transaction_Id := SEQ_TRANSACTIONS.NEXTVAL;
END;
/

CREATE OR REPLACE TRIGGER TRANSACTIONS_Start_date_TRIGG BEFORE
  INSERT ON TRANSACTIONS FOR EACH ROW 
  DECLARE
  now_date TRANSACTIONS.START_DATE%Type;
   BEGIN 
   SELECT current_date into now_date FROM DUAL;
  	 IF :NEW.start_date <  TRUNC(now_date) THEN
  		RAISE_APPLICATION_ERROR(-20002, 'You are trying pick up date from the past');
  	END IF;
END;
/
