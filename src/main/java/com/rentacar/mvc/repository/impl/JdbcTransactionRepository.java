package com.rentacar.mvc.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rentacar.mvc.doamin.Transaction;
import com.rentacar.mvc.repository.TransactionRepository;

@Repository
public class JdbcTransactionRepository implements TransactionRepository {
	
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcTransactionRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private static final String INSERT_SQL_TRAN = "INSERT INTO TRANSACTIONS (TRANSACTION_ID, START_DATE, "
			+ "END_DATE, CAR_ID, CUSTOMER_ID) VALUES(?,?,?,?,?)";

	@Override
	public void saveTransaction(Transaction transaction) {
		this.jdbcTemplate.update(INSERT_SQL_TRAN, new Object[] {
				transaction.getTransactionId(), transaction.getStart(), transaction.getEnd(), transaction.getCarId(),
				transaction.getCustomerId()		
		});
	}

}
