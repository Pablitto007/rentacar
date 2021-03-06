package com.rentacar.test.context;

import static org.junit.Assert.assertNotNull;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.rentacar.mvc.repository.CustomerRepository;
import com.rentacar.mvc.service.CustomerService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/webcontext/Root-config.xml", "classpath:/webcontext/Data-config.xml"})
public class ContextAndBeansTest {
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserDetailsService jdbcDetailsService;
	
	@Test
	public void jdbcDetServNotNull(){
		assertNotNull(jdbcDetailsService);
	}
	
	@Test
	public void serviceNotNull(){
	assertNotNull(customerService);
	}
	
	@Test
	public void repoNotNull(){
	assertNotNull(repository);
	}
	
	@Test
	public void sourceNotNull(){
	assertNotNull(dataSource);
	}
}
	
