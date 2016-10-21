package com.rentacar.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentacar.mvc.doamin.Customer;
import com.rentacar.mvc.repository.CustomerRepository;
import com.rentacar.mvc.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	@Transactional
	public void addCustomerPerson(Customer customer) {
		String hashedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
		customer.setPassword(hashedPassword);
		customerRepository.addCustomerPerson(customer);
	}
	
	@Override
	@Transactional
	public void addCustomerCompany(Customer customer) {
		String hashedPassword = bCryptPasswordEncoder.encode(customer.getPassword());
		customer.setPassword(hashedPassword);
		customerRepository.addCustomerCompany(customer);
	}

	@Override
	@Transactional
	public Customer getCustomerByEmail(String email) {
		return customerRepository.getCustomerByEmail(email);
	}
	
	@Override
	@Transactional
	public Integer getIdFromLoggedCustomer(String email) {
		Customer customer = customerRepository.getCustomerByEmail(email);
		return customer.getCustomerId();
	}
}
