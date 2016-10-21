CREATE TABLE CARS
  (
    Car_Id              INTEGER PRIMARY KEY ,
    VIN                 VARCHAR (30) NOT NULL ,
    Brand               VARCHAR (20) NOT NULL ,
    Model               VARCHAR (20) NOT NULL ,
    Year_of_Manufacture INTEGER  NOT NULL ,
    Price_Per_Day       INTEGER  NOT NULL ,
    Description         VARCHAR (700)
  ) ;

CREATE TABLE CUSTOMERS
  (
    Customer_Id    INTEGER PRIMARY KEY ,
    Street_Address VARCHAR (40) NOT NULL ,
    City           VARCHAR (30) NOT NULL ,
    Postal_Code    VARCHAR (7) NOT NULL ,
    Company_Name   VARCHAR (30) ,
    Tax_Id         VARCHAR (10) ,
    Person_Name    VARCHAR (30) ,
    Person_Surname VARCHAR (30) ,
    Id_Card        VARCHAR (30) ,
    Customer_Type  VARCHAR (4) NOT NULL ,
    email          VARCHAR (50) NOT NULL ,
    password       VARCHAR (70) NOT NULL
  ) ;

CREATE TABLE TRANSACTIONS
  (
    Transaction_Id INTEGER PRIMARY KEY ,
    Start_Date     DATE NOT NULL ,
    End_Date       DATE NOT NULL ,
    Car_Id         INTEGER  ,
    Customer_Id    INTEGER
  );
