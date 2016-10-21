package com.rentacar.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rentacar.mvc.doamin.Customer;
import com.rentacar.mvc.service.CustomerService;

@Service
public class JdbcDetailsService extends JdbcDaoImpl {
	
	private CustomerService customerService;
	
	@Autowired
	public JdbcDetailsService(CustomerService customerService){
		this.customerService = customerService;
	}
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer;
		customer = customerService.getCustomerByEmail(username);
		
		if (customer == null) {
			throw new UsernameNotFoundException("User details not found with this username : " + username);
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if (username.equalsIgnoreCase("admin@admin.com")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else{
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		
		return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
				authorities);
	}

}
