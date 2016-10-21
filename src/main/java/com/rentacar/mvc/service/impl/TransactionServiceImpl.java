package com.rentacar.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentacar.mvc.doamin.Transaction;
import com.rentacar.mvc.repository.TransactionRepository;
import com.rentacar.mvc.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository){
		this.transactionRepository = transactionRepository;
	}

	@Override
	@Transactional
	public void saveTransaction(Transaction transaction) {
		transactionRepository.saveTransaction(transaction);

	}

}
