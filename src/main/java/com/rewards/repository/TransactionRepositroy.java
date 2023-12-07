package com.rewards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rewards.entity.Transaction;

@Repository
public interface TransactionRepositroy extends CrudRepository<Transaction, Integer>{

	public List<Transaction> findAllByCustomerId(Integer customerId);
}
