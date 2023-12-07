package com.rewards.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rewards.entity.Customer;
import com.rewards.model.Rewards;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.RewardsService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class RewardsController {
	
	@Autowired
    RewardsService rewardsService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId")@Valid Integer  customerId){
    	
        Optional<Customer> customer = customerRepository.findById(customerId);
       
        if(customer.isEmpty())
        {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Customer Id Not found");
        }
        Rewards rewards = rewardsService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(rewards,HttpStatus.OK);
    }
}
