package com.rentacar.mvc.repository;

import java.util.List;
import com.rentacar.mvc.doamin.Customer;

public interface CustomerRepository {
	
	void addCustomerPerson(Customer customer);
	
	void addCustomerCompany(Customer customer);

	Customer getCustomerByEmail(String email);
	
	List <Customer> getAllCustomers();

	int count();
}
