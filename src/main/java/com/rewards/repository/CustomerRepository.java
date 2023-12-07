package com.rewards.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rewards.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	
	/*
	 * @Override default Optional<Customer> findById(Integer id) { // TODO
	 * Auto-generated method stub return Optional.empty(); }
	 */
	public Optional<Customer> findCustomerByCustomerId(Integer customerId);

}
