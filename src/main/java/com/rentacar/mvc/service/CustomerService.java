package com.rentacar.mvc.service;

import com.rentacar.mvc.doamin.Customer;

public interface CustomerService {
	
	void addCustomerPerson(Customer customer);
	
	void addCustomerCompany(Customer customer);
	
	Customer getCustomerByEmail(String email);
	
	Integer getIdFromLoggedCustomer(String email);
	
}
