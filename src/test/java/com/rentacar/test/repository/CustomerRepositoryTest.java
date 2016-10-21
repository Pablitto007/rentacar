package com.rentacar.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import javax.sql.DataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.rentacar.mvc.doamin.Customer;
import com.rentacar.mvc.repository.CustomerRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=JdbcConfigTest.class)
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;	
	
	@Autowired
	DataSource dataSource;
	
	@Test
	public void dataSourceNotNull(){
		assertNotNull(dataSource);
	}
	
	@Test
	public void customerRepoNotNull(){
		assertNotNull(customerRepository);
	}
	
	@Test
	@Transactional
	public void shouldAddCustomerPerson(){
		int counter = customerRepository.count();
		customerRepository.addCustomerPerson(new Customer(5, "Some Street 2", 
				"Warsaw", "09-222", null, null, "James", "Bond", "AUH12345", "PERS",  "jamesb@buziaczek.pl", "passMe"));
		assertEquals(counter +1, customerRepository.count());
	}
	
	@Test
	@Transactional
	public void shouldAddCustomerCompany(){
		int counter = customerRepository.count();
		customerRepository.addCustomerPerson(new Customer(6, "Some Street 20", 
				"Warsaw", "00-222", "IT Company", "123555", null, null, null, "COMP",  "it@company.pl", "passMePls"));
		assertEquals(counter +1, customerRepository.count());
	}
	
	@Test
	@Transactional
	public void shouldGetCustomerByEmail(){
		Customer customerPerson = customerRepository.getCustomerByEmail("jg@java.com");
		Customer customerCompany = customerRepository.getCustomerByEmail("it@compay.com");
		assertCustomer(1, customerPerson);
		assertCustomer(3, customerCompany);
	}
	
	@Test
	@Transactional
	public void shouldGetAllCustomers(){
		List<Customer> customersList = customerRepository.getAllCustomers();
		assertCustomer(0, customersList.get(0));
		assertCustomer(2, customersList.get(2));
	}
	
	 private static void assertCustomer(int customersIndex, Customer actual){
		Customer customer = CUSTOMERS[customersIndex];
		assertEquals(customer, actual);
	}
	
	private static final Customer [] CUSTOMERS = new Customer [4];
	
	@BeforeClass
	public static void before(){
		CUSTOMERS[0] = new Customer(1, "Some Street 22", "Warsaw", 
				"00-000", null, null, "John", "Klamka", "ABC123", "PERS", "john.klamka@com", "passMe");
		CUSTOMERS[1] = new Customer(2, "JAVA Avenue 8", "NY", 
				"88-888", null, null, "James", "Gosling", "123ABC", "PERS", "jg@java.com", "passMe");
		CUSTOMERS[2] = new Customer(3, "Test Street 1", "Warsaw", 
				"55-555", "Some Company c.o.", "123456", null, null, null, "COMP", "some@compay.com" ,"passCompany");
		CUSTOMERS[3] = new Customer(4, "Some Address 90", "Berlin", 
				"11-555", "IT Company c.o.", "100200", null, null, null, "COMP", "it@compay.com", "passCompany");
		
	}
}
