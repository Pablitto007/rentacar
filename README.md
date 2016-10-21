# rentacar
Rent a car system.
Web application that simulates simply rent a car company software.
App is able to store data on cars, customers and rental transactions. 
You can do some CRUD opertions on stored in Oracle DB data.
Another functionality are authentication and authorization based on Spring Security.
Please note that Customers model has two subtypes (Persons and Compmanies)  ans is implemented as single table in database. For this purpose I needed more control over it so JDBC Template from Spring was my choose.

Used technologies: Java 8, 7, Spring-MVC 4, Spring Security, Spring JDBC Template, Oracle DBMS, JSP (JSTL, CSS Bootstrap)
Tests: JUnit, Mockito, Power Mockito
Used Tools: Spring Tool Site, Oracle SQL Developer Data Modeler.
