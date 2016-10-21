package com.rentacar.mvc.repository;

import com.rentacar.mvc.doamin.Transaction;

public interface TransactionRepository {

	void saveTransaction(Transaction transaction);
}
