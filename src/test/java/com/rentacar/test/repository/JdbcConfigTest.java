package com.rentacar.test.repository;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import com.rentacar.mvc.repository.CarRepository;
import com.rentacar.mvc.repository.CustomerRepository;
import com.rentacar.mvc.repository.impl.JdbcCarRepository;
import com.rentacar.mvc.repository.impl.JdbcCustomerRepository;

@Configuration
public class JdbcConfigTest {
	
	@Bean
	  public DataSource dataSource() {
	    return new EmbeddedDatabaseBuilder()
	      .setType(EmbeddedDatabaseType.H2)
	      .addScripts("classpath:h2/schema.sql", "classpath:h2/test-data.sql")
	      .build();
	}
	
	@Bean
	  public CarRepository carRepository(DataSource dataSource) {
	    return new JdbcCarRepository(dataSource);
	}
	
	@Bean
	public CustomerRepository customerRepository(DataSource dataSource){
		return new JdbcCustomerRepository(dataSource);
	}
	
	@Bean
	  public PlatformTransactionManager transactionManager(DataSource dataSource) {
	    return new DataSourceTransactionManager(dataSource);
	  }
}
