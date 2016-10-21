package com.rentacar.mvc.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rentacar.mvc.doamin.Customer;
import com.rentacar.mvc.repository.CustomerRepository;


@Repository
public class JdbcCustomerRepository implements CustomerRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcCustomerRepository (DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final String INSERT_SQL_PERSON = "INSERT INTO CUSTOMERS (CUSTOMER_ID, STREET_ADDRESS,"
			+ "CITY, POSTAL_CODE, PERSON_NAME,"
			+ "PERSON_SURNAME, ID_CARD, CUSTOMER_TYPE, EMAIL, PASSWORD) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private static final String INSERT_SQL_COMPANY = "INSERT INTO CUSTOMERS (CUSTOMER_ID, STREET_ADDRESS,"
			+ " CITY, POSTAL_CODE, COMPANY_NAME, TAX_ID,"
			+  " CUSTOMER_TYPE, EMAIL, PASSWORD) VALUES (?,?,?,?,?,?,?,?,?)";
	
	
//	private static final String SELECT_SQL = "SELECT CUSTOMER_ID, STREET_ADDRESS,"
//			+ "CITY, POSTAL_CODE, COMPANY_NAME,"
//			+ "TAX_ID, PERSON_NAME,"
//			+ "PERSON_SURNAME, ID_CARD, CUSTOMER_TYPE, EMAIL, PASSWORD FROM CUSTOMERS WHERE EMAIL = ?";
	
	private static final String SELECT_SQL = "SELECT * FROM CUSTOMERS WHERE EMAIL = ?";
	
	
	public int count(){
		return this.jdbcTemplate.queryForObject("SELECT COUNT(CUSTOMER_ID) FROM CUSTOMERS", Integer.class);
	}
	
	@Override
	public List <Customer> getAllCustomers() {
		 return this.jdbcTemplate.query("SELECT * FROM CUSTOMERS ORDER BY CUSTOMER_ID", new CustomerMapper());
	}


	@Override
	public Customer getCustomerByEmail(String email) {
		return this.jdbcTemplate.queryForObject(SELECT_SQL, new CustomerMapper(), email);
	}
	

	@Override
	public void addCustomerPerson(Customer customer) {
		this.jdbcTemplate.update(INSERT_SQL_PERSON, new Object[] {
				customer.getCustomerId(), customer.getStreetAddress(), customer.getCity(), customer.getPostalCode(),
				customer.getPersonName(), customer.getPersonSurname(), customer.getIdCard(), "PERS",
				customer.getEmail(), customer.getPassword() });
	}

	@Override
	public void addCustomerCompany(Customer customer) {
		this.jdbcTemplate.update(INSERT_SQL_COMPANY, new Object[] {
				customer.getCustomerId(), customer.getStreetAddress(), customer.getCity(), customer.getPostalCode(),
				customer.getCompanyName(), customer.getTaxId(), "COMP",
				customer.getEmail(), customer.getPassword()});
	}

	private static final class CustomerMapper implements RowMapper<Customer> {

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt("customer_id"));
			customer.setStreetAddress(rs.getString("street_address"));
			customer.setCity(rs.getString("city"));
			customer.setPostalCode(rs.getString("postal_code"));
			customer.setCompanyName(rs.getString("company_name"));
			customer.setTaxId(rs.getString("tax_id"));
			customer.setPersonName(rs.getString("person_name"));
			customer.setPersonSurname(rs.getString("person_surname"));
			customer.setIdCard(rs.getString("id_card"));
			customer.setCustomerType(rs.getString("customer_type"));
			customer.setEmail(rs.getString("email"));
			customer.setPassword(rs.getString("password"));
	
			return customer;
		}
	}
}
